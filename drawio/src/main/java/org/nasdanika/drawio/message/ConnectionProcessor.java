package org.nasdanika.drawio.message;

import java.util.List;

import org.nasdanika.common.Message;
import org.nasdanika.common.Section;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.message.SourceMessage;
import org.nasdanika.drawio.message.TargetMessage;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.RegistryEntry;
import org.nasdanika.graph.processor.SourceHandler;

public class ConnectionProcessor extends LayerElementProcessor<Connection> {

	public ConnectionProcessor(MessageProcessorFactory factory) {
		super(factory);
	}
	
	
	@Override
	protected Section doCreateSection() {
		Section section = super.doCreateSection();
		
		// TODO - style, geometry (direction), start and end decorations
		
		return section;
	}
	
	// icon?
	

	@ProcessorElement
	@Override
	public void setElement(Connection element) {
		super.setElement(element);
	}
		
	@SourceHandler
	public ConnectionProcessor getSourceHandler() {
		return this;
	}
		
	@RegistryEntry("#element.target == #this")
	public NodeProcessor targetProcessor;	
		
	@RegistryEntry("#element.source == #this")
	public NodeProcessor sourceProcessor;	
	
	@Override
	public List<Message<?>> processMessage(Message<?> message) {
		List<Message<?>> ret = super.processMessage(message);
		if (sourceProcessor != null && !message.hasSeen(sourceProcessor)) {
			ret.add(new SourceMessage(message, sourceProcessor));			
		}
		if (targetProcessor != null && !message.hasSeen(targetProcessor)) {
			ret.add(new TargetMessage(message, targetProcessor));			
		}
		
		return ret;
	}
	
}
