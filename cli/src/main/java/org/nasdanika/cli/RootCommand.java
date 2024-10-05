package org.nasdanika.cli;

import org.nasdanika.common.Invocable;

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
@SubCommands(HelpCommand.class)
public class RootCommand implements Invocable.Invoker {

	/**
	 * To allow running scripts and Groovy shell
	 */
	@Override
	public Object invoke(Invocable invocable) {
		return invocable.invoke();
	}

}
