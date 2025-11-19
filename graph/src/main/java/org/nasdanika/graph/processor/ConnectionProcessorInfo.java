package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

public class ConnectionProcessorInfo<H,E,P> extends ProcessorInfo<H,E,P> implements ConnectionProcessorConfig<H,E> {
	
	/**
	 * Functional interface for processor/info creation.
	 * @param <P>
	 */
	public interface Factory<P,H,E> {
		
		ConnectionProcessorInfo<P,H,E> create(
				ConnectionProcessorConfig<H,E> config, 
				boolean parallel, 
				BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,P>,ProgressMonitor>> infoProvider,
				Consumer<CompletionStage<?>> endpointWiringStageConsumer,
				Context context,
				ProgressMonitor progressMonitor);
		
	}		
	
	public ConnectionProcessorInfo(ConnectionProcessorConfig<H, E> config, P processor) {
		super(config, processor);
	}

	@Override
	public Connection getElement() {
		return ((ConnectionProcessorConfig<H, E>) config).getElement();
	}

	@Override
	public CompletionStage<E> getSourceEndpoint() {
		return ((ConnectionProcessorConfig<H, E>) config).getSourceEndpoint();
	}

	@Override
	public void setSourceHandler(H sourceHandler) {
		((ConnectionProcessorConfig<H, E>) config).setSourceHandler(sourceHandler);
	}

	@Override
	public CompletionStage<E> getTargetEndpoint() {
		return ((ConnectionProcessorConfig<H, E>) config).getTargetEndpoint();
	}

	@Override
	public void setTargetHandler(H targetHandler) {		
		((ConnectionProcessorConfig<H, E>) config).setTargetHandler(targetHandler);
	}

}
