package org.nasdanika.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityProvider;
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
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new DrawioCommand());
	}

}
