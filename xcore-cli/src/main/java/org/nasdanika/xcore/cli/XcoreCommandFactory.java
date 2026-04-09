package org.nasdanika.xcore.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class XcoreCommandFactory extends SubCommandCapabilityFactory<XcoreCommand> {

	@Override
	protected Class<XcoreCommand> getCommandType() {
		return XcoreCommand.class;
	}
	
	@Override
	protected CompletionStage<XcoreCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new XcoreCommand(loader.getCapabilityLoader()));
	}

}
