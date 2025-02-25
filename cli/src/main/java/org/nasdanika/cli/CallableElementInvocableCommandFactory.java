package org.nasdanika.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class CallableElementInvocableCommandFactory extends SubCommandCapabilityFactory<CallableElementInvocableCommand> {

	@Override
	protected Class<CallableElementInvocableCommand> getCommandType() {
		return CallableElementInvocableCommand.class;
	}
	
	@Override
	protected CompletionStage<CallableElementInvocableCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new CallableElementInvocableCommand(loader.getCapabilityLoader()));
	}

}
