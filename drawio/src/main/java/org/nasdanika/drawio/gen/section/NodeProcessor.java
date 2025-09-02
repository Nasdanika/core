package org.nasdanika.drawio.gen.section;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import org.nasdanika.common.Section;
import org.nasdanika.common.message.Message;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.message.IncomingMessage;
import org.nasdanika.drawio.message.OutgoingMessage;
import org.nasdanika.drawio.message.SourceMessage;
import org.nasdanika.drawio.message.TargetMessage;
import org.nasdanika.graph.processor.ChildProcessor;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.RegistryEntry;

import reactor.core.publisher.Mono;

public class NodeProcessor extends LayerElementProcessor<Node> {
		
	private static final String DATA_URI_PNG_PREFIX_NO_BASE_64 = "data:image/png,";
	private static final String DATA_URI_JPEG_PREFIX_NO_BASE_64 = "data:image/jpeg,";	
	
	public NodeProcessor(SectionProcessorFactory factory) {
		super(factory);
	}
	
	@Override
	protected Section doCreateSection() {
		Section section = super.doCreateSection();
		
		// TODO - style, size, geometry
		
		return section;
	}
	
	@ChildProcessor(info = true)
	public void addChildInfo(ProcessorInfo<BaseProcessor<?>> childInfo) {
		childInfos.put((ModelElement) childInfo.getElement(), childInfo);
	}
	
	@OutgoingEndpoint
	public void addOutgoingEndpoints(Connection connection, ConnectionProcessor connectionProcessor) {
		outgoingEndpoints.put(connection, CompletableFuture.completedStage(connectionProcessor).toCompletableFuture());
	}

	@ProcessorElement
	@Override
	public void setElement(Node element) {
		super.setElement(element);
	}
	
	@Override
	public Mono<Section> createSectionAsync() {
		// TODO image description if image and image narrator (URI)
		return super.createSectionAsync();
	}
	
	protected Collection<ConnectionProcessor> incomingProcessors = Collections.synchronizedList(new ArrayList<>());	
		
	@RegistryEntry("#element.target == #this")
	public void addIncomingProcessor(ConnectionProcessor incomingProcessor) {
		if (incomingProcessor != null) {
			incomingProcessors.add(incomingProcessor);
		}
	}	
	
	protected Collection<ConnectionProcessor> outgoingProcessors = Collections.synchronizedList(new ArrayList<>());	
		
	@RegistryEntry("#element.source == #this")
	public void addOutgoingProcessor(ConnectionProcessor outgoingProcessor) {
		if (outgoingProcessor != null) {
			incomingProcessors.add(outgoingProcessor);
		}
	}		
		
	@Override
	public List<Message<?>> processMessage(Message<?> message) {
		List<Message<?>> ret = super.processMessage(message);
		for (ConnectionProcessor op: outgoingProcessors) {			
			if (op != null & !message.hasSeen(op)) {
				ret.add(new OutgoingMessage(message, op));							
			}
		}
		for (ConnectionProcessor ip: incomingProcessors) {			
			if (ip != null & !message.hasSeen(ip)) {
				ret.add(new IncomingMessage(message, ip));							
			}
		}						
		
		return ret;
	}	
	
//		Map<String, String> style = element.getStyle();
//		String image = style.get("image");
//		if (!Util.isBlank(image)) {
//			// Drawio does not add ;base64 to the image URL, browsers don't understand. Fixing it here.
//			if (image.startsWith(DATA_URI_PNG_PREFIX_NO_BASE_64)) {
//				int insertIdx = DATA_URI_PNG_PREFIX_NO_BASE_64.length() - 1;
//				image = image.substring(0, insertIdx) + ";base64" + image.substring(insertIdx);
//			} else if (image.startsWith(DATA_URI_JPEG_PREFIX_NO_BASE_64)) {
//				int insertIdx = DATA_URI_JPEG_PREFIX_NO_BASE_64.length() - 1;
//				image = image.substring(0, insertIdx) + ";base64" + image.substring(insertIdx);
//			}
//		
//			// TODO - describe here
//			
//		}
	
}
