package org.nasdanika.graph.processor;

//import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

//import org.nasdanika.common.InvocationRecord;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reference;
//import org.nasdanika.common.Util;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

/**
 * Creates a map/registry of element to processor config. 
 * @author Pavel
 *
 * @param <P> Processor type.
 * @param <H> Handler type.
 * @param <E> Endpoint type.
 */
class ProcessorConfigFactoryVisitor<H,E> {
	
	interface Registration {
		
		ProcessorConfig getConfig();
		
		void setParentConfig(ProcessorConfig config);
		
		void setRegistry(Map<Element, ProcessorConfig> registry);
		
	}	
	
	private ProcessorConfigFactory<H,E> factory;
	
	private Map<Element, Registration> registry = Collections.synchronizedMap(new LinkedHashMap<>());
	
	// Endpoints for connection source to call the connection
	private Map<Connection,CompletableFuture<E>> sourceEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());
	
	// Endpoints for connection target to call the connection
	private Map<Connection, CompletableFuture<E>> targetEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());

	private Map<Node, Map<Connection, CompletableFuture<E>>> incomingEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());
	
	// Endpoints for outgoing connections to call the source node
	private Map<Node, Map<Connection, CompletableFuture<E>>> outgoingEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());	

	ProcessorConfigFactoryVisitor(ProcessorConfigFactory<H,E> factory) {
		this.factory = factory;
	}
			
	Map<Element, Registration> getRegistry() {
		return registry;
	}	

	Registration createElementProcessorHelper(Element element, Map<? extends Element, Registration> childRegistrations, ProgressMonitor progressMonitor) {
		if (progressMonitor.isCancelled()) {
			throw new CancellationException();
		}		
		try (ProgressMonitor subMonitor =  progressMonitor.split("Creating element helper", 1, element)) {
			Map<Element, ProcessorConfig> childConfigs = new LinkedHashMap<>();
			childRegistrations.forEach((e,h) -> childConfigs.put(e, h.getConfig()));
			
			Reference<ProcessorConfig> parentReference = new Reference<>();
			Reference<Map<Element, ProcessorConfig>> registryReference = new Reference<>();
			ProcessorConfig config;
			if (element instanceof Node) {
				Node node = (Node) element;
				Map<Connection, Consumer<H>> incomingHandlerConsumers = Collections.synchronizedMap(new LinkedHashMap<>());
				Map<Connection, CompletableFuture<E>> nodeIncomingEndpoints = incomingEndpoints.computeIfAbsent(node, n -> Collections.synchronizedMap(new LinkedHashMap<>()));
				
				for (Connection incomingConnection: node.getIncomingConnections()) {
					// Incoming endpoint - creating if not there
					nodeIncomingEndpoints.computeIfAbsent(incomingConnection, ic -> new CompletableFuture<E>());
					
					// Incoming handler consumer - wiring to the connection target endpoint or to the node outgoing endpoint
					CompletableFuture<E> endpoint;
					if (factory.isPassThrough(incomingConnection)) {
						// Wiring to the source node outgoing endpoint
						Node source = incomingConnection.getSource();
						if (source == null) {
							endpoint = null;
						} else {
							endpoint = outgoingEndpoints
									.computeIfAbsent(source, n -> Collections.synchronizedMap(new LinkedHashMap<>()))
									.computeIfAbsent(incomingConnection, ic -> new CompletableFuture<E>());
						}
					} else {
						endpoint = targetEndpoints.computeIfAbsent(incomingConnection, ic -> new CompletableFuture<E>());
					}
					
					incomingHandlerConsumers.put(incomingConnection, handler -> {
						if (endpoint != null) {
							endpoint.complete(factory.createEndpoint(incomingConnection, handler, HandlerType.INCOMING));
						}
					});
				}
				
				Map<Connection, Consumer<H>> outgoingHandlerConsumers = new LinkedHashMap<>();
				Map<Connection, CompletableFuture<E>> nodeOutgoingEndpoints = outgoingEndpoints.computeIfAbsent(node, n -> Collections.synchronizedMap(new LinkedHashMap<>()));
				for (Connection outgoingConnection: node.getOutgoingConnections()) {
					nodeOutgoingEndpoints.computeIfAbsent(outgoingConnection, ic -> new CompletableFuture<E>());
					
					// Outgoing handler consumer - wiring to the connection source endpoint or to the node incoming endpoint
					CompletableFuture<E> endpoint;
					if (factory.isPassThrough(outgoingConnection)) {
						// Wiring to the source node outgoing endpoint
						Node target = outgoingConnection.getTarget();
						if (target == null) {
							endpoint = null;
						} else {
							endpoint = incomingEndpoints
									.computeIfAbsent(target, n -> Collections.synchronizedMap(new LinkedHashMap<>()))
									.computeIfAbsent(outgoingConnection, ic -> new CompletableFuture<E>());
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
				
				config = new NodeProcessorConfig() {
					
					@Override
					public Node getElement() {
						return node;
					}
	
					@SuppressWarnings({ "unchecked", "rawtypes" }) 
					@Override
					public Map<Connection, CompletionStage<E>> getIncomingEndpoints() {
						return (Map) nodeIncomingEndpoints; // Rude cast to resolve compiler's unhappiness
					}
	
					@Override
					public Map<Connection, Consumer<H>> getIncomingHandlerConsumers() {
						return incomingHandlerConsumers;
					}
	
					@SuppressWarnings({ "unchecked", "rawtypes" }) 
					@Override
					public Map<Connection, CompletionStage<E>> getOutgoingEndpoints() {
						return (Map) nodeOutgoingEndpoints; // Rude cast to resolve compiler's unhappiness
					}
	
					@Override
					public Map<Connection, Consumer<H>> getOutgoingHandlerConsumers() {
						return outgoingHandlerConsumers;
					}

					@Override
					public Map<Element, ProcessorConfig> getChildProcessorsConfig() {
						return childConfigs;
					}

					@Override
					public ProcessorConfig getParentProcessorConfig() {
						return parentReference.get();
					}

					@Override
					public Map<Element, ProcessorConfig> getRegistry() {
						return registryReference.get();
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
					
					config = new ConnectionProcessorConfig<H, E>() {
						
						@Override
						public Connection getElement() {
							return connection;
						}

						@Override
						public Map<Element, ProcessorConfig> getChildProcessorsConfig() {
							return childConfigs;
						}

						@Override
						public ProcessorConfig getParentProcessorConfig() {
							return parentReference.get();
						}

						@Override
						public Map<Element, ProcessorConfig> getRegistry() {
							return registryReference.get();
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
				config = new ProcessorConfig() {
					
					@Override
					public Element getElement() {
						return element;
					}
	
					@Override
					public Map<Element, ProcessorConfig> getChildProcessorsConfig() {
						return childConfigs;
					}

					@Override
					public ProcessorConfig getParentProcessorConfig() {
						return parentReference.get();
					}

					@Override
					public Map<Element, ProcessorConfig> getRegistry() {
						return registryReference.get();
					}
					
				};
			}
			
			if (config == null) {
				return null;
			}
			
			Registration registration = new Registration() {
				
				@Override
				public ProcessorConfig getConfig() {
					return config;
				}
				
				public void setParentConfig(ProcessorConfig config) {
					parentReference.set(config);
				}
				
				public void setRegistry(java.util.Map<Element,ProcessorConfig> registry) {
					registryReference.set(registry);
					childRegistrations.values().forEach(ch -> ch.setRegistry(registry));
				}
				
			};
			
			if (childRegistrations != null) {
				childRegistrations.values().forEach(ch -> ch.setParentConfig(config));
			}
			registry.put(element, registration);
			
			subMonitor.worked(1, "Created a registration", element, registration);
			return registration;
		} 
	}

}
