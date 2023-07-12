package org.nasdanika.graph.tests;

import java.io.File;
import java.io.IOException;
import java.lang.module.ModuleDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.JGraphTAdapter;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.emf.EObjectGraphFactory;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.emf.EObjectNodeProcessorReflectiveFactory;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.gen.ActionSiteGenerator;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.URINodeProcessor;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.emf.EObjectReflectiveProcessorFactory;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.models.ecore.graph.EClassNode;
import org.nasdanika.models.ecore.graph.EcoreGraphFactory;
import org.nasdanika.models.ecore.graph.processors.EcoreNodeProcessorFactory;
import org.nasdanika.models.ecore.test.Fox;
import org.nasdanika.models.ecore.test.TestPackage;
import org.nasdanika.models.ecore.test.processors.EcoreGenTestProcessorsFactory;
import org.nasdanika.models.ecore.test.util.TestObjectLoaderSupplier;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.persistence.ObjectLoaderResource;

/**
 * Tests Ecore -> Graph -> Processor -> actions generation
 * @author Pavel
 *
 */
public class TestGraph {
	
	private void testProcessorFactory(boolean parallel, int passes) throws IOException, DiagnosticException {
		List<EPackage> ePackages = Arrays.asList(
				EcorePackage.eINSTANCE, 
				NcorePackage.eINSTANCE);
		
		for (int i = 0; i < passes; ++i) {
			System.out.println("*** Pass " + i);
			EObjectGraphFactory graphFactory = new EObjectGraphFactory(parallel);  
			ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
			List<EObjectNode> nodes = graphFactory.createGraph(ePackages, progressMonitor);
			System.out.println("Roots: " + nodes.size());
			
			AtomicLong nodeCounter = new AtomicLong();
			AtomicLong connectionCounter = new AtomicLong();
			for (EObjectNode node: nodes) {
				node.accept(e -> {
					(e instanceof Node ? nodeCounter : connectionCounter).incrementAndGet();
				});				
			}
			System.out.println("Nodes: " + nodeCounter.get() + ", Connections: " + connectionCounter.get());
			
			ProcessorConfigFactory<Object, Object> processorConfigFactory = new NopEndpointProcessorConfigFactory<Object>() {
			};
			
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
		testProcessorFactory(true, 10);
	}	
	
	@Test
	public void testProcessorFactorySequential() throws IOException, DiagnosticException {
		testProcessorFactory(false, 1);
	}	
	
}
