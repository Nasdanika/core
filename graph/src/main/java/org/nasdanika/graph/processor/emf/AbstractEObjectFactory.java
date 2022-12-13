package org.nasdanika.graph.processor.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
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
public abstract class AbstractEObjectFactory<T extends EObject, P extends SemanticProcessor<T>> implements NopEndpointProcessorFactory<P, ProcessorInfo<P>> {
	
	@SuppressWarnings("unchecked")
	@Override
	public ProcessorInfo<P> createProcessor(ProcessorConfig<P> config, ProgressMonitor progressMonitor) {
		Collection<T> semanticElements = createSemanticElements(config, progressMonitor);
		P processor = createProcessor(config, semanticElements, progressMonitor);
		Collection<Throwable> failures = new ArrayList<>();
		BiFunction<Void, Throwable, ProcessorInfo<P>> failureHandler  = (result, failure) -> {
			if (failure != null) {
				failures.add(failure);
			}
			return null;
		};
		ProcessorInfo<P> processorInfo = ProcessorInfo.of(config, processor, () -> {
			return failures;
		});
		if (processor == null) {
			if (config instanceof ConnectionProcessorConfig) {
				// Pass-through wiring source endpoint to target handler and target endpoint to source handler
				ConnectionProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>> connectionConfig = (ConnectionProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>>) config;
				CompletionStage<ProcessorInfo<P>> sourceEndpoint = connectionConfig.getSourceEndpoint();
				if (sourceEndpoint != null) {
					sourceEndpoint.thenAccept(connectionConfig::setTargetHandler).handle(failureHandler);
				}
				
				CompletionStage<ProcessorInfo<P>> targetEndpoint = connectionConfig.getTargetEndpoint();
				if (targetEndpoint != null) {
					targetEndpoint.thenAccept(connectionConfig::setSourceHandler).handle(failureHandler);
				}
			}
		} else {
			// Wiring			

			// Parent
			config.getParentProcessorInfo().thenAccept(parentInfo -> setParent(config, processor, parentInfo, progressMonitor, failures::add)).handle(failureHandler);
			
			// Children
			setChildren(config, processor, config.getChildProcessorsInfo(), progressMonitor);
			
			// Registry
			config.getRegistry().thenAccept(registry -> setRegistry(config, processor, registry, progressMonitor)).handle(failureHandler);
			
			if (config instanceof NodeProcessorConfig) {
				NodeProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>> nodeConfig = (NodeProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>>) config;
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new NodeProcessorConfigAdapter<>(nodeConfig));
				}
				
				// Incoming 
				for (Entry<Connection, CompletionStage<ProcessorInfo<P>>> ie: nodeConfig.getIncomingEndpoints().entrySet()) {
					ie.getValue().thenAccept(incomingInfo -> setIncoming(nodeConfig, processor, ie.getKey(), incomingInfo, progressMonitor)).handle(failureHandler);
				}
				
				// Outgoing
				for (Entry<Connection, CompletionStage<ProcessorInfo<P>>> oe: nodeConfig.getOutgoingEndpoints().entrySet()) {
					oe.getValue().thenAccept(outgoingInfo -> setOutgoing(nodeConfig, processor, oe.getKey(), outgoingInfo, progressMonitor)).handle(failureHandler);
				}
				
				nodeConfig.getIncomingHandlerConsumers().forEach((k,v) -> v.accept(processorInfo));
				nodeConfig.getOutgoingHandlerConsumers().forEach((k,v) -> v.accept(processorInfo));				
			} else if (config instanceof ConnectionProcessorConfig) {
				ConnectionProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>> connectionConfig = (ConnectionProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>>) config;
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new ConnectionProcessorConfigAdapter<>(connectionConfig));
				}
				
				// Source
				connectionConfig.getSourceEndpoint().thenAccept(sourceInfo -> setSource(connectionConfig, processor, sourceInfo, progressMonitor)).handle(failureHandler);
				
				// Target
				connectionConfig.getTargetEndpoint().thenAccept(targetInfo -> setTarget(connectionConfig, processor, targetInfo, progressMonitor)).handle(failureHandler);

				connectionConfig.setSourceHandler(processorInfo);
				connectionConfig.setTargetHandler(processorInfo);				
			} else {
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new ProcessorConfigAdapter<P, ProcessorConfig<P>>(config));
				}
			}
			
			for (T semanticElement: processor.getSemanticElements()) {
				LinkedResourcesAdapter linkedResourcesAdapter = (LinkedResourcesAdapter) EcoreUtil.getRegisteredAdapter(semanticElement, LinkedResourcesAdapter.class);
				if (linkedResourcesAdapter != null) {
					for (Resource linkedResource: linkedResourcesAdapter.getLinkedResources()) {
						linkResource(config, processor, linkedResource, progressMonitor);
					}
				}
			}
		}		
		
		return processorInfo;
	}
		
	/**
	 * Creates a semantic element for a diagram element. May return null if there is no semantic mapping for a given diagram element.
	 * @param config
	 * @return
	 */	
	protected abstract Collection<T> createSemanticElements(ProcessorConfig<P> config, ProgressMonitor progressMonitor);	

	/**
	 * Creates a processor from config and a collection of semantic elements. May return null.
	 * @param config
	 * @param semanticElements
	 * @param progressMonitor
	 * @return
	 */
	protected abstract P createProcessor(ProcessorConfig<P> config, Collection<T> semanticElements, ProgressMonitor progressMonitor);
	
	/**
	 * Links resource. This implementation calls setChildren() for resource roots.
	 * @param config
	 * @param semanticElement
	 * @param linkedResource
	 * @param progressMonitor
	 */
	@SuppressWarnings("unchecked")
	protected void linkResource(ProcessorConfig<P> config, P processor, Resource linkedResource, ProgressMonitor progressMonitor) {
		for (EObject linkedRoot: new ArrayList<>(linkedResource.getContents())) {
			ProcessorConfig<P> linkedRootConfig = (ProcessorConfig<P>) EcoreUtil.getRegisteredAdapter(linkedRoot, ProcessorConfig.class);
			P linkedRootProcessor = createProcessor(config, Collections.singleton((T) linkedRoot), progressMonitor);
			setChildren(config, processor, Collections.singletonMap(linkedRootConfig == null ? null : linkedRootConfig.getElement(), ProcessorInfo.of(linkedRootConfig, linkedRootProcessor, null)), progressMonitor);
		}
	}
	
	/**
	 * Returns semantic element's {@link EReference} to add a child semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getChildReference(ProcessorConfig<P> config, T semanticElement, Element child, ProcessorInfo<P> childProcessorInfo, T semanticChild);
	
	/**
	 * Sets element children.
	 * @param config
	 * @param semanticElement
	 * @param children
	 */
	@SuppressWarnings("unchecked")
	protected void setChildren(ProcessorConfig<P> config, P processor, Map<Element, ProcessorInfo<P>> children, ProgressMonitor progressMonitor) {
		for (T semanticElement: processor.getSemanticElements()) {
			for (Entry<Element, ProcessorInfo<P>> ce: children.entrySet()) {
				P childProcessor = ce.getValue().getProcessor();
				if (childProcessor != null) {
					for (T semanticChild: childProcessor.getSemanticElements()) {
						EReference childReference = getChildReference(config, semanticElement, ce.getKey(), ce.getValue(), semanticChild);
						if (childReference != null) {
							if (childReference.isMany()) {
								((Collection<EObject>) semanticElement.eGet(childReference)).add(semanticChild);
							} else {
								semanticElement.eSet(childReference, semanticChild);
							}
						}
					}
				}																	
			}
		}
	}
	
	/**
	 * Returns semantic element's {@link EReference} to add a registry semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getRegistryReference(ProcessorConfig<P> config, P processor, T semanticElement, Element registryElement, ProcessorInfo<P> registryElementProcessorInfo, T registrySemanticElement);
	
	/**
	 * Sets registry. This method does nothing.
	 * @param config
	 * @param semanticElement
	 * @param registry
	 */
	@SuppressWarnings("unchecked")
	protected void setRegistry(ProcessorConfig<P> config, P processor, Map<Element, ProcessorInfo<P>> registry, ProgressMonitor progressMonitor) {
		if (processor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (Entry<Element, ProcessorInfo<P>> re: registry.entrySet()) {
					P registryEntryProcessor = re.getValue().getProcessor();
					if (registryEntryProcessor != null) {
						for (T registrySemanticElement: registryEntryProcessor.getSemanticElements()) {
							if (registrySemanticElement != null) {
								EReference registryReference = getRegistryReference(config, processor, semanticElement, re.getKey(), re.getValue(), registrySemanticElement);
								if (registryReference != null) {
									if (registryReference.isMany()) {
										((Collection<EObject>) semanticElement.eGet(registryReference)).add(registrySemanticElement);
									} else {
										semanticElement.eSet(registryReference, registrySemanticElement);
									}
								}
							}
						}
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
	 * Returns parent's {@link EReference} to add the semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getChildReference(ProcessorConfig<P> config, P processor, T semanticElement, ProcessorInfo<P> parentProcessorInfo, T parentSemanticElement);
	
	/**
	 * Returns semantic element's {@link EReference} to add the parent to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getParentReference(ProcessorConfig<P> config, P processor, T semanticElement, ProcessorInfo<P> parentProcessorInfo, T parentSemanticElement);
	
	/**
	 * Sets parent. If parent is null this method sets itself to be called for the grand parent etc. 
	 * If child reference property is not null then the semantic element is injected into the parent's references specified in 
	 * the child reference property. Otherwise, if parent reference property is not null then the parent is injected into the semantic element's 
	 * reference specified in the parent reference property.
	 * @param semanticElement
	 * @param parentProcessorInfo
	 */
	@SuppressWarnings("unchecked")
	protected void setParent(ProcessorConfig<P> config, P processor, ProcessorInfo<P> parentProcessorInfo, ProgressMonitor progressMonitor, Consumer<Throwable> failureConsumer) {
		P parentProcessor = parentProcessorInfo.getProcessor();
		if (parentProcessor == null) {
			// No processor, need to go up to the parent.
			ProcessorConfig<P> parentConfig = parentProcessorInfo.getConfig();
			if (parentConfig != null) {
				parentConfig.getParentProcessorInfo().thenAccept(grandParentProcessorInfo -> setParent(config, processor, grandParentProcessorInfo, progressMonitor, failureConsumer)).handle((result, failure) -> {
					if (failure != null) {
						failureConsumer.accept(failure);
					}
					return null; 
				} );
			}
		} else {
			for (T semanticElement: processor.getSemanticElements()) {
				for (T parentSemanticElement: parentProcessor.getSemanticElements()) {
					EReference childReference = getChildReference(config, processor, semanticElement, parentProcessorInfo, parentSemanticElement);
					if (childReference == null) {
						EReference parentReference = getParentReference(config, processor, semanticElement, parentProcessorInfo, parentSemanticElement);
						if (parentReference != null) {
							if (parentReference.isMany()) {
								((Collection<EObject>) semanticElement.eGet(parentReference)).add(parentSemanticElement);
							} else {
								semanticElement.eSet(parentReference, parentSemanticElement);
							}					
						}
					} else {
						if (childReference.isMany()) {
							((Collection<EObject>) parentSemanticElement.eGet(childReference)).add(semanticElement);
						} else {
							parentSemanticElement.eSet(childReference, semanticElement);
						}
					}
					
				}
			}
		}												
	}
	
	/**
	 * This method calls protected setParent() method to establish parent/child relationships between semantic elements
	 * using data from the graph elements from which these semantic elements were created.
	 * This method uses {@link ProcessorConfigAdapter}s to retrieve graph configs to pass to setParent()  
	 * @param child
	 * @param parent
	 */
	@SuppressWarnings("unchecked")
	public void setParent(T child, T parent, ProgressMonitor progressMonitor) {
		if (child != null && parent != null) {
			ProcessorConfig<P> childConfig = (ProcessorConfig<P>) EcoreUtil.getRegisteredAdapter(child, ProcessorConfig.class);
			ProcessorConfig<P> parentConfig = (ProcessorConfig<P>) EcoreUtil.getRegisteredAdapter(parent, ProcessorConfig.class);
			P childProcessor = createProcessor(childConfig, Collections.singleton(child), progressMonitor);
			P parentProcessor = createProcessor(parentConfig, Collections.singleton(parent), progressMonitor);
			setParent(childConfig, childProcessor, ProcessorInfo.of(parentConfig, parentProcessor, null), progressMonitor, f -> {
				f.printStackTrace();
			}); // Should be no failures here
		}
	}	
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the source to.
	 * @return
	 */
	protected abstract EReference getSourceReference(ConnectionProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>> config, P processor, T semanticElement, ProcessorInfo<P> sourceProcessorInfo, T sourceSemanticElement);	
		
	/**
	 * Sets connection semantic element source if source reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected void setSource(ConnectionProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>> config, P processor, ProcessorInfo<P> sourceProcessorInfo, ProgressMonitor progressMonitor) {
		P sourceProcessor = sourceProcessorInfo.getProcessor();		
		if (sourceProcessor != null && processor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (T sourceSemanticElement: sourceProcessor.getSemanticElements()) {
					EReference sourceReference = getSourceReference(config, processor, semanticElement, sourceProcessorInfo, sourceSemanticElement);
					if (sourceReference != null) {
						if (sourceReference.isMany()) {
							((Collection<EObject>) semanticElement.eGet(sourceReference)).add(sourceSemanticElement);
						} else {
							semanticElement.eSet(sourceReference, sourceSemanticElement);
						}
					}
				}
			}
		}												
	}
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the target to.
	 * @return
	 */
	protected abstract EReference getTargetReference(ConnectionProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>> config, P processor, T semanticElement, ProcessorInfo<P> targetProcessorInfo, T targetSemanticElement);
	
	/**
	 * Sets connection semantic element target if target reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected void setTarget(ConnectionProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>> config, P processor, ProcessorInfo<P> targetProcessorInfo, ProgressMonitor progressMonitor) {
		P targetProcessor = targetProcessorInfo.getProcessor();
		if (targetProcessor != null && processor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (T targetSemanticElement: targetProcessor.getSemanticElements()) {
					EReference targetReference = getTargetReference(config, processor, semanticElement, targetProcessorInfo, targetSemanticElement);
					if (targetReference != null) {
						if (targetReference.isMany()) {
							((Collection<EObject>) semanticElement.eGet(targetReference)).add(targetSemanticElement);
						} else {
							semanticElement.eSet(targetReference, targetSemanticElement);
						}
					}
					
				}
			}
		}												
	}
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the incoming semantic element to.
	 */
	protected abstract EReference getIncomingReference(
			NodeProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>> config, 
			P processor, 
			T semanticElement,
			Connection connection, 
			ProcessorInfo<P> incomingProcessorInfo,
			T incomingSemanticElement);	
	
	/**
	 * Sets node semantic element incoming semantic element (connection's or source's if connection semantic element is null and the connection is pass-through) if incoming reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected void setIncoming(NodeProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>> config, P processor, Connection connection, ProcessorInfo<P> incomingProcessorInfo, ProgressMonitor progressMonitor) {
		P incomingProcessor = incomingProcessorInfo.getProcessor();
		if (incomingProcessor != null && processor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (T incomingSemanticElement: incomingProcessor.getSemanticElements()) {
					EReference incomingReference = getIncomingReference(config, processor, semanticElement, connection, incomingProcessorInfo, incomingSemanticElement);
					if (incomingReference != null) {
						if (incomingReference.isMany()) {
							((Collection<EObject>) semanticElement.eGet(incomingReference)).add(incomingSemanticElement);
						} else {
							semanticElement.eSet(incomingReference, incomingSemanticElement);
						}
					}
				}
			}
		}												
	}
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the outgoing semantic element to.
	 */
	protected abstract EReference getOutgoingReference(
			NodeProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>> config, 
			P processor, 
			T semanticElement,
			Connection connection, 
			ProcessorInfo<P> outgoingProcessorInfo,
			T outgoingSemanticElement);	
		
	/**
	 * Sets node semantic element outgoing semantic element (connection's or target's if connection semantic element is null and the connection is pass-through) if outgoing reference property is not null.
	 */
	@SuppressWarnings("unchecked")
	protected void setOutgoing(NodeProcessorConfig<P, ProcessorInfo<P>, ProcessorInfo<P>> config, P processor, Connection connection, ProcessorInfo<P> outgoingProcessorInfo, ProgressMonitor progressMonitor) {
		P outgoingProcessor = outgoingProcessorInfo.getProcessor();
		if (outgoingProcessor != null && processor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (T outgoingSemanticElement: outgoingProcessor.getSemanticElements()) {
					EReference outgoingReference = getOutgoingReference(config, processor, semanticElement, connection, outgoingProcessorInfo, outgoingSemanticElement);
					if (outgoingReference != null) {
						if (outgoingReference.isMany()) {
							((Collection<EObject>) semanticElement.eGet(outgoingReference)).add(outgoingSemanticElement);
						} else {
							semanticElement.eSet(outgoingReference, outgoingSemanticElement);
						}
					}
				}
			}
		}												
	}

}
