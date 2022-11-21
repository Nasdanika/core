package org.nasdanika.graph.processor;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

/**
 * 
 * @author Pavel
 *
 * @param <P> Processor type.
 * @param <H> Handler type.
 * @param <E> Endpoint type.
 */
class ProcessorFactoryVisitor<P,H,E> {
	
	private ProcessorFactory<P,H,E> factory;
	private Map<Element, ProcessorInfo<P>> registry = new LinkedHashMap<>();
	
	// Endpoints for connection source to call the connection
	private Map<Connection,CompletableFuture<E>> sourceEndpoints = new LinkedHashMap<>();
	
	// Endpoints for connection target to call the connection
	private Map<Connection, CompletableFuture<E>> targetEndpoints = new LinkedHashMap<>();
	
	// Endpoints for incoming connections to call the target node
	private Map<Node, Map<Connection, CompletableFuture<E>>> incomingEndpoints = new LinkedHashMap<>();
	
	// Endpoints for outgoing connections to call the source node
	private Map<Node, Map<Connection, CompletableFuture<E>>> outgoingEndpoints = new LinkedHashMap<>();	

	ProcessorFactoryVisitor(ProcessorFactory<P,H,E> factory) {
		this.factory = factory;
	}
		
	Map<Element, ProcessorInfo<P>> getRegistry() {
		return registry;
	}	

	Helper<P> createElementProcessor(Element element, Map<? extends Element, Helper<P>> childProcessors) {
		CompletableFuture<ProcessorInfo<P>> parentProcessorInfoCompletableFuture = new CompletableFuture<>();
		CompletableFuture<Map<Element, ProcessorInfo<P>>> registryCompletableFuture = new CompletableFuture<>();
		ProcessorConfig<P> config;
		if (element instanceof Node) {
			Node node = (Node) element;
			Map<Connection, Consumer<H>> incomingHandlerConsumers = new LinkedHashMap<>();
			for (Connection incomingConnection: node.getIncomingConnections()) {
				// Incoming endpoint - creating if not there
				incomingEndpoints
					.computeIfAbsent(node, n -> new LinkedHashMap<>())
					.computeIfAbsent(incomingConnection, ic -> new CompletableFuture<E>());
				
				// Incoming handler consumer - wiring to the connection target endpoint or to the node outgoing endpoint
				CompletableFuture<E> endpoint;
				if (factory.isPassThrough(incomingConnection)) {
					// Wiring to the source node outgoing endpoint
					endpoint = outgoingEndpoints
							.computeIfAbsent(node, n -> new LinkedHashMap<>())
							.computeIfAbsent(incomingConnection, ic -> new CompletableFuture<E>());
				} else {
					// Wiring to the connection target endpoint
					endpoint = targetEndpoints.computeIfAbsent(incomingConnection, ic -> new CompletableFuture<E>()); 
				}
				
//				endpoint.thenAccept(v -> {
//					System.out.println(v);
//				});
				
				incomingHandlerConsumers.put(incomingConnection, handler -> {
					endpoint.complete(factory.createEndpoint(incomingConnection, handler, HandlerType.INCOMING));
				});
			}
			
			Map<Connection, Consumer<H>> outgoingHandlerConsumers = new LinkedHashMap<>();
			for (Connection outgoingConnection: node.getOutgoingConnections()) {
				outgoingEndpoints
					.computeIfAbsent(node, n -> new LinkedHashMap<>())
					.computeIfAbsent(outgoingConnection, ic -> new CompletableFuture<E>());
				
				// Outgoing handler consumer - wiring to the connection source endpoint or to the node incoming endpoint
				CompletableFuture<E> endpoint;
				if (factory.isPassThrough(outgoingConnection)) {
					// Wiring to the source node outgoing endpoint
					endpoint = incomingEndpoints
							.computeIfAbsent(node, n -> new LinkedHashMap<>())
							.computeIfAbsent(outgoingConnection, ic -> new CompletableFuture<E>());
				} else {
					// Wiring to the connection target endpoint
					endpoint = sourceEndpoints.computeIfAbsent(outgoingConnection, ic -> new CompletableFuture<E>()); 
				}
				
				outgoingHandlerConsumers.put(outgoingConnection, handler -> {
					endpoint.complete(factory.createEndpoint(outgoingConnection, handler, HandlerType.OUTGOING));
				});
			}
			
			config = new NodeProcessorConfig<P, H, E>() {
				
				@Override
				public Node getElement() {
					return node;
				}

				@Override
				public Map<Element, ProcessorInfo<P>> getChildProcessorsInfo() {
					Map<Element,ProcessorInfo<P>> ret = new LinkedHashMap<>();
					childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessorInfo()));
					return ret;
				}
				
				@Override
				public CompletableFuture<ProcessorInfo<P>> getParentProcessorInfo() {
					return parentProcessorInfoCompletableFuture;
				}

				@Override
				public CompletableFuture<Map<Element, ProcessorInfo<P>>> getRegistry() {
					return registryCompletableFuture;
				}

				@Override
				public Map<Connection, CompletionStage<E>> getIncomingEndpoints() {
					return Collections.unmodifiableMap(incomingEndpoints.computeIfAbsent(node, e -> new LinkedHashMap<>()));
				}

				@Override
				public Map<Connection, Consumer<H>> getIncomingHandlerConsumers() {
					return Collections.unmodifiableMap(incomingHandlerConsumers);
				}

				@Override
				public Map<Connection, CompletionStage<E>> getOutgoingEndpoints() {
					return Collections.unmodifiableMap(outgoingEndpoints.computeIfAbsent(node, e -> new LinkedHashMap<>()));
				}

				@Override
				public Map<Connection, Consumer<H>> getOutgoingHandlerConsumers() {
					return Collections.unmodifiableMap(outgoingHandlerConsumers);
				}
			};

		} else if (element instanceof Connection) {
			if (factory.isPassThrough((Connection) element)) {
				config = null;
			} else {
				Connection connection = (Connection) element;
				Node source = connection.getSource();
				Node target = connection.getTarget();
				
				CompletableFuture<E> sourceEndpoint = source == null ? null : sourceEndpoints.computeIfAbsent(connection, ic -> new CompletableFuture<E>());
				CompletableFuture<E> targetEndpoint = target == null ? null : targetEndpoints.computeIfAbsent(connection, ic -> new CompletableFuture<E>());
											
				CompletableFuture<E> outgoingEndpoint = source == null ? null : outgoingEndpoints.computeIfAbsent(source, n -> new LinkedHashMap<>()).computeIfAbsent(connection, ic -> new CompletableFuture<E>());
				CompletableFuture<E> incomingEndpoint = target == null ? null : incomingEndpoints.computeIfAbsent(target, n -> new LinkedHashMap<>()).computeIfAbsent(connection, ic -> new CompletableFuture<E>());
				
				config = new ConnectionProcessorConfig<P, H, E>() {
					
					@Override
					public Connection getElement() {
						return connection;
					}

					@Override
					public Map<Element, ProcessorInfo<P>> getChildProcessorsInfo() {
						Map<Element,ProcessorInfo<P>> ret = new LinkedHashMap<>();
						childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessorInfo()));
						return ret;
					}

					@Override
					public CompletableFuture<ProcessorInfo<P>> getParentProcessorInfo() {
						return parentProcessorInfoCompletableFuture;
					}

					@Override
					public CompletableFuture<Map<Element, ProcessorInfo<P>>> getRegistry() {
						return registryCompletableFuture;
					}

					@Override
					public CompletionStage<E> getSourceEndpoint() {
						return sourceEndpoint;
					}

					@Override
					public void setSourceHandler(H sourceHandler) {
						if (outgoingEndpoint != null) {
							outgoingEndpoint.complete(factory.createEndpoint(connection, sourceHandler, HandlerType.SOURCE));
						}
					}

					@Override
					public CompletionStage<E> getTargetEndpoint() {
						return targetEndpoint;
					}

					@Override
					public void setTargetHandler(H targetHandler) {
						if (incomingEndpoint != null) {
							incomingEndpoint.complete(factory.createEndpoint(connection, targetHandler, HandlerType.TARGET));
						}
					}
				};
			}
		} else {
			config = new ProcessorConfig<P>() {
				
				@Override
				public Element getElement() {
					return element;
				}

				@Override
				public Map<Element, ProcessorInfo<P>> getChildProcessorsInfo() {
					Map<Element,ProcessorInfo<P>> ret = new LinkedHashMap<>();
					childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessorInfo()));
					return ret;
				}

				@Override
				public CompletableFuture<ProcessorInfo<P>> getParentProcessorInfo() {
					return parentProcessorInfoCompletableFuture;
				}

				@Override
				public CompletableFuture<Map<Element, ProcessorInfo<P>>> getRegistry() {
					return registryCompletableFuture;
				}
				
			};
		}
		
		ProcessorInfo<P> processorInfo = config == null ? null : factory.createProcessor(config);
		
		if (childProcessors != null) {
			childProcessors.values().forEach(ch -> ch.setParentProcessorInfo(processorInfo));
		}
		if (processorInfo != null) {
			registry.put(element, processorInfo);
		}
		return new Helper<>(processorInfo) {

			@Override
			void setParentProcessorInfo(ProcessorInfo<P> parentProcessorInfo) {
				parentProcessorInfoCompletableFuture.complete(parentProcessorInfo);
			}

			@Override
			void setRegistry(Map<Element, ProcessorInfo<P>> registry) {
				childProcessors.values().forEach(ch -> ch.setRegistry(registry));
				registryCompletableFuture.complete(registry);
			}
			
		};
	}

}
