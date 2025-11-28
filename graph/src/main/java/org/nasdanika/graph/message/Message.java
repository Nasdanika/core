package org.nasdanika.graph.message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.HandlerType;

/**
 * A message sent to a target of type T and value of type V
 * @param <T> Message target type
 * @param <V> Message value type
 */
public class Message<V> implements Element {
	
	private HandlerType type;
	private Element target;
	private V value;
	private Message<V> parent;
	private List<Message<V>> children = Collections.synchronizedList(new ArrayList<>());

	public Message(HandlerType type, Element target, V value) {
		this(type, null, target, value);
	}

	public Message(HandlerType type, Message<V> parent, Element target, V value) {
		this.type = type;
		this.parent = parent;
		if (parent != null) {
			parent.children.add(this);
		}
		this.target = target;
		this.value = value;
	}
	
	public HandlerType getType() {
		return type;
	}
		
	public V getValue() {
		return value;
	}

	/**
	 * Processor which processes this message by possibly sending child messages to
	 * related elements.
	 * @return
	 */
	public Element getTarget() {
		return target;
	}
	
	/**
	 * Used to prevent infinite loops. It a message path
	 * already contains the argument target this method shall return true. 
	 * @param target
	 * @return
	 */
	public boolean hasSeen(Object target) {
		if (this.target == target) {
			return true;
		};
		return getParent() != null && getParent().hasSeen(target);
	}
	
	public Message<V> getParent() {
		return parent;
	}
	
	public Message<V> getRoot() {
		if (getParent() == null) {
			return this;
		}
		return getParent().getRoot();
	}
	
	/**
	 * Message path starting from the root message.
	 * @return
	 */
	public List<Message<V>> getPath() {
		List<Message<V>> path = getParent() == null ? getParent().getPath() : new ArrayList<>();
		path.add(this);
		return path;			
	}
	
	public List<Message<V>> getChildren() {
		return children;
	}
	
	/**
	 * @param target
	 * @return Messages which reached the argument target from this message
	 */
	public List<Message<V>> getMessages(Object target) {
		List<Message<V>> ret = new ArrayList<>();
		if (this.target == target) {
			ret.add(this);
		}
		for (Message<V> child: getChildren()) {
			ret.addAll(child.getMessages(target));
		}
		return ret;
	}
	
}	
