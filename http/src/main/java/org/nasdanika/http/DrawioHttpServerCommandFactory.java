package org.nasdanika.http;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class DrawioHttpServerCommandFactory extends SubCommandCapabilityFactory<DrawioHttpServerCommand> {

	@Override
	protected Class<DrawioHttpServerCommand> getCommandType() {
		return DrawioHttpServerCommand.class;
	}
	
	@Override
	protected CompletionStage<DrawioHttpServerCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new DrawioHttpServerCommand(loader.getCapabilityLoader()));
	}

}
