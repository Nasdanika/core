package org.nasdanika.cli;

import java.util.List;

import picocli.CommandLine;

/**
 * Requirement for a mix-in to add to the command 
 * identified by the path or the root command if the command path is null or empty.
 */
public record MixInRequirement(List<CommandLine> commandPath) {
	
}
