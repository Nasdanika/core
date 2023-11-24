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
public class ReflectiveContentMapper<S extends EObject, T extends EObject> extends FeatureMapper<S, T> {
	
	/**
	 * Annotation for feature wiring methods. 
	 * Methods shall have the same parameters as {@link FeatureMapper}.wireParentFeature().
	 * Methods are matched by annotation xxxType() values and by method parameter types.   
	 */
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface ContainerFeature {
		
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
	 * Methods shall have the same parameters as {@link FeatureMapper}.wireChildFeature().
	 * Methods are matched by annotation xxxType() values and by method parameter types.   
	 */
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface ContentsdFeature {
		
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
					.filter(ar -> ar.getAnnotation(ContainerFeature.class) != null && ar.getAnnotatedElement() instanceof Method)
					.sorted((a,b) -> b.getAnnotation(ContainerFeature.class).priority() - a.getAnnotation(ContainerFeature.class).priority())
					.forEach(parentFeatureWires::add);
				
				getAnnotatedElementRecords(target)
					.filter(ar -> ar.getAnnotation(ContentsdFeature.class) != null && ar.getAnnotatedElement() instanceof Method)
					.sorted((a,b) -> b.getAnnotation(ContentsdFeature.class).priority() - a.getAnnotation(ContentsdFeature.class).priority())
					.forEach(childFeatureWires::add);
			}
		}
		
		protected boolean wireContentsFeature(
				S contents, 
				T contentsValue, 
				EStructuralFeature contentsValueFeature,
				S container, 
				T containerValue, 
				LinkedList<EObject> sourcePath, 
				Map<S, T> registry,
				ProgressMonitor progressMonitor) {
			
//			List<Object> results = new ArrayList<>(); 			
//			for (AnnotatedElementRecord ar: childFeatureWires) {
//				ContentsdFeature rWire = ar.getAnnotation(ContentsdFeature.class);
//				Class<?>[] parameterTypes = ((Method) ar.getAnnotatedElement()).getParameterTypes();
//				if (rWire.sourceType().isInstance(source) 
//						&& parameterTypes[0].isInstance(source)
//						&& rWire.targetType().isInstance(target) 
//						&& parameterTypes[1].isInstance(target)
//						&& rWire.value() == childTargetFeature.getFeatureID()
//						&& rWire.childTargetType().isInstance(childTarget) 
//						&& parameterTypes[2].isInstance(childTarget)) {
//					
//					Map<String, Object> variables = new HashMap<>();
//					variables.put("target", target);
//					variables.put("feature", childTargetFeature);
//					variables.put("childTarget", childTarget);
//					variables.put("sourcePath", sourcePath);
//					variables.put("registry", registry);
//					if (evaluatePredicate(source, rWire.condition(), variables)) {
//						results.add(ar.invoke(source, target, childTarget, childTargetFeature, sourcePath, registry, progressMonitor));
//					}					
//				}				
//			}			
//			
//			return !Boolean.FALSE.equals(
//					results
//						.stream()
//						.reduce((a, b) -> Boolean.FALSE.equals(a) && Boolean.FALSE.equals(b) ? false : true)
//						.orElse(true));
			throw new UnsupportedOperationException();
		}
		
		protected boolean wireContainerFeature(
				S container, 
				T containerValue, 
				EStructuralFeature containerValueFeature,
				S contents, 
				T contentsValue, 
				LinkedList<EObject> sourcePath, 
				Map<S, T> registry,
				ProgressMonitor progressMonitor) {
			
//			List<Object> results = new ArrayList<>(); 			
//			for (AnnotatedElementRecord ar: parentFeatureWires) {
//				ContainerFeature rWire = ar.getAnnotation(ContainerFeature.class);
//				Class<?>[] parameterTypes = ((Method) ar.getAnnotatedElement()).getParameterTypes();
//				if (rWire.sourceType().isInstance(source) 
//						&& parameterTypes[0].isInstance(source)
//						&& rWire.targetType().isInstance(target) 
//						&& parameterTypes[1].isInstance(target)
//						&& rWire.value() == targetFeature.getFeatureID()
//						&& rWire.childTargetType().isInstance(childTarget) 
//						&& parameterTypes[3].isInstance(childTarget)) {
//					
//					Map<String, Object> variables = new HashMap<>();
//					variables.put("target", target);
//					variables.put("feature", targetFeature);
//					variables.put("childTarget", childTarget);
//					variables.put("sourcePath", sourcePath);
//					variables.put("registry", registry);
//					if (evaluatePredicate(source, rWire.condition(), variables)) {
//						results.add(ar.invoke(source, target, targetFeature, childTarget, sourcePath, registry, progressMonitor));
//					}					
//				}				
//			}			
//			
//			return !Boolean.FALSE.equals(
//					results
//						.stream()
//						.reduce((a, b) -> Boolean.FALSE.equals(a) && Boolean.FALSE.equals(b) ? false : true)
//						.orElse(true));
			throw new UnsupportedOperationException();
		}
	}
	
	protected Dispatcher dispatcher;
	
	public ReflectiveContentMapper(Object... targets) {
		dispatcher = new Dispatcher(targets);
	}

	@Override
	protected S getConnectionSource(S connection) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected S getConnectionTarget(S connection) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean wireContainerFeature(
			S container, 
			T containerValue, 
			EStructuralFeature containerValueFeature,
			S contents, 
			T contentsValue, 
			LinkedList<EObject> sourcePath, 
			Map<S, T> registry,
			ProgressMonitor progressMonitor) {

		return dispatcher.wireContainerFeature(
				container, 
				containerValue, 
				containerValueFeature, 
				contents, 
				contentsValue, 
				sourcePath, 
				registry, 
				progressMonitor);
	}

	@Override
	protected boolean wireContentsFeature(
			S contents, 
			T contentsValue, 
			EStructuralFeature contentsValueFeature,
			S container, 
			T containerValue, 
			LinkedList<EObject> sourcePath, 
			Map<S, T> registry,
			ProgressMonitor progressMonitor) {

		return dispatcher.wireContentsFeature(
				contents, 
				contentsValue, 
				contentsValueFeature, 
				container, 
				containerValue, 
				sourcePath, 
				registry, 
				progressMonitor);
	}

	@Override
	protected void wireConnectionSourceFeature(
			S connection, 
			S connectionSource, 
			T connectionSourceValue,
			EStructuralFeature connectionSourceValueFeature, 
			S argument,
			T argumentValue,
			Map<S, T> registry,
			ProgressMonitor progressMonitor) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void wireConnectionTargetFeature(
			S connection, 
			S connectionTarget, 
			T connectionTargetValue,
			EStructuralFeature connectionTargetValueFeature, 
			S argument,
			T argumentValue,
			Map<S, T> registry,
			ProgressMonitor progressMonitor) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void wireConnectionStartFeature(
			S connection, 
			T connectionValue,
			EStructuralFeature connectionValueFeature, 
			S connectionSource, 
			T connectionSourceValue, 
			Map<S, T> registry,
			ProgressMonitor progressMonitor) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void wireConnectionEndFeature(
			S connection, 
			T connectionValue, 
			EStructuralFeature connectionValueFeature,
			S connectionTarget, 
			T connectionTargetValue, 
			Map<S, T> registry, 
			ProgressMonitor progressMonitor) {
		throw new UnsupportedOperationException();
	}
		
}