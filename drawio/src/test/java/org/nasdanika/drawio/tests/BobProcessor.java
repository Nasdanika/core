package org.nasdanika.drawio.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import java.util.function.Function;

import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.IncomingEndpoint;
import org.nasdanika.graph.processor.IncomingHandler;
import org.nasdanika.graph.processor.NodeProcessorInfo;
import org.nasdanika.graph.processor.ParentProcessor;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorElement;
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
	public ProcessorConfig parentConfig;
	
	@RegistryEntry("label == 'Library'")
	public Function<String,String> library;
	
	private NodeProcessorInfo<Object, Function<String,String>, Function<String,String>> libraryInfo;
	
	@RegistryEntry(value = "label == 'Library'", info = true)
	public void setLibraryConfig(NodeProcessorInfo<Object,Function<String,String>, Function<String,String>> libraryInfo) {
		this.libraryInfo = libraryInfo;
	};
	
	@Registry
	public Map<Element, ProcessorConfig> registry;
	
	@IncomingHandler("source.label == 'Alice'")
	public Function<String,String> aliceIncomingHandler = request -> {
		System.out.println("Request: " + request);
		System.out.println("Request: " + library.apply(request));
		System.out.println(library.apply("Hello!"));		
		System.out.println(libraryInfo.getElement());		
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
