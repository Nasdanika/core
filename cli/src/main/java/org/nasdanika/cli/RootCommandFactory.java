package org.nasdanika.cli;

import java.util.List;

import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class RootCommandFactory extends SubCommandCapabilityFactory<RootCommand> {

	@Override
	protected RootCommand createCommand(List<CommandLine> parentPath, ProgressMonitor progressMonitor) {
		return parentPath == null || parentPath.isEmpty() ? new RootCommand() : null;
	}

	@Override
	protected Class<RootCommand> getCommandType() {
		return RootCommand.class;
	}

}
