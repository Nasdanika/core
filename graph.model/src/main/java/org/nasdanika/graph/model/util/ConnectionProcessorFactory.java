package org.nasdanika.graph.model.util;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.model.adapters.ConnectionAdapter;
import org.nasdanika.graph.model.util.ReflectiveProcessorFactory.ConnectionProcessorRequirement;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.ConnectionProcessor;

import reactor.core.publisher.Flux;


public abstract class ConnectionProcessorFactory extends ServiceCapabilityFactory<ConnectionProcessorRequirement, ConnectionProcessor<Object, Object, Object, Object>> {

	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return ConnectionProcessor.class.equals(type);
	}
	
	@Override
	public boolean canHandle(Object requirement) {
		if (super.canHandle(requirement)) {
			Object serviceRequirement = ((Requirement<?,?>) requirement).serviceRequirement();
			if (serviceRequirement instanceof  ConnectionProcessorRequirement) {
				ConnectionProcessorRequirement connectionProcessorRequirement = (ConnectionProcessorRequirement) serviceRequirement;
				if (canHandleRequirement(connectionProcessorRequirement.requirement())) {
					Connection connection = connectionProcessorRequirement.config().getElement();
					if (connection instanceof ConnectionAdapter) {
						return isForModelElement(((ConnectionAdapter) connection).get());
					}
				}
			}			
		}
		
		return false;
	}
	
	protected abstract boolean canHandleRequirement(Object requirement);
	
	protected abstract boolean isForModelElement(EObject eObj);
	
	@Override
	protected CompletionStage<Iterable<CapabilityProvider<ConnectionProcessor<Object, Object, Object, Object>>>> createService(
			Class<ConnectionProcessor<Object, Object, Object, Object>> serviceType,
			ConnectionProcessorRequirement serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
	
		ConnectionProcessor<Object, Object, Object, Object> nodeProcessor = createProcessor(
				serviceRequirement.config(), 
				serviceRequirement.infoProvider(), 
				serviceRequirement.endpointWiringStageConsumer(), 
				serviceRequirement.context(), 
				progressMonitor);
		
		Flux<ConnectionProcessor<Object, Object, Object, Object>> publisher = Flux.just(nodeProcessor);
		
		CapabilityProvider<ConnectionProcessor<Object, Object, Object, Object>> capabilityProvider = new CapabilityProvider<ConnectionProcessor<Object, Object, Object, Object>>() {

			@Override
			public Flux<ConnectionProcessor<Object, Object, Object, Object>> getPublisher() {
				return publisher;
			}
			
		};
		
		return CompletableFuture.completedStage(Collections.singletonList(capabilityProvider));
	}

	protected abstract ConnectionProcessor<Object, Object, Object, Object> createProcessor(
			ConnectionProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> config,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Context context,
			ProgressMonitor progressMonitor);
				
}
