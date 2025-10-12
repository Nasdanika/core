package org.nasdanika.mapping;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;

/**
 * 
 * @param <S>
 * @param <T>
 */
public class ReflectiveContributor<S, T extends EObject> extends Reflector implements AbstractMappingFactory.Contributor<S, T> {
		
	private List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();
		
	public ReflectiveContributor(Collection<Object> targets) {
		for (Object target: targets) {
			getAnnotatedElementRecords(target, Collections.emptyList()).forEach(annotatedElementRecords::add);
		}
	}	

	@Override
	public boolean canHandle(Object source, EObject target) {
		boolean hasConfigurators = annotatedElementRecords
				.stream()
				.filter(aer -> aer.test(source) && aer.getAnnotatedElement() instanceof Method && matchConfiguratorMethod(source, target, (Method) aer.getAnnotatedElement()))
				.findAny()
				.isPresent();
		
		if (hasConfigurators) {
			return true;
		}
		return annotatedElementRecords
				.stream()
				.filter(aer -> aer.test(source) && aer.getAnnotatedElement() instanceof Method && matchInitializerMethod(source, target, (Method) aer.getAnnotatedElement()))
				.findAny()
				.isPresent();
	}
	
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface Configurator {
		
		/**
		 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
		 * which is evaluated in the context of the source with target as <code>target</code> variable. 
		 * @return
		 */
		String value() default "";

		/**
		 * Matching by source type.
		 * @return
		 */
		Class<?> sourceType() default Object.class;
		
		/**
		 * Matching by target type.
		 * @return
		 */
		Class<?> targetType() default EObject.class; 		
		
		/**
		 * Factory priority
		 * @return
		 */
		int priority() default 0;

	}
		
	protected boolean matchConfiguratorMethod(Object source, EObject target, Method method) {
		if (Modifier.isAbstract(method.getModifiers()) || method.getParameterCount() < 2) {
			return false;
		}
		
		Configurator elementConfiguratorAnnotation = method.getAnnotation(Configurator.class);
		if (elementConfiguratorAnnotation == null) {
			return false;
		}
		
		Class<?> sourceType = elementConfiguratorAnnotation.sourceType();
		if (sourceType.isAssignableFrom(method.getParameterTypes()[0])) {
			sourceType = method.getParameterTypes()[0];
		}
		if (!sourceType.isInstance(source)) {
			return false;
		}
		
		Class<?> targetType = elementConfiguratorAnnotation.targetType();
		if (targetType.isAssignableFrom(method.getParameterTypes()[1])) {
			targetType = method.getParameterTypes()[1];
		}
		if (!targetType.isInstance(target)) {
			return false;
		}
		
		return evaluatePredicate(source, elementConfiguratorAnnotation.value(), Map.of("target", target));
	}
	
	protected boolean matchInitializerMethod(Object source, EObject target, Method method) {
		if (Modifier.isAbstract(method.getModifiers()) || method.getParameterCount() < 2) {
			return false;
		}
		
		Initializer elementConfiguratorAnnotation = method.getAnnotation(Initializer.class);
		if (elementConfiguratorAnnotation == null) {
			return false;
		}
		
		Class<?> sourceType = elementConfiguratorAnnotation.sourceType();
		if (sourceType.isAssignableFrom(method.getParameterTypes()[0])) {
			sourceType = method.getParameterTypes()[0];
		}
		if (!sourceType.isInstance(source)) {
			return false;
		}
		
		Class<?> targetType = elementConfiguratorAnnotation.targetType();
		if (targetType.isAssignableFrom(method.getParameterTypes()[1])) {
			targetType = method.getParameterTypes()[1];
		}
		if (!targetType.isInstance(target)) {
			return false;
		}
		
		return evaluatePredicate(source, elementConfiguratorAnnotation.value(), Map.of("target", target));
	}
		
	protected int compareConfiguratorMethods(Method a, Method b) {
		int priorityCmp = b.getAnnotation(Configurator.class).priority() - a.getAnnotation(Configurator.class).priority();
		if (priorityCmp != 0) {
			return priorityCmp;
		}
		
		Class<?> asType = a.getAnnotation(Configurator.class).sourceType();
		Class<?> bsType = b.getAnnotation(Configurator.class).sourceType();
		if (!Objects.equals(asType, bsType)) {
			if (asType.isAssignableFrom(bsType)) {
				// b is more specific
				return 1;
			}
			if (bsType.isAssignableFrom(asType)) {
				// a is more specific
				return -1;
			}
		}
		
		Class<?> atType = a.getAnnotation(Configurator.class).targetType();
		Class<?> btType = b.getAnnotation(Configurator.class).targetType();
		if (!Objects.equals(atType, btType)) {
			if (atType.isAssignableFrom(btType)) {
				// b is more specific
				return 1;
			}
			if (btType.isAssignableFrom(atType)) {
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
		
	protected int compareInitializerMethods(Method a, Method b) {
		int priorityCmp = b.getAnnotation(Initializer.class).priority() - a.getAnnotation(Initializer.class).priority();
		if (priorityCmp != 0) {
			return priorityCmp;
		}
		
		Class<?> asType = a.getAnnotation(Initializer.class).sourceType();
		Class<?> bsType = b.getAnnotation(Initializer.class).sourceType();
		if (!Objects.equals(asType, bsType)) {
			if (asType.isAssignableFrom(bsType)) {
				// b is more specific
				return 1;
			}
			if (bsType.isAssignableFrom(asType)) {
				// a is more specific
				return -1;
			}
		}
		
		Class<?> atType = a.getAnnotation(Initializer.class).targetType();
		Class<?> btType = b.getAnnotation(Initializer.class).targetType();
		if (!Objects.equals(atType, btType)) {
			if (atType.isAssignableFrom(btType)) {
				// b is more specific
				return 1;
			}
			if (btType.isAssignableFrom(atType)) {
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
	
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface Initializer {
		
		/**
		 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
		 * which is evaluated in the context of the source with target as <code>target</code> variable. 
		 * @return
		 */
		String value() default "";

		/**
		 * Matching by source type.
		 * @return
		 */
		Class<?> sourceType() default Object.class;
		
		/**
		 * Matching by target type.
		 * @return
		 */
		Class<?> targetType() default EObject.class; 		
		
		/**
		 * Factory priority
		 * @return
		 */
		int priority() default 0;

	}
	
	
	@Override
	public void initialize(
			S source, 
			T target, 
			BiConsumer<EObject, BiConsumer<EObject, ProgressMonitor>> elementProvider,
			Consumer<BiConsumer<Map<EObject, EObject>, ProgressMonitor>> registry, 
			ProgressMonitor progressMonitor) {
		
		List<AnnotatedElementRecord> initializers = annotatedElementRecords
				.stream()
				.filter(aer -> aer.test(source) && aer.getAnnotatedElement() instanceof Method && matchInitializerMethod(source, target, (Method) aer.getAnnotatedElement()))
				.sorted((a, b) -> compareInitializerMethods((Method) a.getAnnotatedElement(), (Method) b.getAnnotatedElement()))				
				.toList();
		
		for (AnnotatedElementRecord initializer: initializers) {
			Method method = (Method) initializer.getAnnotatedElement();
			Object[] args = new Object[method.getParameterCount()];
			for (int i = 0; i < args.length; ++i) {
				args[i] = switch (i) {
				case 0 -> source;
				case 1 -> target;
				case 2 -> elementProvider;
				case 3 -> registry;
				case 4 -> progressMonitor;
				default -> throw new IllegalArgumentException("More than 5 parameters in " + method);
				};
			}
			initializer.invoke(args);
		}
	}
	
	@Override
	public void configure(
			S source, 
			Collection<EObject> documentation, 
			T target, 
			Map<S, T> registry, 
			boolean isPrototype,
			ProgressMonitor progressMonitor) {
		
		List<AnnotatedElementRecord> configurators = annotatedElementRecords
				.stream()
				.filter(aer -> aer.test(source) && aer.getAnnotatedElement() instanceof Method && matchConfiguratorMethod(source, target, (Method) aer.getAnnotatedElement()))
				.sorted((a, b) -> compareConfiguratorMethods((Method) a.getAnnotatedElement(), (Method) b.getAnnotatedElement()))
				.toList();
		
		for (AnnotatedElementRecord configurator: configurators) {
			Method method = (Method) configurator.getAnnotatedElement();
			Object[] args = new Object[method.getParameterCount()];
			for (int i = 0; i < args.length; ++i) {
				args[i] = switch (i) {
				case 0 -> source;
				case 1 -> target;
				case 2 -> documentation;
				case 3 -> registry;
				case 4 -> isPrototype;
				case 5 -> progressMonitor;
				default -> throw new IllegalArgumentException("More than 6 parameters in " + method);
				};
			}
			configurator.invoke(args);
		}
	}
	

}
