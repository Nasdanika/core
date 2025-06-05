package org.nasdanika.graph.processor.function;

import java.util.HashMap;
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

/**
 * A processor factory for passing messages between nodes via connections.
 * @param <T> Message type - first handler parameter type
 * @param <U> Handler return type
 * @param <V> First endpoint argument type 
 * @param <W> Endpoint return type
 */
public abstract class MessageProcessorFactory<T,U,V,W,NS,CS> extends BiFunctionProcessorFactory<T,U,V,W> {

	/**
	 * Creates a message to be sent to the connection source
	 * @param sender
	 * @param parent
	 * @param result
	 * @param progressMonitor
	 * @return
	 */
	protected abstract T createSourceMessage(
			CS processorState,
			Connection sender,
			T parent, 
			CompletionStage<W> result,
			ProgressMonitor progressMonitor);

	/**
	 * Creates a message to be sent to the connection target
	 * @param sender
	 * @param parent
	 * @param result
	 * @param progressMonitor
	 * @return
	 */
	protected abstract T createTargetMessage(
			CS processorState,
			Connection sender,
			T parent, 
			CompletionStage<W> result,
			ProgressMonitor progressMonitor);
	
	protected CS createConnectionProcessorState(
			ConnectionProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>> connectionProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<T, ProgressMonitor, U>>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {
		
		return null;
	}	
	
	@Override
	protected ConnectionProcessor<T, U, V, W> createConnectionProcessor(
			ConnectionProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>> connectionProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<T, ProgressMonitor, U>>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {
	
		CS state = createConnectionProcessorState(
				connectionProcessorConfig, 
				parallel, 
				infoProvider, 
				endpointWiringStageConsumer, 
				progressMonitor);
		
		return new ConnectionProcessor<T,U,V,W>() {

			@Override
			public U apply(T message, ProgressMonitor progressMonitor) {
				// Connections only pass messages between source and target
				return createEmptyResult();
			}

			@Override
			public U targetApply(
					T input, 
					BiFunction<V, ProgressMonitor, W> sourceEndpoint,
					ProgressMonitor progressMonitor) {
				
				CompletableFuture<W> resultCF = new CompletableFuture<>();
				// Sending a message to the source node
				T sourceMessage = createSourceMessage(
						state,
					connectionProcessorConfig.getElement(), 
					input, 
					resultCF,
					progressMonitor);
	
				if (sourceMessage == null) {
					return createEmptyResult();
				}
				W result = sourceEndpoint.apply(toEndpointArgument(sourceMessage), progressMonitor);
				resultCF.complete(result);
				return createConnectionResult(result);
			}

			@Override
			public U sourceApply(
					T input, 
					BiFunction<V, ProgressMonitor, W> targetEndpoint,
					ProgressMonitor progressMonitor) {
				
				CompletableFuture<W> resultCF = new CompletableFuture<>();
				// Sending a message to the target node
				T targetMessage = createTargetMessage(
						state,
					connectionProcessorConfig.getElement(), 
					input, 
					resultCF,
					progressMonitor);
	
				if (targetMessage == null) {
					return createEmptyResult();
				}
				W result = targetEndpoint.apply(toEndpointArgument(targetMessage), progressMonitor);
				resultCF.complete(result);
				return createConnectionResult(result);
			}
			
		};
	}
	
	protected abstract V toEndpointArgument(T message);
	
	protected U createEmptyResult() {
		return null;
	}
		
	protected abstract U createConnectionResult(W endpointResult);
	
	/**
	 * Creates a message to be sent by a node to an incoming or outgoing connection
	 * @param activator Connection which activated creation of this message. Null for handler activation
	 * @param incomingActivator true if the connection is incoming
	 * @param sender sender node
	 * @param recipient recipient connection
	 * @param incomingRecipient true for incoming connections 
	 * @param parent parent message
	 * @param result
	 * @param progressMonitor
	 * @return
	 */
	protected abstract T createConnectionMessage(
			NS processorState,
			Connection activator,
			boolean incomingActivator,
			Node sender,
			Connection recipient,
			boolean incomingRecipient,
			T parent, 
			CompletionStage<W> result,
			ProgressMonitor progressMonitor);
	
	protected NS createNodeProcessorState(
			NodeProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>> nodeProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<T, ProgressMonitor, U>>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<V, ProgressMonitor, W>> incomingEndpoints,
			Map<Connection, BiFunction<V, ProgressMonitor, W>> outgoingEndpoints,
			ProgressMonitor progressMonitor) {
		
		return null;
	}
	
	@Override
	protected NodeProcessor<T, U, V, W> createNodeProcessor(
			NodeProcessorConfig<BiFunction<T, ProgressMonitor, U>, BiFunction<V, ProgressMonitor, W>> nodeProcessorConfig,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<T, ProgressMonitor, U>>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<V, ProgressMonitor, W>> incomingEndpoints,
			Map<Connection, BiFunction<V, ProgressMonitor, W>> outgoingEndpoints,
			ProgressMonitor progressMonitor) {
		
		NS state = createNodeProcessorState(
				nodeProcessorConfig, 
				parallel, 
				infoProvider, 
				endpointWiringStageConsumer, 
				incomingEndpoints, 
				outgoingEndpoints, 
				progressMonitor);
	
		return new NodeProcessor<T,U,V,W>() {

			private U sendMessage(
					Connection activator, 
					boolean incomingActivator, 
					T input, 
					ProgressMonitor progressMonitor) {
				Node node = nodeProcessorConfig.getElement(); 
				Map<Connection, W> incomingResults = new HashMap<>();
				Map<Connection, W> outgoingResults = new HashMap<>();
				
				// Outgoing				
				for (Entry<Connection, BiFunction<V, ProgressMonitor, W>> oe: outgoingEndpoints.entrySet()) {
					BiFunction<V, ProgressMonitor, W> handler = oe.getValue();
					if (handler != null) {
						CompletableFuture<W> resultCF = new CompletableFuture<>();
						// Sending a message to the target node
						T message = createConnectionMessage(
							state,
							activator,
							incomingActivator,
							node,
							oe.getKey(),
							false,
							input, 
							resultCF,
							progressMonitor);
			
						if (message != null) {
							W result = handler.apply(toEndpointArgument(message), progressMonitor);
							resultCF.complete(result);
							incomingResults.put(oe.getKey(), result);
						}
					}
				}
				
				// Incoming
				for (Entry<Connection, BiFunction<V, ProgressMonitor, W>> ie: incomingEndpoints.entrySet()) {
					BiFunction<V, ProgressMonitor, W> handler = ie.getValue();
					if (handler != null) {													
						CompletableFuture<W> resultCF = new CompletableFuture<>();
						// Sending a message to the target node
						T message = createConnectionMessage(
							state,	
							activator,
							incomingActivator,
							node,
							ie.getKey(),
							true,
							input, 
							resultCF,
							progressMonitor);
			
						if (message != null) {
							W result = handler.apply(toEndpointArgument(message), progressMonitor);
							resultCF.complete(result);
							incomingResults.put(ie.getKey(), result);
						}
					}
				}
				
				return createNodeResult(incomingResults, outgoingResults);
			}
			
			@Override
			public U apply(T input, ProgressMonitor progressMonitor) {
				onApply(nodeProcessorConfig.getElement(), input, progressMonitor);
				return sendMessage(null, false, input, progressMonitor);
			}				

			@Override
			public U applyIncoming(Connection connection, T input, ProgressMonitor progressMonitor) {
				onApplyIncoming(nodeProcessorConfig.getElement(), connection, input, progressMonitor);
				return sendMessage(connection, true, input, progressMonitor);
			}

			@Override
			public U applyOutgoing(Connection connection, T input, ProgressMonitor progressMonitor) {
				onApplyOutgoing(nodeProcessorConfig.getElement(), connection, input, progressMonitor);
				return sendMessage(connection, false, input, progressMonitor);
			}
			
		};
	}
	
	protected void onApply(Node node, T input, ProgressMonitor progressMonitor) {
		
	}				

	protected void onApplyIncoming(Node node, Connection connection, T input, ProgressMonitor progressMonitor) {
		
	}

	protected void onApplyOutgoing(Node node, Connection connection, T input, ProgressMonitor progressMonitor) {
		
	}	
		
	protected abstract U createNodeResult(Map<Connection, W> incomingResults, Map<Connection, W> outgoingResults);	
	
}
