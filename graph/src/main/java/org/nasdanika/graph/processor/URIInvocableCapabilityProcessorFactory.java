package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;

/**
 * Creates processors by loading {@link Invocable}s by {@link URI} and calling them.
 * @param <P>
 */
public abstract class URIInvocableCapabilityProcessorFactory<H,E,K,P> extends InvocableProcessorFactory<H,E,K,P> {
	
	protected CapabilityLoader capabilityLoader;

	public URIInvocableCapabilityProcessorFactory() {
		this(new CapabilityLoader());
	}
	
	public URIInvocableCapabilityProcessorFactory(CapabilityLoader capabilityLoader) {
		this.capabilityLoader = capabilityLoader;
	}
	
	@Override
	protected Invocable getProcessorFactory(
			ProcessorConfig<H,E,K> config, 
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<H,E,K,P>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {
		
		URI uri = getURI(config, progressMonitor);
		if (uri == null) {
			return null;					
		}
			
		Requirement<URI, Invocable> requirement = ServiceCapabilityFactory.createRequirement(Invocable.class, null, uri);
		return capabilityLoader.loadOne(requirement, progressMonitor);
	}
	
	protected abstract URI getURI(ProcessorConfig<H,E,K> config, ProgressMonitor progressMonitor);

}
