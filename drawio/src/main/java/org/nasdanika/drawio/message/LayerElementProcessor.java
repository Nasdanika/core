package org.nasdanika.drawio.message;

import java.util.List;

import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.graph.message.ElementMessage;
import org.nasdanika.graph.message.ParentMessage;
import org.nasdanika.graph.processor.RegistryEntry;

public abstract class LayerElementProcessor<T extends LayerElement,V> extends LinkTargetProcessor<T,V> {
		
	@RegistryEntry("#element.linkTarget == #this")
	public LinkTargetProcessor<LinkTarget,V> linkTargetProcessor;		
		
	@RegistryEntry("#element.parent == #this")
	public BaseProcessor<Layer,V> parentProcessor;	
		
	@Override
	public List<ElementMessage<?, V, ?>> processMessage(ElementMessage<?,V,?> message) {
		List<ElementMessage<?, V, ?>> ret = super.processMessage(message);
		
		Layer parent = parentProcessor.getElement();
		if (!message.hasSeen(parent)) {
			V parentValue = parentValue(message.getValue(), parent);
			if (parentValue != null) {
				ret.add(new ParentMessage<Layer,V,BaseProcessor<Layer,V>>(message, parent, parentValue, parentProcessor));
			}
		}

		if (linkTargetProcessor != null) {
			LinkTarget linkTarget = linkTargetProcessor.getElement();
			if (!message.hasSeen(linkTarget)) {
				V linkTargetValue = parentValue(message.getValue(), linkTarget);
				if (linkTargetValue != null) {
					ret.add(new LinkTargetMessage<LinkTarget,V,LinkTargetProcessor<LinkTarget,V>>(message, linkTarget, linkTargetValue, linkTargetProcessor));
				}
			}
		}
		
		return ret;
	}

	protected abstract V parentValue(V messageValue, Element parent);

	protected abstract V linkTargetValue(V messageValue, LinkTarget linkTarget);	

}
