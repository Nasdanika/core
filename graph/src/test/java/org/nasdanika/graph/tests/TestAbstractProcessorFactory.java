package org.nasdanika.graph.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.ObjectConnection;
import org.nasdanika.graph.ObjectNode;
import org.nasdanika.graph.SimpleNode;
import org.nasdanika.graph.processor.AbstractProcessorFactory;
import org.nasdanika.graph.processor.ConnectionHandlerFactory;
import org.nasdanika.graph.processor.ElementHandlerFactory;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NodeHandlerFactory;
import org.nasdanika.graph.processor.ProcessorConfig;

public class TestAbstractProcessorFactory {
	
	private AbstractProcessorFactory<Function<String,String>, Function<String,String>> processorFactory = new AbstractProcessorFactory<Function<String,String>, Function<String,String>>() {

		@Override
		protected ElementHandlerFactory<Function<String, String>, Function<String, String>> getElementHandlerFactory(
				ProcessorConfig config, 
				Map<Element, Function<String, String>> childEndpoints) {
			return new ElementHandlerFactory<Function<String,String>, Function<String,String>>() {
				
				@Override
				public Function<String, String> getParentHandler(Function<String, String> parentEndpoint) {
					return a -> a + " element parent handler";
				}
				
				@Override
				public Function<String, String> getHandler(Function<String, String> parentEndpoint) {
					return a -> a + " element handler";
				}
				
				@Override
				public Function<String, String> getChildHandler(Element child, Supplier<Function<String, String>> parentEndpointSupplier) {
					return a -> a + " element child handler";
				}
			};
		}

		@Override
		protected NodeHandlerFactory<Function<String, String>, Function<String, String>> getNodeHandlerFactory(
				ProcessorConfig config, 
				Map<Element, Function<String, String>> childEndpoints,
				Map<Connection, Function<String, String>> incomingEndpoints,
				Map<Connection, Function<String, String>> outgoingEndpoints) {
			
			return new NodeHandlerFactory<Function<String,String>, Function<String,String>>() {
				
				@Override
				public Function<String, String> getParentHandler(Function<String, String> parentEndpoint) {
					return a -> a + " node parent handler";
				}
				
				@Override
				public Function<String, String> getHandler(Function<String, String> parentEndpoint) {
					return a -> a + " node handler";
				}
				
				@Override
				public Function<String, String> getChildHandler(Element child, Supplier<Function<String, String>> parentEndpointSupplier) {
					return a -> a + " node child handler";
				}
				
				@Override
				public Function<String, String> getOutgoingHandler(Connection outgoingConnection, Supplier<Function<String, String>> parentEndpointSupplier) {
					return a -> a + " node outgoing handler";
				}
				
				@Override
				public Function<String, String> getIncomingHandler(Connection incomingConnection, Supplier<Function<String, String>> parentEndpointSupplier) {
					return a -> a + " node incoming handler";
				}
			};
		}

		@Override
		protected ConnectionHandlerFactory<Function<String, String>, Function<String, String>> getConnectionHandlerFactory(
				ProcessorConfig config, 
				Map<Element, Function<String, String>> childEndpoints,
				Supplier<Function<String, String>> sourceEndpointSupplier,
				Supplier<Function<String, String>> targetEndpointSupplier) {
			
			return new ConnectionHandlerFactory<Function<String,String>, Function<String,String>>() {
				
				@Override
				public Function<String, String> getParentHandler(Function<String, String> parentEndpoint) {
					return a -> a + " connection parent handler";
				}
				
				@Override
				public Function<String, String> getHandler(Function<String, String> parentEndpoint) {
					return a -> a + " connection handler";
				}
				
				@Override
				public Function<String, String> getChildHandler(Element child, Supplier<Function<String, String>> parentEndpointSupplier) {
					return a -> a + " connection child handler";
				}
				
				@Override
				public Function<String, String> getTargetHandler(Supplier<Function<String, String>> parentEndpointSupplier) {
					return a -> a + " connection target handler";
				}
				
				@Override
				public Function<String, String> getSourceHandler(Supplier<Function<String, String>> parentEndpointSupplier) {
					return a -> a + " connection source handler";
				}
			};
		}

		@Override
		protected Function<String, String> createEndpoint(Element element, Function<String, String> handler) {
			return handler;
		}

		@Override
		protected Function<String, String> createParentEndpoint(Element element, Function<String, String> handler) {
			return handler;
		}

		@Override
		protected Function<String, String> createChildEndpoint(Element parent, Element child, Function<String, String> handler) {
			return handler;
		}

		@Override
		protected Function<String, String> createEndpoint(Connection connection, Function<String, String> handler,	HandlerType type) {
			return handler;
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
