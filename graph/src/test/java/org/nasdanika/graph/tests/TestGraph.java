package org.nasdanika.graph.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.GraphFactory;
import org.nasdanika.graph.Node;

/**
 * Tests Integer -> Graph
 * @author Pavel
 *
 */
public class TestGraph {
	
	private void testGraphFactory(boolean parallel, int size) {
		List<Integer> elements = new ArrayList<>();
		while (elements.size() < size) {
			elements.add(elements.size());
		}
		
		GraphFactory<Integer> graphFactory = new GraphFactory<>() {
			
			@Override
			protected Element createElement(
					Integer element, 
					boolean parallel, 
					Function<Integer,CompletionStage<Element>> elementProvider, 
					Consumer<CompletionStage<?>> stageConsumer, 
					ProgressMonitor progressMonitor) {
				
				ObjectNode<Integer> node = new ObjectNode<>(element);
				System.out.println("Created " + element + " node: " + node);
				for (int i = 0; i < size; ++i) {
					int targetElement = i;
					if (i < element) {
						System.out.println("\tCreating a > connection to " + i);
						stageConsumer.accept(elementProvider.apply(i).thenAccept(target -> {							
							ObjectConnection<String> connection = new ObjectConnection<String>(node, (Node) target, ">");
							System.out.println("Created > connection from " + element + " to " + targetElement + ": " + connection);							
						}));
					} else if (i > element) {
						System.out.println("\tCreating a < connection to " + i);
						stageConsumer.accept(elementProvider.apply(i).thenAccept(target -> {
							ObjectConnection<String> connection = new ObjectConnection<String>(node, (Node) target, "<");
							System.out.println("Created < connection from " + element + " to " + targetElement + ": " + connection);														
						}));						
					}
				}
				
				System.out.println(node);				
				return node;
			};
			
		};
		
		ProgressMonitor progressMonitor = new NullProgressMonitor();		
		Map<Integer, Element> graph = graphFactory.createGraph(elements, parallel, progressMonitor);
		assertEquals(size, graph.size());
		System.out.println("===");
		for (Entry<Integer, Element> ge: graph.entrySet()) {
			Node node = (Node) ge.getValue();		
			System.out.println("----");
			System.out.println(node);
			for (Connection ic: node.getIncomingConnections()) {
				System.out.println("\t<- " + ((ObjectConnection<?>) ic).getValue() + " " + ic.getSource());
			}
			System.out.println();
			for (Connection oc: node.getOutgoingConnections()) {
				System.out.println("\t-> " + ((ObjectConnection<?>) oc).getValue() + " " + oc.getTarget());
			}			
		}
		// Assertions
		for (Entry<Integer, Element> ge: graph.entrySet()) {
			assertTrue(ge.getValue() instanceof Node);
			Node node = (Node) ge.getValue();		
			assertEquals(size - 1, node.getIncomingConnections().size());
			assertEquals(size - 1, node.getOutgoingConnections().size());
		}		
		
	}
	
	@Test
	public void testGraphFactoryParallel() {
		testGraphFactory(true, 20);
	}	
	
	@Test
	public void testGraphFactorySequential() {
		testGraphFactory(false, 20);
	}			
	
}
