package org.nasdanika.cli;

import java.util.Collection;

import picocli.CommandLine;

/**
 * Commands can implement this interface to select a mix-in from a list of choices of mix-ins with the same name.
 * This allows to implement mix-in overriding/polymorphism.
 */
public interface MixInSelector {
	
	CommandLine selectSubCommand(Collection<CommandLine> choices);

}
