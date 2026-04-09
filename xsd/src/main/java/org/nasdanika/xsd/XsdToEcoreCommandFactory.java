package org.nasdanika.xsd;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class XsdToEcoreCommandFactory extends SubCommandCapabilityFactory<XsdToEcoreCommand> {

	@Override
	protected Class<XsdToEcoreCommand> getCommandType() {
		return XsdToEcoreCommand.class;
	}
	
	@Override
	protected CompletionStage<XsdToEcoreCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new XsdToEcoreCommand(loader.getCapabilityLoader()));
	}

}
