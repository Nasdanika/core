package org.nasdanika.cli;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Closeable;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

/**
 * Base class for Nasdanika CLI commands
 * @author Pavel
 *
 */
@Command(mixinStandardHelpOptions = true)
public abstract class CommandBase implements Callable<Integer>, Closeable {
	
	protected CapabilityLoader capabilityLoader;
	
	public CapabilityLoader getCapabilityLoader() {
		if (capabilityLoader == null) {
			capabilityLoader = new CapabilityLoader();
		}
		return capabilityLoader;
	}

	public CommandBase() {
		
	}
	
	public CommandBase(CapabilityLoader capabilityLoader) {
		this.capabilityLoader = capabilityLoader;
	}
	
	@Spec
	protected CommandSpec spec;
	
	/**
	 * Closes all mix-ins and sub-commands implementing {@link Closeable}
	 */
	@Override
	public void close(ProgressMonitor progressMonitor) {
		if (spec != null) {
			List<Entry<String, CommandLine>> closeableSubCommands = spec
					.subcommands()
					.entrySet()
					.stream()
					.filter(e -> e.getValue().getCommandSpec().userObject() instanceof Closeable)
					.toList();
			
			List<Entry<String, CommandSpec>> closeableMixIns= spec
					.mixins()
					.entrySet()
					.stream()
					.filter(e -> e.getValue().userObject() instanceof Closeable)
					.toList();
			
			int size = closeableSubCommands.size() + closeableMixIns.size();
			if (size > 0) {
				ProgressMonitor scaledProgressMonitor = progressMonitor.scale(size);
				for (Entry<String, CommandLine> subCommandEntry: closeableSubCommands) {
					Object userObject = subCommandEntry.getValue().getCommandSpec().userObject();
					((Closeable) userObject).close(scaledProgressMonitor.split("Closing " + subCommandEntry.getKey() + " subcommand", 1));
				}
				for (Entry<String, CommandSpec> mixInEntry: closeableMixIns) {
					Object userObject = mixInEntry.getValue().userObject();
					((Closeable) userObject).close(scaledProgressMonitor.split("Closing " + mixInEntry.getKey() + " mixIn", 1));
				}
			}
		}		
	}

}
