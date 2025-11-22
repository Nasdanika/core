package org.nasdanika.drawio.tests;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ChildEndpoint;
import org.nasdanika.graph.processor.ChildHandler;
import org.nasdanika.graph.processor.ChildProcessor;

public class BobHouseProcessor implements Runnable {

	@ChildProcessor("label == 'Bob'")
	public BobProcessor bobProcessor;

	private Map<Element, Function<String, String>> childEndpoints = new ConcurrentHashMap<>();
	
	@ChildEndpoint
	public void setChildEndpoint(Element child, Function<String,String> clientEndpoint) {
		childEndpoints.put(child, clientEndpoint);
	} 
	
	@ChildHandler
	public Function<String,String> getChildHandler(Node node) {
		return str -> "{Bob's house child handler - %s} ".formatted(node.getLabel()) + str;
	}
	
	@Override
	public void run() {
		System.out.println("Bob: " + bobProcessor);
	}

}
