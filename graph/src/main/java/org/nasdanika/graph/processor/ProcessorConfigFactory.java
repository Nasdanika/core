package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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
			BiConsumer<Element, BiConsumer<ProcessorConfig,ProgressMonitor>> configProvider, 
			Consumer<BiConsumer<Map<Element, ProcessorConfig>,ProgressMonitor>> registryProvider,
			ProgressMonitor progressMonitor) {

		if (progressMonitor.isCancelled()) {
			throw new CancellationException();
		}

		registryProvider.accept((registry, pm) -> config.setRegistry(registry));
		
		for (Element child: config.getElement().getChildren()) {
			configProvider.accept(child, (cf, pm) -> {
				if (cf != null) {
					config.putChildConfig(child, cf);
					((ProcessorConfigImpl) cf).setParentConfig(config);
				}				
			});
		}
		
		return config;
	}
			
	@org.nasdanika.common.Transformer.Factory(type=Element.class)
	public ProcessorConfig createElementConfig(
			Element element,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorConfig,ProgressMonitor>> configProvider, 
			Consumer<BiConsumer<Map<Element, ProcessorConfig>,ProgressMonitor>> registryProvider,
			ProgressMonitor progressMonitor) {

		return wireConfig(
				new ProcessorConfigImpl(element),
				parallel,
				configProvider,
				registryProvider,
				progressMonitor);
		
	}
	
	@SuppressWarnings("unchecked")
	@org.nasdanika.common.Transformer.Factory(type=Node.class)
	public NodeProcessorConfig<H, E> createNodeConfig(
			Node node,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorConfig,ProgressMonitor>> configProvider, 
			Consumer<BiConsumer<Map<Element, ProcessorConfig>,ProgressMonitor>> registryProvider,
			ProgressMonitor progressMonitor) {
		
		try (ProgressMonitor subMonitor =  progressMonitor.split("Creating node config", 1, node)) {
			
			NodeProcessorConfigImpl<H, E> configImpl = wireConfig(
					new NodeProcessorConfigImpl<>(node),
					parallel,
					configProvider,
					registryProvider,
					progressMonitor);
			
			for (Connection incomingConnection: node.getIncomingConnections()) {
				if (isPassThrough(incomingConnection)) {
					configProvider.accept(incomingConnection.getSource(), (sourceConfig, pMonitor) -> {
						configImpl.wireIncomingHandlerEndpoint(
								incomingConnection, 
								incomingConnectionHandler -> createEndpoint(
										incomingConnection, 
										incomingConnectionHandler, 
										HandlerType.INCOMING), 
								ep -> ((NodeProcessorConfigImpl<H,E>) sourceConfig).setOutgoingEndpoint(incomingConnection, ep));											
					});					
				} else {
					configProvider.accept(incomingConnection, (connectionConfig, pMonitor) -> {
						configImpl.wireIncomingHandlerEndpoint(
								incomingConnection, 
								incomingConnectionHandler -> createEndpoint(
										incomingConnection, 
										incomingConnectionHandler, 
										HandlerType.INCOMING), 
								((ConnectionProcessorConfigImpl<H,E>) connectionConfig)::setTargetEndpoint);											
					});					
				}
			}
			
			for (Connection outgoingConnection: node.getOutgoingConnections()) {
				if (isPassThrough(outgoingConnection)) {
					configProvider.accept(outgoingConnection.getTarget(), (targetConfig, pMonitor) -> {
						configImpl.wireOutgoingHandlerEndpoint(
								outgoingConnection, 
								outgoingConnectionHandler -> createEndpoint(
										outgoingConnection, 
										outgoingConnectionHandler, 
										HandlerType.OUTGOING), 
								ep -> ((NodeProcessorConfigImpl<H,E>) targetConfig).setIncomingEndpoint(outgoingConnection, ep));											
					});					
				} else {
					configProvider.accept(outgoingConnection, (connectionConfig, pMonitor) -> {
						configImpl.wireOutgoingHandlerEndpoint(
								outgoingConnection, 
								outgoingConnectionHandler -> createEndpoint(
										outgoingConnection, 
										outgoingConnectionHandler, 
										HandlerType.OUTGOING), 
								((ConnectionProcessorConfigImpl<H,E>) connectionConfig)::setSourceEndpoint);											
					});					
				}
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
			BiConsumer<Element, BiConsumer<ProcessorConfig,ProgressMonitor>> configProvider, 
			Consumer<BiConsumer<Map<Element, ProcessorConfig>,ProgressMonitor>> registryProvider,
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
					registryProvider,
					progressMonitor);
			
			if (source != null) {
				configProvider.accept(source, (sourceConfig, pMonitor) -> {
					@SuppressWarnings("unchecked")
					Consumer<E> sourceHandlerEndpointConsumer = ep -> ((NodeProcessorConfigImpl<H, E>) sourceConfig).setOutgoingEndpoint(connection, ep); 
					configImpl.wireSourceHandlerEndpoint(sourceHandler -> createEndpoint(connection, sourceHandler, HandlerType.SOURCE), sourceHandlerEndpointConsumer);					
				});
			}
			
			if (target != null) {
				configProvider.accept(target, (targetConfig, pMonitor) -> {
					@SuppressWarnings("unchecked")
					Consumer<E> targetHandlerEndpointConsumer = ep -> ((NodeProcessorConfigImpl<H, E>) targetConfig).setIncomingEndpoint(connection, ep); 
					configImpl.wireTargetHandlerEndpoint(targetHandler -> createEndpoint(connection, targetHandler, HandlerType.TARGET), targetHandlerEndpointConsumer);
				});
			}
			
			subMonitor.worked(1, "Created connection config", connection, configImpl);			
			return configImpl;
		}
	}	

}
