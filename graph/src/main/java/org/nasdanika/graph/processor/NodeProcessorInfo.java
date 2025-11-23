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
public class NodeProcessorInfo<H,E,P> extends ProcessorInfo<H,E,P> implements NodeProcessorConfig<H, E> {
	/**
	 * Functional interface for processor/info creation.
	 * @param <P>
	 */
	public interface Factory<H,E,P> {
		
		NodeProcessorInfo<H,E,P> create(
				NodeProcessorConfig<H,E> config, 
				boolean parallel, 
				BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,P>,ProgressMonitor>> infoProvider,
				Consumer<CompletionStage<?>> endpointWiringStageConsumer,
				Context context,
				ProgressMonitor progressMonitor);
		
	}			
	
	public NodeProcessorInfo(NodeProcessorConfig<H, E> config, P processor) {
		super(config, processor);
	}
	
	@Override
	public Node getElement() {
		return ((NodeProcessorConfig<H, E>) config).getElement();
	}

	@Override
	public Map<Connection, Synapse<H, E>> getIncomingSynapses() {
		return ((NodeProcessorConfig<H, E>) config).getIncomingSynapses();
	}

	@Override
	public Map<Connection, Synapse<H, E>> getOutgoingSynapses() {
		return ((NodeProcessorConfig<H, E>) config).getOutgoingSynapses();
	}

}
