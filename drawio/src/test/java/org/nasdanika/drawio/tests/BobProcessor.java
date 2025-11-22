package org.nasdanika.drawio.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ClientEndpoint;
import org.nasdanika.graph.processor.ClientHandler;
import org.nasdanika.graph.processor.IncomingEndpoint;
import org.nasdanika.graph.processor.IncomingHandler;
import org.nasdanika.graph.processor.NodeProcessorInfo;
import org.nasdanika.graph.processor.ParentEndpoint;
import org.nasdanika.graph.processor.ParentHandler;
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
	public ProcessorConfig<Function<String,String>,Function<String,String>> parentConfig;
	
	@RegistryEntry("label == 'Library'")
	public Function<String,String> library;
	
	private NodeProcessorInfo<Object, Function<String,String>, Function<String,String>> libraryInfo;
	
	@RegistryEntry(value = "label == 'Library'", info = true)
	public void setLibraryConfig(NodeProcessorInfo<Object,Function<String,String>, Function<String,String>> libraryInfo) {
		this.libraryInfo = libraryInfo;
	};
	
	@Registry
	public Map<Element, ProcessorConfig<Function<String,String>,Function<String,String>>> registry;
	
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

	private Function<String, String> parentEndpoint;

	private Map<Object, Function<String, String>> clientEndpoints = new ConcurrentHashMap<>();

	@Override
	public void run() {
		assertNotNull(library);
		// TODO Assertions here
		
	}
	
	@ParentEndpoint
	public void setParentEndpoint(Function<String,String> parentEndpoint) {
		this.parentEndpoint = parentEndpoint;
	} 
	
	@ParentHandler
	public Function<String,String> getParentHandler() {
		return str -> "{Bob's parent handler} " + str;
	}
	
	@ClientEndpoint
	public void setClientEndpoint(Object clientKey, Function<String,String> clientEndpoint) {
		clientEndpoints.put(clientKey, clientEndpoint);
	} 
	
	@ClientHandler
	public Function<String,String> getClientHandler(Object clientKey) {
		return str -> "{Bob's client handler - %s} ".formatted(clientKey) + str;
	}

}
