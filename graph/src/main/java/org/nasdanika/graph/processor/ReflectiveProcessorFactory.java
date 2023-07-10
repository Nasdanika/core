package org.nasdanika.graph.processor;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.common.Util;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

/**
 * Creates processor and wires hanlders and endpoints using annotations. 
 * @author Pavel
 *
 * @param <P>
 * @param <T>
 * @param <R>
 * @param <U>
 * @param <S>
 */
public abstract class ReflectiveProcessorFactory<P, H, E, R> extends Reflector implements ProcessorFactory<P, H, E, R> {
	
	private List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();

	protected ReflectiveProcessorFactory(Object... targets) {
		for (Object target: targets) {
			getAnnotatedElementRecords(target).forEach(annotatedElementRecords::add);
		}
	}
	
	public R createProcessors(Element element, boolean parallel, ProgressMonitor progressMonitor, Object... registryTargets) {
		R registry = ProcessorFactory.super.createProcessors(progressMonitor, parallel, element);
		for (Object registryTarget: registryTargets) {
			// TODO  - progress moinitor			
			wireRegistryEntry(registryTarget, parallel).accept(registry);
			wireRegistry(registryTarget, parallel).accept(registry);
		}
		return registry;
	}
	
	private <K,V> Map<K,V> synchroCopy(Map<K,V> map) {
		synchronized (map) {
			return Collections.synchronizedMap(new LinkedHashMap<>(map));
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CompletionStage<P> createProcessor(ProcessorConfig<P,R> config, boolean parallel, ProgressMonitor progressMonitor) {
		// TODO - progress steps.
		Optional<AnnotatedElementRecord> match = (parallel ? annotatedElementRecords.parallelStream() : annotatedElementRecords.stream())
			.filter(r -> r.test(config.getElement()) && r.getAnnotatedElement() instanceof Method && matchFactoryMethod(config, (Method) r.getAnnotatedElement()))
			.sorted((a, b) -> compareProcessorMethods((Method) a.getAnnotatedElement(), (Method) b.getAnnotatedElement()))
			.findFirst();
		
		if (match.isEmpty()) {
			return ProcessorFactory.super.createProcessor(config, parallel, progressMonitor);			
		}
		
		AnnotatedElementRecord matchedRecord = match.get();
		Method method = (Method) matchedRecord.getAnnotatedElement();
		Object processor;
		if (method.getParameterCount() == 0) {
			processor = matchedRecord.invoke();
		} else if (method.getParameterCount() == 1) {
			processor = matchedRecord.invoke(config);
		} else {
			processor = matchedRecord.invoke(config, progressMonitor);			
		}
		if (processor == null) {
			return ProcessorFactory.super.createProcessor(config, parallel, progressMonitor);			
		}
		Processor elementProcessorAnnotation = method.getAnnotation(Processor.class);
		
		boolean hideWired = elementProcessorAnnotation.hideWired();
		Map<Element, ProcessorInfo<P,R>> unwiredChildProcessorsInfo = wireChildProcessor(processor, config.getChildProcessorsInfo(), parallel);
		wireChildProcessors(processor, hideWired ? unwiredChildProcessorsInfo : config.getChildProcessorsInfo(), parallel);
		
		List<CompletionStage<?>> allStages = new ArrayList<>();

		Consumer<ProcessorInfo<P,R>> pc = wireParentProcessor(processor, parallel);
		if (pc != null) {
			allStages.add(config.getParentProcessorInfo().thenAccept(pc));
		}
		wireProcessorElement(processor, config.getElement(), parallel);
		
		allStages.add(config.getRegistry().thenAccept(wireRegistryEntry(processor, parallel)));
		
		Consumer<R> rc = wireRegistry(processor, parallel);
		if (rc != null) {
			allStages.add(config.getRegistry().thenAccept(rc));
		}
		
		if (config instanceof NodeProcessorConfig) {
			NodeProcessorConfig<P, H, E, R> nodeProcessorConfig = (NodeProcessorConfig<P, H, E, R>) config;

			Map<Connection, CompletionStage<E>> incomingEndpoints = synchroCopy(nodeProcessorConfig.getIncomingEndpoints()); // Making a copy to remove wired on completion 
			wireIncomingEndpoint(processor, incomingEndpoints, parallel, progressMonitor).forEach(r -> {
				if (r.consume()) {
					incomingEndpoints.remove(r.connection());
				}
				allStages.add(r.result());
			});
			wireIncomingEndpoints(processor, incomingEndpoints, parallel);
			
			Map<Connection, Consumer<H>> incomingHandlerConsumers = nodeProcessorConfig.getIncomingHandlerConsumers();
			Map<Connection, Consumer<H>> unwiredIncomingHandlerConsumers;
			synchronized (incomingHandlerConsumers) {
				unwiredIncomingHandlerConsumers = wireIncomingHandler(processor, incomingHandlerConsumers, parallel);
				wireIncomingHandlerConsumers(processor, hideWired ? unwiredIncomingHandlerConsumers : incomingHandlerConsumers, parallel);
			}
			
			Map<Connection, CompletionStage<E>> outgoingEndpoints = synchroCopy(nodeProcessorConfig.getOutgoingEndpoints());
			wireOutgoingEndpoint(processor, outgoingEndpoints, parallel, progressMonitor).forEach(r -> {
				if (r.consume()) {
					outgoingEndpoints.remove(r.connection());
				}
				allStages.add(r.result());
			});
			wireOutgoingEndpoints(processor, outgoingEndpoints, parallel);
			
			Map<Connection, Consumer<H>> outgoingHandlerConsumers = nodeProcessorConfig.getOutgoingHandlerConsumers();
			Map<Connection, Consumer<H>> unwiredOutgoingHandlerConsumers;
			synchronized (outgoingHandlerConsumers) {
				unwiredOutgoingHandlerConsumers = wireOutgoingHandler(processor, outgoingHandlerConsumers, parallel);
				wireOutgoingHandlerConsumers(processor, hideWired ? unwiredOutgoingHandlerConsumers : outgoingHandlerConsumers, parallel);
			}
		} else if (config instanceof ConnectionProcessorConfig) {
			ConnectionProcessorConfig<P, H, E, R> connectionProcessorConfig = (ConnectionProcessorConfig<P, H, E, R>) config;
			allStages.add(wireSourceEndpoint(processor, connectionProcessorConfig.getSourceEndpoint(), parallel));
			wireSourceHandler(processor, connectionProcessorConfig, parallel);
			allStages.add(wireTargetEndpoint(processor, connectionProcessorConfig.getTargetEndpoint(), parallel));
			wireTargetHandler(processor, connectionProcessorConfig, parallel);
		}
		
		CompletableFuture<?>[] allStagesArray = allStages.stream().map(CompletionStage::toCompletableFuture).collect(Collectors.toList()).toArray(new CompletableFuture[0]);
		return CompletableFuture.allOf(allStagesArray).handle((r, e) -> {
			if (e == null) {
				return (P) processor;
			}
			NasdanikaException ne = new NasdanikaException("Theres's been errors during processor creation");
			for (CompletableFuture<?> cf: allStagesArray) {
				if (cf.isCompletedExceptionally()) {
					cf.whenComplete((rs, ex) -> ne.addSuppressed(ex));
				}
			}
			throw ne;
		});
	}

	protected int compareProcessorMethods(Method a, Method b) {
		int priorityCmp = b.getAnnotation(Processor.class).priority() - a.getAnnotation(Processor.class).priority();
		if (priorityCmp != 0) {
			return priorityCmp;
		}
		
		Class<? extends Element> aType = a.getAnnotation(Processor.class).type();
		Class<? extends Element> bType = b.getAnnotation(Processor.class).type();
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
		
		// Taking config is more specific 
		int paramCountCmp = b.getParameterCount() - a.getParameterCount();
		if (paramCountCmp != 0) {
			return paramCountCmp;
		}
		
		return a.getName().compareTo(b.getName());
	}
		
	protected Consumer<R> wireRegistryEntry(Object processor, boolean parallel) {
		List<AccessibleObject> registryEntrySetters = getFieldsAndMethods(processor.getClass(), parallel)
			.filter(ae -> ae.getAnnotation(RegistryEntry.class) != null)
			.filter(ae -> mustSet(ae, null, "Fields/methods annotated with RegistryEntry must have (parameter) type assignable from the processor type or ProcessorConfig if config is set to true: " + ae))
			.collect(Collectors.toList());
		
		return registry -> {
			registryEntrySetters.forEach(setter -> {
				for (Entry<Element, ProcessorInfo<P,R>> re: registryEntries(registry)) {
					RegistryEntry registryEntryAnnotation = setter.getAnnotation(RegistryEntry.class);
					if (matchPredicate(re.getKey(), registryEntryAnnotation.value())) {
						set(processor, setter, registryEntryAnnotation.config() ? re.getValue().getConfig() : re.getValue().getProcessor());
					};						
				}				
			});						
		};
	}
	
	/**
	 * Iterable of registry entries for wiring using {@link RegistryEntry} annotation
	 * @param registry
	 * @return
	 */
	protected abstract Iterable<Entry<Element, ProcessorInfo<P,R>>> registryEntries(R registry);

	protected Consumer<R> wireRegistry(Object processor, boolean parallel) {
		List<AccessibleObject> registrySetters = getFieldsAndMethods(processor.getClass(), parallel)
				.filter(ae -> ae.getAnnotation(Registry.class) != null)
				.filter(ae -> mustSet(ae, Map.class, "Fields/methods annotated with Registry must have (parameter) type assignable from Map: " + ae))
				.collect(Collectors.toList());
			
		return registry -> registrySetters.forEach(setter -> set(processor, setter, registry));
	}

	protected void wireProcessorElement(Object processor, Element element, boolean parallel) {
		getFieldsAndMethods(processor.getClass(), parallel)
				.filter(ae -> ae.getAnnotation(ProcessorElement.class) != null)
				.filter(ae -> mustSet(ae, element.getClass(), "Methods annotated with ProcessorElement must have one parameter compatible with the processor element type (" + element.getClass() + "): " + ae))
				.forEach(setter -> {
					set(processor, setter, element);
				});						
	}

	protected Consumer<ProcessorInfo<P,R>> wireParentProcessor(Object processor, boolean parallel) {
		List<AccessibleObject> parentProcessorSetters = getFieldsAndMethods(processor.getClass(), parallel)
				.filter(ae -> ae.getAnnotation(ParentProcessor.class) != null)
				.filter(ae -> mustSet(ae, null, "Fields/methods annotated with ParentProcessor must have (parameter) type assignable from the processor type or ProcessorConfig if value is set to true: " + ae))
				.collect(Collectors.toList());
			
			return createParentProcessorInfoConsumer(processor, parentProcessorSetters);
	}
	
	protected Consumer<ProcessorInfo<P,R>> createParentProcessorInfoConsumer(Object processor, List<AccessibleObject> parentProcessorSetters) {
		return parentProcessorInfo -> {
			if (parentProcessorInfo != null) {
				for (AccessibleObject setter: parentProcessorSetters) {
					ParentProcessor parentProcessorAnnotation = setter.getAnnotation(ParentProcessor.class);
					set(processor, setter, parentProcessorAnnotation.value() ? parentProcessorInfo.getConfig() : parentProcessorInfo.getProcessor());			
				}
			}
		};
	}
	
	private Map<Element, ProcessorInfo<P,R>> wireChildProcessor(Object processor, Map<Element, ProcessorInfo<P,R>> childProcessorsInfo, boolean parallel) {		
		Map<Element, ProcessorInfo<P,R>> ret = new LinkedHashMap<>(childProcessorsInfo); 
		
		getFieldsAndMethods(processor.getClass(), parallel)
			.filter(ae -> ae.getAnnotation(ChildProcessor.class) != null)
			.filter(ae -> mustSet(ae, null, "Fields/methods annotated with ChildProcessor must have (parameter) type assignable from the processor type or ProcessorConfig if config is set to true: " + ae))
			.forEach(setter -> {
				for (Entry<Element, ProcessorInfo<P,R>> ce: childProcessorsInfo.entrySet()) {
					ChildProcessor childProcessorAnnotation = setter.getAnnotation(ChildProcessor.class);
					if (matchPredicate(ce.getKey(), childProcessorAnnotation.value())) {
						set(processor, setter, childProcessorAnnotation.config() ? ce.getValue().getConfig() : ce.getValue().getProcessor());
						ret.remove(ce.getKey());
					};						
				}				
				
			});
		return ret;
	}	

	protected void wireChildProcessors(Object processor, Map<Element, ProcessorInfo<P,R>> childProcessorsInfo, boolean parallel) {		
		getFieldsAndMethods(processor.getClass(), parallel)
				.filter(ae -> ae.getAnnotation(ChildProcessors.class) != null)
				.filter(ae -> mustSet(ae, Map.class, "Fields/methods annotated with ChildProcessors must have (parameter) type assignable from Map: " + ae))
				.forEach(setter -> set(processor, setter, childProcessorsInfo));
	}
	
	/**
	 * Matches processor field or method and incoming connection.
	 * @return
	 */
	protected boolean matchIncomingHandler(AnnotatedElement handlerMember, Connection incomingConnection) {
		IncomingHandler incomingHandlerAnnotation = handlerMember.getAnnotation(IncomingHandler.class);
		if (incomingHandlerAnnotation == null) {
			return false;
		}
		
		if (handlerMember instanceof Method) {
			Method handlerMethod = (Method) handlerMember;
			int pc = handlerMethod.getParameterCount();
			if (pc > 1) {
				throw new NasdanikaException("A method annotated with IncomingHandler shall have zero or one parameter: " + handlerMethod);
			}
			if (pc == 1 && !handlerMethod.getParameterTypes()[0].isInstance(incomingConnection)) {
				return false;				
			}
		}
				
		return matchPredicate(incomingConnection, incomingHandlerAnnotation.value());
	}
	
	// Node wiring
	@SuppressWarnings("unchecked")
	protected Map<Connection, Consumer<H>> wireIncomingHandler(Object processor, Map<Connection, Consumer<H>> incomingHandlerConsumers, boolean parallel) {		
		Map<Connection, Consumer<H>> ret = new LinkedHashMap<>(incomingHandlerConsumers);

		// Streaming fields and methods and then flat mapping them to all permutations with incoming handler consumers.
		// then filtering using matchIncomingHandler, sorting by priority, for all matching - wiring and removing from ret.
		getFieldsAndMethods(processor.getClass(), parallel)
			.filter(m -> !Modifier.isAbstract(((Member) m).getModifiers()))
			.flatMap(ae -> incomingHandlerConsumers.entrySet().stream().map(ihce -> new ConnectionMatchRecord<Consumer<H>>(
					ae, 
					ihce.getKey(), 
					ihce.getValue(), 
					ao -> ao.getAnnotation(IncomingHandler.class).priority(), 
					ao -> ao.getAnnotation(IncomingHandler.class).value())))
			.filter(mr -> matchIncomingHandler(mr.accessibleObject(), mr.connection()))
			.sorted()
			.forEach(mr -> {
				AccessibleObject handlerMember = mr.accessibleObject();
				Connection incomingConnection = mr.connection();
				if (ret.containsKey(incomingConnection)) { // Wiring once
					if (handlerMember instanceof Field) {					
						mr.value().accept((H) getFieldValue(processor, (Field) handlerMember));
					} else {
						Method handlerSupplierMethod = (Method) handlerMember;
						Object incomingHandler = handlerSupplierMethod.getParameterCount() == 0 ? invokeMethod(processor, handlerSupplierMethod) : invokeMethod(processor, handlerSupplierMethod, incomingConnection);
						mr.value().accept((H) incomingHandler);
					}
					ret.remove(incomingConnection);
				}
			});
				
		return Collections.unmodifiableMap(ret);
	}	
	
	protected void wireIncomingHandlerConsumers(Object processor, Map<Connection, Consumer<H>> incomingHandlerConsumers, boolean parallel) {				
		getFieldsAndMethods(processor.getClass(), parallel)
				.filter(ae -> ae.getAnnotation(IncomingHandlerConsumers.class) != null)
				.filter(ae -> mustSet(ae, Map.class, "Fields/methods annotated with IncomingHandlersConsumers must have (parameter) type assignable from Map: " + ae))
				.forEach(setter -> set(processor, setter, incomingHandlerConsumers));
	}
	
	/**
	 * Matches processor field or method and incoming connection.
	 * @return
	 */
	protected boolean matchIncomingEndpoint(AnnotatedElement endpointMember, Connection incomingConnection) {
		IncomingEndpoint incomingEndpointAnnotation = endpointMember.getAnnotation(IncomingEndpoint.class);
		if (incomingEndpointAnnotation == null) {
			return false;
		}
		
		if (endpointMember instanceof Method) {
			Method endpointMethod = (Method) endpointMember;
			int pc = endpointMethod.getParameterCount();
			if (pc == 0 || pc > 3) {
				throw new NasdanikaException("A method annotated with IncomingEndpoint shall have 1 - 3 parameters: " + endpointMethod);
			}
			if (pc > 1 && !endpointMethod.getParameterTypes()[0].isInstance(incomingConnection)) {
				return false;				
			}
		}
				
		return matchPredicate(incomingConnection, incomingEndpointAnnotation.value());
	}	
	
	protected record ConnectionMatchRecord<T>(
			AccessibleObject accessibleObject, 
			Connection connection, 
			T value, 
			Function<AccessibleObject, Integer> priorityGetter, 
			Function<AccessibleObject, String> selectorGetter) implements Comparable<ConnectionMatchRecord<T>> {

		@Override
		public int compareTo(ConnectionMatchRecord<T> o) {
			AccessibleObject a = accessibleObject();
			AccessibleObject b = o.accessibleObject();
			
			if (priorityGetter != null) {
				Integer pa = priorityGetter.apply(a);
				Integer pb = priorityGetter.apply(b);
				if (!Objects.equals(pa, pb)) {
					return pb - pa;
				}
			}			
			if (a instanceof Member && b instanceof Member) {
				Class<?> adc = ((Member) a).getDeclaringClass();
				Class<?> bdc = ((Member) b).getDeclaringClass();
				if (adc.isAssignableFrom(bdc)) {
					return adc == bdc ? 0 : 1;
				} 
				
				if (bdc.isAssignableFrom(adc)) {
					return -1;
				}
			}
			if (selectorGetter != null) {
				String sa = selectorGetter.apply(a);
				String sb = selectorGetter.apply(b);
				if (sa.length() != sb.length()) {
					return sb.length() - sa.length();
				}
			}
			
			return a.hashCode() - b.hashCode();
		}
		
	}
	
	private record EndpointWireRecord(Connection connection, CompletionStage<Void> result, boolean consume) {};

	/**
	 * @param processor
	 * @param incomingEndpoints
	 * @param progressMonitor
	 * @return Wired incoming endpoints for collection of failures
	 */
	protected List<EndpointWireRecord> wireIncomingEndpoint(
			Object processor, 
			Map<Connection, CompletionStage<E>> incomingEndpoints,
			boolean parallel,
			ProgressMonitor progressMonitor) {
						
		Set<AccessibleObject> wiredFields = Collections.synchronizedSet(new HashSet<>()); // For setting a field once, setter methods may be invoked multiple times.
		Set<Connection> wiredConnections = Collections.synchronizedSet(new HashSet<>()); // For wiring a connection once.
				
		// Streaming fields and methods and then flat mapping them to all permutations with incoming endpoints.
		// then filtering using matchIncomingEndpoint, sorting by priority, wiring all matching and removing from ret.
		return getFieldsAndMethods(processor.getClass(), parallel)
			.filter(m -> !Modifier.isAbstract(((Member) m).getModifiers()))
			.flatMap(ae -> incomingEndpoints.entrySet().stream().map(iee -> new ConnectionMatchRecord<CompletionStage<E>>( 
					ae, 
					iee.getKey(), 
					iee.getValue(), 
					ao -> ao.getAnnotation(IncomingEndpoint.class).priority(), 
					ao -> ao.getAnnotation(IncomingEndpoint.class).value())))
			.filter(mr -> matchIncomingEndpoint(mr.accessibleObject(), mr.connection()))
			.sorted()
			.map(mr -> {
				AccessibleObject endpointMember = mr.accessibleObject();
				Connection incomingConnection = mr.connection();
				if (wiredConnections.add(incomingConnection) && (!(endpointMember instanceof Field) || wiredFields.add(endpointMember))) { // Wiring an endpoint once and setting a field once
					CompletionStage<Void> result = mr.value().thenAccept(incomingEndpoint -> {
						if (endpointMember instanceof Field) {
							setFieldValue(processor, (Field) endpointMember, incomingEndpoint);
						} else {
							Method endpointMethod = (Method) endpointMember;
							switch (endpointMethod.getParameterCount()) {
							case 1:
								invokeMethod(processor, endpointMethod, incomingEndpoint);
								break;
							case 2:
								invokeMethod(processor, endpointMethod, incomingConnection, incomingEndpoint);						
								break;
							case 3:
								invokeMethod(processor, endpointMethod, incomingConnection, incomingEndpoint, progressMonitor);						
								break;								
							default:
								throw new NasdanikaException("Incoming endpoint method shall have 1 to 3 parameters: " + endpointMethod);
							}							
						}
					});
					return new EndpointWireRecord(incomingConnection, result, endpointMember.getAnnotation(IncomingEndpoint.class).consume());
				}
				return null;
			})
			.filter(null)
			.collect(Collectors.toList());				
	}
	
	protected void wireIncomingEndpoints(Object processor, Map<Connection, CompletionStage<E>> incomingEndpoints, boolean parallel) {
		getFieldsAndMethods(processor.getClass(), parallel)
				.filter(ae -> ae.getAnnotation(IncomingEndpoints.class) != null)
				.filter(ae -> mustSet(ae, Map.class, "Fields/methods annotated with IncomingEndpoints must have (parameter) type assignable from Map: " + ae))
				.forEach(setter -> set(processor, setter, incomingEndpoints));
	}
	
	/**
	 * Matches processor field or method and outgoing connection.
	 * @return
	 */
	protected boolean matchOutgoingHandler(AnnotatedElement handlerMember, Connection outgoingConnection) {
		OutgoingHandler outgoingHandlerAnnotation = handlerMember.getAnnotation(OutgoingHandler.class);
		if (outgoingHandlerAnnotation == null) {
			return false;
		}
		
		if (handlerMember instanceof Method) {
			Method handlerMethod = (Method) handlerMember;
			int pc = handlerMethod.getParameterCount();
			if (pc > 1) {
				throw new NasdanikaException("A method annotated with OutgoingHandler shall have zero or one parameter: " + handlerMethod);
			}
			if (pc == 1 && !handlerMethod.getParameterTypes()[0].isInstance(outgoingConnection)) {
				return false;				
			}
		}
				
		return matchPredicate(outgoingConnection, outgoingHandlerAnnotation.value());
	}
	
	@SuppressWarnings("unchecked")
	protected Map<Connection, Consumer<H>> wireOutgoingHandler(Object processor, Map<Connection, Consumer<H>> outgoingHandlerConsumers, boolean parallel) {

		Map<Connection, Consumer<H>> ret = new LinkedHashMap<>(outgoingHandlerConsumers);

		// Streaming fields and methods and then flat mapping them to all permutations with outgoing handler consumers.
		// then filtering using matchOutgoingHandler, sorting by priority, wiring all matching and removing from ret.
		getFieldsAndMethods(processor.getClass(), parallel)
			.filter(m -> !Modifier.isAbstract(((Member) m).getModifiers()))
			.flatMap(ae -> outgoingHandlerConsumers.entrySet().stream().map(ihce -> new ConnectionMatchRecord<Consumer<H>>(
					ae, 
					ihce.getKey(), 
					ihce.getValue(), 
					ao -> ao.getAnnotation(OutgoingHandler.class).priority(), 
					ao -> ao.getAnnotation(OutgoingHandler.class).value())))
			.filter(mr -> matchOutgoingHandler(mr.accessibleObject(), mr.connection()))
			.sorted()
			.forEach(mr -> {
				AccessibleObject handlerMember = mr.accessibleObject();
				Connection outgoingConnection = mr.connection();
				if (ret.containsKey(outgoingConnection)) {
					if (handlerMember instanceof Field) {					
						mr.value().accept((H) getFieldValue(processor, (Field) handlerMember));
					} else {
						Method handlerSupplierMethod = (Method) handlerMember;
						Object outgoinggHandler = handlerSupplierMethod.getParameterCount() == 0 ? invokeMethod(processor, handlerSupplierMethod) : invokeMethod(processor, handlerSupplierMethod, outgoingConnection);
						mr.value().accept((H) outgoinggHandler);
					}
					ret.remove(outgoingConnection);
				}
			});
				
		return Collections.unmodifiableMap(ret);
	}
	
	protected void wireOutgoingHandlerConsumers(Object processor, Map<Connection, Consumer<H>> outgoingHandlerConsumers, boolean parallel) {
		getFieldsAndMethods(processor.getClass(), parallel)
				.filter(ae -> ae.getAnnotation(OutgoingHandlerConsumers.class) != null)
				.filter(ae -> mustSet(ae, Map.class, "Fields/methods annotated with OutgoingHandlersConsumers must have (parameter) type assignable from Map: " + ae))
				.forEach(setter -> set(processor, setter, outgoingHandlerConsumers));
	}
	
	/**
	 * Matches processor field or method and outgoing connection.
	 * @return
	 */
	protected boolean matchOutgoingEndpoint(AnnotatedElement endpointMember, Connection outgoingConnection) {
		OutgoingEndpoint outgoingEndpointAnnotation = endpointMember.getAnnotation(OutgoingEndpoint.class);
		if (outgoingEndpointAnnotation == null) {
			return false;
		}
		
		if (endpointMember instanceof Method) {
			Method endpointMethod = (Method) endpointMember;
			int pc = endpointMethod.getParameterCount();
			if (pc == 0 || pc > 3) {
				throw new NasdanikaException("A method annotated with OutgoingEndpoint shall have 1 - 3 parameters: " + endpointMethod);
			}
			if (pc > 1 && !endpointMethod.getParameterTypes()[0].isInstance(outgoingConnection)) {
				return false;				
			}
		}
				
		return matchPredicate(outgoingConnection, outgoingEndpointAnnotation.value());
	}	
	
	protected List<EndpointWireRecord> wireOutgoingEndpoint(
			Object processor, 
			Map<Connection, CompletionStage<E>> outgoingEndpoints,
			boolean parallel,
			ProgressMonitor progressMonitor) {
						
		Set<AccessibleObject> wiredFields = Collections.synchronizedSet(new HashSet<>()); // For setting a field once, setter methods may be invoked multiple times.
		Set<Connection> wiredConnections = Collections.synchronizedSet(new HashSet<>()); // For wiring a connection once.

		// Streaming fields and methods and then flat mapping them to all permutations with outgoing endpoints.
		// then filtering using matchOutgoingEndpoint, sorting by priority, wiring all matching and removing from ret.
		return getFieldsAndMethods(processor.getClass(), parallel)
			.filter(m -> !Modifier.isAbstract(((Member) m).getModifiers()))
			.flatMap(ae -> outgoingEndpoints.entrySet().stream().map(iee -> new ConnectionMatchRecord<CompletionStage<E>>(
					ae, 
					iee.getKey(), 
					iee.getValue(), 
					ao -> ao.getAnnotation(OutgoingEndpoint.class).priority(), 
					ao -> ao.getAnnotation(OutgoingEndpoint.class).value())))
			.filter(mr -> matchOutgoingEndpoint(mr.accessibleObject(), mr.connection()))
			.sorted()
			.map(mr -> {
				AccessibleObject endpointMember = mr.accessibleObject();
				Connection outgoingConnection = mr.connection();
				if (wiredConnections.add(outgoingConnection) && (!(endpointMember instanceof Field) || wiredFields.add(endpointMember))) { // Wiring once and setting a field once
					CompletionStage<Void> result = mr.value().thenAccept(outgoingEndpoint -> {
						if (endpointMember instanceof Field) {
							setFieldValue(processor, (Field) endpointMember, outgoingEndpoint);
						} else {
							Method endpointMethod = (Method) endpointMember;
							switch (endpointMethod.getParameterCount()) {
							case 1:
								invokeMethod(processor, endpointMethod, outgoingEndpoint);
								break;
							case 2:
								invokeMethod(processor, endpointMethod, outgoingConnection, outgoingEndpoint);
								break;
							case 3:
								invokeMethod(processor, endpointMethod, outgoingConnection, outgoingEndpoint, progressMonitor);
								break;								
							default:
								throw new NasdanikaException("Outgoing endpoint method shall have 1 to 3 parameters: " + endpointMethod);
							}
						}					
					});
					return new EndpointWireRecord(outgoingConnection, result, endpointMember.getAnnotation(OutgoingEndpoint.class).consume());
				}
				return null;
			})
			.filter(Objects::nonNull)
			.collect(Collectors.toList());				
	}

	protected void wireOutgoingEndpoints(Object processor, Map<Connection, CompletionStage<E>> outgoingEndpoints, boolean parallel) {
		getFieldsAndMethods(processor.getClass(), parallel)
				.filter(ae -> ae.getAnnotation(OutgoingEndpoints.class) != null)
				.filter(ae -> mustSet(ae, Map.class, "Fields/methods annotated with OutgoingEndpoints must have (parameter) type assignable from Map: " + ae))
				.forEach(setter -> set(processor, setter, outgoingEndpoints));
	}
	
	protected static Stream<AccessibleObject> getFieldsAndMethods(Class<?> clazz, boolean parallel) {		
		Stream<AccessibleObject> fieldsAndMethods = Util.getFieldsAndMethods(clazz);
		return parallel ? fieldsAndMethods : fieldsAndMethods.parallel();
	}

	// Connection wiring
	@SuppressWarnings("unchecked")
	protected void wireTargetHandler(Object processor, ConnectionProcessorConfig<P, H, E, R> connectionProcessorConfig, boolean parallel) {
		Optional<AccessibleObject> getter = getFieldsAndMethods(processor.getClass(), parallel)
			.filter(ae -> ae.getAnnotation(TargetHandler.class) != null)
			.filter(ae -> mustGet(ae, null, "Cannot use " + ae + " to get target connection handler"))
			.findFirst();
		
		if (getter.isPresent()) {
			 connectionProcessorConfig.setTargetHandler((H) get(processor, getter.get()));
		}
	}

	protected CompletionStage<Void> wireTargetEndpoint(
			Object processor, 
			CompletionStage<E> targetEndpointCompletionStage, 
			boolean parallel) {
		
		Optional<AccessibleObject> setter = getFieldsAndMethods(processor.getClass(), parallel)
			.filter(ae -> ae.getAnnotation(TargetEndpoint.class) != null)
			.filter(ae -> mustSet(ae, null, "Cannot use " + ae + " to set target connection endpoint"))
			.findFirst();
		
		
		if (setter.isPresent()) {
			return targetEndpointCompletionStage.thenAccept(targetEndpoint -> {
				set(processor, setter.get(), targetEndpoint);	
			});
		}
		return CompletableFuture.completedStage(null);
	}

	@SuppressWarnings("unchecked")
	protected void wireSourceHandler(Object processor, ConnectionProcessorConfig<P, H, E, R> connectionProcessorConfig, boolean parallel) {
		Optional<AccessibleObject> getter = getFieldsAndMethods(processor.getClass(), parallel)
			.filter(ae -> ae.getAnnotation(SourceHandler.class) != null)
			.filter(ae -> mustGet(ae, null, "Cannot use " + ae + " to get source connection handler"))
			.findFirst();
		
		if (getter.isPresent()) {
			 connectionProcessorConfig.setSourceHandler((H) get(processor, getter.get()));
		}
	}

	protected CompletionStage<Void> wireSourceEndpoint(
			Object processor, 
			CompletionStage<E> sourceEndpointCompletionStage,
			boolean parallel) {
		
		Optional<AccessibleObject> setter = getFieldsAndMethods(processor.getClass(), parallel)
			.filter(ae -> ae.getAnnotation(SourceEndpoint.class) != null)
			.filter(ae -> mustSet(ae, null, "Cannot use " + ae + " to set source connection endpoint"))
			.findFirst();
		
		
		if (setter.isPresent()) {
			return sourceEndpointCompletionStage.thenAccept(sourceEndpoint -> {
				set(processor, setter.get(), sourceEndpoint);	
			});				
		}
		return CompletableFuture.completedStage(null);
	}
	
	protected boolean matchFactoryMethod(ProcessorConfig<P,R> elementProcessorConfig, Method method) {
		if (Modifier.isAbstract(method.getModifiers())) {
			return false;
		}
		
		Processor elementProcessorAnnotation = method.getAnnotation(Processor.class);
		if (elementProcessorAnnotation == null) {
			return false;
		}
		
		Element element = elementProcessorConfig.getElement();
		if (!elementProcessorAnnotation.type().isInstance(element)) {
			return false;
		}
		
		if (method.getParameterCount() > 2) {
			return false;
		}
		
		if (method.getParameterCount() > 0 && !method.getParameterTypes()[0].isInstance(elementProcessorConfig)) {
			return false;
		}

		if (method.getParameterCount() == 2 && !method.getParameterTypes()[1].isAssignableFrom(ProgressMonitor.class)) {
			return false;
		}
		
		return matchPredicate(elementProcessorConfig.getElement(), elementProcessorAnnotation.value());
	}
	
}
