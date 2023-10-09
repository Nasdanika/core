package org.nasdanika.common;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Creates objects from other object.
 * Uses reflection to match factory methods and delegate to them.
 * Can create target objects sequentially or in parallel. 
 * Objects yet to be created can be referenced by objects being created using {@link CompletionStage}s.
 * The transformation process may create objects from referenced objects and as such the result size may be smaller (if no object created) or larger (related objects created) than the source collection. 
 * One application is creation and wiring of graphs. 
 * @author Pavel
 * @param <S> Source object type
 * @param <T> Target object type
 *
 */
public class Transformer<S,T> extends Reflector {
	
	/**
	 * Annotation for a method creating a {@link Element} from an object
	 * TODO - method parameters
	 * @author Pavel
	 *
	 */
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface Factory {
		
		/**
		 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
		 * which is evaluated in the context of an element. 
		 * @return
		 */
		String value() default "";

		/**
		 * Matching by object type.
		 * @return
		 */
		Class<?> type() default Object.class; 
		
		/**
		 * Factory priority
		 * @return
		 */
		int priority() default 0;

	}

	protected List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();
	
	public Transformer(Object... factories) {
		for (Object factory: factories) {
			getAnnotatedElementRecords(factory).forEach(annotatedElementRecords::add);
		}
	}
	
	// For troubleshooting
	private record StageRecord(/*StackTraceElement[] stack, */ CompletionStage<?> stage) {
		
		static StageRecord create(CompletionStage<?> stage) {
			return new StageRecord(/* new RuntimeException().getStackTrace(), */ stage);
		}
		
	};
	
	/**
	 * Joins not yet completed stages. 
	 * @param stages
	 */
	protected void join(Queue<StageRecord> stageRecords) {		
		StageRecord stageRecord;
		while ((stageRecord = stageRecords.poll()) != null) {
			stageRecord.stage().toCompletableFuture().join();
		}
	}
	
	/**
	 * Creates target objects from source objects and returns a map of source object to target objects created from them and their related elements.
	 * @param source
	 * @param parallel If false all processing is performed in the caller thread. Otherwise, processing might be performed in multiple threads.
	 * @param progressMonitor
	 * @return
	 */
	public Map<S, T> transform(Collection<? extends S> source, boolean parallel, ProgressMonitor progressMonitor) {
		Map<S,CompletableFuture<T>> registry = new ConcurrentHashMap<>(); // Registry is used to prevent duplication of creation				
		CompletableFuture<Map<S,T>> registryCompletableFuture = new CompletableFuture<>(); // Allows to inject registry into created elements.
		Queue<StageRecord> registryStages = new ConcurrentLinkedQueue<>();
		Consumer<BiConsumer<Map<S,T>,ProgressMonitor>> registryCallback = biConsumer -> {
			CompletableFuture<Void> rStage = registryCompletableFuture.thenAccept(r -> biConsumer.accept(r, progressMonitor));
			registryStages.add(StageRecord.create(rStage));
		};
		Queue<StageRecord> stages = new ConcurrentLinkedQueue<>();
		Consumer<CompletionStage<?>> stageConsumer = stage -> {
			if (stage != null) {
				stages.add(StageRecord.create(stage));
			}
		};
		AtomicBoolean registryFrozen = new AtomicBoolean(); // Registry is frozen before the registryCompletableFuture is completed. Registry provider must not be used in registryCompletionStage then handlers
		
		// Parallel/asynchronous processing
		if (parallel) {
			
			// Constructs a new graph element asynchronously
			Function<S, CompletableFuture<T>> constructor = new Function<S,CompletableFuture<T>>() {

				@Override
				public CompletableFuture<T> apply(S element) {
					// A closure to supply a new element. 
					Supplier<T> elementSupplier = () -> createElement(
							element, 
							parallel, 
							(e, c) -> {
								if (registryFrozen.get()) {
									throw new IllegalStateException("Registry is frozen, use registry to get elements");
								}
							
								stageConsumer.accept(registry.computeIfAbsent(e, this).thenAccept(t -> c.accept(t, progressMonitor))); // TODO - split progress monitor
							},
							registryCallback,
							progressMonitor);
					
						return CompletableFuture.supplyAsync(elementSupplier);
				}
				
			};
			
			source
				.parallelStream()
				.map(e -> registry.computeIfAbsent(e, constructor))
				.forEach(stageConsumer);			
		} else {
			// Sequential processing
			Queue<Runnable> constructionQueue = new ConcurrentLinkedQueue<>(); // 
			
			// Constructs a new graph element 
			Function<S, CompletableFuture<T>> constructor = new Function<S,CompletableFuture<T>>() {
	
				@Override
				public CompletableFuture<T> apply(S element) {
					// A closure to supply a new element. 
					Supplier<T> elementSupplier = () -> createElement(
							element, 
							parallel, 
							(e,c) -> {
								if (registryFrozen.get()) {
									throw new IllegalStateException("Registry is frozen, use registry to get elements");
								}
								stageConsumer.accept((CompletionStage<?>) registry.computeIfAbsent(e, this).thenAccept(t -> c.accept(t, progressMonitor))); // TODO - split progress monitor
							},
							registryCallback,
							progressMonitor);
					
					CompletableFuture<T> ret = new CompletableFuture<>();
					constructionQueue.add(() -> { // A runnable which completes the future
						ret.complete(elementSupplier.get());
					}); 
					return ret;
				}
				
			};
			
			source
					.stream()
					.map(e -> registry.computeIfAbsent(e, constructor))
					.forEach(stageConsumer);
			
			// Processing the queue. Executing a work item may create more work items. Processing until there are not more items
			Runnable workItem;
			while ((workItem = constructionQueue.poll()) != null) {
				workItem.run();
			}
		}
		
		join(stages);
		
		Map<S, T> reg = registry
				.entrySet()
				.stream()
				.map(re -> {
					T element = re.getValue().join();
					return element == null ? null : Map.entry(re.getKey(), element);
				})
				.filter(Objects::nonNull)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		registryFrozen.set(true);
		registryCompletableFuture.complete(reg);
		join(stages);
		join(registryStages);
		return reg;
	}

	/**
	 * 
	 * @param element
	 * @param parallel
	 * @param elementProvider Accepts a source element and a consumer of the target element and a progress monitor, which is invoked when the target element is created from the source element. 
	 * The function may optionally return a completion stage which will be used to report exceptional completions.  
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T createElement(
			S element,
			boolean parallel,
			BiConsumer<S, BiConsumer<T, ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<S,T>,ProgressMonitor>> registry,
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
		return (T) matchedRecord.invoke(element, parallel, elementProvider, registry, progressMonitor);
	}
	
	protected boolean matchFactoryMethod(S element, Method method) {
		if (Modifier.isAbstract(method.getModifiers())) {
			return false;
		}
		
		Factory elementFactoryAnnotation = method.getAnnotation(Factory.class);
		if (elementFactoryAnnotation == null) {
			return false;
		}
		
		Class<?> type = elementFactoryAnnotation.type();
		if (type == Object.class) {
			type = method.getParameterTypes()[0];
		}
		if (!type.isInstance(element)) {
			return false;
		}
		
		return matchPredicate(element, elementFactoryAnnotation.value());
	}
	
	protected int compareProcessorMethods(Method a, Method b) {
		int priorityCmp = b.getAnnotation(Factory.class).priority() - a.getAnnotation(Factory.class).priority();
		if (priorityCmp != 0) {
			return priorityCmp;
		}
		
		Class<?> aType = a.getAnnotation(Factory.class).type();
		Class<?> bType = b.getAnnotation(Factory.class).type();
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
