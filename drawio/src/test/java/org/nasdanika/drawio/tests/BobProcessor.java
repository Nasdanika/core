package org.nasdanika.drawio.tests;

import java.util.function.Function;

import org.nasdanika.drawio.processor.InboundHandler;

public class BobProcessor {
	
	@InboundHandler
	private Function<String,String> aliceInboundHandler = request -> {
		System.out.println("Request: " + request);
		return request + System.lineSeparator() + "[Bob] Hello, my name is Bob! What is yours?";
	};

}
