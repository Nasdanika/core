package org.nasdanika.graph.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.ObjectConnection;
import org.nasdanika.graph.ObjectNode;
import org.nasdanika.graph.SimpleNode;
import org.nasdanika.graph.message.ElementProcessor;
import org.nasdanika.graph.message.Message;
import org.nasdanika.graph.message.MessageProcessorFactory;
import org.nasdanika.graph.message.RootMessage;
import org.nasdanika.graph.processor.ProcessorInfo;

public class TestMessages {
	
	private MessageProcessorFactory<String> messageProcessorFactory = new MessageProcessorFactory<String>() {

		@Override
		protected String parentValue(Element element, Message<String> message) {
			return message.getValue() + ".P";
		}

		@Override
		protected String childValue(Element element, Message<String> message, Element child) {
			return message.getValue() + ".C";
		}
		
	};
	
	@Test
	public void testSingleElement() {
		ObjectNode<String> node = new ObjectNode<>("Node");
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, ElementProcessor<String>>> processors = messageProcessorFactory.createProcessors(Collections.singleton(node), progressMonitor);
		ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, ElementProcessor<String>> processorInfo = processors.get(node);
		BiConsumer<Message<String>,String> clientEndpoint = processorInfo.getClientSynapse("test").getEndpoint().toCompletableFuture().join();		
		RootMessage<String> rootMessage = new RootMessage<>("Root");
		clientEndpoint.accept(rootMessage, "World");
		System.out.println(rootMessage.getChildren());
	}
		
	@Test
	public void testParentChild() {
		ObjectNode<String> child = new ObjectNode<>("Child") {
			
			@Override
			public String toString() {
				return get();
			}
			
		};
		
		ObjectNode<String> parent = new ObjectNode<>("Parent") {
			
			@Override
			public Collection<Element> getChildren() {
				Collection<Element> children = new ArrayList<>(super.getChildren());
				children.add(child);
				return children;
			}
			
			@Override
			public String toString() {
				return get();
			}
			
		};
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, ElementProcessor<String>>> processors = messageProcessorFactory.createProcessors(Collections.singleton(parent), progressMonitor);
		
		ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, ElementProcessor<String>> parentInfo = processors.get(parent);
		BiConsumer<Message<String>,String> parentClientEndpoint = parentInfo.getClientSynapse("parent test").getEndpoint().toCompletableFuture().join();		
		RootMessage<String> parentRootMessage = new RootMessage<>("Parent Root");
		parentClientEndpoint.accept(parentRootMessage, "World");
		System.out.println("Parent message children: " + parentRootMessage.getChildren());
		
		ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, ElementProcessor<String>> childInfo = processors.get(child);
		BiConsumer<Message<String>,String> childClientEndpoint = childInfo.getClientSynapse("child test").getEndpoint().toCompletableFuture().join();		
		RootMessage<String> childRootMessage = new RootMessage<>("Child Root");
		childClientEndpoint.accept(childRootMessage, "World");
		System.out.println("Child message children: " + childRootMessage.getChildren());
	}
	
	@Test
	public void testOutgoingConnection() {
		ObjectNode<String> source = new ObjectNode<>("Source");
		ObjectConnection<String> outgoingConnection = new ObjectConnection<>(source, null, false, "Outgoing connection");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<MessageSender<String>, MessageSender<String>, ElementProcessor<?, String>>> processors = messageProcessorFactory.creatProcessors(Collections.singleton(source), progressMonitor);
		ProcessorInfo<MessageSender<String>, MessageSender<String>, ElementProcessor<?, String>> processorInfo = processors.get(source);
		ElementProcessor<?, String> processor = processorInfo.getProcessor();
		RootElementMessage<?, String, ?> rootMessage = processor.sendMessages("Hello");
		System.out.println(rootMessage.getValue());
	}
	
	@Test
	public void testIncomingConnection() {
		ObjectNode<String> target = new ObjectNode<>("Target");
		ObjectConnection<String> incomingConnection = new ObjectConnection<>(null, target, false, "Incoming connection");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<MessageSender<String>, MessageSender<String>, ElementProcessor<?, String>>> processors = messageProcessorFactory.creatProcessors(Collections.singleton(target), progressMonitor);
		ProcessorInfo<MessageSender<String>, MessageSender<String>, ElementProcessor<?, String>> processorInfo = processors.get(target);
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
		Map<Element, ProcessorInfo<MessageSender<String>, MessageSender<String>, ElementProcessor<?, String>>> processors = messageProcessorFactory.creatProcessors(List.of(source, target), progressMonitor);
		
		ProcessorInfo<MessageSender<String>, MessageSender<String>, ElementProcessor<?, String>> targetProcessorInfo = processors.get(target);
		ElementProcessor<?, String> targetProcessor = targetProcessorInfo.getProcessor();
		RootElementMessage<?, String, ?> targetRootMessage = targetProcessor.sendMessages("Hello from target");
		System.out.println(targetRootMessage.getValue());
		
		ProcessorInfo<MessageSender<String>, MessageSender<String>, ElementProcessor<?, String>> sourceProcessorInfo = processors.get(source);
		ElementProcessor<?, String> sourceProcessor = sourceProcessorInfo.getProcessor();
		RootElementMessage<?, String, ?> sourceRootMessage = sourceProcessor.sendMessages("Hello from source");
		System.out.println(sourceRootMessage.getValue());
	}
	
}
