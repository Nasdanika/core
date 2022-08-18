package org.nasdanika.drawio.tests;

import static org.junit.Assert.assertNotNull;

import java.util.Map;
import java.util.function.Function;

import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.IncomingHandler;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ParentProcessor;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.Registry;
import org.nasdanika.graph.processor.RegistryEntry;

public class BobProcessor implements Runnable {
	
//	@IncomingHandler("source.label == 'Alice'")
//	public Function<String,String> aliceInboundHandler(Connection connection) { 
//		return request -> {
//			return request + System.lineSeparator() + " [Bob] Hello, my name is Bob! What is yours?";
//		};
//	} 
	
	@ProcessorElement
	private Node bobNode;
	
	@ParentProcessor(true)
	private ProcessorConfig<Object> parentConfig;
	
	@RegistryEntry("label == 'Library'")
	private Function<String,String> library;
	
	private NodeProcessorConfig<Object, Function<String,String>,  Function<String,String>> libraryConfig;
	
	@RegistryEntry(value = "label == 'Library'", config = true)
	public void setLibraryConfig(NodeProcessorConfig<Object, Function<String,String>,  Function<String,String>> libraryConfig) {
		this.libraryConfig = libraryConfig;
	};
	
	@Registry
	private Map<Element, ProcessorInfo<Object>> registry;
	
	@IncomingHandler("source.label == 'Alice'")
	private Function<String,String> aliceInboundHandler = request -> {
		System.out.println("Request: " + request);
		System.out.println("Request: " + library.apply(request));
		System.out.println(library.apply("Hello!"));		
		System.out.println(libraryConfig.getElement());		
		System.out.println(registry.size());		
		System.out.println(parentConfig.getElement());		
		System.out.println(bobNode);		
		return request + System.lineSeparator() + "[Bob] Hello, my name is Bob! What is yours?";
	};

	@Override
	public void run() {
		assertNotNull(library);
		// TODO Assertions here
		
	}

}
