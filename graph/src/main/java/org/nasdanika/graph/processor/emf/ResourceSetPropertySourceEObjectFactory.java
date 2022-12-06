package org.nasdanika.graph.processor.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertySource;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.persistence.ObjectLoaderResource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Uses {@link ResourceSet} to load objects from spec-uri.
 * Text specs get encoded into a data URL by creating a JSON object with spec, format, and base fields. The object is converted to string and base64 encoded.
 * application/json-eobject-spec is used as media type. 
 * @author Pavel
 *
 * @param <T>
 */
public abstract class ResourceSetPropertySourceEObjectFactory<T extends EObject> extends PropertySourceEObjectFactory<T> {
	
	private static final String EXPR_PREFIX = "expr/";
	private static final String PROPERTIES_PREFIX = "properties/";
	
	/**
	 * Context for spec interpolation.
	 * @return
	 */
	protected Context getContext() {
		return Context.EMPTY_CONTEXT;
	}
	
	/**
	 * @return {@link ResourceSet} to load objects. 
	 */
	protected abstract ResourceSet getResourceSet();
	
	/**
	 * Returns an object identified by specURI by calling {@link ResourceSet}.getEObject(). A single resource root object has <code>/</code> URI fragment by default.
	 * @param specURI
	 * @param config
	 * @param progressMonitor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected T load(URI specURI, String specFormat, ProcessorConfig<T> config, Context context, ProgressMonitor progressMonitor) {
		ResourceSet resourceSet = getResourceSet();
		return (T) resourceSet.getEObject(specURI, true);
	}

	/**
	 * Encodes spec as a data URI and calls load(uri, ...)
	 */
	@Override
	protected T load(String spec, String specFormat, URI specBase, ProcessorConfig<T> config, Context context, ProgressMonitor progressMonitor) {
		String interpolatedSpec = createElementContext(config).interpolateToString(spec);
		URI specURI = ObjectLoaderResource.encode(interpolatedSpec, specFormat, specBase).appendFragment("/");	// Loading the single root object.	
		return load(specURI, specFormat, config, context, progressMonitor);
	}
		
	protected Context createElementContext(ProcessorConfig<T> config) {
		Context context = getContext();
		
		Context propertyAndExpressionComputerContext = new Context() {

			@SuppressWarnings("unchecked")
			@Override
			public Object get(String key) {
				if (key == null) {
					return null;
				}
				Element element = config.getElement();
				if (key.startsWith(PROPERTIES_PREFIX)) {
					if (element instanceof PropertySource) {
						return ((PropertySource<Object,?>) element).getProperty(key.substring(PROPERTIES_PREFIX.length()));
					}
				} else if (key.startsWith(EXPR_PREFIX)) {
					String expr = key.substring(EXPR_PREFIX.length());
					if (!org.nasdanika.common.Util.isBlank(expr)) {
						ExpressionParser parser = new SpelExpressionParser();
						Expression exp = parser.parseExpression(expr);
						EvaluationContext evaluationContext = createEvaluationContext();
						evaluationContext.setVariable("context", context);
						evaluationContext.setVariable("config", config);
						return exp.getValue(evaluationContext, element);						
					}													
				}
				return null;
			}

			@Override
			public <V> V get(Class<V> type) {
				return null;
			}
			
		};
		
		return propertyAndExpressionComputerContext.compose(context.computingContext());
	}
	

	// semantic-type, semanticLoadOnDemand, isLoadOnDemand
	
}
