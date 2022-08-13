package org.nasdanika.drawio.processor;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.text.WordUtils;
import org.jsoup.Jsoup;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;

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
			boolean canAccess = method.canAccess(target);
			try {
				if (!canAccess) {
					method.setAccessible(true);
				}
				Object processor;
				if (method.getParameterCount() == 0) {
						processor = method.invoke(target);
				} else {
					processor = method.invoke(target, config);						
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
					
					Map<Element, ElementProcessorInfo<P>> unwiredRegistryEntries = wireRegistryEntry(processor, config.getRegistry(), processorIntrospectionLevel);
					wireRegistry(processor, hideWired ? unwiredRegistryEntries : config.getRegistry(), processorIntrospectionLevel);
					
					ElementProcessorConfig<P> unwiredConfig;
					
					if (config instanceof NodeProcessorConfig) {
						@SuppressWarnings("unchecked")
						NodeProcessorConfig<P, H, E> nodeProcessorConfig = (NodeProcessorConfig<P, H, E>) config;
						Map<Connection, E> unwiredInboundEndpoints = wireInboundEndpoint(processor, nodeProcessorConfig.getInboundEndpoints(), processorIntrospectionLevel);
						wireInboundEndpoints(processor, hideWired ? unwiredInboundEndpoints : nodeProcessorConfig.getInboundEndpoints(), processorIntrospectionLevel);
						
						Map<Connection, Consumer<H>> unwiredInboundHandlerConsumers = wireInboundHandler(processor, nodeProcessorConfig.getInboundHandlerConsumers(), processorIntrospectionLevel);
						wireInboundHandlers(processor, hideWired ? unwiredInboundHandlerConsumers : nodeProcessorConfig.getInboundHandlerConsumers(), processorIntrospectionLevel);
						
						Map<Connection, E> unwiredOutboundEndpoints = wireOutboundEndpoint(processor, nodeProcessorConfig.getOutboundEndpoints(), processorIntrospectionLevel);
						wireOutboundEndpoints(processor, hideWired ? unwiredOutboundEndpoints : nodeProcessorConfig.getOutboundEndpoints(), processorIntrospectionLevel);
						
						Map<Connection, Consumer<H>> unwiredOutboundHandlerConsumers = wireOutboundHandler(processor, nodeProcessorConfig.getOutboundHandlerConsumers(), processorIntrospectionLevel);
						wireOutboundHandlers(processor, hideWired ? unwiredOutboundHandlerConsumers : nodeProcessorConfig.getOutboundHandlerConsumers(), processorIntrospectionLevel);
						
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
								return unwiredRegistryEntries;
							}

							@Override
							public Node getElement() {
								return (Node) config.getElement();
							}

							@Override
							public Map<Connection, E> getInboundEndpoints() {
								return unwiredInboundEndpoints;
							}

							@Override
							public Map<Connection, Consumer<H>> getInboundHandlerConsumers() {
								return unwiredInboundHandlerConsumers;
							}

							@Override
							public Map<Connection, E> getOutboundEndpoints() {
								return unwiredOutboundEndpoints;
							}

							@Override
							public Map<Connection, Consumer<H>> getOutboundHandlerConsumers() {
								return unwiredOutboundHandlerConsumers;
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
								return unwiredRegistryEntries;
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
								return unwiredRegistryEntries;
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
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new NasdanikaException("Error creating processor for " + config.getElement() + ": " + e, e);
			} finally {
				if (!canAccess) {
					method.setAccessible(false);
				}
			}
		}
		return ProcessorFactory.super.createProcessor(config, setParentProcessorInfoCallback); 
	}
	
	// Element wiring
	protected Map<Element, ElementProcessorInfo<P>> wireRegistryEntry(
			Object processor, 
			Map<Element, ElementProcessorInfo<P>> registry,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return registry;
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
	 * Matches processor field or method and inbound connection.
	 * @return
	 */
	protected boolean matchInboundHandler(AnnotatedElement handlerMember, Connection inboundConnection) {
		InboundHandler inboundHanlderAnnotation = handlerMember.getAnnotation(InboundHandler.class);
		if (inboundHanlderAnnotation == null) {
			return false;
		}
		
		if (handlerMember instanceof Method) {
			Method handlerMethod = (Method) handlerMember;
			int pc = handlerMethod.getParameterCount();
			if (pc > 1) {
				throw new NasdanikaException("A method annotated with InboundHandler shall have zero or one parameter: " + handlerMethod);
			}
			if (pc == 1 && !handlerMethod.getParameters()[0].getType().isInstance(inboundConnection)) {
				throw new NasdanikaException("A single parameter type of a method annotated with InboundHandler shall be assignable from Connection: " + handlerMethod);				
			}
		}
		
//		/**
//		 * If not blank value is used to match the inbound {@link Connection} label.
//		 * If blank and selector is not provided, then connection label is converted to camel case by 
//		 * lower casing the first word, upper casing the rest and concatenating them. E.g. "Hello world" would become "helloWorld".
//		 * The resulting string is used to match the annotated field. 
//		 * For methods all words are upper cased, concatenated and prefixed with get. E.g. "Hello world" would become "getHelloWorld".
//		 * If selector is set and value is blank then value is not used for matching.
//		 * @return
//		 */
//		String value() default "";
//			
//		/**
//		 * Inbound {@link Connection} selector in the form of <code>property=value pattern</code>.
//		 * Elements are concatenated with and. 
//		 * @return
//		 */
//		String[] selector() default {};
//		
//		/**
//		 * If not blank value is used to match the inbound {@link Connection}'s source {@link Node} label.
//		 * @return
//		 */
//		String source() default "";
//			
//		/**
//		 * Inbound {@link Connection}'s source {@link Node} selector in the form of <code>property=value pattern</code>.
//		 * Elements are concatenated with and. 
//		 * @return
//		 */
//		String[] sourceSelector() default {};
		
		return true;
	}
	
	// Node wiring
	@SuppressWarnings("unchecked")
	protected Map<Connection, Consumer<H>> wireInboundHandler(
			Object processor, 
			Map<Connection, Consumer<H>> inboundHandlerConsumers,
			IntrospectionLevel processorIntrospectionLevel) {
		
		Map<Connection, Consumer<H>> ret = new LinkedHashMap<>(inboundHandlerConsumers);

		// Streaming fields and methods and then flat mapping them to all permutations with inbound handler consumers.
		// then filtering using matchInboundHandler, sorting by priority, finding first, wiring it and removing from ret.
		getFieldsAndMethods(processor.getClass(), processorIntrospectionLevel)
			.flatMap(ae -> inboundHandlerConsumers.entrySet().stream().map(ihce -> Map.entry(ae, ihce)))
			.filter(e -> matchInboundHandler(e.getKey(), e.getValue().getKey()))
			.sorted((a, b) -> b.getKey().getAnnotation(InboundHandler.class).priority() - a.getKey().getAnnotation(InboundHandler.class).priority())
			.findFirst().ifPresent(e -> {
				AccessibleObject handlerMember = e.getKey();
				Entry<Connection, Consumer<H>> inboundHandlerConsumerEntry = e.getValue();
				boolean isAccessible = introspectionLevel == IntrospectionLevel.DECLARED ? handlerMember.canAccess(processor) : true;
				try {
					if (!isAccessible) {
						handlerMember.setAccessible(true);
					}
					if (handlerMember instanceof Field) {
						inboundHandlerConsumerEntry.getValue().accept((H) ((Field) handlerMember).get(processor));
					} else {
						Method handlerSupplierMethod = (Method) handlerMember;
						Object inboundHandler = handlerSupplierMethod.getParameterCount() == 0 ? handlerSupplierMethod.invoke(processor) : handlerSupplierMethod.invoke(processor, inboundHandlerConsumerEntry.getKey());
						inboundHandlerConsumerEntry.getValue().accept((H) inboundHandler);
					}
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
					throw new NasdanikaException("Error obtaining handler value: " + ex, ex);
				} finally {
					if (!isAccessible) {
						handlerMember.setAccessible(false);
					}
				}
				ret.remove(inboundHandlerConsumerEntry.getKey());
				
			});
				
		return ret;
	}	
	
	protected void wireInboundHandlers(
			Object processor, 
			Map<Connection, Consumer<H>> inboundHandlerConsumers,
			IntrospectionLevel processorIntrospectionLevel) {
		
		// TODO Auto-generated method stub

	}

	protected Map<Connection, E> wireInboundEndpoint(
			Object processor, 
			Map<Connection, E> inboundEndpoints,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return inboundEndpoints;
	}

	protected void wireInboundEndpoints(
			Object processor, 
			Map<Connection, E> inboundEndpoints,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}
	
	protected Map<Connection, Consumer<H>> wireOutboundHandler(
			Object processor, 
			Map<Connection,
			Consumer<H>> outboundHandlerConsumers,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return outboundHandlerConsumers;
	}
	
	protected void wireOutboundHandlers(
			Object processor,
			Map<Connection, Consumer<H>> outboundHandlerConsumers,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected Map<Connection, E> wireOutboundEndpoint(
			Object processor, 
			Map<Connection, E> outboundEndpoints,
			IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		return outboundEndpoints;
	}

	protected void wireOutboundEndpoints(
			Object processor, 
			Map<Connection, E> outboundEndpoints,
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
		
		if (method.getParameterCount() == 1 && !method.getParameters()[0].getType().isInstance(elementProcessorConfig)) {
			return false;
		}
		
		String value = elementProcessorAnnotation.value();		
		String[] selector = elementProcessorAnnotation.selector();
		
		String elementLabel = null;		
		if (element instanceof ModelElement) {
			elementLabel = ((ModelElement) element).getLabel(); 
		} else if (element instanceof Page) {
			elementLabel = ((Page) element).getName();
		}
		
		if (!Util.isBlank(elementLabel)) {
			elementLabel = Jsoup.parse(elementLabel).text();
			
			// Matching value
			if (Util.isBlank(value)) {
				if (selector.length == 0) {
					String methodName = labelToMethodName(elementLabel);
					if (!method.getName().equals(methodName)) {
						return false;
					}
				}
			} else if (!value.equals(elementLabel)) {
				return false;
			}
		}		
		
		// Matching selector
		for (String selectorElement: selector) {
			int equalsIdx = selectorElement.indexOf("=");
			if (equalsIdx == -1) {
				throw new IllegalArgumentException("Selector shall be in the form of <property>=<value pattern>. Method: " + method);				
			}
			if (element instanceof ModelElement) {
				ModelElement modelElement = (ModelElement) element;
				String property = selectorElement.substring(0, equalsIdx);
				String propertyValue = modelElement.getProperty(property);				
				String pattern = selectorElement.substring(equalsIdx + 1);
				if (Util.isBlank(pattern)) {
					if (!Util.isBlank(propertyValue)) {
						return false;
					}
				}
				
				if (!Pattern.matches(pattern, propertyValue)) {
					return false;
				}
				
			} else {
				return false;  
			}
		}
		
		return true;
	}
	
	protected String labelToMethodName(String label) {
		String[] la = label.split("\\s+");
		for (int i = 0; i < la.length; ++i) {
			la[i] = i== 0 ? WordUtils.uncapitalize(la[i]) : WordUtils.capitalize(la[i]);
		}
		return String.join("", la);
	}
	
	/**
	 * @return A stream of methods. Accessible methods for the introspection level ACCESSIBLE and declared methods from the class and all super classes and implemented interfaces for the introspection level DECLARED.
	 */
	protected static Stream<Method> getMethods(Class<?> clazz, IntrospectionLevel introspectionLevel) {
		if (introspectionLevel == null || introspectionLevel == IntrospectionLevel.NONE || Object.class.equals(clazz)) {
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
		if (introspectionLevel == null || introspectionLevel == IntrospectionLevel.NONE || Object.class.equals(clazz)) {
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
	
}
