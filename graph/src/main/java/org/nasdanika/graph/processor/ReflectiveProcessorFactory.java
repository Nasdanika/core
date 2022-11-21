package org.nasdanika.graph.processor;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

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
public abstract class ReflectiveProcessorFactory<P, H, E> implements ProcessorFactory<P, H, E> {
	
	private Object[] targets;
	private IntrospectionLevel introspectionLevel;

	public ReflectiveProcessorFactory(IntrospectionLevel introspectionLevel, Object... targets) {
		this.introspectionLevel = introspectionLevel == null ? IntrospectionLevel.NONE : introspectionLevel;
		this.targets = targets;
	}
	
	public Map<Element, ProcessorInfo<P>> createProcessors(Element element, IntrospectionLevel introspectionLevel, Object... registryTargets) {
		Map<Element, ProcessorInfo<P>> registry = ProcessorFactory.super.createProcessors(element);
		for (Object registryTarget: registryTargets) {
			wireRegistryEntry(registryTarget, introspectionLevel).accept(registry);
			wireRegistry(registryTarget, introspectionLevel).accept(registry);
		}
		return registry;
	}
	
	@Override
	public ProcessorInfo<P> createProcessor(ProcessorConfig<P> config) {
		if (introspectionLevel != IntrospectionLevel.NONE) {
			for (Object target: targets) {
				ProcessorInfo<P> elementProcessorInfo = createProcessor(target, config, introspectionLevel);
				if (elementProcessorInfo != null) {
					return elementProcessorInfo;
				}
			}
		}
		return ProcessorFactory.super.createProcessor(config); 		
	}		
	
	@SuppressWarnings("unchecked")
	protected ProcessorInfo<P> createProcessor(Object target, ProcessorConfig<P> config, IntrospectionLevel introspectionLevel) {
	
		if (target != null && (target.getClass().getAnnotation(Factory.class) == null || matchPredicate(config.getElement(), target.getClass().getAnnotation(Factory.class).value()))) {
			
			Optional<Method> match = getMethods(target.getClass(), introspectionLevel)
				.filter(m -> matchFactoryMethod(config, m))
				.sorted(this::compareProcessorMethods)
				.findFirst();
			
			if (match.isPresent()) {
				Method method = match.get();
				Object processor;
				if (method.getParameterCount() == 0) {
					processor = invokeMethod(target, method);
				} else {
					processor = invokeMethod(target, method, config);
				}
				if (processor != null) {
					Processor elementProcessorAnnotation = method.getAnnotation(Processor.class);
					IntrospectionLevel processorIntrospectionLevel = elementProcessorAnnotation.introspect();
					if (processorIntrospectionLevel == IntrospectionLevel.NONE) {
						return ProcessorFactory.super.createProcessor(config);
					}
					
					boolean hideWired = elementProcessorAnnotation.hideWired();
					Map<Element, ProcessorInfo<P>> unwiredChildProcessorsInfo = wireChildProcessor(processor, config.getChildProcessorsInfo(), processorIntrospectionLevel);
					wireChildProcessors(processor, hideWired ? unwiredChildProcessorsInfo : config.getChildProcessorsInfo(), processorIntrospectionLevel);
					
					Consumer<ProcessorInfo<P>> pc = wireParentProcessor(processor, processorIntrospectionLevel);
					if (pc != null) {
						config.getParentProcessorInfo().thenAccept(pc);
					}
					wireProcessorElement(processor, config.getElement(), processorIntrospectionLevel);
					
					config.getRegistry().thenAccept(wireRegistryEntry(processor, processorIntrospectionLevel));
					
					Consumer<Map<Element, ProcessorInfo<P>>> rc = wireRegistry(processor, processorIntrospectionLevel);
					if (rc != null) {
						config.getRegistry().thenAccept(rc);
					}
					
					ProcessorConfig<P> unwiredConfig;
					
					if (config instanceof NodeProcessorConfig) {
						NodeProcessorConfig<P, H, E> nodeProcessorConfig = (NodeProcessorConfig<P, H, E>) config;
						Map<Connection, CompletionStage<E>> unwiredIncomingEndpoints = wireIncomingEndpoint(processor, nodeProcessorConfig.getIncomingEndpoints(), processorIntrospectionLevel);
						wireIncomingEndpoints(processor, hideWired ? unwiredIncomingEndpoints : nodeProcessorConfig.getIncomingEndpoints(), processorIntrospectionLevel);
						
						Map<Connection, Consumer<H>> unwiredIncomingHandlerConsumers = wireIncomingHandler(processor, nodeProcessorConfig.getIncomingHandlerConsumers(), processorIntrospectionLevel);
						wireIncomingHandlerConsumers(processor, hideWired ? unwiredIncomingHandlerConsumers : nodeProcessorConfig.getIncomingHandlerConsumers(), processorIntrospectionLevel);
						
						Map<Connection, CompletionStage<E>> unwiredOutgoingEndpoints = wireOutgoingEndpoint(processor, nodeProcessorConfig.getOutgoingEndpoints(), processorIntrospectionLevel);
						wireOutgoingEndpoints(processor, hideWired ? unwiredOutgoingEndpoints : nodeProcessorConfig.getOutgoingEndpoints(), processorIntrospectionLevel);
						
						Map<Connection, Consumer<H>> unwiredOutgoingHandlerConsumers = wireOutgoingHandler(processor, nodeProcessorConfig.getOutgoingHandlerConsumers(), processorIntrospectionLevel);
						wireOutgoingHandlerConsumers(processor, hideWired ? unwiredOutgoingHandlerConsumers : nodeProcessorConfig.getOutgoingHandlerConsumers(), processorIntrospectionLevel);
						
						unwiredConfig = new NodeProcessorConfig<P, H, E>() {
	
							@Override
							public Map<Element, ProcessorInfo<P>> getChildProcessorsInfo() {
								return unwiredChildProcessorsInfo;
							}
	
							@Override
							public CompletionStage<ProcessorInfo<P>> getParentProcessorInfo() {
								return config.getParentProcessorInfo();
							}
	
							@Override
							public CompletionStage<Map<Element, ProcessorInfo<P>>> getRegistry() {
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
						ConnectionProcessorConfig<P, H, E> connectionProcessorConfig = (ConnectionProcessorConfig<P, H, E>) config;
						boolean wiredSourceEndpoint = wireSourceEndpoint(processor, connectionProcessorConfig.getSourceEndpoint(), processorIntrospectionLevel);
						boolean wiredSourceHandler = wireSourceHandler(processor, connectionProcessorConfig, processorIntrospectionLevel);
						boolean wiredTargetEndpoint = wireTargetEndpoint(processor, connectionProcessorConfig.getTargetEndpoint(), processorIntrospectionLevel);
						boolean wiredTargetHandler = wireTargetHandler(processor, connectionProcessorConfig, processorIntrospectionLevel);
						
						unwiredConfig = new ConnectionProcessorConfig<P, H, E>() {
	
							@Override
							public Map<Element, ProcessorInfo<P>> getChildProcessorsInfo() {
								return unwiredChildProcessorsInfo;
							}
							
							@Override
							public CompletionStage<ProcessorInfo<P>> getParentProcessorInfo() {
								return config.getParentProcessorInfo();
							}
	
							@Override
							public CompletionStage<Map<Element, ProcessorInfo<P>>> getRegistry() {
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
						unwiredConfig = new ProcessorConfig<P>() {
	
							@Override
							public Map<Element, ProcessorInfo<P>> getChildProcessorsInfo() {
								return unwiredChildProcessorsInfo;
							}
							
							@Override
							public CompletionStage<ProcessorInfo<P>> getParentProcessorInfo() {
								return config.getParentProcessorInfo();
							}
	
							@Override
							public CompletionStage<Map<Element, ProcessorInfo<P>>> getRegistry() {
								return config.getRegistry();
							}
	
							@Override
							public Element getElement() {
								return config.getElement();
							}
	
						};
					}

					return ProcessorInfo.of(hideWired ? unwiredConfig : config, (P) processor);
				}
			}
			
			// Factories
			List<Entry<Object, IntrospectionLevel>> factories = getFieldsAndMethods(target.getClass(), introspectionLevel)
					.filter(ae -> ae.getAnnotation(Factory.class) != null)
					.filter(ae -> matchPredicate(config.getElement(), ae.getAnnotation(Factory.class).value()))
					.filter(ae -> mustGet(ae, null, "Methods annotated with Factory shall have no parameters: " + ae))
					.sorted((a, b) -> b.getAnnotation(Factory.class).priority() - a.getAnnotation(Factory.class).priority())
					.map(ae -> Map.entry(get(target, ae), ae.getAnnotation(Factory.class).introspect()))
					.collect(Collectors.toList());
			
			for (Entry<Object, IntrospectionLevel> factory: factories) {
				ProcessorInfo<P> elementProcessorInfo = createProcessor(factory.getKey(), config, factory.getValue());
				if (elementProcessorInfo != null) {
					return elementProcessorInfo;
				}
			}
			
			factories = getFieldsAndMethods(target.getClass(), introspectionLevel)
					.filter(ae -> ae.getAnnotation(Factories.class) != null)
					.filter(ae -> matchPredicate(config.getElement(), ae.getAnnotation(Factories.class).value()))
					.filter(ae -> mustGet(ae, Collection.class, "Methods and fields annotated with Factories shall be of type/return Collection. Methods shall have no parameters: " + ae))
					.sorted((a, b) -> b.getAnnotation(Factories.class).priority() - a.getAnnotation(Factories.class).priority())
					.flatMap(ae -> ((Collection<Object>) get(target, ae)).stream().map(e -> Map.entry(e, ae.getAnnotation(Factories.class).introspect())))
					.collect(Collectors.toList());
			
			for (Entry<Object, IntrospectionLevel> factory: factories) {
				ProcessorInfo<P> elementProcessorInfo = createProcessor(factory.getKey(), config, factory.getValue());
				if (elementProcessorInfo != null) {
					return elementProcessorInfo;
				}
			}			
			
		}
		return null;
	}

	protected int compareProcessorMethods(Method a, Method b) {
		int priorityCmp = b.getAnnotation(Processor.class).priority() - a.getAnnotation(Processor.class).priority();
		if (priorityCmp != 0) {
			return priorityCmp;
		}
		
		Class<Element> aType = a.getAnnotation(Processor.class).type();
		Class<Element> bType = b.getAnnotation(Processor.class).type();
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
		
	protected Consumer<Map<Element, ProcessorInfo<P>>> wireRegistryEntry(Object processor, IntrospectionLevel processorIntrospectionLevel) {
		List<AccessibleObject> registryEntrySetters = getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
			.filter(ae -> ae.getAnnotation(RegistryEntry.class) != null)
			.filter(ae -> mustSet(ae, null, "Fields/methods annotated with RegistryEntry must have (parameter) type assignable from the processor type or ProcessorConfig if config is set to true: " + ae))
			.collect(Collectors.toList());
		
		return registry -> {
			registryEntrySetters.forEach(setter -> {
				for (Entry<Element, ProcessorInfo<P>> re: registry.entrySet()) {
					RegistryEntry registryEntryAnnotation = setter.getAnnotation(RegistryEntry.class);
					if (matchPredicate(re.getKey(), registryEntryAnnotation.value())) {
						set(processor, setter, registryEntryAnnotation.config() ? re.getValue().getConfig() : re.getValue().getProcessor());
					};						
				}				
			});						
		};
	}

	protected Consumer<Map<Element, ProcessorInfo<P>>> wireRegistry(Object processor, IntrospectionLevel processorIntrospectionLevel) {
		List<AccessibleObject> registrySetters = getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
				.filter(ae -> ae.getAnnotation(Registry.class) != null)
				.filter(ae -> mustSet(ae, Map.class, "Fields/methods annotated with Registry must have (parameter) type assignable from Map: " + ae))
				.collect(Collectors.toList());
			
		return registry -> registrySetters.forEach(setter -> set(processor, setter, registry));
	}

	protected void wireProcessorElement(
			Object processor, 
			Element element,
			IntrospectionLevel processorIntrospectionLevel) {
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
				.filter(ae -> ae.getAnnotation(ProcessorElement.class) != null)
				.filter(ae -> mustSet(ae, element.getClass(), "Methods annotated with ProcessorElement must have one parameter compatible with the processor element type (" + element.getClass() + "): " + ae))
				.forEach(setter -> {
					set(processor, setter, element);
				});						
	}

	protected Consumer<ProcessorInfo<P>> wireParentProcessor(
			Object processor, 
			IntrospectionLevel processorIntrospectionLevel) {
		List<AccessibleObject> parentProcessorSetters = getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
				.filter(ae -> ae.getAnnotation(ParentProcessor.class) != null)
				.filter(ae -> mustSet(ae, null, "Fields/methods annotated with ParentProcessor must have (parameter) type assignable from the processor type or ProcessorConfig if value is set to true: " + ae))
				.collect(Collectors.toList());
			
			return parentProcessorInfo -> {
				parentProcessorSetters.forEach(setter -> {
					ParentProcessor parentProcessorAnnotation = setter.getAnnotation(ParentProcessor.class);
					set(processor, setter, parentProcessorAnnotation.value() ? parentProcessorInfo.getConfig() : parentProcessorInfo.getProcessor());
				});						
			};
	}
	
	private Map<Element, ProcessorInfo<P>> wireChildProcessor(
			Object processor, 
			Map<Element, ProcessorInfo<P>> childProcessorsInfo,
			IntrospectionLevel processorIntrospectionLevel) {
		
		Map<Element, ProcessorInfo<P>> ret = new LinkedHashMap<>(childProcessorsInfo); 
		
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
			.filter(ae -> ae.getAnnotation(ChildProcessor.class) != null)
			.filter(ae -> mustSet(ae, null, "Fields/methods annotated with ChildProcessor must have (parameter) type assignable from the processor type or ProcessorConfig if config is set to true: " + ae))
			.forEach(setter -> {
				for (Entry<Element, ProcessorInfo<P>> ce: childProcessorsInfo.entrySet()) {
					ChildProcessor childProcessorAnnotation = setter.getAnnotation(ChildProcessor.class);
					if (matchPredicate(ce.getKey(), childProcessorAnnotation.value())) {
						set(processor, setter, childProcessorAnnotation.config() ? ce.getValue().getConfig() : ce.getValue().getProcessor());
					};						
				}				
				
			});
		return ret;
	}	

	protected void wireChildProcessors(
			Object processor, 
			Map<Element, ProcessorInfo<P>> childProcessorsInfo,
			IntrospectionLevel processorIntrospectionLevel) {
		
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
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
				throw new NasdanikaException("A single parameter type of a method annotated with IncomingHandler shall be assignable from Connection: " + handlerMethod);				
			}
		}
				
		return matchPredicate(incomingConnection, incomingHandlerAnnotation.value());
	}
	
	// Node wiring
	@SuppressWarnings("unchecked")
	protected Map<Connection, Consumer<H>> wireIncomingHandler(
			Object processor, 
			Map<Connection, Consumer<H>> incomingHandlerConsumers,
			IntrospectionLevel processorIntrospectionLevel) {
		
		Map<Connection, Consumer<H>> ret = new LinkedHashMap<>(incomingHandlerConsumers);

		// Streaming fields and methods and then flat mapping them to all permutations with incoming handler consumers.
		// then filtering using matchIncomingHandler, sorting by priority, finding first, wiring it and removing from ret.
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
			.filter(m -> !Modifier.isAbstract(((Member) m).getModifiers()))
			.flatMap(ae -> incomingHandlerConsumers.entrySet().stream().map(ihce -> Map.entry(ae, ihce)))
			.filter(e -> matchIncomingHandler(e.getKey(), e.getValue().getKey()))
			.sorted((a, b) -> b.getKey().getAnnotation(IncomingHandler.class).priority() - a.getKey().getAnnotation(IncomingHandler.class).priority())
			.findFirst().ifPresent(e -> {
				AccessibleObject handlerMember = e.getKey();
				Entry<Connection, Consumer<H>> incomingHandlerConsumerEntry = e.getValue();
				if (handlerMember instanceof Field) {					
					incomingHandlerConsumerEntry.getValue().accept((H) getFieldValue(processor, (Field) handlerMember));
				} else {
					Method handlerSupplierMethod = (Method) handlerMember;
					Object incomingHandler = handlerSupplierMethod.getParameterCount() == 0 ? invokeMethod(processor, handlerSupplierMethod) : invokeMethod(processor, handlerSupplierMethod, incomingHandlerConsumerEntry.getKey());
					incomingHandlerConsumerEntry.getValue().accept((H) incomingHandler);
				}
				ret.remove(incomingHandlerConsumerEntry.getKey());
			});
				
		return ret;
	}	
	
	protected void wireIncomingHandlerConsumers(
			Object processor, 
			Map<Connection, Consumer<H>> incomingHandlerConsumers,
			IntrospectionLevel processorIntrospectionLevel) {
				
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
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
			if (pc == 0 || pc > 2) {
				throw new NasdanikaException("A method annotated with IncomingEndpoint shall have one or two parameters: " + endpointMethod);
			}
			if (!endpointMethod.getParameterTypes()[0].isInstance(incomingConnection)) {
				throw new NasdanikaException("The first parameter type of a method annotated with IncomingEndpoint shall be assignable from Connection: " + endpointMethod);				
			}
		}
				
		return matchPredicate(incomingConnection, incomingEndpointAnnotation.value());
	}	

	protected Map<Connection, CompletionStage<E>> wireIncomingEndpoint(
			Object processor, 
			Map<Connection, CompletionStage<E>> incomingEndpoints,
			IntrospectionLevel processorIntrospectionLevel) {
						
		Map<Connection, CompletionStage<E>> ret = new LinkedHashMap<>(incomingEndpoints);

		// Streaming fields and methods and then flat mapping them to all permutations with incoming endpoints.
		// then filtering using matchIncomingEndpoint, sorting by priority, finding first, wiring it and removing from ret.
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
			.filter(m -> !Modifier.isAbstract(((Member) m).getModifiers()))
			.flatMap(ae -> incomingEndpoints.entrySet().stream().map(iee -> Map.entry(ae, iee)))
			.filter(e -> matchIncomingEndpoint(e.getKey(), e.getValue().getKey()))
			.sorted((a, b) -> b.getKey().getAnnotation(IncomingEndpoint.class).priority() - a.getKey().getAnnotation(IncomingEndpoint.class).priority())
			.findFirst().ifPresent(e -> {
				AccessibleObject endpointMember = e.getKey();
				Entry<Connection, CompletionStage<E>> incomingEndpointEntry = e.getValue();
				incomingEndpointEntry.getValue().thenAccept(incomingEndpoint -> {
					if (endpointMember instanceof Field) {
						setFieldValue(processor, (Field) endpointMember, incomingEndpoint);
					} else {
						Method endpointMethod = (Method) endpointMember;
						if (endpointMethod.getParameterCount() == 1) {
							invokeMethod(processor, endpointMethod, incomingEndpoint);
						} else {
							invokeMethod(processor, endpointMethod, incomingEndpointEntry.getKey(), incomingEndpoint);						
						}
					}					
				});
				ret.remove(incomingEndpointEntry.getKey());
			});
				
		return ret;
	}

	protected void wireIncomingEndpoints(
			Object processor, 
			Map<Connection, CompletionStage<E>> incomingEndpoints,
			IntrospectionLevel processorIntrospectionLevel) {
		
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
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
				throw new NasdanikaException("A single parameter type of a method annotated with OutgoingHandler shall be assignable from Connection: " + handlerMethod);				
			}
		}
				
		return matchPredicate(outgoingConnection, outgoingHandlerAnnotation.value());
	}
	
	@SuppressWarnings("unchecked")
	protected Map<Connection, Consumer<H>> wireOutgoingHandler(
			Object processor, 
			Map<Connection, Consumer<H>> outgoingHandlerConsumers,
			IntrospectionLevel processorIntrospectionLevel) {

		Map<Connection, Consumer<H>> ret = new LinkedHashMap<>(outgoingHandlerConsumers);

		// Streaming fields and methods and then flat mapping them to all permutations with outgoing handler consumers.
		// then filtering using matchOutgoingHandler, sorting by priority, finding first, wiring it and removing from ret.
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
			.filter(m -> !Modifier.isAbstract(((Member) m).getModifiers()))
			.flatMap(ae -> outgoingHandlerConsumers.entrySet().stream().map(ihce -> Map.entry(ae, ihce)))
			.filter(e -> matchOutgoingHandler(e.getKey(), e.getValue().getKey()))
			.sorted((a, b) -> b.getKey().getAnnotation(OutgoingHandler.class).priority() - a.getKey().getAnnotation(OutgoingHandler.class).priority())
			.findFirst().ifPresent(e -> {
				AccessibleObject handlerMember = e.getKey();
				Entry<Connection, Consumer<H>> outgoingHandlerConsumerEntry = e.getValue();
				if (handlerMember instanceof Field) {					
					outgoingHandlerConsumerEntry.getValue().accept((H) getFieldValue(processor, (Field) handlerMember));
				} else {
					Method handlerSupplierMethod = (Method) handlerMember;
					Object incomingHandler = handlerSupplierMethod.getParameterCount() == 0 ? invokeMethod(processor, handlerSupplierMethod) : invokeMethod(processor, handlerSupplierMethod, outgoingHandlerConsumerEntry.getKey());
					outgoingHandlerConsumerEntry.getValue().accept((H) incomingHandler);
				}
				ret.remove(outgoingHandlerConsumerEntry.getKey());
			});
				
		return ret;
	}
	
	protected void wireOutgoingHandlerConsumers(
			Object processor,
			Map<Connection, Consumer<H>> outgoingHandlerConsumers,
			IntrospectionLevel processorIntrospectionLevel) {
		
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
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
			if (pc == 0 || pc > 2) {
				throw new NasdanikaException("A method annotated with OutgoingEndpoint shall have one or two parameters: " + endpointMethod);
			}
			if (!endpointMethod.getParameterTypes()[0].isInstance(outgoingConnection)) {
				throw new NasdanikaException("The first parameter type of a method annotated with OutgoingEndpoint shall be assignable from Connection: " + endpointMethod);				
			}
		}
				
		return matchPredicate(outgoingConnection, outgoingEndpointAnnotation.value());
	}	

	protected Map<Connection, CompletionStage<E>> wireOutgoingEndpoint(
			Object processor, 
			Map<Connection, CompletionStage<E>> outgoingEndpoints,
			IntrospectionLevel processorIntrospectionLevel) {
						
		Map<Connection, CompletionStage<E>> ret = new LinkedHashMap<>(outgoingEndpoints);

		// Streaming fields and methods and then flat mapping them to all permutations with outgoing endpoints.
		// then filtering using matchOutgoingEndpoint, sorting by priority, finding first, wiring it and removing from ret.
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
			.filter(m -> !Modifier.isAbstract(((Member) m).getModifiers()))
			.flatMap(ae -> outgoingEndpoints.entrySet().stream().map(iee -> Map.entry(ae, iee)))
			.filter(e -> matchOutgoingEndpoint(e.getKey(), e.getValue().getKey()))
			.sorted((a, b) -> b.getKey().getAnnotation(OutgoingEndpoint.class).priority() - a.getKey().getAnnotation(OutgoingEndpoint.class).priority())
			.findFirst().ifPresent(e -> {
				AccessibleObject endpointMember = e.getKey();
				Entry<Connection, CompletionStage<E>> outgoingEndpointEntry = e.getValue();
				outgoingEndpointEntry.getValue().thenAccept(outgoingEndpoint -> {
					if (endpointMember instanceof Field) {
						setFieldValue(processor, (Field) endpointMember, outgoingEndpoint);
					} else {
						Method endpointMethod = (Method) endpointMember;
						if (endpointMethod.getParameterCount() == 1) {
							invokeMethod(processor, endpointMethod, outgoingEndpoint);
						} else {
							invokeMethod(processor, endpointMethod, outgoingEndpointEntry.getKey(), outgoingEndpoint);						
						}
					}					
				});
				ret.remove(outgoingEndpointEntry.getKey());
			});
				
		return ret;
	}

	protected void wireOutgoingEndpoints(
			Object processor, 
			Map<Connection, CompletionStage<E>> outgoingEndpoints,
			IntrospectionLevel processorIntrospectionLevel) {
		
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
				.filter(ae -> ae.getAnnotation(OutgoingEndpoints.class) != null)
				.filter(ae -> mustSet(ae, Map.class, "Fields/methods annotated with OutgoingEndpoints must have (parameter) type assignable from Map: " + ae))
				.forEach(setter -> set(processor, setter, outgoingEndpoints));
	}

	// Connection wiring
	@SuppressWarnings("unchecked")
	protected boolean wireTargetHandler(
			Object processor, 
			ConnectionProcessorConfig<P, H, E> connectionProcessorConfig, 
			IntrospectionLevel processorIntrospectionLevel) {
		
		Optional<AccessibleObject> getter = getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
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
			IntrospectionLevel processorIntrospectionLevel) {
		
		Optional<AccessibleObject> setter = getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
			.filter(ae -> ae.getAnnotation(TargetEndpoint.class) != null)
			.filter(ae -> mustSet(ae, null, "Cannot use " + ae + " to set target connection endpoint"))
			.findFirst();
		
		
		if (setter.isPresent()) {
			targetEndpointCompletionStage.thenAccept(targetEndpoint -> {
				set(processor, setter.get(), targetEndpoint);	
			});
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	protected boolean wireSourceHandler(
			Object processor, 
			ConnectionProcessorConfig<P, H, E> connectionProcessorConfig,
			IntrospectionLevel processorIntrospectionLevel) {
		
		Optional<AccessibleObject> getter = getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
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
			IntrospectionLevel processorIntrospectionLevel) {
		
		Optional<AccessibleObject> setter = getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
			.filter(ae -> ae.getAnnotation(SourceEndpoint.class) != null)
			.filter(ae -> mustSet(ae, null, "Cannot use " + ae + " to set source connection endpoint"))
			.findFirst();
		
		
		if (setter.isPresent()) {
			sourceEndpointCompletionStage.thenAccept(sourceEndpoint -> {
				set(processor, setter.get(), sourceEndpoint);	
			});				
			return true;
		}
		return false;
	}
	
	protected boolean matchFactoryMethod(ProcessorConfig<P> elementProcessorConfig, Method method) {
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
		
		if (method.getParameterCount() > 1) {
			return false;
		}
		
		if (method.getParameterCount() == 1 && !method.getParameterTypes()[0].isInstance(elementProcessorConfig)) {
			return false;
		}
		
		return matchPredicate(elementProcessorConfig.getElement(), elementProcessorAnnotation.value());
	}

	/**
	 * Parses and evaluates expression using <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions">Spring Expression Language</a> 
	 * @param obj
	 * @param expr 
	 * @return true if expression is blank or evaluates to true, false if the expression evaluates to false or throws EvaluationException.
	 */
	protected boolean matchPredicate(Object obj, String expr) {
		if (Util.isBlank(expr)) {
			return true;
		}
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(expr);
		EvaluationContext evaluationContext = getEvaluationContext();
		try {			
			return evaluationContext == null ? exp.getValue(obj, Boolean.class) : exp.getValue(evaluationContext, obj, Boolean.class);
		} catch (EvaluationException e) {
			return false;
		}
	}
	
	/**
	 * @return A stream of methods. Accessible methods for the introspection level ACCESSIBLE and declared methods from the class and all super classes and implemented interfaces for the introspection level DECLARED.
	 */
	protected static Stream<Method> getMethods(Class<?> clazz, IntrospectionLevel introspectionLevel) {
		if (clazz == null || introspectionLevel == null || introspectionLevel == IntrospectionLevel.NONE || Object.class.equals(clazz)) {
			return Stream.empty();
		}
		
		if (introspectionLevel == IntrospectionLevel.ACCESSIBLE) {
			return Arrays.stream(clazz.getMethods());
		}
		
		Stream<Method> declared = Arrays.stream(clazz.getDeclaredMethods());
		
		Class<?> superClass = clazz.getSuperclass();
		declared = Stream.concat(declared, getMethods(superClass, introspectionLevel));
		
		for (Class<?> implementedInterface: clazz.getInterfaces()) {
			declared = Stream.concat(declared, getMethods(implementedInterface, introspectionLevel));
			
		}
		return declared;
	}
	
	/**
	 * @return A stream of fields. Accessible fields for the introspection level ACCESSIBLE and declared fields from the class and all super classes and implemented interfaces for the introspection level DECLARED.
	 */
	protected static Stream<Field> getFields(Class<?> clazz, IntrospectionLevel introspectionLevel) {
		if (clazz == null || introspectionLevel == null || introspectionLevel == IntrospectionLevel.NONE || Object.class.equals(clazz)) {
			return Stream.empty();
		}
		
		if (introspectionLevel == IntrospectionLevel.ACCESSIBLE) {
			return Arrays.stream(clazz.getFields());
		}
		
		Stream<Field> declared = Arrays.stream(clazz.getDeclaredFields());
		
		Class<?> superClass = clazz.getSuperclass();
		declared = Stream.concat(declared, getFields(superClass, introspectionLevel));
		
		for (Class<?> implementedInterface: clazz.getInterfaces()) {
			declared = Stream.concat(declared, getFields(implementedInterface, introspectionLevel));
			
		}
		return declared;
	}
	
	protected static Stream<AccessibleObject> getFieldsAndMethods(Class<?> clazz, IntrospectionLevel introspectionLevel) {
		return Stream.concat(getMethods(clazz, introspectionLevel), getFields(clazz, introspectionLevel));
	}
	
	/**
	 * Retrieves field value. May need to be overridden if the target class is not exported by its module.
	 * @param target
	 * @param field
	 * @return
	 */
	protected Object getFieldValue(Object target, Field field) {
		boolean canAccess = field.canAccess(target);
		try {
			if (!canAccess) {
				field.setAccessible(true);
			}
			return  field.get(target);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new NasdanikaException("Cannot access field " + field + " of " + target + ": " + e, e);
		} finally {
			if (!canAccess) {
				field.setAccessible(false);
			}
		}	
	}

	/**
	 * Sets field value. May need to be overridden if the target class is not exported by its module.
	 * @param target
	 * @param field
	 * @param value
	 * @return
	 */
	protected void setFieldValue(Object target, Field field, Object value) {
		boolean canAccess = field.canAccess(target);
		try {
			if (!canAccess) {
				field.setAccessible(true);
			}
			field.set(target, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new NasdanikaException("Cannot access field " + field + " of " + target + ": " + e, e);
		} finally {
			if (!canAccess) {
				field.setAccessible(false);
			}
		}	
	}
	
	/**
	 * Invokes method. May need to be overridden if the target class is not exported by its module.
	 * @param target
	 * @param method
	 * @param args
	 * @return
	 */
	protected Object invokeMethod(Object target, Method method, Object... args) {
		boolean canAccess = method.canAccess(target);
		try {
			if (!canAccess) {
				method.setAccessible(true);
			}
			return method.invoke(target, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new NasdanikaException("Error invoking " + method + " of " + target + ": " + e, e);
		} finally {
			if (!canAccess) {
				method.setAccessible(false);
			}
		}		
	}
	
	protected boolean canSet(AnnotatedElement element, Class<?> type) {
		if (element instanceof Field) {
			return type == null || ((Field) element).getType().isAssignableFrom(type);
		}
		if (element instanceof Method) {
			Method method = (Method) element;
			return !Modifier.isAbstract(((Member) method).getModifiers()) && method.getParameterCount() == 1 && (type == null || method.getParameterTypes()[0].isAssignableFrom(type));
		}
		return false;
	}
	
	protected boolean mustSet(AnnotatedElement element, Class<?> type, String message) {
		if (canSet(element, type)) {
			return true;
		}
		throw new NasdanikaException(message);
	}
	
	protected void set(Object target, AnnotatedElement element, Object value) {
		if (element instanceof Field) {
			setFieldValue(target, (Field) element, value);
		} else if (element instanceof Method) {
			invokeMethod(target, (Method) element, value);
		} else {
			throw new IllegalArgumentException("Cannot set value into " + element);
		}
	}
	
	protected Object get(Object target, AnnotatedElement element) {
		if (element instanceof Field) {
			return getFieldValue(target, (Field) element);
		} 
		if (element instanceof Method) {
			return invokeMethod(target, (Method) element);
		}
		
		throw new IllegalArgumentException("Cannot get value from " + element);
	}
	
	protected boolean canGet(AnnotatedElement element, Class<?> type) {
		if (element instanceof Field) {
			return type == null || type.isAssignableFrom(((Field) element).getType());
		}
		if (element instanceof Method) {
			Method method = (Method) element;					
			return !Modifier.isAbstract(((Member) method).getModifiers()) && method.getParameterCount() == 0 && (type == null || type.isAssignableFrom(method.getReturnType()));
		}
		return false;
	}
	
	protected boolean mustGet(AnnotatedElement element, Class<?> type, String message) {
		if (canGet(element, type)) {
			return true;
		}
		throw new NasdanikaException(message);
	}

	protected boolean isValueSupplier(AnnotatedElement element) {
		return element instanceof Field || (element instanceof Method && ((Method) element).getParameterCount() == 0);
	}
	
	protected EvaluationContext getEvaluationContext() {
		return null;
	}
	
}
