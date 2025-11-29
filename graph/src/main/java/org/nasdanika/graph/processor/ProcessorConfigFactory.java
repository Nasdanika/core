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
public abstract class ProcessorConfigFactory<H,E,K> implements EndpointFactory<H,E> {
		
	private <T extends ProcessorConfigImpl<H,E,K>> T wireConfig(
			T config,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorConfig<H,E,K>,ProgressMonitor>> configProvider, 
			Consumer<BiConsumer<Map<Element, ProcessorConfig<H,E,K>>,ProgressMonitor>> registryProvider,
			ProgressMonitor progressMonitor) {

		if (progressMonitor.isCancelled()) {
			throw new CancellationException();
		}

		registryProvider.accept((registry, pm) -> config.setRegistry(registry));
		
		for (Element child: config.getElement().getChildren()) {
			configProvider.accept(child, (cf, pm) -> {
				if (cf != null) {
					Pipe<H,E> childPipe = Pipe.create(
							h -> createEndpoint(child, h, HandlerType.PARENT), 
							h -> createEndpoint(config.getElement(), h, HandlerType.CHILD));
					config.putChildConfigAndSynapse(child, cf, childPipe.getSource());
					((ProcessorConfigImpl<H,E,K>) cf).setParentConfigAndSynapse(config, childPipe.getTarget());
				}				
			});
			
		}
		return config;
	}
			
	@org.nasdanika.common.Transformer.Factory(type=Element.class)
	public ProcessorConfig<H,E,K> createElementConfig(
			Element element,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorConfig<H,E,K>,ProgressMonitor>> configProvider, 
			Consumer<BiConsumer<Map<Element, ProcessorConfig<H,E,K>>,ProgressMonitor>> registryProvider,
			ProgressMonitor progressMonitor) {

		return wireConfig(
				new ProcessorConfigImpl<H,E,K>(element, h -> createEndpoint(element, h, HandlerType.CLIENT), h -> createEndpoint(element, h, HandlerType.PROCESSOR)),
				parallel,
				configProvider,
				registryProvider,
				progressMonitor);
		
	}
	
	@org.nasdanika.common.Transformer.Factory(type=Node.class)
	public NodeProcessorConfig<H,E,K> createNodeConfig(
			Node node,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorConfig<H,E,K>,ProgressMonitor>> configProvider, 
			Consumer<BiConsumer<Map<Element, ProcessorConfig<H,E,K>>,ProgressMonitor>> registryProvider,
			ProgressMonitor progressMonitor) {
		
		try (ProgressMonitor subMonitor =  progressMonitor.split("Creating node config", 1, node)) {
			
			NodeProcessorConfigImpl<H,E,K> configImpl = wireConfig(
					new NodeProcessorConfigImpl<>(
							node, 
							h -> createEndpoint(node, h, HandlerType.CLIENT), 
							h -> createEndpoint(node, h, HandlerType.PROCESSOR)),
					parallel,
					configProvider,
					registryProvider,
					progressMonitor);
			
			for (Connection incomingConnection: node.getIncomingConnections()) {
				if (isPassThrough(incomingConnection)) {
					Node source = incomingConnection.getSource();
					if (source != null) {
						configProvider.accept(source, (sourceConfig, pMonitor) -> {
							configImpl.wireIncomingHandlerEndpoint(
									incomingConnection, 
									incomingConnectionHandler -> createEndpoint(
											incomingConnection, 
											incomingConnectionHandler, 
											HandlerType.INCOMING), 
									ep -> ((NodeProcessorConfigImpl<H,E,K>) sourceConfig).setOutgoingEndpoint(incomingConnection, ep));											
						});
					}
				} else {
					configProvider.accept(incomingConnection, (connectionConfig, pMonitor) -> {
						configImpl.wireIncomingHandlerEndpoint(
								incomingConnection, 
								incomingConnectionHandler -> createEndpoint(
										incomingConnection, 
										incomingConnectionHandler, 
										HandlerType.INCOMING), 
								((ConnectionProcessorConfigImpl<H,E,K>) connectionConfig)::setTargetEndpoint);											
					});					
				}
			}
			
			for (Connection outgoingConnection: node.getOutgoingConnections()) {
				if (isPassThrough(outgoingConnection)) {
					Node target = outgoingConnection.getTarget();
					if (target != null) {
						configProvider.accept(target, (targetConfig, pMonitor) -> {
							configImpl.wireOutgoingHandlerEndpoint(
									outgoingConnection, 
									outgoingConnectionHandler -> createEndpoint(
											outgoingConnection, 
											outgoingConnectionHandler, 
											HandlerType.OUTGOING), 
									ep -> ((NodeProcessorConfigImpl<H,E,K>) targetConfig).setIncomingEndpoint(outgoingConnection, ep));											
						});
					}
				} else {
					configProvider.accept(outgoingConnection, (connectionConfig, pMonitor) -> {
						configImpl.wireOutgoingHandlerEndpoint(
								outgoingConnection, 
								outgoingConnectionHandler -> createEndpoint(
										outgoingConnection, 
										outgoingConnectionHandler, 
										HandlerType.OUTGOING), 
								((ConnectionProcessorConfigImpl<H,E,K>) connectionConfig)::setSourceEndpoint);											
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
	protected boolean isPassThrough(Connection connection) {
		return true;
	};
	
	@org.nasdanika.common.Transformer.Factory(type=Connection.class)
	public ConnectionProcessorConfig<H,E,K> createConnectionConfig(
			Connection connection,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorConfig<H,E,K>,ProgressMonitor>> configProvider, 
			Consumer<BiConsumer<Map<Element, ProcessorConfig<H,E,K>>,ProgressMonitor>> registryProvider,
			ProgressMonitor progressMonitor) {
		
		if (isPassThrough(connection)) {
			return null;
		}	
		
		try (ProgressMonitor subMonitor =  progressMonitor.split("Creating connection config", 1, connection)) {
			Node source = connection.getSource();
			Node target = connection.getTarget();
			
			ConnectionProcessorConfigImpl<H,E,K> configImpl = wireConfig(
					new ConnectionProcessorConfigImpl<>(
							connection,
							h -> createEndpoint(connection, h, HandlerType.CLIENT), 
							h -> createEndpoint(connection, h, HandlerType.PROCESSOR)),
					parallel,
					configProvider,
					registryProvider,
					progressMonitor);
			
			if (source != null) {
				configProvider.accept(source, (sourceConfig, pMonitor) -> {
					Consumer<E> sourceHandlerEndpointConsumer = ep -> ((NodeProcessorConfigImpl<H,E,K>) sourceConfig).setOutgoingEndpoint(connection, ep); 
					configImpl.wireSourceHandlerEndpoint(sourceHandler -> createEndpoint(connection, sourceHandler, HandlerType.SOURCE), sourceHandlerEndpointConsumer);					
				});
			}
			
			if (target != null) {
				configProvider.accept(target, (targetConfig, pMonitor) -> {
					Consumer<E> targetHandlerEndpointConsumer = ep -> ((NodeProcessorConfigImpl<H,E,K>) targetConfig).setIncomingEndpoint(connection, ep); 
					configImpl.wireTargetHandlerEndpoint(targetHandler -> createEndpoint(connection, targetHandler, HandlerType.TARGET), targetHandlerEndpointConsumer);
				});
			}
			
			subMonitor.worked(1, "Created connection config", connection, configImpl);			
			return configImpl;
		}
	}	

}
