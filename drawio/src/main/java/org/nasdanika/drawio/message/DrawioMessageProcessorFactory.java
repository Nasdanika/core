package org.nasdanika.drawio.message;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.message.Message;
import org.nasdanika.graph.message.MessageProcessorFactory;

public abstract class DrawioMessageProcessorFactory<V,K> extends MessageProcessorFactory<V,K> {
	
	protected abstract V linkTargetValue(Element element, Message<V> message, K clientKey);
		
	protected abstract V referrerValue(Element element, Message<V> message, K clientKey);
	
	// TODO - wiring of referrers and link targets when processor is created, referrer and link target messages in 

}
