package org.nasdanika.graph.processor;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

/**
 * Basic processor factory
 * @param <V>
 */
public abstract class AbstractProcessorFactory<H,E>  {
	
	protected abstract ElementHandlerFactory<H,E> getElementHandlerFactory(ProcessorConfig config, Map<Element, E> childEndpoints);
					
	@Processor(type = Element.class)
	public AbstractElementProcessor<Element,H,E> createElementProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new AbstractElementProcessor<Element,H,E>() {

			@Override
			protected E getChildEndpoint(Element child, Supplier<E> parentEndpointSupplier, Map<Element, E> childEndpoints) {
				H childHandler = getElementHandlerFactory(config,childEndpoints).getChildHandler(child, parentEndpointSupplier);
				return createChildEndpoint(getElement(), child, childHandler);
			}

			@Override
			protected E getParentEndpoint(E parentEndpoint, Map<Element, E> childEndpoints) {
				H parentHandler = getElementHandlerFactory(config,childEndpoints).getParentHandler(parentEndpoint);
				return createParentEndpoint(getElement(), parentHandler);
			}

			@Override
			protected H getHandler(E parentEndpoint, Map<Element, E> childEndpoints) {
				return getElementHandlerFactory(config, childEndpoints).getHandler(parentEndpoint);
			}
			
		};
	}
		
	protected abstract NodeHandlerFactory<H,E> getNodeHandlerFactory(
			ProcessorConfig config, 
			Map<Element, E> childEndpoints, 
			Map<Connection, E> incomingEndpoints,
			Map<Connection, E> outgoingEndpoints);	
				
	@Processor(type = Node.class)
	public AbstractNodeProcessor<Node,H,E> createNodeProcessor(
		NodeProcessorConfig<H,E> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new AbstractNodeProcessor<Node,H,E>() {

			@Override
			protected H getOutgoingHandler(
					Connection outgoingConnection, 
					Supplier<E> parentEndpointSupplier,
					Map<Element, E> childEndpoints, 
					Map<Connection, E> incomingEndpoints,
					Map<Connection, E> outgoingEndpoints) {
				
				return getNodeHandlerFactory(config, childEndpoints, incomingEndpoints, outgoingEndpoints).getOutgoingHandler(outgoingConnection, parentEndpointSupplier);
			}

			@Override
			protected H getIncomingHandler(
					Connection incomingConnection, 
					Supplier<E> parentEndpointSupplier,
					Map<Element, E> childEndpoints, 
					Map<Connection, E> incomingEndpoints,
					Map<Connection, E> outgoingEndpoints) {

				
				return getNodeHandlerFactory(config, childEndpoints, incomingEndpoints, outgoingEndpoints).getIncomingHandler(incomingConnection, parentEndpointSupplier);
			}

			@Override
			protected H getHandler(
					E parentEndpoint, 
					Map<Element, E> childEndpoints,
					Map<Connection, E> incomingEndpoints, 
					Map<Connection, E> outgoingEndpoints) {
				
				return getNodeHandlerFactory(config, childEndpoints, incomingEndpoints, outgoingEndpoints).getHandler(parentEndpoint);
			}

			@Override
			protected E getChildEndpoint(
					Element child, 
					Supplier<E> parentEndpointSupplier,
					Map<Element, E> childEndpoints, 
					Map<Connection, E> incomingEndpoints,
					Map<Connection, E> outgoingEndpoints) {
				
				H childHandler = getNodeHandlerFactory(config, childEndpoints, incomingEndpoints, outgoingEndpoints).getChildHandler(child, parentEndpointSupplier);
				return createChildEndpoint(getElement(), child, childHandler);
			}

			@Override
			protected E getParentEndpoint(
					E parentEndpoint, 
					Map<Element, E> childEndpoints,
					Map<Connection, E> incomingEndpoints, 
					Map<Connection, E> outgoingEndpoints) {
				
				H parentHandler = getNodeHandlerFactory(config, childEndpoints, incomingEndpoints, outgoingEndpoints).getParentHandler(parentEndpoint);
				return createParentEndpoint(getElement(), parentHandler);
			}
			
		};
	}
		
	protected abstract ConnectionHandlerFactory<H,E> getConnectionHandlerFactory(
			ProcessorConfig config, 
			Map<Element, E> childEndpoints, 
			Supplier<E> sourceEndpointSupplier,
			Supplier<E> targetEndpointSupplier);		
						
	@Processor(type = Connection.class)
	public AbstractConnectionProcessor<Connection,H,E> createConnectionProcessor(
		ConnectionProcessorConfig<H,E> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new AbstractConnectionProcessor<Connection,H,E>() {

			@Override
			protected H getHandler(
					E parentEndpoint, 
					Map<Element, E> childEndpoints, 
					Supplier<E> sourceEndpointSupplier,
					Supplier<E> targetEndpointSupplier) {
				
				return getConnectionHandlerFactory(config, childEndpoints, sourceEndpointSupplier, targetEndpointSupplier).getHandler(parentEndpoint);
			}

			@Override
			protected E getChildEndpoint(
					Element child, Supplier<E> parentEndpointSupplier,
					Map<Element, E> childEndpoints, 
					Supplier<E> sourceEndpointSupplier,
					Supplier<E> targetEndpointSupplier) {
				
				H childHandler = getConnectionHandlerFactory(config, childEndpoints, sourceEndpointSupplier, targetEndpointSupplier).getChildHandler(child, parentEndpointSupplier);
				return createChildEndpoint(getElement(), child, childHandler);
			}

			@Override
			protected E getParentEndpoint(
					E parentEndpoint, 
					Map<Element, E> childEndpoints,
					Supplier<E> sourceEndpointSupplier, 
					Supplier<E> targetEndpointSupplier) {
				
				H parentHandler = getConnectionHandlerFactory(config, childEndpoints, sourceEndpointSupplier, targetEndpointSupplier).getParentHandler(parentEndpoint);
				return createParentEndpoint(getElement(), parentHandler);
			}

			@Override
			protected H getSourceHandler(
					Supplier<E> parentEndpointSupplier, 
					Map<Element, E> childEndpoints,
					Supplier<E> sourceEndpointSupplier, 
					Supplier<E> targetEndpointSupplier) {
				
				return getConnectionHandlerFactory(config, childEndpoints, sourceEndpointSupplier, targetEndpointSupplier).getSourceHandler(parentEndpointSupplier);
			}

			@Override
			protected H getTargetHandler(
					Supplier<E> parentEndpointSupplier, 
					Map<Element, E> childEndpoints,
					Supplier<E> sourceEndpointSupplier, 
					Supplier<E> targetEndpointSupplier) {
				
				return getConnectionHandlerFactory(config, childEndpoints, sourceEndpointSupplier, targetEndpointSupplier).getTargetHandler(parentEndpointSupplier);
			}
				
		};
	}
	
	protected abstract E createEndpoint(Element element, H handler);		
		
	/**
	 * Endpoint for the parent element processor to invoke the child element element processor
	 * @param parent
	 * @param child
	 * @param handler
	 * @return
	 */
	protected abstract E createParentEndpoint(Element element, H handler);	
	
	/**
	 * Enpoint for the child element processor to invoke the parent element processor
	 * @param parent
	 * @param child
	 * @param handler
	 * @return
	 */
	protected abstract E createChildEndpoint(Element parent, Element child, H handler);	
	
	protected abstract E createEndpoint(Connection connection, H handler, HandlerType type);
		
	public Map<Element,E> creatProcessors(Collection<Element> elements, ProgressMonitor progressMonitor) {		
		ProcessorConfigFactory<H,E> processorConfigFactory = new ProcessorConfigFactory<H,E>() {
			
			@Override
			protected boolean isPassThrough(Connection incomingConnection) {
				return false;
			}

			@Override
			public E createEndpoint(Connection connection, H handler, HandlerType type) {
				return AbstractProcessorFactory.this.createEndpoint(connection, handler, type);
			}
			
		};
		
		Transformer<Element,ProcessorConfig> processorConfigTransformer = new Transformer<>(processorConfigFactory);				
		Map<Element, ProcessorConfig> configs = processorConfigTransformer.transform(elements, false, progressMonitor);
				
		ReflectiveProcessorFactoryProvider<AbstractElementProcessor<Element,H,E>,H,E> rpfp = new ReflectiveProcessorFactoryProvider<>(this);
		return rpfp
				.getFactory()
				.createProcessors(configs.values(), false, progressMonitor)
				.entrySet()
				.stream()
				.map(e -> {
					if (e.getValue() == null) {
						return null;
					}
					AbstractElementProcessor<Element,H,E> processor = e.getValue().getProcessor();
					if (processor == null) {
						return null;
					}
					H handler = processor.getHandler();
					if (handler == null) {
						return null;
					}
					E endpoint = createEndpoint(e.getKey(), handler);
					if (endpoint == null) {
						return null;
					}
					return Map.entry(e.getKey(), endpoint);
				})
				.filter(Objects::nonNull)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}		
		
}
