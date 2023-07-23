package org.nasdanika.graph.processor.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertySource;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;
import org.nasdanika.persistence.Marker;
import org.nasdanika.persistence.MarkerImpl;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.yaml.snakeyaml.Yaml;

/**
 * Resolves references from properties.
 * @author Pavel
 *
 */
public abstract class PropertySourceEObjectFactory<T extends EObject, P extends SemanticProcessor<T>> extends AbstractEObjectFactory<T,P> {
	
	protected String getPropertyValue(org.nasdanika.graph.Element element, String propertyName) {
		if (!org.nasdanika.common.Util.isBlank(propertyName) && element instanceof PropertySource) {
			@SuppressWarnings("unchecked")
			PropertySource<Object,?> propertySource = (PropertySource<Object,?>) element;
			for (String propertyPrefix: getPropertyPrefixes()) {
				Object propertyValue = propertySource.getProperty(propertyPrefix + propertyName);
				if (propertyValue instanceof String && !org.nasdanika.common.Util.isBlank((String) propertyValue)) {
					return (String) propertyValue;
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
	protected abstract URI getBaseURI();
	
	@Override
	protected Collection<T> createSemanticElements(ProcessorConfig config, ProgressMonitor progressMonitor) {
		String spec = getPropertyValue(config.getElement(), getSpecPropertyName());
		String specFormat = getPropertyValue(config.getElement(), getSpecFormatPropertyName());
		if (!org.nasdanika.common.Util.isBlank(spec)) {
			return load(spec, specFormat, getBaseURI(), config, getLoadingContext(config, progressMonitor), progressMonitor);
		}
				
		// Spec URI
		String specUriStr = getPropertyValue(config.getElement(), getSpecUriPropertyName());
		if (!org.nasdanika.common.Util.isBlank(specUriStr)) {
			URI specURI = URI.createURI(specUriStr);
			if (specURI.isRelative()) {
				URI baseURI = getBaseURI();
				if (baseURI != null && !baseURI.isRelative() && baseURI.isHierarchical()) {
					specURI = specURI.resolve(baseURI);
				}
			}
			return load(specURI, specFormat, config, getLoadingContext(config, progressMonitor), progressMonitor);
		}		
		
		// Semantic URI
		String semanticUriStr = getPropertyValue(config.getElement(), getSemanticUriPropertyName());
		if (!org.nasdanika.common.Util.isBlank(semanticUriStr)) {
			URI semanticURI = URI.createURI(semanticUriStr);
			if (semanticURI.isRelative()) {
				URI baseURI = getBaseURI();
				if (baseURI != null && !baseURI.isRelative() && baseURI.isHierarchical()) {
					semanticURI = semanticURI.resolve(baseURI);
				}
			}
			return load(semanticURI, specFormat, config, getLoadingContext(config, progressMonitor), progressMonitor);
		}
				
		return Collections.emptyList();
	}
	
	/**
	 * Override to create a context to be used for semantic element loading.
	 * @param config
	 * @param progressMonitor
	 * @return
	 */
	protected Context getLoadingContext(ProcessorConfig config, ProgressMonitor progressMonitor) {
		return Context.EMPTY_CONTEXT;
	}

	/**
	 * Loads semantic element from a URI
	 * @return
	 */
	protected Collection<T> load(URI specURI, String specFormat, ProcessorConfig config, Context context, ProgressMonitor progressMonitor) {
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
	protected Collection<T> load(URL url, String specFormat, ProcessorConfig config, Context context, ProgressMonitor progressMonitor) throws IOException {
		return load(url.openStream(), specFormat, URI.createURI(url.toString()), config, context, progressMonitor);
	}
	
	/**
	 * Loads semantic element from an input stream.
	 * @return
	 */
	protected Collection<T> load(InputStream inputStream, String specFormat, URI base, ProcessorConfig config, Context context, ProgressMonitor progressMonitor) throws IOException {
		try (Reader reader = new InputStreamReader(inputStream, getCharset())) {
			return load(DefaultConverter.INSTANCE.toString(reader), specFormat, base, config, context, progressMonitor);
		}		
	}
	
	/**
	 * Loads semantic element from an input stream.
	 * @return
	 */
	protected Collection<T> load(Reader reader, String specFormat, URI base, ProcessorConfig config, Context context, ProgressMonitor progressMonitor) throws IOException {
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
	protected abstract Collection<T> load(String spec, String specFormat, URI specBase, ProcessorConfig config, Context context, ProgressMonitor progressMonitor);
		
	protected String getSpecPropertyName() {
		return "spec";
	}
	
	protected String getSpecUriPropertyName() {
		return "spec-uri";
	}		
	
	protected String getSemanticUriPropertyName() {
		return "semantic-uri";
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
	
	protected Marked getMarked(ProcessorConfig config) {
		Element element = config.getElement();
		if (element instanceof Marked) {
			return (Marked) element;
		}
		return new Marked() {

			@Override
			public List<? extends Marker> getMarkers() {
				return Collections.singletonList(new MarkerImpl(null, String.valueOf(config.getElement())));
			}
			
		};
	}
	
	@Override
	protected EReference getParentChildReference(
			ProcessorInfo<P> info, 
			T semanticElement,
			ProcessorInfo<P> parentInfo, 
			T parentSemanticElement) {
		if (parentSemanticElement == null || info == null) {
			return null;
		}
		String value = getPropertyValue(info.getElement(), getChildReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		EClass eClass = parentSemanticElement.eClass();
		EStructuralFeature ref = eClass.getEStructuralFeature(value);
		if (ref instanceof EReference) {
			return (EReference) ref;
		}
		throw new ConfigurationException("Reference '" + value + "' not found in " + eClass.getName() + " " + eClass.getEPackage().getNsURI(), getMarked(info));
	}	
	
	@Override
	protected EReference getChildReference(
			ProcessorInfo<P> info, 
			T semanticElement, 
			ProcessorInfo<P> childInfo, 
			T semanticChild) {
		if (info == null) {
			return null;
		}
		String value = getPropertyValue(info.getElement(), getChildReferencesPropertyName());
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
						evaluationContext.setVariable("parentInfo", info);
						evaluationContext.setVariable("parent", semanticElement);
						evaluationContext.setVariable("info", childInfo);
						try {
							if (exp.getValue(evaluationContext, childInfo.getProcessor(), Boolean.class)) { 
								EClass eClass = semanticElement.eClass();
								EStructuralFeature ref = eClass.getEStructuralFeature((String) key);
								if (ref instanceof EReference) {
									return (EReference) ref;
								}
								throw new ConfigurationException("Reference '" + key + "' not found in " + eClass.getName() + " " + eClass.getEPackage().getNsURI(), getMarked(info));
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
	
	// Parent
	protected String getParentReferencePropertyName() {
		return "parent-reference";
	}
	
	@Override
	protected EReference getParentReference(
			ProcessorInfo<P> info,
			T semanticElement,
			ProcessorInfo<P> parentInfo,
			T parentSemanticElement) {
		if (info == null) {
			return null;
		}
		String value = getPropertyValue(info.getElement(), getParentReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		
		EClass eClass = semanticElement.eClass();
		EStructuralFeature ref = eClass.getEStructuralFeature(value);
		if (ref instanceof EReference) {
			return (EReference) ref;
		}
		throw new ConfigurationException("Reference '" + value + "' not found in " + eClass.getName() + " " + eClass.getEPackage().getNsURI(), getMarked(info));
	}	
	
	protected String getParentInjectorPropertyName() {
		return "parent-injector";
	}
	
	@Override
	protected void setParent(
			ProcessorInfo<P> info,
			ProcessorInfo<P> parentInfo,
			ProgressMonitor progressMonitor) {
		
		super.setParent(info, parentInfo, progressMonitor);
		if (info != null) {
			String expr = getPropertyValue(info.getElement(), getParentInjectorPropertyName());
			if (!org.nasdanika.common.Util.isBlank(expr) && info.getProcessor() != null && parentInfo.getProcessor() != null) {
				for (T semanticElement: info.getProcessor().getSemanticElements()) {
					for (T parentSemanticElement: parentInfo.getProcessor().getSemanticElements()) {
						ExpressionParser parser = new SpelExpressionParser();
						Expression exp = parser.parseExpression(expr);
						EvaluationContext evaluationContext = createEvaluationContext();
						evaluationContext.setVariable("info", info);
						evaluationContext.setVariable("parentInfo", parentInfo);			
						evaluationContext.setVariable("parentSemanticElement", parentSemanticElement);
						exp.getValue(evaluationContext, semanticElement);					
					}
				}
			}
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
	protected EReference getSourceReference(
			ConnectionProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config,
			P processor, 
			T semanticElement, 
			ProcessorInfo<P> sourceProcessorInfo, 
			T sourceSemanticElement) {
		String value = getPropertyValue(config.getElement(), getSourceReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		EClass eClass = semanticElement.eClass();
		EStructuralFeature ref = eClass.getEStructuralFeature(value);
		if (ref instanceof EReference) {
			return (EReference) ref;
		}
		throw new ConfigurationException("Reference '" + value + "' not found in " + eClass.getName() + " " + eClass.getEPackage().getNsURI(), getMarked(config));
	}
	
	protected String getSourceInjectorPropertyName() {
		return "source-injector";
	}
	
	@Override
	protected void setSource(
			ConnectionProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config, 
			P processor,
			ProcessorInfo<P> sourceProcessorInfo, 
			ProgressMonitor progressMonitor) {
		
		super.setSource(config, processor, sourceProcessorInfo, progressMonitor);
		String expr = getPropertyValue(config.getElement(), getSourceInjectorPropertyName());
		P sourceProcessor = sourceProcessorInfo.getProcessor(); 
		if (!org.nasdanika.common.Util.isBlank(expr) && processor != null && sourceProcessor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (T sourceSemanticElement: sourceProcessor.getSemanticElements()) {
					ExpressionParser parser = new SpelExpressionParser();
					Expression exp = parser.parseExpression(expr);
					EvaluationContext evaluationContext = createEvaluationContext();
					evaluationContext.setVariable("config", config);
					evaluationContext.setVariable("sourceProcessor", sourceProcessor);
					evaluationContext.setVariable("sourceSemanticElement", sourceSemanticElement);
					evaluationContext.setVariable("sourceInfo", sourceProcessorInfo);			
					exp.getValue(evaluationContext, semanticElement);					
				}
			}
		}
	}
	
	// Target
	protected String getTargetReferencePropertyName() {
		return "target-reference";
	}
	
	@Override
	protected EReference getTargetReference(
			ConnectionProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config,
			P processor, 
			T semanticElement, 
			ProcessorInfo<P> targetProcessorInfo, 
			T targetSemanticElement) {
		String value = getPropertyValue(config.getElement(), getTargetReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		EClass eClass = semanticElement.eClass();
		EStructuralFeature ref = eClass.getEStructuralFeature(value);
		if (ref instanceof EReference) {
			return (EReference) ref;
		}
		throw new ConfigurationException("Reference '" + value + "' not found in " + eClass.getName() + " " + eClass.getEPackage().getNsURI(), getMarked(config));
	}
	
	protected String getTargetInjectorPropertyName() {
		return "target-injector";
	}
	
	@Override
	protected void setTarget(
			ConnectionProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config, 
			P processor,
			ProcessorInfo<P> targetProcessorInfo, 
			ProgressMonitor progressMonitor) {
		
		super.setTarget(config, processor, targetProcessorInfo, progressMonitor);
		String expr = getPropertyValue(config.getElement(), getTargetInjectorPropertyName());
		P targetProcessor = targetProcessorInfo.getProcessor();
		if (!org.nasdanika.common.Util.isBlank(expr) && processor != null && targetProcessor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (T targetSemanticElement: targetProcessor.getSemanticElements()) {
					ExpressionParser parser = new SpelExpressionParser();
					Expression exp = parser.parseExpression(expr);
					EvaluationContext evaluationContext = createEvaluationContext();
					evaluationContext.setVariable("config", config);
					evaluationContext.setVariable("targetProcessor", targetProcessor);
					evaluationContext.setVariable("targetSemanticElement", targetSemanticElement);
					evaluationContext.setVariable("targetInfo", targetProcessorInfo);			
					exp.getValue(evaluationContext, semanticElement);
				}
			}
		}
	}
	
	// Incoming
	protected String getIncomingReferencePropertyName() {
		return "incoming-reference";
	}
	
	@Override
	protected EReference getIncomingReference(
			NodeProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config,
			P processor, T semanticElement, Connection connection, ProcessorInfo<P> incomingProcessorInfo,
			T incomingSemanticElement) {
		String value = getPropertyValue(connection, getIncomingReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		EClass eClass = semanticElement.eClass();
		EStructuralFeature ref = eClass.getEStructuralFeature(value);
		if (ref instanceof EReference) {
			return (EReference) ref;
		}
		throw new ConfigurationException("Reference '" + value + "' not found in " + eClass.getName() + " " + eClass.getEPackage().getNsURI(), getMarked(config));
	}
	
	protected String getIncomingInjectorPropertyName() {
		return "incoming-injector";
	}
	
	@Override
	protected void setIncoming(
			NodeProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config, 
			P processor,
			Connection connection, 
			ProcessorInfo<P> incomingProcessorInfo, 
			ProgressMonitor progressMonitor) {
		
		super.setIncoming(config, processor, connection, incomingProcessorInfo, progressMonitor);
		String expr = getPropertyValue(connection, getIncomingInjectorPropertyName());
		P incomingProcessor = incomingProcessorInfo.getProcessor();
		if (!org.nasdanika.common.Util.isBlank(expr) && processor != null && incomingProcessor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (T incomingSemanticElement: incomingProcessor.getSemanticElements()) {
					ExpressionParser parser = new SpelExpressionParser();
					Expression exp = parser.parseExpression(expr);
					EvaluationContext evaluationContext = createEvaluationContext();
					evaluationContext.setVariable("config", config);
					evaluationContext.setVariable("connection", connection);
					evaluationContext.setVariable("incomingProcessor", incomingProcessor);
					evaluationContext.setVariable("incomingSemanticElement", incomingSemanticElement);
					evaluationContext.setVariable("incomingInfo", incomingProcessorInfo);			
					exp.getValue(evaluationContext, semanticElement);
				}
			}
		}
	}
	
	// Outgoing
	protected String getOutgoingReferencePropertyName() {
		return "outgoing-reference";
	}
	
	@Override
	protected EReference getOutgoingReference(
			NodeProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config,
			P processor, 
			T semanticElement, 
			Connection connection, 
			ProcessorInfo<P> outgoingProcessorInfo,
			T outgoingSemanticElement) {
		String value = getPropertyValue(connection, getOutgoingReferencePropertyName());
		if (org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		EClass eClass = semanticElement.eClass();
		EStructuralFeature ref = eClass.getEStructuralFeature(value);
		if (ref instanceof EReference) {
			return (EReference) ref;
		}
		throw new ConfigurationException("Reference '" + value + "' not found in " + eClass.getName() + " " + eClass.getEPackage().getNsURI(), getMarked(config));
	}
	
	protected String getOutgoingInjectorPropertyName() {
		return "outgoing-injector";
	}
	
	@Override
	protected void setOutgoing(
			NodeProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config, 
			P processor,
			Connection connection, 
			ProcessorInfo<P> outgoingProcessorInfo, 
			ProgressMonitor progressMonitor) {
		super.setOutgoing(config, processor, connection, outgoingProcessorInfo, progressMonitor);
		P outgoingProcessor = outgoingProcessorInfo.getProcessor();
		String expr = getPropertyValue(connection, getOutgoingInjectorPropertyName());
		if (!org.nasdanika.common.Util.isBlank(expr) && processor != null && outgoingProcessor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (T outgoingSemanticElement: outgoingProcessor.getSemanticElements()) {
					ExpressionParser parser = new SpelExpressionParser();
					Expression exp = parser.parseExpression(expr);
					EvaluationContext evaluationContext = createEvaluationContext();
					evaluationContext.setVariable("config", config);
					evaluationContext.setVariable("connection", connection);
					evaluationContext.setVariable("outgoingProcessor", outgoingProcessor);
					evaluationContext.setVariable("outgoingSemanticElement", outgoingSemanticElement);
					evaluationContext.setVariable("outgoingInfo", outgoingProcessorInfo);			
					exp.getValue(evaluationContext, semanticElement);
				}
			}
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
	
	protected String getChildInjectorsPropertyName() {
		return "child-injectors";
	}
	
	@Override
	protected void setChildren(
			ProcessorInfo<P> info, 
			Map<Element, ProcessorConfig> children,
			Function<Element, CompletionStage<ProcessorInfo<P>>> infoProvider,
			Consumer<CompletionStage<?>> stageConsumer, boolean parallel, ProgressMonitor progressMonitor) {

		super.setChildren(info, children, infoProvider, stageConsumer, parallel, progressMonitor);
		if (info != null && info.getProcessor() != null) {
			String expr = getPropertyValue(info.getElement(), getChildInjectorsPropertyName());
			if (!org.nasdanika.common.Util.isBlank(expr)) {
				for (T semanticElement: info.getProcessor().getSemanticElements()) {
					ExpressionParser parser = new SpelExpressionParser();
					Expression exp = parser.parseExpression(expr);
					EvaluationContext evaluationContext = createEvaluationContext();
					evaluationContext.setVariable("info", info);
					evaluationContext.setVariable("children", children);
					exp.getValue(evaluationContext, semanticElement);
				}
			}
		}
	}
	
	// Registry
	protected String getRegistryReferencesPropertyName() {
		return "registry-references";
	}
	
	@Override
	protected EReference getRegistryReference(
			ProcessorInfo<P> info, 
			T semanticElement, 
			Element registryElement,
			ProcessorInfo<P> registryElementProcessorInfo, 
			T registrySemanticElement) {

		if (registrySemanticElement == null) {
			return null;
		}
		
		String value = getPropertyValue(info.getElement(), getRegistryReferencesPropertyName());
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
						evaluationContext.setVariable("info", info);
						evaluationContext.setVariable("registryElement", registryElement);
						evaluationContext.setVariable("registryInfo", registryElementProcessorInfo);
						evaluationContext.setVariable("semanticElement", semanticElement);
						try {
							if (exp.getValue(evaluationContext, registrySemanticElement, Boolean.class)) {
								EClass eClass = semanticElement.eClass();
								EStructuralFeature ref = eClass.getEStructuralFeature((String) key);
								if (ref instanceof EReference) {
									return (EReference) ref;
								}
								throw new ConfigurationException("Reference '" + key + "' not found in " + eClass.getName() + " " + eClass.getEPackage().getNsURI(), getMarked(info));
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
	protected void setRegistry(
			ProcessorInfo<P> info,
			Function<Element, CompletionStage<ProcessorInfo<P>>> processorInfoProvider,
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {
		super.setRegistry(info, processorInfoProvider, stageConsumer, progressMonitor);
		String expr = getPropertyValue(info.getElement(), getRegistryInjectorsPropertyName());
		if (info.getProcessor() != null && !org.nasdanika.common.Util.isBlank(expr)) {
			for (T semanticElement: info.getProcessor().getSemanticElements()) {
				ExpressionParser parser = new SpelExpressionParser();
				Expression exp = parser.parseExpression(expr);
				EvaluationContext evaluationContext = createEvaluationContext();
				evaluationContext.setVariable("info", info);
				exp.getValue(evaluationContext, semanticElement);
			}
		}								
	}	

	protected String getLinkPagePropertyName() {
		return "link-page";
	}	
	
}