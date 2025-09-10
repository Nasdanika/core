package org.nasdanika.drawio.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.message.ElementMessage;
import org.nasdanika.graph.message.ElementProcessor;
import org.nasdanika.graph.message.MessageProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;

public abstract class DrawioMessageProcessorFactory<V> extends MessageProcessorFactory<V> {
		
	protected abstract V linkTargetValue(V messageValue, ModelElement referrer, LinkTarget linkTarget);	
	
	protected abstract V referrerValue(V messageValue, LinkTarget linkTarget, ModelElement referrer);	
	
	@Override
	protected Collection<ElementMessage<?, V, ?>> processMessage(ElementProcessor<?, V> processor, ElementMessage<?, V, ?> message) {
		Collection<ElementMessage<?, V, ?>> messages = new ArrayList<>();
		Element element = processor.getElement();
		if (element instanceof ModelElement) {
			ModelElement modelElement = (ModelElement) processor.getElement();
			LinkTarget linkTarget = modelElement.getLinkTarget();
			if (linkTarget != null) {
				ProcessorInfo<ElementProcessor<Element, V>> linkTargetProcessorInfo = processor.registry.get(linkTarget);
				if (linkTargetProcessorInfo != null) {
					@SuppressWarnings({ "unchecked", "rawtypes" })
					ElementProcessor<LinkTarget, V> linkTargetProcessor = (ElementProcessor) linkTargetProcessorInfo.getProcessor();
					if (linkTargetProcessor != null) {
						V linkTargetValue = linkTargetValue(message.getValue(), modelElement, linkTarget);
						if (linkTargetValue != null) {		
							LinkTargetMessage<LinkTarget, V, ElementProcessor<LinkTarget,V>> linkTargetMessage = new LinkTargetMessage<>(message, linkTarget, linkTargetValue, linkTargetProcessor);
							messages.add(linkTargetMessage);
						}
					}
				}
			}
		}
		
		if (element instanceof LinkTarget) {
			processor.registry.values()
				.stream()
				.filter(Objects::nonNull)
				.map(ProcessorInfo::getProcessor)
				.filter(Objects::nonNull)
				.filter(p -> p.getElement() instanceof ModelElement && ((ModelElement) p.getElement()).getLinkTarget() == element)
				.forEach(p -> {
					ModelElement referrer = (ModelElement) p.getElement();
					V referrerValue = referrerValue(message.getValue(), (LinkTarget) element, referrer);
					if (referrerValue != null) {
						@SuppressWarnings({ "rawtypes", "unchecked" })
						ReferrerMessage<V> referrerMessage = new ReferrerMessage<>(message, referrer, referrerValue, (ElementProcessor) p);
						messages.add(referrerMessage);
					}
				});
		}
		
		return messages;
	}

}
