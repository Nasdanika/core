package org.nasdanika.drawio.message;

import java.util.List;

import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.message.ChildMessage;
import org.nasdanika.graph.message.ElementMessage;
import org.nasdanika.graph.message.ParentMessage;
import org.nasdanika.graph.processor.RegistryEntry;

/**
 * Page processor creates an action for top level pages, adds page diagram and root documentation.
 * For linked pages passes through child labels to be used by the linking element. 
 * Also generates diagram embedding widget to add to the linking element page. 
 */
public abstract class PageProcessor<V> extends LinkTargetProcessor<Page,V> {
	
	@RegistryEntry("#element.model.root == #this")
	public RootProcessor<V> rootProcessor;
	
	@RegistryEntry("#element.model.document == #this")
	public DocumentProcessor<V> documentProcessor;	
	
	@Override
	public List<ElementMessage<?, V, ?>> processMessage(ElementMessage<?, V, ?> message) {
		List<ElementMessage<?, V, ?>> ret = super.processMessage(message);
		
		Root root = rootProcessor.getElement();
		if (!message.hasSeen(root)) {
			V rootValue = rootValue(message.getValue(), root);
			if (rootValue != null) {
				ret.add(new ChildMessage<Root,V,RootProcessor<V>>(message, root, rootValue, rootProcessor));
			}
		}
		
		Document document = documentProcessor.getElement();
		if (!message.hasSeen(document)) {
			V documentValue = documentValue(message.getValue(), root);
			if (documentValue != null) {
				ret.add(new ParentMessage<Document,V,DocumentProcessor<V>>(message, document, documentValue, documentProcessor));
			}
		}
				
		return ret;
	}
	
	protected V rootValue(V messageValue, Root root) {
		return messageValue;
	}
	
	protected abstract V documentValue(V messageValue, Root root);
		
}


