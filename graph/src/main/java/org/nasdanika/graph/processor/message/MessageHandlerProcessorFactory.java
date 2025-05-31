package org.nasdanika.graph.processor.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * A processor factory with {@link MessageHander} handler and endpoint
 */
public abstract class MessageHandlerProcessorFactory<M extends Message> extends ProcessorFactory<MessageHandler<M>> {
		
	/**
	 * Processes messages sent to a node. 
	 * process() processes handler, parent, and child messages.
	 * @param <M>
	 */
	public interface NodeProcessor<M extends Message> extends MessageHandler<M> {
		
		Flux<M> processIncoming(Connection connection, M message, ProgressMonitor progressMonitor);
		
		Flux<M> processOutgoing(Connection connection, M message, ProgressMonitor progressMonitor);
		
	}
	
	/**
	 * Processes messages sent to a connection. 
	 * process() processes handler, parent, and child messages.
	 * @param <M>
	 */
	public interface ConnectionProcessor<M extends Message> extends MessageHandler<M> {
		
		Flux<M> targetProcess(M message, MessageHandler<M> sourceEndpoint, ProgressMonitor progressMonitor);
		
		Flux<M> sourceProcess(M message, MessageHandler<M> targetEndpoint, ProgressMonitor progressMonitor);
		
	}	
	
	@Override
	protected ProcessorInfo<MessageHandler<M>> createProcessor(
			ProcessorConfig config, 
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<MessageHandler<M>>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {
		
		
		if (config instanceof NodeProcessorConfig) {
			@SuppressWarnings("unchecked")
			NodeProcessorConfig<MessageHandler<M>, MessageHandler<M>> nodeProcessorConfig = (NodeProcessorConfig<MessageHandler<M>, MessageHandler<M>>) config;

			Map<Connection, MessageHandler<M>> incomingEndpoints = new ConcurrentHashMap<>();
			for (Entry<Connection, CompletionStage<MessageHandler<M>>> incomingEndpointEntry: nodeProcessorConfig.getIncomingEndpoints().entrySet()) {
				endpointWiringStageConsumer.accept(incomingEndpointEntry.getValue().thenAccept(endpoint -> incomingEndpoints.put(incomingEndpointEntry.getKey(), endpoint)));
			}
						
			Map<Connection, MessageHandler<M>> outgoingEndpoints = new ConcurrentHashMap<>();
			for (Entry<Connection, CompletionStage<MessageHandler<M>>> outgoingEndpointEntry: nodeProcessorConfig.getOutgoingEndpoints().entrySet()) {
				endpointWiringStageConsumer.accept(outgoingEndpointEntry.getValue().thenAccept(endpoint -> outgoingEndpoints.put(outgoingEndpointEntry.getKey(), endpoint)));				
			}
						
			NodeProcessor<M> nodeProcessor = createNodeProcessor(nodeProcessorConfig, parallel, infoProvider, endpointWiringStageConsumer, incomingEndpoints, outgoingEndpoints, progressMonitor);			
			
			for (Entry<Connection, Consumer<MessageHandler<M>>> incomingHandlerConsumerEntry: nodeProcessorConfig.getIncomingHandlerConsumers().entrySet()) {
				incomingHandlerConsumerEntry.getValue().accept((t,pm) -> t.test(nodeProcessorConfig.getElement()) || pm.isCancelled() ? Flux.empty() : nodeProcessor.processIncoming(incomingHandlerConsumerEntry.getKey(), t, pm));
			}
			
			for (Entry<Connection, Consumer<MessageHandler<M>>> outgoingHandlerConsumerEntry: nodeProcessorConfig.getOutgoingHandlerConsumers().entrySet()) {
				outgoingHandlerConsumerEntry.getValue().accept((t,pm) -> t.test(nodeProcessorConfig.getElement()) || pm.isCancelled() ? Flux.empty() : nodeProcessor.processOutgoing(outgoingHandlerConsumerEntry.getKey(), t, pm));
			}
			
			return ProcessorInfo.of(nodeProcessorConfig, nodeProcessor);						
		} 
		
		if (config instanceof ConnectionProcessorConfig) {
			@SuppressWarnings("unchecked")
			ConnectionProcessorConfig<MessageHandler<M>, MessageHandler<M>> connectionProcessorConfig = (ConnectionProcessorConfig<MessageHandler<M>, MessageHandler<M>>) config;			
			ConnectionProcessor<M> connectionProcessor = createConnectionProcessor(connectionProcessorConfig, parallel, infoProvider, endpointWiringStageConsumer, progressMonitor);
			
			endpointWiringStageConsumer.accept(connectionProcessorConfig
				.getSourceEndpoint()
				.thenAccept(endpoint -> {										
					connectionProcessorConfig.setTargetHandler((t,pm) -> t.test(connectionProcessorConfig.getElement()) || pm.isCancelled() ? Flux.empty() : connectionProcessor.targetProcess(t, endpoint, pm));
				}));
			
			endpointWiringStageConsumer.accept(connectionProcessorConfig
				.getTargetEndpoint()
				.thenAccept(endpoint -> {										
					connectionProcessorConfig.setSourceHandler((t,pm) -> t.test(connectionProcessorConfig.getElement()) || pm.isCancelled() ? Flux.empty() : connectionProcessor.sourceProcess(t, endpoint, pm));
				}));
			
			return ProcessorInfo.of(connectionProcessorConfig, connectionProcessor);			
		}
		
		return super.createProcessor(config, parallel, infoProvider, endpointWiringStageConsumer, progressMonitor);
	}
	
	protected ConnectionProcessor<M> createConnectionProcessor(
			ConnectionProcessorConfig<MessageHandler<M>, MessageHandler<M>> connectionProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<MessageHandler<M>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 			
			ProgressMonitor progressMonitor) {
		
		return new ConnectionProcessor<M>() {

			@Override
			public Flux<M> process(M message, ProgressMonitor progressMonitor) {
				// Connections only pass messages between source and target.
				// Parent/child and handler messaging is not supported.
				return Flux.empty();
			}

			@Override
			public Flux<M> targetProcess(M message, MessageHandler<M> sourceEndpoint, ProgressMonitor progressMonitor) {				
				if (message.isPruned()) {
					return Flux.empty();
				}
				
				Mono<M> sourceMessageMono = Mono.fromSupplier(
						() -> createMessage(
							Message.Type.OUTGOING, 
							connectionProcessorConfig.getElement(), 
							connectionProcessorConfig.getElement().getSource(),
							message, 
							progressMonitor));
				
				return sourceMessageMono.flatMapMany(sourceMessage -> {
					Flux<M> sourceFlux = sourceEndpoint.process(sourceMessage, progressMonitor);
					return Flux.just(sourceMessage).concatWith(sourceFlux);					
				});
			}

			@Override
			public Flux<M> sourceProcess(M message, MessageHandler<M> targetEndpoint, ProgressMonitor progressMonitor) {
				if (message.isPruned()) {
					return Flux.empty();
				}
				
				Mono<M> targetMessageMono = Mono.fromSupplier(
						() -> createMessage(
							Message.Type.INCOMING, 
							connectionProcessorConfig.getElement(), 
							connectionProcessorConfig.getElement().getTarget(), 							
							message, 
							progressMonitor));
				
				return targetMessageMono.flatMapMany(targetMessage -> {
					Flux<M> targetFlux = targetEndpoint.process(targetMessage, progressMonitor);
					return Flux.just(targetMessage).concatWith(targetFlux);					
				});
			}
			
		};		
		
	}

	protected NodeProcessor<M> createNodeProcessor(
			NodeProcessorConfig<MessageHandler<M>, MessageHandler<M>> nodeProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<MessageHandler<M>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 			 
			Map<Connection, MessageHandler<M>> incomingEndpoints,
			Map<Connection, MessageHandler<M>> outgoingEndpoints,			 
			ProgressMonitor progressMonitor) {
		
		return new NodeProcessor<M>() {

			@Override
			public Flux<M> process(M message, ProgressMonitor progressMonitor) {
				if (message.isPruned()) {
					return Flux.empty();
				}
								
				return sendMessages(null, false, message, progressMonitor);
			}

			@Override
			public Flux<M> processIncoming(Connection connection, M message, ProgressMonitor progressMonitor) {
				return sendMessages(connection, false, message, progressMonitor);
			}

			@Override
			public Flux<M> processOutgoing(Connection connection, M message, ProgressMonitor progressMonitor) {
				return sendMessages(connection, true, message, progressMonitor);
			}
			
			private Flux<M> sendMessages(
					Connection source,
					boolean isSourceOutgoing,
					M message, 
					ProgressMonitor progressMonitor) {
								
				Node node = nodeProcessorConfig.getElement(); 
				Collection<Flux<M>> results = new ArrayList<>();

				if (!message.isPruned()) {
					// Parent
					Element parent = nodeProcessorConfig.getParentProcessorConfig().getElement();
					if (parent != null) {
						infoProvider.accept(parent, (pi, pm) -> {
							MessageHandler<M> parentProcessor = pi.getProcessor();
							if (parentProcessor != null) {
								Mono<M> parentMessageMono = Mono.fromSupplier(
										() -> createMessage(
											Message.Type.CHILD, 
											node, 
											parent,
											message, 
											progressMonitor));
								
								results.add(parentMessageMono.flatMapMany(parentMessage -> {
									Flux<M> parentFlux = parentProcessor.process(parentMessage, progressMonitor);
									return Flux.just(parentMessage).concatWith(parentFlux);					
								}));
							}
						});
					}					
					
					// Children
					for (Element child: node.getChildren()) {
						infoProvider.accept(child, (pi, pm) -> {
							MessageHandler<M> childProcessor = pi.getProcessor();
							if (childProcessor != null) {
								Mono<M> childMessageMono = Mono.fromSupplier(
										() -> createMessage(
											Message.Type.PARENT, 
											node, 
											child,
											message, 
											progressMonitor));
								
								results.add(childMessageMono.flatMapMany(childMessage -> {
									Flux<M> childFlux = childProcessor.process(childMessage, progressMonitor);
									return Flux.just(childMessage).concatWith(childFlux);					
								}));
							}
						});					
					}
					
					// Incoming
					for (Entry<Connection, MessageHandler<M>> ie: incomingEndpoints.entrySet()) {
						MessageHandler<M> handler = ie.getValue();
						if (handler != null) {
							Mono<M> incomingMessageMono = Mono.fromSupplier(
									() -> createMessage(
										Message.Type.TARGET, 
										node,
										ie.getKey(),
										message, 
										progressMonitor));
							
							results.add(incomingMessageMono.flatMapMany(incomingMessage -> {
								Flux<M> incomingFlux = handler.process(incomingMessage, progressMonitor);
								return Flux.just(incomingMessage).concatWith(incomingFlux);					
							}));
						}
					}
					
					// Outgoing				
					for (Entry<Connection, MessageHandler<M>> oe: outgoingEndpoints.entrySet()) {
						MessageHandler<M> handler = oe.getValue();
						if (handler != null) {
							Mono<M> outgoingMessageMono = Mono.fromSupplier(
									() -> createMessage(
										node, 
										Message.Type.SOURCE, 
										message, 
										progressMonitor));
							
							results.add(incomingMessageMono.flatMapMany(incomingMessage -> {
								Flux<M> incomingFlux = handler.process(incomingMessage, progressMonitor);
								return Flux.just(incomingMessage).concatWith(incomingFlux);					
							}));
						}
					}
				}
				
				return Flux.merge(results);
			}			
			
		};
		
	}
	
	protected abstract M createMessage(
			Message.Type type,
			Element sender,
			Element recipient,
			M parent, 
			ProgressMonitor progressMonitor);	
	
}
