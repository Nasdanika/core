package org.nasdanika.graph.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import org.nasdanika.graph.message.ElementProcessor;
import org.nasdanika.graph.message.Message;
import org.nasdanika.graph.message.MessageProcessorFactory;
import org.nasdanika.graph.message.RootMessage;
import org.nasdanika.graph.processor.ProcessorInfo;

public class TestMessages {
	
	private MessageProcessorFactory<String,String> messageProcessorFactory = new MessageProcessorFactory<String,String>() {

		@Override
		protected String parentValue(Element element, Message<String> message) {
			return message.getValue() + ".P";
		}

		@Override
		protected String childValue(Element element, Message<String> message, Element child) {
			return message.getValue() + ".C";
		}

		@Override
		protected String sourceValue(Connection connection, Message<String> message) {
			return message.getValue() + ".S";
		}

		@Override
		protected String targetValue(Connection connection, Message<String> message) {
			return message.getValue() + ".T";
		}

		@Override
		protected String outgoingValue(Node node, Message<String> message, Connection outgoingConnection) {
			return message.getValue() + ".O";
		}

		@Override
		protected String incomingValue(Node node, Message<String> message, Connection incomingConnection) {
			return message.getValue() + ".I";
		}

		@Override
		protected String clientValue(Element element, Message<String> message, String clientKey) {
			return message.getValue() + ".L";
		}
		
	};
	
	@Test
	public void testSingleElement() {
		ObjectNode<String> node = new ObjectNode<>("Node");
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>>> processors = messageProcessorFactory.createProcessors(Collections.singleton(node), progressMonitor);
		ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>> processorInfo = processors.get(node);
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
		Map<Element, ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>>> processors = messageProcessorFactory.createProcessors(Collections.singleton(parent), progressMonitor);
		
		ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>> parentInfo = processors.get(parent);
		BiConsumer<Message<String>,String> parentClientEndpoint = parentInfo.getClientSynapse("parent test").getEndpoint().toCompletableFuture().join();		
		RootMessage<String> parentRootMessage = new RootMessage<>("Parent Root");
		parentClientEndpoint.accept(parentRootMessage, "World");
		System.out.println("Parent message children: " + parentRootMessage.getChildren());
		
		ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>> childInfo = processors.get(child);
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
		Map<Element, ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>>> processors = messageProcessorFactory.createProcessors(Collections.singleton(source), progressMonitor);
		ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>> processorInfo = processors.get(source);
		BiConsumer<Message<String>,String> clientEndpoint = processorInfo.getClientSynapse("test").getEndpoint().toCompletableFuture().join();		
		RootMessage<String> rootMessage = new RootMessage<>("Root");
		clientEndpoint.accept(rootMessage, "World");
	}
	
	@Test
	public void testIncomingConnection() {
		ObjectNode<String> target = new ObjectNode<>("Target");
		ObjectConnection<String> incomingConnection = new ObjectConnection<>(null, target, false, "Incoming connection");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>>> processors = messageProcessorFactory.createProcessors(Collections.singleton(target), progressMonitor);
		ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>> processorInfo = processors.get(target);
		BiConsumer<Message<String>,String> clientEndpoint = processorInfo.getClientSynapse("test").getEndpoint().toCompletableFuture().join();		
		RootMessage<String> rootMessage = new RootMessage<>("Root");
		clientEndpoint.accept(rootMessage, "World");
	}
		
	@Test
	public void testConnection() {
		ObjectNode<String> source = new ObjectNode<>("Source");
		ObjectNode<String> target = new ObjectNode<>("Target");
		ObjectConnection<String> connection = new ObjectConnection<>(source, target, false, "Connection");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>>> processors = messageProcessorFactory.createProcessors(Collections.singleton(target), progressMonitor);
		
		
		ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>> sourceProcessorInfo = processors.get(source);
		BiConsumer<Message<String>,String> sourceClientEndpoint = sourceProcessorInfo.getClientSynapse("source test").getEndpoint().toCompletableFuture().join();		
		RootMessage<String> sourceRootMessage = new RootMessage<>("Source Root");
		sourceClientEndpoint.accept(sourceRootMessage, "Source World");
		
		ProcessorInfo<Consumer<Message<String>>, BiConsumer<Message<String>, String>, String, ElementProcessor<String, String>> targetProcessorInfo = processors.get(target);
		BiConsumer<Message<String>,String> targetClientEndpoint = targetProcessorInfo.getClientSynapse("target test").getEndpoint().toCompletableFuture().join();		
		RootMessage<String> targetRootMessage = new RootMessage<>("Target Root");
		targetClientEndpoint.accept(targetRootMessage, "Target World");
	}
	
}
