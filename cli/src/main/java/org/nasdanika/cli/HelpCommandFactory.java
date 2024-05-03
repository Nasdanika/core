package org.nasdanika.cli;

import java.util.List;

import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class HelpCommandFactory extends SubCommandCapabilityFactory<HelpCommand> {

	@Override
	protected HelpCommand doCreateCommand(List<CommandLine> parentPath, ProgressMonitor progressMonitor) {
		return new HelpCommand(parentPath.get(parentPath.size() - 1));			
	}

	@Override
	protected Class<HelpCommand> getCommandType() {
		return HelpCommand.class;
	}

}
