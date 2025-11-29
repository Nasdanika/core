package org.nasdanika.cli;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Description;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.processor.ElementInvocableFactory;
import org.nasdanika.graph.processor.EndpointFactory;

import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;

/**
 * Base class for commands creating processors and dynamic proxies from diagram elements using {@link ElementInvocableFactory}
 */
public abstract class AbstractElementInvocableCommand<H,E,K,T> extends CommandBase {

	protected AbstractElementInvocableCommand() {
		super();
	}

	protected AbstractElementInvocableCommand(CapabilityLoader capabilityLoader) {
		super(capabilityLoader);
	}
		
	@ParentCommand
	private Document.Supplier documentSupplier;
		
	@Option(
		names = {"-b", "--bind-property"},
		description = {
			"Bind property providing dynamic",
			"proxy method name",
			"Defaults to ${DEFAULT-VALUE}"
		},
		defaultValue = "bind")
	private String bindProperty;	
	
	@Option(
		names = {"-p", "--processor-property"},
		description = {
			"Processor property",
			"Defaults to ${DEFAULT-VALUE}"
		},
		defaultValue = "processor")
	@Description
	private String processorProperty;
		
	@Option(
		names = {"-c", "--connection-base"},
		description = {
			"Connection base",
			"Valid values: ${COMPLETION-CANDIDATES}"
		})
	private ConnectionBase connectionBase;	
	
	protected ElementInvocableFactory<H,E,K> createFactory(ProgressMonitor progressMonitor) {
		return new ElementInvocableFactory<H,E,K>(documentSupplier.getDocument(null), processorProperty);
	}
		
	protected ClassLoader getClassLoader(ProgressMonitor progressMonitor) {
		return Thread.currentThread().getContextClassLoader();
	}
	
	@SuppressWarnings("unchecked")
	protected EndpointFactory<H,E> getEndpointFactory(ProgressMonitor progressMonitor) {
		return (EndpointFactory<H,E>) EndpointFactory.nopEndpointFactory();
	}
	
	protected T createProxy(ProgressMonitor progressMonitor, Class<?>... interfaces) {
		ElementInvocableFactory<H,E,K> factory = createFactory(progressMonitor);
		return factory.createProxy(
				bindProperty, 
				getEndpointFactory(progressMonitor), 
				connectionBase, 
				progressMonitor, 
				getClassLoader(progressMonitor), 
				interfaces);
	}	

}
