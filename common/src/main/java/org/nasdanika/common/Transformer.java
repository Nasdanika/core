package org.nasdanika.common;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;
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
import java.util.stream.Stream;

/**
 * Creates objects from other objects.
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
	 * Annotation for a method creating a target object from the source object
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
	
	/**
	 * Annotation for a method wiring target object to other objects. 
	 * The method takes source object, target object (if not null), registry, pass number, and progress monitor.
	 * It may return boolean value. If returned value is <code>false</code> then it means that wiring is not possible yet and
	 * another pass through this phase wire methods is required. 
	 * Passes stop when there are no more wires to process. Registry map can be modified, e.g. by replacing values. 
	 * If the registry map is modified wires are re-matched. 
	 * An exception is thrown if the number of passes exceeds the value returned by <code>getMaxPasses()</code> method.
	 * @author Pavel
	 *
	 */
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface Wire {
		
		/**
		 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
		 * which is evaluated in the context of the source element with target element as target variable (might be null). 
		 * @return
		 */
		String value() default "";

		/**
		 * Matching by source type.
		 * @return
		 */
		Class<?> sourceType() default Object.class; 

		/**
		 * Matching by target type. Use {@link Void} class to match <code>null</code> values.
		 * @return
		 */
		Class<?> targetType() default Object.class; 
		
		/**
		 * Wiring is done in phases.
		 * @return
		 */
		int phase() default 0;
		
		/**
		 * Wire priority within the phase
		 * @return
		 */
		int priority() default 0;
		
	}

	protected List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();
	
	public Transformer(Object... factories) {
		for (Object factory: factories) {
			getAnnotatedElementRecords(factory, Collections.singletonList(factory)).forEach(annotatedElementRecords::add);
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
	 * Helper class for invoking wire methods
	 */
	private class WireEntry implements Comparable<WireEntry> {
		
		private Map<S, T> entryReg;
		private ProgressMonitor progressMonitor;

		WireEntry(
				S source, 
				T target, 
				AnnotatedElementRecord wire, 
				Map<S,T> entryReg,
				ProgressMonitor progressMonitor) {
			super();
			this.source = source;
			this.target = target;
			this.wire = wire;
			this.entryReg = entryReg;
			this.progressMonitor = progressMonitor;
			this.priority = - wire.getAnnotation(Wire.class).priority();
		}
		
		S source;
		T target;
		AnnotatedElementRecord wire;	
		int pass;
		int priority;
		
		boolean invoke() {
			Object result = target == null ? wire.invoke(source, entryReg, pass, progressMonitor) : wire.invoke(source, target, entryReg, pass, progressMonitor);
			++pass;
			++priority;
			if (pass > getMaxPasses()) {
				throw new IllegalStateException("Maximum number of passes has been exceeded for " + wire.getAnnotatedElement());
			}
			return !Boolean.FALSE.equals(result);
		}

		@Override
		public int compareTo(WireEntry o) {
			int cmp = priority - o.priority; 
			return cmp == 0 ? hashCode() - o.hashCode() : cmp;
		}
		
	}; 
	
	/**
	 * Maximum number of passes per entry. If this number is exceeded then an exception is thrown.
	 * @return
	 */
	protected int getMaxPasses() {
		return 1000;
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
		
		Map<S, T> reg = new LinkedHashMap<>();		
		registry
				.entrySet()
				.stream()
				.map(re -> new AbstractMap.SimpleEntry<S, T>(re.getKey(), re.getValue().join()))
				.forEach(e -> reg.put(e.getKey(), e.getValue()));
		
		registryFrozen.set(true);
		registryCompletableFuture.complete(reg);
		join(stages);
		join(registryStages);
						
		// Wiring
		Map<S, T> wireReg = new LinkedHashMap<>(reg); // Modifiable registry - wires may replace mappings
		
		Map<Integer, List<AnnotatedElementRecord>> wires = annotatedElementRecords
			.stream()
			.filter(ar -> ar.getAnnotation(Wire.class) != null && ar.getAnnotatedElement() instanceof Method)
			.sorted((a,b) -> b.getAnnotation(Wire.class).priority() - a.getAnnotation(Wire.class).priority())
			.collect(Collectors.groupingBy(ar -> ar.getAnnotation(Wire.class).phase()));
		
		for (Map.Entry<Integer, List<AnnotatedElementRecord>> phaseEntry: new TreeMap<>(wires).entrySet()) {						
			
			// Wire entries to invoke
			Queue<WireEntry> wireEntries = new PriorityQueue<>();
			Map<S,T> phaseRegistry = new ChangeTrackingMap<S, T>(wireReg) {
				
				@Override
				protected void onPut(S key, T oldValue, T newValue) {
					onRemove(key, oldValue);
					for (AnnotatedElementRecord ar: phaseEntry.getValue()) {
						if (matchWireMethod(key, newValue, (Method) ar.getAnnotatedElement())) {
							wireEntries.add(new WireEntry(
									key, 
									newValue, 
									ar,
									this,
									progressMonitor));
						}
					}						
				}
				
				@Override
				protected void onRemove(S key, T value) {
					wireEntries.removeIf(we -> Objects.equals(we.source, key));					
				}
				
			};
			
			// Populating with the initial permutations
			for (Map.Entry<S, T> wireRegEntry: wireReg.entrySet()) {
				for (AnnotatedElementRecord ar: phaseEntry.getValue()) {
					S key = wireRegEntry.getKey();
					T value = wireRegEntry.getValue();
					if (matchWireMethod(key, value, (Method) ar.getAnnotatedElement())) {
						wireEntries.add(new WireEntry(
								key, 
								value, 
								ar,
								phaseRegistry,
								progressMonitor));
					}
				}
			}
			
			// Invoking wire entries while there are entries and there are changes
			while (!wireEntries.isEmpty()) {
				WireEntry we = wireEntries.poll();
				if (!we.invoke()) {
					wireEntries.add(we); // Adding back - returned false.
				}
			}
		}		
		
		return wireReg;
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
		Stream<AnnotatedElementRecord> matchStream = (parallel ? annotatedElementRecords.parallelStream() : annotatedElementRecords.stream())
					.filter(aer -> aer.test(element) && aer.getAnnotatedElement() instanceof Method && matchFactoryMethod(element, (Method) aer.getAnnotatedElement()))
					.sorted((a, b) -> compareFactoryMethods((Method) a.getAnnotatedElement(), (Method) b.getAnnotatedElement()));
		
		if (isMultiTransformer()) {
			return (T) matchStream.map(mr -> mr.invoke(element, parallel, elementProvider, registry, progressMonitor)).toList();
		}
		
		Optional<AnnotatedElementRecord> match = matchStream.findFirst();		
		if (match.isEmpty()) {
			return null;			
		}		
		AnnotatedElementRecord matchedRecord = match.get();
		return (T) matchedRecord.invoke(element, parallel, elementProvider, registry, progressMonitor);
	}
	
	/**
	 * If this method returns true then all matched create methods are invoked and results are collected into a list. 
	 * This can be used, for example, for inspection/diagnostic where multiple methods diagnose different aspects of the source object.
	 * When true, <code>T</code> must be assignable from List.
	 * @return
	 */
	protected boolean isMultiTransformer() {
		return false;
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
	
	protected int compareFactoryMethods(Method a, Method b) {
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
	
	protected boolean matchWireMethod(S source, T target, Method method) {
		if (Modifier.isAbstract(method.getModifiers())) {
			return false;
		}
		
		Wire elementWireAnnotation = method.getAnnotation(Wire.class);
		if (elementWireAnnotation == null) {
			return false;
		}
		
		Class<?>[] parameterTypes = method.getParameterTypes();

		Class<?> sourceType = elementWireAnnotation.sourceType();
		if (sourceType == Object.class) {
			sourceType = parameterTypes[0];
		}
		if (!sourceType.isInstance(source)) {
			return false;
		}

		Class<?> targetType = elementWireAnnotation.targetType();
		if (target == null) {
			if (targetType != Void.class) {
				return false;
			}
		} else {
			if (targetType == Object.class) {
				targetType = parameterTypes[1];
			}
			if (!targetType.isInstance(target)) {
				return false;
			}
		}
		
		return evaluatePredicate(source, elementWireAnnotation.value(), target == null ? null : Collections.singletonMap("target", target));
	}	
				
}
