package org.nasdanika.drawio.gen.section;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Section;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.message.ChildMessage;
import org.nasdanika.graph.message.Message;
import org.nasdanika.graph.message.ParentMessage;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.RegistryEntry;

public class RootProcessor extends BaseProcessor<Root> {
	
	// TODO - background color and background image from the model
		
	@RegistryEntry("#element.model.page == #this")
	public PageProcessor pageProcessor;
	
	public RootProcessor(SectionProcessorFactory factory) {
		super(factory);
	}

	@ChildProcessors
	public Map<Layer, ProcessorInfo<LayerProcessor>> layerProcessorInfos;
	
	@Override
	public void configureSection(Section section) {
		super.configureSection(section);
		section.setTitle(element.getModel().getPage().getName());
		// TODO - layer list if more than one. number of elements?
		// TODO - logically merge with the background layer if it doesn't have a name
	}
		
	@Override
	public List<Message<?>> processMessage(Message<?> message) {
		List<Message<?>> ret = new ArrayList<>();
		if (pageProcessor != null && !message.hasSeen(pageProcessor)) {
			ret.add(new ParentMessage(message, pageProcessor));			
		}
		
		for (ProcessorInfo<LayerProcessor> lpi: layerProcessorInfos.values()) {
			LayerProcessor lp = lpi.getProcessor();
			if (lp != null && !message.hasSeen(lp)) {
				ret.add(new ChildMessage(message, lp));
			}
		}		
		return ret;
	}
	
}
