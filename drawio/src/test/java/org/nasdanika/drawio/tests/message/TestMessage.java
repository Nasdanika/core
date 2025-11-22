package org.nasdanika.drawio.tests.message;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
//import org.nasdanika.drawio.message.DrawioMessageProcessorFactory;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
//import org.nasdanika.graph.message.ElementMessage;
//import org.nasdanika.graph.message.ElementProcessor;
//import org.nasdanika.graph.message.Message;
//import org.nasdanika.graph.message.RootElementMessage;
import org.nasdanika.graph.processor.ProcessorInfo;

public class TestMessage {
	
	
//	private DrawioMessageProcessorFactory<String> stringProcessorFactory = new DrawioMessageProcessorFactory<String>() {
//		
//		@Override
//		protected String targetValue(String messageValue, Node node, Connection incomingConnection) {
//			return messageValue + ".T";
//		}
//		
//		@Override
//		protected String sourceValue(String messageValue, Node node, Connection outgoingConnection) {
//			return messageValue + ".S";
//		}
//		
//		@Override
//		protected String outgoingValue(String messageValue, Connection connection) {
//			return messageValue + ".O";
//		}
//		
//		@Override
//		protected String incomingValue(String messageValue, Connection connection) {
//			return messageValue + ".I";
//		}
//		
//		@Override
//		protected String childValue(String messageValue, Element parent, Element child) {
//			return messageValue + ".C";
//		}
//		
//		@Override
//		protected Collection<ElementMessage<?,String,?>> processMessage(ElementProcessor<?,String> processor, ElementMessage<?,String,?> message) {
//			System.out.println(processor + " " + message);
//			return super.processMessage(processor, message);
//		}
//
//		@Override
//		protected String linkTargetValue(String messageValue, ModelElement referrer, LinkTarget linkTarget) {
//			return messageValue + ".L";
//		}
//
//		@Override
//		protected String referrerValue(String messageValue, LinkTarget linkTarget, ModelElement referrer) {
//			return messageValue + ".R";
//		}
//
//		@Override
//		protected String parentValue(String messageValue, Element child, Element parent) {
//			return messageValue + ".P";
//		};
//	};	
//	
//	public static void dump(Message<?,?> message) {
//		dump(message, 0);
//	}
//	
//	public static void dump(Message<?,?> message, int indent) {
//		for (int i = 0; i < indent; ++i) {
//			System.out.print("  ");
//		}
//		System.out.println(message.getClass().getName() + " " + message.getTarget() + ": " + message.getValue());
//		message.getChildren().forEach(c -> dump(c, indent + 1));
//	}
//		
//	@Test
//	public void testSinglePage() throws Exception {
//		Document document = Document.load(getClass().getResource("single-page.drawio"));
//		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
//		Map<Element, ProcessorInfo<ElementProcessor<?, String>>> processors = stringProcessorFactory.creatProcessors(Collections.singleton(document), progressMonitor);
//		ProcessorInfo<ElementProcessor<?, String>> processorInfo = processors.get(document);
//		ElementProcessor<?, String> processor = processorInfo.getProcessor();
//		RootElementMessage<?, String, ?> rootMessage = processor.sendMessages("Hello");
//		dump(rootMessage);
//	}	
	
	// TODO double single page
	
	// TODO container
	
	// TODO double container
	
	// TODO connection
	
	// TODO double connection
	
	// TODO link to page
	
	// TODO double link to page
	
	// TODO link to element
	
	// TODO double link to element
	

}
