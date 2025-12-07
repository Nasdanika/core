package org.nasdanika.drawio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Realm;
import org.nasdanika.drawio.Document.Context;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.expression.spel.support.StandardTypeLocator;

/**
 * Context specialization for Spel evaluations. 
 */
public interface SpelContext extends Document.Context {
	
	default EvaluationContext createEvaluationContext(ModelElement<?> modelElement) {
		StandardEvaluationContext ret = new StandardEvaluationContext();
		ClassLoader classLoader = getClassLoader(modelElement);
		if (classLoader != null) {
			ret.setTypeLocator(new StandardTypeLocator(classLoader));
		}
		for (Entry<String, Object> ve: getVariables(modelElement).entrySet()) {
			ret.setVariable(ve.getKey(), ve.getValue());
		}
		return ret;
	}
	
	default ClassLoader getClassLoader(ModelElement<?> modelElement) {
		return Thread.currentThread().getContextClassLoader();
	}
	
	default SpelExpressionParser createExpressionParser(ModelElement<?> modelElement) {
		SpelParserConfiguration config = new SpelParserConfiguration(getCompilerMode(modelElement), getClassLoader(modelElement));
		return new SpelExpressionParser(config);
	}	
		
	default SpelCompilerMode getCompilerMode(ModelElement<?> modelElement) {
		return null;
	}
	
	default <T> T evaluate(ModelElement<?> modelElement, String expression, Class<T> type) {
		return evaluate(modelElement, expression, Collections.emptyMap(), type);
	}

	default <T> T evaluate(ModelElement<?> modelElement, String expression, Map<String,Object> variables, Class<T> type) {
		ExpressionParser parser = createExpressionParser(modelElement);
		Expression exp = parser.parseExpression(expression);
		EvaluationContext evaluationContext = createEvaluationContext(modelElement);
		if (variables != null) {
			variables.entrySet().forEach(ve -> evaluationContext.setVariable(ve.getKey(), ve.getValue()));
		}
		return exp.getValue(evaluationContext, modelElement, type);
	}

	@Override
	default Context compose(Context other) {
		if (other == null) {
			return this;
		}
		Context superComposed = Context.super.compose(other);
		
		return new SpelContext() {
			
			@Override
			public InputStream openStream(URI uri) throws IOException, URISyntaxException {
				return superComposed.openStream(uri);
			}
			
			@Override
			public String getProperty(String name) {
				return superComposed.getProperty(name);
			}
			
			@Override
			public String filterProperty(ModelElement<?> modelElement, String propertyName, String propertyValue) {
				return superComposed.filterProperty(modelElement, propertyName, propertyValue);
			}				
			
			@Override
			public Map<String, Object> getVariables(ModelElement<?> modelElement) {
				return superComposed.getVariables(modelElement);				
			}
			
			@Override
			public Realm getRealm() {
				return SpelContext.this.getRealm();
			}
			
		};
		
	}

}
