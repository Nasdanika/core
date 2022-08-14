package org.nasdanika.drawio.tests;

import java.util.function.Function;

import org.nasdanika.drawio.processor.ElementProcessor;
import org.nasdanika.drawio.processor.NodeProcessorConfig;

public class AliceBobProcessorFactory {
	
	@ElementProcessor
	public BobProcessor createBobProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>> config) {
		return new BobProcessor();
	}

}
