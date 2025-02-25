package org.nasdanika.cli;

import java.util.List;
import java.util.concurrent.CompletionStage;

import org.eclipse.emf.ecore.resource.URIHandler;
import org.nasdanika.capability.ServiceCapabilityFactory;
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
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		CompletionStage<List<URIHandler>> uriHandlersCS = loader.loadAll(ServiceCapabilityFactory.createRequirement(URIHandler.class), progressMonitor);
		return uriHandlersCS.thenApply(uriHandlers -> new DrawioCommand(loader.getCapabilityLoader(), uriHandlers));
	}

}
