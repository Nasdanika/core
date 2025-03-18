package org.nasdanika.emf;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class ModelCommandFactory extends SubCommandCapabilityFactory<ModelCommand> {

	@Override
	protected Class<ModelCommand> getCommandType() {
		return ModelCommand.class;
	}
	
	@Override
	protected CompletionStage<ModelCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new ModelCommand(loader.getCapabilityLoader()));
	}

}
