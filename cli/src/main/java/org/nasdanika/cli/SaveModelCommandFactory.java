package org.nasdanika.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class SaveModelCommandFactory extends SubCommandCapabilityFactory<SaveModelCommand> {

	@Override
	protected Class<SaveModelCommand> getCommandType() {
		return SaveModelCommand.class;
	}
	
	@Override
	protected CompletionStage<SaveModelCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new SaveModelCommand());
	}

}
