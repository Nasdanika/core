package org.nasdanika.cli;

import java.util.List;

import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class HelpCommandFactory extends SubCommandCapabilityFactory {


	@Override
	protected Object createCommand(List<CommandLine> parentPath, ProgressMonitor progressMonitor) {
		if (parentPath != null && parentPath.size() == 1 && parentPath.get(0).getCommandSpec().userObject() instanceof RootCommand) {
			return new HelpCommand(parentPath.get(0));			
		}
		return null;
	}

}
