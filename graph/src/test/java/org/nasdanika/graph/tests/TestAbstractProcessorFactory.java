package org.nasdanika.graph.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.ObjectConnection;
import org.nasdanika.graph.ObjectNode;
import org.nasdanika.graph.SimpleNode;
import org.nasdanika.graph.processor.AbstractProcessorFactory;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;

public class TestAbstractProcessorFactory {
	
	private AbstractProcessorFactory<Function<String,String>, Function<String,String>> processorFactory = new AbstractProcessorFactory<Function<String,String>, Function<String,String>>() {

		@Override
		protected Function<String, String> getHandler(
				ProcessorConfig config, 
				Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Function<String, String> getParentHandler(
				ProcessorConfig config,
				Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Function<String, String> getChildHandler(ProcessorConfig config, Element child,
				Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Function<String, String> getOutgoingHandler(
				NodeProcessorConfig<Function<String, String>, Function<String, String>> config,
				Connection outgoingConnection, Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider,
				Map<Connection, Function<String, String>> incomingEndpoints,
				Map<Connection, Function<String, String>> outgoingEndpoints) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Function<String, String> getIncomingHandler(
				NodeProcessorConfig<Function<String, String>, Function<String, String>> config,
				Connection incomingConnection, Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider,
				Map<Connection, Function<String, String>> incomingEndpoints,
				Map<Connection, Function<String, String>> outgoingEndpoints) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Function<String, String> getHandler(
				NodeProcessorConfig<Function<String, String>, Function<String, String>> config,
				Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider,
				Map<Connection, Function<String, String>> incomingEndpoints,
				Map<Connection, Function<String, String>> outgoingEndpoints) {
			if (parentHandler != null) {
				return a -> parentHandler.apply(a + " World");
			}
			return a -> a + " World";
		}

		@Override
		protected Function<String, String> getChildHandler(
				NodeProcessorConfig<Function<String, String>, Function<String, String>> config, Element child,
				Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider,
				Map<Connection, Function<String, String>> incomingEndpoints,
				Map<Connection, Function<String, String>> outgoingEndpoints) {
			return a -> a + " child handler";
		}

		@Override
		protected Function<String, String> getParentHandler(
				NodeProcessorConfig<Function<String, String>, Function<String, String>> config,
				Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider,
				Map<Connection, Function<String, String>> incomingEndpoints,
				Map<Connection, Function<String, String>> outgoingEndpoints) {
			return a -> a + " parent handler";
		}

		@Override
		protected Function<String, String> getSourceHandler(
				ConnectionProcessorConfig<Function<String, String>, Function<String, String>> config,
				Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider,
				Function<String, String> targetEndpiont) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Function<String, String> getTargetHandler(
				ConnectionProcessorConfig<Function<String, String>, Function<String, String>> config,
				Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider,
				Function<String, String> sourceEndpiont) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Function<String, String> getHandler(
				ConnectionProcessorConfig<Function<String, String>, Function<String, String>> config,
				Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider,
				Function<String, String> sourceEndpoint, Function<String, String> targetEndpoint) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Function<String, String> getChildHandler(
				ConnectionProcessorConfig<Function<String, String>, Function<String, String>> config, Element child,
				Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider,
				Function<String, String> sourceEndpoint, Function<String, String> targetEndpoint) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Function<String, String> getParentHandler(
				ConnectionProcessorConfig<Function<String, String>, Function<String, String>> config,
				Function<String, String> parentHandler,
				Function<Element, Function<String, String>> childHandlerProvider,
				Function<String, String> sourceEndpoint, Function<String, String> targetEndpoint) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Function<String, String> createEndpoint(
				Connection connection, 
				Function<String, String> handler,
				HandlerType type) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	@Test
	public void testSingleElement() {
		SimpleNode node = new SimpleNode();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, Function<String, String>> processors = processorFactory.creatProcessors(Collections.singleton(node), progressMonitor); 
		Function<String, String> processor = processors.get(node);		
		String result = processor.apply("Hello");
		System.out.println(result);
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
		Map<Element, Function<String, String>> processors = processorFactory.creatProcessors(Collections.singleton(parent), progressMonitor); 
		Function<String, String> processor = processors.get(parent);		
		String result = processor.apply("Hello parent");
		System.out.println(result);
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
		Map<Element, Function<String, String>> processors = processorFactory.creatProcessors(List.of(parent), progressMonitor); 
		Function<String, String> processor = processors.get(child);		
		String result = processor.apply("Hello child");
		System.out.println(result);
	}
	
	@Test
	public void testOutgoingConnection() {
		ObjectNode<String> source = new ObjectNode<>("Source");
		ObjectConnection<String> outgoingConnection = new ObjectConnection<>(source, null, false, "Outgoing connection");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, Function<String, String>> processors = processorFactory.creatProcessors(Collections.singleton(source), progressMonitor); 
		Function<String, String> processor = processors.get(source);		
		String result = processor.apply("Hello source");
		System.out.println(result);
	}
	
	@Test
	public void testIncomingConnection() {
		ObjectNode<String> target = new ObjectNode<>("Target");
		ObjectConnection<String> incomingConnection = new ObjectConnection<>(null, target, false, "Incoming connection");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, Function<String, String>> processors = processorFactory.creatProcessors(Collections.singleton(target), progressMonitor); 
		Function<String, String> processor = processors.get(target);		
		String result = processor.apply("Hello target");
		System.out.println(result);
	}
		
	@Test
	public void testConnection() {
		ObjectNode<String> source = new ObjectNode<>("Source");
		ObjectNode<String> target = new ObjectNode<>("Target");
		ObjectConnection<String> connection = new ObjectConnection<>(source, target, false, "Connection");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();

		Map<Element, Function<String, String>> processors = processorFactory.creatProcessors(Collections.singleton(source), progressMonitor); 
		Function<String, String> sourceProcessor = processors.get(source);		
		String sourceResult = sourceProcessor.apply("Hello from source");
		System.out.println(sourceResult);

		Function<String, String> targetProcessor = processors.get(source);		
		String targetResult = targetProcessor.apply("Hello from target");
		System.out.println(targetResult);
	}
	

}
