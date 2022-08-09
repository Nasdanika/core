package org.nasdanika.drawio.processor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.text.WordUtils;
import org.jsoup.Jsoup;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ModelElement;
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
					IntrospectionLevel processorIntrospectionLevel = method.getAnnotation(ElementProcessor.class).introspect();
					if (processorIntrospectionLevel == IntrospectionLevel.NONE) {
						return ProcessorFactory.super.createProcessor(config, setParentProcessorInfoCallback);
					}
					
					throw new UnsupportedOperationException("Work in progress");
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
	 * @return A stream of methods. Accessible methods for introspection level ACCESSIBLE and declared methods from the class and all super classes and implemented interfaces.
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
	
}
