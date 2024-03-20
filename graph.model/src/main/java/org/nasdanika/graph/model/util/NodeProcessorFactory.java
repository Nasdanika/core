package org.nasdanika.graph.model.util;

import java.util.Collections;
import java.util.Map;
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
import org.nasdanika.graph.Node;
import org.nasdanika.graph.model.adapters.NodeAdapter;
import org.nasdanika.graph.model.util.ReflectiveProcessorFactory.NodeProcessorRequirement;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.NodeProcessor;

import reactor.core.publisher.Flux;

public abstract class NodeProcessorFactory implements ServiceCapabilityFactory<NodeProcessorRequirement, NodeProcessor<Object, Object, Object, Object>> {

	@Override
	public boolean isForServiceType(Class<?> type) {
		return NodeProcessor.class.equals(type);
	}
	
	@Override
	public boolean canHandle(Object requirement) {
		if (ServiceCapabilityFactory.super.canHandle(requirement)) {
			Object serviceRequirement = ((Requirement<?,?>) requirement).serviceRequirement();
			if (serviceRequirement instanceof  NodeProcessorRequirement) {
				NodeProcessorRequirement nodeProcessorRequirement = (NodeProcessorRequirement) serviceRequirement;
				if (canHandleRequirement(nodeProcessorRequirement.requirement())) {
					Node node = nodeProcessorRequirement.config().getElement();
					if (node instanceof NodeAdapter) {
						return isForModelElement(((NodeAdapter) node).get());
					}
				}
			}			
		}
		
		return false;
	}
	
	protected abstract boolean canHandleRequirement(Object requirement);
	
	protected abstract boolean isForModelElement(EObject eObj);

	@Override
	public CompletionStage<Iterable<CapabilityProvider<NodeProcessor<Object, Object, Object, Object>>>> createService(
			NodeProcessorRequirement serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {

		NodeProcessor<Object, Object, Object, Object> nodeProcessor = createProcessor(
				serviceRequirement.config(), 
				serviceRequirement.infoProvider(), 
				serviceRequirement.endpointWiringStageConsumer(), 
				serviceRequirement.incomingEndpoints(), 
				serviceRequirement.outgoingEndpoints(), 
				serviceRequirement.context(), 
				progressMonitor);
		
		Flux<NodeProcessor<Object, Object, Object, Object>> publisher = Flux.just(nodeProcessor);
		
		CapabilityProvider<NodeProcessor<Object, Object, Object, Object>> capabilityProvider = new CapabilityProvider<NodeProcessor<Object, Object, Object, Object>>() {

			@Override
			public Flux<NodeProcessor<Object, Object, Object, Object>> getPublisher() {
				return publisher;
			}
			
		};
		
		return CompletableFuture.completedStage(Collections.singletonList(capabilityProvider));
	}

	protected abstract NodeProcessor<Object, Object, Object, Object> createProcessor(
			NodeProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> config,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> incomingEndpoints,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> outgoingEndpoints, 
			Context context,
			ProgressMonitor progressMonitor);
				
}
