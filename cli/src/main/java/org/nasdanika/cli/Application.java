package org.nasdanika.cli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

/**
 * Nasdanika command line application.
 * Collects sub-commands for the {@link RootCommand} using the capability framework
 */
public class Application {
	
	public static void main(String[] args) {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor(); - TODO configurable through system properties

		// Sub-commands, sorting alphabetically
		List<CommandLine> rootCommands = new ArrayList<>();		
		Requirement<SubCommandRequirement, CommandLine> subCommandRequirement = ServiceCapabilityFactory.createRequirement(CommandLine.class, null,  new SubCommandRequirement(Collections.emptyList()));
		for (CapabilityProvider<Object> cp: capabilityLoader.load(subCommandRequirement, progressMonitor)) {
			cp.getPublisher().subscribe(cmd -> rootCommands.add((CommandLine) cmd));
		}
		
		// Executing the first one
		for (CommandLine rootCommand: rootCommands) {	
			int exitCode = rootCommand.execute(args);
			System.exit(exitCode);
		}
		
		throw new UnsupportedOperationException("There are no root commands");		
	}

}
