package org.nasdanika.graph;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nasdanika.common.NasdanikaException;
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
	protected void join(List<CompletableFuture<?>> stages) {
		stages
			.stream()
			.map(CompletionStage::toCompletableFuture)
			.map(CompletableFuture::join);
	}
	
	/**
	 * Handles exceptionally completed stages
	 * @param throwed
	 */
	protected void onExceptionalCompletions(List<Throwable> throwed) {
		if (!throwed.isEmpty()) {
			NasdanikaException ne = new NasdanikaException("Theres's been errors during processor creation");
			for (Throwable th: throwed) {
				ne.addSuppressed(th);
			}
			throw ne;
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
		Queue<ElementRecord<T>> constructionQueue = new ConcurrentLinkedQueue<>();
		Map<T,CompletableFuture<Element>> registry = Collections.synchronizedMap(new LinkedHashMap<>());		
		List<CompletionStage<?>> stages = Collections.synchronizedList(new ArrayList<>());
		
		Function<T, CompletionStage<Element>> elementProvider = new Function<T,CompletionStage<Element>>() {

			@Override
			public CompletionStage<Element> apply(T element) {
				return registry.computeIfAbsent(element, e -> {
					if (parallel) {
						return CompletableFuture.supplyAsync(() -> createElement(e, parallel, this, stages::add, progressMonitor));						
					}
					
					// Adding a work item to the queue
					CompletableFuture<Element> cf = new CompletableFuture<Element>();
					constructionQueue.add(new ElementRecord<T>(e,cf));
					return cf;
				});
			}
			
		};
		
		Stream<T> elementsStream = parallel ? elements.parallelStream() : elements.stream();
		elementsStream.forEach(e -> {
			// Creating elements
			Element element = createElement(e, parallel, elementProvider, stages::add, progressMonitor);
			registry.computeIfAbsent(e, el -> new CompletableFuture<>()).complete(element);			
		});
		
		// Processing the queue
		ElementRecord<T> workItem;
		while ((workItem = constructionQueue.poll()) != null) {
			if (!workItem.elementCompletableFuture().isDone()) { 
				if (parallel) {
					T workItemElement = workItem.element(); 
					stages.add(CompletableFuture.supplyAsync(() -> createElement(workItemElement, parallel, elementProvider, stages::add, progressMonitor)).thenAccept(workItem.elementCompletableFuture::complete));
				} else {
					Element element = createElement(workItem.element(), parallel, elementProvider, stages::add, progressMonitor);
					workItem.elementCompletableFuture.complete(element);
				}
			}
		}								
		
		join(stages.stream().map(CompletionStage::toCompletableFuture).filter(cf -> !cf.isDone()).collect(Collectors.toList()));

		// Post-processing the queue in this thread in case some stages created new work items. Joining the stages every time to drain
		while ((workItem = constructionQueue.poll()) != null) {
			if (!workItem.elementCompletableFuture().isDone()) { 
				Element element = createElement(workItem.element(), parallel, elementProvider, stages::add, progressMonitor);
				workItem.elementCompletableFuture.complete(element);
				join(stages.stream().map(CompletionStage::toCompletableFuture).filter(cf -> !cf.isDone()).collect(Collectors.toList()));
			}
		}								
		
		// Collecting exceptions
		List<Throwable> throwed = new ArrayList<>();		
		stages
			.stream()
			.map(CompletionStage::toCompletableFuture)
			.filter(CompletableFuture::isCompletedExceptionally)
			.forEach(cf -> cf.handle((r,e) -> {
				if (e == null) {
					throw new IllegalArgumentException("Error shall not be null");
				}
				throwed.add(e);
				return null;
			}));
		
		onExceptionalCompletions(throwed);		

		Map<T, Element> ret = new LinkedHashMap<>();
		registry.forEach((e, cf) -> ret.put(e, cf.join()));
		return ret;		
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
		
		return a.getName().compareTo(b.getName());
	}	
				
}
