package org.nasdanika.graph.message;

import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.ProcessorConfigFactory;

public class MessageProcessorConfigFactory<V> extends ProcessorConfigFactory<Consumer<Message<V>>, BiConsumer<Message<V>,V>> {
	
	private Executor executor;

	public MessageProcessorConfigFactory(Executor executor) {
		this.executor = executor;
	}

	/**
	 * Calling thread executor
	 */
	public MessageProcessorConfigFactory() {
		this(Runnable::run);
	}	

	@Override
	public BiConsumer<Message<V>,V> createEndpoint(Element element, Consumer<Message<V>> handler, HandlerType type) {
//		System.out.println("Endpoint " + type + " for " + element);

		return (parentMessage, value) -> {
//			System.out.println("Message " + type + " for " + element);
//			System.out.println("\tParent");
//			System.out.println("\t\ttype " + parentMessage.getType());
//			System.out.println("\t\tclass " + parentMessage.getClass());
//			System.out.println("\t\ttarget " + parentMessage.getTarget());
//			System.out.println("\t\tvalue " + parentMessage.getValue());
//			System.out.println("\tValue " + value);			
			
			if (!parentMessage.hasSeen(element)) {				
				Message<V> message = new Message<V>(type, parentMessage, element, value);
				executor.execute(() -> handler.accept(message));
			}
		};
	}
	
	@Override
	protected boolean isPassThrough(Connection connection) {
		return false;
	}

}
