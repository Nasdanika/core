package org.nasdanika.common;

import java.lang.reflect.InvocationTargetException;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.ExpressionEvaluator;

/**
 * Evaluates java expressions passed as sub-keys for this property key. E.g. <code>2+2</code> in <code>eval/2+2</code> if the property key is eval.
 * Uses the {@link ClassLoader} context service defaulting to the classloader passed to the constructor, which in turn defaults to this class classloader.
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
	public <T> T compute(Context context, String key, String path, Class<T> type) {
		ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
		expressionEvaluator.setParentClassLoader(context.get(ClassLoader.class, classLoader));
		expressionEvaluator.setExpressionType(type);
		expressionEvaluator.setParameters(new String[] { "context" }, new Class[] { Context.class });
		try {
			expressionEvaluator.cook(path);
		} catch (CompileException e) {
			throw new NasdanikaException("Cannot compile java expression "+path, e);
		}
		
		try {
			return (T) expressionEvaluator.evaluate(new Object[] { context });
		} catch (InvocationTargetException e) {
			throw new NasdanikaException("Cannot evaluate java expression "+path, e);
		}
	}

}
