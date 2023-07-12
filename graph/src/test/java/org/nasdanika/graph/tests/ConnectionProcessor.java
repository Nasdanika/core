package org.nasdanika.graph.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;

public class ConnectionProcessor {
	
	private ConnectionProcessorConfig<Function<Element, Element>, Function<Element, Element>> config;
	private Function<Element, Element> sourceEndpoint;
	private Function<Element, Element> targetEndpoint;

	public ConnectionProcessor(ConnectionProcessorConfig<Function<Element,Element>, Function<Element,Element>> config, Consumer<CompletionStage<?>> stageConsumer) {
		this.config = config;
		
		config.setSourceHandler(e -> {
			assertEquals(config.getElement().getSource() , e);
			assertNotNull(targetEndpoint);
			Element ret = targetEndpoint.apply(config.getElement());
			assertEquals(config.getElement().getTarget() , ret);			
			return config.getElement();
		});			
		
		config.setTargetHandler(e -> {
			assertEquals(config.getElement().getTarget() , e);
			assertNotNull(sourceEndpoint);
			Element ret = sourceEndpoint.apply(config.getElement());
			assertEquals(config.getElement().getSource() , ret);			
			return config.getElement();
		});			
		
		stageConsumer.accept(config.getSourceEndpoint().thenAccept(se -> this.sourceEndpoint = se));
		stageConsumer.accept(config.getTargetEndpoint().thenAccept(te -> this.targetEndpoint = te));		
	}

}
