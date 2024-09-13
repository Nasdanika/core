package org.nasdanika.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class DrawioCommandFactory extends SubCommandCapabilityFactory<DrawioCommand> {

	@Override
	protected Class<DrawioCommand> getCommandType() {
		return DrawioCommand.class;
	}
	
	@Override
	protected CompletionStage<DrawioCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new DrawioCommand());
	}

}
