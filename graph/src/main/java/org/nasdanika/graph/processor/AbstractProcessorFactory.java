package org.nasdanika.graph.processor;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
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
	
	/**
	 * Creates a client handler
	 * @param config 
	 * @param parentHandler Handler to work with the parent
	 * @param childHandlerProvider Provider of child handlers
	 * @return
	 */
	protected abstract H getHandler(
			ProcessorConfig config, 
			H parentHandler, 
			Function<Element,H> childHandlerProvider);

	/**
	 * Handler to be used by the parent
	 * @param config
	 * @param childHandlerProvider
	 * @return 
	 */
	protected abstract H getParentHandler(ProcessorConfig config, H parentHandler,Function<Element,H> childHandlerProvider);

	/**
	 * Handler to be used by a given child
	 * @param config
	 * @param child
	 * @param parentHandler
	 * @param childHandlerProvider
	 * @return
	 */
	protected abstract H getChildHandler(ProcessorConfig config, Element child, H parentHandler, Function<Element,H> childHandlerProvider);
					
	@Processor(type = Element.class)
	public AbstractElementProcessor<Element,H> createElementProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new AbstractElementProcessor<Element,H>() {

			@Override
			protected H getHandler(H parentHandler, Function<Element,H> childHandlerProvider) {
				return AbstractProcessorFactory.this.getHandler(config, parentHandler, childHandlerProvider);
			}

			@Override
			protected H getParentHandler(H parentHandler, Function<Element,H> childHandlerProvider) {
				return AbstractProcessorFactory.this.getParentHandler(config, parentHandler, childHandlerProvider);
			}

			@Override
			protected H getChildHandler(Element child, H parentHandler, Function<Element,H> childHandlerProvider) {
				return AbstractProcessorFactory.this.getChildHandler(config, child, parentHandler, childHandlerProvider);
			}
			
		};
	}
	
	protected abstract H getOutgoingHandler(
			NodeProcessorConfig<H,E> config, 
			Connection outgoingConnection, 
			H parentHandler, 
			Function<Element,H> childHandlerProvider,
			Map<Connection, E> incomingEndpoints, 
			Map<Connection, E> outgoingEndpoints);

	protected abstract H getIncomingHandler(
			NodeProcessorConfig<H,E> config, 
			Connection incomingConnection, 
			H parentHandler, 
			Function<Element,H> childHandlerProvider,
			Map<Connection, E> incomingEndpoints, 
			Map<Connection, E> outgoingEndpoints);

	protected abstract H getHandler(
			NodeProcessorConfig<H,E> config, 
			H parentHandler, 
			Function<Element,H> childHandlerProvider, 
			Map<Connection, E> incomingEndpoints,
			Map<Connection, E> outgoingEndpoints);

	protected abstract H getChildHandler(
			NodeProcessorConfig<H,E> config, 
			Element child, 
			H parentHandler, 
			Function<Element,H> childHandlerProvider,
			Map<Connection, E> incomingEndpoints, 
			Map<Connection, E> outgoingEndpoints);

	protected abstract H getParentHandler(
			NodeProcessorConfig<H,E> config, 
			H parentHandler,
			Function<Element,H> childHandlerProvider, 
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
					H parentHandler, 
					Function<Element,H> childHandlerProvider,
					Map<Connection, E> incomingEndpoints, 
					Map<Connection, E> outgoingEndpoints) {
				
				return AbstractProcessorFactory.this.getOutgoingHandler(
						config, 
						outgoingConnection, 
						parentHandler, 
						childHandlerProvider, 
						incomingEndpoints, 
						outgoingEndpoints);
			}

			@Override
			protected H getIncomingHandler(
					Connection incomingConnection, 
					H parentHandler, 
					Function<Element,H> childHandlerProvider,
					Map<Connection, E> incomingEndpoints, 
					Map<Connection, E> outgoingEndpoints) {
				
				return AbstractProcessorFactory.this.getIncomingHandler(
						config, 
						incomingConnection, 
						parentHandler, 
						childHandlerProvider, 
						incomingEndpoints, 
						outgoingEndpoints);
			}

			@Override
			protected H getHandler(
					H parentHandler, 
					Function<Element,H> childHandlerProvider, 
					Map<Connection, E> incomingEndpoints,
					Map<Connection, E> outgoingEndpoints) {
				
				return AbstractProcessorFactory.this.getHandler(
						config, 
						parentHandler, 
						childHandlerProvider,
						incomingEndpoints,
						outgoingEndpoints);
			}

			@Override
			protected H getChildHandler(
					Element child, 
					H parentHandler, 
					Function<Element,H> childHandlerProvider,
					Map<Connection, E> incomingEndpoints, 
					Map<Connection, E> outgoingEndpoints) {

				return AbstractProcessorFactory.this.getChildHandler(
						config, 
						child, 
						parentHandler, 
						childHandlerProvider, 
						incomingEndpoints, 
						outgoingEndpoints);
			}

			@Override
			protected H getParentHandler(
					H parentHandler,
					Function<Element,H> childHandlerProvider, 
					Map<Connection, E> incomingEndpoints,
					Map<Connection, E> outgoingEndpoints) {

				return AbstractProcessorFactory.this.getParentHandler(
						config, 
						parentHandler,
						childHandlerProvider, 
						incomingEndpoints, 
						outgoingEndpoints);
			}
			
		};
	}
	
	protected abstract H getSourceHandler(
			ConnectionProcessorConfig<H,E> config, 
			H parentHandler, 
			Function<Element, H> childHandlerProvider, 
			E targetEndpiont);

	protected abstract H getTargetHandler(
			ConnectionProcessorConfig<H,E> config, 
			H parentHandler, 
			Function<Element, H> childHandlerProvider, 
			E sourceEndpiont);

	protected abstract H getHandler(
			ConnectionProcessorConfig<H,E> config, 
			H parentHandler, 
			Function<Element, H> childHandlerProvider, 
			E sourceEndpoint,
			E targetEndpoint);

	protected abstract H getChildHandler(
			ConnectionProcessorConfig<H,E> config, 
			Element child, 
			H parentHandler, 
			Function<Element, H> childHandlerProvider,
			E sourceEndpoint, 
			E targetEndpoint);

	protected abstract H getParentHandler(
			ConnectionProcessorConfig<H,E> config, 
			H parentHandler, 
			Function<Element, H> childHandlerProvider, 
			E sourceEndpoint,
			E targetEndpoint);
					
	@Processor(type = Connection.class)
	public AbstractConnectionProcessor<Connection,H,E> createConnectionProcessor(
		ConnectionProcessorConfig<H,E> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new AbstractConnectionProcessor<Connection,H,E>() {

			@Override
			protected H getSourceHandler(H parentHandler, Function<Element, H> childHandlerProvider, E targetEndpiont) {
				return AbstractProcessorFactory.this.getSourceHandler(config, parentHandler, childHandlerProvider, targetEndpiont);
			}

			@Override
			protected H getTargetHandler(H parentHandler, Function<Element, H> childHandlerProvider, E sourceEndpiont) {
				return AbstractProcessorFactory.this.getTargetHandler(config, parentHandler, childHandlerProvider, sourceEndpiont);
			}

			@Override
			protected H getHandler(
					H parentHandler, 
					Function<Element, H> childHandlerProvider, 
					E sourceEndpoint,
					E targetEndpoint) {
				return AbstractProcessorFactory.this.getHandler(config, parentHandler, childHandlerProvider, sourceEndpoint, targetEndpoint);
			}

			@Override
			protected H getChildHandler(
					Element child, 
					H parentHandler, 
					Function<Element, H> childHandlerProvider,
					E sourceEndpoint, 
					E targetEndpoint) {
				return AbstractProcessorFactory.this.getChildHandler(config, child, parentHandler, childHandlerProvider, sourceEndpoint, targetEndpoint);
			}

			@Override
			protected H getParentHandler(
					H parentHandler, 
					Function<Element, H> childHandlerProvider, 
					E sourceEndpoint,
					E targetEndpoint) {
				return AbstractProcessorFactory.this.getParentHandler(config, parentHandler, childHandlerProvider, sourceEndpoint, targetEndpoint);
			}
			
		};
	}	
	
	protected abstract E createEndpoint(Connection connection, H handler, HandlerType type);
		
	public Map<Element,H> creatProcessors(Collection<Element> elements, ProgressMonitor progressMonitor) {		
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
				
		ReflectiveProcessorFactoryProvider<AbstractElementProcessor<Element,H>,H,E> rpfp = new ReflectiveProcessorFactoryProvider<>(this);
		return rpfp
				.getFactory()
				.createProcessors(configs.values(), false, progressMonitor)
				.entrySet()
				.stream()
				.map(e -> {
					if (e.getValue() == null) {
						return null;
					}
					AbstractElementProcessor<Element, H> processor = e.getValue().getProcessor();
					if (processor == null) {
						return null;
					}
					H handler = processor.getHandler();
					if (handler == null) {
						return null;
					}
					return Map.entry(e.getKey(), handler);
				})
				.filter(Objects::nonNull)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}		
		
}
