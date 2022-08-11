package org.nasdanika.drawio.tests;

import org.nasdanika.drawio.processor.InboundHandler;

public class BobProcessor {
	
	@InboundHandler
	public String talkToAlice(String request) {
		System.out.println("Request: " + request);
		return request + System.lineSeparator() + "[Bob] Hello, my name is Bob! What is yours?";
	}

}
