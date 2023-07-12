package org.nasdanika.graph.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.emf.EObjectGraphFactory;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfigFactory;
import org.nasdanika.ncore.NcorePackage;

/**
 * Tests Ecore -> Graph -> Processor -> actions generation
 * @author Pavel
 *
 */
public class TestGraph {
	
	private void testProcessorFactory(boolean parallel, int passes, boolean passThrough) throws IOException, DiagnosticException {
		List<EPackage> ePackages = Arrays.asList(
				EcorePackage.eINSTANCE, 
				NcorePackage.eINSTANCE);
		
		for (int i = 0; i < passes; ++i) {
			System.out.println("*** Pass " + i);
			EObjectGraphFactory graphFactory = new EObjectGraphFactory(parallel);  
			ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
			List<EObjectNode> nodes = graphFactory.createGraph(ePackages, progressMonitor);
			System.out.println("Roots: " + nodes.size());
			
			AtomicInteger nodeCounter = new AtomicInteger();
			AtomicInteger connectionCounter = new AtomicInteger();
			for (EObjectNode node: nodes) {
				node.accept(e -> {
					(e instanceof Node ? nodeCounter : connectionCounter).incrementAndGet();
				});				
			}
			System.out.println("Nodes: " + nodeCounter.get() + ", Connections: " + connectionCounter.get());
			
			ProcessorConfigFactory<Object, Object> processorConfigFactory = new NopEndpointProcessorConfigFactory<Object>() {
				
				@Override
				protected boolean isPassThrough(Connection connection) {
					return passThrough;
				}
				
			};
			Map<Element, ProcessorConfig> configs = processorConfigFactory.createConfigs(nodes, parallel, progressMonitor);
			System.out.println("Configs: " + configs.size());
			int expectedConfigSize = nodeCounter.get();
			if (!passThrough) {
				expectedConfigSize += connectionCounter.get();
			}
			assertEquals(expectedConfigSize, configs.size());
			
			
//			Context context = Context.EMPTY_CONTEXT;
//			Consumer<Diagnostic> diagnosticConsumer = d -> d.dump(System.out, 0);
//	
//			List<Function<URI,Action>> actionProviders = new ArrayList<>();		
//			
//			EcoreGenTestProcessorsFactory ecoreGenTestProcessorFactory = new EcoreGenTestProcessorsFactory();
//			
//			
//			EcoreNodeProcessorFactory ecoreNodeProcessorFactory = new EcoreNodeProcessorFactory(
//					context, 
//					(uri, pm) -> {
//						for (Function<URI, Action> ap: actionProviders) {
//							Action prototype = ap.apply(uri);
//							if (prototype != null) {
//								return prototype;
//							}
//						}
//						return null;
//					},
//					diagnosticConsumer,
//					ecoreGenTestProcessorFactory);
//			
//			EObjectNodeProcessorReflectiveFactory<Object, WidgetFactory, WidgetFactory, Registry<URI>> eObjectNodeProcessorReflectiveFactory = new EObjectNodeProcessorReflectiveFactory<>(ecoreNodeProcessorFactory);
//			EObjectReflectiveProcessorFactory eObjectReflectiveProcessorFactory = new EObjectReflectiveProcessorFactory(eObjectNodeProcessorReflectiveFactory);
//			org.nasdanika.html.model.app.graph.Registry<URI> registry = eObjectReflectiveProcessorFactory.createProcessors(nodes, false, progressMonitor);
//			System.out.println(registry.getProcessorInfoMap().size() + " " + registry.getProcessorInfoMap().values().stream().filter(pi -> pi.getProcessor() != null).count());
		}
	
	}
		
	@Test
	public void testProcessorFactoryParallel() throws IOException, DiagnosticException {
		testProcessorFactory(true, 10, true);
		testProcessorFactory(true, 10, false);
	}	
	
	@Test
	public void testProcessorFactorySequential() throws IOException, DiagnosticException {
		testProcessorFactory(false, 1, true);
		testProcessorFactory(false, 1, false);
	}	
	
}
