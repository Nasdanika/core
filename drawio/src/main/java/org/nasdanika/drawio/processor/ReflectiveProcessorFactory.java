package org.nasdanika.drawio.processor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
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
public abstract class ReflectiveProcessorFactory<P, T, R, U, S> implements ProcessorFactory<P, T, R, U, S> {
	
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
					Map<Element, ElementProcessorInfo<P>> unwiredChildProcessorsInfo = wireChildProcessor(config.getChildProcessorsInfo(), processorIntrospectionLevel);
					wireChildProcessors(hideWired ? unwiredChildProcessorsInfo : config.getChildProcessorsInfo(), processorIntrospectionLevel);
					
					wireParentProcessor(setParentProcessorInfoCallback, processorIntrospectionLevel);
					wireProcessorElement(config.getElement(), processorIntrospectionLevel);
					
					Map<Element, ElementProcessorInfo<P>> unwiredRegistryEntries = wireRegistryEntry(config.getRegistry(), processorIntrospectionLevel);
					wireRegistry(hideWired ? unwiredRegistryEntries : config.getRegistry(), processorIntrospectionLevel);
					
					ElementProcessorConfig<P> unwiredConfig;
					
					if (config instanceof NodeProcessorConfig) {
						@SuppressWarnings("unchecked")
						NodeProcessorConfig<P, T, R, U, S> nodeProcessorConfig = (NodeProcessorConfig<P, T, R, U, S>) config;
						Map<Connection, Function<T, R>> unwiredInboundEndpoints = wireInboundEndpoint(nodeProcessorConfig.getInboundEndpoints(), processorIntrospectionLevel);
						wireInboundEndpoints(hideWired ? unwiredInboundEndpoints : nodeProcessorConfig.getInboundEndpoints(), processorIntrospectionLevel);
						
						Map<Connection, Consumer<Function<U, S>>> unwiredInboundHandlerConsumers = wireInboundHandler(nodeProcessorConfig.getInboundHandlerConsumers(), processorIntrospectionLevel);
						wireInboundHandlers(hideWired ? unwiredInboundHandlerConsumers : nodeProcessorConfig.getInboundHandlerConsumers(), processorIntrospectionLevel);
						
						Map<Connection, Function<T, R>> unwiredOutboundEndpoints = wireOutboundEndpoint(nodeProcessorConfig.getOutboundEndpoints(), processorIntrospectionLevel);
						wireOutboundEndpoints(hideWired ? unwiredOutboundEndpoints : nodeProcessorConfig.getOutboundEndpoints(), processorIntrospectionLevel);
						
						Map<Connection, Consumer<Function<U, S>>> unwiredOutboundHandlerConsumers = wireOutboundHandler(nodeProcessorConfig.getOutboundHandlerConsumers(), processorIntrospectionLevel);
						wireOutboundHandlers(hideWired ? unwiredOutboundHandlerConsumers : nodeProcessorConfig.getOutboundHandlerConsumers(), processorIntrospectionLevel);
						
						unwiredConfig = new NodeProcessorConfig<P, T, R, U, S>() {

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
							public Map<Connection, Function<T, R>> getInboundEndpoints() {
								return unwiredInboundEndpoints;
							}

							@Override
							public Map<Connection, Consumer<Function<U, S>>> getInboundHandlerConsumers() {
								return unwiredInboundHandlerConsumers;
							}

							@Override
							public Map<Connection, Function<T, R>> getOutboundEndpoints() {
								return unwiredOutboundEndpoints;
							}

							@Override
							public Map<Connection, Consumer<Function<U, S>>> getOutboundHandlerConsumers() {
								return unwiredOutboundHandlerConsumers;
							}
						};
					} else if (config instanceof ConnectionProcessorConfig) {
						@SuppressWarnings("unchecked")
						ConnectionProcessorConfig<P, T, R, U, S> connectionProcessorConfig = (ConnectionProcessorConfig<P, T, R, U, S>) config;
						boolean wiredSourceEndpoint = wireSourceEndpoint(connectionProcessorConfig.getSourceEndpoint(), processorIntrospectionLevel);
						boolean wiredSourceHandler = wireSourceHandler(connectionProcessorConfig, processorIntrospectionLevel);
						boolean wiredTargetEndpoint = wireTargetEndpoint(connectionProcessorConfig.getTargetEndpoint(), processorIntrospectionLevel);
						boolean wiredTargetHandler = wireTargetHandler(connectionProcessorConfig, processorIntrospectionLevel);
						
						unwiredConfig = new ConnectionProcessorConfig<P, T, R, U, S>() {

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
							public Function<T, R> getSourceEndpoint() {
								return wiredSourceEndpoint ? null : connectionProcessorConfig.getSourceEndpoint();
							}

							@Override
							public void setSourceHandler(Function<U, S> sourceHandler) {
								if (wiredSourceHandler) {
									throw new IllegalStateException("Source handler is already wired for " + getElement());
								}
								connectionProcessorConfig.setSourceHandler(sourceHandler);
							}

							@Override
							public Function<T, R> getTargetEndpoint() {
								return wiredTargetEndpoint ? null : connectionProcessorConfig.getTargetEndpoint();
							}

							@Override
							public void setTargetHandler(Function<U, S> targetHandler) {
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
	
	protected boolean wireTargetHandler(ConnectionProcessorConfig<P, T, R, U, S> connectionProcessorConfig, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected boolean wireTargetEndpoint(Function<T, R> targetEndpoint, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected boolean wireSourceHandler(ConnectionProcessorConfig<P, T, R, U, S> connectionProcessorConfig, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected boolean wireSourceEndpoint(Function<T, R> sourceEndpoint, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected void wireOutboundHandlers(Map<Connection, Consumer<Function<U, S>>> outboundHandlerConsumers,	IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected Map<Connection, Consumer<Function<U, S>>> wireOutboundHandler(Map<Connection, Consumer<Function<U, S>>> outboundHandlerConsumers, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected void wireOutboundEndpoints(Map<Connection, Function<T, R>> outboundEndpoints, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected Map<Connection, Function<T, R>> wireOutboundEndpoint(Map<Connection, Function<T, R>> outboundEndpoints, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected void wireInboundHandlers(Map<Connection, Consumer<Function<U, S>>> inboundHandlerConsumers, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected Map<Connection, Consumer<Function<U, S>>> wireInboundHandler(Map<Connection, Consumer<Function<U, S>>> inboundHandlerConsumers, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected void wireInboundEndpoints(Map<Connection, Function<T, R>> inboundEndpoints, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub

	}

	protected Map<Connection, Function<T, R>> wireInboundEndpoint(Map<Connection, Function<T, R>> inboundEndpoints, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		
	}

	protected Map<Element, ElementProcessorInfo<P>> wireRegistryEntry(Map<Element, ElementProcessorInfo<P>> registry, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		
	}

	protected void wireRegistry(Map<Element, ElementProcessorInfo<P>> registry,	IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		
	}

	protected void wireProcessorElement(Element element, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		
	}

	protected void wireParentProcessor(Consumer<Consumer<ElementProcessorInfo<P>>> setParentProcessorInfoCallback, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		
	}

	protected void wireChildProcessors(Map<Element, ElementProcessorInfo<P>> childProcessorsInfo, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		
	}

	private Map<Element, ElementProcessorInfo<P>> wireChildProcessor(Map<Element, ElementProcessorInfo<P>> childProcessorsInfo, IntrospectionLevel processorIntrospectionLevel) {
		// TODO Auto-generated method stub
		
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
	
}
