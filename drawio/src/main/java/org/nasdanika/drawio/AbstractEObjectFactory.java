package org.nasdanika.drawio;

import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.NopEndpointProcessorFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * Creates {@link EObject}s from graph elements and wires them together using diagram element properties.
 * @author Pavel
 *
 */
public abstract class AbstractEObjectFactory implements NopEndpointProcessorFactory<EObject,EObject> {
	
	@Override
	public ProcessorInfo<EObject> createProcessor(ProcessorConfig<EObject> config) {
		EObject semanticElement = createSemanticElement(config);
		if (semanticElement == null) {
			if (config instanceof ConnectionProcessorConfig) {
				// Pass-through wiring source endpoint to target handler and target endpoint to source handler
				ConnectionProcessorConfig<EObject, EObject, EObject> connectionConfig = (ConnectionProcessorConfig<EObject, EObject, EObject>) config;
				connectionConfig.getSourceEndpoint().thenAccept(connectionConfig::setTargetHandler);
				connectionConfig.getTargetEndpoint().thenAccept(connectionConfig::setSourceHandler);
			}
		} else {
			// Wiring			
			// Parent
			Consumer<ProcessorInfo<EObject>> parentProcessorInfoConsumer = new Consumer<ProcessorInfo<EObject>>() {

				@Override
				public void accept(ProcessorInfo<EObject> parentProcessorInfo) {
					if (semanticElement.eContainer() == null) {
						if (parentProcessorInfo.getProcessor() == null) {
							// No processor, need to go up to the parent.
							parentProcessorInfo.getConfig().getParentProcessorInfo().thenAccept(this);
						} else {
							777
						}										
					}
				}
				
			};
					
			config.getParentProcessorInfo().thenAccept(parentProcessorInfoConsumer);
			
			if (config instanceof NodeProcessorConfig) {
				NodeProcessorConfig<EObject, EObject, EObject> nodeConfig = (NodeProcessorConfig<EObject, EObject, EObject>) config;
				
			} else if (config instanceof ConnectionProcessorConfig) {
				ConnectionProcessorConfig<EObject, EObject, EObject> connectionConfig = (ConnectionProcessorConfig<EObject, EObject, EObject>) config;
				
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
	protected abstract EObject createSemanticElement(ProcessorConfig<EObject> config);

}
