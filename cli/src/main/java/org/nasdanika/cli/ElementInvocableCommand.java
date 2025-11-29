package org.nasdanika.cli;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Description;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Parameters;

@Command(
		description = {
				"Creates an Invocable dynamic proxy from a diagram",
				"calls it with command parameters and returns result"
				},
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "invoke")
@ParentCommands(Document.Supplier.class)
@Description(icon = "https://docs.nasdanika.org/images/automation.svg")
public class ElementInvocableCommand extends AbstractElementInvocableCommand<Object,Object,Object,Invocable> {
	
	@Parameters(
			arity = "*", 
			description = "Invocable parameters")
    private Object[] parameters;	
	
	public ElementInvocableCommand() {
		super();
	}

	public ElementInvocableCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
	}

	@Mixin
	private ProgressMonitorMixIn progressMonitorMixIn;	

	@Override
	public Integer call() throws Exception {
		try (ProgressMonitor progressMonitor = progressMonitorMixIn.createProgressMonitor(1)) {
			Invocable proxy = createProxy(progressMonitor, Invocable.class);
			Object result = proxy.invoke(parameters);
			if (result instanceof Integer) {
				return (Integer) result;
			}
			System.out.println(result);
			return 0;
		}
	}

}
