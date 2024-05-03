package org.nasdanika.cli;

import picocli.CommandLine;

/**
 * Commands can implement this interface to accept sub-commands
 */
public interface SubCommandPredicate {
	
	boolean acceptSubCommand(CommandLine commandLine, CommandLine subCommand);

}
