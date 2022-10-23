package org.nasdanika.graph.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
	
	// Handlers to call connection sources by outgoing endpoints
	private Map<Connection,H> sourceHandlers = new HashMap<>();
	
	// Handlers to call connection targets by incoming endpoints
	private Map<Connection,H> targetHandlers = new HashMap<>();
	
	// Handlers to call nodes by target endpoints or by outgoing endpoints for pass-through connections.
	private Map<Node, Map<Connection,H>> incomingHandlers = new HashMap<>();
	
	// Handlers to call nodes by source endpoints or by incoming endpoints for pass-through connections.
	private Map<Node, Map<Connection,H>> outgoingHandlers = new HashMap<>();	

	ProcessorFactoryVisitor(ProcessorFactory<P,H,E> factory) {
		this.factory = factory;
	}
		
	Map<Element, ProcessorInfo<P>> getRegistry() {
		return registry;
	}	

	public Helper<P> createElementProcessor(Element element, Map<? extends Element, Helper<P>> childProcessors) {
		Collection<Consumer<ProcessorInfo<P>>> parentProcessorInfoConsumers = new ArrayList<>();
		Collection<Consumer<Map<Element, ProcessorInfo<P>>>> registryConsumers = new ArrayList<>();
		ProcessorConfig<P> config;
		if (element instanceof Node) {
			Node node = (Node) element;
			Map<Connection, E> incomingEndpoints = new HashMap<>();
			Map<Connection, Consumer<H>> incomingHandlerConsumers = new HashMap<>();
			for (Connection incomingConnection: node.getIncomingConnections()) {
				Supplier<H> incomingHandlerSupplier = () -> {
					if (factory.isPassThrough(incomingConnection)) {
						// Returning outgoing handler directly as there is no target handler
						Node incomingConnectionSource = incomingConnection.getSource();
						if (incomingConnectionSource == null) {
							throw new IllegalStateException("Incoming connection has no source node: " + incomingConnection);
						}
						Map<Connection,H> sourceOutgoingHandlers = outgoingHandlers.get(incomingConnectionSource);
						if (sourceOutgoingHandlers == null) {
							throw new IllegalStateException("Outgoing handlers not found for " + incomingConnectionSource);
						}
						H sourceOutgoingHandler = sourceOutgoingHandlers.get(incomingConnection);
						if (sourceOutgoingHandler == null) {
							throw new IllegalStateException("Source outgoing hanlder not found for incoming connection " + incomingConnection);
						}
						return sourceOutgoingHandler;
					}
					
					H targetHandler = targetHandlers.get(incomingConnection);
					if (targetHandler == null) {
						throw new IllegalStateException("Target handler is not set for connection " + incomingConnection);
					}
					return targetHandler;
					
				};
				H incomingEndpointHandlerProxy = factory.createHandlerProxy(incomingConnection, incomingHandlerSupplier, HandlerType.INCOMING); 
				E incomingEndpoint = incomingEndpointHandlerProxy == null ? null : factory.createEndpoint(incomingConnection, incomingEndpointHandlerProxy, HandlerType.INCOMING);
				if (incomingEndpoint != null) {
					incomingEndpoints.put(incomingConnection, incomingEndpoint);
				}
				
				Consumer<H> incomingHandlerConsumer = incomingHandler -> incomingHandlers.computeIfAbsent(node, n -> new HashMap<>()).put(incomingConnection, incomingHandler);
				incomingHandlerConsumers.put(incomingConnection, incomingHandlerConsumer);										
			}
			
			Map<Connection, E> outgoingEndpoints = new HashMap<>();
			Map<Connection, Consumer<H>> outgoingHandlerConsumers = new HashMap<>();
			for (Connection outgoingConnection: node.getOutgoingConnections()) {
				Supplier<H> outgoingHandlerSupplier = () -> {
					if (factory.isPassThrough(outgoingConnection)) {
						// Calling incoming handler directly as there is no source handler
						Node outgoingConnectionTarget = outgoingConnection.getTarget();
						if (outgoingConnectionTarget == null) {
							throw new IllegalStateException("Outgoing connection has no target node: " + outgoingConnection);
						}
						Map<Connection, H> targetIncomingHandlers = incomingHandlers.get(outgoingConnectionTarget);
						if (targetIncomingHandlers == null) {
							throw new IllegalStateException("Incoming handlers not found for " + outgoingConnectionTarget);
						}
						H targetIncomingHandler = targetIncomingHandlers.get(outgoingConnection);
						if (targetIncomingHandler == null) {
							throw new IllegalStateException("Target incoming hanlder not found for outgoing connection " + outgoingConnection);
						}
						return targetIncomingHandler;
					}
					
					H sourceHandler = sourceHandlers.get(outgoingConnection);
					if (sourceHandler == null) {
						throw new IllegalStateException("Source handler is not set for connection " + outgoingConnection);
					}
					return sourceHandler;
					
				};
				H outgoingEndpointHandlerProxy = factory.createHandlerProxy(outgoingConnection, outgoingHandlerSupplier, HandlerType.OUTGOING);
				E outgoingEndpoint = outgoingEndpointHandlerProxy == null ? null : factory.createEndpoint(outgoingConnection, outgoingEndpointHandlerProxy, HandlerType.OUTGOING);
				if (outgoingEndpoint != null) {
					outgoingEndpoints.put(outgoingConnection, outgoingEndpoint);
				}
				
				Consumer<H> outgoingHandlerConsumer = outgoingHandler -> outgoingHandlers.computeIfAbsent(node, n -> new HashMap<>()).put(outgoingConnection, outgoingHandler);
				outgoingHandlerConsumers.put(outgoingConnection, outgoingHandlerConsumer);										
			}
			
			config = new NodeProcessorConfig<P, H, E>() {
				
				private ProcessorInfo<P> parentProcessorInfo;
				
				{
					parentProcessorInfoConsumers.add(ppi -> this.parentProcessorInfo = ppi);
				}

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
				public ProcessorInfo<P> getParentProcessorInfo() {
					return parentProcessorInfo;
				}

				@Override
				public Map<Element, ProcessorInfo<P>> getRegistry() {
					return Collections.unmodifiableMap(registry);
				}

				@Override
				public Map<Connection, E> getIncomingEndpoints() {
					return Collections.unmodifiableMap(incomingEndpoints);
				}

				@Override
				public Map<Connection, Consumer<H>> getIncomingHandlerConsumers() {
					return Collections.unmodifiableMap(incomingHandlerConsumers);
				}

				@Override
				public Map<Connection, E> getOutgoingEndpoints() {
					return Collections.unmodifiableMap(outgoingEndpoints);
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
				E sourceEndpoint;
				if (source == null) {
					sourceEndpoint = null;
				} else {
					Supplier<H> sourceHandlerSupplier = () -> {
						Map<Connection, H> sourceOutgoingHandlers = outgoingHandlers.get(source);
						if (sourceOutgoingHandlers == null) {
							throw new IllegalStateException("Outgoing handlers not found for " + source);
						}																					
						H sourceHandler = sourceOutgoingHandlers.get(connection);
						if (sourceHandler == null) {
							throw new IllegalStateException("Source handler is not set for connection " + connection);
						}
						return sourceHandler;
						
					};
					H sourceHandlerProxy = factory.createHandlerProxy(connection, sourceHandlerSupplier, HandlerType.SOURCE);
					sourceEndpoint = sourceHandlerProxy == null ? null : factory.createEndpoint(connection, sourceHandlerProxy, HandlerType.SOURCE);
				}
				
				Consumer<H> sourceHandlerConsumer = sourceHandler -> sourceHandlers.put((Connection) element, sourceHandler);						
				
				Node target = connection.getTarget();
				E targetEndpoint;
				if (target == null) {
					targetEndpoint = null;
				} else {
					Supplier<H> targetHandlerSupplier = () -> {
						Map<Connection, H> targetIncomingHandlers = incomingHandlers.get(target);
						if (targetIncomingHandlers == null) {
							throw new IllegalStateException("Incoming handlers not found for " + target);
						}																					
						H targetHandler = targetIncomingHandlers.get(connection);
						if (targetHandler == null) {
							throw new IllegalStateException("Target handler is not set for connection " + connection);
						}
						return targetHandler;						
					};
					H targetHandlerProxy = factory.createHandlerProxy(connection, targetHandlerSupplier, HandlerType.TARGET);
					targetEndpoint = targetHandlerProxy == null ? null : factory.createEndpoint(connection, targetHandlerProxy, HandlerType.TARGET);
				}
				Consumer<H> targetHandlerConsumer = targetHandler -> targetHandlers.put((Connection) element, targetHandler);						
				
				config = new ConnectionProcessorConfig<P, H, E>() {
					
					private ProcessorInfo<P> parentProcessorInfo;
					
					{
						parentProcessorInfoConsumers.add(ppi -> this.parentProcessorInfo = ppi);
					}

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
					public ProcessorInfo<P> getParentProcessorInfo() {
						return parentProcessorInfo;
					}

					@Override
					public Map<Element, ProcessorInfo<P>> getRegistry() {
						return Collections.unmodifiableMap(registry);
					}

					@Override
					public E getSourceEndpoint() {
						return sourceEndpoint;
					}

					@Override
					public void setSourceHandler(H sourceHandler) {
						sourceHandlerConsumer.accept(sourceHandler);						
					}

					@Override
					public E getTargetEndpoint() {
						return targetEndpoint;
					}

					@Override
					public void setTargetHandler(H targetHandler) {
						targetHandlerConsumer.accept(targetHandler);						
					}
				};
			}
		} else {
			config = new ProcessorConfig<P>() {
				
				private ProcessorInfo<P> parentProcessorInfo;
				
				{
					parentProcessorInfoConsumers.add(ppi -> this.parentProcessorInfo = ppi);
				}

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
				public ProcessorInfo<P> getParentProcessorInfo() {
					return parentProcessorInfo;
				}

				@Override
				public Map<Element, ProcessorInfo<P>> getRegistry() {
					return Collections.unmodifiableMap(registry);
				}
				
			};
		}
		
		ProcessorInfo<P> processorInfo = config == null ? null : factory.createProcessor(config, parentProcessorInfoConsumers::add, registryConsumers::add);
		
		if (childProcessors != null) {
			childProcessors.values().forEach(ch -> ch.setParentProcessorInfo(processorInfo));
		}
		if (processorInfo != null) {
			registry.put(element, processorInfo);
		}
		return new Helper<>(processorInfo) {

			@Override
			void setParentProcessorInfo(ProcessorInfo<P> parentProcessorInfo) {
				parentProcessorInfoConsumers.forEach(ppic -> ppic.accept(parentProcessorInfo));				
			}

			@Override
			void setRegistry(Map<Element, ProcessorInfo<P>> registry) {
				childProcessors.values().forEach(ch -> ch.setRegistry(registry));
				registryConsumers.forEach(rc -> rc.accept(registry));
			}
			
		};
	}

}
