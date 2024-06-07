package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.SupercedingCapabilityProvider;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.common.Util;
import org.nasdanika.graph.processor.CapabilityProcessorFactory.ProcessorRequirement;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import reactor.core.publisher.Flux;


/**
 * Creates graph element processors by reflectively calling factory objects with annotated methods.
 * @param <R>
 * @param <P>
 */
public abstract class ReflectiveProcessorServiceFactory<R,P> extends ServiceCapabilityFactory<ProcessorRequirement<R, P>, P> {
	
	@Retention(RUNTIME)
	@Target(METHOD)	
	public @interface ProcessorFactory {
		/**
		 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
		 * which is evaluated in the context of a {@link ProcessorConfig} with requirement variable 
		 * @return
		 */
		String condition() default "";

		/**
		 * Matching by element type.
		 * @return
		 */
		Class<?> type() default Object.class; 
		
		/**
		 * Matching by element value type for elements implementing Supplier.
		 * @return
		 */
		Class<?> valueType() default Object.class; 

		/**
		 * Matching by handler type.
		 * @return
		 */
		Class<?> handlerType() default Object.class; 

		/**
		 * Matching by element type.
		 * @return
		 */
		Class<?> endpointType() default Object.class; 
		
		/**
		 * Factory priority
		 * @return
		 */
		int priority() default 0;
		
	}
	
	protected interface ProcessorCapabilityProvider<T> extends SupercedingCapabilityProvider<T> {
		
		/**
		 * One processor (capability provider) supercedes another if it has higher priority,
		 * its type is a subtype of the other, or, if types are equal,  value type is a sub-type of the other value type.
		 * @param other
		 * @return
		 */
		default boolean supercedes(CapabilityProvider<?> other) {
			if (other == this) {
				return false;
			}
			if (other == null) {
				return true;
			}
			
			if (other instanceof ProcessorCapabilityProvider) {
				ProcessorCapabilityProvider<?> pOther = (ProcessorCapabilityProvider<?>) other;
				if (getPriority() > pOther.getPriority()) {
					return true;
				}
				
				if (getType() == pOther.getType()) {
					if (pOther.getValueType().isAssignableFrom(getValueType())) {
						return true;
					}
				} else if (pOther.getType().isAssignableFrom(getType())) {
					return true;
				}
			}
			
			return false;
		}

		Class<?> getType(); 
		
		Class<?> getValueType(); 
		
		int getPriority();
		
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<P>>> createService(
			Class<P> processorType,
			ProcessorRequirement<R, P> serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		CompletionStage<Collection<Object>> collectorCS = CompletableFuture.completedStage(new ArrayList<Object>());
		for (CompletionStage<Object> targetCS: loadTargets(processorType, serviceRequirement, resolver, progressMonitor)) {
			collectorCS = collectorCS.thenCombine(targetCS, (collector, target) -> {
				collector.add(target);
				return collector;
			});
		}
		
		return collectorCS.thenApply(targets -> createProcessors(processorType, serviceRequirement, resolver, targets, progressMonitor));
	}
	
	protected Iterable<CapabilityProvider<P>> createProcessors(
			Class<P> processorType,
			ProcessorRequirement<R, P> serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			Collection<Object> factories, 
			ProgressMonitor progressMonitor) {
		
		Reflector reflector = new Reflector();
		return factories
			.stream()
			.flatMap(factory -> reflector.getAnnotatedElementRecords(factory, Collections.singletonList(factory)))
			.filter(aer -> match(aer, processorType, serviceRequirement, progressMonitor))
			.map(aer -> create(aer, processorType, serviceRequirement, progressMonitor))
			.filter(Objects::nonNull)
			.toList();
	}
	
	protected boolean match(
			Reflector.AnnotatedElementRecord aer,
			Class<P> processorType,
			ProcessorRequirement<R, P> serviceRequirement,
			ProgressMonitor progressMonitor) {
		
		ProcessorFactory processorFactory = aer.getAnnotation(ProcessorFactory.class);
		if (processorFactory == null) {
			return false;
		}
		
		AnnotatedElement annotatedElement = aer.getAnnotatedElement();
		if (!(annotatedElement instanceof Method)) {
			return false;
		}
		
		Method method = (Method) annotatedElement;
		
		// processor type
		if (!processorType.isAssignableFrom(method.getReturnType())) {
			return false;
		}		
		
		Object element = serviceRequirement.config().getElement();
		// type
		if (!processorFactory.type().isInstance(element)) {
			return false;
		}
		
		// value type
		if (element instanceof Supplier && !processorFactory.valueType().isInstance(((Supplier<?>) element).get())) {
			return false;
		}
		
		// handler type
		if (serviceRequirement.handlerType() != null && !serviceRequirement.handlerType().isAssignableFrom(processorFactory.handlerType())) {
			return false;
		}
		
		// endpoint type
		if (serviceRequirement.endpointType() != null && !serviceRequirement.endpointType().isAssignableFrom(processorFactory.endpointType())) {
			return false;
		}
				
		// condition
		if (!evaluatePredicate(serviceRequirement.config(), processorFactory.condition(), Collections.singletonMap("requirement", serviceRequirement.requirement()))) {
			return false;
		}

		Class<?>[] parameterTypes = method.getParameterTypes();
		/*
		 * Method signature:
		 * ProcessorConfig config, 
		 * BiConsumer<Element, BiConsumer<ProcessorInfo<P>, ProgressMonitor>> infoProvider,
		 * Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
 		 * R requirement,
		 * ProgressMonitor progressMonitor
		 */
		
		if (!parameterTypes[0].isInstance(serviceRequirement.config())) {
			return false;
		}
		
		return serviceRequirement.requirement() == null || parameterTypes[3].isInstance(serviceRequirement.requirement());
	}
	
	protected CapabilityProvider<P> create(
			Reflector.AnnotatedElementRecord aer,
			Class<P> processorType,
			ProcessorRequirement<R, P> serviceRequirement,
			ProgressMonitor progressMonitor) {
		
		ProcessorFactory processorFactory = aer.getAnnotation(ProcessorFactory.class);

		/*
		 * Method signature:
		 * ProcessorConfig config, 
		 * BiConsumer<Element, BiConsumer<ProcessorInfo<P>, ProgressMonitor>> infoProvider,
		 * Consumer<CompletionStage<?>> endpointWiringStageConsumer,
		 * Class<P> processorType,
		 * Class<?> handlerType,
		 * Class<?> endpointType, 
 		 * R requirement,
		 * ProgressMonitor progressMonitor
		 */
		@SuppressWarnings("unchecked")
		P processor = (P) aer.invoke(
				serviceRequirement.config(), 
				serviceRequirement.infoProvider(),
				serviceRequirement.endpointWiringStageConsumer(),
				processorType,
				serviceRequirement.handlerType(),
				serviceRequirement.endpointType(),
				serviceRequirement.requirement(),
				progressMonitor);
		
		if (processor == null) {
			return null;
		}
		
		return new ProcessorCapabilityProvider<P>() {
			
			@Override
			public Flux<P> getPublisher() {
				return Flux.just(processor);
			}

			@Override
			public Class<?> getType() {
				return processorFactory.type();
			}

			@Override
			public Class<?> getValueType() {
				return processorFactory.valueType();
			}

			@Override
			public int getPriority() {
				return processorFactory.priority();
			}
			
		};		
	}
	
	protected boolean evaluatePredicate(Object obj, String expr, Map<String,Object> variables) {
		if (Util.isBlank(expr)) {
			return true;
		}
		
		ExpressionParser parser = getExpressionParser();
		Expression exp = parser.parseExpression(expr);
		EvaluationContext evaluationContext = getEvaluationContext();
		if (variables != null) {
			variables.entrySet().forEach(ve -> evaluationContext.setVariable(ve.getKey(), ve.getValue()));
		}
		try {			
			return evaluationContext == null ? exp.getValue(obj, Boolean.class) : exp.getValue(evaluationContext, obj, Boolean.class);
		} catch (EvaluationException e) {
			onEvaluationException(obj, expr, evaluationContext, e);
			return false;
		}
	}
	
	protected EvaluationContext getEvaluationContext() {
		return new StandardEvaluationContext();
	}
	
	protected ThreadLocal<SpelExpressionParser> expressionParserThreadLocal = new ThreadLocal<>() {
		
		@Override
		protected SpelExpressionParser initialValue() {
			return new SpelExpressionParser();			
		}
		
	};

	protected SpelExpressionParser getExpressionParser() {
		return expressionParserThreadLocal.get();
	}
	
	/**
	 * Override to troubleshoot SPEL predicates.
	 * @param obj
	 * @param expr
	 * @param evaluationContext
	 */
	protected void onEvaluationException(Object obj, String expr, EvaluationContext evaluationContext, EvaluationException exception) {
		
	}
		
	/**
	 * Loads/creates reflection targets.
	 * @param processorType
	 * @param serviceRequirement
	 * @param resolver
	 * @param progressMonitor
	 * @return
	 */
	protected abstract Iterable<CompletionStage<Object>> loadTargets(
			Class<P> processorType,
			ProcessorRequirement<R, P> serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor);

}
