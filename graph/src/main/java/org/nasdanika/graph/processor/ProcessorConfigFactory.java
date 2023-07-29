package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

/**
 * Reflective {@link Transformer} target which creates {@link ProcessorConfig} and its subclasses for {@link Element} and its subclasses respectively.
 */
public abstract class ProcessorConfigFactory<H,E> {
	
	/**
	 * Creates an endpoint to invoke the argument handler of specified type.
	 * @param connection
	 * @param handler
	 * @param type
	 * @return
	 */
	protected abstract E createEndpoint(Connection connection, H handler, HandlerType type);	
	
	private <T extends ProcessorConfigImpl> T wireConfig(
			T config,
			boolean parallel,
			Function<Element, CompletionStage<ProcessorConfig>> configProvider, 
			CompletionStage<Map<Element, ProcessorConfig>> registryCS,
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {

		if (progressMonitor.isCancelled()) {
			throw new CancellationException();
		}

		registryCS.thenAccept(config::setRegistry); // Not adding to stages - registryCS is completed after joining of other stages, would result in deadlock 
		
		for (Element child: config.getElement().getChildren()) {
			stageConsumer.accept(configProvider
					.apply(child)
					.thenAccept(cf -> {
						if (cf != null) {
							config.putChildConfig(child, cf);
							((ProcessorConfigImpl) cf).setParentConfig(config);
						}
					}));			
		}
		
		return config;
	}
			
	@org.nasdanika.common.Transformer.Factory(type=Element.class)
	public ProcessorConfig createElementConfig(
			Element element,
			boolean parallel,
			Function<Element, CompletionStage<ProcessorConfig>> configProvider, 
			CompletionStage<Map<Element, ProcessorConfig>> registryCS,
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {

		return wireConfig(
				new ProcessorConfigImpl(element),
				parallel,
				configProvider,
				registryCS,
				stageConsumer,
				progressMonitor);
		
	}
	
	@SuppressWarnings("unchecked")
	@org.nasdanika.common.Transformer.Factory(type=Node.class)
	public NodeProcessorConfig<H, E> createNodeConfig(
			Node node,
			boolean parallel,
			Function<Element, CompletionStage<ProcessorConfig>> configProvider, 
			CompletionStage<Map<Element, ProcessorConfig>> registryCS,
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {
		
		try (ProgressMonitor subMonitor =  progressMonitor.split("Creating node config", 1, node)) {
			
			NodeProcessorConfigImpl<H, E> configImpl = wireConfig(
					new NodeProcessorConfigImpl<>(node),
					parallel,
					configProvider,
					registryCS,
					stageConsumer,
					progressMonitor);
			
			for (Connection incomingConnection: node.getIncomingConnections()) {
				CompletionStage<Consumer<E>> incomingConnectionHandlerEndpointConsumerCompletionStage; 

				if (isPassThrough(incomingConnection)) {
					incomingConnectionHandlerEndpointConsumerCompletionStage = configProvider.apply(incomingConnection.getSource()).thenApply(sourceConfig -> {
						return ep -> ((NodeProcessorConfigImpl<H,E>) sourceConfig).setOutgoingEndpoint(incomingConnection, ep);
					});
				} else {
					incomingConnectionHandlerEndpointConsumerCompletionStage = configProvider
							.apply(incomingConnection)
							.thenApply(connectionConfig -> ((ConnectionProcessorConfigImpl<H,E>) connectionConfig)::setTargetEndpoint);
					
				}
				
				stageConsumer.accept(incomingConnectionHandlerEndpointConsumerCompletionStage.thenAccept(incomingConnectionHandlerEndpointConsumer -> {
					configImpl.wireIncomingHandlerEndpoint(
							incomingConnection, 
							incomingConnectionHandler -> createEndpoint(
									incomingConnection, 
									incomingConnectionHandler, 
									HandlerType.INCOMING), 
							incomingConnectionHandlerEndpointConsumer);											
				}));
			}
			
			for (Connection outgoingConnection: node.getOutgoingConnections()) {
				CompletionStage<Consumer<E>> outgoingConnectionHandlerEndpointConsumerCompletionStage; 

				if (isPassThrough(outgoingConnection)) {
					outgoingConnectionHandlerEndpointConsumerCompletionStage = configProvider.apply(outgoingConnection.getTarget()).thenApply(targetConfig -> {
						return ep -> ((NodeProcessorConfigImpl<H,E>) targetConfig).setIncomingEndpoint(outgoingConnection, ep);
					});
				} else {
					outgoingConnectionHandlerEndpointConsumerCompletionStage = configProvider
							.apply(outgoingConnection)
							.thenApply(connectionConfig -> ((ConnectionProcessorConfigImpl<H,E>) connectionConfig)::setSourceEndpoint);
					
				}
				
				stageConsumer.accept(outgoingConnectionHandlerEndpointConsumerCompletionStage.thenAccept(outgoingConnectionHandlerEndpointConsumer -> {
					configImpl.wireOutgoingHandlerEndpoint(
							outgoingConnection, 
							outgoingConnectionHandler -> createEndpoint(
									outgoingConnection, 
									outgoingConnectionHandler, 
									HandlerType.OUTGOING), 
							outgoingConnectionHandlerEndpointConsumer);											
				}));
			}
			
			subMonitor.worked(1, "Created node config", node, configImpl);			
			return configImpl;
		}		
	}
	
	/**
	 * Connections are pass-through by default for simplicity.
	 * @param incomingConnection
	 * @return
	 */
	protected boolean isPassThrough(Connection incomingConnection) {
		return true;
	};
	
	@org.nasdanika.common.Transformer.Factory(type=Connection.class)
	public ConnectionProcessorConfig<H, E> createConnectionConfig(
			Connection connection,
			boolean parallel,
			Function<Element, CompletionStage<ProcessorConfig>> configProvider, 
			CompletionStage<Map<Element, ProcessorConfig>> registryCS,
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {
		
		if (isPassThrough(connection)) {
			return null;
		}	
		
		try (ProgressMonitor subMonitor =  progressMonitor.split("Creating connection config", 1, connection)) {
			Node source = connection.getSource();
			Node target = connection.getTarget();
			
			ConnectionProcessorConfigImpl<H, E> configImpl = wireConfig(
					new ConnectionProcessorConfigImpl<>(connection),
					parallel,
					configProvider,
					registryCS,
					stageConsumer,
					progressMonitor);
			
			if (source != null) {
				stageConsumer.accept(configProvider.apply(source).thenAccept(sourceConfig -> {
					@SuppressWarnings("unchecked")
					Consumer<E> sourceHandlerEndpointConsumer = ep -> ((NodeProcessorConfigImpl<H, E>) sourceConfig).setOutgoingEndpoint(connection, ep); 
					configImpl.wireSourceHandlerEndpoint(sourceHandler -> createEndpoint(connection, sourceHandler, HandlerType.SOURCE), sourceHandlerEndpointConsumer);
				}));
			}
			
			if (target != null) {
				stageConsumer.accept(configProvider.apply(target).thenAccept(targetConfig -> {
					@SuppressWarnings("unchecked")
					Consumer<E> targetHandlerEndpointConsumer = ep -> ((NodeProcessorConfigImpl<H, E>) targetConfig).setIncomingEndpoint(connection, ep); 
					configImpl.wireTargetHandlerEndpoint(targetHandler -> createEndpoint(connection, targetHandler, HandlerType.TARGET), targetHandlerEndpointConsumer);
				}));
			}
			
			subMonitor.worked(1, "Created connection config", connection, configImpl);			
			return configImpl;
		}
	}	

}
