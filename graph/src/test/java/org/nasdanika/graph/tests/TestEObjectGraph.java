package org.nasdanika.graph.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.emf.EClassConnection;
import org.nasdanika.graph.emf.EContainerConnection;
import org.nasdanika.graph.emf.EObjectGraphFactory;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EOperationConnection;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;
import org.nasdanika.ncore.NcorePackage;

/**
 * Tests Ecore -> Graph -> Processor generation
 * @author Pavel
 *
 */
public class TestEObjectGraph {
	
	private record GraphResult(Map<EObject, EObjectNode> registry, int nodes, int connections) {}	
	
	// For matching
	record ConnectionRecord(EObject source, EObject target, int index, Object type) {
		
		boolean match(Connection connection) {
			if (((EObjectNode) connection.getSource()).getTarget() == source() && ((EObjectNode) connection.getTarget()).getTarget() == target()) {
				if (connection instanceof EReferenceConnection) {
					EReferenceConnection eRefCon = (EReferenceConnection) connection;
					return eRefCon.getReference() == type() && eRefCon.getIndex() == index();
				}
				if (connection instanceof EOperationConnection) {
					EOperationConnection eOpCon = (EOperationConnection) connection;
					return eOpCon.getOperation() == type() && eOpCon.getIndex() == index();
				}
				if (connection instanceof EClassConnection) {
					return type() == EcorePackage.Literals.ECLASS;
				}
				if (connection instanceof EContainerConnection) {
					return type() == EcorePackage.Literals.EOBJECT___ECONTAINER;
				}
				throw new IllegalArgumentException("Unexpected connection type: " + connection);
			}
			return false;
		}
		
	}

	private record TraverseRecord(Collection<Connection> outgoingConnections, Collection<Connection> incomingConnections) {}	
	
	protected GraphResult createGraph(
			boolean parallel, 
			Collection<? extends EObject> entryPoints, 
			ProgressMonitor progressMonitor) {
		
		// Explicitly counting EObject's and connections for assertions
		Map<EObject, List<ConnectionRecord>> connectionRecords = new HashMap<>();
		
		for (EObject ep: entryPoints) {
			countConnections(ep, connectionRecords);
		}
		
		int expectedObjects = connectionRecords.size();
		int expectedConnections = connectionRecords.values().stream().mapToInt(Collection::size).sum();
		System.out.println("Objects: " + expectedObjects + ", connections: " + expectedConnections);
				
		EObjectGraphFactory eObjectGraphFactory = new EObjectGraphFactory();  
		Transformer<EObject,EObjectNode> graphFactory = new Transformer<>(eObjectGraphFactory); // Reflective node creation using @ElementFactory annotation
		Map<EObject, EObjectNode> registry = graphFactory.transform(entryPoints, parallel, progressMonitor);
		System.out.println("Registry size: " + registry.size());
		
		Map<EObjectNode, TraverseRecord> traverseRecords = new HashMap<>();
		registry.values().forEach(e -> traverse(e, traverseRecords));
		
		assertEquals(expectedObjects, registry.size());
		assertEquals(expectedObjects, traverseRecords.size());
		int outgoingConnections = traverseRecords.values().stream().mapToInt(tr -> tr.outgoingConnections().size()).sum();
		int incomingConnections = traverseRecords.values().stream().mapToInt(tr -> tr.incomingConnections().size()).sum();
		assertEquals(outgoingConnections, incomingConnections);
						
		List<ConnectionRecord> unmatchedConnectionRecords = connectionRecords
			.values()
			.stream()
			.flatMap(Collection::stream)
			.filter(cr -> {
				List<Connection> matched = traverseRecords
					.values()
					.stream()
					.flatMap(tr -> tr.outgoingConnections().stream())
					.filter(cr::match)
					.toList();
				if (matched.size() > 1) {
					fail("More than one match: " + matched);
				}
				return matched.isEmpty();
			})
			.toList();
		
		assertEquals(0, unmatchedConnectionRecords.size());
		
		List<Connection> unmatchedConnections = traverseRecords
				.values()
				.stream()
				.flatMap(tr -> tr.outgoingConnections().stream())
				.filter(oc -> {
					List<ConnectionRecord> matched = connectionRecords
						.values()
						.stream()
						.flatMap(Collection::stream)
						.filter(cr -> cr.match(oc))
						.toList();
					if (matched.size() > 1) {
						fail("More than one match: " + matched);
					}
					return matched.isEmpty();
				})
				.toList();
			
		assertEquals(0, unmatchedConnections.size());						
		assertEquals(expectedConnections, outgoingConnections);
		
		
//		AtomicInteger nodeCounter = new AtomicInteger();
//		AtomicInteger connectionCounter = new AtomicInteger(),
//		
//		Collection<EObjectNode> rootNodes = roots.values();
//		for (EObjectNode node: rootNodes) {
//			node.accept(e -> {
//				(e instanceof Node ? nodeCounter : connectionCounter).incrementAndGet();
//			});				
//		}
//		System.out.println("Nodes: " + nodeCounter.get() + ", Connections: " + connectionCounter.get());
//		
//		
//		System.out.println("Total references: " + totalRefs);
//		
//		assertEquals(referenceCount.size(), nodeCounter.get());
//		assertEquals(totalRefs + referenceCount.size(), connectionCounter.get());
		
		return null; //new GraphResult(registry, );
	}
	
	/**
	 * Counts references for a given eObject
	 * @param eObject 
	 * @return
	 */
	protected void countConnections(EObject eObject, Map<EObject, List<ConnectionRecord>> collector) {
		if (eObject != null && !collector.containsKey(eObject)) {
			List<ConnectionRecord> connections = new ArrayList<>();
			collector.put(eObject, connections);
			for (EReference eRef: eObject.eClass().getEAllReferences()) {
				if (eRef.isMany()) {
					int idx = 0;
					for (Object refVal: ((Collection<?>) eObject.eGet(eRef))) {
						if (refVal != null) {
							connections.add(new ConnectionRecord(eObject, (EObject) refVal, idx++, eRef));
							countConnections((EObject) refVal, collector);
						}
					}
				} else {
					Object refVal = eObject.eGet(eRef);
					if (refVal != null) {
						connections.add(new ConnectionRecord(eObject, (EObject) refVal, -1, eRef));
						countConnections((EObject) refVal, collector);							
					}
				}
			}
			
			EObject eContainer = eObject.eContainer();
			if (eContainer != null) {
				connections.add(new ConnectionRecord(eObject, eContainer, -1, EcorePackage.Literals.EOBJECT___ECONTAINER));
				countConnections(eContainer, collector);
			}
			
			EClass eClass = eObject.eClass();
			if (eClass != null && eClass != eObject) {
				connections.add(new ConnectionRecord(eObject, eClass, -1, EcorePackage.Literals.ECLASS));
				countConnections(eClass, collector);					
			}
		}
	}
	
	/**
	 * 
	 * @param eObjectNode
	 * @param collector counter has 2 elements - connections traversed from source to target (outgoing), connections traversed from target to source (incoming).
	 */
	protected void traverse(EObjectNode eObjectNode, Map<EObjectNode, TraverseRecord> collector) {
		if (!collector.containsKey(eObjectNode)) {
			TraverseRecord traverseRecord = new TraverseRecord(eObjectNode.getOutgoingConnections(), eObjectNode.getIncomingConnections());
			collector.put(eObjectNode, traverseRecord);
			for (Connection oc: eObjectNode.getOutgoingConnections()) {
				traverse((EObjectNode) oc.getTarget(), collector);
			}
			for (Connection ic: eObjectNode.getIncomingConnections()) {
				traverse((EObjectNode) ic.getSource(), collector);				
			}
		}		
	}
	
	@Test
	public void testGraphFactorySequential() {
		List<EPackage> ePackages = Arrays.asList(
				EcorePackage.eINSTANCE, 
				NcorePackage.eINSTANCE);

		createGraph(false, ePackages, new PrintStreamProgressMonitor());
	}			
	
	@Test
	public void testGraphFactoryParallel() {
		List<EPackage> ePackages = Arrays.asList(
				EcorePackage.eINSTANCE, 
				NcorePackage.eINSTANCE);

		createGraph(true, ePackages, new PrintStreamProgressMonitor());
	}		
	
	protected Map<Element, ProcessorConfig> createConfigs(
			boolean parallel, 
			boolean passThrough,
			List<EPackage> ePackages, 
			AtomicInteger connectionCounter, 
			ProgressMonitor progressMonitor) {
						
		AtomicInteger nodeCounter = new AtomicInteger();
		
		Collection<EObjectNode> rootNodes = createGraph(
				parallel, 
				ePackages,
				nodeCounter,
				connectionCounter,
				progressMonitor);
		
		ProcessorConfigFactory<Object, Object> processorConfigFactory = new NopEndpointProcessorConfigFactory<Object>() {
			
			@Override
			protected boolean isPassThrough(Connection connection) {
				return passThrough;
			}
			
		};
		Map<Element, ProcessorConfig> configs = processorConfigFactory.createConfigs(rootNodes, parallel, progressMonitor);
		System.out.println("Configs: " + configs.size());
				
		AtomicLong elementCounter = new AtomicLong();		
		for (EObjectNode node: rootNodes) {
			node.accept(e -> {
				if (e instanceof Node || !passThrough) {
					elementCounter.incrementAndGet();
					ProcessorConfig config = configs.get(e);
					assertNotNull(config);
					Element configElement = config.getElement();
					assertEquals(e, configElement);
					assertTrue(configElement == e);
				}
			});				
		}		
		
		int expectedConfigSize = nodeCounter.get();
		if (!passThrough) {
			expectedConfigSize += connectionCounter.get();
		}
		assertEquals(expectedConfigSize, configs.size());
		assertEquals(expectedConfigSize, elementCounter.get());
		return configs;
	}
	
	private void testProcessorFactory(boolean parallel, int passes, boolean passThrough) {
		List<EPackage> ePackages = Arrays.asList(
				EcorePackage.eINSTANCE, 
				NcorePackage.eINSTANCE);
		
		for (int i = 0; i < passes; ++i) {
			System.out.println("*** Pass " + i);
			AtomicInteger connectionCounter = new AtomicInteger();
			ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
			Map<Element, ProcessorConfig> configs = createConfigs(parallel, passThrough, ePackages, connectionCounter, progressMonitor);
			
			ProcessorFactory<Object> processorFactory = new ProcessorFactory<Object>() {

				@SuppressWarnings("unchecked")
				@Override
				protected ProcessorInfo<Object> createProcessor(
						ProcessorConfig config, 
						boolean parallel,
						Function<Element, CompletionStage<ProcessorInfo<Object>>> processorInfoProvider,
						Consumer<CompletionStage<?>> stageConsumer, ProgressMonitor progressMonitor) {
					
					if (config instanceof NodeProcessorConfig) {
						return config.toInfo(new NodeProcessor((NodeProcessorConfig<Function<Element,Element>, Function<Element,Element>>) config, stageConsumer, passThrough));
					}
					
					if (config instanceof ConnectionProcessorConfig) {
						return config.toInfo(new ConnectionProcessor((ConnectionProcessorConfig<Function<Element,Element>, Function<Element,Element>>) config, stageConsumer));
					}
					
					throw new IllegalArgumentException("Neither node nor connection config: " + config);
				}
				
			};
			
			Map<Element, ProcessorInfo<Object>> processors = processorFactory.createProcessors(configs, parallel, progressMonitor);
			assertEquals(configs.size(), processors.size());
			
			@SuppressWarnings("unchecked")
			Stream<Supplier<Integer>> ps = processors
					.values()
					.stream()
					.map(ProcessorInfo::getProcessor)
					.filter(Supplier.class::isInstance)
					.map(s -> (Supplier<Integer>) s);
			
			if (parallel) {
				ps = ps.parallel();
			}
			
			int calls = ps.mapToInt(Supplier<Integer>::get).sum();
			System.out.println("Calls: " + calls);
			assertEquals(connectionCounter.get() * 2, calls);
		}
	
	}
	
	@Test
	public void testProcessorFactorySequential() {
		testProcessorFactory(false, 1, true);
		testProcessorFactory(false, 1, false);
	}			
	
	@Test
	public void testProcessorFactoryParallel() {
		testProcessorFactory(true, 10, true);
		testProcessorFactory(true, 10, false);
	}	
	
	private void testBiFunctionProcessorFactory(boolean parallel, int passes, boolean passThrough) {
		List<EPackage> ePackages = Arrays.asList(
				EcorePackage.eINSTANCE, 
				NcorePackage.eINSTANCE);
		
		for (int i = 0; i < passes; ++i) {
			System.out.println("*** Pass " + i);
			AtomicInteger connectionCounter = new AtomicInteger();
			ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
			Map<Element, ProcessorConfig> configs = createConfigs(parallel, passThrough, ePackages, connectionCounter, progressMonitor);
			
			BiFunctionProcessorFactoryImpl processorFactory = new BiFunctionProcessorFactoryImpl(passThrough);
			
			Map<Element, ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>> processors = processorFactory.createProcessors(configs, parallel, progressMonitor);
			assertEquals(configs.size(), processors.size());
			
			Stream<BiFunction<Object,ProgressMonitor,Object>> ps = processors
					.values()
					.stream()
					.filter(pr -> {
						return pr.getProcessor() instanceof BiFunctionNodeProcessor;
					})
					.map(pr -> (BiFunction<Object,ProgressMonitor,Object>) pr.getProcessor());
			
			if (parallel) {
				ps = ps.parallel();
			}
			
			int calls = ps.mapToInt(p -> {
				return (Integer) ((BiFunction<Object,ProgressMonitor,Object>) p).apply(33, progressMonitor);
			}).sum();
			System.out.println("Calls: " + calls);
			assertEquals(connectionCounter.get() * 2, calls);
		}	
	}
	
	@Test
	public void testBiFunctionProcessorFactoryParallel() {
		testBiFunctionProcessorFactory(true, 10, true);
		testBiFunctionProcessorFactory(true, 10, false);
	}	
	
	@Test
	public void testBiFunctionProcessorFactorySequential() {
		testBiFunctionProcessorFactory(false, 1, true);
		testBiFunctionProcessorFactory(false, 1, false);
	}			
	
	private void testReflectiveProcessorFactory(boolean parallel, int passes, boolean passThrough) {
		List<EPackage> ePackages = Arrays.asList(
				EcorePackage.eINSTANCE, 
				NcorePackage.eINSTANCE);
		
		for (int i = 0; i < passes; ++i) {
			System.out.println("*** Pass " + i);
			AtomicInteger connectionCounter = new AtomicInteger();
			ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
			Map<Element, ProcessorConfig> configs = createConfigs(parallel, passThrough, ePackages, connectionCounter, progressMonitor);
									
			ReflectiveProcessorFactoryProvider<Supplier<Integer>, Function<Element, Element>, Function<Element, Element>> processorFactoryProvider = new ReflectiveProcessorFactoryProvider<>(new ReflectiveProcessorFactory()); 			
			ProcessorFactory<Supplier<Integer>> processorFactory = processorFactoryProvider.getFactory();
			
			Map<Element, ProcessorInfo<Supplier<Integer>>> processors = processorFactory.createProcessors(configs, parallel, progressMonitor);
			assertEquals(configs.size(), processors.size());
			
			Stream<Supplier<Integer>> ps = processors.values().stream().filter(Objects::nonNull).map(ProcessorInfo::getProcessor);			
			if (parallel) {
				ps = ps.parallel();
			}
			
			int calls = ps.mapToInt(Supplier<Integer>::get).sum();
			System.out.println("Calls: " + calls);
			assertEquals(connectionCounter.get() * 2, calls);
		}
	
	}
				
	@Test
	@Disabled // Requires opening the test package
	public void testReflectiveProcessorFactoryParallel() {
		testReflectiveProcessorFactory(true, 10, true);
		testReflectiveProcessorFactory(true, 10, false);
	}	
	
	@Test
	@Disabled // Requires opening the test package
	public void testReflectiveProcessorFactorySequential() {
		testReflectiveProcessorFactory(false, 1, true);
		testReflectiveProcessorFactory(false, 1, false);
	}			
	
	// BiFunction reflective test also requires opening the test package
}
