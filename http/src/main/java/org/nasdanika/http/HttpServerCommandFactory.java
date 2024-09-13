package org.nasdanika.http;

import java.util.List;
import java.util.concurrent.CompletionStage;

import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.cli.RootCommand;
import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class HttpServerCommandFactory extends SubCommandCapabilityFactory<HttpServerCommand> {

	@Override
	protected CompletionStage<HttpServerCommand> createCommand(
			List<CommandLine> parentPath, 
			Loader loader,
			ProgressMonitor progressMonitor) {
		if (parentPath != null && parentPath.size() == 1 && parentPath.get(0).getCommandSpec().userObject() instanceof RootCommand) {
			CompletionStage<List<HttpServerRouteBuilder>> routeBuildersCS = loader.loadAll(ServiceCapabilityFactory.createRequirement(HttpServerRouteBuilder.class), progressMonitor);
			return routeBuildersCS.thenApply(HttpServerCommand::new);
		}
		return null;
	}

	@Override
	protected Class<HttpServerCommand> getCommandType() {
		return HttpServerCommand.class;
	}

}
