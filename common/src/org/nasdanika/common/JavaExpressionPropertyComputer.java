package org.nasdanika.common;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.ExpressionEvaluator;

/**
 * Evaluates java expressions passed as sub-keys for this property key. E.g. <code>2+2</code> in <code>eval/2+2</code> if the property key is eval.
 * @author Pavel
 *
 */
public class JavaExpressionPropertyComputer implements PropertyComputer {
	
	private ClassLoader classLoader;


	public JavaExpressionPropertyComputer() {
		this(JavaExpressionPropertyComputer.class.getClassLoader());
	}
	
	public JavaExpressionPropertyComputer(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T compute(Context context, String key, Class<T> type) {
		return (T) new Function<String, Object>() {

			@Override
			public Object apply(String expression) {
				ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
				expressionEvaluator.setParentClassLoader(classLoader);
				expressionEvaluator.setExpressionType(type);
				expressionEvaluator.setParameters(new String[] { "context" }, new Class[] { Context.class });
				try {
					expressionEvaluator.cook(expression);
				} catch (CompileException e) {
					throw new NasdanikaException("Cannot compile java expression "+expression, e);
				}
				
				try {
					return (T) expressionEvaluator.evaluate(new Object[] { context });
				} catch (InvocationTargetException e) {
					throw new NasdanikaException("Cannot evaluate java expression "+expression, e);
				}
			}
			
		};
	}

}
