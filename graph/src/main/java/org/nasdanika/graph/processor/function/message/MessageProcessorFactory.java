package org.nasdanika.graph.processor.function.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory;

/**
 * A processor factory for passing messages between nodes via connections.
 */
public abstract class MessageProcessorFactory<M extends Message> extends BiFunctionProcessorFactory<M, Collection<M>, M, CompletionStage<Collection<M>>> {
		
	protected abstract M createMessage(
			Message.Type type,
			Element sender,
			Element recipient,
			M parent, 
			CompletionStage<Collection<M>> children,
			ProgressMonitor progressMonitor);

	@Override
	protected ConnectionProcessor<M, Collection<M>, M, CompletionStage<Collection<M>>> createConnectionProcessor(
			ConnectionProcessorConfig<BiFunction<M, ProgressMonitor, Collection<M>>, BiFunction<M, ProgressMonitor, CompletionStage<Collection<M>>>> connectionProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<M, ProgressMonitor, Collection<M>>>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, ProgressMonitor progressMonitor) {
		
		return new ConnectionProcessor<M, Collection<M>, M, CompletionStage<Collection<M>>>() {

			@Override
			public Collection<M> apply(M t, ProgressMonitor u) {
				// Connections only pass messages beween source and target
				return Collections.emptyList();
			}

			@Override
			public Collection<M> targetApply(
					M input,
					BiFunction<M, ProgressMonitor, CompletionStage<Collection<M>>> sourceEndpoint,
					ProgressMonitor progressMonitor) {
				
				CompletableFuture<Collection<M>> children = new CompletableFuture<>();
				
				// Sending a message to the source node
				M sourceMessage = createMessage(
					Message.Type.OUTGOING, 
					connectionProcessorConfig.getElement(), 
					connectionProcessorConfig.getElement().getSource(),
					input, 
					children,
					progressMonitor);
	
				if (sourceMessage == null) {
					return Collections.emptyList();
				}
				sourceEndpoint.apply(sourceMessage, progressMonitor).thenAccept(children::complete);
				return Collections.singleton(sourceMessage);
			}

			@Override
			public Collection<M> sourceApply(M input,
					BiFunction<M, ProgressMonitor, CompletionStage<Collection<M>>> targetEndpoint,
					ProgressMonitor progressMonitor) {
				
				CompletableFuture<Collection<M>> children = new CompletableFuture<>();
				
				// Sending a message to the target node
				M targetMessage = createMessage(
					Message.Type.INCOMING, 
					connectionProcessorConfig.getElement(), 
					connectionProcessorConfig.getElement().getTarget(),
					input, 
					children,
					progressMonitor);
	
				if (targetMessage == null) {
					return Collections.emptyList();
				}
				targetEndpoint.apply(targetMessage, progressMonitor).thenAccept(children::complete);
				return Collections.singleton(targetMessage);
			}
			
		};
	}
	
	@Override
	protected NodeProcessor<M, Collection<M>, M, CompletionStage<Collection<M>>> createNodeProcessor(
			NodeProcessorConfig<BiFunction<M, ProgressMonitor, Collection<M>>, BiFunction<M, ProgressMonitor, CompletionStage<Collection<M>>>> nodeProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<M, ProgressMonitor, Collection<M>>>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<M, ProgressMonitor, CompletionStage<Collection<M>>>> incomingEndpoints,
			Map<Connection, BiFunction<M, ProgressMonitor, CompletionStage<Collection<M>>>> outgoingEndpoints,
			ProgressMonitor progressMonitor) {
		
		return new NodeProcessor<M, Collection<M>, M, CompletionStage<Collection<M>>>() {

			@Override
			public Collection<M> apply(M message, ProgressMonitor u) {
				return sendMessages(null, false, message, progressMonitor);
			}

			@Override
			public Collection<M> applyIncoming(Connection connection, M input, ProgressMonitor progressMonitor) {
				return sendMessages(connection, false, input, progressMonitor);
			}

			@Override
			public Collection<M> applyOutgoing(Connection connection, M input, ProgressMonitor progressMonitor) {
				return sendMessages(connection, true, input, progressMonitor);
			}
			
			private Collection<M> sendMessages(
					Connection source,
					boolean isSourceOutgoing,
					M message, 
					ProgressMonitor progressMonitor) {
								
				Node node = nodeProcessorConfig.getElement(); 
				Collection<M> results = new ArrayList<>();
				
				// Incoming
				for (Entry<Connection, BiFunction<M, ProgressMonitor, CompletionStage<Collection<M>>>> ie: incomingEndpoints.entrySet()) {
					BiFunction<M, ProgressMonitor, CompletionStage<Collection<M>>> handler = ie.getValue();
					if (handler != null) {						
						CompletableFuture<Collection<M>> children = new CompletableFuture<>();						
						M incomingMessage = createMessage(
									Message.Type.TARGET, 
									node,
									ie.getKey(),
									message, 
									children,
									progressMonitor);
						
						if (incomingMessage != null) {
							results.add(incomingMessage);
							handler.apply(incomingMessage, progressMonitor).thenAccept(children::complete);								
						}
					}
				}
				
				// Outgoing				
				for (Entry<Connection, BiFunction<M, ProgressMonitor, CompletionStage<Collection<M>>>> oe: outgoingEndpoints.entrySet()) {
					BiFunction<M, ProgressMonitor, CompletionStage<Collection<M>>> handler = oe.getValue();
					if (handler != null) {
						CompletableFuture<Collection<M>> children = new CompletableFuture<>();						
						M outgoingMessage = createMessage(
									Message.Type.SOURCE, 
									node,
									oe.getKey(),
									message, 
									children,
									progressMonitor);
												
						if (outgoingMessage != null) {
							results.add(outgoingMessage);
							handler.apply(outgoingMessage, progressMonitor).thenAccept(children::complete);								
						}
					}
				}
				
				return results;
			}						
			
		};
	}
	
}
