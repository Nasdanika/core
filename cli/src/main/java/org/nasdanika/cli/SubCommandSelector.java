package org.nasdanika.cli;

import java.util.Collection;

import picocli.CommandLine;

/**
 * Commands can implement this interface to select a sub-command from a list of choices of sub-commands with the same name.
 * This allows to implement sub-command overriding/polymorphism.
 */
public interface SubCommandSelector {
	
	CommandLine selectSubCommand(Collection<CommandLine> choices);

}
