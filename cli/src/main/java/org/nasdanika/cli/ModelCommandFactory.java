package org.nasdanika.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityProvider;
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
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new ModelCommand());
	}

}
