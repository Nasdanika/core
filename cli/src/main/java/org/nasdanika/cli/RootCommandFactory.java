package org.nasdanika.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class RootCommandFactory extends SubCommandCapabilityFactory<RootCommand> {

	@Override
	protected CompletionStage<RootCommand> createCommand(
			List<CommandLine> parentPath, 
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		return parentPath == null || parentPath.isEmpty() ? CompletableFuture.completedStage(new RootCommand()) : null;
	}

	@Override
	protected Class<RootCommand> getCommandType() {
		return RootCommand.class;
	}

}
