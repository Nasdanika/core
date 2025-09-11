package org.nasdanika.graph.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.ObjectConnection;
import org.nasdanika.graph.ObjectNode;
import org.nasdanika.graph.SimpleNode;
import org.nasdanika.graph.message.ElementMessage;
import org.nasdanika.graph.message.ElementProcessor;
import org.nasdanika.graph.message.MessageProcessorFactory;
import org.nasdanika.graph.message.RootElementMessage;
import org.nasdanika.graph.processor.ProcessorInfo;

public class TestMessages {
	
	private MessageProcessorFactory<String> messageProcessorFactory = new MessageProcessorFactory<String>() {
		
		@Override
		protected String targetValue(String messageValue, Node node, Connection incomingConnection) {
			return messageValue + ".T";
		}
		
		@Override
		protected String sourceValue(String messageValue, Node node, Connection outgoingConnection) {
			return messageValue + ".S";
		}
		
		@Override
		protected String outgoingValue(String messageValue, Connection connection) {
			return messageValue + ".O";
		}
		
		@Override
		protected String incomingValue(String messageValue, Connection connection) {
			return messageValue + ".I";
		}
		
		@Override
		protected String childValue(String messageValue, Element parent, Element child) {
			return messageValue + ".C";
		}
		
		@Override
		protected Collection<ElementMessage<?,String,?>> processMessage(ElementProcessor<?,String> processor, ElementMessage<?,String,?> message) {
			System.out.println(processor + " " + message);
			return super.processMessage(processor, message);
		}

		@Override
		protected String parentValue(String messageValue, Element child, Element parent) {
			return messageValue + ".P";
		};
	};
	
	@Test
	public void testSingleElement() {
		SimpleNode node = new SimpleNode();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<ElementProcessor<?, String>>> processors = messageProcessorFactory.creatProcessors(Collections.singleton(node), progressMonitor);
		ProcessorInfo<ElementProcessor<?, String>> processorInfo = processors.get(node);
		ElementProcessor<?, String> processor = processorInfo.getProcessor();
		RootElementMessage<?, String, ?> rootMessage = processor.sendMessages("Hello");
		System.out.println(rootMessage.getValue());
	}
		
	@Test
	public void testParentChild() {
		ObjectNode<String> child = new ObjectNode<>("Child");
		ObjectNode<String> parent = new ObjectNode<>("Parent") {
			@Override
			public Collection<Element> getChildren() {
				Collection<Element> children = new ArrayList<>(super.getChildren());
				children.add(child);
				return children;
			}
		};
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<ElementProcessor<?, String>>> processors = messageProcessorFactory.creatProcessors(Collections.singleton(parent), progressMonitor);
		ProcessorInfo<ElementProcessor<?, String>> processorInfo = processors.get(parent);
		ElementProcessor<?, String> processor = processorInfo.getProcessor();
		RootElementMessage<?, String, ?> rootMessage = processor.sendMessages("Hello");
		System.out.println(rootMessage.getValue());
	}
	
	@Test
	public void testChildParent() {
		ObjectNode<String> child = new ObjectNode<>("Child");
		ObjectNode<String> parent = new ObjectNode<>("Parent") {
			@Override
			public Collection<Element> getChildren() {
				Collection<Element> children = new ArrayList<>(super.getChildren());
				children.add(child);
				return children;
			}
		};
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<ElementProcessor<?, String>>> processors = messageProcessorFactory.creatProcessors(Collections.singleton(parent), progressMonitor);
		ProcessorInfo<ElementProcessor<?, String>> processorInfo = processors.get(child);
		ElementProcessor<?, String> processor = processorInfo.getProcessor();
		RootElementMessage<?, String, ?> rootMessage = processor.sendMessages("Hello");
		System.out.println(rootMessage.getValue());
	}
	
	@Test
	public void testOutgoingConnection() {
		ObjectNode<String> source = new ObjectNode<>("Source");
		ObjectConnection<String> outgoingConnection = new ObjectConnection<>(source, null, false, "Outgoing connection");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<ElementProcessor<?, String>>> processors = messageProcessorFactory.creatProcessors(Collections.singleton(source), progressMonitor);
		ProcessorInfo<ElementProcessor<?, String>> processorInfo = processors.get(source);
		ElementProcessor<?, String> processor = processorInfo.getProcessor();
		RootElementMessage<?, String, ?> rootMessage = processor.sendMessages("Hello");
		System.out.println(rootMessage.getValue());
	}
	
	@Test
	public void testIncomingConnection() {
		ObjectNode<String> target = new ObjectNode<>("Target");
		ObjectConnection<String> incomingConnection = new ObjectConnection<>(null, target, false, "Incoming connection");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<ElementProcessor<?, String>>> processors = messageProcessorFactory.creatProcessors(Collections.singleton(target), progressMonitor);
		ProcessorInfo<ElementProcessor<?, String>> processorInfo = processors.get(target);
		ElementProcessor<?, String> processor = processorInfo.getProcessor();
		RootElementMessage<?, String, ?> rootMessage = processor.sendMessages("Hello");
		System.out.println(rootMessage.getValue());
	}
		
	@Test
	public void testConnection() {
		ObjectNode<String> source = new ObjectNode<>("Source");
		ObjectNode<String> target = new ObjectNode<>("Target");
		ObjectConnection<String> connection = new ObjectConnection<>(source, target, false, "Connection");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<ElementProcessor<?, String>>> processors = messageProcessorFactory.creatProcessors(List.of(source, target), progressMonitor);
		
		ProcessorInfo<ElementProcessor<?, String>> targetProcessorInfo = processors.get(target);
		ElementProcessor<?, String> targetProcessor = targetProcessorInfo.getProcessor();
		RootElementMessage<?, String, ?> targetRootMessage = targetProcessor.sendMessages("Hello from target");
		System.out.println(targetRootMessage.getValue());
		
		ProcessorInfo<ElementProcessor<?, String>> sourceProcessorInfo = processors.get(source);
		ElementProcessor<?, String> sourceProcessor = sourceProcessorInfo.getProcessor();
		RootElementMessage<?, String, ?> sourceRootMessage = sourceProcessor.sendMessages("Hello from source");
		System.out.println(sourceRootMessage.getValue());
	}
	

}
