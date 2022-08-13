package org.nasdanika.drawio.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Node;

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
	private Map<Element, ElementProcessorInfo<P>> registry = new LinkedHashMap<>();
	
	// Handlers to call connection sources by outbound endpoints
	private Map<Connection,H> sourceHandlers = new HashMap<>();
	
	// Handlers to call connection targets by inbound endpoints
	private Map<Connection,H> targetHandlers = new HashMap<>();
	
	// Handlers to call nodes by target endpoints or by outbound endpoints for pass-through connections.
	private Map<Node, Map<Connection,H>> inboundHandlers = new HashMap<>();
	
	// Handlers to call nodes by source endpoints or by inbound endpoints for pass-through connections.
	private Map<Node, Map<Connection,H>> outboundHandlers = new HashMap<>();	

	ProcessorFactoryVisitor(ProcessorFactory<P,H,E> factory) {
		this.factory = factory;
	}
		
	Map<Element, ElementProcessorInfo<P>> getRegistry() {
		return registry;
	}	


	public Helper<P> createElementProcessor(Element element, Map<Element, Helper<P>> childProcessors) {
		Collection<Consumer<ElementProcessorInfo<P>>> parentProcessorInfoConsumers = new ArrayList<>();
		ElementProcessorConfig<P> config;
		if (element instanceof Node) {
			Node node = (Node) element;
			Map<Connection, E> inboundEndpoints = new HashMap<>();
			Map<Connection, Consumer<H>> inboundHandlerConsumers = new HashMap<>();
			for (Connection inboundConnection: node.getInboundConnections()) {
				Supplier<H> inboundHandlerSupplier = () -> {
					if (factory.isPassThrough(inboundConnection)) {
						// Returning outbound handler directly as there is no target handler
						Node inboundConnectionSource = inboundConnection.getSource();
						if (inboundConnectionSource == null) {
							throw new IllegalStateException("Inbound connection has no source node: " + inboundConnection);
						}
						Map<Connection,H> sourceOutboundHandlers = outboundHandlers.get(inboundConnectionSource);
						if (sourceOutboundHandlers == null) {
							throw new IllegalStateException("Outbound handlers not found for " + inboundConnectionSource);
						}
						H sourceOutboundHandler = sourceOutboundHandlers.get(inboundConnection);
						if (sourceOutboundHandler == null) {
							throw new IllegalStateException("Source outbound hanlder not found for inbound connection " + inboundConnection);
						}
						return sourceOutboundHandler;
					}
					
					H targetHandler = targetHandlers.get(inboundConnection);
					if (targetHandler == null) {
						throw new IllegalStateException("Target handler is not set for connection " + inboundConnection);
					}
					return targetHandler;
					
				};
				H inboundEndpointHandlerProxy = factory.createHandlerProxy(inboundConnection, inboundHandlerSupplier, HandlerType.INBOUND); 
				E inboundEndpoint = factory.createEndpoint(inboundConnection, inboundEndpointHandlerProxy, HandlerType.INBOUND);
				inboundEndpoints.put(inboundConnection, inboundEndpoint);
				
				Consumer<H> inboundHandlerConsumer = inboundHandler -> inboundHandlers.computeIfAbsent(node, n -> new HashMap<>()).put(inboundConnection, inboundHandler);
				inboundHandlerConsumers.put(inboundConnection, inboundHandlerConsumer);										
			}
			
			Map<Connection, E> outboundEndpoints = new HashMap<>();
			Map<Connection, Consumer<H>> outboundHandlerConsumers = new HashMap<>();
			for (Connection outboundConnection: node.getOutboundConnections()) {
				Supplier<H> outboundHandlerSupplier = () -> {
					if (factory.isPassThrough(outboundConnection)) {
						// Calling inbound handler directly as there is no source handler
						Node outboundConnectionTarget = outboundConnection.getTarget();
						if (outboundConnectionTarget == null) {
							throw new IllegalStateException("Outbound connection has no target node: " + outboundConnection);
						}
						Map<Connection, H> targetInboundHandlers = inboundHandlers.get(outboundConnectionTarget);
						if (targetInboundHandlers == null) {
							throw new IllegalStateException("Inbound handlers not found for " + outboundConnectionTarget);
						}
						H targetInboundHandler = targetInboundHandlers.get(outboundConnection);
						if (targetInboundHandler == null) {
							throw new IllegalStateException("Target inbound hanlder not found for outbound connection " + outboundConnection);
						}
						return targetInboundHandler;
					}
					
					H sourceHandler = sourceHandlers.get(outboundConnection);
					if (sourceHandler == null) {
						throw new IllegalStateException("Source handler is not set for connection " + outboundConnection);
					}
					return sourceHandler;
					
				};
				H outboundEndpointHandlerProxy = factory.createHandlerProxy(outboundConnection, outboundHandlerSupplier, HandlerType.OUTBOUND);
				E outboundEndpoint = factory.createEndpoint(outboundConnection, outboundEndpointHandlerProxy, HandlerType.OUTBOUND);
				outboundEndpoints.put(outboundConnection, outboundEndpoint);
				
				Consumer<H> outboundHandlerConsumer = outboundHandler -> outboundHandlers.computeIfAbsent(node, n -> new HashMap<>()).put(outboundConnection, outboundHandler);
				outboundHandlerConsumers.put(outboundConnection, outboundHandlerConsumer);										
			}
			
			config = new NodeProcessorConfig<P, H, E>() {
				
				private ElementProcessorInfo<P> parentProcessorInfo;
				
				{
					parentProcessorInfoConsumers.add(ppi -> this.parentProcessorInfo = ppi);
				}

				@Override
				public Node getElement() {
					return node;
				}

				@Override
				public Map<Element, ElementProcessorInfo<P>> getChildProcessorsInfo() {
					Map<Element,ElementProcessorInfo<P>> ret = new LinkedHashMap<>();
					childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessorInfo()));
					return ret;
				}

				@Override
				public ElementProcessorInfo<P> getParentProcessorInfo() {
					return parentProcessorInfo;
				}

				@Override
				public Map<Element, ElementProcessorInfo<P>> getRegistry() {
					return Collections.unmodifiableMap(registry);
				}

				@Override
				public Map<Connection, E> getInboundEndpoints() {
					return Collections.unmodifiableMap(inboundEndpoints);
				}

				@Override
				public Map<Connection, Consumer<H>> getInboundHandlerConsumers() {
					return Collections.unmodifiableMap(inboundHandlerConsumers);
				}

				@Override
				public Map<Connection, E> getOutboundEndpoints() {
					return Collections.unmodifiableMap(outboundEndpoints);
				}

				@Override
				public Map<Connection, Consumer<H>> getOutboundHandlerConsumers() {
					return Collections.unmodifiableMap(outboundHandlerConsumers);
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
						Map<Connection, H> sourceOutboundHandlers = outboundHandlers.get(source);
						if (sourceOutboundHandlers == null) {
							throw new IllegalStateException("Outbound handlers not found for " + source);
						}																					
						H sourceHandler = sourceOutboundHandlers.get(connection);
						if (sourceHandler == null) {
							throw new IllegalStateException("Source handler is not set for connection " + connection);
						}
						return sourceHandler;
						
					};
					H sourceHandlerProxy = factory.createHandlerProxy(connection, sourceHandlerSupplier, HandlerType.SOURCE);
					sourceEndpoint = factory.createEndpoint(connection, sourceHandlerProxy, HandlerType.SOURCE);
				}
				
				Consumer<H> sourceHandlerConsumer = sourceHandler -> sourceHandlers.put((Connection) element, sourceHandler);						
				
				Node target = connection.getTarget();
				E targetEndpoint;
				if (target == null) {
					targetEndpoint = null;
				} else {
					Supplier<H> targetHandlerSupplier = () -> {
						Map<Connection, H> targetInboundHandlers = inboundHandlers.get(target);
						if (targetInboundHandlers == null) {
							throw new IllegalStateException("Inbound handlers not found for " + target);
						}																					
						H targetHandler = targetInboundHandlers.get(connection);
						if (targetHandler == null) {
							throw new IllegalStateException("Target handler is not set for connection " + connection);
						}
						return targetHandler;						
					};
					H targetHandlerProxy = factory.createHandlerProxy(connection, targetHandlerSupplier, HandlerType.TARGET);
					targetEndpoint = factory.createEndpoint(connection, targetHandlerProxy, HandlerType.TARGET);
				}
				Consumer<H> targetHandlerConsumer = targetHandler -> targetHandlers.put((Connection) element, targetHandler);						
				
				config = new ConnectionProcessorConfig<P, H, E>() {
					
					private ElementProcessorInfo<P> parentProcessorInfo;
					
					{
						parentProcessorInfoConsumers.add(ppi -> this.parentProcessorInfo = ppi);
					}

					@Override
					public Connection getElement() {
						return connection;
					}

					@Override
					public Map<Element, ElementProcessorInfo<P>> getChildProcessorsInfo() {
						Map<Element,ElementProcessorInfo<P>> ret = new LinkedHashMap<>();
						childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessorInfo()));
						return ret;
					}

					@Override
					public ElementProcessorInfo<P> getParentProcessorInfo() {
						return parentProcessorInfo;
					}

					@Override
					public Map<Element, ElementProcessorInfo<P>> getRegistry() {
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
			config = new ElementProcessorConfig<P>() {
				
				private ElementProcessorInfo<P> parentProcessorInfo;
				
				{
					parentProcessorInfoConsumers.add(ppi -> this.parentProcessorInfo = ppi);
				}

				@Override
				public Element getElement() {
					return element;
				}

				@Override
				public Map<Element, ElementProcessorInfo<P>> getChildProcessorsInfo() {
					Map<Element,ElementProcessorInfo<P>> ret = new LinkedHashMap<>();
					childProcessors.entrySet().forEach(e -> ret.put(e.getKey(), e.getValue().getProcessorInfo()));
					return ret;
				}

				@Override
				public ElementProcessorInfo<P> getParentProcessorInfo() {
					return parentProcessorInfo;
				}

				@Override
				public Map<Element, ElementProcessorInfo<P>> getRegistry() {
					return Collections.unmodifiableMap(registry);
				}
				
			};
		}
		
		ElementProcessorInfo<P> processorInfo = config == null ? null : factory.createProcessor(config, parentProcessorInfoConsumers::add);
		
		if (childProcessors != null) {
			for (Helper<P> childHelper: childProcessors.values()) {
				childHelper.setParentProcessorInfo(processorInfo);
			}
		}
		if (processorInfo != null) {
			registry.put(element, processorInfo);
		}
		return new Helper<>(processorInfo) {

			@Override
			void setParentProcessorInfo(ElementProcessorInfo<P> parentProcessorInfo) {
				parentProcessorInfoConsumers.forEach(ppic -> ppic.accept(parentProcessorInfo));				
			}
			
		};
	}

}
