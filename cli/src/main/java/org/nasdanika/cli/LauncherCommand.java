package org.nasdanika.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
		description = "Generates Java command line from directories of modules/jars",
		name = "launcher",
		versionProvider = ModuleVersionProvider.class)
public class LauncherCommand extends AbstractLauncherCommand {
	
	@Option(
			names = {"-m", "--module"}, 
			description = {
					"Application module,",
					"defaults to ${DEFAULT-VALUE}"
				}, 
			defaultValue = "org.nasdanika.cli")
	private String moduleName;
	
	@Override
	protected String getModuleName() {
		return moduleName;
	}
		
	@Option(
			names = {"-c", "--class"}, 
			description = {
					"Application class,",
					"defaults to ${DEFAULT-VALUE}"
				}, 
			defaultValue = "org.nasdanika.cli.Application")
	private String className;
	
	@Override
	protected String getClassName() {
		return className;
	}

}
