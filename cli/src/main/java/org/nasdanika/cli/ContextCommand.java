package org.nasdanika.cli;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine.Mixin;

/**
 * Base class for commands which build {@link Context} from command line options.
 * @author Pavel
 *
 */
public abstract class ContextCommand extends CommandBase {
	
	@Mixin
	ContextMixIn contextMixIn;
	
	/**
	 * Creates and configures context by adding mounts, contexts and calling context builders.
	 * @param progressMonitor
	 * @return
	 * @throws Exception
	 */
	protected Context createContext(ProgressMonitor progressMonitor) throws Exception {
		return contextMixIn.createContext(progressMonitor);
	}
	
	public ContextCommand() {
		
	}
	
	public ContextCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
	}
		
}
