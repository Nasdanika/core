package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

public class ConnectionProcessorInfo<H,E,K,P> extends ProcessorInfo<H,E,K,P> implements ConnectionProcessorConfig<H,E,K> {
	
	/**
	 * Functional interface for processor/info creation.
	 * @param <P>
	 */
	public interface Factory<H,E,K,P> {
		
		ConnectionProcessorInfo<H,E,K,P> create(
				ConnectionProcessorConfig<H,E,K> config, 
				boolean parallel, 
				BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,K,P>,ProgressMonitor>> infoProvider,
				Consumer<CompletionStage<?>> endpointWiringStageConsumer,
				Context context,
				ProgressMonitor progressMonitor);
		
	}		
	
	public ConnectionProcessorInfo(ConnectionProcessorConfig<H,E,K> config, P processor) {
		super(config, processor);
	}

	@Override
	public Connection getElement() {
		return ((ConnectionProcessorConfig<H,E,K>) config).getElement();
	}

	@Override
	public Synapse<H, E> getSourceSynapse() {
		return ((ConnectionProcessorConfig<H,E,K>) config).getSourceSynapse();
	}

	@Override
	public Synapse<H, E> getTargetSynapse() {
		return ((ConnectionProcessorConfig<H,E,K>) config).getTargetSynapse();
	}

}
