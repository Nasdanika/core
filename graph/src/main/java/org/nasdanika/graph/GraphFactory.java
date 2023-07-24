package org.nasdanika.graph;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;

/**
 * Creates graph elements from objects. Uses reflection to match factory methods and delegate to them. 
 * @author Pavel
 * @param <T> Element type
 *
 */
public class GraphFactory<T> extends Reflector {

	protected List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();
	
	public GraphFactory(Object... factories) {
		for (Object factory: factories) {
			getAnnotatedElementRecords(factory).forEach(annotatedElementRecords::add);
		}
	}
	
	/**
	 * Joins not yet completed stages. 
	 * @param stages
	 */
	protected void join(Queue<CompletionStage<?>> stages) {
		CompletionStage<?> stage;
		while ((stage = stages.poll()) != null) {
			stage.toCompletableFuture().join();
		}
	}
	
	/**
	 * Creates a graph and returns a map of source elements to nodes created from them and their related elements.
	 * @param elements
	 * @param parallel If false all processing is performed in the caller thread. Otherwise, processing might be performed in multiple threads.
	 * @param progressMonitor
	 * @return
	 */
	public Map<T, Element> createGraph(Collection<T> elements, boolean parallel, ProgressMonitor progressMonitor) {
		record ElementRecord<T>(T element, CompletableFuture<Element> elementCompletableFuture) {}
		
		// Parallel/asynchronous processing
		if (parallel) {
			Map<T,CompletableFuture<Element>> registry = new ConcurrentHashMap<>(); // Registry is used to prevent duplication of creation				
			Queue<CompletionStage<?>> stages = new ConcurrentLinkedQueue<>();
			
			// Constructs a new graph element asynchronously
			Function<T, CompletableFuture<Element>> constructor = new Function<T,CompletableFuture<Element>>() {

				@Override
				public CompletableFuture<Element> apply(T element) {
					// A closure to supply a new element. 
					Supplier<Element> elementSupplier = () -> createElement(
							element, 
							parallel, 
							e -> registry.computeIfAbsent(e, this), 
							stages::add, 
							progressMonitor);
					
						return CompletableFuture.supplyAsync(elementSupplier);
				}
				
			};
			
			List<ElementRecord<T>> ercfl = elements
					.parallelStream()
					.map(e -> new ElementRecord<T>(e,registry.computeIfAbsent(e, constructor)))
					.toList();
			join(stages);
			return ercfl.stream().collect(Collectors.toMap(ElementRecord::element, er -> er.elementCompletableFuture().join()));
		}
		
		// Sequential processing
		Map<T,CompletableFuture<Element>> registry = new LinkedHashMap<>(); // Registry is used to prevent duplication of creation				
		Queue<Runnable> constructionQueue = new ConcurrentLinkedQueue<>(); // 
		Queue<CompletionStage<?>> stages = new ConcurrentLinkedQueue<>();
		
		// Constructs a new graph element 
		Function<T, CompletableFuture<Element>> constructor = new Function<T,CompletableFuture<Element>>() {

			@Override
			public CompletableFuture<Element> apply(T element) {
				// A closure to supply a new element. 
				Supplier<Element> elementSupplier = () -> createElement(
						element, 
						parallel, 
						e -> registry.computeIfAbsent(e, this), 
						stages::add, 
						progressMonitor);
				
				CompletableFuture<Element> ret = new CompletableFuture<>();
				constructionQueue.add(() -> { // A runnable which completes the future
					ret.complete(elementSupplier.get());
				}); 
				return ret;
			}
			
		};
		
		List<ElementRecord<T>> ercfl = elements
				.stream()
				.map(e -> new ElementRecord<T>(e,registry.computeIfAbsent(e, constructor)))
				.toList();
		
		// Processing the queue. Executing a work item may create more work items. Processing until there are not more items
		Runnable workItem;
		while ((workItem = constructionQueue.poll()) != null) {
			workItem.run();
		}										
		join(stages);
		
		return ercfl.stream().collect(Collectors.toMap(ElementRecord::element, er -> er.elementCompletableFuture().join()));
	}
	
	/**
	 * Creates a node for an element
	 * @param element Element
	 * @param parallel If false processing is done in the caller thread. Otherwise it may be done in multiple threads.
	 * @param nodeProvider Provider of nodes for other elements. Those nodes may not exist at the time of this node construction, thus returning a completion stage.
	 * @param stageConsumer Consumer of stages in order to collect exceptions.
	 * @param progressMonitor Progress monitor
	 * @return
	 */
	protected Element createElement(
			T element,
			boolean parallel,
			Function<T, CompletionStage<Element>> elementProvider, 
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {
		
		// TODO - progress steps.
		Optional<AnnotatedElementRecord> match = (parallel ? annotatedElementRecords.parallelStream() : annotatedElementRecords.stream())
			.filter(aer -> aer.test(element) && aer.getAnnotatedElement() instanceof Method && matchFactoryMethod(element, (Method) aer.getAnnotatedElement()))
			.sorted((a, b) -> compareProcessorMethods((Method) a.getAnnotatedElement(), (Method) b.getAnnotatedElement()))
			.findFirst();
		
		if (match.isEmpty()) {
			return null;			
		}
		
		AnnotatedElementRecord matchedRecord = match.get();
		return (Element) matchedRecord.invoke(element, parallel, elementProvider, stageConsumer, progressMonitor);
	}
	
	protected boolean matchFactoryMethod(T element, Method method) {
		if (Modifier.isAbstract(method.getModifiers())) {
			return false;
		}
		
		ElementFactory elementFactoryAnnotation = method.getAnnotation(ElementFactory.class);
		if (elementFactoryAnnotation == null) {
			return false;
		}
		
		if (!elementFactoryAnnotation.type().isInstance(element)) {
			return false;
		}
		
		return matchPredicate(element, elementFactoryAnnotation.value());
	}
	
	protected int compareProcessorMethods(Method a, Method b) {
		int priorityCmp = b.getAnnotation(ElementFactory.class).priority() - a.getAnnotation(ElementFactory.class).priority();
		if (priorityCmp != 0) {
			return priorityCmp;
		}
		
		Class<?> aType = a.getAnnotation(ElementFactory.class).type();
		Class<?> bType = b.getAnnotation(ElementFactory.class).type();
		if (!Objects.equals(aType, bType)) {
			if (aType.isAssignableFrom(bType)) {
				// b is more specific
				return 1;
			}
			if (bType.isAssignableFrom(aType)) {
				// a is more specific
				return -1;
			}
		}
		
		// Method in a sub-class is more specific
		Class<?> adc = a.getDeclaringClass();
		Class<?> bdc = b.getDeclaringClass();
		if (adc.isAssignableFrom(bdc)) {
			return adc == bdc ? 0 : 1;
		} 
		
		if (bdc.isAssignableFrom(adc)) {
			return -1;
		}		
		
		return a.getName().compareTo(b.getName());
	}	
				
}
