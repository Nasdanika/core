package org.nasdanika.graph.model.tests;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.graph.model.Connection;
import org.nasdanika.graph.model.ConnectionTarget;
import org.nasdanika.graph.model.DocumentedNamedConnection;
import org.nasdanika.graph.model.DocumentedNamedConnectionSource;
import org.nasdanika.graph.model.DocumentedNamedConnectionTarget;
import org.nasdanika.graph.model.DocumentedNamedGraph;
import org.nasdanika.graph.model.DocumentedNamedNode;
import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelFactory;
import org.nasdanika.graph.model.adapters.ConnectionAdapter;
import org.nasdanika.graph.model.adapters.ElementAdapter;
import org.nasdanika.graph.model.adapters.GraphAdapterFactory;
import org.nasdanika.graph.model.adapters.NodeAdapter;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.ncore.NamedElement;

public class TestAdapters {
	
	@Test
	public void testAdapters() {
		// Creating a test graph
		ModelFactory factory = ModelFactory.eINSTANCE;
		DocumentedNamedGraph<GraphElement> graph = factory.createDocumentedNamedGraph();
		graph.setName("My process");
		
		DocumentedNamedConnectionSource<Connection<?>> start = factory.createDocumentedNamedConnectionSource();
		start.setName("Start");
		graph.getElements().add(start);
		
		DocumentedNamedNode<Connection<?>> processor = factory.createDocumentedNamedNode();
		processor.setName("Processor");
		graph.getElements().add(processor);
		
		DocumentedNamedConnectionTarget<Connection<?>> end = factory.createDocumentedNamedConnectionTarget();
		end.setName("End");
		graph.getElements().add(end);
		
		DocumentedNamedConnection<ConnectionTarget<?>> s2p = factory.createDocumentedNamedConnection();
		s2p.setName("Call processor");		
		start.getOutgoingConnections().add(s2p);
		s2p.setTarget(processor);
		
		DocumentedNamedConnection<ConnectionTarget<?>> p2e = factory.createDocumentedNamedConnection();
		p2e.setName("Provide result");		
		processor.getOutgoingConnections().add(p2e);
		p2e.setTarget(end);
				
		// Creating adapters
		GraphAdapterFactory graphAdapterFactory = new GraphAdapterFactory();  
		Transformer<EObject,ElementAdapter<?>> graphFactory = new Transformer<>(graphAdapterFactory); // Reflective node creation using @ElementFactory annotation
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<EObject, ElementAdapter<?>> registry = graphFactory.transform(Collections.singleton(graph), false, progressMonitor);
		System.out.println("Registry size: " + registry.size());
		
		for (Entry<EObject, ElementAdapter<?>> re: registry.entrySet()) {
			ElementAdapter<?> value = re.getValue();
			System.out.println(((NamedElement) re.getKey()).getName() + " -> " + value);
			if (value instanceof ConnectionAdapter) {
				ConnectionAdapter connection = (ConnectionAdapter) value;
				System.out.println("\t" + ((NamedElement) connection.getSource().get()).getName());
				System.out.println("\t" + ((NamedElement) connection.getTarget().get()).getName());
			} else if (value instanceof NodeAdapter) {
				NodeAdapter nodeAdapter = (NodeAdapter) value;
				System.out.println("\t" + nodeAdapter.getOutgoingConnections());
				System.out.println("\t" + nodeAdapter.getIncomingConnections());				
			}
		}
		
		// Configs and processors
		NopEndpointProcessorConfigFactory<Function<String,String>> processorConfigFactory = new NopEndpointProcessorConfigFactory<>() {
			
			protected boolean isPassThrough(org.nasdanika.graph.Connection connection) {
				return false;
			};
			
		};
		
		Transformer<org.nasdanika.graph.Element, ProcessorConfig> transformer = new Transformer<>(processorConfigFactory);
		Map<org.nasdanika.graph.Element, ProcessorConfig> configs = transformer.transform(registry.values(), false, progressMonitor);

		System.out.println(configs.size());
	}

}
