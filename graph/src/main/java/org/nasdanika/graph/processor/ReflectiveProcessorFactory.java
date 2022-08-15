package org.nasdanika.graph.processor;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
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
	
	private Object target;
	private IntrospectionLevel introspectionLevel;

	public ReflectiveProcessorFactory(Object target, IntrospectionLevel introspectionLevel) {
		this.target = Objects.requireNonNull(target);
		this.introspectionLevel = introspectionLevel == null ? IntrospectionLevel.NONE : introspectionLevel;
	}
	
	public Map<Element, ElementProcessorInfo<P>> createProcessors(Element element, Object... registryTargets) {
		// TODO wire registry target if not null
		return ProcessorFactory.super.createProcessors(element);
	}
	
	@Override
	public ElementProcessorInfo<P> createProcessor(ElementProcessorConfig<P> config, Consumer<Consumer<ElementProcessorInfo<P>>> setParentProcessorInfoCallback) {
		if (target == null || introspectionLevel == IntrospectionLevel.NONE) {
			return ProcessorFactory.super.createProcessor(config, setParentProcessorInfoCallback);
		}
		Optional<Method> match = getMethods(target.getClass(), introspectionLevel)
			.filter(m -> matchFactoryMethod(config, m))
			.sorted((a, b) -> b.getAnnotation(ElementProcessor.class).priority() - a.getAnnotation(ElementProcessor.class).priority())
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
				ElementProcessor elementProcessorAnnotation = method.getAnnotation(ElementProcessor.class);
				IntrospectionLevel processorIntrospectionLevel = elementProcessorAnnotation.introspect();
				if (processorIntrospectionLevel == IntrospectionLevel.NONE) {
					return ProcessorFactory.super.createProcessor(config, setParentProcessorInfoCallback);
				}
				
				boolean hideWired = elementProcessorAnnotation.hideWired();
				Map<Element, ElementProcessorInfo<P>> unwiredChildProcessorsInfo = wireChildProcessor(processor, config.getChildProcessorsInfo(), processorIntrospectionLevel);
				wireChildProcessors(processor, hideWired ? unwiredChildProcessorsInfo : config.getChildProcessorsInfo(), processorIntrospectionLevel);
				
				wireParentProcessor(processor, setParentProcessorInfoCallback, processorIntrospectionLevel);
				wireProcessorElement(processor, config.getElement(), processorIntrospectionLevel);
				
				wireRegistryEntrySupplier(processor, config.getRegistry(), processorIntrospectionLevel);
				wireRegistry(processor, config.getRegistry(), processorIntrospectionLevel);
				
				ElementProcessorConfig<P> unwiredConfig;
				
				if (config instanceof NodeProcessorConfig) {
					@SuppressWarnings("unchecked")
					NodeProcessorConfig<P, H, E> nodeProcessorConfig = (NodeProcessorConfig<P, H, E>) config;
					Map<Connection, E> unwiredIncomingEndpoints = wireIncomingEndpoint(processor, nodeProcessorConfig.getIncomingEndpoints(), processorIntrospectionLevel);
					wireIncomingEndpoints(processor, hideWired ? unwiredIncomingEndpoints : nodeProcessorConfig.getIncomingEndpoints(), processorIntrospectionLevel);
					
					Map<Connection, Consumer<H>> unwiredIncomingHandlerConsumers = wireIncomingHandler(processor, nodeProcessorConfig.getIncomingHandlerConsumers(), processorIntrospectionLevel);
					wireIncomingHandlers(processor, hideWired ? unwiredIncomingHandlerConsumers : nodeProcessorConfig.getIncomingHandlerConsumers(), processorIntrospectionLevel);
					
					Map<Connection, E> unwiredOutgoingEndpoints = wireOutgoingEndpoint(processor, nodeProcessorConfig.getOutgoingEndpoints(), processorIntrospectionLevel);
					wireOutgoingEndpoints(processor, hideWired ? unwiredOutgoingEndpoints : nodeProcessorConfig.getOutgoingEndpoints(), processorIntrospectionLevel);
					
					Map<Connection, Consumer<H>> unwiredOutgoingHandlerConsumers = wireOutgoingHandler(processor, nodeProcessorConfig.getOutgoingHandlerConsumers(), processorIntrospectionLevel);
					wireOutgoingHandlers(processor, hideWired ? unwiredOutgoingHandlerConsumers : nodeProcessorConfig.getOutgoingHandlerConsumers(), processorIntrospectionLevel);
					
					unwiredConfig = new NodeProcessorConfig<P, H, E>() {

						@Override
						public Map<Element, ElementProcessorInfo<P>> getChildProcessorsInfo() {
							return unwiredChildProcessorsInfo;
						}

						@Override
						public ElementProcessorInfo<P> getParentProcessorInfo() {
							return config.getParentProcessorInfo();
						}

						@Override
						public Map<Element, ElementProcessorInfo<P>> getRegistry() {
							return config.getRegistry();
						}

						@Override
						public Node getElement() {
							return (Node) config.getElement();
						}

						@Override
						public Map<Connection, E> getIncomingEndpoints() {
							return unwiredIncomingEndpoints;
						}

						@Override
						public Map<Connection, Consumer<H>> getIncomingHandlerConsumers() {
							return unwiredIncomingHandlerConsumers;
						}

						@Override
						public Map<Connection, E> getOutgoingEndpoints() {
							return unwiredOutgoingEndpoints;
						}

						@Override
						public Map<Connection, Consumer<H>> getOutgoingHandlerConsumers() {
							return unwiredOutgoingHandlerConsumers;
						}
					};
				} else if (config instanceof ConnectionProcessorConfig) {
					@SuppressWarnings("unchecked")
					ConnectionProcessorConfig<P, H, E> connectionProcessorConfig = (ConnectionProcessorConfig<P, H, E>) config;
					boolean wiredSourceEndpoint = wireSourceEndpoint(processor, connectionProcessorConfig.getSourceEndpoint(), processorIntrospectionLevel);
					boolean wiredSourceHandler = wireSourceHandler(processor, connectionProcessorConfig, processorIntrospectionLevel);
					boolean wiredTargetEndpoint = wireTargetEndpoint(processor, connectionProcessorConfig.getTargetEndpoint(), processorIntrospectionLevel);
					boolean wiredTargetHandler = wireTargetHandler(processor, connectionProcessorConfig, processorIntrospectionLevel);
					
					unwiredConfig = new ConnectionProcessorConfig<P, H, E>() {

						@Override
						public Map<Element, ElementProcessorInfo<P>> getChildProcessorsInfo() {
							return unwiredChildProcessorsInfo;
						}

						@Override
						public ElementProcessorInfo<P> getParentProcessorInfo() {
							return config.getParentProcessorInfo();
						}

						@Override
						public Map<Element, ElementProcessorInfo<P>> getRegistry() {
							return config.getRegistry();
						}

						@Override
						public Connection getElement() {
							return (Connection) config.getElement();
						}

						@Override
						public E getSourceEndpoint() {
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
						public E getTargetEndpoint() {
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
					unwiredConfig = new ElementProcessorConfig<P>() {

						@Override
						public Map<Element, ElementProcessorInfo<P>> getChildProcessorsInfo() {
							return unwiredChildProcessorsInfo;
						}

						@Override
						public ElementProcessorInfo<P> getParentProcessorInfo() {
							return config.getParentProcessorInfo();
						}

						@Override
						public Map<Element, ElementProcessorInfo<P>> getRegistry() {
							return config.getRegistry();
						}

						@Override
						public Element getElement() {
							return config.getElement();
						}

					};
				}
				
				Object theProcessor = processor;
				return new ElementProcessorInfo<P>() {

					@Override
					public ElementProcessorConfig<P> getConfig() {
						return hideWired ? unwiredConfig : config;
					}

					@SuppressWarnings("unchecked")
					@Override
					public P getProcessor() {
						return (P) theProcessor;
					}
					
				};										
			}
		}
		return ProcessorFactory.super.createProcessor(config, setParentProcessorInfoCallback); 
	}
	
	/**
	 * Matches processor field to a registry entry.
	 * @return
	 */
	protected boolean matchRegistryEntry(AnnotatedElement registryEntryReceiver) {
		RegistryEntry registryEntryAnnotation = registryEntryReceiver.getAnnotation(RegistryEntry.class);
		if (registryEntryAnnotation == null) {
			return false;
		}
		
		if (registryEntryReceiver instanceof Method) {
			Method registryEntrySetterMethod = (Method) registryEntryReceiver;
			int pc = registryEntrySetterMethod.getParameterCount();
			if (pc == 1) {
				Class<?> parameterType = registryEntrySetterMethod.getParameterTypes()[0];
				if (!parameterType.isAssignableFrom(Supplier.class)) {
					throw new NasdanikaException("A method annotated with RegistryEntry shall have one parameter assignable from Supplier: " + registryEntrySetterMethod);
				}
			}
		} else {
			Class<?> fieldType = ((Field) registryEntryReceiver).getType();
			if (!fieldType.isAssignableFrom(Supplier.class)) {
				throw new NasdanikaException("A field annotated with RegistryEntry shall have type assignable from Supplier: " + registryEntryReceiver);
			}
		}

		return true;
	}
	
	// Registry wiring
	protected void wireRegistryEntrySupplier(
			Object processor, 
			Map<Element, ElementProcessorInfo<P>> registry,
			IntrospectionLevel processorIntrospectionLevel) {
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
			.filter(this::matchRegistryEntry)
			.forEach(ae -> {
				Supplier<Object> supplier = () -> {
					for (Entry<Element, ElementProcessorInfo<P>> re: registry.entrySet()) {
						RegistryEntry registryEntryAnnotation = ae.getAnnotation(RegistryEntry.class);
						if (matchPredicate(re.getKey(), registryEntryAnnotation.value())) {
							return registryEntryAnnotation.info() ? re.getValue() : re.getValue().getProcessor();
						};						
					}
					
					return null;
				};
		
				if (ae instanceof Field) {
					setFieldValue(processor, (Field) ae, supplier);
				} else {
					invokeMethod(processor, (Method) ae, supplier);
				}
			});
	}

	protected void wireRegistry(
			Object processor, 
			Map<Element, ElementProcessorInfo<P>> registry,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		
	}

	protected void wireProcessorElement(
			Object processor, 
			Element element,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		
	}

	protected void wireParentProcessor(
			Object processor, 
			Consumer<Consumer<ElementProcessorInfo<P>>> setParentProcessorInfoCallback,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		
	}

	protected void wireChildProcessors(
			Object processor, 
			Map<Element, ElementProcessorInfo<P>> childProcessorsInfo,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		
	}

	private Map<Element, ElementProcessorInfo<P>> wireChildProcessor(
			Object processor, 
			Map<Element, ElementProcessorInfo<P>> childProcessorsInfo,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return childProcessorsInfo;
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
	
	protected void wireIncomingHandlers(
			Object processor, 
			Map<Connection, Consumer<H>> incomingHandlerConsumers,
			IntrospectionLevel processorIntrospectionLevel) {
		
		// TODO Auto-generated method stub

	}

	protected Map<Connection, E> wireIncomingEndpoint(
			Object processor, 
			Map<Connection, E> incomingEndpoints,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return incomingEndpoints;
	}

	protected void wireIncomingEndpoints(
			Object processor, 
			Map<Connection, E> incomingEndpoints,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}
	
	protected Map<Connection, Consumer<H>> wireOutgoingHandler(
			Object processor, 
			Map<Connection,
			Consumer<H>> outgoingHandlerConsumers,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return outgoingHandlerConsumers;
	}
	
	protected void wireOutgoingHandlers(
			Object processor,
			Map<Connection, Consumer<H>> outgoingHandlerConsumers,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected Map<Connection, E> wireOutgoingEndpoint(
			Object processor, 
			Map<Connection, E> outgoingEndpoints,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return outgoingEndpoints;
	}

	protected void wireOutgoingEndpoints(
			Object processor, 
			Map<Connection, E> outgoingEndpoints,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	// Connection wiring
	protected boolean wireTargetHandler(
			Object processor, 
			ConnectionProcessorConfig<P, H, E> connectionProcessorConfig, 
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean wireTargetEndpoint(
			Object processor, 
			E targetEndpoint, 
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean wireSourceHandler(
			Object processor, 
			ConnectionProcessorConfig<P, H, E> connectionProcessorConfig,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean wireSourceEndpoint(
			Object processor, 
			E sourceEndpoint,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected boolean matchFactoryMethod(ElementProcessorConfig<P> elementProcessorConfig, Method method) {
		if (Modifier.isAbstract(method.getModifiers())) {
			return false;
		}
		
		ElementProcessor elementProcessorAnnotation = method.getAnnotation(ElementProcessor.class);
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
		try {
			return exp.getValue(obj, Boolean.class);
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
	
}
