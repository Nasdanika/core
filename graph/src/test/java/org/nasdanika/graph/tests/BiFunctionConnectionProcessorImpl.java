package org.nasdanika.graph.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.ConnectionProcessor;

public class BiFunctionConnectionProcessorImpl implements ConnectionProcessor<Object, Object, Object, Object> {
	
	private ConnectionProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> config;

	public BiFunctionConnectionProcessorImpl(
			ConnectionProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>> connectionProcessorConfig,
			boolean parallel,
			Function<Element, CompletionStage<BiFunction<Object, ProgressMonitor, Object>>> processorProvider,
			Consumer<CompletionStage<?>> stageConsumer) {
		
		this.config = connectionProcessorConfig;		
	}

	@Override
	public Object apply(Object arg, ProgressMonitor progressMonitor) {
		return 0;
	}

	@Override
	public Object targetApply(
			Object input, 
			ProgressMonitor progressMonitor,
			BiFunction<Object, ProgressMonitor, Object> sourceEndpoint) {
		
		assertEquals(config.getElement().getTarget() , input);
		assertNotNull(sourceEndpoint);
		Object ret = sourceEndpoint.apply(config.getElement(), progressMonitor);
		assertEquals(config.getElement().getSource() , ret);			
		return config.getElement();
	}

	@Override
	public Object sourceApply(
			Object input, 
			ProgressMonitor progressMonitor,
			BiFunction<Object, ProgressMonitor, Object> targetEndpoint) {
		
		assertEquals(config.getElement().getSource() , input);
		assertNotNull(targetEndpoint);
		Object ret = targetEndpoint.apply(config.getElement(), progressMonitor);
		assertEquals(config.getElement().getTarget() , ret);			
		return config.getElement();
	}

}
