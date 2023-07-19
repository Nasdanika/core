package org.nasdanika.graph.processor.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.emf.AbstractEObjectFactory.ProcessorEntryRecord;

/**
 * Creates {@link EObject}s from graph elements and wires them together using properties.
 * Uses {@link ProcessorInfo} as handler and enpoint type.
 * @author Pavel
 *
 */
public abstract class AbstractEObjectFactory<T extends EObject, P extends SemanticProcessor<T>> extends ProcessorFactory<P> {
	
	protected record ProcessorEntryRecord<P>(Element key, P processor) {};

	@SuppressWarnings("unchecked")
	@Override
	protected P createProcessor(
			ProcessorConfig config, 
			boolean parallel,
			Function<Element, CompletionStage<P>> processorProvider, 
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {
		
		Collection<T> semanticElements = createSemanticElements(config, progressMonitor);
		P processor = createProcessor(config, semanticElements, progressMonitor);
		
		if (processor == null) {
			if (config instanceof ConnectionProcessorConfig) {
				// Pass-through wiring source endpoint to target handler and target endpoint to source handler
				ConnectionProcessorConfig<ProcessorConfig,ProcessorConfig> connectionConfig = (ConnectionProcessorConfig<ProcessorConfig,ProcessorConfig>) config;
				CompletionStage<ProcessorConfig> sourceEndpoint = connectionConfig.getSourceEndpoint();
				if (sourceEndpoint != null) {
					stageConsumer.accept(sourceEndpoint.thenAccept(connectionConfig::setTargetHandler));
				}
				
				CompletionStage<ProcessorConfig> targetEndpoint = connectionConfig.getTargetEndpoint();
				if (targetEndpoint != null) {
					stageConsumer.accept(targetEndpoint.thenAccept(connectionConfig::setSourceHandler));
				}
			}
		} else {
			// Wiring			

			// Parent
			setParent(config, processor, config.getParentProcessorConfig(), processorProvider, stageConsumer, progressMonitor);
			
			// Children
			setChildren(config, processor, processorProvider, stageConsumer, parallel, progressMonitor);
			
			// Registry
			setRegistry(config, processor, processorProvider, stageConsumer, progressMonitor);
			
			if (config instanceof NodeProcessorConfig) {
				NodeProcessorConfig<ProcessorConfig,ProcessorConfig> nodeConfig = (NodeProcessorConfig<ProcessorConfig,ProcessorConfig>) config;
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new NodeProcessorConfigAdapter<>(nodeConfig));
				}
				
				// Incoming 
				for (Entry<Connection, CompletionStage<ProcessorConfig>> ie: nodeConfig.getIncomingEndpoints().entrySet()) {
					stageConsumer.accept(ie.getValue().thenAccept(incomingInfo -> stageConsumer.accept(setIncoming(nodeConfig, processor, ie.getKey(), incomingInfo, progressMonitor))));
				}
				
				// Outgoing
				for (Entry<Connection, CompletionStage<ProcessorConfig>> oe: nodeConfig.getOutgoingEndpoints().entrySet()) {
					stageConsumer.accept(oe.getValue().thenAccept(outgoingInfo -> stageConsumer.accept(setOutgoing(nodeConfig, processor, oe.getKey(), outgoingInfo, progressMonitor))));
				}
				
				nodeConfig.getIncomingHandlerConsumers().forEach((k,v) -> v.accept(processorInfo));
				nodeConfig.getOutgoingHandlerConsumers().forEach((k,v) -> v.accept(processorInfo));				
			} else if (config instanceof ConnectionProcessorConfig) {
				ConnectionProcessorConfig<P, ProcessorConfig, ProcessorConfig, R> connectionConfig = (ConnectionProcessorConfig<P, ProcessorConfig, ProcessorConfig, R>) config;
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new ConnectionProcessorConfigAdapter<>(connectionConfig));
				}
				
				// Source
				stageConsumer.accept(connectionConfig.getSourceEndpoint().thenAccept(sourceInfo -> stageConsumer.accept(setSource(connectionConfig, processor, sourceInfo, progressMonitor))));
				
				// Target
				stageConsumer.accept(connectionConfig.getTargetEndpoint().thenAccept(targetInfo -> stageConsumer.accept(setTarget(connectionConfig, processor, targetInfo, progressMonitor))));

				connectionConfig.setSourceHandler(processorInfo);
				connectionConfig.setTargetHandler(processorInfo);				
			} else {
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new ProcessorConfigAdapter<P, R, ProcessorConfig<P, R>>(config));
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
		
		return processor;
	}
		
	/**
	 * Creates semantic elements for a graph element. May return a collection with zero or more elements
	 * @param config
	 * @return
	 */	
	protected abstract Collection<T> createSemanticElements(ProcessorConfig config, ProgressMonitor progressMonitor);	

	/**
	 * Creates a processor from config and a collection of semantic elements. May return null.
	 * @param config
	 * @param semanticElements
	 * @param progressMonitor
	 * @return
	 */
	protected abstract P createProcessor(ProcessorConfig config, Collection<T> semanticElements, ProgressMonitor progressMonitor);
	
	/**
	 * Links resource. This implementation calls setChildren() for resource roots.
	 * @param config
	 * @param semanticElement
	 * @param linkedResource
	 * @param progressMonitor
	 */
	@SuppressWarnings("unchecked")
	protected void linkResource(ProcessorConfig<P, R> config, P processor, Resource linkedResource, ProgressMonitor progressMonitor) {
		for (EObject linkedRoot: new ArrayList<>(linkedResource.getContents())) {
			ProcessorConfig<P, R> linkedRootConfig = (ProcessorConfig<P, R>) EcoreUtil.getRegisteredAdapter(linkedRoot, ProcessorConfig.class);
			P linkedRootProcessor = createProcessor(config, Collections.singleton((T) linkedRoot), progressMonitor);
			setChildren(config, processor, Collections.singletonMap(linkedRootConfig == null ? null : linkedRootConfig.getElement(), ProcessorInfo.of(linkedRootConfig, linkedRootProcessor)), progressMonitor);
		}
	}
	
	/**
	 * Returns semantic element's {@link EReference} to add a child semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getChildReference(
			ProcessorConfig config, 
			T semanticElement, 
			Element child, 
			ProcessorConfig childConfig,
			T semanticChild);
	
	/**
	 * Sets element children.
	 * @param config
	 * @param semanticElement
	 * @param children
	 */
	@SuppressWarnings("unchecked")
	protected void setChildren(
			ProcessorConfig config, 
			P processor, 
			Function<Element, CompletionStage<P>> processorProvider, 			
			Consumer<CompletionStage<?>> stageConsumer,		
			boolean parallel,
			ProgressMonitor progressMonitor) {
		for (T semanticElement: processor.getSemanticElements()) {
			Stream<Entry<Element, ProcessorConfig>> cs = config.getChildProcessorConfigs().entrySet().stream();
			if (parallel) {
				cs = cs.parallel();
			}
			cs.forEach(ce -> {
				CompletionStage<P> ccs = processorProvider.apply(ce.getKey());
				if (ccs != null) {
					stageConsumer.accept(ccs.thenAccept(childProcessor -> {
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
					}));
				}
			});
		}
	}
	
	/**
	 * Returns semantic element's {@link EReference} to add a registry semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getRegistryReference(
			ProcessorConfig config, 
			P processor, 
			T semanticElement, 
			Element registryElement, 
			ProcessorConfig registryElementProcessorInfo, 
			T registrySemanticElement);
	
	/**
	 * Sets registry. This method does nothing.
	 * @param config
	 * @param semanticElement
	 * @param registry
	 */
	@SuppressWarnings("unchecked")
	protected void setRegistry(
			ProcessorConfig config, 
			P processor,
			Function<Element, CompletionStage<P>> processorProvider, 
			Consumer<CompletionStage<?>> stageConsumer,			
			ProgressMonitor progressMonitor) {
		if (processor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (Entry<Element, ProcessorConfig> re: config.getRegistry().entrySet()) {
					CompletionStage<P> recs = processorProvider.apply(re.getKey());
					if (recs != null) {
						stageConsumer.accept(recs.thenAccept(registryEntryProcessor -> {
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
						}));
					}
				}
			}
		}				
	}	
	
	/**
	 * Returns parent's {@link EReference} to add the semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getChildReference(ProcessorConfig config, P processor, T semanticElement, ProcessorConfig parentProcessorConfig, T parentSemanticElement);
	
	/**
	 * Returns semantic element's {@link EReference} to add the parent to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getParentReference(
			ProcessorConfig config,
			P processor,
			T semanticElement, 
			ProcessorConfig parentConfig,
			T parentSemanticElement);
	
	/**
	 * Sets parent. If parent is null this method sets itself to be called for the grand parent etc. 
	 * If child reference property is not null then the semantic element is injected into the parent's references specified in 
	 * the child reference property. Otherwise, if parent reference property is not null then the parent is injected into the semantic element's 
	 * reference specified in the parent reference property.
	 * @param semanticElement
	 * @param parentProcessorInfo
	 */
	@SuppressWarnings("unchecked")
	protected void setParent(
			ProcessorConfig config, 
			P processor,
			ProcessorConfig parentConfig, 
			Function<Element, CompletionStage<P>> processorProvider, 			
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {
		
		if (parentConfig != null) {
			CompletionStage<P> pcs = processorProvider.apply(parentConfig.getElement());
			if (pcs != null) {
				stageConsumer.accept(pcs.thenAccept(parentProcessor -> {
					if (parentProcessor == null) {
						// No processor, need to go up to the grandparent.
						setParent(parentConfig, processor, parentConfig, processorProvider, stageConsumer, progressMonitor);
					} else {
						for (T semanticElement: processor.getSemanticElements()) {
							for (T parentSemanticElement: parentProcessor.getSemanticElements()) {
								EReference childReference = getChildReference(config, processor, semanticElement, parentConfig, parentSemanticElement);
								if (childReference == null) {
									EReference parentReference = getParentReference(config, processor, semanticElement, parentConfig, parentSemanticElement);
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
					
				}));				
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
	public CompletionStage<P> setParent(T child, T parent, ProgressMonitor progressMonitor) {
		if (child != null && parent != null) {
			ProcessorConfig<P, R> childConfig = (ProcessorConfig<P, R>) EcoreUtil.getRegisteredAdapter(child, ProcessorConfig.class);
			ProcessorConfig<P, R> parentConfig = (ProcessorConfig<P, R>) EcoreUtil.getRegisteredAdapter(parent, ProcessorConfig.class);
			P childProcessor = createProcessor(childConfig, Collections.singleton(child), progressMonitor);
			P parentProcessor = createProcessor(parentConfig, Collections.singleton(parent), progressMonitor);
			Consumer<CompletionStage<?>> stageConsumer = stage -> stage.exceptionally(e -> {
				e.printStackTrace(); // Should not happen
				return null;
			});
			return setParent(childConfig, childProcessor, ProcessorInfo.of(parentConfig, parentProcessor), stageConsumer, progressMonitor); 
		}
		return null;
	}	
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the source to.
	 * @return
	 */
	protected abstract EReference getSourceReference(ConnectionProcessorConfig<P, ProcessorConfig, ProcessorConfig, R> config, P processor, T semanticElement, ProcessorConfig sourceProcessorInfo, T sourceSemanticElement);	
		
	/**
	 * Sets connection semantic element source if source reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected CompletionStage<P> setSource(ConnectionProcessorConfig<P, ProcessorConfig, ProcessorConfig, R> config, P processor, ProcessorConfig sourceProcessorInfo, ProgressMonitor progressMonitor) {
		return ((Helper<P,R>) sourceProcessorInfo).getProcessorCompletionStage().thenApply(sourceProcessor -> {
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
			return sourceProcessor;
		});
	}
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the target to.
	 * @return
	 */
	protected abstract EReference getTargetReference(ConnectionProcessorConfig<P, ProcessorConfig, ProcessorConfig, R> config, P processor, T semanticElement, ProcessorConfig targetProcessorInfo, T targetSemanticElement);
	
	/**
	 * Sets connection semantic element target if target reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected CompletionStage<P> setTarget(ConnectionProcessorConfig<P, ProcessorConfig, ProcessorConfig, R> config, P processor, ProcessorConfig targetProcessorInfo, ProgressMonitor progressMonitor) {
		return ((Helper<P,R>) targetProcessorInfo).getProcessorCompletionStage().thenApply(targetProcessor -> {
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
			return targetProcessor;
		});
	}
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the incoming semantic element to.
	 */
	protected abstract EReference getIncomingReference(
			NodeProcessorConfig<P, ProcessorConfig, ProcessorConfig, R> config, 
			P processor, 
			T semanticElement,
			Connection connection, 
			ProcessorConfig incomingProcessorInfo,
			T incomingSemanticElement);	
	
	/**
	 * Sets node semantic element incoming semantic element (connection's or source's if connection semantic element is null and the connection is pass-through) if incoming reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected CompletionStage<P> setIncoming(NodeProcessorConfig<P, ProcessorConfig, ProcessorConfig, R> config, P processor, Connection connection, ProcessorConfig incomingProcessorInfo, ProgressMonitor progressMonitor) {
		return ((Helper<P,R>) incomingProcessorInfo).getProcessorCompletionStage().thenApply(incomingProcessor -> {
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
			return incomingProcessor;
		});
	}
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the outgoing semantic element to.
	 */
	protected abstract EReference getOutgoingReference(
			NodeProcessorConfig<P, ProcessorConfig, ProcessorConfig, R> config, 
			P processor, 
			T semanticElement,
			Connection connection, 
			ProcessorConfig outgoingProcessorInfo,
			T outgoingSemanticElement);	
		
	/**
	 * Sets node semantic element outgoing semantic element (connection's or target's if connection semantic element is null and the connection is pass-through) if outgoing reference property is not null.
	 */
	@SuppressWarnings("unchecked")
	protected CompletionStage<P> setOutgoing(NodeProcessorConfig<P, ProcessorConfig, ProcessorConfig, R> config, P processor, Connection connection, ProcessorConfig outgoingProcessorInfo, ProgressMonitor progressMonitor) {
		return ((Helper<P,R>) outgoingProcessorInfo).getProcessorCompletionStage().thenApply(outgoingProcessor -> {
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
			return outgoingProcessor;
		});
	}
}
