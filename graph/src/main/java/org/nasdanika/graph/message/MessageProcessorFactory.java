package org.nasdanika.graph.message;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;

/**
 * Basic message processor factory
 * @param <V>
 */
public abstract class MessageProcessorFactory<V>  {
	
	protected abstract V childValue(V messageValue, Element parent, Element child);

	protected abstract V sourceValue(V messageValue, Node node, Connection outgoingConnection);

	protected abstract V targetValue(V messageValue, Node node, Connection incomingConnection);
	
	protected abstract V incomingValue(V messageValue, Connection connection);

	protected abstract V outgoingValue(V messageValue, Connection connection);	
					
	@Processor(type = Element.class)
	public ElementProcessor<Element,V> createElementProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new ElementProcessor<Element,V>() {

			@Override
			protected V childValue(V messageValue, Element child) {
				return MessageProcessorFactory.this.childValue(messageValue, getElement(), child);
			}
			
			@Override
			public Collection<ElementMessage<?, V, ?>> processMessage(ElementMessage<?, V, ?> message) {
				Collection<ElementMessage<?, V, ?>> messages = super.processMessage(message);
				messages.addAll(MessageProcessorFactory.this.processMessage(this, message));
				return messages;
			}
			
		};
	}
				
	@Processor(type = Node.class)
	public NodeProcessor<Node,V> createNodeProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new NodeProcessor<Node,V>() {

			@Override
			protected V childValue(V messageValue, Element child) {
				return MessageProcessorFactory.this.childValue(messageValue, getElement(), child);
			}

			@Override
			protected V sourceValue(V messageValue, Connection outgoingConnection) {
				return MessageProcessorFactory.this.sourceValue(messageValue, getElement(), outgoingConnection);
			}

			@Override
			protected V targetValue(V messageValue, Connection incomingConnection) {
				return MessageProcessorFactory.this.targetValue(messageValue, getElement(), incomingConnection);
			}
			
			@Override
			public Collection<ElementMessage<?, V, ?>> processMessage(ElementMessage<?, V, ?> message) {
				Collection<ElementMessage<?, V, ?>> messages = super.processMessage(message);
				messages.addAll(MessageProcessorFactory.this.processMessage(this, message));
				return messages;
			}
			
		};
	}
					
	@Processor(type = Connection.class)
	public ConnectionProcessor<Connection,V> createConnectionProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new ConnectionProcessor<Connection,V>() {

			@Override
			protected V childValue(V messageValue, Element child) {
				return MessageProcessorFactory.this.childValue(messageValue, getElement(), child);
			}

			@Override
			protected V incomingValue(V messageValue) {
				return MessageProcessorFactory.this.incomingValue(messageValue, getElement());
			}

			@Override
			protected V outgoingValue(V messageValue) {
				return MessageProcessorFactory.this.outgoingValue(messageValue, getElement());
			}
			
			@Override
			public Collection<ElementMessage<?, V, ?>> processMessage(ElementMessage<?, V, ?> message) {
				Collection<ElementMessage<?, V, ?>> messages = super.processMessage(message);
				messages.addAll(MessageProcessorFactory.this.processMessage(this, message));
				return messages;
			}
			
		};
	}
	
	/**
	 * Override to create additional messages if needed.
	 * @param processor
	 * @param message
	 * @return
	 */
	protected Collection<ElementMessage<?, V, ?>> processMessage(ElementProcessor<?,V> processor, ElementMessage<?, V, ?> message) {
		return Collections.emptyList();
	}
	
	public Map<Element, ProcessorInfo<ElementProcessor<?,V>>> creatProcessors(Collection<Element> elements, ProgressMonitor progressMonitor) {
		
		NopEndpointProcessorConfigFactory<ElementProcessor<?,V>> processorConfigFactory = new NopEndpointProcessorConfigFactory<ElementProcessor<?,V>>() {
			
			@Override
			protected boolean isPassThrough(Connection incomingConnection) {
				return false;
			}
			
		};
		
		Transformer<Element,ProcessorConfig> processorConfigTransformer = new Transformer<>(processorConfigFactory);				
		Map<Element, ProcessorConfig> configs = processorConfigTransformer.transform(elements, false, progressMonitor);
				
		ReflectiveProcessorFactoryProvider<ElementProcessor<?,V>, Function<ElementMessage<?,V,?>, ElementMessage<?,V,?>>, Function<ElementMessage<?,V,?>, ElementMessage<?,V,?>>> rpfp = new ReflectiveProcessorFactoryProvider<>(this);
		return rpfp.getFactory().createProcessors(configs.values(), false, progressMonitor);
	}	
	
}
