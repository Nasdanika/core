package org.nasdanika.capability.requirements;

import java.util.Map;

import org.nasdanika.common.Util;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public record DiagramRecord(
		
		String location,
		
		/**
		 * Inline diagram
		 */
		String source,
		
		/**
		 * For source - base URI
		 */
		String base,		
		
		/**
		 * An optional SpEL expression to select an element from a document
		 */
		String selector,
		
		/**
		 * properties to pass to the diagram.
		 * Can be specified inline (if map) or loaded from YAML, JSON, or properties URL - format is determined by by extension.
		 * Nested properties can be addressed using "." (dot) separator. For arrays index is used as key. E.g. people.3.name
		 */
		Map<String,?> properties,
		
		/**
		 * Processor property
		 */
		String processor,
		
		/**
		 * Bind property for dynamic proxy
		 */
		String bind,
		
		/**
		 * Interfaces to be implemented by a proxy.
		 * If not provided, no proxy is created and a map of elements to processor info (registry) is used as a result.
		 */
		String[] interfaces) {
	
	
	public Object select(Object object) {
		if (Util.isBlank(selector())) {
			return object;
		}
		
		StandardEvaluationContext evaluationContext = new StandardEvaluationContext(object);
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(selector());		
		return exp.getValue(evaluationContext);		
	}

}
