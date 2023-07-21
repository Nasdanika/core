package org.nasdanika.graph.processor.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
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
import org.nasdanika.graph.processor.ProcessorInfo;

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
	protected ProcessorInfo<P> createProcessor(
			ProcessorConfig config, 
			boolean parallel,
			Function<Element, CompletionStage<ProcessorInfo<P>>> infoProvider, 
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {
		
		Collection<T> semanticElements = createSemanticElements(config, progressMonitor);
		P processor = createProcessor(config, semanticElements, progressMonitor);
		ProcessorInfo<P> processorInfo = ProcessorInfo.of(config, processor);
		
		if (processor == null) {
			if (config instanceof ConnectionProcessorConfig) {
				// Pass-through wiring source endpoint to target handler and target endpoint to source handler
				ConnectionProcessorConfig<ProcessorInfo<P>,ProcessorInfo<P>> connectionConfig = (ConnectionProcessorConfig<ProcessorInfo<P>,ProcessorInfo<P>>) config;
				CompletionStage<ProcessorInfo<P>> sourceEndpoint = connectionConfig.getSourceEndpoint();
				if (sourceEndpoint != null) {
					stageConsumer.accept(sourceEndpoint.thenAccept(connectionConfig::setTargetHandler));
				}
				
				CompletionStage<ProcessorInfo<P>> targetEndpoint = connectionConfig.getTargetEndpoint();
				if (targetEndpoint != null) {
					stageConsumer.accept(targetEndpoint.thenAccept(connectionConfig::setSourceHandler));
				}
			}
		} else {
			// Wiring			

			// Parent
			setParent(processorInfo, config.getParentProcessorConfig(), infoProvider, stageConsumer, progressMonitor);
			
			// Children
			setChildren(processorInfo, config.getChildProcessorConfigs(), infoProvider, stageConsumer, parallel, progressMonitor);
			
			// Registry
			setRegistry(processorInfo, infoProvider, stageConsumer, progressMonitor);
			
			if (config instanceof NodeProcessorConfig) {
				NodeProcessorConfig<ProcessorInfo<P>,ProcessorInfo<P>> nodeConfig = (NodeProcessorConfig<ProcessorInfo<P>,ProcessorInfo<P>>) config;
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new ProcessorInfoAdapter<>(processorInfo));
				}
				
				// Incoming 
				for (Entry<Connection, CompletionStage<ProcessorInfo<P>>> ie: nodeConfig.getIncomingEndpoints().entrySet()) {
					stageConsumer.accept(ie.getValue().thenAccept(incomingRecord -> setIncoming(nodeConfig, processor, ie.getKey(), incomingRecord, progressMonitor)));
				}
				
				// Outgoing
				for (Entry<Connection, CompletionStage<ProcessorInfo<P>>> oe: nodeConfig.getOutgoingEndpoints().entrySet()) {
					stageConsumer.accept(oe.getValue().thenAccept(outgoingRecord -> setOutgoing(nodeConfig, processor, oe.getKey(), outgoingRecord, progressMonitor)));
				}
				
				nodeConfig.getIncomingHandlerConsumers().forEach((k,v) -> v.accept(processorInfo));
				nodeConfig.getOutgoingHandlerConsumers().forEach((k,v) -> v.accept(processorInfo));				
			} else if (config instanceof ConnectionProcessorConfig) {
				ConnectionProcessorConfig<ProcessorInfo<P>,ProcessorInfo<P>> connectionConfig = (ConnectionProcessorConfig<ProcessorInfo<P>,ProcessorInfo<P>>) config;
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new ProcessorInfoAdapter<>(processorInfo));
				}
				
				// Source
				connectionConfig.getSourceEndpoint().thenAccept(sourceRecord -> setSource(connectionConfig, processor, sourceRecord, progressMonitor));
				
				// Target
				connectionConfig.getTargetEndpoint().thenAccept(targetRecord -> setTarget(connectionConfig, processor, targetRecord, progressMonitor));

				connectionConfig.setSourceHandler(processorInfo);
				connectionConfig.setTargetHandler(processorInfo);				
			} else {
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new ProcessorInfoAdapter<>(processorInfo));
				}
			}
			
			for (T semanticElement: processor.getSemanticElements()) {
				LinkedResourcesAdapter linkedResourcesAdapter = (LinkedResourcesAdapter) EcoreUtil.getRegisteredAdapter(semanticElement, LinkedResourcesAdapter.class);
				if (linkedResourcesAdapter != null) {
					for (Resource linkedResource: linkedResourcesAdapter.getLinkedResources()) {
						linkResource(processorInfo, linkedResource, stageConsumer, parallel, progressMonitor);
					}
				}
			}
		}		
		
		return processorInfo;
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
	protected void linkResource(
			ProcessorInfo<P> info, 
			Resource linkedResource,
			Consumer<CompletionStage<?>> stageConsumer,		
			boolean parallel,			
			ProgressMonitor progressMonitor) {
		for (EObject linkedRoot: new ArrayList<>(linkedResource.getContents())) {
			ProcessorConfig linkedRootConfig = (ProcessorConfig) EcoreUtil.getRegisteredAdapter(linkedRoot, ProcessorConfig.class);
			P linkedRootProcessor = createProcessor(info, Collections.singleton((T) linkedRoot), progressMonitor);
			Map<Element, ProcessorConfig> children = Collections.singletonMap(linkedRootConfig == null ? null : linkedRootConfig.getElement(), linkedRootConfig);
			Function<Element, CompletionStage<ProcessorInfo<P>>> infoProvider = e -> Objects.equals(e, linkedRootConfig.getElement()) ? CompletableFuture.completedStage(ProcessorInfo.of(linkedRootConfig, linkedRootProcessor)) : null; 			
			setChildren(info, children, infoProvider, stageConsumer, parallel, progressMonitor);
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
			ProcessorInfo<P> info, 
			T semanticElement, 
			ProcessorInfo<P> childInfo,
			T semanticChild);	
	
	/**
	 * Returns parent's {@link EReference} to add the semantic element to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getParentChildReference(
			ProcessorInfo<P> info, 
			T semanticElement, 
			ProcessorInfo<P> parentProcessorInfo, 
			T parentSemanticElement);
	
	/**
	 * Sets element children.
	 * @param config
	 * @param semanticElement
	 * @param children
	 */
	@SuppressWarnings("unchecked")
	protected void setChildren(
			ProcessorInfo<P> info, 
			Map<Element, ProcessorConfig> children,
			Function<Element, CompletionStage<ProcessorInfo<P>>> infoProvider, 			
			Consumer<CompletionStage<?>> stageConsumer,		
			boolean parallel,
			ProgressMonitor progressMonitor) {
		for (T semanticElement: info.getProcessor().getSemanticElements()) {			
			Stream<Entry<Element, ProcessorConfig>> cs = children.entrySet().stream();
			if (parallel) {
				cs = cs.parallel();
			}
			cs.forEach(ce -> {
				CompletionStage<ProcessorInfo<P>> cics = infoProvider.apply(ce.getKey());
				if (cics != null) {
					stageConsumer.accept(cics.thenAccept(childInfo -> {
						if (childInfo.getProcessor() != null) {
							for (T semanticChild: childInfo.getProcessor().getSemanticElements()) {
								EReference childReference = getChildReference(info, semanticElement, childInfo, semanticChild);
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
			ProcessorInfo<P> info, 
			T semanticElement, 
			Element registryElement, 
			ProcessorInfo<P> registryElementProcessorInfo, 
			T registrySemanticElement);
	
	/**
	 * Sets registry. This method does nothing.
	 * @param config
	 * @param semanticElement
	 * @param registry
	 */
	@SuppressWarnings("unchecked")
	protected void setRegistry(
			ProcessorInfo<P> info, 
			Function<Element, CompletionStage<ProcessorInfo<P>>> infoProvider, 
			Consumer<CompletionStage<?>> stageConsumer,			
			ProgressMonitor progressMonitor) {
		if (info != null && info.getProcessor() != null) {
			for (T semanticElement: info.getProcessor().getSemanticElements()) {
				for (Entry<Element, ProcessorConfig> re: info.getRegistry().entrySet()) {
					CompletionStage<ProcessorInfo<P>> reics = infoProvider.apply(re.getKey());
					if (reics != null) {
						stageConsumer.accept(reics.thenAccept(registryEntryInfo -> {
							if (registryEntryInfo.getProcessor() != null) {
								for (T registrySemanticElement: registryEntryInfo.getProcessor().getSemanticElements()) {
									if (registrySemanticElement != null) {
										EReference registryReference = getRegistryReference(info, semanticElement, re.getKey(), registryEntryInfo, registrySemanticElement);
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
	 * Returns semantic element's {@link EReference} to add the parent to.
	 * @param config
	 * @param semanticElement
	 * @param parentProcessorInfo
	 * @return
	 */
	protected abstract EReference getParentReference(
			ProcessorInfo<P> config,
			T semanticElement, 
			ProcessorInfo<P> parentInfo,
			T parentSemanticElement);
	
	/**
	 * Sets parent. If parent is null this method sets itself to be called for the grand parent etc. 
	 * If child reference property is not null then the semantic element is injected into the parent's references specified in 
	 * the child reference property. Otherwise, if parent reference property is not null then the parent is injected into the semantic element's 
	 * reference specified in the parent reference property.
	 * @param semanticElement
	 * @param parentProcessorInfo
	 */
	protected void setParent(
			ProcessorInfo<P> info, 
			ProcessorConfig parentConfig, 
			Function<Element, CompletionStage<ProcessorInfo<P>>> infoProvider, 			
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {
		
		if (parentConfig != null) {
			CompletionStage<ProcessorInfo<P>> pics = infoProvider.apply(parentConfig.getElement());
			if (pics != null) {
				stageConsumer.accept(pics.thenAccept(parentInfo -> {
					if (parentInfo.getProcessor() == null) {
						// No processor, need to go up to the grandparent.
						setParent(parentConfig.toInfo(info.getProcessor()), parentConfig, infoProvider, stageConsumer, progressMonitor);
					} else {
						setParent(info, parentInfo, progressMonitor);
					}												
					
				}));				
			}
		}
	}

	@SuppressWarnings("unchecked")	
	protected void setParent(
			ProcessorInfo<P> info, 
			ProcessorInfo<P> parentInfo, 
			ProgressMonitor progressMonitor) {
		for (T semanticElement: info.getProcessor().getSemanticElements()) {
			for (T parentSemanticElement: parentInfo.getProcessor().getSemanticElements()) {
				EReference parentChildReference = getParentChildReference(info, semanticElement, parentInfo, parentSemanticElement);
				if (parentChildReference == null) {
					EReference parentReference = getParentReference(info, semanticElement, parentInfo, parentSemanticElement);
					if (parentReference != null) {
						if (parentReference.isMany()) {
							((Collection<EObject>) semanticElement.eGet(parentReference)).add(parentSemanticElement);
						} else {
							semanticElement.eSet(parentReference, parentSemanticElement);
						}					
					}
				} else {
					if (parentChildReference.isMany()) {
						((Collection<EObject>) parentSemanticElement.eGet(parentChildReference)).add(semanticElement);
					} else {
						parentSemanticElement.eSet(parentChildReference, semanticElement);
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
			ProcessorInfo<P> childInfo = ((ProcessorInfoAdapter<P>) EcoreUtil.getRegisteredAdapter(child, ProcessorInfoAdapter.class)).getProcessorInfo();
			ProcessorInfo<P> parentInfo = ((ProcessorInfoAdapter<P>) EcoreUtil.getRegisteredAdapter(parent, ProcessorInfoAdapter.class)).getProcessorInfo();
			Consumer<CompletionStage<?>> stageConsumer = stage -> stage.exceptionally(e -> {
				e.printStackTrace(); // Should not happen
				return null;
			});
			
			Function<Element, CompletionStage<ProcessorInfo<P>>> parentProcessorInfoProvider = e -> Objects.equals(e, parentInfo.getElement()) ? CompletableFuture.completedStage(parentInfo) : null;   			
			
			setParent(
					childInfo,
					parentInfo,
					parentProcessorInfoProvider,
					stageConsumer, 
					progressMonitor); 
		}
	}	
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the source to.
	 * @return
	 */
	protected abstract EReference getSourceReference(ConnectionProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config, P processor, T semanticElement, ProcessorInfo<P> sourceProcessorRecord, T sourceSemanticElement);	
		
	/**
	 * Sets connection semantic element source if source reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected void setSource(
			ConnectionProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config, 
			P processor, 
			ProcessorInfo<P> sourceProcessorInfo, 
			ProgressMonitor progressMonitor) {
		
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
	protected abstract EReference getTargetReference(ConnectionProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config, P processor, T semanticElement, ProcessorInfo<P> targetProcessorRecord, T targetSemanticElement);
	
	/**
	 * Sets connection semantic element target if target reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected void setTarget(
			ConnectionProcessorConfig<ProcessorInfo<P>, ProcessorInfo<P>> config, 
			P processor, 
			ProcessorInfo<P> targetProcessorInfo, 
			ProgressMonitor progressMonitor) {
		
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
			NodeProcessorConfig<ProcessorInfo<P>,ProcessorInfo<P>> config, 
			P processor, 
			T semanticElement,
			Connection connection, 
			ProcessorInfo<P> incomingProcessorRecord,
			T incomingSemanticElement);	
	
	/**
	 * Sets node semantic element incoming semantic element (connection's or source's if connection semantic element is null and the connection is pass-through) if incoming reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected void setIncoming(
			NodeProcessorConfig<ProcessorInfo<P>,ProcessorInfo<P>> config, 
			P processor, 
			Connection connection, 
			ProcessorInfo<P> incomingProcessorInfo,
			ProgressMonitor progressMonitor) {
		
		if (processor != null) {
			P incomingProcessor = incomingProcessorInfo.getProcessor();
			if (incomingProcessor != null) {
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
	}
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the outgoing semantic element to.
	 */
	protected abstract EReference getOutgoingReference(
			NodeProcessorConfig<ProcessorInfo<P>,ProcessorInfo<P>> config, 
			P processor, 
			T semanticElement,
			Connection connection, 
			ProcessorInfo<P> outgoingProcessorRecord,
			T outgoingSemanticElement);	
		
	/**
	 * Sets node semantic element outgoing semantic element (connection's or target's if connection semantic element is null and the connection is pass-through) if outgoing reference property is not null.
	 */
	@SuppressWarnings("unchecked")
	protected void setOutgoing(
			NodeProcessorConfig<ProcessorInfo<P>,ProcessorInfo<P>> config, 
			P processor, 
			Connection connection, 
			ProcessorInfo<P> outgoingProcessorInfo,
			ProgressMonitor progressMonitor) {

		if (processor != null) {
			P outgoingProcessor = outgoingProcessorInfo.getProcessor();
			if (outgoingProcessor != null) {
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
}
