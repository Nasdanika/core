package org.nasdanika.cli;

import java.util.List;

import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class ProgressMonitorMixInFactory extends MixInCapabilityFactory {

	@Override
	protected String getName() {
		return "progress-monitor";
	}

	@Override
	protected Object createMixIn(List<CommandLine> commandPath, ProgressMonitor progressMonitor) {
		if (commandPath != null && commandPath.size() == 1 && commandPath.get(0).getCommandSpec().userObject() instanceof RootCommand) {
			return new ProgressMonitorMixin();
		}
		return null;
	}

}
