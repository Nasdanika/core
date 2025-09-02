package org.nasdanika.drawio.gen.section;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.message.Message;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.message.ChildMessage;
import org.nasdanika.drawio.message.ParentMessage;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.RegistryEntry;

public class LayerProcessor extends BaseProcessor<Layer> {
	
	// TODO - if background (no title) then merge with the root/page/model
	
	public LayerProcessor(SectionProcessorFactory factory) {
		super(factory);
	}
	
	@RegistryEntry("#element.root == #this")
	public RootProcessor rootProcessor;

	@ChildProcessors
	public Map<LayerElement, ProcessorInfo<LayerElementProcessor<?>>> childInfos;	
	
	protected boolean isLogicalChild(LayerElement layerElement) {
		if (layerElement instanceof Node) {
			return true;
		}
		if (layerElement instanceof Connection) {
			Node source = ((Connection) layerElement).getSource();
			if (source != null) {
				return source == element;
			}
			return element == layerElement.getParent();
		}
		return false;
	}		
	
	@ProcessorElement
	@Override
	public void setElement(Layer element) {
		super.setElement(element);
	}
		
	@Override
	public List<Message<?>> processMessage(Message<?> message) {
		List<Message<?>> ret = new ArrayList<>();
		if (rootProcessor != null && !message.hasSeen(rootProcessor)) {
			ret.add(new ParentMessage(message, rootProcessor));			
		}
		
		for (ProcessorInfo<LayerElementProcessor<?>> cpi: childInfos.values()) {
			LayerElementProcessor<?> lep = cpi.getProcessor();
			if (lep != null && !message.hasSeen(lep)) {
				ret.add(new ChildMessage(message, lep));
			}
		}		
		return ret;
	}

}
