package org.nasdanika.graph.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.ConnectionProcessor;

public class BiFunctionConnectionProcessorImpl implements ConnectionProcessor<Object, Object, Object, Object> {
	
	private ConnectionProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>, String> config;

	public BiFunctionConnectionProcessorImpl(
			ConnectionProcessorConfig<BiFunction<Object, ProgressMonitor, Object>, BiFunction<Object, ProgressMonitor, Object>, String> connectionProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>,BiFunction<Object, ProgressMonitor, Object>,String,BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endointWiringStageConsumer) {
		
		this.config = connectionProcessorConfig;		
	}

	@Override
	public Object apply(Object arg, ProgressMonitor progressMonitor) {
		return 0;
	}

	@Override
	public Object targetApply(
			Object input,
			BiFunction<Object, ProgressMonitor, Object> sourceEndpoint, 
			ProgressMonitor progressMonitor) {
		
		assertEquals(config.getElement().getTarget() , input);
		assertNotNull(sourceEndpoint);
		Object ret = sourceEndpoint.apply(config.getElement(), progressMonitor);
		assertEquals(config.getElement().getSource() , ret);			
		return config.getElement();
	}

	@Override
	public Object sourceApply(
			Object input,
			BiFunction<Object, ProgressMonitor, Object> targetEndpoint, 
			ProgressMonitor progressMonitor) {
		
		assertEquals(config.getElement().getSource() , input);
		assertNotNull(targetEndpoint);
		Object ret = targetEndpoint.apply(config.getElement(), progressMonitor);
		assertEquals(config.getElement().getTarget() , ret);			
		return config.getElement();
	}

}
