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
	
	protected abstract V parentValue(V messageValue, Element child, Element parent);
	
	protected abstract V childValue(V messageValue, Element parent, Element child);

	protected abstract V sourceValue(V messageValue, Node node, Connection outgoingConnection);

	protected abstract V targetValue(V messageValue, Node node, Connection incomingConnection);
	
	protected abstract V incomingValue(V messageValue, Connection connection);

	protected abstract V outgoingValue(V messageValue, Connection connection);	
	
	protected ParentMessage<Element, V, ElementProcessor<Element, V>> createParentMessage(ElementMessage<?, V, ?> message, Element element, ElementProcessor<Element,V> parentProcessor, V parentValue) {
		return new ParentMessage<Element,V,ElementProcessor<Element,V>>(
				message, 
				parentProcessor, 
				parentValue);
	}

	protected ChildMessage<Element, V, ElementProcessor<Element, V>> createChildMessage(ElementMessage<?, V, ?> message, Element element, ElementProcessor<Element, V> childProcessor, V childValue) {
		return new ChildMessage<Element,V,ElementProcessor<Element,V>>(
				message, 
				childProcessor, 
				childValue);
	}
	
	protected <C extends Connection> SourceMessage<C, Node, V> createSourceMessage(IncomingConnectionMessage<C, V> message, NodeProcessor<Node,V> processor, V sourceValue) {
		return new SourceMessage<C,Node,V>(message, processor, sourceValue);
	}		

	protected <C extends Connection> TargetMessage<C, Node, V> createTargetMessage(OutgoingConnectionMessage<C, V> message, NodeProcessor<Node,V> processor, V targetValue) {
		return new TargetMessage<C,Node,V>(message, processor, targetValue);
	}	
	
	protected OutgoingConnectionMessage<Connection, V> createOutgoingConnectionMessage(ElementMessage<?, V, ?> message, ConnectionProcessor<Connection,V> processor, V outgoingValue) {
		return new OutgoingConnectionMessage<Connection,V>(message, processor, outgoingValue);
	}

	protected IncomingConnectionMessage<Connection, V> createIncomingConnectionMessage(ElementMessage<?, V, ?> message, ConnectionProcessor<Connection,V> processor,V incomingValue) {
		return new IncomingConnectionMessage<Connection,V>(message, processor, incomingValue);
	}
	
					
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

			@Override
			protected V parentValue(V messageValue, Element parent) {
				return MessageProcessorFactory.this.parentValue(messageValue, getElement(), parent);
			}
			
			@Override
			protected ChildMessage<Element, V, ElementProcessor<Element, V>> createChildMessage(ElementMessage<?, V, ?> message, ElementProcessor<Element, V> childProcessor, V childValue) {
				return MessageProcessorFactory.this.createChildMessage(message, getElement(), childProcessor, childValue);
			}
			
			@Override
			protected ParentMessage<Element, V, ElementProcessor<Element, V>> createParentMessage(ElementMessage<?, V, ?> message, V parentValue) {
				return MessageProcessorFactory.this.createParentMessage(message, getElement(), parentProcessor,  parentValue);
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
			protected V parentValue(V messageValue, Element parent) {
				return MessageProcessorFactory.this.parentValue(messageValue, getElement(), parent);
			}
			
			@Override
			protected ChildMessage<Element, V, ElementProcessor<Element, V>> createChildMessage(ElementMessage<?, V, ?> message, ElementProcessor<Element, V> childProcessor, V childValue) {
				return MessageProcessorFactory.this.createChildMessage(message, getElement(), childProcessor, childValue);
			}
			
			@Override
			protected ParentMessage<Element, V, ElementProcessor<Element, V>> createParentMessage(ElementMessage<?, V, ?> message, V parentValue) {
				return MessageProcessorFactory.this.createParentMessage(message, getElement(), parentProcessor,  parentValue);
			}
			
			@Override
			protected <C extends Connection> SourceMessage<C, Node, V> createSourceMessage(IncomingConnectionMessage<C, V> message, V sourceValue) {
				return MessageProcessorFactory.this.createSourceMessage(message, this, sourceValue);
			}
			
			@Override
			protected <C extends Connection> TargetMessage<C, Node, V> createTargetMessage(OutgoingConnectionMessage<C, V> message, V targetValue) {
				return MessageProcessorFactory.this.createTargetMessage(message, this, targetValue);
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
			protected V parentValue(V messageValue, Element parent) {
				return MessageProcessorFactory.this.parentValue(messageValue, getElement(), parent);
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
			protected ChildMessage<Element, V, ElementProcessor<Element, V>> createChildMessage(ElementMessage<?, V, ?> message, ElementProcessor<Element, V> childProcessor, V childValue) {
				return MessageProcessorFactory.this.createChildMessage(message, getElement(), childProcessor, childValue);
			}
			
			@Override
			protected ParentMessage<Element, V, ElementProcessor<Element, V>> createParentMessage(ElementMessage<?, V, ?> message, V parentValue) {
				return MessageProcessorFactory.this.createParentMessage(message, getElement(), parentProcessor,  parentValue);
			}
			
			@Override
			protected IncomingConnectionMessage<Connection, V> createIncomingConnectionMessage(ElementMessage<?, V, ?> message, V incomingValue) {
				return MessageProcessorFactory.this.createIncomingConnectionMessage(message, this, incomingValue);
			}
			
			@Override
			protected OutgoingConnectionMessage<Connection, V> createOutgoingConnectionMessage(ElementMessage<?, V, ?> message, V outgoingValue) {
				return MessageProcessorFactory.this.createOutgoingConnectionMessage(message, this, outgoingValue);
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
