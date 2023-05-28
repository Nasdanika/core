package org.nasdanika.graph.processor.emf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.Factory;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.ncore.util.NcoreUtil;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Uses 
 * @author Pavel
 *
 */
@Factory(type = EObjectNode.class)
public class EObjectNodeProcessorReflectiveFactory<P,H,E,R> {
		
	@Processor(type = EObjectNode.class)
	public Object createEObjectNodeProcessor(NodeProcessorConfig<P,H,E,R> config) {
		EObject eObj = ((EObjectNode) config.getElement()).getTarget();
		URI[] identifierBase = { null };
		EObjectNodeProcessor classAnnotation = getClass().getAnnotation(EObjectNodeProcessor.class);
		if (classAnnotation != null) {
			if (!classAnnotation.type().isInstance(eObj)) {
				return null;
			}
			
			String identifierStr = classAnnotation.identifier();
			if (!Util.isBlank(identifierStr)) {
				identifierBase[0] = URI.createURI(identifierStr); 
			}
			if (!matchPredicate(eObj, classAnnotation.value())) {
				return null;
			};			
		}
		
		Optional<Method> factoryMethod = Stream.of(getClass().getMethods())
				.filter(m -> {
					EObjectNodeProcessor annotation = m.getAnnotation(EObjectNodeProcessor.class);
					if (annotation == null) {
						return false;
					}
					
					if (!annotation.type().isInstance(eObj)) {
						return false;
					}
					
					String identifierStr = annotation.identifier();
					if (Util.isBlank(identifierStr)) {
						return true;
					}
					URI identifier = URI.createURI(identifierStr);
					return NcoreUtil.getIdentifiers(eObj).contains(identifier) ? matchPredicate(eObj, annotation.value()) : false;
				})
				.sorted(this::compareFactoryMethods)
				.findFirst();
		
		if (factoryMethod.isEmpty()) {
			return null;
		}
		
		try {
			return factoryMethod.get().invoke(this, config);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new NasdanikaException("Error invoking " + factoryMethod.get() + ": " + e, e);
		}
	}

	protected int compareFactoryMethods(Method a, Method b) {
		EObjectNodeProcessor aAnnotation = a.getAnnotation(EObjectNodeProcessor.class);
		EObjectNodeProcessor bAnnotation = b.getAnnotation(EObjectNodeProcessor.class);
		int priorityCmp = bAnnotation.priority() - aAnnotation.priority();
		if (priorityCmp != 0) {
			return priorityCmp;
		}
		Class<? extends EObject> aType = aAnnotation.type(); 
		Class<? extends EObject> bType = bAnnotation.type();
		if (aType == bType) {
			return 0;
		}
		if (aType.isAssignableFrom(bType)) {
			return 1;
		}
		if (bType.isAssignableFrom(aType)) {
			return -1;
		}
		return a.hashCode() - b.hashCode();
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
		
		ExpressionParser parser = getExpressionParser();
		Expression exp = parser.parseExpression(expr);
		EvaluationContext evaluationContext = getEvaluationContext();
		try {			
			return evaluationContext == null ? exp.getValue(obj, Boolean.class) : exp.getValue(evaluationContext, obj, Boolean.class);
		} catch (EvaluationException e) {
			onEvaluationException(obj, expr, evaluationContext, e);
			return false;
		}
	}
	
	protected EvaluationContext getEvaluationContext() {
		return null;
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
	
}
