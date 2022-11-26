package org.nasdanika.drawio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.AbstractEObjectFactory;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Creates {@link EObject}s from graph elements and wires them together using diagram element properties.
 * This is a base class. Subclasses shall override one or more load() methods depending on their needs.
 * @author Pavel
 *
 */
public class DrawioEObjectFactory<T extends EObject> extends AbstractEObjectFactory<T> {
	
	protected String getPropertyValue(org.nasdanika.graph.Element element, String propertyName) {
		if (!org.nasdanika.common.Util.isBlank(propertyName) && element instanceof ModelElement) {
			return ((ModelElement) element).getProperty(getPropertyPrefix() + propertyName);
		}
		return null;
	}
	
	/**
	 * Prefix added to property names. Override to implement namespacing of properties for elements with multiple semantic mappings. 
	 * For example, an element may have one mapping for documentation, another for code generation, and one more for status updates - pulling runtime information from monitoring systems and updating diagram elements with health status. 
	 * @return
	 */
	protected String getPropertyPrefix() {
		return "";
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
		if (!org.nasdanika.common.Util.isBlank(spec)) {
			return load(spec, getBaseURI(), progressMonitor);
		}
		String specRef = getPropertyValue(config.getElement(), getSpecRefPropertyName());
		if (!org.nasdanika.common.Util.isBlank(specRef)) {
			URI specURI = URI.createURI(specRef);
			if (specURI.isRelative()) {
				URI baseURI = getBaseURI();
				if (baseURI != null && !baseURI.isRelative()) {
					specURI = specURI.resolve(baseURI);
				}
			}
			return load(specURI, progressMonitor);
		}
		return null;
	}
	
	/**
	 * Loads semantic element from a URI
	 * @return
	 */
	protected T load(URI specURI, ProgressMonitor progressMonitor) {
		try {
			URL specUrl = new URL(specURI.toString());
			return load(specUrl, progressMonitor);				
		} catch (IOException e) {
			throw new NasdanikaException("Error loading specification from specification URI " + specURI, e);
		}			
	}	
	
	/**
	 * Loads semantic element from a URL
	 * @return
	 */
	protected T load(URL url, ProgressMonitor progressMonitor) throws IOException {
		return load(url.openStream(), URI.createURI(url.toString()), progressMonitor);
	}
	
	/**
	 * Loads semantic element from an input stream.
	 * @return
	 */
	protected T load(InputStream inputStream, URI base, ProgressMonitor progressMonitor) throws IOException {
		try (Reader reader = new InputStreamReader(inputStream, getCharset())) {
			return load(DefaultConverter.INSTANCE.toString(reader), base, progressMonitor);
		}		
	}
	
	/**
	 * Loads semantic element from an input stream.
	 * @return
	 */
	protected T load(Reader reader, URI base, ProgressMonitor progressMonitor) throws IOException {
		return load(DefaultConverter.INSTANCE.toString(reader), base, progressMonitor);
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
	protected T load(String spec, URI specBase, ProgressMonitor progressMonitor) {
		throw new UnsupportedOperationException();
	}
		
	protected String getSpecPropertyName() {
		return "spec";
	}
	
	protected String getSpecRefPropertyName() {
		return "spec-ref";
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
		// TODO Auto-generated method stub
		return null;
	}
	
	protected String getSourceInjectorPropertyName() {
		return "source-injector";
	}
	
	// Target
	protected String getTargetReferencePropertyName() {
		return "target-reference";
	}

	@Override
	protected EReference getTargetReference(ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, ProcessorInfo<T> targetProcessorInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected String getTargetInjectorPropertyName() {
		return "target-injector";
	}
	
	// Incoming
	protected String getIncomingReferencePropertyName() {
		return "incoming-reference";
	}
	
	protected String getIncomingInjectorPropertyName() {
		return "incoming-injector";
	}

	@Override
	protected EReference getIncomingReference(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> incomingProcessorInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Outgoing
	protected String getOutgoingReferencePropertyName() {
		return "outgoing-reference";
	}

	@Override
	protected EReference getOutgoingReference(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> outgoingProcessorInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected String getOutgoingInjectorPropertyName() {
		return "outgoing-injector";
	}
		
	// Children
	protected String getChildReferencesPropertyName() {
		return "child-references";
	}

	@Override
	protected EReference getChildReference(ProcessorConfig<T> config, T semanticElement, Element child,	ProcessorInfo<T> childProcessorInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected String getChildInjectorsPropertyName() {
		return "child-injectors";
	}
	
	// Registry
	protected String getRegistryReferencesPropertyName() {
		return "registry-references";
	}	

	@Override
	protected EReference getRegistryReference(ProcessorConfig<T> config, T semanticElement, Element registryElement, ProcessorInfo<T> registryElementProcessorInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	protected String getRegistryInjectorsPropertyName() {
		return "registry-injectors";
	}
	
}
