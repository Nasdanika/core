package org.nasdanika.http;

import java.util.List;

import org.nasdanika.cli.RootCommand;
import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class HttpServerCommandFactory extends SubCommandCapabilityFactory {

	@Override
	protected Object createCommand(List<CommandLine> parentPath, ProgressMonitor progressMonitor) {
		if (parentPath != null && parentPath.size() == 1 && parentPath.get(0).getCommandSpec().userObject() instanceof RootCommand) {
			return new HttpServerCommand();			
		}
		return null;
	}

}
