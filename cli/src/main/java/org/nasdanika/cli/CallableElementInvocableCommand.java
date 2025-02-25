package org.nasdanika.cli;

import java.util.concurrent.Callable;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(
		description = {
				"Creates a Callable dynamic proxy from a diagram",
				"calls it and returns result"
				},
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "call")
@ParentCommands(Document.Supplier.class)
//@Description()
public class CallableElementInvocableCommand extends AbstractElementInvocableCommand<Object,Object,Callable<Integer>> {
	
	public CallableElementInvocableCommand() {
		super();
	}

	public CallableElementInvocableCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
	}

	@Mixin
	private ProgressMonitorMixIn progressMonitorMixIn;	

	@Override
	public Integer call() throws Exception {
		try (ProgressMonitor progressMonitor = progressMonitorMixIn.createProgressMonitor(1)) {
			Callable<Integer> proxy = createProxy(progressMonitor, Callable.class);
			return proxy.call();
		}
	}

}
