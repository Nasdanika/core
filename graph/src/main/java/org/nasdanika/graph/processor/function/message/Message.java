package org.nasdanika.graph.processor.function.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionStage;

import org.nasdanika.graph.Element;

/**
 * A message from one element to another.
 * 
 */
public interface Message {
	
	/**
	 * Message type
	 */
	enum Type {
		
		/**
		 * Message came from the client code via the element processor handler.
		 * In this case the parent message can be null.
		 */
		HANDLER,
		
		/**
		 * Incoming connection sent a message from its end to this node, which is the connection's target.
		 * Sender is a recipient's incoming connection. 
		 */
		INCOMING,
		
		/**
		 * Outgoing connection sent a message from its start to this node, which is the connection's source.
		 * Sender is a recipient's outgoing connection. 
		 */
		OUTGOING,
		
		/**
		 * Connection source node sent a message to this connection start.
		 * Sender is the recipient's source. 
		 */		
		SOURCE,
				
		/**
		 * Connection target node sent a message to this connection end.
		 * Sender is the recipient's target. 
		 */				
		TARGET
		
	}
	
	Type getType();

	/**
	 * A message which initiate sending of this message. 
	 * May be null for messages sent by the client code via handlers.
	 * @return
	 */
	Message getParent();
	
	/**
	 * An element which sent the message. 
	 * Can be null for messages sent by the client code via handlers.
	 * @return
	 */
	Element getSender();
	
	default List<Message> getPath() {
		Message parent = getParent();
		if (parent == null) {
			return Collections.singletonList(this);
		}
		List<Message> path = new ArrayList<>(parent.getPath());
		path.add(this);
		return Collections.unmodifiableList(path);
	}
	
	CompletionStage<? extends Collection<? extends Message>> getChildren();
	
}
