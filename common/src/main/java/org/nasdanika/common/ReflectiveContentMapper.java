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
import org.eclipse.emf.ecore.EReference;

public class ReflectiveContentMapper<S extends EObject, T extends EObject> extends ContentMapper<S, T> {
	
	/**
	 * Annotation for reference wiring methods. 
	 * Methods shall have the same parameters as {@link ContentMapper}.wireReference().
	 * Methods are matched by annotatioin xxxType() values and by method parameter types.   
	 */
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface ReferenceWire {
		
		/**
		 * Reference feature id
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

		protected List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();
		
		public Dispatcher(Object[] targets) {
			for (Object target: targets) {
				getAnnotatedElementRecords(target)
					.filter(ar -> ar.getAnnotation(ReferenceWire.class) != null && ar.getAnnotatedElement() instanceof Method)
					.sorted((a,b) -> b.getAnnotation(ReferenceWire.class).priority() - a.getAnnotation(ReferenceWire.class).priority())
					.forEach(annotatedElementRecords::add);
			}
		}
		
		protected boolean wireReference(
				S source, 
				T target, 
				EReference eReference, 
				T childTarget,
				LinkedList<EObject> sourcePath, 
				Map<S, T> registry, 
				ProgressMonitor progressMonitor) {
			
			List<Object> results = new ArrayList<>(); 			
			for (AnnotatedElementRecord ar: annotatedElementRecords) {
				ReferenceWire rWire = ar.getAnnotation(ReferenceWire.class);
				Class<?>[] parameterTypes = ((Method) ar.getAnnotatedElement()).getParameterTypes();
				if (rWire.sourceType().isInstance(source) 
						&& parameterTypes[0].isInstance(source)
						&& rWire.targetType().isInstance(target) 
						&& parameterTypes[1].isInstance(target)
						&& rWire.value() == eReference.getFeatureID()
						&& rWire.childTargetType().isInstance(childTarget) 
						&& parameterTypes[3].isInstance(childTarget)) {
					
					Map<String, Object> variables = new HashMap<>();
					variables.put("target", target);
					variables.put("eReference", eReference);
					variables.put("childTarget", childTarget);
					variables.put("sourcePath", sourcePath);
					variables.put("registry", registry);
					if (evaluatePredicate(source, rWire.condition(), variables)) {
						results.add(ar.invoke(source, target, eReference, childTarget, sourcePath, registry, progressMonitor));
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
	protected boolean wireReference(
			S source, 
			T target, 
			EReference eReference, 
			T childTarget,
			LinkedList<EObject> sourcePath, 
			Map<S, T> registry, 
			ProgressMonitor progressMonitor) {
		return dispatcher.wireReference(source, target, eReference, childTarget, sourcePath, registry, progressMonitor);
	}
	
}