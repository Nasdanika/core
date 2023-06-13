package org.nasdanika.graph;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Graph element accepts visitors and supports streaming. 
 * @author Pavel
 *
 */
public interface Element {
	
	/**
	 * Accepts the visitor in children first way.
	 * @param visitor
	 */
	default void accept(Consumer<? super Element> visitor) {
		accept((e, cr) -> { visitor.accept(e); return null; });
	}
	
	/**
	 * Accepts a result producing visitor {@link BiFunction}. Passes to it a map of results collected from children, nulls are not included.   
	 * @param <T>
	 * @param visitor
	 * @return result returned by the visitor.
	 */
	<T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor);
	
	/**
	 * Creates a visitor dispatching elements to methods annotated with {@link Handler}.
	 * Methods may have one or two parameters taking Element and a map of children.
	 */
	default Object dispatch(Object... targets) {
		return dispatch(Arrays.asList(targets));
	}
	
	default Object dispatch(Collection<Object> targets) {
		return accept((element, childResults) -> {
			for (Object target: targets) {
				Optional<Method> handlerOptional = Arrays.stream(target.getClass().getMethods())
					.filter(m -> !Modifier.isAbstract(m.getModifiers()))	
					.filter(m -> m.getAnnotation(Handler.class) != null)
					.filter(m -> m.getParameterCount() == 1 || m.getParameterCount() == 2)
					.filter(m -> m.getParameters()[0].getType().isInstance(element))
					.filter(m -> m.getParameterCount() == 1 ? true : m.getParameters()[1].getType().isInstance(childResults))
					.filter(m -> matchPredicate(element, m.getAnnotation(Handler.class).value()))
					.sorted((a, b) -> b.getAnnotation(Handler.class).priority() - a.getAnnotation(Handler.class).priority())
					.findFirst();
				
				if (!handlerOptional.isEmpty()) {
					Method handler = handlerOptional.get();
					try {
						if (handler.getParameterCount() == 1) {
							return handler.invoke(target, element);
						}
						return handler.invoke(target, element, childResults);					
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new NasdanikaException("Error invoking handler: " + e, e);
					}
				}
			}
			return null;
		});
	}
	
	/**
	 * @return Stream containing this element and its children.
	 */
	default Stream<Element> stream() {
		BiFunction<Element, Map<? extends Element, Stream<Element>>, Stream<Element>> visitor = (element, childMappings) -> {
			return childMappings.values().stream().reduce(Stream.of(element), (a,b) -> Stream.concat(a, b));
		};
		return accept(visitor);
	}; 
	
	/**
	 * Parses and evaluates expression using <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions">Spring Expression Language</a> 
	 * @param obj
	 * @param expr 
	 * @return true if expression is blank or evaluates to true, false if the expression evaluates to false or throws EvaluationException.
	 */
	private boolean matchPredicate(Object obj, String expr) {
		if (Util.isBlank(expr)) {
			return true;
		}
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(expr);			
		try {
			return exp.getValue(obj, Boolean.class);
		} catch (EvaluationException e) {
			return false;
		}
	}
	
}
