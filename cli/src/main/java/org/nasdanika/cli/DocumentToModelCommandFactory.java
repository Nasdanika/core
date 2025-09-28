package org.nasdanika.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class DocumentToModelCommandFactory extends SubCommandCapabilityFactory<DocumentToModelCommand> {

	@Override
	protected Class<DocumentToModelCommand> getCommandType() {
		return DocumentToModelCommand.class;
	}
	
	@Override
	protected CompletionStage<DocumentToModelCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new DocumentToModelCommand(loader.getCapabilityLoader()));
	}

}
