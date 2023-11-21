package org.nasdanika.common;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Content mapper which uses annotations for wiring 
 * @param <S>
 * @param <T>
 */
public class ReflectiveContentMapper<S extends EObject, T extends EObject> extends ContentMapper<S, T> {
	
	/**
	 * Annotation for feature wiring methods. 
	 * Methods shall have the same parameters as {@link ContentMapper}.wireParentFeature().
	 * Methods are matched by annotation xxxType() values and by method parameter types.   
	 */
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface ParentFeature {
		
		/**
		 * Feature id
		 */
		int value();
		
		/**
		 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
		 * which is evaluated in the context of the source element with other method arguments as variables 
		 * @return
		 */
		String condition() default "";

		/**
		 * Matching by source type.
		 * @return
		 */
		Class<?> sourceType() default Object.class; 

		/**
		 * Matching by target type. 
		 * @return
		 */
		Class<?> targetType() default Object.class;
		
		/**
		 * Matching by child target type. 
		 * @return
		 */
		Class<?> childTargetType() default Object.class;
		
		/**
		 * Wire priority within the phase
		 * @return
		 */
		int priority() default 0;
		
	}	
	
	/**
	 * Annotation for feature wiring methods. 
	 * Methods shall have the same parameters as {@link ContentMapper}.wireChildFeature().
	 * Methods are matched by annotation xxxType() values and by method parameter types.   
	 */
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface ChildFeature {
		
		/**
		 * Feature id
		 */
		int value();
		
		/**
		 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
		 * which is evaluated in the context of the source element with other method arguments as variables 
		 * @return
		 */
		String condition() default "";

		/**
		 * Matching by source type.
		 * @return
		 */
		Class<?> sourceType() default Object.class; 

		/**
		 * Matching by target type. 
		 * @return
		 */
		Class<?> targetType() default Object.class;
		
		/**
		 * Matching by child target type. 
		 * @return
		 */
		Class<?> childTargetType() default Object.class;
		
		/**
		 * Wire priority within the phase
		 * @return
		 */
		int priority() default 0;
		
	}	
	
	protected class Dispatcher extends Reflector {

		protected List<AnnotatedElementRecord> parentFeatureWires = new ArrayList<>();
		protected List<AnnotatedElementRecord> childFeatureWires = new ArrayList<>();
		
		public Dispatcher(Object[] targets) {
			for (Object target: targets) {
				getAnnotatedElementRecords(target)
					.filter(ar -> ar.getAnnotation(ParentFeature.class) != null && ar.getAnnotatedElement() instanceof Method)
					.sorted((a,b) -> b.getAnnotation(ParentFeature.class).priority() - a.getAnnotation(ParentFeature.class).priority())
					.forEach(parentFeatureWires::add);
				
				getAnnotatedElementRecords(target)
					.filter(ar -> ar.getAnnotation(ChildFeature.class) != null && ar.getAnnotatedElement() instanceof Method)
					.sorted((a,b) -> b.getAnnotation(ChildFeature.class).priority() - a.getAnnotation(ChildFeature.class).priority())
					.forEach(childFeatureWires::add);
			}
		}
		
		protected boolean wireChildFeature(
				S source, 
				T target, 
				T childTarget, 
				EStructuralFeature childTargetFeature,
				LinkedList<EObject> sourcePath, 
				Map<S, T> registry, 
				ProgressMonitor progressMonitor) {
			
			List<Object> results = new ArrayList<>(); 			
			for (AnnotatedElementRecord ar: childFeatureWires) {
				ChildFeature rWire = ar.getAnnotation(ChildFeature.class);
				Class<?>[] parameterTypes = ((Method) ar.getAnnotatedElement()).getParameterTypes();
				if (rWire.sourceType().isInstance(source) 
						&& parameterTypes[0].isInstance(source)
						&& rWire.targetType().isInstance(target) 
						&& parameterTypes[1].isInstance(target)
						&& rWire.value() == childTargetFeature.getFeatureID()
						&& rWire.childTargetType().isInstance(childTarget) 
						&& parameterTypes[2].isInstance(childTarget)) {
					
					Map<String, Object> variables = new HashMap<>();
					variables.put("target", target);
					variables.put("feature", childTargetFeature);
					variables.put("childTarget", childTarget);
					variables.put("sourcePath", sourcePath);
					variables.put("registry", registry);
					if (evaluatePredicate(source, rWire.condition(), variables)) {
						results.add(ar.invoke(source, target, childTarget, childTargetFeature, sourcePath, registry, progressMonitor));
					}					
				}				
			}			
			
			return !Boolean.FALSE.equals(
					results
						.stream()
						.reduce((a, b) -> Boolean.FALSE.equals(a) && Boolean.FALSE.equals(b) ? false : true)
						.orElse(true));
		}
		
		protected boolean wireParentFeature(
				S source, 
				T target, 
				EStructuralFeature targetFeature, 
				T childTarget,
				LinkedList<EObject> sourcePath, 
				Map<S, T> registry, 
				ProgressMonitor progressMonitor) {
			
			List<Object> results = new ArrayList<>(); 			
			for (AnnotatedElementRecord ar: parentFeatureWires) {
				ParentFeature rWire = ar.getAnnotation(ParentFeature.class);
				Class<?>[] parameterTypes = ((Method) ar.getAnnotatedElement()).getParameterTypes();
				if (rWire.sourceType().isInstance(source) 
						&& parameterTypes[0].isInstance(source)
						&& rWire.targetType().isInstance(target) 
						&& parameterTypes[1].isInstance(target)
						&& rWire.value() == targetFeature.getFeatureID()
						&& rWire.childTargetType().isInstance(childTarget) 
						&& parameterTypes[3].isInstance(childTarget)) {
					
					Map<String, Object> variables = new HashMap<>();
					variables.put("target", target);
					variables.put("feature", targetFeature);
					variables.put("childTarget", childTarget);
					variables.put("sourcePath", sourcePath);
					variables.put("registry", registry);
					if (evaluatePredicate(source, rWire.condition(), variables)) {
						results.add(ar.invoke(source, target, targetFeature, childTarget, sourcePath, registry, progressMonitor));
					}					
				}				
			}			
			
			return !Boolean.FALSE.equals(
					results
						.stream()
						.reduce((a, b) -> Boolean.FALSE.equals(a) && Boolean.FALSE.equals(b) ? false : true)
						.orElse(true));
		}
	}
	
	protected Dispatcher dispatcher;
	
	public ReflectiveContentMapper(Object... targets) {
		dispatcher = new Dispatcher(targets);
	}
	
	@Override
	protected boolean wireContentsFeature(
			S source, 
			T target, 
			T childTarget, 
			EStructuralFeature childTargetFeature,
			LinkedList<EObject> sourcePath, 
			Map<S, T> registry, 
			ProgressMonitor progressMonitor) {
		return dispatcher.wireChildFeature(source, target, childTarget, childTargetFeature, sourcePath, registry, progressMonitor);
	}
	
	@Override
	protected boolean wireContainerFeature(
			S source, 
			T target, 
			EStructuralFeature targetFeature, 
			T childTarget,
			LinkedList<EObject> sourcePath, 
			Map<S, T> registry, 
			ProgressMonitor progressMonitor) {
		return dispatcher.wireParentFeature(source, target, targetFeature, childTarget, sourcePath, registry, progressMonitor);
	}
		
}