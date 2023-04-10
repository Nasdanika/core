package org.nasdanika.graph.processor.emf;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertySource;
import org.nasdanika.common.Util;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.persistence.ObjectLoaderResource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.yaml.snakeyaml.Yaml;

/**
 * Uses {@link ResourceSet} to load objects from spec-uri.
 * Text specs get encoded into a data URL by creating a JSON object with spec, format, and base fields. The object is converted to string and base64 encoded.
 * application/json-eobject-spec is used as media type. 
 * @author Pavel
 *
 * @param <T>
 */
public abstract class ResourceSetPropertySourceEObjectFactory<T extends EObject, P extends SemanticProcessor<T>, R> extends PropertySourceEObjectFactory<T,P, R> {
	
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
	protected Collection<T> load(URI specURI, String specFormat, ProcessorConfig<P, R> config, Context context, ProgressMonitor progressMonitor) {
		ResourceSet resourceSet = getResourceSet();
		String semanticType = getPropertyValue(config.getElement(), getSemanticTypePropertyName());
		if (!Util.isBlank(semanticType)) {
			// Proxy. Try to resolve first in case already available in the resource set.
			T semanticElement = (T) resourceSet.getEObject(specURI, false);
			if (semanticElement != null) {
				return Collections.singleton(semanticElement);
			}
			Yaml yaml = new Yaml();
			Object semanticTypeObj = yaml.load(semanticType);
			if (semanticTypeObj instanceof Map) {
				Map<?, ?> semanticTypeMap = (Map<?,?>) semanticTypeObj;
				Object typeName = semanticTypeMap.get("name");
				if (typeName instanceof String) {
					Object typeNamespaceUri = semanticTypeMap.get("namespace-uri");
					if (typeNamespaceUri instanceof String) {
						EPackage ePackage = resourceSet.getPackageRegistry().getEPackage((String) typeNamespaceUri);
						if (ePackage == null) {
							throw new NasdanikaException("EPackage not found for namespace URI " + typeNamespaceUri);												
						}
						EClassifier eClassifier = ePackage.getEClassifier((String) typeName);
						if (eClassifier instanceof EClass) {
							EObject proxy = ePackage.getEFactoryInstance().create((EClass) eClassifier);
							if (proxy instanceof InternalEObject) {
								((InternalEObject) proxy).eSetProxyURI(specURI);
								return Collections.singleton((T) proxy);
							} else {
								throw new NasdanikaException("Cannot set proxy URI, not an instance of InteralEObject: " + proxy);																											
							}
						} else {
							throw new NasdanikaException("EClass '" + typeName + "' not found in " + typeNamespaceUri);																			
						}
					} else {
						throw new NasdanikaException("Semantic type map shall have 'namespace-uri' entry with String value: " + semanticType);					
					}					
				} else {
					throw new NasdanikaException("Semantic type map shall have 'name' entry with String value: " + semanticType);					
				}				
			} else {
				throw new NasdanikaException("Semantic type shall be a map, got " + semanticTypeObj.getClass() + " for " + semanticType);
			}
		}
		
		if (specURI.hasFragment()) {
			T semanticElement = (T) resourceSet.getEObject(specURI, isLoadOnDemand(config.getElement())); 
			return semanticElement == null ? Collections.emptySet() : Collections.singleton(semanticElement);
		}
		Resource resource = resourceSet.getResource(specURI, isLoadOnDemand(config.getElement()));
		return resource == null ? Collections.emptyList() : (Collection<T>) resource.getContents();
	}
	
	protected boolean isLoadOnDemand(Element element) {
		String loadOnDemandVal = getPropertyValue(element, getSemanticLoadOnDemanPropertyName());
		return Util.isBlank(loadOnDemandVal) || "true".equals(loadOnDemandVal);
	}
	
	protected String getSemanticLoadOnDemanPropertyName() {
		return "semantic-load-on-demand";
	}		

	/**
	 * YAML map with name and namespace-uri keys to resolve proxy {@link EClass}.
	 * @return
	 */
	protected String getSemanticTypePropertyName() {
		return "semantic-type";
	}			
	
	// TODO semantic-type, semanticLoadOnDemand, isLoadOnDemand	

	/**
	 * Encodes spec as a data URI and calls load(uri, ...)
	 */
	@Override
	protected Collection<T> load(String spec, String specFormat, URI specBase, ProcessorConfig<P, R> config, Context context, ProgressMonitor progressMonitor) {
		String interpolatedSpec = createElementContext(config).interpolateToString(spec);
		URI specURI = ObjectLoaderResource.encode(interpolatedSpec, specFormat, specBase, getElementQualifier(config));	
		return load(specURI, specFormat, config, context, progressMonitor);
	}

	/**
	 * @param config
	 * @return Qualifier to differentiate identical specifications.
	 */
	protected Object getElementQualifier(ProcessorConfig<P, R> config) {
		Element element = config.getElement();
		return element.getClass() + ":" + element.hashCode();
	}
		
	protected Context createElementContext(ProcessorConfig<P, R> config) {
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
	
}
