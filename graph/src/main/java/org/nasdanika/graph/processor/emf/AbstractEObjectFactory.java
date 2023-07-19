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
				ConnectionProcessorConfig<ProcessorRecord<P>,ProcessorRecord<P>> connectionConfig = (ConnectionProcessorConfig<ProcessorRecord<P>,ProcessorRecord<P>>) config;
				CompletionStage<ProcessorRecord<P>> sourceEndpoint = connectionConfig.getSourceEndpoint();
				if (sourceEndpoint != null) {
					stageConsumer.accept(sourceEndpoint.thenAccept(connectionConfig::setTargetHandler));
				}
				
				CompletionStage<ProcessorRecord<P>> targetEndpoint = connectionConfig.getTargetEndpoint();
				if (targetEndpoint != null) {
					stageConsumer.accept(targetEndpoint.thenAccept(connectionConfig::setSourceHandler));
				}
			}
		} else {
			ProcessorRecord<P> processorRecord = new ProcessorRecord<>(config, processor);
			// Wiring			

			// Parent
			setParent(config, processor, config.getParentProcessorConfig(), processorProvider, stageConsumer, progressMonitor);
			
			// Children
			setChildren(config, processor, config.getChildProcessorConfigs(), processorProvider, stageConsumer, parallel, progressMonitor);
			
			// Registry
			setRegistry(config, processor, processorProvider, stageConsumer, progressMonitor);
			
			if (config instanceof NodeProcessorConfig) {
				NodeProcessorConfig<ProcessorRecord<P>,ProcessorRecord<P>> nodeConfig = (NodeProcessorConfig<ProcessorRecord<P>,ProcessorRecord<P>>) config;
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new ProcessorRecordAdapter<>(processorRecord));
				}
				
				// Incoming 
				for (Entry<Connection, CompletionStage<ProcessorRecord<P>>> ie: nodeConfig.getIncomingEndpoints().entrySet()) {
					stageConsumer.accept(ie.getValue().thenAccept(incomingRecord -> setIncoming(nodeConfig, processor, ie.getKey(), incomingRecord, progressMonitor)));
				}
				
				// Outgoing
				for (Entry<Connection, CompletionStage<ProcessorRecord<P>>> oe: nodeConfig.getOutgoingEndpoints().entrySet()) {
					stageConsumer.accept(oe.getValue().thenAccept(outgoingRecord -> setOutgoing(nodeConfig, processor, oe.getKey(), outgoingRecord, progressMonitor)));
				}
				
				nodeConfig.getIncomingHandlerConsumers().forEach((k,v) -> v.accept(processorRecord));
				nodeConfig.getOutgoingHandlerConsumers().forEach((k,v) -> v.accept(processorRecord));				
			} else if (config instanceof ConnectionProcessorConfig) {
				ConnectionProcessorConfig<ProcessorRecord<P>,ProcessorRecord<P>> connectionConfig = (ConnectionProcessorConfig<ProcessorRecord<P>,ProcessorRecord<P>>) config;
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new ProcessorRecordAdapter<>(processorRecord));
				}
				
				// Source
				connectionConfig.getSourceEndpoint().thenAccept(sourceRecord -> setSource(connectionConfig, processor, sourceRecord, progressMonitor));
				
				// Target
				connectionConfig.getTargetEndpoint().thenAccept(targetRecord -> setTarget(connectionConfig, processor, targetRecord, progressMonitor));

				connectionConfig.setSourceHandler(processorRecord);
				connectionConfig.setTargetHandler(processorRecord);				
			} else {
				for (T semanticElement: processor.getSemanticElements()) {
					semanticElement.eAdapters().add(new ProcessorRecordAdapter<>(processorRecord));
				}
			}
			
			for (T semanticElement: processor.getSemanticElements()) {
				LinkedResourcesAdapter linkedResourcesAdapter = (LinkedResourcesAdapter) EcoreUtil.getRegisteredAdapter(semanticElement, LinkedResourcesAdapter.class);
				if (linkedResourcesAdapter != null) {
					for (Resource linkedResource: linkedResourcesAdapter.getLinkedResources()) {
						linkResource(config, processor, linkedResource, stageConsumer, parallel, progressMonitor);
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
	protected void linkResource(
			ProcessorConfig config, 
			P processor, 
			Resource linkedResource,
			Consumer<CompletionStage<?>> stageConsumer,		
			boolean parallel,			
			ProgressMonitor progressMonitor) {
		for (EObject linkedRoot: new ArrayList<>(linkedResource.getContents())) {
			ProcessorConfig linkedRootConfig = (ProcessorConfig) EcoreUtil.getRegisteredAdapter(linkedRoot, ProcessorConfig.class);
			P linkedRootProcessor = createProcessor(config, Collections.singleton((T) linkedRoot), progressMonitor);
			Map<Element, ProcessorConfig> children = Collections.singletonMap(linkedRootConfig == null ? null : linkedRootConfig.getElement(), linkedRootConfig);
			Function<Element, CompletionStage<P>> processorProvider = e -> Objects.equals(e, linkedRootConfig.getElement()) ? CompletableFuture.completedStage(linkedRootProcessor) : null; 			
			setChildren(config, processor, children, processorProvider, stageConsumer, parallel, progressMonitor);
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
			P childProcessor,
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
			Map<Element, ProcessorConfig> children,
			Function<Element, CompletionStage<P>> processorProvider, 			
			Consumer<CompletionStage<?>> stageConsumer,		
			boolean parallel,
			ProgressMonitor progressMonitor) {
		for (T semanticElement: processor.getSemanticElements()) {			
			Stream<Entry<Element, ProcessorConfig>> cs = children.entrySet().stream();
			if (parallel) {
				cs = cs.parallel();
			}
			cs.forEach(ce -> {
				CompletionStage<P> ccs = processorProvider.apply(ce.getKey());
				if (ccs != null) {
					stageConsumer.accept(ccs.thenAccept(childProcessor -> {
						if (childProcessor != null) {
							for (T semanticChild: childProcessor.getSemanticElements()) {
								EReference childReference = getChildReference(config, semanticElement, ce.getKey(), ce.getValue(), childProcessor, semanticChild);
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
						setParent(config, processor, parentConfig, parentProcessor, progressMonitor);
					}												
					
				}));				
			}
		}
	}

	@SuppressWarnings("unchecked")	
	protected void setParent(
			ProcessorConfig config, 
			P processor,
			ProcessorConfig parentConfig, 
			P parentProcessor, 			
			ProgressMonitor progressMonitor) {
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
			ProcessorRecord<P> childRecord = ((ProcessorRecordAdapter<P>) EcoreUtil.getRegisteredAdapter(child, ProcessorRecordAdapter.class)).getProcessorRecord();
			ProcessorRecord<P> parentRecord = ((ProcessorRecordAdapter<P>) EcoreUtil.getRegisteredAdapter(parent, ProcessorRecordAdapter.class)).getProcessorRecord();
			P childProcessor = createProcessor(childRecord.config(), Collections.singleton(child), progressMonitor);
			Consumer<CompletionStage<?>> stageConsumer = stage -> stage.exceptionally(e -> {
				e.printStackTrace(); // Should not happen
				return null;
			});
			
			Function<Element, CompletionStage<P>> parentProcessorProvider = e -> Objects.equals(e, parentRecord.config().getElement()) ? CompletableFuture.completedStage(parentRecord.processor()) : null;   			
			
			setParent(
					childRecord.config(),
					childProcessor,
					parentRecord.config(),
					parentProcessorProvider,
					stageConsumer, 
					progressMonitor); 
		}
	}	
	
	/**
	 * Returns connections's semantic elment {@link EReference} to add the source to.
	 * @return
	 */
	protected abstract EReference getSourceReference(ConnectionProcessorConfig<ProcessorRecord<P>, ProcessorRecord<P>> config, P processor, T semanticElement, ProcessorRecord<P> sourceProcessorRecord, T sourceSemanticElement);	
		
	/**
	 * Sets connection semantic element source if source reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected void setSource(ConnectionProcessorConfig<ProcessorRecord<P>, ProcessorRecord<P>> config, P processor, ProcessorRecord<P> sourceProcessorRecord, ProgressMonitor progressMonitor) {
		P sourceProcessor = sourceProcessorRecord.processor();		
		if (sourceProcessor != null && processor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (T sourceSemanticElement: sourceProcessor.getSemanticElements()) {
					EReference sourceReference = getSourceReference(config, processor, semanticElement, sourceProcessorRecord, sourceSemanticElement);
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
	protected abstract EReference getTargetReference(ConnectionProcessorConfig<ProcessorRecord<P>, ProcessorRecord<P>> config, P processor, T semanticElement, ProcessorRecord<P> targetProcessorRecord, T targetSemanticElement);
	
	/**
	 * Sets connection semantic element target if target reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected void setTarget(ConnectionProcessorConfig<ProcessorRecord<P>, ProcessorRecord<P>> config, P processor, ProcessorRecord<P> targetProcessorRecord, ProgressMonitor progressMonitor) {
		P targetProcessor = targetProcessorRecord.processor();
		if (targetProcessor != null && processor != null) {
			for (T semanticElement: processor.getSemanticElements()) {
				for (T targetSemanticElement: targetProcessor.getSemanticElements()) {
					EReference targetReference = getTargetReference(config, processor, semanticElement, targetProcessorRecord, targetSemanticElement);
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
			NodeProcessorConfig<ProcessorRecord<P>,ProcessorRecord<P>> config, 
			P processor, 
			T semanticElement,
			Connection connection, 
			ProcessorRecord<P> incomingProcessorRecord,
			T incomingSemanticElement);	
	
	/**
	 * Sets node semantic element incoming semantic element (connection's or source's if connection semantic element is null and the connection is pass-through) if incoming reference property is not null.  
	 */
	@SuppressWarnings("unchecked")
	protected void setIncoming(
			NodeProcessorConfig<ProcessorRecord<P>,ProcessorRecord<P>> config, 
			P processor, 
			Connection connection, 
			ProcessorRecord<P> incomingProcessorRecord,
			ProgressMonitor progressMonitor) {
		
		if (processor != null) {
			P incomingProcessor = incomingProcessorRecord.processor();
			if (incomingProcessor != null) {
				for (T semanticElement: processor.getSemanticElements()) {
					for (T incomingSemanticElement: incomingProcessor.getSemanticElements()) {
						EReference incomingReference = getIncomingReference(config, processor, semanticElement, connection, incomingProcessorRecord, incomingSemanticElement);
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
			NodeProcessorConfig<ProcessorRecord<P>,ProcessorRecord<P>> config, 
			P processor, 
			T semanticElement,
			Connection connection, 
			ProcessorRecord<P> outgoingProcessorRecord,
			T outgoingSemanticElement);	
		
	/**
	 * Sets node semantic element outgoing semantic element (connection's or target's if connection semantic element is null and the connection is pass-through) if outgoing reference property is not null.
	 */
	@SuppressWarnings("unchecked")
	protected void setOutgoing(
			NodeProcessorConfig<ProcessorRecord<P>,ProcessorRecord<P>> config, 
			P processor, 
			Connection connection, 
			ProcessorRecord<P> outgoingProcessorRecord,
			ProgressMonitor progressMonitor) {

		if (processor != null) {
			P outgoingProcessor = outgoingProcessorRecord.processor();
			if (outgoingProcessor != null) {
				for (T semanticElement: processor.getSemanticElements()) {
					for (T outgoingSemanticElement: outgoingProcessor.getSemanticElements()) {
						EReference outgoingReference = getOutgoingReference(config, processor, semanticElement, connection, outgoingProcessorRecord, outgoingSemanticElement);
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
