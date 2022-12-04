package org.nasdanika.drawio.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.emf.AbstractEObjectFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.yaml.snakeyaml.Yaml;

/**
 * Creates {@link EObject}s from graph elements and wires them together using diagram element properties.
 * This is a base class. Subclasses shall override one or more load() methods depending on their needs.
 * @author Pavel
 *
 */
public abstract class DrawioEObjectFactory<T extends EObject> extends AbstractEObjectFactory<T> {
	
	protected String getPropertyValue(org.nasdanika.graph.Element element, String propertyName) {
		if (!org.nasdanika.common.Util.isBlank(propertyName) && element instanceof ModelElement) {
			for (String propertyPrefix: getPropertyPrefixes()) {
				String propertyValue = ((ModelElement) element).getProperty(propertyPrefix + propertyName);
				if (!org.nasdanika.common.Util.isBlank(propertyValue)) {
					return propertyValue;
				}
			}
		}
		return null;
	}
	
	/**
	 * Prefixes added to property names. Override to implement namespacing of properties for elements with multiple semantic mappings. 
	 * Multiple prefixes may be used for profiles.
	 * For example, an element may have one mapping for documentation, another for code generation, and one more for status updates - pulling runtime information from monitoring systems and updating diagram elements with health status. 
	 * @return This implementation returns a singleton list of an empty string;
	 */
	protected List<String> getPropertyPrefixes() {
		return Collections.singletonList("");
	}
	
	// Loading spec

	/**
	 * Base URI for resolving spec references. This implementation returns null.
	 * @return
	 */
	protected URI getBaseURI() {
		return null;
	}
			
	/**
	 * Loads specification from a spec property or URI specified in spec-ref property. 
	 * The URI is resolved relative to the base URI if it is not null and absolute.
	 */
	@Override
	protected T createSemanticElement(ProcessorConfig<T> config, ProgressMonitor progressMonitor) {
		String spec = getPropertyValue(config.getElement(), getSpecPropertyName());
		String specFormat = getPropertyValue(config.getElement(), getSpecFormatPropertyName());
		if (!org.nasdanika.common.Util.isBlank(spec)) {
			return load(spec, specFormat, getBaseURI(), config, getLoadingContext(config, progressMonitor), progressMonitor);
		}
		
		String specUriStr = getPropertyValue(config.getElement(), getSpecUriPropertyName());
		if (!org.nasdanika.common.Util.isBlank(specUriStr)) {
			URI specURI = URI.createURI(specUriStr);
			if (specURI.isRelative()) {
				URI baseURI = getBaseURI();
				if (baseURI != null && !baseURI.isRelative()) {
					specURI = specURI.resolve(baseURI);
				}
			}
			return load(specURI, specFormat, config, getLoadingContext(config, progressMonitor), progressMonitor);
		}
		return null;
	}
	
	/**
	 * Override to create a context to be used for semantic element loading.
	 * @param config
	 * @param progressMonitor
	 * @return
	 */
	protected Context getLoadingContext(ProcessorConfig<T> config, ProgressMonitor progressMonitor) {
		return Context.EMPTY_CONTEXT;
	}

	/**
	 * Loads semantic element from a URI
	 * @return
	 */
	protected T load(URI specURI, String specFormat, ProcessorConfig<T> config, Context context, ProgressMonitor progressMonitor) {
		try {
			URL specUrl = new URL(specURI.toString());
			return load(specUrl, specFormat, config, context, progressMonitor);				
		} catch (IOException e) {
			throw new NasdanikaException("Error loading specification from specification URI " + specURI, e);
		}			
	}	
	
	/**
	 * Loads semantic element from a URL
	 * @return
	 */
	protected T load(URL url, String specFormat, ProcessorConfig<T> config, Context context, ProgressMonitor progressMonitor) throws IOException {
		return load(url.openStream(), specFormat, URI.createURI(url.toString()), config, context, progressMonitor);
	}
	
	/**
	 * Loads semantic element from an input stream.
	 * @return
	 */
	protected T load(InputStream inputStream, String specFormat, URI base, ProcessorConfig<T> config, Context context, ProgressMonitor progressMonitor) throws IOException {
		try (Reader reader = new InputStreamReader(inputStream, getCharset())) {
			return load(DefaultConverter.INSTANCE.toString(reader), specFormat, base, config, context, progressMonitor);
		}		
	}
	
	/**
	 * Loads semantic element from an input stream.
	 * @return
	 */
	protected T load(Reader reader, String specFormat, URI base, ProcessorConfig<T> config, Context context, ProgressMonitor progressMonitor) throws IOException {
		return load(DefaultConverter.INSTANCE.toString(reader), specFormat, base, config, context, progressMonitor);
	}	
	
	/**
	 * Override to customize charset.
	 * @return
	 */
	protected Charset getCharset() {
		return StandardCharsets.UTF_8;
	}	
	
	/**
	 * Loads semantic element from a string specification.
	 * @param spec Semantic element specification.
	 * @param specBase Base URI for resolving relative URI's.
	 * @return
	 */
	protected abstract T load(String spec, String specFormat, URI specBase, ProcessorConfig<T> config, Context context, ProgressMonitor progressMonitor);
		
	protected String getSpecPropertyName() {
		return "spec";
	}
	
	protected String getSpecUriPropertyName() {
		return "spec-uri";
	}		
	
	/**
	 * Specification format (MIME type). E.g. text/yaml or application/json. It is used to load specification. 
	 * @return
	 */
	protected String getSpecFormatPropertyName() {
		return "spec-format";
	}			

	// Child
	/**
	 * Property name of the parent's reference to inject child into 
	 * @return
	 */
	protected String getChildReferencePropertyName() {
		return "child-reference";
	}

	@Override
	protected EReference getChildReference(ProcessorConfig<T> config, T semanticElement, ProcessorInfo<T> parentProcessorInfo) {
		T parentSemanticElement = parentProcessorInfo.getProcessor();
		if (parentSemanticElement == null) {
			return null;
		}
		String value = getPropertyValue(config.getElement(), getChildReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		return (EReference) parentSemanticElement.eClass().getEStructuralFeature(value);
	}
	
	// Parent
	protected String getParentReferencePropertyName() {
		return "parent-reference";
	}

	@Override
	protected EReference getParentReference(ProcessorConfig<T> config, T semanticElement, ProcessorInfo<T> parentProcessorInfo) {
		String value = getPropertyValue(config.getElement(), getParentReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		return (EReference) semanticElement.eClass().getEStructuralFeature(value);
	}	
	
	protected String getParentInjectorPropertyName() {
		return "parent-injector";
	}
	
	@Override
	protected void setParent(ProcessorConfig<T> config, T semanticElement, ProcessorInfo<T> parentProcessorInfo) {
		super.setParent(config, semanticElement, parentProcessorInfo);
		String expr = getPropertyValue(config.getElement(), getParentInjectorPropertyName());
		if (!org.nasdanika.common.Util.isBlank(expr)) {
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(expr);
			EvaluationContext evaluationContext = createEvaluationContext();
			evaluationContext.setVariable("config", config);
			evaluationContext.setVariable("element", config.getElement());
			evaluationContext.setVariable("parent", parentProcessorInfo.getProcessor());
			evaluationContext.setVariable("parentConfig", parentProcessorInfo.getConfig());			
			exp.getValue(evaluationContext, semanticElement);
		}		
	}
	
	protected EvaluationContext createEvaluationContext() {
		return new StandardEvaluationContext();
	}	
		
	// Source
	protected String getSourceReferencePropertyName() {
		return "source-reference";
	}

	@Override
	protected EReference getSourceReference(ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, ProcessorInfo<T> sourceProcessorInfo) {
		String value = getPropertyValue(config.getElement(), getSourceReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		return (EReference) semanticElement.eClass().getEStructuralFeature(value);
	}
	
	protected String getSourceInjectorPropertyName() {
		return "source-injector";
	}
	
	@Override
	protected void setSource(ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, ProcessorInfo<T> sourceProcessorInfo) {
		super.setSource(config, semanticElement, sourceProcessorInfo);
		String expr = getPropertyValue(config.getElement(), getSourceInjectorPropertyName());
		if (!org.nasdanika.common.Util.isBlank(expr)) {
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(expr);
			EvaluationContext evaluationContext = createEvaluationContext();
			evaluationContext.setVariable("config", config);
			evaluationContext.setVariable("element", config.getElement());
			evaluationContext.setVariable("source", sourceProcessorInfo.getProcessor());
			evaluationContext.setVariable("sourceConfig", sourceProcessorInfo.getConfig());			
			exp.getValue(evaluationContext, semanticElement);
		}				
	}
	
	// Target
	protected String getTargetReferencePropertyName() {
		return "target-reference";
	}

	@Override
	protected EReference getTargetReference(ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, ProcessorInfo<T> targetProcessorInfo) {
		String value = getPropertyValue(config.getElement(), getTargetReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		return (EReference) semanticElement.eClass().getEStructuralFeature(value);
	}
	
	protected String getTargetInjectorPropertyName() {
		return "target-injector";
	}
	
	@Override
	protected void setTarget(ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, ProcessorInfo<T> targetProcessorInfo) {
		super.setTarget(config, semanticElement, targetProcessorInfo);
		String expr = getPropertyValue(config.getElement(), getTargetInjectorPropertyName());
		if (!org.nasdanika.common.Util.isBlank(expr)) {
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(expr);
			EvaluationContext evaluationContext = createEvaluationContext();
			evaluationContext.setVariable("config", config);
			evaluationContext.setVariable("element", config.getElement());
			evaluationContext.setVariable("target", targetProcessorInfo.getProcessor());
			evaluationContext.setVariable("targetConfig", targetProcessorInfo.getConfig());			
			exp.getValue(evaluationContext, semanticElement);
		}						
	}
	
	// Incoming
	protected String getIncomingReferencePropertyName() {
		return "incoming-reference";
	}

	@Override
	protected EReference getIncomingReference(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> incomingProcessorInfo) {
		String value = getPropertyValue(connection, getIncomingReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		return (EReference) semanticElement.eClass().getEStructuralFeature(value);
	}
	
	protected String getIncomingInjectorPropertyName() {
		return "incoming-injector";
	}
	
	@Override
	protected void setIncoming(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> incomingProcessorInfo) {
		super.setIncoming(config, semanticElement, connection, incomingProcessorInfo);
		String expr = getPropertyValue(config.getElement(), getIncomingInjectorPropertyName());
		if (!org.nasdanika.common.Util.isBlank(expr)) {
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(expr);
			EvaluationContext evaluationContext = createEvaluationContext();
			evaluationContext.setVariable("config", config);
			evaluationContext.setVariable("element", config.getElement());
			evaluationContext.setVariable("connection", connection);
			evaluationContext.setVariable("incoming", incomingProcessorInfo.getProcessor());
			evaluationContext.setVariable("incoingConfig", incomingProcessorInfo.getConfig());			
			exp.getValue(evaluationContext, semanticElement);
		}								
	}
	
	// Outgoing
	protected String getOutgoingReferencePropertyName() {
		return "outgoing-reference";
	}

	@Override
	protected EReference getOutgoingReference(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> outgoingProcessorInfo) {
		String value = getPropertyValue(connection, getOutgoingReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		return (EReference) semanticElement.eClass().getEStructuralFeature(value);
	}
	
	protected String getOutgoingInjectorPropertyName() {
		return "outgoing-injector";
	}
	
	@Override
	protected void setOutgoing(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> outgoingProcessorInfo) {
		super.setOutgoing(config, semanticElement, connection, outgoingProcessorInfo);
		String expr = getPropertyValue(config.getElement(), getOutgoingInjectorPropertyName());
		if (!org.nasdanika.common.Util.isBlank(expr)) {
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(expr);
			EvaluationContext evaluationContext = createEvaluationContext();
			evaluationContext.setVariable("config", config);
			evaluationContext.setVariable("element", config.getElement());
			evaluationContext.setVariable("connection", connection);
			evaluationContext.setVariable("outgoing", outgoingProcessorInfo.getProcessor());
			evaluationContext.setVariable("outgoingConfig", outgoingProcessorInfo.getConfig());			
			exp.getValue(evaluationContext, semanticElement);
		}								
	}
		
	// Children
	
	/**
	 * Value of child references property shall be a YAML map with reference names as keys and boolean Spel expressions as values. 
	 * @return
	 */
	protected String getChildReferencesPropertyName() {
		return "child-references";
	}

	@Override
	protected EReference getChildReference(ProcessorConfig<T> config, T semanticElement, Element child,	ProcessorInfo<T> childProcessorInfo) {
		if (config == null) {
			return null;
		}
		String value = getPropertyValue(config.getElement(), getChildReferencesPropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		Yaml yaml = new Yaml();
		Object yamlValue = yaml.load(value);
		if (yamlValue instanceof Map) {
			Map<?, ?> yamlMap = (Map<?,?>) yamlValue;
			for (Entry<?, ?> me: yamlMap.entrySet()) {
				Object key = me.getKey();
				if (key instanceof String) {
					Object eValue = me.getValue();
					if (eValue instanceof String) {
						ExpressionParser parser = new SpelExpressionParser();
						Expression exp = parser.parseExpression((String) eValue);
						EvaluationContext evaluationContext = createEvaluationContext();
						evaluationContext.setVariable("config", config);
						evaluationContext.setVariable("element", config.getElement());
						evaluationContext.setVariable("childElement", child);
						evaluationContext.setVariable("child", childProcessorInfo.getProcessor());
						evaluationContext.setVariable("childConfig", childProcessorInfo.getConfig());
						try {
							if (exp.getValue(evaluationContext, semanticElement, Boolean.class)) {
								return (EReference) semanticElement.eClass().getEStructuralFeature((String) key);																					
							}
						} catch (EvaluationException e) {
							// NOP - continue
						}
					} else {
						throw new IllegalArgumentException(getChildReferencesPropertyName() + " map values shall be strings, got " + eValue);						
					}
				} else {
					throw new IllegalArgumentException(getChildReferencesPropertyName() + " map keys shall be strings, got " + key);
				}
			}						
		}
		throw new IllegalArgumentException(getChildReferencesPropertyName() + " value shall be a YAML map, got " + yamlValue);
	}
	
	protected String getChildInjectorsPropertyName() {
		return "child-injectors";
	}
	
	@Override
	protected void setChildren(ProcessorConfig<T> config, T semanticElement, Map<Element, ProcessorInfo<T>> children) {
		super.setChildren(config, semanticElement, children);
		if (config != null) {
			String expr = getPropertyValue(config.getElement(), getChildInjectorsPropertyName());
			if (!org.nasdanika.common.Util.isBlank(expr)) {
				ExpressionParser parser = new SpelExpressionParser();
				Expression exp = parser.parseExpression(expr);
				EvaluationContext evaluationContext = createEvaluationContext();
				evaluationContext.setVariable("config", config);
				evaluationContext.setVariable("element", config.getElement());
				evaluationContext.setVariable("children", children);
				exp.getValue(evaluationContext, semanticElement);
			}
		}
	}
	
	// Registry
	protected String getRegistryReferencesPropertyName() {
		return "registry-references";
	}	

	@Override
	protected EReference getRegistryReference(ProcessorConfig<T> config, T semanticElement, Element registryElement, ProcessorInfo<T> registryElementProcessorInfo) {
		String value = getPropertyValue(config.getElement(), getRegistryReferencesPropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		Yaml yaml = new Yaml();
		Object yamlValue = yaml.load(value);
		if (yamlValue instanceof Map) {
			Map<?, ?> yamlMap = (Map<?,?>) yamlValue;
			for (Entry<?, ?> me: yamlMap.entrySet()) {
				Object key = me.getKey();
				if (key instanceof String) {
					Object eValue = me.getValue();
					if (eValue instanceof String) {
						ExpressionParser parser = new SpelExpressionParser();
						Expression exp = parser.parseExpression((String) eValue);
						EvaluationContext evaluationContext = createEvaluationContext();
						evaluationContext.setVariable("config", config);
						evaluationContext.setVariable("element", config.getElement());
						evaluationContext.setVariable("registryElement", registryElement);
						evaluationContext.setVariable("registrySemanticElement", registryElementProcessorInfo.getProcessor());
						evaluationContext.setVariable("registrySemanticElementConfig", registryElementProcessorInfo.getConfig());
						try {
							if (exp.getValue(evaluationContext, semanticElement, Boolean.class)) {
								return (EReference) semanticElement.eClass().getEStructuralFeature((String) key);																					
							}
						} catch (EvaluationException e) {
							// NOP - continue
						}
					} else {
						throw new IllegalArgumentException(getRegistryReferencesPropertyName() + " map values shall be strings, got " + eValue);						
					}
				} else {
					throw new IllegalArgumentException(getRegistryReferencesPropertyName() + " map keys shall be strings, got " + key);
				}
			}						
		}
		throw new IllegalArgumentException(getRegistryReferencesPropertyName() + " value shall be a YAML map, got " + yamlValue);
	}

	protected String getRegistryInjectorsPropertyName() {
		return "registry-injectors";
	}
	
	@Override
	protected void setRegistry(ProcessorConfig<T> config, T semanticElement, Map<Element, ProcessorInfo<T>> registry) {
		super.setRegistry(config, semanticElement, registry);
		Element element = config.getElement();
		if (element instanceof ModelElement) {
			Page linkedPage = ((ModelElement) element).getLinkedPage();
			if (linkedPage != null && !"false".equals(getPropertyValue(config.getElement(), getLinkPagePropertyName()))) {
				linkPage(config, semanticElement, registry, registry.get(linkedPage));
			}
		}				
		
		String expr = getPropertyValue(config.getElement(), getRegistryInjectorsPropertyName());
		if (!org.nasdanika.common.Util.isBlank(expr)) {
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(expr);
			EvaluationContext evaluationContext = createEvaluationContext();
			evaluationContext.setVariable("config", config);
			evaluationContext.setVariable("element", config.getElement());
			evaluationContext.setVariable("registry", registry);
			exp.getValue(evaluationContext, semanticElement);
		}								
	}
	

	protected String getLinkPagePropertyName() {
		return "link-page";
	}	
	
	/**
	 * Processes elements from the linked page. This implementation collects all page semantic elements without container and processes then as children of this element.
	 * @param config
	 * @param semanticElement
	 * @param registry
	 * @param likedPageInfo
	 */
	protected void linkPage(ProcessorConfig<T> config, T semanticElement, Map<Element, ProcessorInfo<T>> registry, ProcessorInfo<T> linkedPageInfo) {
		ProcessorInfo<T> thisInfo = ProcessorInfo.of(config, semanticElement);
		linkedPageInfo.getConfig().getElement().accept(pe -> {
			ProcessorInfo<T> re = registry.get(pe);
			if (re.getProcessor() != null && re.getProcessor().eContainer() == null) {
				setParent(re.getConfig(), re.getProcessor(), thisInfo);
			}
		});		
	}
	
}
