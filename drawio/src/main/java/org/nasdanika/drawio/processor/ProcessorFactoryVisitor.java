package org.nasdanika.drawio.processor;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.common.Reference;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Node;

class ProcessorFactoryVisitor<P,T,R,U,S> {
	
	private ProcessorFactory<P, T, R, U, S> factory;
	private Map<Element, P> registry = new LinkedHashMap<>();
	
	// Handlers to call connection sources by outbound endpoints
	private Map<Connection, Function<U,S>> sourceHandlers = new HashMap<>();
	
	// Handlers to call connection targets by inbound endpoints
	private Map<Connection, Function<U,S>> targetHandlers = new HashMap<>();
	
	// Handlers to call nodes by target endpoints or by outbound endpoints for pass-through connections.
	private Map<Node, Map<Connection, Function<U,S>>> inboundHandlers = new HashMap<>();
	
	// Handlers to call nodes by source endpoints or by inbound endpoints for pass-through connections.
	private Map<Node, Map<Connection, Function<U,S>>> outboundHandlers = new HashMap<>();	

	ProcessorFactoryVisitor(ProcessorFactory<P,T,R,U,S> factory) {
		this.factory = factory;
	}
		
	Map<Element, P> getRegistry() {
		return registry;
	}	


	public Helper<P> createElementProcessor(Element element, Map<Element, Helper<P>> childProcessors) {
		Reference<P> parentProcessorReference = new Reference<>();
		ElementProcessorConfig<? extends Element, P> config;
		if (element instanceof Node) {
			Node node = (Node) element;
			Map<Connection, Function<T, R>> inboundEndpoints = new HashMap<>();
			Map<Connection, Consumer<Function<U, S>>> inboundHandlerConsumers = new HashMap<>();
			for (Connection inboundConnection: node.getInboundConnections()) {
				Function<U, S> inboundEndpointHandler = new Function<U, S>() {

					@Override
					public S apply(U t) {
						if (factory.isPassThrough(inboundConnection)) {
							// Calling outbound handler directly as there is no target handler
							Node inboundConnectionSource = inboundConnection.getSource();
							if (inboundConnectionSource == null) {
								throw new IllegalStateException("Inbound connection has no source node: " + inboundConnection);
							}
							Map<Connection, Function<U, S>> sourceOutboundHandlers = outboundHandlers.get(inboundConnectionSource);
							if (sourceOutboundHandlers == null) {
								throw new IllegalStateException("Outbound handlers not found for " + inboundConnectionSource);
							}
							Function<U, S> sourceOutboundHandler = sourceOutboundHandlers.get(inboundConnection);
							if (sourceOutboundHandler == null) {
								throw new IllegalStateException("Source outbound hanlder not found for inbound connection " + inboundConnection);
							}
							return sourceOutboundHandler.apply(t);
						}
						
						Function<U, S> targetHandler = targetHandlers.get(inboundConnection);
						if (targetHandler == null) {
							throw new IllegalStateException("Target handler is not set for connection " + inboundConnection);
						}
						return targetHandler.apply(t);
					}
					
				};
				Function<T, R> inboundEndpoint = factory.createEndpoint(inboundConnection, inboundEndpointHandler, EndpointType.INBOUND);
				inboundEndpoints.put(inboundConnection, inboundEndpoint);
				
				Consumer<Function<U, S>> inboundHandlerConsumer = new Consumer<Function<U,S>>() {
					
					@Override
					public void accept(Function<U, S> inboundHandler) {
						inboundHandlers.computeIfAbsent(node, n -> new HashMap<>()).put(inboundConnection, inboundHandler);
					}
					
				};
				inboundHandlerConsumers.put(inboundConnection, inboundHandlerConsumer);										
			}
			
			Map<Connection, Function<T, R>> outboundEndpoints = new HashMap<>();
			Map<Connection, Consumer<Function<U, S>>> outboundHandlerConsumers = new HashMap<>();
			for (Connection outboundConnection: node.getOutboundConnections()) {
				Function<U, S> outboundEndpointHandler = new Function<U, S>() {

					@Override
					public S apply(U t) {
						if (factory.isPassThrough(outboundConnection)) {
							// Calling inbound handler directly as there is no source handler
							Node outboundConnectionTarget = outboundConnection.getTarget();
							if (outboundConnectionTarget == null) {
								throw new IllegalStateException("Outbound connection has no target node: " + outboundConnection);
							}
							Map<Connection, Function<U, S>> targetInboundHandlers = inboundHandlers.get(outboundConnectionTarget);
							if (targetInboundHandlers == null) {
								throw new IllegalStateException("Inbound handlers not found for " + outboundConnectionTarget);
							}
							Function<U, S> targetInboundHandler = targetInboundHandlers.get(outboundConnection);
							if (targetInboundHandler == null) {
								throw new IllegalStateException("Target inbound hanlder not found for outbound connection " + outboundConnection);
							}
							return targetInboundHandler.apply(t);
						}
						
						Function<U, S> sourceHandler = sourceHandlers.get(outboundConnection);
						if (sourceHandler == null) {
							throw new IllegalStateException("Source handler is not set for connection " + outboundConnection);
						}
						return sourceHandler.apply(t);
					}
					
				};
				Function<T, R> outboundEndpoint = factory.createEndpoint(outboundConnection, outboundEndpointHandler, EndpointType.OUTBOUND);
				outboundEndpoints.put(outboundConnection, outboundEndpoint);
				
				Consumer<Function<U, S>> outboundHandlerConsumer = new Consumer<Function<U,S>>() {
					
					@Override
					public void accept(Function<U, S> inboundHandler) {
						outboundHandlers.computeIfAbsent(node, n -> new HashMap<>()).put(outboundConnection, inboundHandler);
					}
					
				};
				outboundHandlerConsumers.put(outboundConnection, outboundHandlerConsumer);										
			}
			
			config = new NodeProcessorConfig<P, T, R, U, S>() {

				@Override
				public Node getElement() {
					return node;
				}

				@Override
				public Map<Element, P> getChildProcessors() {
					Map<Element,P> ret = new LinkedHashMap<>();
					childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessor()));
					return ret;
				}

				@Override
				public P getParentProcessor() {
					return parentProcessorReference.get();
				}

				@Override
				public Map<Element, P> getRegistry() {
					return registry;
				}

				@Override
				public Map<Connection, Function<T, R>> getInboundEndpoints() {
					return inboundEndpoints;
				}

				@Override
				public Map<Connection, Consumer<Function<U, S>>> getInboundHandlerConsumers() {
					return inboundHandlerConsumers;
				}

				@Override
				public Map<Connection, Function<T, R>> getOutboundEndpoints() {
					return outboundEndpoints;
				}

				@Override
				public Map<Connection, Consumer<Function<U, S>>> getOutboundHandlerConsumers() {
					return outboundHandlerConsumers;
				}
			};

		} else if (element instanceof Connection) {
			if (factory.isPassThrough((Connection) element)) {
				config = null;
			} else {
				Connection connection = (Connection) element;
				Node source = connection.getSource();
				Function<T, R> sourceEndpoint;
				if (source == null) {
					sourceEndpoint = null;
				} else {
					Function<U, S> handler = new Function<U, S>() {
	
						@Override
						public S apply(U t) {
							Map<Connection, Function<U, S>> sourceOutboundHandlers = outboundHandlers.get(source);
							if (sourceOutboundHandlers == null) {
								throw new IllegalStateException("Outbound handlers not found for " + source);
							}																					
							Function<U, S> sourceHandler = sourceOutboundHandlers.get(connection);
							if (sourceHandler == null) {
								throw new IllegalStateException("Source handler is not set for connection " + connection);
							}
							return sourceHandler.apply(t);
						}
						
					};
					sourceEndpoint = factory.createEndpoint(connection, handler, EndpointType.SOURCE);
				}
				
				Consumer<Function<U, S>> sourceHandlerConsumer = new Consumer<Function<U,S>>() {

					@Override
					public void accept(Function<U, S> handler) {
						sourceHandlers.put((Connection) element, handler);						
					}
					
				};
				
				Node target = connection.getTarget();
				Function<T, R> targetEndpoint;
				if (target == null) {
					targetEndpoint = null;
				} else {
					targetEndpoint = factory.createEndpoint(connection, new Function<U, S>() {
	
						@Override
						public S apply(U t) {
							Map<Connection, Function<U, S>> targetInboundHandlers = inboundHandlers.get(target);
							if (targetInboundHandlers == null) {
								throw new IllegalStateException("Inbound handlers not found for " + target);
							}																					
							Function<U, S> targetHandler = targetInboundHandlers.get(connection);
							if (targetHandler == null) {
								throw new IllegalStateException("Target handler is not set for connection " + connection);
							}
							return targetHandler.apply(t);
						}
						
					}, EndpointType.TARGET);
				}
				Consumer<Function<U, S>> targetHandlerConsumer = new Consumer<Function<U,S>>() {

					@Override
					public void accept(Function<U, S> handler) {
						targetHandlers.put((Connection) element, handler);						
					}
					
				};
				
				config = new ConnectionProcessorConfig<P, T, R, U, S>() {

					@Override
					public Connection getElement() {
						return connection;
					}

					@Override
					public Map<Element, P> getChildProcessors() {
						Map<Element,P> ret = new LinkedHashMap<>();
						childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessor()));
						return ret;
					}

					@Override
					public P getParentProcessor() {
						return parentProcessorReference.get();
					}

					@Override
					public Map<Element, P> getRegistry() {
						return registry;
					}

					@Override
					public Function<T, R> getSourceEndpoint() {
						return sourceEndpoint;
					}

					@Override
					public void setSourceHandler(Function<U, S> sourceHandler) {
						sourceHandlerConsumer.accept(sourceHandler);						
					}

					@Override
					public Function<T, R> getTargetEndpoint() {
						return targetEndpoint;
					}

					@Override
					public void setTargetHandler(Function<U, S> targetHandler) {
						targetHandlerConsumer.accept(targetHandler);						
					}
				};
			}
		} else {
			config = new ElementProcessorConfig<Element, P>() {

				@Override
				public Element getElement() {
					return element;
				}

				@Override
				public Map<Element, P> getChildProcessors() {
					Map<Element,P> ret = new LinkedHashMap<>();
					childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessor()));
					return ret;
				}

				@Override
				public P getParentProcessor() {
					return parentProcessorReference.get();
				}

				@Override
				public Map<Element, P> getRegistry() {
					return registry;
				}
				
			};
		}

		P processor = config == null ? null : factory.createProcessor(config);
		
		if (childProcessors != null) {
			for (Helper<P> childHelper: childProcessors.values()) {
				childHelper.setParentProcessor(processor);
			}
		}
		if (processor != null) {
			registry.put(element, processor);
		}
		return new Helper<>(processor, parentProcessorReference);
	}

}
