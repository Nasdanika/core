package org.nasdanika.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.fusesource.jansi.AnsiConsole;

import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * Entry point to the Nasdanika CLI.
 * @author Pavel
 *
 */
public class Application implements IApplication {
	
	@Command(
			description= "Nasdanika Command Line Interface. " +
					"Eclipse options can be added before nsd options, " +
					"e.g. @|bold,yellow -data|@ to specify workspace location", 
			synopsisSubcommandLabel = "COMMAND", 
			name="nsd", 
			mixinStandardHelpOptions = true, 
			versionProvider = BundleVersionProvider.class)
	
//	@Description(icon = "las la-terminal")
	public static class RootCommand extends CommandGroup {
		
	}
	
	public static final String CLI_EXTENSION_POINT_ID = "org.nasdanika.cli.cli";
	
	static boolean equal(String a, String b) {
		if (a == null) {
			return b == null || b.trim().length() == 0;
		}
		return a.equals(b);
	}	
	
	@Override
	public Object start(IApplicationContext context) throws Exception {
		try {
			AnsiConsole.systemInstall();
			CommandLine commandLine = new CommandLine(new RootCommand());
			commandLine.addSubcommand(new HelpCommand(commandLine));
			for (CommandLine subCommand: getSubCommands(null)) {
				commandLine.addSubcommand(subCommand);
			}
			
			String[] args = (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
			int exitCode = commandLine.execute(args);
			System.out.println("Exit code: "+exitCode);
			return exitCode;
		} finally {
			AnsiConsole.systemUninstall();
		}
	}

	@Override
	public void stop() {
		// NOP		
	}

	/**
	 * Collects subcommands from extensions 
	 * @param command
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	private List<CommandLine> getSubCommands(String id) throws Exception {
		List<CommandLine> ret = new ArrayList<>();
		for (IConfigurationElement ce: Platform.getExtensionRegistry().getConfigurationElementsFor(CLI_EXTENSION_POINT_ID)) {
			if ("command".equals(ce.getName()) && equal(id, ce.getAttribute("parent"))) {
				Object command = ce.createExecutableExtension("class");
				for (IConfigurationElement parameter: ce.getChildren("parameter")) {
					((BiConsumer<String, String>) command).accept(parameter.getAttribute("name"), parameter.getAttribute("value"));
				}
				CommandLine commandLine = new CommandLine(command);
				ret.add(commandLine);
				
				String cId = ce.getAttribute("id");
				if (cId != null && cId.trim().length() > 0) {
					for (CommandLine subCommand: getSubCommands(ce.getContributor().getName()+"#"+cId)) {
						commandLine.addSubcommand(subCommand);
					}
				}
			}
		}
		ret.sort((a,b) -> a.getCommandName().compareTo(b.getCommandName()));
		return ret;
	}
	
}

//eclipse -nosplash -application org.eclipse.equinox.p2.metadata.repository.mirrorApplication %*




