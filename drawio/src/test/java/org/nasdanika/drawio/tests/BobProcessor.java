package org.nasdanika.drawio.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import java.util.function.Function;

import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.IncomingEndpoint;
import org.nasdanika.graph.processor.IncomingHandler;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ParentProcessor;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.Registry;
import org.nasdanika.graph.processor.RegistryEntry;

public class BobProcessor implements Runnable {
	
	private Function<String, String> aliceEndpoint;

	@IncomingEndpoint("source.label == 'Alice'")
	public void setAliceIncomingEndpoint(Connection connection, Function<String,String> aliceEndpoint) {
		this.aliceEndpoint = aliceEndpoint;
	} 
	
	@ProcessorElement
	public Node bobNode;
	
	@ParentProcessor(true)
	public ProcessorConfig<Object, Map<Element, ProcessorInfo<Object,?>>> parentConfig;
	
	@RegistryEntry("label == 'Library'")
	public Function<String,String> library;
	
	private NodeProcessorConfig<Object, Function<String,String>,  Function<String,String>, Map<Element, ProcessorInfo<Object,?>>> libraryConfig;
	
	@RegistryEntry(value = "label == 'Library'", config = true)
	public void setLibraryConfig(NodeProcessorConfig<Object, Function<String,String>,  Function<String,String>, Map<Element, ProcessorInfo<Object,?>>> libraryConfig) {
		this.libraryConfig = libraryConfig;
	};
	
	@Registry
	public Map<Element, ProcessorInfo<Object, Map<Element, ProcessorInfo<Object,?>>>> registry;
	
	@IncomingHandler("source.label == 'Alice'")
	public Function<String,String> aliceIncomingHandler = request -> {
		System.out.println("Request: " + request);
		System.out.println("Request: " + library.apply(request));
		System.out.println(library.apply("Hello!"));		
		System.out.println(libraryConfig.getElement());		
		System.out.println(registry.size());		
		System.out.println(parentConfig.getElement());		
		System.out.println(bobNode);		
		return request + System.lineSeparator() + aliceEndpoint.apply("[" + bobNode.getLabel() + "] Hello, my name is Bob! What is yours?");
	};

	@Override
	public void run() {
		assertNotNull(library);
		// TODO Assertions here
		
	}

}
