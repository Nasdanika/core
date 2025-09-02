package org.nasdanika.drawio.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.nasdanika.common.message.Message;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.message.ChildMessage;
import org.nasdanika.drawio.message.LinkTargetMessage;
import org.nasdanika.drawio.message.ParentMessage;
import org.nasdanika.drawio.message.ReferrerMessage;
import org.nasdanika.graph.processor.NodeProcessorInfo;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.RegistryEntry;

public abstract class LayerElementProcessor<T extends LayerElement,V> extends LinkTargetProcessor<T,V> {
	
	protected Map<ModelElement, ProcessorInfo<BaseProcessor<?>>> childInfos = new ConcurrentHashMap<>();
	
	protected Map<Connection, CompletableFuture<ConnectionProcessor>> outgoingEndpoints = new ConcurrentHashMap<>();	
		
	@RegistryEntry("#element.linkTarget == #this")
	public BaseProcessor<?> linkTargetProcessor;		
		
	@RegistryEntry("#element.parent == #this")
	public BaseProcessor<?> parentProcessor;	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addReferrer(ModelElement referrer) {
		super.addReferrer(referrer);		
		for (Element child: referrer.getChildren()) {
			if (child instanceof ModelElement) {
				ProcessorInfo<BaseProcessor<?>> ci = registry.get(child);
				if (ci != null /* && ci.getProcessor() != null */) {
					childInfos.put((ModelElement) child, ci);
				}
			}
		}		
		
		ProcessorInfo<BaseProcessor<?>> referrerInfo = registry.get(referrer);
		if (referrerInfo instanceof NodeProcessorInfo) {
			NodeProcessorInfo<BaseProcessor<?>, BaseProcessor<?>, BaseProcessor<?>> npi = (NodeProcessorInfo<BaseProcessor<?>, BaseProcessor<?>, BaseProcessor<?>>) referrerInfo;
			outgoingEndpoints.putAll((Map) npi.getOutgoingEndpoints());			
		}
	}
		
	@Override
	public List<Message<?>> processMessage(Message<?> message) {
		List<Message<?>> ret = new ArrayList<>();
		if (parentProcessor != null && !message.hasSeen(parentProcessor)) {
			ret.add(new ParentMessage(message, parentProcessor));			
		}
		
		for (BaseProcessor<?> rp: referrerProcessors) {
			if (!message.hasSeen(rp)) {
				ret.add(new ReferrerMessage(message, rp));
			}
		}
		
		if (linkTargetProcessor != null && !message.hasSeen(linkTargetProcessor)) {
			ret.add(new LinkTargetMessage(message, linkTargetProcessor));
		}
		
		for (ProcessorInfo<BaseProcessor<?>> cpi: childInfos.values()) {
			BaseProcessor<?> cp = cpi.getProcessor();
			if (cp != null && !message.hasSeen(cp)) {
				ret.add(new ChildMessage(message, cp));
			}
		}		
		
		return ret;
	}

}
