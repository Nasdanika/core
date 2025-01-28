package org.nasdanika.cli.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.Test;
import org.nasdanika.cli.Application;
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
		
		ModuleLayer layer = Application.class.getModule().getLayer();
		try (Writer writer = new FileWriter(new File("target/dist/modules"))) {
			for (String name: layer.modules().stream().map(Module::getName).sorted().toList()) {
				writer.write(name);
				writer.write(System.lineSeparator());
			};
		}
				
		CommandLine launcherCommandLine = new CommandLine(new LauncherCommand());
		launcherCommandLine.execute(
				"-b", "target/dist",
				"-M", "target/dist/modules", 
				"-f", "options",
				"-j", "@java",
//				"-r", "org.nasdanika.**",
				"-o", "nsd.bat");
		
		launcherCommandLine.execute(
				"-b", "target/dist",
				"-M", "target/dist/modules", 
				"-f", "options",
				"-j", "@java -Xdebug -Xrunjdwp:transport=dt_socket,address=8998,server=y",
//				"-r", "org.nasdanika.**",
				"-o", "nsd-debug.bat");		
		
		launcherCommandLine.execute(
				"-b", "target/dist", 
				"-M", "target/dist/modules", 
				"-j", "#!/bin/bash\n\njava",
				"--add-modules", "ALL-SYSTEM",
				"-o", "nsd",
//				"-r", "org.nasdanika.**",
				"-p", ":",
				"-a", "$@");		
		
		launcherCommandLine.execute(
				"-b", "target/dist", 
				"-M", "target/dist/modules", 
				"-o", "nsd-debug",
				"-j", "#!/bin/bash\n\njava -Xdebug -Xrunjdwp:transport=dt_socket,address=8998,server=y",
				"--add-modules", "ALL-SYSTEM",
//				"-r", "org.nasdanika.**",
				"-p", ":",
				"-a", "$@");		
		
	}

}
