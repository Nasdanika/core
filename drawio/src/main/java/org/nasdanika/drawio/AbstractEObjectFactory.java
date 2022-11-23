package org.nasdanika.drawio;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.NopEndpointProcessorFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * Creates {@link EObject}s from graph elements and wires them together using diagram element properties.
 * Uses {@link ProcessorInfo} as handler and enpoint type.
 * @author Pavel
 *
 */
public abstract class AbstractEObjectFactory<T extends EObject> implements NopEndpointProcessorFactory<T, ProcessorInfo<EObject>> {
	
	@SuppressWarnings("unchecked")
	@Override
	public ProcessorInfo<T> createProcessor(ProcessorConfig<T> config) {
		T semanticElement = createSemanticElement(config);
		if (semanticElement == null) {
			if (config instanceof ConnectionProcessorConfig) {
				// Pass-through wiring source endpoint to target handler and target endpoint to source handler
				ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> connectionConfig = (ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>>) config;
				connectionConfig.getSourceEndpoint().thenAccept(connectionConfig::setTargetHandler);
				connectionConfig.getTargetEndpoint().thenAccept(connectionConfig::setSourceHandler);
			}
		} else {
			// Wiring			

			// Parent
			config.getParentProcessorInfo().thenAccept(parentInfo -> setParent(config, semanticElement, parentInfo));
			
			if (config instanceof NodeProcessorConfig) {
				NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> nodeConfig = (NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>>) config;
				
			} else if (config instanceof ConnectionProcessorConfig) {
				ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> connectionConfig = (ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>>) config;
				
			}			
		}		
		
		return ProcessorInfo.of(config, semanticElement);
	}
	
	@Override
	public boolean isPassThrough(Connection connection) {
		return false;
	}
	
	/**
	 * Creates a semantic element for a diagram element. May return null if there is no semantic mapping for a given diagram element.
	 * @param config
	 * @return
	 */	
	protected abstract T createSemanticElement(ProcessorConfig<T> config);
	
	/**
	 * Sets parent. If parent is null this method sets itself to be called for the grand parent etc. 
	 * If child reference property is set then the semantic element is injected into the parent's references specified in 
	 * the child reference property. Otherwise, if parent reference property is set then the parent is injected into the semantic element's 
	 * reference specified in the parent reference property.
	 * @param semanticElement
	 * @param parentProcessorInfo
	 */
	protected void setParent(ProcessorConfig<T> config, T semanticElement, ProcessorInfo<T> parentProcessorInfo) {
		if (parentProcessorInfo.getProcessor() == null) {
			// No processor, need to go up to the parent.
			parentProcessorInfo.getConfig().getParentProcessorInfo().thenAccept(grandParentProcessorInfo -> setParent(config, semanticElement, grandParentProcessorInfo));
		} else {
			String childReferenceName = getPropertyValue(config.getElement(), getChildReferencePropertyName());
		}												
	}	
	
	protected String getChildReferencePropertyName() {
		return "child-reference";
	}
	
	protected String getParentReferencePropertyName() {
		return "parent-reference";
	}
	
	protected String getPropertyValue(org.nasdanika.graph.Element element, String propertyName) {
		if (!org.nasdanika.common.Util.isBlank(propertyName) && element instanceof ModelElement) {
			return ((ModelElement) element).getProperty(propertyName);
		}
		return null;
	}

}
