package org.nasdanika.graph.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.emf.EObjectGraphFactory;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;
import org.nasdanika.ncore.NcorePackage;

/**
 * Tests Ecore -> Graph -> Processor -> actions generation
 * @author Pavel
 *
 */
public class TestGraph {
	
	protected Map<Element, ProcessorConfig> createConfigs(
			boolean parallel, 
			boolean passThrough,
			List<EPackage> ePackages, 
			AtomicInteger connectionCounter, 
			ProgressMonitor progressMonitor) {
		
		EObjectGraphFactory graphFactory = new EObjectGraphFactory(parallel);  
		List<EObjectNode> nodes = graphFactory.createGraph(ePackages, progressMonitor);
		System.out.println("Roots: " + nodes.size());
		
		AtomicInteger nodeCounter = new AtomicInteger();
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
				protected Object createProcessor(
						ProcessorConfig config, 
						boolean parallel,
						Function<Element, CompletionStage<Object>> processorProvider,
						Consumer<CompletionStage<?>> stageConsumer, 
						ProgressMonitor progressMonitor) {
					
					if (config instanceof NodeProcessorConfig) {
						return new NodeProcessor((NodeProcessorConfig<Function<Element,Element>, Function<Element,Element>>) config, stageConsumer, passThrough);
					}
					
					if (config instanceof ConnectionProcessorConfig) {
						return new ConnectionProcessor((ConnectionProcessorConfig<Function<Element,Element>, Function<Element,Element>>) config, stageConsumer);
					}
					
					throw new IllegalArgumentException("Neither node nor connection config: " + config);
				}
				
			};
			
			Map<Element, Object> processors = processorFactory.createProcessors(configs, parallel, progressMonitor);
			assertEquals(configs.size(), processors.size());
			
			@SuppressWarnings("unchecked")
			Stream<Supplier<Integer>> ps = processors
					.values()
					.stream()
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
	public void testProcessorFactoryParallel() {
		testProcessorFactory(true, 10, true);
		testProcessorFactory(true, 10, false);
	}	
	
	@Test
	public void testProcessorFactorySequential() {
		testProcessorFactory(false, 1, true);
		testProcessorFactory(false, 1, false);
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
			
			Map<Element, BiFunction<Object, ProgressMonitor, Object>> processors = processorFactory.createProcessors(configs, parallel, progressMonitor);
			assertEquals(configs.size(), processors.size());
			
			Stream<BiFunction<Object,ProgressMonitor,Object>> ps = processors
					.values()
					.stream()
					.filter(p -> {
						return p instanceof BiFunctionNodeProcessor;
					})
					.map(p -> (BiFunction<Object,ProgressMonitor,Object>) p);
			
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
			
			Map<Element, Supplier<Integer>> processors = processorFactory.createProcessors(configs, parallel, progressMonitor);
			assertEquals(configs.size(), processors.size());
			
			Stream<Supplier<Integer>> ps = processors.values().stream().filter(Objects::nonNull);			
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
