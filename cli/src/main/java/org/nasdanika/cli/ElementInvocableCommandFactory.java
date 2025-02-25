package org.nasdanika.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class ElementInvocableCommandFactory extends SubCommandCapabilityFactory<ElementInvocableCommand> {

	@Override
	protected Class<ElementInvocableCommand> getCommandType() {
		return ElementInvocableCommand.class;
	}
	
	@Override
	protected CompletionStage<ElementInvocableCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new ElementInvocableCommand(loader.getCapabilityLoader()));
	}

}
