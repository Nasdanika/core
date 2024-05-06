package org.nasdanika.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class HelpCommandFactory extends SubCommandCapabilityFactory<HelpCommand> {

	@Override
	protected CompletionStage<HelpCommand> doCreateCommand(
			List<CommandLine> parentPath, 
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new HelpCommand(parentPath.get(parentPath.size() - 1)));			
	}

	@Override
	protected Class<HelpCommand> getCommandType() {
		return HelpCommand.class;
	}

}
