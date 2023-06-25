package org.nasdanika.graph.processor;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.common.Util;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

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
	
	public R createProcessors(Element element, ProgressMonitor progressMonitor, Object... registryTargets) {
		R registry = ProcessorFactory.super.createProcessors(progressMonitor, element);
		for (Object registryTarget: registryTargets) {
			// TODO  - progress moinitor			
			wireRegistryEntry(registryTarget).accept(registry);
			wireRegistry(registryTarget).accept(registry);
		}
		return registry;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ProcessorInfo<P,R> createProcessor(ProcessorConfig<P,R> config, ProgressMonitor progressMonitor) {
		// TODO - progress steps.
		Optional<AnnotatedElementRecord> match = annotatedElementRecords.stream()
			.filter(r -> r.test(config.getElement()) && r.getAnnotatedElement() instanceof Method && matchFactoryMethod(config, (Method) r.getAnnotatedElement()))
			.sorted((a, b) -> compareProcessorMethods((Method) a.getAnnotatedElement(), (Method) b.getAnnotatedElement()))
			.findFirst();
		
		if (match.isEmpty()) {
			return ProcessorFactory.super.createProcessor(config, progressMonitor);			
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
			return ProcessorFactory.super.createProcessor(config, progressMonitor);			
		}
		Processor elementProcessorAnnotation = method.getAnnotation(Processor.class);
		
		boolean hideWired = elementProcessorAnnotation.hideWired();
		Map<Element, ProcessorInfo<P,R>> unwiredChildProcessorsInfo = wireChildProcessor(processor, config.getChildProcessorsInfo());
		wireChildProcessors(processor, hideWired ? unwiredChildProcessorsInfo : config.getChildProcessorsInfo());

		Collection<Throwable> failures = new ArrayList<>();
		Function<Throwable, Void> failureHandler  = failure -> {
			if (failure != null) {
				failures.add(failure);
			}
			return null;
		};

		Consumer<ProcessorInfo<P,R>> pc = wireParentProcessor(processor);
		if (pc != null) {
			config.getParentProcessorInfo().thenAccept(pc).exceptionally(failureHandler);
		}
		wireProcessorElement(processor, config.getElement());
		
		config.getRegistry().thenAccept(wireRegistryEntry(processor)).exceptionally(failureHandler);
		
		Consumer<R> rc = wireRegistry(processor);
		if (rc != null) {
			config.getRegistry().thenAccept(rc).exceptionally(failureHandler);
		}
		
		ProcessorConfig<P,R> unwiredConfig;
		
		if (config instanceof NodeProcessorConfig) {
			NodeProcessorConfig<P, H, E, R> nodeProcessorConfig = (NodeProcessorConfig<P, H, E, R>) config;
			Map<Connection, CompletionStage<E>> unwiredIncomingEndpoints = wireIncomingEndpoint(processor, nodeProcessorConfig.getIncomingEndpoints(), failureHandler, progressMonitor);
			wireIncomingEndpoints(processor, hideWired ? unwiredIncomingEndpoints : nodeProcessorConfig.getIncomingEndpoints());
			
			Map<Connection, Consumer<H>> unwiredIncomingHandlerConsumers = wireIncomingHandler(processor, nodeProcessorConfig.getIncomingHandlerConsumers());
			wireIncomingHandlerConsumers(processor, hideWired ? unwiredIncomingHandlerConsumers : nodeProcessorConfig.getIncomingHandlerConsumers());
			
			Map<Connection, CompletionStage<E>> unwiredOutgoingEndpoints = wireOutgoingEndpoint(processor, nodeProcessorConfig.getOutgoingEndpoints(), failureHandler, progressMonitor);
			wireOutgoingEndpoints(processor, hideWired ? unwiredOutgoingEndpoints : nodeProcessorConfig.getOutgoingEndpoints());
			
			Map<Connection, Consumer<H>> unwiredOutgoingHandlerConsumers = wireOutgoingHandler(processor, nodeProcessorConfig.getOutgoingHandlerConsumers());
			wireOutgoingHandlerConsumers(processor, hideWired ? unwiredOutgoingHandlerConsumers : nodeProcessorConfig.getOutgoingHandlerConsumers());
			
			unwiredConfig = new NodeProcessorConfig<P, H, E, R>() {

				@Override
				public Map<Element, ProcessorInfo<P,R>> getChildProcessorsInfo() {
					return unwiredChildProcessorsInfo;
				}

				@Override
				public CompletionStage<ProcessorInfo<P,R>> getParentProcessorInfo() {
					return config.getParentProcessorInfo();
				}

				@Override
				public CompletionStage<R> getRegistry() {
					return config.getRegistry();
				}

				@Override
				public Node getElement() {
					return (Node) config.getElement();
				}

				@Override
				public Map<Connection, CompletionStage<E>> getIncomingEndpoints() {
					return unwiredIncomingEndpoints;
				}

				@Override
				public Map<Connection, Consumer<H>> getIncomingHandlerConsumers() {
					return unwiredIncomingHandlerConsumers;
				}

				@Override
				public Map<Connection, CompletionStage<E>> getOutgoingEndpoints() {
					return unwiredOutgoingEndpoints;
				}

				@Override
				public Map<Connection, Consumer<H>> getOutgoingHandlerConsumers() {
					return unwiredOutgoingHandlerConsumers;
				}
			};
		} else if (config instanceof ConnectionProcessorConfig) {
			ConnectionProcessorConfig<P, H, E, R> connectionProcessorConfig = (ConnectionProcessorConfig<P, H, E, R>) config;
			boolean wiredSourceEndpoint = wireSourceEndpoint(processor, connectionProcessorConfig.getSourceEndpoint(), failureHandler);
			boolean wiredSourceHandler = wireSourceHandler(processor, connectionProcessorConfig);
			boolean wiredTargetEndpoint = wireTargetEndpoint(processor, connectionProcessorConfig.getTargetEndpoint(), failureHandler);
			boolean wiredTargetHandler = wireTargetHandler(processor, connectionProcessorConfig);
			
			unwiredConfig = new ConnectionProcessorConfig<P, H, E, R>() {

				@Override
				public Map<Element, ProcessorInfo<P,R>> getChildProcessorsInfo() {
					return unwiredChildProcessorsInfo;
				}
				
				@Override
				public CompletionStage<ProcessorInfo<P,R>> getParentProcessorInfo() {
					return config.getParentProcessorInfo();
				}

				@Override
				public CompletionStage<R> getRegistry() {
					return config.getRegistry();
				}

				@Override
				public Connection getElement() {
					return (Connection) config.getElement();
				}

				@Override
				public CompletionStage<E> getSourceEndpoint() {
					return wiredSourceEndpoint ? null : connectionProcessorConfig.getSourceEndpoint();
				}

				@Override
				public void setSourceHandler(H sourceHandler) {
					if (wiredSourceHandler) {
						throw new IllegalStateException("Source handler is already wired for " + getElement());
					}
					connectionProcessorConfig.setSourceHandler(sourceHandler);
				}

				@Override
				public CompletionStage<E> getTargetEndpoint() {
					return wiredTargetEndpoint ? null : connectionProcessorConfig.getTargetEndpoint();
				}

				@Override
				public void setTargetHandler(H targetHandler) {
					if (wiredTargetHandler) {
						throw new IllegalStateException("Target handler is already wired for " + getElement());
					}
					connectionProcessorConfig.setTargetHandler(targetHandler);
				}
			};
		} else {
			unwiredConfig = new ProcessorConfig<P,R>() {

				@Override
				public Map<Element, ProcessorInfo<P,R>> getChildProcessorsInfo() {
					return unwiredChildProcessorsInfo;
				}
				
				@Override
				public CompletionStage<ProcessorInfo<P,R>> getParentProcessorInfo() {
					return config.getParentProcessorInfo();
				}

				@Override
				public CompletionStage<R> getRegistry() {
					return config.getRegistry();
				}

				@Override
				public Element getElement() {
					return config.getElement();
				}

			};
		}

		return ProcessorInfo.of(hideWired ? unwiredConfig : config, (P) processor, () -> failures);
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
		
	protected Consumer<R> wireRegistryEntry(Object processor) {
		List<AccessibleObject> registryEntrySetters = Util.getFieldsAndMethods(processor.getClass())
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

	protected Consumer<R> wireRegistry(Object processor) {
		List<AccessibleObject> registrySetters = Util.getFieldsAndMethods(processor.getClass())
				.filter(ae -> ae.getAnnotation(Registry.class) != null)
				.filter(ae -> mustSet(ae, Map.class, "Fields/methods annotated with Registry must have (parameter) type assignable from Map: " + ae))
				.collect(Collectors.toList());
			
		return registry -> registrySetters.forEach(setter -> set(processor, setter, registry));
	}

	protected void wireProcessorElement(Object processor, Element element) {
		Util.getFieldsAndMethods(processor.getClass())
				.filter(ae -> ae.getAnnotation(ProcessorElement.class) != null)
				.filter(ae -> mustSet(ae, element.getClass(), "Methods annotated with ProcessorElement must have one parameter compatible with the processor element type (" + element.getClass() + "): " + ae))
				.forEach(setter -> {
					set(processor, setter, element);
				});						
	}

	protected Consumer<ProcessorInfo<P,R>> wireParentProcessor(Object processor) {
		List<AccessibleObject> parentProcessorSetters = Util.getFieldsAndMethods(processor.getClass())
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
	
	private Map<Element, ProcessorInfo<P,R>> wireChildProcessor(Object processor, Map<Element, ProcessorInfo<P,R>> childProcessorsInfo) {		
		Map<Element, ProcessorInfo<P,R>> ret = new LinkedHashMap<>(childProcessorsInfo); 
		
		Util.getFieldsAndMethods(processor.getClass())
			.filter(ae -> ae.getAnnotation(ChildProcessor.class) != null)
			.filter(ae -> mustSet(ae, null, "Fields/methods annotated with ChildProcessor must have (parameter) type assignable from the processor type or ProcessorConfig if config is set to true: " + ae))
			.forEach(setter -> {
				for (Entry<Element, ProcessorInfo<P,R>> ce: childProcessorsInfo.entrySet()) {
					ChildProcessor childProcessorAnnotation = setter.getAnnotation(ChildProcessor.class);
					if (matchPredicate(ce.getKey(), childProcessorAnnotation.value())) {
						set(processor, setter, childProcessorAnnotation.config() ? ce.getValue().getConfig() : ce.getValue().getProcessor());
					};						
				}				
				
			});
		return ret;
	}	

	protected void wireChildProcessors(Object processor, Map<Element, ProcessorInfo<P,R>> childProcessorsInfo) {		
		Util.getFieldsAndMethods(processor.getClass())
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
	protected Map<Connection, Consumer<H>> wireIncomingHandler(Object processor, Map<Connection, Consumer<H>> incomingHandlerConsumers) {		
		Map<Connection, Consumer<H>> ret = new LinkedHashMap<>(incomingHandlerConsumers);

		// Streaming fields and methods and then flat mapping them to all permutations with incoming handler consumers.
		// then filtering using matchIncomingHandler, sorting by priority, for all matching - wiring and removing from ret.
		Util.getFieldsAndMethods(processor.getClass())
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
				
		return ret;
	}	
	
	protected void wireIncomingHandlerConsumers(Object processor, Map<Connection, Consumer<H>> incomingHandlerConsumers) {				
		Util.getFieldsAndMethods(processor.getClass())
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

	protected Map<Connection, CompletionStage<E>> wireIncomingEndpoint(
			Object processor, 
			Map<Connection, CompletionStage<E>> incomingEndpoints,
			Function<Throwable, Void> failureHandler, 
			ProgressMonitor progressMonitor) {
						
		Map<Connection, CompletionStage<E>> ret = new LinkedHashMap<>(incomingEndpoints);
		Set<AccessibleObject> wiredFields = new HashSet<>(); // For setting a field once, setter methods may be invoked multiple times.
				
		// Streaming fields and methods and then flat mapping them to all permutations with incoming endpoints.
		// then filtering using matchIncomingEndpoint, sorting by priority, wiring all matching and removing from ret.
		Util.getFieldsAndMethods(processor.getClass())
			.filter(m -> !Modifier.isAbstract(((Member) m).getModifiers()))
			.flatMap(ae -> incomingEndpoints.entrySet().stream().map(iee -> new ConnectionMatchRecord<CompletionStage<E>>(
					ae, 
					iee.getKey(), 
					iee.getValue(), 
					ao -> ao.getAnnotation(IncomingEndpoint.class).priority(), 
					ao -> ao.getAnnotation(IncomingEndpoint.class).value())))
			.filter(mr -> matchIncomingEndpoint(mr.accessibleObject(), mr.connection()))
			.sorted()
			.forEach(mr -> {
				AccessibleObject endpointMember = mr.accessibleObject();
				Connection incomingConnection = mr.connection();
				if (ret.containsKey(incomingConnection) && (!(endpointMember instanceof Field) || wiredFields.add(endpointMember))) { // Wiring an endpoint once and setting a field once
					mr.value().thenAccept(incomingEndpoint -> {
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
					}).exceptionally(failureHandler);
					if (endpointMember.getAnnotation(IncomingEndpoint.class).consume()) {
						ret.remove(incomingConnection);
					}
				}
			});
				
		return ret;
	}

	protected void wireIncomingEndpoints(Object processor, Map<Connection, CompletionStage<E>> incomingEndpoints) {
		Util.getFieldsAndMethods(processor.getClass())
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
	protected Map<Connection, Consumer<H>> wireOutgoingHandler(Object processor, Map<Connection, Consumer<H>> outgoingHandlerConsumers) {

		Map<Connection, Consumer<H>> ret = new LinkedHashMap<>(outgoingHandlerConsumers);

		// Streaming fields and methods and then flat mapping them to all permutations with outgoing handler consumers.
		// then filtering using matchOutgoingHandler, sorting by priority, wiring all matching and removing from ret.
		Util.getFieldsAndMethods(processor.getClass())
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
				
		return ret;
	}
	
	protected void wireOutgoingHandlerConsumers(Object processor, Map<Connection, Consumer<H>> outgoingHandlerConsumers) {
		Util.getFieldsAndMethods(processor.getClass())
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

	protected Map<Connection, CompletionStage<E>> wireOutgoingEndpoint(
			Object processor, 
			Map<Connection, CompletionStage<E>> outgoingEndpoints,
			Function<Throwable, Void> failureHandler, 
			ProgressMonitor progressMonitor) {
						
		Map<Connection, CompletionStage<E>> ret = new LinkedHashMap<>(outgoingEndpoints);
		Set<AccessibleObject> wiredFields = new HashSet<>(); // For setting a field once, setter methods may be invoked multiple times.

		// Streaming fields and methods and then flat mapping them to all permutations with outgoing endpoints.
		// then filtering using matchOutgoingEndpoint, sorting by priority, wiring all matching and removing from ret.
		Util.getFieldsAndMethods(processor.getClass())
			.filter(m -> !Modifier.isAbstract(((Member) m).getModifiers()))
			.flatMap(ae -> outgoingEndpoints.entrySet().stream().map(iee -> new ConnectionMatchRecord<CompletionStage<E>>(
					ae, 
					iee.getKey(), 
					iee.getValue(), 
					ao -> ao.getAnnotation(OutgoingEndpoint.class).priority(), 
					ao -> ao.getAnnotation(OutgoingEndpoint.class).value())))
			.filter(mr -> matchOutgoingEndpoint(mr.accessibleObject(), mr.connection()))
			.sorted()
			.forEach(mr -> {
				AccessibleObject endpointMember = mr.accessibleObject();
				Connection outgoingConnection = mr.connection();
				if (ret.containsKey(outgoingConnection) && (!(endpointMember instanceof Field) || wiredFields.add(endpointMember))) { // Wiring once and setting a field once
					mr.value().thenAccept(outgoingEndpoint -> {
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
					}).exceptionally(failureHandler);
					if (endpointMember.getAnnotation(OutgoingEndpoint.class).consume()) {
						ret.remove(outgoingConnection);
					}
				}
			});
				
		return ret;
	}

	protected void wireOutgoingEndpoints(Object processor, Map<Connection, CompletionStage<E>> outgoingEndpoints) {
		Util.getFieldsAndMethods(processor.getClass())
				.filter(ae -> ae.getAnnotation(OutgoingEndpoints.class) != null)
				.filter(ae -> mustSet(ae, Map.class, "Fields/methods annotated with OutgoingEndpoints must have (parameter) type assignable from Map: " + ae))
				.forEach(setter -> set(processor, setter, outgoingEndpoints));
	}

	// Connection wiring
	@SuppressWarnings("unchecked")
	protected boolean wireTargetHandler(Object processor, ConnectionProcessorConfig<P, H, E, R> connectionProcessorConfig) {
		Optional<AccessibleObject> getter = Util.getFieldsAndMethods(processor.getClass())
			.filter(ae -> ae.getAnnotation(TargetHandler.class) != null)
			.filter(ae -> mustGet(ae, null, "Cannot use " + ae + " to get target connection handler"))
			.findFirst();
		
		
		if (getter.isPresent()) {
			 connectionProcessorConfig.setTargetHandler((H) get(processor, getter.get()));
			 return true;
		}
		return false;
	}

	protected boolean wireTargetEndpoint(
			Object processor, 
			CompletionStage<E> targetEndpointCompletionStage, 
			Function<Throwable, Void> failureHandler) {
		
		Optional<AccessibleObject> setter = Util.getFieldsAndMethods(processor.getClass())
			.filter(ae -> ae.getAnnotation(TargetEndpoint.class) != null)
			.filter(ae -> mustSet(ae, null, "Cannot use " + ae + " to set target connection endpoint"))
			.findFirst();
		
		
		if (setter.isPresent()) {
			targetEndpointCompletionStage.thenAccept(targetEndpoint -> {
				set(processor, setter.get(), targetEndpoint);	
			}).exceptionally(failureHandler);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	protected boolean wireSourceHandler(Object processor, ConnectionProcessorConfig<P, H, E, R> connectionProcessorConfig) {
		Optional<AccessibleObject> getter = Util.getFieldsAndMethods(processor.getClass())
			.filter(ae -> ae.getAnnotation(SourceHandler.class) != null)
			.filter(ae -> mustGet(ae, null, "Cannot use " + ae + " to get source connection handler"))
			.findFirst();
		
		
		if (getter.isPresent()) {
			 connectionProcessorConfig.setSourceHandler((H) get(processor, getter.get()));
			 return true;
		}
		return false;
	}

	protected boolean wireSourceEndpoint(
			Object processor, 
			CompletionStage<E> sourceEndpointCompletionStage,
			Function<Throwable, Void> failureHandler) {
		
		Optional<AccessibleObject> setter = Util.getFieldsAndMethods(processor.getClass())
			.filter(ae -> ae.getAnnotation(SourceEndpoint.class) != null)
			.filter(ae -> mustSet(ae, null, "Cannot use " + ae + " to set source connection endpoint"))
			.findFirst();
		
		
		if (setter.isPresent()) {
			sourceEndpointCompletionStage.thenAccept(sourceEndpoint -> {
				set(processor, setter.get(), sourceEndpoint);	
			}).exceptionally(failureHandler);				
			return true;
		}
		return false;
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
