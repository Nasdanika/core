package org.nasdanika.drawio.processors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Node;

/**
 * 
 * @author Pavel
 *
 * @param <T> Processor type returned to the parent or client code. It can be used to call the processor.  
 * @param <U> Parameter type of the {@link Endpoint} output, what is passed to an endpoint.
 * @param <R> Return type of the endpoint output, what is returned by an endpoint.
 * @param <V> Parameter type of the endpoint input, what endpoint passes to {@link Connection}/{@link Node} processor.
 * @param <S> Return type of the endpoint input, what is returned to the endpoint by Connection/Node processor.
 */
public abstract class ProcessorFactory<T,U,R,V,S> implements Resolver<T> {
	
	private Map<Element, T> processors = new HashMap<>();
	private Map<Connection, Function<V,S>> sourceHandlers = new HashMap<>();
	private Map<Connection, Function<V,S>> targetHandlers = new HashMap<>();
	private Map<Node, Map<Connection, Function<V,S>>> inboundHandlers = new HashMap<>();
	private Map<Node, Map<Connection, Function<V,S>>> outboundHandlers = new HashMap<>();

	@Override
	public Stream<T> select(Predicate<Element> predicate) {
		return processors.entrySet().stream().filter(e -> predicate.test(e.getKey())).map(Map.Entry::getValue);
	}
	
	@Override
	public T resolve(Element element) {
		return processors.get(element);
	}

	/**
	 * Creates element processor. Pass this function to Element.accept() method to create processors for the element and its children.
	 * @param element
	 * @param childProcessors
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T createProcessors(Element element, Map<Element, T> childProcessors) {
		T processor;
		if (element instanceof Node) {
			Map<Connection, Function<U, R>> inboundEndpoints = new HashMap<>();
			Map<Connection, Consumer<Function<V, S>>> inboundHandlerConsumers = new HashMap<>();
			Node node = (Node) element;
			for (Connection inboundConnection: node.getInboundConnections()) {
				if (isPassTroughConnection(inboundConnection)) {
					Node inboundConnectionSource = inboundConnection.getSource();
					if (inboundConnectionSource != null) {
						Function<U, R> inboundEndpoint = createEndpoint(new Function<V, S>() {
	
							@Override
							public S apply(V t) {
								Map<Connection, Function<V, S>> sourceOutboundHandlers = outboundHandlers.get(inboundConnectionSource);
								if (sourceOutboundHandlers == null) {
									throw new IllegalStateException("Outbound handlers not found for " + inboundConnectionSource);
								}							
								Function<V, S> оutboundHandler = sourceOutboundHandlers.get(inboundConnection);
								if (оutboundHandler == null) {
									throw new IllegalStateException("Outbound handler is not set for connection " + inboundConnection);
								}
								return оutboundHandler.apply(t);
							}
							
						});
						inboundEndpoints.put(inboundConnection, inboundEndpoint);						
					}
					Consumer<Function<V, S>> inboundHandlerConsumer = new Consumer<Function<V,S>>() {
						
						@Override
						public void accept(Function<V, S> handler) {
							Map<Connection, Function<V, S>> nodeInboundHandlers = inboundHandlers.computeIfAbsent(node, n -> new HashMap<>());							
							nodeInboundHandlers.put(inboundConnection, handler);						
						}
						
					};
					inboundHandlerConsumers.put(inboundConnection, inboundHandlerConsumer);					
				} else {
					Function<U, R> inboundEndpoint = createEndpoint(new Function<V, S>() {

						@Override
						public S apply(V t) {
							Function<V, S> targetHandler = targetHandlers.get(inboundConnection);
							if (targetHandler == null) {
								throw new IllegalStateException("Target handler is not set for connection " + inboundConnection);
							}
							return targetHandler.apply(t);
						}
						
					});
					inboundEndpoints.put(inboundConnection, inboundEndpoint);
					
					Consumer<Function<V, S>> inboundHandlerConsumer = new Consumer<Function<V,S>>() {
						
						@Override
						public void accept(Function<V, S> handler) {
							targetHandlers.put(inboundConnection, handler);
						}
						
					};
					inboundHandlerConsumers.put(inboundConnection, inboundHandlerConsumer);										
				}
			}
			
			Map<Connection, Function<U, R>> outboundEndpoints = new HashMap<>();
			Map<Connection, Consumer<Function<V, S>>> outboundHandlerConsumers = new HashMap<>();
			for (Connection outboundConnection: node.getOutboundConnections()) {
				if (isPassTroughConnection(outboundConnection)) {
					Node outboundConnectionTarget = outboundConnection.getTarget();
					if (outboundConnectionTarget != null) {
						Function<U, R> outboundEndpoint = createEndpoint(new Function<V, S>() {
	
							@Override
							public S apply(V t) {
								Map<Connection, Function<V, S>> targetInboundHandlers = inboundHandlers.get(outboundConnectionTarget);
								if (targetInboundHandlers == null) {
									throw new IllegalStateException("Inbound handlers not found for " + outboundConnectionTarget);
								}							
								Function<V, S> inboundHandler = targetInboundHandlers.get(outboundConnection);
								if (inboundHandler == null) {
									throw new IllegalStateException("Inbound handler is not set for connection " + outboundConnection);
								}
								return inboundHandler.apply(t);
							}
							
						});
						outboundEndpoints.put(outboundConnection, outboundEndpoint);
					}
					
					Consumer<Function<V, S>> outboundHandlerConsumer = new Consumer<Function<V,S>>() {

						@Override
						public void accept(Function<V, S> handler) {
							Map<Connection, Function<V, S>> nodeOutboundHandlers = outboundHandlers.computeIfAbsent(node, n -> new HashMap<>());							
							nodeOutboundHandlers.put(outboundConnection, handler);						
						}
						
					};
					outboundHandlerConsumers.put(outboundConnection, outboundHandlerConsumer);
				} else {
					Function<U, R> outboundEndpoint = createEndpoint(new Function<V, S>() {

						@Override
						public S apply(V t) {
							Function<V, S> sourceHandler = sourceHandlers.get(outboundConnection);
							if (sourceHandler == null) {
								throw new IllegalStateException("Source handler is not set for connection " + outboundConnection);
							}
							return sourceHandler.apply(t);
						}
						
					});
					inboundEndpoints.put(outboundConnection, outboundEndpoint);
					
					Consumer<Function<V, S>> outboundHandlerConsumer = new Consumer<Function<V,S>>() {
						
						@Override
						public void accept(Function<V, S> handler) {
							sourceHandlers.put(outboundConnection, handler);
						}
						
					};
					outboundHandlerConsumers.put(outboundConnection, outboundHandlerConsumer);															
				}
			}
			
			processor = createNodeProcessor(
					(Node) element, 
					childProcessors,
					inboundEndpoints,
					inboundHandlerConsumers,
					outboundEndpoints,
					outboundHandlerConsumers);
		} else if (element instanceof Connection) {
			if (isPassTroughConnection((Connection) element)) {
				processor = null;
			} else {
				Connection connection = (Connection) element;
				Node source = connection.getSource();
				Function<U, R> sourceEndpoint = null;
				if (source != null) {
					sourceEndpoint = createEndpoint(new Function<V, S>() {
	
						@Override
						public S apply(V t) {
							Map<Connection, Function<V, S>> sourceOutboundHandlers = outboundHandlers.get(source);
							if (sourceOutboundHandlers == null) {
								throw new IllegalStateException("Outbound handlers not found for " + source);
							}																					
							Function<V, S> sourceHandler = sourceOutboundHandlers.get(connection);
							if (sourceHandler == null) {
								throw new IllegalStateException("Source handler is not set for connection " + connection);
							}
							return sourceHandler.apply(t);
						}
						
					});
				}
				
				Consumer<Function<V, S>> sourceHandlerConsumer = new Consumer<Function<V,S>>() {

					@Override
					public void accept(Function<V, S> handler) {
						sourceHandlers.put((Connection) element, handler);						
					}
					
				};
				
				Node target = connection.getTarget();
				Function<U, R> targetEndpoint = null;
				if (target != null) {
					targetEndpoint = createEndpoint(new Function<V, S>() {
	
						@Override
						public S apply(V t) {
							Map<Connection, Function<V, S>> targetInboundHandlers = inboundHandlers.get(target);
							if (targetInboundHandlers == null) {
								throw new IllegalStateException("Inbound handlers not found for " + target);
							}																					
							Function<V, S> targetHandler = targetInboundHandlers.get(connection);
							if (targetHandler == null) {
								throw new IllegalStateException("Target handler is not set for connection " + connection);
							}
							return targetHandler.apply(t);
						}
						
					});
				}
				Consumer<Function<V, S>> targetHandlerConsumer = new Consumer<Function<V,S>>() {

					@Override
					public void accept(Function<V, S> handler) {
						targetHandlers.put((Connection) element, handler);						
					}
					
				};
				processor = createConnectionProcessor(
						(Connection) element, 
						childProcessors,
						sourceEndpoint,
						sourceHandlerConsumer,
						targetEndpoint,
						targetHandlerConsumer);
			}
		} else {
			processor = createElementProcessor(element, childProcessors);
		}
		if (childProcessors != null) {
			for (T childProcessor: childProcessors.values()) {
				if (childProcessor instanceof ElementProcessor) {
					((ElementProcessor<T>) childProcessor).setParentProcessor(processor);
				}
			}
		}
		if (processor != null) {
			processors.put(element, processor);
		}
		return processor;
	}
	
	protected T createElementProcessor(Element element, Map<Element, T> childProcessors) {
		return null;
	}

	protected T createConnectionProcessor(
			Connection connection, 
			Map<Element, T> childProcessors,
			Function<U,R> sourceEndpoint,
			Consumer<Function<V,S>> sourceHandlerConsumer,
			Function<U,R> targetEndpoint,
			Consumer<Function<V,S>> targetHandlerConsumer) {
		
		throw new UnsupportedOperationException();
		
	}
	
	@SuppressWarnings("unchecked")
	protected T createNodeProcessor(
			Node node, 
			Map<Element, T> childProcessors,
			Map<Connection, Function<U,R>> inboundEndpoints,
			Map<Connection, Consumer<Function<V,S>>> inboundHandlerConsumers,
			Map<Connection, Function<U,R>> outboundEndpoints,
			Map<Connection, Consumer<Function<V,S>>> outboundHandlerConsumers) {
		
		
		abstract class ExportNodeProcessorImpl implements ExportNodeProcessor<T, U, R, V, S>, ElementProcessor<T> {
			
		}

		return (T) new ExportNodeProcessorImpl() {
			
			private T parentProcessor;
			
			@Override
			public void setParentProcessor(T parentProcessor) {
				this.parentProcessor = parentProcessor;
			}

			@Override
			public Stream<T> select(Predicate<Element> predicate) {
				return ProcessorFactory.this.select(predicate);
			}

			@Override
			public T resolve(Element element) {
				return ProcessorFactory.this.resolve(element);
			}

			@Override
			public Node getNode() {
				return node;
			}

			@Override
			public Map<Element, T> getChildProcessors() {
				return childProcessors;
			}

			@Override
			public Map<Connection, Function<U, R>> getInboundEndpoints() {
				return inboundEndpoints;
			}

			@Override
			public Map<Connection, Consumer<Function<V, S>>> getInboundHandlerConsumers() {
				return inboundHandlerConsumers;
			}

			@Override
			public Map<Connection, Function<U, R>> getOutboundEndpoints() {
				return outboundEndpoints;
			}

			@Override
			public Map<Connection, Consumer<Function<V, S>>> getOutboundHandlerConsumers() {
				return outboundHandlerConsumers;
			}

			@Override
			public T getParentProcessor() {
				return parentProcessor;
			}
		};
	}
	
	/**
	 * Creates an endpoint which invokes the handler. The endpoint may change parameter and return types. E.g. an asynchronous endpoint would returns a {@link Future} or {@link CompletableFuture}.
	 * @param handler
	 * @return
	 */
	protected abstract Function<U,R> createEndpoint(Function<V,S> handler);
	
	/**
	 * @param connection
	 * @return true if the argument connection does not perform any processing. This implementation returns true.
	 */
	protected boolean isPassTroughConnection(Connection connection) {
		return true;
	}

}
