package org.nasdanika.drawio.tests;

import java.util.function.Function;

import org.nasdanika.drawio.Node;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.graph.processor.OutgoingHandler;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.RegistryEntry;
import org.nasdanika.graph.processor.Synapse;

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
	
	@RegistryEntry("label == 'Bob'")
	public void setBobSynapse(Synapse<Function<String,String>, Function<String,String>> bobSynapse) {
		bobSynapse.setHandler(str -> "{Alice's handler for Bob} " + str);
	};

}
