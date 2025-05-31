package org.nasdanika.graph.processor.message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.nasdanika.graph.Element;

/**
 * A message from one element to another.
 * 
 */
public interface Message extends Element, Predicate<Element> {
	
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
		 * Parent element sent a message to its child.
		 * Sender is the recipient's parent. 
		 */
		PARENT,
		
		/**
		 * Child element sent a message to its parent.
		 * Sender is a recipient's child. 
		 */
		CHILD,
		
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
	 * An element which sent the message
	 * @return
	 */
	Element getSender();

	/**
	 * Call to this method indicates that there should be no further message processing like sending child messages.
	 */
	void prune();
	
	/**
	 * Invoked before passing this message to its processor. 
	 * This method returns true if the message is not pruned.
	 */
	@Override
	default boolean test(Element element) {
		return !isPruned();
	}
	
	default boolean isPruned() {
		Message parent = getParent();
		return parent != null && parent.isPruned();
	}	
	
	default List<Message> getPath() {
		Message parent = getParent();
		if (parent == null) {
			return Collections.singletonList(this);
		}
		List<Message> path = new ArrayList<>(parent.getPath());
		path.add(this);
		return Collections.unmodifiableList(path);
	}
	
}
