package org.nasdanika.drawio.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.graph.processor.RegistryEntry;

public abstract class LinkTargetProcessor<T extends LinkTarget,V> extends BaseProcessor<T,V> {

	protected Collection<BaseProcessor<ModelElement,V>> referrerProcessors = Collections.synchronizedList(new ArrayList<>());	
		
	@RegistryEntry("#element.linkTarget == #this")
	public void addReferrerProcessor(BaseProcessor<ModelElement,V> referrerProcessor) {
		if (referrerProcessor != null) {
			referrerProcessors.add(referrerProcessor);
		}
	}
	
	@Override
	public List<ElementMessage<?, V, ?>> processMessage(ElementMessage<?,V,?> message) {
		List<ElementMessage<?, V, ?>> ret = new ArrayList<>();
		for (BaseProcessor<ModelElement,V> referrerProcessor: referrerProcessors) {
			ModelElement referrer = (ModelElement) referrerProcessor.getElement();
			if (!message.hasSeen(referrer)) {
				V referrerValue = referrerValue(message.getValue(), referrer);
				if (referrerValue != null) {
					ret.add(new ReferrerMessage<V>(message, referrer, referrerValue, referrerProcessor));
				}
			}
		}
		
		return ret;
	}

	/**
	 * @param messageValue
	 * @param referrer
	 * @return Value of a message to be sent for a link target (page, model element) to the link source (referrer) model element.
	 */
	protected abstract V referrerValue(V messageValue, Element referrer);	

}
