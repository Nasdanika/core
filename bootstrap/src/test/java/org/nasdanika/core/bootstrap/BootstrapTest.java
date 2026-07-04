package org.nasdanika.core.bootstrap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.nasdanika.core.bootstrap.Bootstrap.LaunchSpec;

class BootstrapTest {

	@Test
	void tokenizeHonoursQuotes() {
		List<String> tokens = Bootstrap.tokenize("-p \"a;b;c\" --add-modules ALL-SYSTEM -m x/y");
		assertLinesMatch(List.of("-p", "a;b;c", "--add-modules", "ALL-SYSTEM", "-m", "x/y"), tokens);
	}

	@Test
	void resolveEntriesMakesPathsAbsoluteAndForwardSlashed(@TempDir Path home) {
		List<String> entries = Bootstrap.resolveEntries(home, "lib/a.jar;lib/b.jar");
		assertEquals(2, entries.size());
		String expectedA = home.resolve("lib/a.jar").toAbsolutePath().normalize().toString().replace('\\', '/');
		assertEquals(expectedA, entries.get(0));
		assertTrue(entries.get(0).indexOf('\\') < 0, "no backslashes in argfile paths");
	}

	@Test
	void resolveEntriesSplitsOnColonToo(@TempDir Path home) {
		// A build-time argfile generated for Linux uses ':' between relative entries.
		List<String> entries = Bootstrap.resolveEntries(home, "lib/a.jar:lib/b.jar");
		assertEquals(2, entries.size());
	}

	@Test
	void parseOptionsSplitsModuleAndClassPathAndMain(@TempDir Path home) {
		String options = "-p \"lib/mp1.jar;lib/mp2.jar\" --add-modules ALL-SYSTEM "
				+ "-classpath \"lib/cp1.jar;lib/cp2.jar\" -m org.example/org.example.Main";
		LaunchSpec spec = new LaunchSpec();
		Bootstrap.parseOptions(home, options, spec);

		assertEquals(2, spec.modulePath.size());
		assertEquals(2, spec.classPath.size());
		assertEquals("org.example/org.example.Main", spec.main);
		assertLinesMatch(List.of("--add-modules", "ALL-SYSTEM"), spec.jvmOptions);
	}

	@Test
	void buildSpecReadsOptionsFileFromHome(@TempDir Path home) throws Exception {
		Files.createDirectories(home.resolve("lib"));
		Files.writeString(home.resolve("options"),
				"-p \"lib/mp.jar\" --add-modules ALL-SYSTEM -classpath \"lib/cp.jar\" -m m/M");

		LaunchSpec spec = Bootstrap.buildSpec(home, new Properties());
		assertEquals("m/M", spec.main);
		assertEquals(1, spec.modulePath.size());
		assertTrue(spec.modulePath.get(0).endsWith("lib/mp.jar"));
	}

	@Test
	void configOverridesMainAndAppendsJvmOptions(@TempDir Path home) throws Exception {
		Files.writeString(home.resolve("options"),
				"-p \"lib/mp.jar\" -classpath \"lib/cp.jar\" -m from/Options");

		Properties config = new Properties();
		config.setProperty("main", "from/Config");
		config.setProperty("jvm.options", "-Xmx2g -Dfoo=bar");

		LaunchSpec spec = Bootstrap.buildSpec(home, config);
		assertEquals("from/Config", spec.main);
		assertTrue(spec.jvmOptions.contains("-Xmx2g"));
		assertTrue(spec.jvmOptions.contains("-Dfoo=bar"));
	}

	@Test
	void resolveJavaExecutableReusesLaunchingJvm(@TempDir Path home) {
		String resolved = new Bootstrap().resolveJavaExecutable(home, new Properties());
		String launching = ProcessHandle.current().info().command().orElse(null);
		if (launching != null) {
			assertEquals(launching, resolved, "should relaunch with the same java executable");
		} else {
			assertTrue(resolved.equals("java") || resolved.equals("java.exe")
					|| resolved.contains("bin"), "fallback should still yield a java executable");
		}
	}

	@Test
	void resolveJavaExecutableHonoursConfigOverride(@TempDir Path home) {
		// A genuinely absolute path (with a drive letter on Windows) is returned as-is.
		Path javaExe = home.resolve("jdk").resolve("bin").resolve("java").toAbsolutePath();
		Properties config = new Properties();
		config.setProperty("java", javaExe.toString());
		assertEquals(javaExe.toString(), new Bootstrap().resolveJavaExecutable(home, config));
	}

	@Test
	void quoteJoinUsesPlatformSeparatorAndQuotes() {
		String joined = Bootstrap.quoteJoin(List.of("/a.jar", "/b.jar"));
		assertEquals('"' + "/a.jar" + File.pathSeparator + "/b.jar" + '"', joined);
	}

	@Test
	void writeArgFilePutsMainLast(@TempDir Path dir) throws Exception {
		LaunchSpec spec = new LaunchSpec();
		spec.modulePath.add("/x/mp.jar");
		spec.classPath.add("/x/cp.jar");
		spec.jvmOptions.add("--add-modules");
		spec.jvmOptions.add("ALL-SYSTEM");
		spec.main = "m/M";

		Path argFile = dir.resolve("test.args");
		Bootstrap.writeArgFile(argFile, spec);
		List<String> lines = Files.readAllLines(argFile);

		assertEquals("-m", lines.get(lines.size() - 2));
		assertEquals("m/M", lines.get(lines.size() - 1));
		assertTrue(lines.contains("--module-path"));
		assertTrue(lines.contains("-classpath"));
	}
}
