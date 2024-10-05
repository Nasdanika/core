package org.nasdanika.groovy;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class GroovyShellCommandFactory extends SubCommandCapabilityFactory<GroovyShellCommand> {

	@Override
	protected Class<GroovyShellCommand> getCommandType() {
		return GroovyShellCommand.class;
	}
	
	@Override
	protected CompletionStage<GroovyShellCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new GroovyShellCommand(loader.getCapabilityLoader()));
	}

}
