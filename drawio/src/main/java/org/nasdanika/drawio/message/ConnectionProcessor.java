package org.nasdanika.drawio.message;

import java.util.List;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.processor.RegistryEntry;

public abstract class ConnectionProcessor<V> extends LayerElementProcessor<Connection,V> {
		
	@RegistryEntry("#element.target == #this")
	public NodeProcessor<V> targetProcessor;	
		
	@RegistryEntry("#element.source == #this")
	public NodeProcessor<V> sourceProcessor;
		
	@SuppressWarnings("unchecked")
	@Override
	public List<ElementMessage<?, V, ?>> processMessage(ElementMessage<?,V,?> message) {
		List<ElementMessage<?, V, ?>> ret = super.processMessage(message);
		
		if (message instanceof IncomingConnectionMessage && sourceProcessor != null) {
			Node source = sourceProcessor.getElement();
			if (!message.hasSeen(source)) {
				V sourceValue = sourceValue(message.getValue(), source);
				if (source != null) {
					ret.add(new SourceMessage<V>((IncomingConnectionMessage<V>) message, source, sourceValue, sourceProcessor));
				}
			}
		}
		
		if (message instanceof OutgoingConnectionMessage && targetProcessor != null) {
			Node target = sourceProcessor.getElement();
			if (!message.hasSeen(target)) {
				V targetValue = targetValue(message.getValue(), target);
				if (target != null) {
					ret.add(new TargetMessage<V>((OutgoingConnectionMessage<V>) message, target, targetValue, sourceProcessor));
				}
			}
		}
		
		return ret;
	}
	
	protected abstract V sourceValue(V messageValue, Node source);
	
	protected abstract V targetValue(V messageValue, Node target);
	
}
