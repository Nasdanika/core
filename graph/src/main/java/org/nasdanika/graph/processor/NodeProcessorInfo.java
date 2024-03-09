package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

/**
 * 
 * @param <P> Processor type
 * @param <H> Handler type
 * @param <E> Endpoint type
 */
public class NodeProcessorInfo<P, H, E> extends ProcessorInfo<P> implements NodeProcessorConfig<H, E> {
	/**
	 * Functional interface for processor/info creation.
	 * @param <P>
	 */
	public interface Factory<P,H,E> {
		
		NodeProcessorInfo<P,H,E> create(
				NodeProcessorConfig<H,E> config, 
				boolean parallel, 
				BiConsumer<Element,BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider,
				Consumer<CompletionStage<?>> endpointWiringStageConsumer,
				Context context,
				ProgressMonitor progressMonitor);
		
	}			
	
	public NodeProcessorInfo(NodeProcessorConfig<H, E> config, P processor) {
		super(config, processor);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Node getElement() {
		return ((NodeProcessorConfig<H, E>) config).getElement();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Connection, CompletionStage<E>> getIncomingEndpoints() {
		return ((NodeProcessorConfig<H, E>) config).getIncomingEndpoints();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Connection, Consumer<H>> getIncomingHandlerConsumers() {
		return ((NodeProcessorConfig<H, E>) config).getIncomingHandlerConsumers();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Connection, CompletionStage<E>> getOutgoingEndpoints() {
		return ((NodeProcessorConfig<H, E>) config).getOutgoingEndpoints();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Connection, Consumer<H>> getOutgoingHandlerConsumers() {
		return ((NodeProcessorConfig<H, E>) config).getOutgoingHandlerConsumers();
	}

}
