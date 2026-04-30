package org.nasdanika.cli;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import picocli.CommandLine;

/**
 * Requirement for a {@link CommandLine} service to add as a sub-command to the provided
 * parent command or use as a root command if the parent path is null or empty.
 */
public record SubCommandRequirement(List<CommandLine> parentPath, AtomicInteger commandCounter) {
	
}
