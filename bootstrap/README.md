# Nasdanika CLI Bootstrap (`bootstrap`)

A tiny, **dependency-free** bootstrap jar that makes a Nasdanika CLI
distribution runnable from **any working directory** out of the box.

It is the implementation of "Option A" in
[`launcher-design.md`](../launcher-design.md).

## The problem it solves

A Nasdanika CLI distribution runs on a mixed **module-path + class-path** layout
with ~1000 jars under `<home>/lib`. The build (`BuildDistributionIT`) already
computes the correct split and writes it to an argfile (`<home>/options`) — but
with paths **relative** to the distribution root:

```
-p "lib/.../a.jar;lib/.../b.jar" --add-modules ALL-SYSTEM -classpath "lib/.../c.jar" -m org.nasdanika.launcher/org.nasdanika.launcher.Launcher
```

Java resolves those `lib/...` paths against the **current working directory**,
and `%~dp0` / `$0` are not expanded inside argfiles. So `nsd` / `nsd.bat` only
work when the CWD is the install directory.

## How it works

`bootstrap.jar`:

1. **Finds the distribution root** from its own jar location (via the code
   source), walking up to the nearest `lib` directory — independent of the
   working directory and of the Maven group depth it is deployed under.
2. **Reads the build-time launch options** and rewrites every relative
   `lib/...` entry to an absolute path under the root. (No re-classification of
   jars — it reuses the split the build already computed.)
3. **Writes a temporary argfile** with absolute paths. An argfile is required
   because ~1000 absolute paths exceed the Windows `CreateProcess` 32&nbsp;KB
   command-line limit.
4. **Launches a child JVM** — `java @<argfile> <user args>` — with stdin/stdout/
   stderr wired through (`inheritIO`) and the exit code propagated.

The child inherits the user's real working directory, so relative file arguments
to CLI commands keep working, while module-path/class-path are absolute.

## Wrapper scripts become trivial

```bat
@java -jar "%~dp0bootstrap.jar" %*
```

```bash
#!/bin/bash
exec java -jar "$(dirname "$0")/bootstrap.jar" "$@"
```

These are size-proof (no more externalized `options` in the script) and work
from any directory.

## Configuration (all optional)

Defaults are derived from the generated distribution. To override, drop a
`bootstrap.conf` (`java.util.Properties` format) in the distribution root:

| Property | Meaning | Default |
|---|---|---|
| `options` | Name of the launch argfile to read | `options` |
| `main` | `<module>/<main-class>` to launch (overrides `-m` in the argfile) | from argfile |
| `module.path` | Explicit module-path list (path-separator/newline separated, relative to root) | from argfile |
| `class.path` | Explicit class-path list | from argfile |
| `java` | Java executable (absolute or relative to root) | see below |
| `jvm.options` | Extra JVM options (whitespace separated) | none |

### Environment variables

| Variable | Meaning |
|---|---|
| `BOOTSTRAP_HOME` | Force the distribution root |
| `BOOTSTRAP_JAVA` | Force the java executable |
| `BOOTSTRAP_JAVA_OPTIONS` | Extra JVM options for this invocation (e.g. a `-debug` wrapper injects `-Xdebug -Xrunjdwp:...`) |

### Java executable resolution order

`BOOTSTRAP_JAVA` → `java` config property → **the exact executable that launched
the bootstrap** (`ProcessHandle.current().info().command()`) → `java.home` →
bundled `<home>/jdk/bin/java` → `java` on `PATH`.

Reusing the launching executable is the most robust option: the child runs on
the same JVM as the bootstrap, and a bundled JDK is honoured automatically
whenever the wrapper script invokes it. `resolveJavaExecutable` is `protected`,
so subclasses can override the selection policy.

## Integrating with a distribution build

`bootstrap` is meant to be pulled in as a normal Maven dependency of a CLI
assembly (e.g. Nasdanika CLI), so `maven-dependency-plugin`'s `copy-dependencies`
drops it into `lib/` with the rest. The generator (`BuildDistributionIT`) then
emits the one-line wrapper scripts above instead of embedding the full argfile.

Because home detection is depth-independent, the coordinates it is deployed
under do not matter — the bootstrap resolves the distribution root by walking up
to the enclosing `lib` directory.

## Building

```
mvn -f bootstrap/pom.xml clean package
```

Produces `target/bootstrap-<version>.jar` with `Main-Class` and
`Automatic-Module-Name: org.nasdanika.core.bootstrap` in its manifest.

## Notes / caveats

- Requires only `java.base`; no third-party dependencies.
- The child process shares the console, so JLine-based interactive shells work;
  worth an end-to-end smoke test on Windows and Linux.
- Targets Java 21 to match the Nasdanika ecosystem.
