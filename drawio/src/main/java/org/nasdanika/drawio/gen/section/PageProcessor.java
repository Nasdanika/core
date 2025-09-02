package org.nasdanika.drawio.gen.section;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.common.SectionReference;
import org.nasdanika.common.message.Message;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.message.ChildMessage;
import org.nasdanika.drawio.message.ReferrerMessage;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.RegistryEntry;

/**
 * Page processor creates an action for top level pages, adds page diagram and root documentation.
 * For linked pages passes through child labels to be used by the linking element. 
 * Also generates diagram embedding widget to add to the linking element page. 
 */
public class PageProcessor extends LinkTargetProcessor<Page> {
	
	public PageProcessor(SectionProcessorFactory factory) {
		super(factory);
	}
	
	@ProcessorElement
	@Override
	public void setElement(Page element) {
		super.setElement(element);
		setId(element.getId());
	}
	
	@Override
	public List<Message<?>> processMessage(Message<?> message) {
		List<Message<?>> ret = new ArrayList<>();
		if (rootProcessor != null && !message.hasSeen(rootProcessor)) {
			ret.add(new ChildMessage(message, rootProcessor));			
		}
		
		for (BaseProcessor<?> rp: referrerProcessors) {
			if (!message.hasSeen(rp)) {
				ret.add(new ReferrerMessage(message, rp));
			}
		}
		
		return ret;
	}
	
	@Override
	public SectionReference createSectionReference() {
		SectionReference sectionReference = super.createSectionReference();
		sectionReference.setTitle(element.getName());
		return sectionReference;
	}
	
	/**
	 * Forcing top-level page
	 */
	public boolean isTopLevelPage;
	
	@RegistryEntry("#element.model.root == #this")
	public RootProcessor rootProcessor;
	
}


