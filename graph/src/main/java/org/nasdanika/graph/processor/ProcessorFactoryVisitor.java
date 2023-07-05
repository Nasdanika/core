package org.nasdanika.graph.processor;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import org.nasdanika.common.ProgressMonitor;
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
class ProcessorFactoryVisitor<P,H,E,R> {
	
	private ProcessorFactory<P,H,E,R> factory;
	
	private Map<Element, ProcessorInfo<P,R>> registry = Collections.synchronizedMap(new LinkedHashMap<>());
	
	// Endpoints for connection source to call the connection
	private Map<Connection,CompletableFuture<E>> sourceEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());
	
	// Endpoints for connection target to call the connection
	private Map<Connection, CompletableFuture<E>> targetEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());
	
	// Endpoints for incoming connections to call the target node
	private Map<Node, Map<Connection, CompletableFuture<E>>> incomingEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());
	
	// Endpoints for outgoing connections to call the source node
	private Map<Node, Map<Connection, CompletableFuture<E>>> outgoingEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());	

	ProcessorFactoryVisitor(ProcessorFactory<P,H,E,R> factory) {
		this.factory = factory;
	}
		
	Map<Element, ProcessorInfo<P,R>> getRegistry() {
		return registry;
	}	

	Helper<P,R> createElementProcessor(Element element, Map<? extends Element, Helper<P,R>> childProcessors, ProgressMonitor progressMonitor) {
		if (progressMonitor.isCancelled()) {
			throw new CancellationException();
		}		
		try (ProgressMonitor subMonitor =  progressMonitor.split("Creating element processor", 1, element)) {
			CompletableFuture<ProcessorInfo<P,R>> parentProcessorInfoCompletableFuture = new CompletableFuture<>();
			CompletableFuture<R> registryCompletableFuture = new CompletableFuture<>();
			ProcessorConfig<P,R> config;
			if (element instanceof Node) {
				Node node = (Node) element;
				Map<Connection, Consumer<H>> incomingHandlerConsumers = Collections.synchronizedMap(new LinkedHashMap<>());
				for (Connection incomingConnection: node.getIncomingConnections()) {
					// Incoming endpoint - creating if not there
					synchronized (incomingEndpoints) {
						incomingEndpoints
							.computeIfAbsent(node, n -> Collections.synchronizedMap(new LinkedHashMap<>()))
							.computeIfAbsent(incomingConnection, ic -> new CompletableFuture<E>());
					}
					
					// Incoming handler consumer - wiring to the connection target endpoint or to the node outgoing endpoint
					CompletableFuture<E> endpoint;
					if (factory.isPassThrough(incomingConnection)) {
						// Wiring to the source node outgoing endpoint
						Node source = incomingConnection.getSource();
						if (source == null) {
							endpoint = null;
						} else {
							synchronized (outgoingEndpoints) {
								endpoint = outgoingEndpoints
										.computeIfAbsent(source, n -> Collections.synchronizedMap(new LinkedHashMap<>()))
										.computeIfAbsent(incomingConnection, ic -> new CompletableFuture<E>());
							}
						}
					} else {
						// Wiring to the connection target endpoint
						synchronized (targetEndpoints) {
							endpoint = targetEndpoints.computeIfAbsent(incomingConnection, ic -> new CompletableFuture<E>());
						}
					}
					
					incomingHandlerConsumers.put(incomingConnection, handler -> {
						if (endpoint != null) {
							endpoint.complete(factory.createEndpoint(incomingConnection, handler, HandlerType.INCOMING));
						}
					});
				}
				
				Map<Connection, Consumer<H>> outgoingHandlerConsumers = new LinkedHashMap<>();
				for (Connection outgoingConnection: node.getOutgoingConnections()) {
					synchronized (outgoingEndpoints) {
						outgoingEndpoints
							.computeIfAbsent(node, n -> Collections.synchronizedMap(new LinkedHashMap<>()))
							.computeIfAbsent(outgoingConnection, ic -> new CompletableFuture<E>());
					}
					
					// Outgoing handler consumer - wiring to the connection source endpoint or to the node incoming endpoint
					CompletableFuture<E> endpoint;
					if (factory.isPassThrough(outgoingConnection)) {
						// Wiring to the source node outgoing endpoint
						Node target = outgoingConnection.getTarget();
						if (target == null) {
							endpoint = null;
						} else {
							synchronized (incomingEndpoints) {
								endpoint = incomingEndpoints
										.computeIfAbsent(target, n -> Collections.synchronizedMap(new LinkedHashMap<>()))
										.computeIfAbsent(outgoingConnection, ic -> new CompletableFuture<E>());
							}
						}
					} else {
						// Wiring to the connection target endpoint
						endpoint = sourceEndpoints.computeIfAbsent(outgoingConnection, ic -> new CompletableFuture<E>());
					}
					
					outgoingHandlerConsumers.put(outgoingConnection, handler -> {
						if (endpoint != null) {
							endpoint.complete(factory.createEndpoint(outgoingConnection, handler, HandlerType.OUTGOING));
						}
					});
				}
				
				config = new NodeProcessorConfig<P, H, E, R>() {
					
					@Override
					public Node getElement() {
						return node;
					}
	
					@Override
					public Map<Element, ProcessorInfo<P,R>> getChildProcessorsInfo() {
						Map<Element,ProcessorInfo<P,R>> ret = new LinkedHashMap<>();
						childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessorInfo()));
						return ret;
					}
					
					@Override
					public CompletableFuture<ProcessorInfo<P,R>> getParentProcessorInfo() {
						return parentProcessorInfoCompletableFuture;
					}
	
					@Override
					public CompletableFuture<R> getRegistry() {
						return registryCompletableFuture;
					}
	
					@Override
					public Map<Connection, CompletionStage<E>> getIncomingEndpoints() {
						synchronized(incomingEndpoints) {
							return Collections.unmodifiableMap(incomingEndpoints.computeIfAbsent(node, e -> Collections.synchronizedMap(new LinkedHashMap<>())));
						}
					}
	
					@Override
					public Map<Connection, Consumer<H>> getIncomingHandlerConsumers() {
						return Collections.unmodifiableMap(incomingHandlerConsumers);
					}
	
					@Override
					public Map<Connection, CompletionStage<E>> getOutgoingEndpoints() {
						synchronized(outgoingEndpoints) {
							return Collections.unmodifiableMap(outgoingEndpoints.computeIfAbsent(node, e -> Collections.synchronizedMap(new LinkedHashMap<>())));
						}
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
					CompletableFuture<E> outgoingEndpoint = source == null ? null : outgoingEndpoints.computeIfAbsent(source, n -> Collections.synchronizedMap(new LinkedHashMap<>())).computeIfAbsent(connection, ic -> new CompletableFuture<E>());
					CompletableFuture<E> incomingEndpoint = target == null ? null : incomingEndpoints.computeIfAbsent(target, n -> Collections.synchronizedMap(new LinkedHashMap<>())).computeIfAbsent(connection, ic -> new CompletableFuture<E>());
					
					config = new ConnectionProcessorConfig<P, H, E, R>() {
						
						@Override
						public Connection getElement() {
							return connection;
						}
	
						@Override
						public Map<Element, ProcessorInfo<P,R>> getChildProcessorsInfo() {
							Map<Element,ProcessorInfo<P,R>> ret = new LinkedHashMap<>();
							childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessorInfo()));
							return ret;
						}
	
						@Override
						public CompletableFuture<ProcessorInfo<P,R>> getParentProcessorInfo() {
							return parentProcessorInfoCompletableFuture;
						}
	
						@Override
						public CompletableFuture<R> getRegistry() {
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
				config = new ProcessorConfig<P,R>() {
					
					@Override
					public Element getElement() {
						return element;
					}
	
					@Override
					public Map<Element, ProcessorInfo<P, R>> getChildProcessorsInfo() {
						Map<Element,ProcessorInfo<P,R>> ret = new LinkedHashMap<>();
						childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessorInfo()));
						return ret;
					}
	
					@Override
					public CompletableFuture<ProcessorInfo<P,R>> getParentProcessorInfo() {
						return parentProcessorInfoCompletableFuture;
					}
	
					@Override
					public CompletableFuture<R> getRegistry() {
						return registryCompletableFuture;
					}
					
				};
			}
			
			ProcessorInfo<P,R> processorInfo = config == null ? null : factory.createProcessor(config, subMonitor);
			
			if (childProcessors != null) {
				childProcessors.values().forEach(ch -> ch.setParentProcessorInfo(processorInfo));
			}
			if (processorInfo != null) {
				registry.put(element, processorInfo);
			}
			
			subMonitor.worked(1, "Created a processor", element, processorInfo);
			
			return new Helper<>(processorInfo) {
	
				@Override
				void setParentProcessorInfo(ProcessorInfo<P,R> parentProcessorInfo) {
					parentProcessorInfoCompletableFuture.complete(parentProcessorInfo);
				}
	
				@Override
				void setRegistry(R registry) {
					childProcessors.values().forEach(ch -> ch.setRegistry(registry));
					registryCompletableFuture.complete(registry);
				}
				
			};
		} 
	}

}
