package org.nasdanika.cli;

import picocli.CommandLine.Command;

@Command(
		description= "Nasdanika Command Line Interface" , 
		synopsisSubcommandLabel = "COMMAND", 
		name="nsd", 
		mixinStandardHelpOptions = true,
		versionProvider = ModuleVersionProvider.class,
		subcommands = {
			LauncherCommand.class	
		})
public class RootCommand {

}
