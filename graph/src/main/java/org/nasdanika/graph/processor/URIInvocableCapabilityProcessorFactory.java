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
public abstract class URIInvocableCapabilityProcessorFactory<P> extends ReflectiveWiringProcessorFactory<P> {
	
	protected CapabilityLoader capabilityLoader;

	public URIInvocableCapabilityProcessorFactory() {
		this(new CapabilityLoader());
	}
	
	public URIInvocableCapabilityProcessorFactory(CapabilityLoader capabilityLoader) {
		this.capabilityLoader = capabilityLoader;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected P doCreateProcessor(
			ProcessorConfig config, 
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<P>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {
		
		URI uri = getURI(config, progressMonitor);
		if (uri == null) {
			return createDefaultProcessor(config, parallel, null, endpointWiringStageConsumer, progressMonitor);					
		}
			
		Requirement<URI, Invocable> requirement = ServiceCapabilityFactory.createRequirement(Invocable.class, null, uri);
		Invocable processorProvider = capabilityLoader.loadOne(requirement, progressMonitor);
		return (P) processorProvider.invoke(config, infoProvider, endpointWiringStageConsumer, progressMonitor);
	}
	
	protected P createDefaultProcessor(
			ProcessorConfig config, 
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<P>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {
		
		return null;
	}

	protected abstract URI getURI(ProcessorConfig config, ProgressMonitor progressMonitor);

}
