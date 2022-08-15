package org.nasdanika.drawio.tests;

import java.util.function.Function;

import org.nasdanika.graph.processor.ElementProcessor;
import org.nasdanika.graph.processor.NodeProcessorConfig;

public class AliceBobProcessorFactory {
	
	@ElementProcessor("label == 'Bob'")
	public BobProcessor createBobProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>> config) {
		return new BobProcessor();
	}
	
	@ElementProcessor("label == 'Library'")
	public Function<String,String> createLibraryProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>> config) {
		return question -> "Answer to " + question;
	}	

}
