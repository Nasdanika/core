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
	private Map<Connection, Function<U,S>> sourceHandlers = new HashMap<>();
	private Map<Connection, Function<U,S>> targetHandlers = new HashMap<>();
	private Map<Node, Map<Connection, Function<U,S>>> inboundHandlers = new HashMap<>();
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
			Map<Connection, Function<T, R>> inboundEndpoints = new HashMap<>();
			Map<Connection, Consumer<Function<U, S>>> inboundHandlerConsumers = new HashMap<>();
			Node node = (Node) element;
			for (Connection inboundConnection: node.getInboundConnections()) {
				if (factory.isPassThrough(inboundConnection)) {
					Node inboundConnectionSource = inboundConnection.getSource();
					if (inboundConnectionSource != null) {
						Function<U, S> inboundHandler = new Function<U, S>() {
	
							@Override
							public S apply(U t) {
								Map<Connection, Function<U, S>> sourceOutboundHandlers = outboundHandlers.get(inboundConnectionSource);
								if (sourceOutboundHandlers == null) {
									throw new IllegalStateException("Outbound handlers not found for " + inboundConnectionSource);
								}							
								Function<U, S> оutboundHandler = sourceOutboundHandlers.get(inboundConnection);
								if (оutboundHandler == null) {
									throw new IllegalStateException("Outbound handler is not set for connection " + inboundConnection);
								}
								return оutboundHandler.apply(t);
							}
							
						};
						Function<T, R> inboundEndpoint = factory.createEndpoint(inboundConnection, inboundHandler, EndpointType.INBOUND);
						inboundEndpoints.put(inboundConnection, inboundEndpoint);						
					}
					Consumer<Function<U, S>> inboundHandlerConsumer = new Consumer<Function<U,S>>() {
						
						@Override
						public void accept(Function<U, S> handler) {
							Map<Connection, Function<U, S>> nodeInboundHandlers = inboundHandlers.computeIfAbsent(node, n -> new HashMap<>());							
							nodeInboundHandlers.put(inboundConnection, handler);						
						}
						
					};
					inboundHandlerConsumers.put(inboundConnection, inboundHandlerConsumer);					
				} else {
					Function<U, S> handler = new Function<U, S>() {

						@Override
						public S apply(U t) {
							Function<U, S> targetHandler = targetHandlers.get(inboundConnection);
							if (targetHandler == null) {
								throw new IllegalStateException("Target handler is not set for connection " + inboundConnection);
							}
							return targetHandler.apply(t);
						}
						
					};
					Function<T, R> inboundEndpoint = factory.createEndpoint(inboundConnection, handler, EndpointType.INBOUND);
					inboundEndpoints.put(inboundConnection, inboundEndpoint);
					
					Consumer<Function<U, S>> inboundHandlerConsumer = new Consumer<Function<U,S>>() {
						
						@Override
						public void accept(Function<U, S> handler) {
							targetHandlers.put(inboundConnection, handler);
						}
						
					};
					inboundHandlerConsumers.put(inboundConnection, inboundHandlerConsumer);										
				}
			}
			
			Map<Connection, Function<T, R>> outboundEndpoints = new HashMap<>();
			Map<Connection, Consumer<Function<U, S>>> outboundHandlerConsumers = new HashMap<>();
			for (Connection outboundConnection: node.getOutboundConnections()) {
				if (factory.isPassThrough(outboundConnection)) {
					Node outboundConnectionTarget = outboundConnection.getTarget();
					if (outboundConnectionTarget != null) {
						Function<U, S> handler = new Function<U, S>() {
	
							@Override
							public S apply(U t) {
								Map<Connection, Function<U, S>> targetInboundHandlers = inboundHandlers.get(outboundConnectionTarget);
								if (targetInboundHandlers == null) {
									throw new IllegalStateException("Inbound handlers not found for " + outboundConnectionTarget);
								}							
								Function<U, S> inboundHandler = targetInboundHandlers.get(outboundConnection);
								if (inboundHandler == null) {
									throw new IllegalStateException("Inbound handler is not set for connection " + outboundConnection);
								}
								return inboundHandler.apply(t);
							}
							
						};
						Function<T, R> outboundEndpoint = factory.createEndpoint(outboundConnection, handler, EndpointType.OUTBOUND);
						outboundEndpoints.put(outboundConnection, outboundEndpoint);
					}
					
					Consumer<Function<U, S>> outboundHandlerConsumer = new Consumer<Function<U,S>>() {

						@Override
						public void accept(Function<U, S> handler) {
							Map<Connection, Function<U, S>> nodeOutboundHandlers = outboundHandlers.computeIfAbsent(node, n -> new HashMap<>());							
							nodeOutboundHandlers.put(outboundConnection, handler);						
						}
						
					};
					outboundHandlerConsumers.put(outboundConnection, outboundHandlerConsumer);
				} else {
					Function<U, S> handler = new Function<U, S>() {

						@Override
						public S apply(U t) {
							Function<U, S> sourceHandler = sourceHandlers.get(outboundConnection);
							if (sourceHandler == null) {
								throw new IllegalStateException("Source handler is not set for connection " + outboundConnection);
							}
							return sourceHandler.apply(t);
						}
						
					};
					Function<T, R> outboundEndpoint = factory.createEndpoint(outboundConnection, handler, EndpointType.OUTBOUND);
					inboundEndpoints.put(outboundConnection, outboundEndpoint);
					
					Consumer<Function<U, S>> outboundHandlerConsumer = new Consumer<Function<U,S>>() {
						
						@Override
						public void accept(Function<U, S> handler) {
							sourceHandlers.put(outboundConnection, handler);
						}
						
					};
					outboundHandlerConsumers.put(outboundConnection, outboundHandlerConsumer);															
				}
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
