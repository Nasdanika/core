package org.nasdanika.drawio.tests;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.nasdanika.graph.processor.Factories;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Processor;

public class AliceBobProcessorFactory {

	@Processor("label == 'Alice'")
	public AliceProcessor createAliceProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>> config) {
		return new AliceProcessor();
	}	
	
	@Processor("label == 'Bob'")
	public BobProcessor createBobProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>> config) {
		return new BobProcessor();
	}

	@Processor("label == 'Bob''s House'")
	public BobHouseProcessor createBobHouseProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>> config) {
		return new BobHouseProcessor();
	}
	
//	@Factory
	private Object libraryProcessor = new Object() {
	
		@Processor("label == 'Library'")
		public Function<String,String> createLibraryProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>> config) {
			return question -> "Answer to " + question;
		}
		
	};
	
	@Processor("source.label == 'Alice' && target.label == 'Bob'")
	public AliceBobConnectionProcessor createAliceBobConnectionProcessor() {
		return new AliceBobConnectionProcessor();
	}
	
	@Factories
	public List<Object> getLibraryProcessor() {
		return Collections.singletonList(libraryProcessor);
	}

}
