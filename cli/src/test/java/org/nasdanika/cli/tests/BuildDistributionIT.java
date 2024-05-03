package org.nasdanika.cli.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.Test;
import org.nasdanika.cli.LauncherCommand;

import picocli.CommandLine;

public class BuildDistributionIT {
		
	@Test
	public void generateLauncher() throws IOException {
		for (File tf: new File("target").listFiles()) {
			if (tf.getName().endsWith(".jar") && !tf.getName().endsWith("-sources.jar") && !tf.getName().endsWith("-javadoc.jar")) {
				Files.copy(
						tf.toPath(), 
						new File(new File("target/dist/lib"), tf.getName()).toPath(), 
						StandardCopyOption.REPLACE_EXISTING);		
			}
		}		
		
		CommandLine launcherCommandLine = new CommandLine(new LauncherCommand());
		launcherCommandLine.execute(
				"-b", "target/dist",
				"-f", "options",
				"-j", "@java",
				"-r", "org.nasdanika.**",
				"-o", "nsd.bat");
		
		launcherCommandLine.execute(
				"-b", "target/dist",
				"-f", "options",
				"-j", "@java -Xdebug -Xrunjdwp:transport=dt_socket,address=8998,server=y",
				"-r", "org.nasdanika.**",
				"-o", "nsd-debug.bat");		
		
		launcherCommandLine.execute(
				"-b", "target/dist", 
				"-o", "nsd",
				"-r", "org.nasdanika.**",
				"-p", ":",
				"-a", "$@");		
		
		launcherCommandLine.execute(
				"-b", "target/dist", 
				"-o", "nsd-debug",
				"-j", "java -Xdebug -Xrunjdwp:transport=dt_socket,address=8998,server=y",
				"-r", "org.nasdanika.**",
				"-p", ":",
				"-a", "$@");		
		
	}

}
