package org.nasdanika.graph.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
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
import org.nasdanika.graph.emf.EObjectConnection;
import org.nasdanika.graph.emf.EObjectGraphFactory;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EOperationConnection;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;
import org.nasdanika.graph.processor.function.MessageProcessorFactory;
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
			if (((EObjectNode) connection.getSource()).get() == source() && ((EObjectNode) connection.getTarget()).get() == target()) {
				if (connection instanceof EReferenceConnection) {
					EReferenceConnection eRefCon = (EReferenceConnection) connection;
					return eRefCon.get().reference() == type() && eRefCon.get().index() == index();
				}
				if (connection instanceof EOperationConnection) {
					EOperationConnection eOpCon = (EOperationConnection) connection;
					return eOpCon.get().operation() == type() && eOpCon.get().index() == index();
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

	private record TraverseRecord(Collection<EObjectConnection> outgoingConnections, Collection<EObjectConnection> incomingConnections) {}	
	
	protected GraphResult createGraph(
			boolean parallel, 
			Collection<? extends EObject> entryPoints, 
			ProgressMonitor progressMonitor) {
		
		// Explicitly counting EObject's and connections for assertions
		Map<EObject, List<ConnectionRecord>> connectionRecords = new HashMap<>();
		
		for (EObject ep: entryPoints) {
			countConnections(ep, connectionRecords);
		}
		
		int expectedNodes = connectionRecords.size();
		int expectedConnections = connectionRecords.values().stream().mapToInt(Collection::size).sum();
		System.out.println("Objects: " + expectedNodes + ", connections: " + expectedConnections);
				
		EObjectGraphFactory eObjectGraphFactory = new EObjectGraphFactory();  
		Transformer<EObject,EObjectNode> graphFactory = new Transformer<>(eObjectGraphFactory); // Reflective node creation using @ElementFactory annotation
		Map<EObject, EObjectNode> registry = graphFactory.transform(entryPoints, parallel, progressMonitor);
		System.out.println("Registry size: " + registry.size());
		
		Map<EObjectNode, TraverseRecord> traverseRecords = new HashMap<>();
		registry.values().forEach(e -> traverse(e, traverseRecords));
		
		assertEquals(expectedNodes, registry.size());
		assertEquals(expectedNodes, traverseRecords.size());
		int outgoingConnections = traverseRecords.values().stream().mapToInt(tr -> tr.outgoingConnections().size()).sum();
		int incomingConnections = traverseRecords.values().stream().mapToInt(tr -> tr.incomingConnections().size()).sum();
		assertEquals(outgoingConnections, incomingConnections);
						
		List<ConnectionRecord> unmatchedConnectionRecords = connectionRecords
			.values()
			.stream()
			.flatMap(Collection::stream)
			.filter(cr -> {
				List<EObjectConnection> matched = traverseRecords
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
		
		List<EObjectConnection> unmatchedConnections = traverseRecords
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
		
		return new GraphResult(registry, expectedNodes, expectedConnections);
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
	
	private record ConfigResult(GraphResult graphResult, Collection<ProcessorConfig> configs) {}	
	
	protected ConfigResult createConfigs(
			boolean parallel, 
			boolean passThrough,
			List<EPackage> ePackages, 
			ProgressMonitor progressMonitor) {
		
		GraphResult graphResult = createGraph(
				parallel, 
				ePackages,
				progressMonitor);
		
		ProcessorConfigFactory<Object, Object> processorConfigFactory = new NopEndpointProcessorConfigFactory<Object>() {
			
			@Override
			protected boolean isPassThrough(Connection connection) {
				return passThrough;
			}
			
		};
		
		Transformer<Element,ProcessorConfig> processorConfigTransformer = new Transformer<>(processorConfigFactory);				
		Map<Element, ProcessorConfig> configs = processorConfigTransformer.transform(graphResult.registry().values(), parallel, progressMonitor);
		System.out.println("Configs: " + configs.size());
			
		List<ProcessorConfig> configValuesNonNull = configs.values().stream().filter(Objects::nonNull).toList();
		assertEquals(
				configValuesNonNull.size(), 
				passThrough ? graphResult.nodes() : graphResult.nodes() + graphResult.connections());
		return new ConfigResult(graphResult, configValuesNonNull);
	}
		
	@Test
	public void testConfigsSequential() {
		List<EPackage> ePackages = Arrays.asList(
				EcorePackage.eINSTANCE, 
				NcorePackage.eINSTANCE);

		createConfigs(false, false, ePackages, new PrintStreamProgressMonitor());
		createConfigs(false, true, ePackages, new PrintStreamProgressMonitor());
	}			
	
	@Test
	public void testConfigsParallel() {
		List<EPackage> ePackages = Arrays.asList(
				EcorePackage.eINSTANCE, 
				NcorePackage.eINSTANCE);

		createConfigs(true, false, ePackages, new PrintStreamProgressMonitor());
		createConfigs(true, true, ePackages, new PrintStreamProgressMonitor());
	}		
	
	private void testProcessorFactory(boolean parallel, int passes, boolean passThrough) {
		List<EPackage> ePackages = Arrays.asList(
				EcorePackage.eINSTANCE, 
				NcorePackage.eINSTANCE);
		
		for (int i = 0; i < passes; ++i) {
			System.out.println("*** Pass " + i);
			ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
			ConfigResult configs = createConfigs(parallel, passThrough, ePackages, progressMonitor);
			
			ProcessorFactory<Object> processorFactory = new ProcessorFactory<Object>() {

				@SuppressWarnings("unchecked")
				@Override
				protected ProcessorInfo<Object> createProcessor(
						ProcessorConfig config, 
						boolean parallel,
						BiConsumer<Element, BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> processorInfoProvider,
						Consumer<CompletionStage<?>> endpoinWiringStageConsumer, 
						ProgressMonitor progressMonitor) {
					
					if (config instanceof NodeProcessorConfig) {
						return config.toInfo(new NodeProcessor((NodeProcessorConfig<Function<Element,Element>, Function<Element,Element>>) config, endpoinWiringStageConsumer, passThrough));
					}
					
					if (config instanceof ConnectionProcessorConfig) {
						return config.toInfo(new ConnectionProcessor((ConnectionProcessorConfig<Function<Element,Element>, Function<Element,Element>>) config, endpoinWiringStageConsumer));
					}
					
					throw new IllegalArgumentException("Neither node nor connection config: " + config);
				}
				
			};
			
			Map<Element, ProcessorInfo<Object>> processors = processorFactory.createProcessors(configs.configs(), parallel, progressMonitor);
			assertEquals(configs.configs().size(), processors.size());
			
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
			assertEquals(configs.graphResult().connections() * 2, calls);
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
			ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
			ConfigResult configs = createConfigs(parallel, passThrough, ePackages, progressMonitor);
			
			BiFunctionProcessorFactoryImpl processorFactory = new BiFunctionProcessorFactoryImpl(passThrough);
			
			Map<Element, ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>> processors = processorFactory.createProcessors(configs.configs(), parallel, progressMonitor);
			assertEquals(configs.configs().size(), processors.size());
			
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
			assertEquals(configs.graphResult().connections() * 2, calls);
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
			ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
			ConfigResult configs = createConfigs(parallel, passThrough, ePackages, progressMonitor);
									
			ReflectiveProcessorFactoryProvider<Supplier<Integer>, Function<Element, Element>, Function<Element, Element>> processorFactoryProvider = new ReflectiveProcessorFactoryProvider<>(new ReflectiveProcessorFactory()); 			
			ProcessorFactory<Supplier<Integer>> processorFactory = processorFactoryProvider.getFactory();
			
			Map<Element, ProcessorInfo<Supplier<Integer>>> processors = processorFactory.createProcessors(configs.configs(), parallel, progressMonitor);
			assertEquals(configs.configs().size(), processors.size());
			
			Stream<Supplier<Integer>> ps = processors.values().stream().filter(Objects::nonNull).map(ProcessorInfo::getProcessor);			
			if (parallel) {
				ps = ps.parallel();
			}
			
			int calls = ps.mapToInt(Supplier<Integer>::get).sum();
			System.out.println("Calls: " + calls);
			assertEquals(configs.graphResult().connections() * 2, calls);
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
	
	public class TestMessage {

		private int hops;
		
		public TestMessage(int hops) {
			this.hops = hops;
		}
		
		public int getHops() {
			return hops;
		}
		
	}	
		
	protected ConfigResult createMessageConfigs(
			boolean parallel, 
			ProcessorConfigFactory<BiFunction<TestMessage, ProgressMonitor, Integer>, BiFunction<TestMessage, ProgressMonitor, CompletionStage<Integer>>> processorConfigFactory,
			List<EPackage> ePackages, 
			ProgressMonitor progressMonitor) {
		
		GraphResult graphResult = createGraph(
				parallel, 
				ePackages,
				progressMonitor);
				
		Transformer<Element,ProcessorConfig> processorConfigTransformer = new Transformer<>(processorConfigFactory);				
		Map<Element, ProcessorConfig> configs = processorConfigTransformer.transform(graphResult.registry().values(), parallel, progressMonitor);
		System.out.println("Configs: " + configs.size());
			
		List<ProcessorConfig> configValuesNonNull = configs.values().stream().filter(Objects::nonNull).toList();
		assertEquals(configValuesNonNull.size(), graphResult.nodes() + graphResult.connections());
		return new ConfigResult(graphResult, configValuesNonNull);
	}	
	
	private void testMessageProcessorFactory(
			boolean parallel, 
			int passes, 
			ProcessorConfigFactory<BiFunction<TestMessage, ProgressMonitor, Integer>, BiFunction<TestMessage, ProgressMonitor, CompletionStage<Integer>>> processorConfigFactory) {
		List<EPackage> ePackages = Arrays.asList(
				EcorePackage.eINSTANCE, 
				NcorePackage.eINSTANCE);
		
		for (int i = 0; i < passes; ++i) {
			System.out.println("*** Pass " + i);
			ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
			ConfigResult configs = createMessageConfigs(parallel, processorConfigFactory, ePackages, progressMonitor);
			
			MessageProcessorFactory<TestMessage,Integer,TestMessage,CompletionStage<Integer>,Void,Void> processorFactory = new MessageProcessorFactory<TestMessage,Integer,TestMessage,CompletionStage<Integer>,Void,Void>() {

				@Override
				protected TestMessage createSourceMessage(
						Void state,
						Connection sender,
						TestMessage parent,
						CompletionStage<CompletionStage<Integer>> result, 
						ProgressMonitor progressMonitor) {
					return null;
				}

				@Override
				protected TestMessage createTargetMessage(
						Void state,
						Connection sender, 
						TestMessage parent,
						CompletionStage<CompletionStage<Integer>> result, 
						ProgressMonitor progressMonitor) {
					return new TestMessage(parent.getHops() + 1);
				}

				@Override
				protected TestMessage toEndpointArgument(TestMessage message) {
					return message;
				}

				@Override
				protected Integer createConnectionResult(CompletionStage<Integer> endpointResult) {
					return 0;
				}

				@Override
				protected TestMessage createConnectionMessage(
						Void state,
						Connection activator, 
						boolean incomingActivator,
						Node sender, 
						Connection recipient, 
						boolean incomingRrecipient, 
						TestMessage parent,
						CompletionStage<CompletionStage<Integer>> result, 
						ProgressMonitor progressMonitor) {
					return new TestMessage(parent == null ? 0 : parent.getHops() + 1);
				}

				@Override
				protected Integer createNodeResult(
						Map<Connection, CompletionStage<Integer>> incomingResults,
						Map<Connection, CompletionStage<Integer>> outgoingResults) {
					return 1;
				}
				
			};
			
			Map<Element, ProcessorInfo<BiFunction<TestMessage, ProgressMonitor, Integer>>> processors = processorFactory.createProcessors(configs.configs(), parallel, progressMonitor);
			assertEquals(configs.configs().size(), processors.size());
			
			Stream<BiFunction<TestMessage, ProgressMonitor, Integer>> ps = processors
					.values()
					.stream()
					.map(pr -> pr.getProcessor());
			
			if (parallel) {
				ps = ps.parallel();
			}
			
			ps.forEach(mh -> {
				mh.apply(new TestMessage(0), progressMonitor);
			});						
		}	
	}
	
//	@Test
//	public void testMessageProcessorFactoryParallel() {
//		testMessageProcessorFactory(true, 10, true);
//		testMessageProcessorFactory(true, 10, false);
//	}	
//	
	@Test
	public void testMessageProcessorFactorySequential() {				
		AtomicInteger counter = new AtomicInteger();
		
		abstract class Task implements Runnable, Comparable<Task> {
			
			int id;
			
			public Task(int id) {
				this.id = id;
			}

			@Override
			public int compareTo(Task o) {
				return o.id - id;
			}
			
		}

		Queue<Runnable> processingQueue = new PriorityQueue<>(); //				
		long start = System.currentTimeMillis();		
		
		ProcessorConfigFactory<BiFunction<TestMessage, ProgressMonitor, Integer>, BiFunction<TestMessage, ProgressMonitor, CompletionStage<Integer>>> processorConfigFactory = new ProcessorConfigFactory<>() {
			
			@Override
			protected boolean isPassThrough(org.nasdanika.graph.Connection connection) {
				return false;
			}
	
			@Override
			public BiFunction<TestMessage, ProgressMonitor, CompletionStage<Integer>> createEndpoint(
					Connection connection, 
					BiFunction<TestMessage, ProgressMonitor, Integer> handler,
					HandlerType type) {
				
				return (m, p) -> {
					if (m.getHops() > 10 || System.currentTimeMillis() - start > 10000) {
						return CompletableFuture.completedStage(0);
					}
					int val = counter.incrementAndGet();
					
					CompletableFuture<Integer> result = new CompletableFuture<>();
					processingQueue.add(new Task(val) {

						@Override
						public void run() {
							Integer handlerResult = handler.apply(m, p);
							result.complete(handlerResult);
						}

					});
					return result;
				};
			}

		};	
		testMessageProcessorFactory(false, 1, processorConfigFactory);		
		// Processing the queue. Executing a work item may create more work items. Processing until there are not more items
		Runnable workItem;
		while ((workItem = processingQueue.poll()) != null) {
			workItem.run();
		}				
		
		System.out.println("Total messages passed: " + counter.get()); 		
	}
	
	/**
	 * Using CompletableFuture async
	 * @throws InterruptedException 
	 */
	@Test
	public void testMessageProcessorFactoryAsync() throws InterruptedException {				
		AtomicInteger counter = new AtomicInteger();
		
		ProcessorConfigFactory<BiFunction<TestMessage, ProgressMonitor, Integer>, BiFunction<TestMessage, ProgressMonitor, CompletionStage<Integer>>> processorConfigFactory = new ProcessorConfigFactory<>() {
			
			@Override
			protected boolean isPassThrough(org.nasdanika.graph.Connection connection) {
				return false;
			}
	
			@Override
			public BiFunction<TestMessage, ProgressMonitor, CompletionStage<Integer>> createEndpoint(
					Connection connection, 
					BiFunction<TestMessage, ProgressMonitor, Integer> handler,
					HandlerType type) {
				
				return (m, p) -> {
					if (m.getHops() > 5) {
						return CompletableFuture.completedStage(0);
					}
					int val = counter.incrementAndGet();
					if (val % 1000 == 0) {
						System.out.print(".");
					}
					if (val % 100000 == 0) {
						System.out.println();
						System.out.print(val);
					}
					
					return CompletableFuture.supplyAsync(() -> handler.apply(m, p));
				};
			}

		};	
		testMessageProcessorFactory(false, 1, processorConfigFactory);		
		Thread.sleep(30000);
		System.out.println("Total messages passed: " + counter.get()); 		
	}			
	
	/**
	 * Using CompletableFuture async
	 * @throws InterruptedException 
	 */
	@Test
	public void testMessageProcessorFactoryThreadPool() throws Exception {				
		AtomicInteger counter = new AtomicInteger();
		
		BlockingQueue<Runnable> processingQueue = new PriorityBlockingQueue<>();		
		ExecutorService executorService = new ThreadPoolExecutor(5, 20, 60L, TimeUnit.SECONDS, processingQueue) {
			
			AtomicInteger taskCounter = new AtomicInteger();
			
			class ComparableFutureTask<V> extends FutureTask<V> implements Comparable<ComparableFutureTask<V>> {
				
				int id = taskCounter.incrementAndGet();

				@Override
				public int compareTo(ComparableFutureTask<V> o) {
					return o.id - id; // tasks which were submitted later shall execute first - depth first
				}

				protected ComparableFutureTask(Callable<V> callable) {
					super(callable);
				}

				protected ComparableFutureTask(Runnable runnable, V result) {
					super(runnable, result);
				}
				
			}				
						
			@Override
			protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {								
				return new ComparableFutureTask<T>(runnable, value);
			}
			
			@Override
			protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
				return new ComparableFutureTask<T>(callable);
			}
			
		};				
		
		long start = System.currentTimeMillis();
		
		ProcessorConfigFactory<BiFunction<TestMessage, ProgressMonitor, Integer>, BiFunction<TestMessage, ProgressMonitor, CompletionStage<Integer>>> processorConfigFactory = new ProcessorConfigFactory<>() {
			
			@Override
			protected boolean isPassThrough(org.nasdanika.graph.Connection connection) {
				return false;
			}
	
			@Override
			public BiFunction<TestMessage, ProgressMonitor, CompletionStage<Integer>> createEndpoint(
					Connection connection, 
					BiFunction<TestMessage, ProgressMonitor, Integer> handler,
					HandlerType type) {
				
				return (m, p) -> {
					if (m.getHops() > 10 || System.currentTimeMillis() - start > 10000) {
						return CompletableFuture.completedStage(0);
					}
					counter.incrementAndGet();
					
					CompletableFuture<Integer> result = new CompletableFuture<>();
					executorService.submit(() -> {
						Integer handlerResult = handler.apply(m, p);
						result.complete(handlerResult);
					});
					return result;
				};
			}

		};	
		
		int mainProcessedCounter = 0;
		testMessageProcessorFactory(false, 1, processorConfigFactory);		
		// Processing the queue in this thread to "help" the thread pool. Executing a work item may create more work items. Processing until there are not more items
		Runnable workItem;
		while ((workItem = processingQueue.poll()) != null) {
			workItem.run();
			++mainProcessedCounter;
		}				
		
		executorService.shutdown();
		System.out.println("Total messages passed: " + counter.get()); 		
		System.out.println("Main messages passed: " + mainProcessedCounter); 		
	}			
		
	// BiFunction reflective test also requires opening the test package
}
