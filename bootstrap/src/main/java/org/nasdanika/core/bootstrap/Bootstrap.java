package org.nasdanika.core.bootstrap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * Dependency-free bootstrap for a Nasdanika CLI distribution.
 *
 * <p>A Nasdanika CLI distribution runs on a mixed module-path + class-path
 * layout with ~1000 jars under {@code <home>/lib}. The exact split between the
 * module path and the class path is computed at build time and materialized in
 * an argfile (by default {@code <home>/options}) with paths <em>relative</em> to
 * the distribution root. Java resolves those relative paths against the current
 * working directory, and {@code %~dp0}/{@code $0} are not expanded inside
 * argfiles, so the generated {@code nsd}/{@code nsd.bat} scripts only work when
 * invoked from the install directory.</p>
 *
 * <p>This bootstrap fixes that. It:</p>
 * <ol>
 *   <li>Determines the distribution root ({@code home}) from its own jar
 *       location &mdash; independent of the working directory.</li>
 *   <li>Reads the build-time launch options and rewrites every relative
 *       {@code lib/...} entry to an absolute path under {@code home}.</li>
 *   <li>Writes a temporary argfile (an argfile is required because ~1000
 *       absolute paths exceed the Windows {@code CreateProcess} 32&nbsp;KB
 *       command-line limit).</li>
 *   <li>Launches a child JVM ({@code java @argfile <user args>}), wiring
 *       stdin/stdout/stderr through and propagating the exit code.</li>
 * </ol>
 *
 * <p>The child JVM inherits the user's real working directory, so relative file
 * arguments passed to CLI commands keep resolving as the user expects, while the
 * class path and module path are absolute and install-relative.</p>
 *
 * <h2>Configuration</h2>
 * <p>All behaviour has sensible defaults derived from the generated
 * distribution. An optional {@code <home>/bootstrap.conf}
 * ({@link java.util.Properties} format) can override:</p>
 * <ul>
 *   <li>{@code options} &ndash; name of the launch argfile to read
 *       (default {@code options}).</li>
 *   <li>{@code main} &ndash; the {@code <module>/<main-class>} to launch,
 *       overriding the {@code -m} entry from the argfile.</li>
 *   <li>{@code module.path} / {@code class.path} &ndash; explicit lists (path
 *       separator or newline separated, relative to {@code home}) that replace
 *       the values parsed from the argfile.</li>
 *   <li>{@code java} &ndash; java executable (absolute, or relative to
 *       {@code home}).</li>
 *   <li>{@code jvm.options} &ndash; extra JVM options (whitespace separated).</li>
 * </ul>
 *
 * <h2>Environment variables</h2>
 * <ul>
 *   <li>{@code BOOTSTRAP_HOME} &ndash; force the distribution root.</li>
 *   <li>{@code BOOTSTRAP_JAVA} &ndash; force the java executable.</li>
 *   <li>{@code BOOTSTRAP_JAVA_OPTIONS} &ndash; extra JVM options appended for
 *       this invocation (e.g. used by a {@code -debug} wrapper script).</li>
 * </ul>
 */
public class Bootstrap {

	/** Optional configuration file, looked up in the distribution root. */
	static final String CONFIG_FILE = "bootstrap.conf";

	/** Default name of the build-time launch argfile in the distribution root. */
	static final String DEFAULT_OPTIONS_FILE = "options";

	/**
	 * Subclasses may extend this bootstrap to override policy methods such as
	 * {@link #resolveJavaExecutable(Path, Properties)}. A subclass provides its
	 * own {@code main} that constructs it and calls {@link #launch(String[])}.
	 */
	protected Bootstrap() {
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		new Bootstrap().launch(args);
	}

	/**
	 * Resolves the distribution, builds the child JVM invocation from the
	 * build-time launch options (rewritten to absolute paths) and relaunches it,
	 * wiring stdin/stdout/stderr through and propagating the exit code.
	 */
	public void launch(String[] args) throws IOException, InterruptedException {
		Path home = resolveHome();
		Properties config = loadConfig(home);

		LaunchSpec spec = buildSpec(home, config);

		Path argFile = Files.createTempFile("bootstrap", ".args");
		// Belt and suspenders: deleteOnExit hooks run even on System.exit().
		argFile.toFile().deleteOnExit();
		try {
			writeArgFile(argFile, spec);

			List<String> command = new ArrayList<>();
			command.add(resolveJavaExecutable(home, config));
			command.add("@" + argFile.toAbsolutePath());
			for (String arg : args) {
				command.add(arg);
			}

			Process process = new ProcessBuilder(command).inheritIO().start();
			int exitCode = process.waitFor();
			Files.deleteIfExists(argFile);
			System.exit(exitCode);
		} catch (IOException | InterruptedException | RuntimeException e) {
			try {
				Files.deleteIfExists(argFile);
			} catch (IOException suppressed) {
				e.addSuppressed(suppressed);
			}
			throw e;
		}
	}

	// ------------------------------------------------------------------
	// Distribution root resolution
	// ------------------------------------------------------------------

	/**
	 * Resolves the distribution root. Honours {@code BOOTSTRAP_HOME} /
	 * {@code bootstrap.home}; otherwise walks up from this jar to the nearest
	 * ancestor {@code lib} directory and returns its parent. This is independent
	 * of the Maven group depth, so it works regardless of the coordinates under
	 * which the jar is deployed.
	 */
	static Path resolveHome() {
		String override = System.getProperty("bootstrap.home");
		if (override == null || override.isBlank()) {
			override = System.getenv("BOOTSTRAP_HOME");
		}
		if (override != null && !override.isBlank()) {
			return Path.of(override).toAbsolutePath().normalize();
		}

		Path jar = locateJar();
		for (Path ancestor = jar.getParent(); ancestor != null; ancestor = ancestor.getParent()) {
			Path name = ancestor.getFileName();
			if (name != null && "lib".equals(name.toString())) {
				Path parent = ancestor.getParent();
				if (parent != null) {
					return parent;
				}
			}
		}
		// Fallback: the directory containing the jar (e.g. running from a flat dir).
		Path parent = jar.getParent();
		return parent != null ? parent : Path.of(".").toAbsolutePath().normalize();
	}

	/** Locates this jar (or classes directory) via its code source. */
	static Path locateJar() {
		try {
			CodeSource codeSource = Bootstrap.class.getProtectionDomain().getCodeSource();
			if (codeSource != null && codeSource.getLocation() != null) {
				return Path.of(codeSource.getLocation().toURI()).toAbsolutePath().normalize();
			}
		} catch (URISyntaxException e) {
			// fall through
		}
		throw new IllegalStateException(
				"Unable to determine bootstrap location; set BOOTSTRAP_HOME or -Dbootstrap.home");
	}

	// ------------------------------------------------------------------
	// Configuration
	// ------------------------------------------------------------------

	static Properties loadConfig(Path home) throws IOException {
		Properties config = new Properties();
		Path configFile = home.resolve(CONFIG_FILE);
		if (Files.isRegularFile(configFile)) {
			try (var in = Files.newInputStream(configFile)) {
				config.load(in);
			}
		}
		return config;
	}

	// ------------------------------------------------------------------
	// Launch specification
	// ------------------------------------------------------------------

	/** The resolved, absolute pieces of the child {@code java} invocation. */
	static final class LaunchSpec {
		final List<String> modulePath = new ArrayList<>();
		final List<String> classPath = new ArrayList<>();
		final List<String> jvmOptions = new ArrayList<>();
		String main;
	}

	static LaunchSpec buildSpec(Path home, Properties config) throws IOException {
		LaunchSpec spec = new LaunchSpec();

		String optionsName = config.getProperty("options", DEFAULT_OPTIONS_FILE);
		Path optionsFile = home.resolve(optionsName);
		if (Files.isRegularFile(optionsFile)) {
			parseOptions(home, Files.readString(optionsFile, StandardCharsets.UTF_8), spec);
		} else if (!config.containsKey("module.path") && !config.containsKey("class.path")) {
			throw new IllegalStateException("Launch options file not found: " + optionsFile
					+ System.lineSeparator()
					+ "Provide the file, a '" + CONFIG_FILE + "' with module.path/class.path, "
					+ "or set -Dbootstrap.home / BOOTSTRAP_HOME to the distribution root.");
		}

		// Explicit config overrides win over whatever was parsed from the argfile.
		if (config.containsKey("module.path")) {
			spec.modulePath.clear();
			spec.modulePath.addAll(resolveEntries(home, config.getProperty("module.path")));
		}
		if (config.containsKey("class.path")) {
			spec.classPath.clear();
			spec.classPath.addAll(resolveEntries(home, config.getProperty("class.path")));
		}
		if (config.containsKey("main")) {
			spec.main = config.getProperty("main");
		}

		addExtraJvmOptions(spec, config);

		if (spec.main == null || spec.main.isBlank()) {
			throw new IllegalStateException("No main module/class resolved. "
					+ "Add a '-m <module>/<main-class>' entry to " + optionsFile
					+ " or a 'main' property to " + CONFIG_FILE + ".");
		}
		return spec;
	}

	/**
	 * Parses a Java argfile produced by the distribution build, collecting the
	 * module path, class path, main module/class and any other JVM options.
	 * Relative path entries are made absolute against {@code home}.
	 */
	static void parseOptions(Path home, String content, LaunchSpec spec) {
		List<String> tokens = tokenize(content);
		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			switch (token) {
				case "-p", "--module-path" -> spec.modulePath.addAll(resolveEntries(home, next(tokens, ++i)));
				case "-classpath", "-cp", "--class-path" -> spec.classPath.addAll(resolveEntries(home, next(tokens, ++i)));
				case "-m", "--module" -> spec.main = next(tokens, ++i);
				default -> {
					if (startsWithKey(token, "--module-path=")) {
						spec.modulePath.addAll(resolveEntries(home, valueOf(token)));
					} else if (startsWithKey(token, "--class-path=")) {
						spec.classPath.addAll(resolveEntries(home, valueOf(token)));
					} else if (startsWithKey(token, "--module=")) {
						spec.main = valueOf(token);
					} else {
						// Preserve any other JVM option verbatim (e.g. --add-modules ALL-SYSTEM,
						// --add-opens, -X..., -D...). Two-token options are preserved because the
						// value is simply the next token, which falls through here as well.
						spec.jvmOptions.add(token);
					}
				}
			}
		}
	}

	private static boolean startsWithKey(String token, String key) {
		return token.startsWith(key);
	}

	private static String valueOf(String token) {
		return token.substring(token.indexOf('=') + 1);
	}

	private static String next(List<String> tokens, int index) {
		if (index >= tokens.size()) {
			throw new IllegalStateException("Missing value after option in launch argfile");
		}
		return tokens.get(index);
	}

	static void addExtraJvmOptions(LaunchSpec spec, Properties config) {
		addWhitespaceSeparated(spec.jvmOptions, config.getProperty("jvm.options"));
		addWhitespaceSeparated(spec.jvmOptions, System.getenv("BOOTSTRAP_JAVA_OPTIONS"));
	}

	private static void addWhitespaceSeparated(List<String> target, String value) {
		if (value != null && !value.isBlank()) {
			for (String part : value.trim().split("\\s+")) {
				target.add(part);
			}
		}
	}

	// ------------------------------------------------------------------
	// Path handling
	// ------------------------------------------------------------------

	/**
	 * Splits a path-list value on {@code ;} or {@code :} and returns absolute,
	 * forward-slashed path strings. Entries in build-time argfiles are always
	 * relative ({@code lib/...}), so splitting on both separators makes a single
	 * generated argfile usable on any OS.
	 */
	static List<String> resolveEntries(Path home, String value) {
		List<String> result = new ArrayList<>();
		if (value == null) {
			return result;
		}
		for (String raw : value.split("[;:\\n\\r]+")) {
			String entry = raw.trim();
			if (entry.isEmpty()) {
				continue;
			}
			Path path = Path.of(entry);
			if (!path.isAbsolute()) {
				path = home.resolve(entry);
			}
			result.add(path.toAbsolutePath().normalize().toString().replace('\\', '/'));
		}
		return result;
	}

	// ------------------------------------------------------------------
	// Argfile generation
	// ------------------------------------------------------------------

	static void writeArgFile(Path argFile, LaunchSpec spec) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(argFile, StandardCharsets.UTF_8)) {
			if (!spec.modulePath.isEmpty()) {
				writeLine(writer, "--module-path");
				writeLine(writer, quoteJoin(spec.modulePath));
			}
			if (!spec.classPath.isEmpty()) {
				writeLine(writer, "-classpath");
				writeLine(writer, quoteJoin(spec.classPath));
			}
			for (String option : spec.jvmOptions) {
				writeLine(writer, option);
			}
			// The main module/class must come last: everything after it is passed
			// to the application as program arguments.
			writeLine(writer, "-m");
			writeLine(writer, spec.main);
		}
	}

	private static void writeLine(BufferedWriter writer, String line) throws IOException {
		writer.write(line);
		writer.newLine();
	}

	/**
	 * Joins absolute paths with the platform path separator and wraps the result
	 * in double quotes. Paths use forward slashes so the backslash (an escape
	 * character inside Java argfiles) never appears.
	 */
	static String quoteJoin(List<String> paths) {
		return '"' + String.join(File.pathSeparator, paths) + '"';
	}

	// ------------------------------------------------------------------
	// Java executable resolution
	// ------------------------------------------------------------------

	/**
	 * Resolves the {@code java} executable used to launch the child JVM.
	 *
	 * <p>Resolution order:</p>
	 * <ol>
	 *   <li>{@code BOOTSTRAP_JAVA} environment variable.</li>
	 *   <li>{@code java} configuration property (absolute, or relative to the
	 *       distribution root).</li>
	 *   <li><strong>The exact executable that launched this bootstrap JVM</strong>
	 *       (via {@link ProcessHandle}). This is the most robust option: the
	 *       child runs on the same JVM as the bootstrap, and a bundled JDK is
	 *       honoured automatically whenever the wrapper script invokes it.</li>
	 *   <li>The running JVM's {@code java.home} (fallback if the OS does not
	 *       report the launching command).</li>
	 *   <li>A bundled {@code <home>/jdk/bin/java} (fallback).</li>
	 *   <li>{@code java} on the {@code PATH} (last resort).</li>
	 * </ol>
	 *
	 * <p>{@code protected} so subclasses can customise executable selection.</p>
	 */
	protected String resolveJavaExecutable(Path home, Properties config) {
		String env = System.getenv("BOOTSTRAP_JAVA");
		if (env != null && !env.isBlank()) {
			return env;
		}

		String configured = config.getProperty("java");
		if (configured != null && !configured.isBlank()) {
			Path path = Path.of(configured);
			return (path.isAbsolute() ? path : home.resolve(configured)).toString();
		}

		// Relaunch with the exact executable that started this JVM. More robust
		// than reconstructing a path, and transparently reuses a bundled JDK when
		// the wrapper script launched the bootstrap with it.
		Optional<String> current = ProcessHandle.current().info().command();
		if (current.isPresent() && !current.get().isBlank()) {
			return current.get();
		}

		String exe = isWindows() ? "java.exe" : "java";

		String javaHome = System.getProperty("java.home");
		if (javaHome != null && !javaHome.isBlank()) {
			Path candidate = Path.of(javaHome, "bin", exe);
			if (Files.isRegularFile(candidate)) {
				return candidate.toString();
			}
		}

		Path bundled = home.resolve("jdk").resolve("bin").resolve(exe);
		if (Files.isRegularFile(bundled)) {
			return bundled.toString();
		}

		return exe;
	}

	private static boolean isWindows() {
		return System.getProperty("os.name", "").toLowerCase().contains("win");
	}

	// ------------------------------------------------------------------
	// Argfile tokenizer
	// ------------------------------------------------------------------

	/**
	 * Splits argfile content into tokens on whitespace, honouring double quotes
	 * (which are stripped). Sufficient for the argfiles produced by the
	 * distribution build.
	 */
	static List<String> tokenize(String content) {
		List<String> tokens = new ArrayList<>();
		StringBuilder current = new StringBuilder();
		boolean inQuotes = false;
		boolean hasToken = false;
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			if (inQuotes) {
				if (c == '"') {
					inQuotes = false;
				} else {
					current.append(c);
					hasToken = true;
				}
			} else if (c == '"') {
				inQuotes = true;
				hasToken = true;
			} else if (Character.isWhitespace(c)) {
				if (hasToken) {
					tokens.add(current.toString());
					current.setLength(0);
					hasToken = false;
				}
			} else {
				current.append(c);
				hasToken = true;
			}
		}
		if (hasToken) {
			tokens.add(current.toString());
		}
		return tokens;
	}

}
