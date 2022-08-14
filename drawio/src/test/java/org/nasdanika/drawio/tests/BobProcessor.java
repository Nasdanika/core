package org.nasdanika.drawio.tests;

import java.util.function.Function;

import org.nasdanika.drawio.Connection;
import org.nasdanika.graph.processor.IncomingHandler;

public class BobProcessor {
	
	@IncomingHandler
	public Function<String,String> aliceInboundHandler(Connection connection) { 
		return request -> {
			return request + System.lineSeparator() + " [Bob] Hello, my name is Bob! What is yours?";
		};
	} 
	
//	@IncomingHandler
//	private Function<String,String> aliceInboundHandler = request -> {
//		System.out.println("Request: " + request);
//		return request + System.lineSeparator() + "[Bob] Hello, my name is Bob! What is yours?";
//	};

}
