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

/**
 * A processor factory with {@link MessageHander} handler and endpoint
 */
public abstract class MessageHandlerProcessorFactory<M extends Message> extends ProcessorFactory<MessageHandler<M>> {
		
	public interface NodeProcessor<M extends Message> extends MessageHandler<M> {
		
		Flux<M> processIncoming(Connection connection, M message, ProgressMonitor progressMonitor);
		
		Flux<M> processOutgoing(Connection connection, M message, ProgressMonitor progressMonitor);
		
	}
	
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
				if (message.isPruned()) {
					return Flux.empty();
				}
				
				throw new UnsupportedOperationException("Sending messages to connections is not yet supported");
				
			}

			@Override
			public Flux<M> targetProcess(M message, MessageHandler<M> sourceEndpoint, ProgressMonitor progressMonitor) {
				M sourceMessage = createTargetToSourceMessage(connectionProcessorConfig.getElement(), message, progressMonitor);
				if (sourceMessage == null) {
					return Flux.empty();
				}
				Flux<M> sourceFlux = sourceEndpoint.process(sourceMessage, progressMonitor);
				return Flux.just(sourceMessage).concatWith(sourceFlux);
			}

			@Override
			public Flux<M> sourceProcess(M message, MessageHandler<M> targetEndpoint, ProgressMonitor progressMonitor) {
				M targetMessage = createSourceToTargetMessage(connectionProcessorConfig.getElement(), message, progressMonitor);
				if (targetMessage == null) {
					return Flux.empty();
				}
				Flux<M> targetFlux = targetEndpoint.process(targetMessage, progressMonitor);
				return Flux.just(targetMessage).concatWith(targetFlux);
			}
			
		};		
		
	}
	
	protected abstract M createSourceToTargetMessage(
			Connection connection,
			M message, 
			ProgressMonitor progressMonitor);	
	
	protected abstract M createTargetToSourceMessage(
			Connection connection,
			M message, 
			ProgressMonitor progressMonitor);	

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
												
				// Children
				for (Element child: node.getChildren()) {
					infoProvider.accept(child, (pi, pm) -> {
						MessageHandler<M> childProcessor = pi.getProcessor();
						if (childProcessor != null) {
							M childMessage = createChildMessage(node, null, false, child, message, progressMonitor);
							if (childMessage != null) {
								results.add(Flux.just(childMessage).concatWith(childProcessor.process(childMessage, progressMonitor)));
							}							
						}
					});					
				}
				
				// Incoming
				for (Entry<Connection, MessageHandler<M>> ie: incomingEndpoints.entrySet()) {
					MessageHandler<M> handler = ie.getValue();
					if (handler != null) {
						M connectionMessage = createConnectionMessage(
								node, 
								source, 
								isSourceOutgoing, 
								ie.getKey(), 
								false, 
								message, 
								progressMonitor);
						
						if (connectionMessage != null) {
							results.add(Flux.just(connectionMessage).concatWith(handler.process(connectionMessage, progressMonitor)));
						}						
					}
				}
				
				// Outgoing				
				for (Entry<Connection, MessageHandler<M>> oe: outgoingEndpoints.entrySet()) {
					MessageHandler<M> handler = oe.getValue();
					if (handler != null) {
						M connectionMessage = createConnectionMessage(
								node, 
								source, 
								isSourceOutgoing, 
								oe.getKey(), 
								true, 
								message, 
								progressMonitor);
						
						if (connectionMessage != null) {
							results.add(Flux.just(connectionMessage).concatWith(handler.process(connectionMessage, progressMonitor)));
						}						
					}
				}
				
				return Flux.merge(results);
			}			
			
		};
		
	}

	/**
	 * Creates a message to send to a connection (incoming or outgoing) 
	 * @param source Connection from which the argument message came from. Can be null if activated by the client code through the processor or by the parent.
	 * @param isSourceOutgoing true if the source connection is outgoing, so the message arrived from its start
	 * @param target Connection to which to send the returned message if it is not null
	 * @param isTargetOutgoing true if the target is outgoing, so the message will be send to its start
	 * @param message Source message
	 * @param progressMonitor
	 * @return
	 */
	protected abstract M createConnectionMessage(
			Node node,
			Connection source,
			boolean isSourceOutgoing,
			Connection target,
			boolean isTargetOutgoing,
			M message, 
			ProgressMonitor progressMonitor);
	
	/**
	 * Creates a message to send to a child
	 * @param source Connection from which the argument message came from. Can be null if activated by the client code through the processor or by the parent.
	 * @param isSourceOutgoing true if the source connection is outgoing, so the message arrived from its start
	 * @param child Child to send the returned message to if it is not null
	 * @param message Source message
	 * @param progressMonitor
	 * @return
	 */
	protected abstract M createChildMessage(
			Node node,
			Connection source,
			boolean isSourceOutgoing,
			Element child,
			M message, 
			ProgressMonitor progressMonitor);		
	
}
