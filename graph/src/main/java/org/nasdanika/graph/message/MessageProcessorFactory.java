package org.nasdanika.graph.message;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;

/**
 * Basic message processor factory
 * @param <V>
 */
public abstract class MessageProcessorFactory<V>  {
	
	protected abstract V parentValue(Element element, Message<V> message);
	
	protected abstract V childValue(Element element, Message<V> message, Element child);
		
	protected void onClientMessage(Element element, Object clientKey, Message<V> message) {
		
	}	
	
	protected void onParentMessage(Element element, Message<V> message) {
		
	}
	
	protected void onChildMessage(Element element, Element child, Message<V> message) {
		
	}	
	
	@Processor(type = Element.class)
	public ElementProcessor<V> createElementProcessor(
		ProcessorConfig<Consumer<Message<V>>, BiConsumer<Message<V>,V>> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Consumer<Message<V>>, BiConsumer<Message<V>,V>, ElementProcessor<V>>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new ElementProcessor<V>() {

			@Override
			protected V parentValue(Message<V> message) {
				return MessageProcessorFactory.this.parentValue(config.getElement(), message);
			}

			@Override
			protected V childValue(Message<V> message, Element child) {
				return MessageProcessorFactory.this.childValue(config.getElement(), message, child);
			}
			
			@Override
			protected void onChildMessage(Element child, Message<V> message) {
				super.onChildMessage(child, message);
				MessageProcessorFactory.this.onChildMessage(config.getElement(), child, message);
			}
			
			@Override
			protected void onClientMessage(Object clientKey, Message<V> message) {
				super.onClientMessage(clientKey, message);
				MessageProcessorFactory.this.onClientMessage(config.getElement(), clientKey, message);
			}
			
			@Override
			protected void onParentMessage(Message<V> message) {
				super.onParentMessage(message);
				MessageProcessorFactory.this.onParentMessage(config.getElement(), message);
			}			
			
		};
	}
	
	protected abstract V sourceValue(Connection connection, Message<V> message);
	
	protected abstract V targetValue(Connection connection, Message<V> message);
		
	protected void onSourceMessage(Connection connection, Message<V> message) {
		
	}	
	
	protected void onTargetMessage(Connection connection, Message<V> message) {
		
	}
	
	@Processor(type = Connection.class)
	public ElementProcessor<V> createConnectionProcessor(
		ConnectionProcessorConfig<Consumer<Message<V>>, BiConsumer<Message<V>,V>> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Consumer<Message<V>>, BiConsumer<Message<V>,V>, ElementProcessor<V>>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new ConnectionProcessor<V>() {

			@Override
			protected V parentValue(Message<V> message) {
				return MessageProcessorFactory.this.parentValue(config.getElement(), message);
			}

			@Override
			protected V childValue(Message<V> message, Element child) {
				return MessageProcessorFactory.this.childValue(config.getElement(), message, child);
			}
			
			@Override
			protected void onChildMessage(Element child, Message<V> message) {
				super.onChildMessage(child, message);
				MessageProcessorFactory.this.onChildMessage(config.getElement(), child, message);
			}
			
			@Override
			protected void onClientMessage(Object clientKey, Message<V> message) {
				super.onClientMessage(clientKey, message);
				MessageProcessorFactory.this.onClientMessage(config.getElement(), clientKey, message);
			}
			
			@Override
			protected void onParentMessage(Message<V> message) {
				super.onParentMessage(message);
				MessageProcessorFactory.this.onParentMessage(config.getElement(), message);
			}

			@Override
			protected V sourceValue(Message<V> message) {
				return MessageProcessorFactory.this.sourceValue(config.getElement(), message);
			}

			@Override
			protected V targetValue(Message<V> message) {
				return MessageProcessorFactory.this.targetValue(config.getElement(), message);
			}		
			
			@Override
			protected void onSourceMessage(Message<V> message) {
				super.onSourceMessage(message);
				MessageProcessorFactory.this.onSourceMessage(config.getElement(), message);
			}
			
			@Override
			protected void onTargetMessage(Message<V> message) {
				super.onTargetMessage(message);
				MessageProcessorFactory.this.onTargetMessage(config.getElement(), message);
			}
			
		};
	}
	
	protected abstract V outgoingValue(Node node, Message<V> message, Connection outgoingConnection);
	
	protected abstract V incomingValue(Node node, Message<V> message, Connection incomingConnection);
	
	protected void onIncomingMessage(Node node, Connection incomingConnection, Message<V> message) {
		
	}
	
	protected void onOutgoingMessage(Node node, Connection incomingConnection, Message<V> message) {
		
	}	
	
	@Processor(type = Node.class)
	public ElementProcessor<V> createElementProcessor(
		NodeProcessorConfig<Consumer<Message<V>>, BiConsumer<Message<V>,V>> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Consumer<Message<V>>, BiConsumer<Message<V>,V>, ElementProcessor<V>>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new NodeProcessor<V>() {

			@Override
			protected V parentValue(Message<V> message) {
				return MessageProcessorFactory.this.parentValue(config.getElement(), message);
			}

			@Override
			protected V childValue(Message<V> message, Element child) {
				return MessageProcessorFactory.this.childValue(config.getElement(), message, child);
			}
			
			@Override
			protected void onChildMessage(Element child, Message<V> message) {
				super.onChildMessage(child, message);
				MessageProcessorFactory.this.onChildMessage(config.getElement(), child, message);
			}
			
			@Override
			protected void onClientMessage(Object clientKey, Message<V> message) {
				super.onClientMessage(clientKey, message);
				MessageProcessorFactory.this.onClientMessage(config.getElement(), clientKey, message);
			}
			
			@Override
			protected void onParentMessage(Message<V> message) {
				super.onParentMessage(message);
				MessageProcessorFactory.this.onParentMessage(config.getElement(), message);
			}

			@Override
			protected V outgoingValue(Message<V> message, Connection outgoingConnection) {
				return MessageProcessorFactory.this.outgoingValue(config.getElement(), message, outgoingConnection);
			}

			@Override
			protected V incomingValue(Message<V> message, Connection incomingConnection) {
				return MessageProcessorFactory.this.incomingValue(config.getElement(), message, incomingConnection);
			}		
			
			@Override
			protected void onIncomingMessage(Connection incomingConnection, Message<V> message) {
				super.onIncomingMessage(incomingConnection, message);
				MessageProcessorFactory.this.onIncomingMessage(config.getElement(), incomingConnection, message);
			}
			
			@Override
			protected void onOutgoingMessage(Connection outgoingConnection, Message<V> message) {
				super.onOutgoingMessage(outgoingConnection, message);
				MessageProcessorFactory.this.onChildMessage(config.getElement(), outgoingConnection, message);				
			}
			
		};
	}
		
	public Map<Element, ProcessorInfo<Consumer<Message<V>>, BiConsumer<Message<V>,V>, ElementProcessor<V>>> createProcessors(Collection<Element> elements, ProgressMonitor progressMonitor) {		
		MessageProcessorConfigFactory<V> processorConfigFactory = new MessageProcessorConfigFactory<>();
		
		Transformer<Element,ProcessorConfig<Consumer<Message<V>>, BiConsumer<Message<V>,V>>> processorConfigTransformer = new Transformer<>(processorConfigFactory);				
		Map<Element, ProcessorConfig<Consumer<Message<V>>, BiConsumer<Message<V>,V>>> configs = processorConfigTransformer.transform(elements, false, progressMonitor);
				
		ReflectiveProcessorFactoryProvider<Consumer<Message<V>>, BiConsumer<Message<V>,V>, ElementProcessor<V>> rpfp = new ReflectiveProcessorFactoryProvider<>(this);
		return rpfp.getFactory().createProcessors(configs.values(), false, progressMonitor);
	}	
	
}
