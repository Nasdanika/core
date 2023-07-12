package org.nasdanika.graph.processor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reference;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

/**
 * Processor config factory creates and "wires" {@link ProcessorConfig}s so they can be used to create processors.
 * @author Pavel
 *
 */
public abstract class ProcessorConfigFactory<H,E> {

	protected interface Registration {
		
		ProcessorConfig getConfig();
		
		void setParentConfig(ProcessorConfig config);
		
		void setRegistry(Map<Element, ProcessorConfig> registry);
		
	}		
	
	/**
	 * Creates a map/registry of element to processor config. 
	 * @author Pavel
	 *
	 * @param <P> Processor type.
	 * @param <H> Handler type.
	 * @param <E> Endpoint type.
	 */
	protected class Visitor {
		
		private Map<Element, Registration> registry = Collections.synchronizedMap(new LinkedHashMap<>());
		
		// Endpoints for connection source to call the connection
		private Map<Connection,CompletableFuture<E>> sourceEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());
		
		// Endpoints for connection target to call the connection
		private Map<Connection, CompletableFuture<E>> targetEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());
	
		private Map<Node, Map<Connection, CompletableFuture<E>>> incomingEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());
		
		// Endpoints for outgoing connections to call the source node
		private Map<Node, Map<Connection, CompletableFuture<E>>> outgoingEndpoints = Collections.synchronizedMap(new LinkedHashMap<>());	
				
		Map<Element, Registration> getRegistry() {
			return registry;
		}	
	
		Registration createElementProcessorHelper(Element element, Map<? extends Element, Registration> childRegistrations, ProgressMonitor progressMonitor) {
			if (progressMonitor.isCancelled()) {
				throw new CancellationException();
			}		
			try (ProgressMonitor subMonitor =  progressMonitor.split("Creating element helper", 1, element)) {
				Map<Element, ProcessorConfig> childConfigs = new LinkedHashMap<>();
				childRegistrations.forEach((e,r) -> childConfigs.put(e, r.getConfig()));
				
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
						if (ProcessorConfigFactory.this.isPassThrough(incomingConnection)) {
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
								endpoint.complete(ProcessorConfigFactory.this.createEndpoint(incomingConnection, handler, HandlerType.INCOMING));
							}
						});
					}
					
					Map<Connection, Consumer<H>> outgoingHandlerConsumers = new LinkedHashMap<>();
					Map<Connection, CompletableFuture<E>> nodeOutgoingEndpoints = outgoingEndpoints.computeIfAbsent(node, n -> Collections.synchronizedMap(new LinkedHashMap<>()));
					for (Connection outgoingConnection: node.getOutgoingConnections()) {
						nodeOutgoingEndpoints.computeIfAbsent(outgoingConnection, ic -> new CompletableFuture<E>());
						
						// Outgoing handler consumer - wiring to the connection source endpoint or to the node incoming endpoint
						CompletableFuture<E> endpoint;
						if (ProcessorConfigFactory.this.isPassThrough(outgoingConnection)) {
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
								endpoint.complete(ProcessorConfigFactory.this.createEndpoint(outgoingConnection, handler, HandlerType.OUTGOING));
							}
						});
					}
					
					config = new NodeProcessorConfig<H,E>() {
						
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
					if (ProcessorConfigFactory.this.isPassThrough((Connection) element)) {
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
									outgoingEndpoint.complete(ProcessorConfigFactory.this.createEndpoint(connection, sourceHandler, HandlerType.SOURCE));
								}
							}
		
							@Override
							public CompletionStage<E> getTargetEndpoint() {
								return targetEndpoint;
							}
		
							@Override
							public void setTargetHandler(H targetHandler) {
								if (incomingEndpoint != null) {
									incomingEndpoint.complete(ProcessorConfigFactory.this.createEndpoint(connection, targetHandler, HandlerType.TARGET));
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
	
	
	/**
	 * If a connection is pass-through its source endpoint is connected directly to the target node handler and vice versa.
	 * @param connection
	 * @return
	 */
	protected boolean isPassThrough(Connection connection) {
		return true;
	};
	
	/**
	 * Creates an endpoint to invoke the argument handler of specified type.
	 * @param connection
	 * @param handler
	 * @param type
	 * @return
	 */
	protected abstract E createEndpoint(Connection connection, H handler, HandlerType type);
	
	public Map<Element, ProcessorConfig> createConfigs(ProgressMonitor progressMonitor, boolean parallel, Element... elements) {
		Stream<Element> stream = Arrays.stream(elements);
		return createProcessors(parallel ? stream.parallel() : stream , parallel, progressMonitor);
	}
	
	public Map<Element, ProcessorConfig> createConfigs(Collection<? extends Element> elements, boolean parallel, ProgressMonitor progressMonitor) {
		return createProcessors(parallel ? elements.parallelStream() : elements.stream(), parallel, progressMonitor);
	}
	
	public Map<Element, ProcessorConfig> createProcessors(Stream<? extends Element> elements, boolean parallel, ProgressMonitor progressMonitor) {
		Visitor visitor = new Visitor();				
		BiFunction<Element, Map<? extends Element, Registration>, Registration> createElementProcessorConfig = (element, childProcessors) -> visitor.createElementProcessorHelper(element, childProcessors, progressMonitor);
		Stream<Registration> registrations = elements.map(element -> element.accept(createElementProcessorConfig)).collect(Collectors.toList()).stream();
		if (parallel) {
			registrations = registrations.parallel();
		}
		Map<Element, ProcessorConfig> registry = new LinkedHashMap<>();
		visitor.getRegistry().forEach((e,r) -> {
			ProcessorConfig config = r.getConfig();
			if (config != null) {
				registry.put(e, config);
			}
		});		
		registrations.forEach(helper -> helper.setRegistry(registry));
		return registry;		
	}

}
