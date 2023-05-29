package org.nasdanika.drawio.tests;

import java.util.function.Function;

import org.nasdanika.drawio.Node;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.graph.processor.OutgoingHandler;
import org.nasdanika.graph.processor.ProcessorElement;

public class AliceProcessor extends BobHouseProcessor {
	
	@ProcessorElement
	public Node aliceNode;
	
	@OutgoingHandler("target.label == 'Bob'")
	public Function<String,String> replyToBob = request -> {
		return request + System.lineSeparator() + "[" + aliceNode.getLabel() + "] My name is " + aliceNode.getLabel() + ".";
	};
	
	@OutgoingEndpoint("target.label == 'Bob'")
	public Function<String,String> bobEndpoint;
	
	public String talkToBob(String str) {
		return bobEndpoint.apply("[" + aliceNode.getLabel() + "] Hello!");
	}	

}
