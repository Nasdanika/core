package org.nasdanika.drawio.tests;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.Factories;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.graph.processor.ProcessorInfo;

public class AliceBobProcessorFactory {

	@Processor("label == 'Alice'")
	public AliceProcessor createAliceProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>, Map<Element, ProcessorInfo<Object,?>>> config) {
		return new AliceProcessor();
	}	
	
	@Processor("label == 'Bob'")
	public BobProcessor createBobProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>, Map<Element, ProcessorInfo<Object,?>>> config) {
		return new BobProcessor();
	}

	@Processor("label == 'Bob''s House'")
	public BobHouseProcessor createBobHouseProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>, Map<Element, ProcessorInfo<Object,?>>> config) {
		return new BobHouseProcessor();
	}
	
//	@Factory
	public Object libraryProcessor = new Object() {
	
		@Processor("label == 'Library'")
		public Function<String,String> createLibraryProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>, Map<Element, ProcessorInfo<Object,?>>> config) {
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
