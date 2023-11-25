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
public class ReflectiveFeatureMapper<S extends EObject, T extends EObject> extends FeatureMapper<S, T> {
	
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
		 * Matching by container source type.
		 * @return
		 */
		Class<?> containerType() default Object.class; 

		/**
		 * Matching by container value (target) type. 
		 * @return
		 */
		Class<?> containerValueType() default Object.class;
		
		/**
		 * Matching by contents (source) type. 
		 * @return
		 */
		Class<?> contentsType() default Object.class;
				
		/**
		 * Matching by contents value (target) type. 
		 * @return
		 */
		Class<?> contentsValueType() default Object.class;		
		
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
	public @interface ContentsFeature {
		
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
		 * Matching by container source type.
		 * @return
		 */
		Class<?> containerType() default Object.class; 

		/**
		 * Matching by container value (target) type. 
		 * @return
		 */
		Class<?> containerValueType() default Object.class;
		
		/**
		 * Matching by contents (source) type. 
		 * @return
		 */
		Class<?> contentsType() default Object.class;
				
		/**
		 * Matching by contents value (target) type. 
		 * @return
		 */
		Class<?> contentsValueType() default Object.class;		
				
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
	public @interface ConnectionStartFeature {
		
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
		 * Matching by connection (source) type.
		 * @return
		 */
		Class<?> connectionType() default Object.class; 

		/**
		 * Matching by connection value (target) type. 
		 * @return
		 */
		Class<?> connectionValueType() default Object.class;
		
		/**
		 * Matching by connection source type. 
		 * @return
		 */
		Class<?> sourceType() default Object.class;
				
		/**
		 * Matching by connection source value (target) type. 
		 * @return
		 */
		Class<?> sourceValueType() default Object.class;		
		
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
	public @interface ConnectionEndFeature {
		
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
		 * Matching by connection (source) type.
		 * @return
		 */
		Class<?> connectionType() default Object.class; 

		/**
		 * Matching by connection value (target) type. 
		 * @return
		 */
		Class<?> connectionValueType() default Object.class;
		
		/**
		 * Matching by connection source type. 
		 * @return
		 */
		Class<?> targetType() default Object.class;
				
		/**
		 * Matching by connection source value (target) type. 
		 * @return
		 */
		Class<?> targetValueType() default Object.class;		
		
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
	public @interface ConnectionSourceFeature {
		
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
		 * Matching by connection (source) type.
		 * @return
		 */
		Class<?> connectionType() default Object.class; 

		/**
		 * Matching by connection value (target) type. 
		 * @return
		 */
		Class<?> connectionValueType() default Object.class;
		
		/**
		 * Matching by connection source type. 
		 * @return
		 */
		Class<?> sourceType() default Object.class;
				
		/**
		 * Matching by connection source value (target) type. 
		 * @return
		 */
		Class<?> sourceValueType() default Object.class;		
		
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
	public @interface ConnectionTargetFeature {
		
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
		 * Matching by connection (source) type.
		 * @return
		 */
		Class<?> connectionType() default Object.class; 

		/**
		 * Matching by connection value (target) type. 
		 * @return
		 */
		Class<?> connectionValueType() default Object.class;
		
		/**
		 * Matching by connection source type. 
		 * @return
		 */
		Class<?> targetType() default Object.class;
				
		/**
		 * Matching by connection source value (target) type. 
		 * @return
		 */
		Class<?> targetValueType() default Object.class;		
		
		/**
		 * Wire priority within the phase
		 * @return
		 */
		int priority() default 0;
		
	}			
	
	protected class Dispatcher extends Reflector {

		protected List<AnnotatedElementRecord> containerFeatureWires = new ArrayList<>();
		protected List<AnnotatedElementRecord> contentsFeatureWires = new ArrayList<>();
		protected List<AnnotatedElementRecord> connectionSourceFeatureWires = new ArrayList<>();
		protected List<AnnotatedElementRecord> connectionStartFeatureWires = new ArrayList<>();
		protected List<AnnotatedElementRecord> connectionTargetFeatureWires = new ArrayList<>();
		protected List<AnnotatedElementRecord> connectionEndFeatureWires = new ArrayList<>();
		
		public Dispatcher(Object[] targets) {
			for (Object target: targets) {
				getAnnotatedElementRecords(target)
					.filter(ar -> ar.getAnnotation(ContainerFeature.class) != null && ar.getAnnotatedElement() instanceof Method)
					.sorted((a,b) -> b.getAnnotation(ContainerFeature.class).priority() - a.getAnnotation(ContainerFeature.class).priority())
					.forEach(containerFeatureWires::add);
				
				getAnnotatedElementRecords(target)
					.filter(ar -> ar.getAnnotation(ContentsFeature.class) != null && ar.getAnnotatedElement() instanceof Method)
					.sorted((a,b) -> b.getAnnotation(ContentsFeature.class).priority() - a.getAnnotation(ContentsFeature.class).priority())
					.forEach(contentsFeatureWires::add);
				
				getAnnotatedElementRecords(target)
					.filter(ar -> ar.getAnnotation(ConnectionSourceFeature.class) != null && ar.getAnnotatedElement() instanceof Method)
					.sorted((a,b) -> b.getAnnotation(ConnectionSourceFeature.class).priority() - a.getAnnotation(ConnectionSourceFeature.class).priority())
					.forEach(connectionSourceFeatureWires::add);
				
				getAnnotatedElementRecords(target)
					.filter(ar -> ar.getAnnotation(ConnectionStartFeature.class) != null && ar.getAnnotatedElement() instanceof Method)
					.sorted((a,b) -> b.getAnnotation(ConnectionStartFeature.class).priority() - a.getAnnotation(ConnectionStartFeature.class).priority())
					.forEach(connectionStartFeatureWires::add);
				
				getAnnotatedElementRecords(target)
					.filter(ar -> ar.getAnnotation(ConnectionTargetFeature.class) != null && ar.getAnnotatedElement() instanceof Method)
					.sorted((a,b) -> b.getAnnotation(ConnectionTargetFeature.class).priority() - a.getAnnotation(ConnectionTargetFeature.class).priority())
					.forEach(contentsFeatureWires::add);
				
				getAnnotatedElementRecords(target)
					.filter(ar -> ar.getAnnotation(ConnectionEndFeature.class) != null && ar.getAnnotatedElement() instanceof Method)
					.sorted((a,b) -> b.getAnnotation(ConnectionEndFeature.class).priority() - a.getAnnotation(ConnectionEndFeature.class).priority())
					.forEach(contentsFeatureWires::add);
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
			
			List<Object> results = new ArrayList<>(); 			
			for (AnnotatedElementRecord ar: contentsFeatureWires) {
				ContentsFeature rWire = ar.getAnnotation(ContentsFeature.class);
				Class<?>[] parameterTypes = ((Method) ar.getAnnotatedElement()).getParameterTypes();
				if (rWire.contentsType().isInstance(contents) && parameterTypes[0].isInstance(contents)
						&& (rWire.contentsValueType() == Void.class ? contentsValue == null : rWire.contentsValueType().isInstance(contentsValue) && parameterTypes[1].isInstance(contentsValue))
						&& rWire.value() == contentsValueFeature.getFeatureID()
						&& rWire.containerType().isInstance(container) && parameterTypes[3].isInstance(container)
						&& (rWire.containerValueType() == Void.class ? containerValue == null : rWire.containerValueType().isInstance(containerValue) && parameterTypes[4].isInstance(containerValue))) {
					
					Map<String, Object> variables = new HashMap<>();
					variables.put("contentsValue", contentsValue); 
					variables.put("feature", contentsValueFeature);
					variables.put("container", container); 
					variables.put("containerValue", containerValue); 
					variables.put("sourcePath", sourcePath); 
					variables.put("registry", registry);
					if (evaluatePredicate(contents, rWire.condition(), variables)) {
						results.add(ar.invoke(
								contents, 
								contentsValue, 
								contentsValueFeature,
								container,
								containerValue,
								sourcePath, 
								registry, 
								progressMonitor));
					}					
				}				
			}			
			
			return !Boolean.FALSE.equals(
					results
						.stream()
						.reduce((a, b) -> Boolean.FALSE.equals(a) && Boolean.FALSE.equals(b) ? false : true)
						.orElse(true));
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

			List<Object> results = new ArrayList<>(); 			
			for (AnnotatedElementRecord ar: contentsFeatureWires) {
				ContainerFeature rWire = ar.getAnnotation(ContainerFeature.class);
				Class<?>[] parameterTypes = ((Method) ar.getAnnotatedElement()).getParameterTypes();
				if (rWire.contentsType().isInstance(container) && parameterTypes[0].isInstance(container)
						&& (rWire.containerValueType() == Void.class ? containerValue == null : rWire.containerValueType().isInstance(containerValue) && parameterTypes[1].isInstance(containerValue))
						&& rWire.value() == containerValueFeature.getFeatureID()
						&& rWire.contentsType().isInstance(contents) && parameterTypes[3].isInstance(contents)
						&& (rWire.contentsValueType() == Void.class ? contentsValue == null : rWire.contentsValueType().isInstance(contentsValue) && parameterTypes[4].isInstance(contentsValue))) {
					
					Map<String, Object> variables = new HashMap<>();
					variables.put("containerValue", containerValue); 
					variables.put("feature", containerValueFeature);
					variables.put("contents", contents); 
					variables.put("contentsValue", contentsValue); 
					variables.put("sourcePath", sourcePath); 
					variables.put("registry", registry);
					if (evaluatePredicate(contents, rWire.condition(), variables)) {
						results.add(ar.invoke(
								container, 
								containerValue, 
								containerValueFeature,
								contents,
								contentsValue,
								sourcePath, 
								registry, 
								progressMonitor));
					}					
				}				
			}			
			
			return !Boolean.FALSE.equals(
					results
						.stream()
						.reduce((a, b) -> Boolean.FALSE.equals(a) && Boolean.FALSE.equals(b) ? false : true)
						.orElse(true));
		}
		
		protected void wireConnectionSourceFeature(
				S connection, 
				S connectionSource, 
				T connectionSourceValue,
				EStructuralFeature connectionSourceValueFeature, 
				S argument,
				T argumentValue,
				Map<S, T> registry,
				ProgressMonitor progressMonitor) {
			
//			List<Object> results = new ArrayList<>(); 			
//			for (AnnotatedElementRecord ar: contentsFeatureWires) {
//				ConnectionSourceFeature rWire = ar.getAnnotation(ConnectionSourceFeature.class);
//				Class<?>[] parameterTypes = ((Method) ar.getAnnotatedElement()).getParameterTypes();
//				if (rWire.connectionType().isInstance(connection) && parameterTypes[0].isInstance(connection)
//						&& (rWire.connectionValueType() == Void.class ? connectionValue == null : rWire.containerValueType().isInstance(containerValue) && parameterTypes[1].isInstance(containerValue))
//						&& rWire.value() == containerValueFeature.getFeatureID()
//						&& rWire.contentsType().isInstance(contents) && parameterTypes[3].isInstance(contents)
//						&& (rWire.contentsValueType() == Void.class ? contentsValue == null : rWire.contentsValueType().isInstance(contentsValue) && parameterTypes[4].isInstance(contentsValue))) {
//					
//					Map<String, Object> variables = new HashMap<>();
//					variables.put("containerValue", containerValue); 
//					variables.put("feature", containerValueFeature);
//					variables.put("contents", contents); 
//					variables.put("contentsValue", contentsValue); 
//					variables.put("sourcePath", sourcePath); 
//					variables.put("registry", registry);
//					if (evaluatePredicate(contents, rWire.condition(), variables)) {
//						results.add(ar.invoke(
//								container, 
//								containerValue, 
//								containerValueFeature,
//								contents,
//								contentsValue,
//								sourcePath, 
//								registry, 
//								progressMonitor));
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
	
	protected Dispatcher dispatcher;
	
	public ReflectiveFeatureMapper(Object... targets) {
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
		
		dispatcher.wireConnectionSourceFeature(
				connection, 
				connectionSource, 
				connectionSourceValue, 
				connectionSourceValueFeature, 
				argument, 
				argumentValue, 
				registry, 
				progressMonitor);
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
		
		dispatcher.wireConnectionTargetFeature(
				connection, 
				connectionTarget, 
				connectionTargetValue, 
				connectionTargetValueFeature, 
				argument, 
				argumentValue, 
				registry, 
				progressMonitor);
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
		
		dispatcher.wireConnectionStartFeature(
				connection, 
				connectionValue, 
				connectionValueFeature, 
				connectionSource, 
				connectionSourceValue, 
				registry, 
				progressMonitor);
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
		
		dispatcher.wireConnectionEndFeature(
				connection, 
				connectionValue, 
				connectionValueFeature, 
				connectionTarget, 
				connectionTargetValue, 
				registry, 
				progressMonitor);
	}
		
}