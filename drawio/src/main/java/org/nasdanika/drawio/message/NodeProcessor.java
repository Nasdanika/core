package org.nasdanika.drawio.message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.processor.RegistryEntry;

public abstract class NodeProcessor<V> extends LayerElementProcessor<Node,V> {
	
	protected List<ConnectionProcessor<V>> outgoingConnectionProcessors = Collections.synchronizedList(new ArrayList<>());
	
	@RegistryEntry("#element.outgoingConnections.contains(#this)")
	public void addOutgoingConnectionProcessor(ConnectionProcessor<V> outgoingConnectionProcessor) {
		outgoingConnectionProcessors.add(outgoingConnectionProcessor);
	}
		
	protected List<ConnectionProcessor<V>> incomingConnectionProcessors = Collections.synchronizedList(new ArrayList<>());
	
	@RegistryEntry("#element.incomingConnections.contains(#this)")
	public void addIncomingConnectionProcessor(ConnectionProcessor<V> incomingConnectionProcessor) {
		incomingConnectionProcessors.add(incomingConnectionProcessor);
	}
	
	@Override
	public List<ElementMessage<?, V, ?>> processMessage(ElementMessage<?,V,?> message) {
		List<ElementMessage<?, V, ?>> ret = super.processMessage(message);
				
		for (ConnectionProcessor<V> outgoingConnectionProcessor: outgoingConnectionProcessors) {
			Connection outgoingConnection = outgoingConnectionProcessor.getElement();
			if (!message.hasSeen(outgoingConnection)) {
				V outgoingConnectionValue = outgoingConnectionValue(message.getValue(), outgoingConnection);
				if (outgoingConnectionValue != null) {
					ret.add(new OutgoingConnectionMessage<V>(message, outgoingConnection, outgoingConnectionValue, outgoingConnectionProcessor));
				}
			}
		}
		
		for (ConnectionProcessor<V> incomingConnectionProcessor: incomingConnectionProcessors) {
			Connection incomingConnection = incomingConnectionProcessor.getElement();
			if (!message.hasSeen(incomingConnection)) {
				V incomingConnectionValue = incomingConnectionValue(message.getValue(), incomingConnection);
				if (incomingConnectionValue != null) {
					ret.add(new IncomingConnectionMessage<V>(message, incomingConnection, incomingConnectionValue, incomingConnectionProcessor));
				}
			}
		}
		
		return ret;
	}	
	
	protected abstract V incomingConnectionValue(V messageValue, Connection incomingConnection);
	
	protected abstract V outgoingConnectionValue(V messageValue, Connection outgoingConnection);
	
}
