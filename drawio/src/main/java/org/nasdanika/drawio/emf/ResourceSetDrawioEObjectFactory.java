package org.nasdanika.drawio.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.jsoup.Jsoup;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
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
public abstract class ResourceSetDrawioEObjectFactory<T extends EObject> extends DrawioEObjectFactory<T> {
	
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
		String interpolatedSpec = createDiagramElementContext(config).interpolateToString(spec);
		URI specURI = ObjectLoaderResource.encode(interpolatedSpec, specFormat, specBase).appendFragment("/");	// Loading the single root object.	
		return load(specURI, specFormat, config, context, progressMonitor);
	}
		
	protected Context createDiagramElementContext(ProcessorConfig<T> config) {
		MutableContext elementContext = getContext().fork();
		PropertyComputer propertyComputer = new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <PT> PT compute(Context context, String key, String path, Class<PT> type) {
				if (type == null || type.isAssignableFrom(String.class)) {
					if (Util.isBlank(path)) {
						return null;
					}
					
					// page name
					if (config.getElement() instanceof Page) {
						Page page = (Page) config.getElement();
						if ("name".equals(path)) {
							return (PT) page.getName();
						}
						if ("id".equals(path)) {
							return (PT) page.getId();
						}
					} else if (config.getElement() instanceof ModelElement) {
						ModelElement modelElement = (ModelElement) config.getElement();

						if ("id".equals(path)) {
							return (PT) modelElement.getId();
						}

						String label = modelElement.getLabel();
						if ("label".equals(path)) {
							return (PT) label;
						}

						if ("label-text".equals(path)) { // Plain text label							
							return (PT) (Util.isBlank(label) ? label : Jsoup.parse(label).text());
						}

						if ("link".equals(path)) {
							return (PT) modelElement.getLink();
						}

						if ("tooltip".equals(path)) {
							return (PT) modelElement.getTooltip();
						}

						if (path.startsWith(PROPERTIES_PREFIX)) {							
							return (PT) modelElement.getProperty(path.substring(PROPERTIES_PREFIX.length()));
						}
					}
					
					if (path.startsWith(EXPR_PREFIX)) {
						String expr = path.substring(EXPR_PREFIX.length());
						if (!org.nasdanika.common.Util.isBlank(expr)) {
							ExpressionParser parser = new SpelExpressionParser();
							Expression exp = parser.parseExpression(expr);
							EvaluationContext evaluationContext = createEvaluationContext();
							evaluationContext.setVariable("context", context);
							evaluationContext.setVariable("config", config);
							Object result = exp.getValue(evaluationContext, config.getElement());
							return result == null ? null : (PT) String.valueOf(result);
						}								
					}
				}
				return null;
			}
		};
		
		elementContext.put("diagram-element", propertyComputer);
		return elementContext;
	}
	

}
