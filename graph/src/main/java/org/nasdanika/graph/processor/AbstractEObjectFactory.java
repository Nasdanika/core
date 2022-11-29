package org.nasdanika.graph.processor;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;

/**
 * Creates {@link EObject}s from graph elements and wires them together using diagram element properties.
 * Uses {@link ProcessorInfo} as handler and enpoint type.
 * @author Pavel
 *
 */
public abstract class AbstractEObjectFactory<T extends EObject> implements NopEndpointProcessorFactory<T, ProcessorInfo<EObject>> {
	
	@SuppressWarnings("unchecked")
	@Override
	public ProcessorInfo<T> createProcessor(ProcessorConfig<T> config, ProgressMonitor progressMonitor) {
		T semanticElement = createSemanticElement(config, progressMonitor);
		ProcessorInfo<T> processorInfo = ProcessorInfo.of(config, semanticElement);
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
			
			// Children
			setChildren(config, semanticElement, config.getChildProcessorsInfo());
			
			// Registry
			config.getRegistry().thenAccept(registry -> setRegistry(config, semanticElement, registry));
			
			if (config instanceof NodeProcessorConfig) {
				NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> nodeConfig = (NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>>) config;
				semanticElement.eAdapters().add(new NodeProcessorConfigAdapter<>(nodeConfig));
				
				// Incoming 
				for (Entry<Connection, CompletionStage<ProcessorInfo<T>>> ie: nodeConfig.getIncomingEndpoints().entrySet()) {
					ie.getValue().thenAccept(incomingInfo -> setIncoming(nodeConfig, semanticElement, ie.getKey(), incomingInfo));
				}
				
				// Outgoing
				for (Entry<Connection, CompletionStage<ProcessorInfo<T>>> oe: nodeConfig.getOutgoingEndpoints().entrySet()) {
					oe.getValue().thenAccept(outgoingInfo -> setOutgoing(nodeConfig, semanticElement, oe.getKey(), outgoingInfo));
				}
				
				nodeConfig.getIncomingHandlerConsumers().forEach((k,v) -> v.accept(processorInfo));
				nodeConfig.getOutgoingHandlerConsumers().forEach((k,v) -> v.accept(processorInfo));				
			} else if (config instanceof ConnectionProcessorConfig) {
				ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> connectionConfig = (ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>>) config;
				semanticElement.eAdapters().add(new ConnectionProcessorConfigAdapter<>(connectionConfig));
				
				// Source
				connectionConfig.getSourceEndpoint().thenAccept(sourceInfo -> setTarget(connectionConfig, semanticElement, sourceInfo));
				
				// Target
				connectionConfig.getTargetEndpoint().thenAccept(targetInfo -> setTarget(connectionConfig, semanticElement, targetInfo));

				connectionConfig.setSourceHandler(processorInfo);
				connectionConfig.setTargetHandler(processorInfo);				
			} else {
				semanticElement.eAdapters().add(new ProcessorConfigAdapter<T, ProcessorConfig<T>>(config));
			}
		}		
		
		return processorInfo;
	}
	
	/**
	 * Sets element children.
	 * @param config
	 * @param semanticElement
	 * @param children
	 */
	@SuppressWarnings("unchecked")
	protected void setChildren(ProcessorConfig<T> config, T semanticElement, Map<Element, ProcessorInfo<T>> children) {
		for (Entry<Element, ProcessorInfo<T>> ce: children.entrySet()) {
			T child = ce.getValue().getProcessor();
			if (child != null) {
				EReference childReference = getChildReference(config, semanticElement, ce.getKey(), ce.getValue());
				if (childReference != null) {
					if (childReference.isMany()) {
						((Collection<EObject>) semanticElement.eGet(childReference)).add(child);
					} else {
						semanticElement.eSet(childReference, child);
					}
				}
			}																	
		}
	}
	
	/**
	 * Sets registry. This method does nothing.
	 * @param config
	 * @param semanticElement
	 * @param registry
	 */
	@SuppressWarnings("unchecked")
	protected void setRegistry(ProcessorConfig<T> config, T semanticElement, Map<Element, ProcessorInfo<T>> registry) {
		for (Entry<Element, ProcessorInfo<T>> re: registry.entrySet()) {
			T registryElement = re.getValue().getProcessor();
			if (registryElement != null) {
				EReference registryReference = getRegistryReference(config, semanticElement, re.getKey(), re.getValue());
				if (registryReference != null) {
					if (registryReference.isMany()) {
						((Collection<EObject>) semanticElement.eGet(registryReference)).add(registryElement);
					} else {
						semanticElement.eSet(registryReference, registryElement);
					}
				}
			}																	
		}		
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
	protected abstract T createSemanticElement(ProcessorConfig<T> config, ProgressMonitor progressMonitor);
	
	/**
	 * Sets parent. If parent is null this method sets itself to be called for the grand parent etc. 
	 * If child reference property is not null then the semantic element is injected into the parent's references specified in 
	 * the child reference property. Otherwise, if parent reference property is not null then the parent is injected into the semantic element's 
	 * reference specified in the parent reference property.
	 * @param semanticElement
	 * @param parentProcessorInfo
	 */
	@SuppressWarnings("unchecked")
	protected void setParent(ProcessorConfig<T> config, T semanticElement, ProcessorInfo<T> parentProcessorInfo) {
		T parent = parentProcessorInfo.getProcessor();
		if (parent == null) {
			// No processor, need to go up to the parent.
			ProcessorConfig<T> parentConfig = parentProcessorInfo.getConfig();
			if (parentConfig != null) {
				parentConfig.getParentProcessorInfo().thenAccept(grandParentProcessorInfo -> setParent(config, semanticElement, grandParentProcessorInfo));
			}
		} else {
			EReference childReference = getChildReference(config, semanticElement, parentProcessorInfo);
			if (childReference == null) {
				EReference parentReference = getParentReference(config, semanticElement, parentProcessorInfo);
				if (parentReference != null) {
					if (parentReference.isMany()) {
						((Collection<EObject>) semanticElement.eGet(parentReference)).add(parent);
					} else {
						semanticElement.eSet(parentReference, parent);
					}					
				}
			} else {
				if (childReference.isMany()) {
					((Collection<EObject>) parent.eGet(childReference)).add(semanticElement);
				} else {
					parent.eSet(childReference, semanticElement);
				}
			}
		}												
	}
	
	/**
	 * This method calls protected setParent() and setChildren() methods to establish parent/child relationships between semantic elements
	 * using data from the graph elements from which these semantic elements were created.
	 * This method uses {@link ProcessorConfigAdapter}s to retrieve graph configs to pass to setParent() and setChildren()  
	 * @param child
	 * @param parent
	 */
	@SuppressWarnings("unchecked")
	public void setParent(T child, T parent) {
		if (child != null && parent != null) {
			ProcessorConfig<T> childConfig = (ProcessorConfig<T>) EcoreUtil.getRegisteredAdapter(child, ProcessorConfig.class);
			ProcessorConfig<T> parentConfig = (ProcessorConfig<T>) EcoreUtil.getRegisteredAdapter(parent, ProcessorConfig.class);
			setParent(childConfig, child, ProcessorInfo.of(parentConfig, parent));
			setChildren(parentConfig, parent, Collections.singletonMap(childConfig == null ? null : childConfig.getElement(), ProcessorInfo.of(childConfig, child)));
		}
	}
	
	/**
	 * Returns parent's {@link EReference} to add the semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getChildReference(ProcessorConfig<T> config, T semanticElement, ProcessorInfo<T> parentProcessorInfo);
	
	/**
	 * Returns semantic element's {@link EReference} to add a child semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getChildReference(ProcessorConfig<T> config, T semanticElement, Element child, ProcessorInfo<T> childProcessorInfo);
	
	/**
	 * Returns semantic element's {@link EReference} to add a registry semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getRegistryReference(ProcessorConfig<T> config, T semanticElement, Element registryElement, ProcessorInfo<T> registryElementProcessorInfo);
	
	/**
	 * Returns semantic element's {@link EReference} to add the parent to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getParentReference(ProcessorConfig<T> config, T semanticElement, ProcessorInfo<T> parentProcessorInfo);
		
	/**
	 * Sets connection semantic element source if source reference property is not null.  
	 * @param semanticElement
	 * @param sourceProcessorInfo
	 */
	@SuppressWarnings("unchecked")
	protected void setSource(ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, ProcessorInfo<T> sourceProcessorInfo) {
		T source = sourceProcessorInfo.getProcessor();
		if (source != null) {
			EReference sourceReference = getSourceReference(config, semanticElement, sourceProcessorInfo);
			if (sourceReference != null) {
				if (sourceReference.isMany()) {
					((Collection<EObject>) semanticElement.eGet(sourceReference)).add(source);
				} else {
					semanticElement.eSet(sourceReference, source);
				}
			}
		}												
	}
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the source to.
	 * @param config
	 * @param semanticElement
	 * @param sourceProcessorInfo
	 * @return
	 */
	protected abstract EReference getSourceReference(ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, ProcessorInfo<T> sourceProcessorInfo);	
	
	/**
	 * Sets connection semantic element target if target reference property is not null.  
	 * @param semanticElement
	 * @param targetProcessorInfo
	 */
	@SuppressWarnings("unchecked")
	protected void setTarget(ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, ProcessorInfo<T> targetProcessorInfo) {
		T target = targetProcessorInfo.getProcessor();
		if (target != null) {
			EReference targetReference = getTargetReference(config, semanticElement, targetProcessorInfo);
			if (targetReference != null) {
				if (targetReference.isMany()) {
					((Collection<EObject>) semanticElement.eGet(targetReference)).add(target);
				} else {
					semanticElement.eSet(targetReference, target);
				}
			}
		}												
	}
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the target to.
	 * @param config
	 * @param semanticElement
	 * @param targetProcessorInfo
	 * @return
	 */
	protected abstract EReference getTargetReference(ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, ProcessorInfo<T> targetProcessorInfo);
	
	/**
	 * Sets node semantic element incoming semantic element (connection's or source's if connection semantic element is null and the connection is pass-through) if incoming reference property is not null.  
	 * @param config
	 * @param semanticElement
	 * @param connection
	 * @param incomingProcessorInfo
	 */
	@SuppressWarnings("unchecked")
	protected void setIncoming(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> incomingProcessorInfo) {
		T incoming = incomingProcessorInfo.getProcessor();
		if (incoming != null) {
			EReference incomingReference = getIncomingReference(config, semanticElement, connection, incomingProcessorInfo);
			if (incomingReference != null) {
				if (incomingReference.isMany()) {
					((Collection<EObject>) semanticElement.eGet(incomingReference)).add(incoming);
				} else {
					semanticElement.eSet(incomingReference, incoming);
				}
			}
		}												
	}
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the incoming semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param incomingProcessorInfo
	 * @return
	 */
	protected abstract EReference getIncomingReference(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> incomingProcessorInfo);	
		
	/**
	 * Sets node semantic element outgoing semantic element (connection's or target's if connection semantic element is null and the connection is pass-through) if outgoing reference property is not null.
	 * @param config  
	 * @param semanticElement
	 * @param connection.
	 * @param outgoingProcessorInfo
	 */
	@SuppressWarnings("unchecked")
	protected void setOutgoing(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> outgoingProcessorInfo) {
		T outgoing = outgoingProcessorInfo.getProcessor();
		if (outgoing != null) {
			EReference outgoingReference = getOutgoingReference(config, semanticElement, connection, outgoingProcessorInfo);
			if (outgoingReference != null) {
				if (outgoingReference.isMany()) {
					((Collection<EObject>) semanticElement.eGet(outgoingReference)).add(outgoing);
				} else {
					semanticElement.eSet(outgoingReference, outgoing);
				}
			}
		}												
	}
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the outgoing semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param connection
	 * @param outgoingProcessorInfo
	 * @return
	 */
	protected abstract EReference getOutgoingReference(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> outgoingProcessorInfo);	

}
