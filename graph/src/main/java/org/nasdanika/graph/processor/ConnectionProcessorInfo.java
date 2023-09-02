package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

public class ConnectionProcessorInfo<P, H, E> extends ProcessorInfo<P> implements ConnectionProcessorConfig<H, E> {
	
	/**
	 * Functional interface for processor/info creation.
	 * @param <P>
	 */
	public interface Factory<P,H,E> {
		
		ConnectionProcessorInfo<P,H,E> create(
				ConnectionProcessorConfig<H,E> config, 
				boolean parallel, 
				BiConsumer<Element,BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider,
				Consumer<CompletionStage<?>> endpointWiringStageConsumer,
				Context context,
				ProgressMonitor progressMonitor);
		
	}		
	
	public ConnectionProcessorInfo(ConnectionProcessorConfig<H, E> config, P processor) {
		super(config, processor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Connection getElement() {
		return ((ConnectionProcessorConfig<H, E>) config).getElement();
	}

	@SuppressWarnings("unchecked")
	@Override
	public CompletionStage<E> getSourceEndpoint() {
		return ((ConnectionProcessorConfig<H, E>) config).getSourceEndpoint();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setSourceHandler(H sourceHandler) {
		((ConnectionProcessorConfig<H, E>) config).setSourceHandler(sourceHandler);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CompletionStage<E> getTargetEndpoint() {
		return ((ConnectionProcessorConfig<H, E>) config).getTargetEndpoint();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setTargetHandler(H targetHandler) {		
		((ConnectionProcessorConfig<H, E>) config).setTargetHandler(targetHandler);
	}

}
