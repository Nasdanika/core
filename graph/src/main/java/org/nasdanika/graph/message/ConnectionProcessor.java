package org.nasdanika.graph.message;

import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.SourceEndpoint;
import org.nasdanika.graph.processor.SourceHandler;
import org.nasdanika.graph.processor.TargetEndpoint;
import org.nasdanika.graph.processor.TargetHandler;

public abstract class ConnectionProcessor<V> extends ElementProcessor<V> {

	protected abstract V sourceValue(Message<V> message);
	
	protected abstract V targetValue(Message<V> message);
			
	@TargetEndpoint
	public BiConsumer<Message<V>,V> targetEndpoint;
	
	@SourceEndpoint
	public BiConsumer<Message<V>,V> sourceEndpoint;
	
	@SourceHandler(proxy = Consumer.class)
	public void receiveFromSource(Message<V> sourceMessage) {
		if (parentEndpoint != null) {
			V parentValue = parentValue(sourceMessage);
			if (parentValue != null) {
				parentEndpoint.accept(sourceMessage, parentValue);
			}				
		}
		
		for (Entry<Element, BiConsumer<Message<V>, V>> ce: childEndpoints.entrySet()) {
			V childValue = childValue(sourceMessage, ce.getKey());
			if (childValue != null) {
				ce.getValue().accept(sourceMessage, childValue);
			}
		}			
		
		if (targetEndpoint != null) {
			V targetValue = targetValue(sourceMessage);
			if (targetValue != null) {
				targetEndpoint.accept(sourceMessage, targetValue);
			}
		}
		
		onSourceMessage(sourceMessage);
	}		
	
	@TargetHandler(proxy = Consumer.class)
	public void receiveFromTarget(Message<V> targetMessage) {
		if (parentEndpoint != null) {
			V parentValue = parentValue(targetMessage);
			if (parentValue != null) {
				parentEndpoint.accept(targetMessage, parentValue);
			}				
		}
		
		for (Entry<Element, BiConsumer<Message<V>, V>> ce: childEndpoints.entrySet()) {
			V childValue = childValue(targetMessage, ce.getKey());
			if (childValue != null) {
				ce.getValue().accept(targetMessage, childValue);
			}
		}			
		
		if (sourceEndpoint != null) {
			V sourceValue = sourceValue(targetMessage);
			if (sourceValue != null) {
				sourceEndpoint.accept(targetMessage, sourceValue);
			}
		}
		
		onTargetMessage(targetMessage);
	}		
	
	protected void onSourceMessage(Message<V> message) {
		
	}	
	
	protected void onTargetMessage(Message<V> message) {
		
	}
		
	protected void onParentMessage(Message<V> message) {
		super.onParentMessage(message);
		
		if (sourceEndpoint != null) {
			V sourceValue = sourceValue(message);
			if (sourceValue != null) {
				sourceEndpoint.accept(message, sourceValue);
			}
		}
		
		if (targetEndpoint != null) {
			V targetValue = targetValue(message);
			if (targetValue != null) {
				targetEndpoint.accept(message, targetValue);
			}
		}		
	}
	
	@Override
	protected void onChildMessage(Element child, Message<V> message) {
		super.onChildMessage(child, message);
		
		if (sourceEndpoint != null) {
			V sourceValue = sourceValue(message);
			if (sourceValue != null) {
				sourceEndpoint.accept(message, sourceValue);
			}
		}
		
		if (targetEndpoint != null) {
			V targetValue = targetValue(message);
			if (targetValue != null) {
				targetEndpoint.accept(message, targetValue);
			}
		}
		
	}

	@Override
	protected void onClientMessage(Object clientKey, Message<V> message) {
		super.onClientMessage(clientKey, message);
		
		if (sourceEndpoint != null) {
			V sourceValue = sourceValue(message);
			if (sourceValue != null) {
				sourceEndpoint.accept(message, sourceValue);
			}				
		}
		
		if (targetEndpoint != null) {
			V targetValue = targetValue(message);
			if (targetValue != null) {
				targetEndpoint.accept(message, targetValue);
			}				
		}		
	}
		
}
