package org.nasdanika.drawio.gen.section.messages;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.drawio.gen.section.BaseProcessor;


public abstract class Message<T extends BaseProcessor<?>> {
	
	private T processor;
	private Message<?> parent;
	private List<Message<?>> children;

	protected Message(T processor) {
		this(null, processor);
	}

	protected Message(Message<?> parent, T processor) {
		this.parent = parent;
		this.processor = processor;
	}

	/**
	 * Processor which processes this message by possibly sending child messages to
	 * related elements.
	 * @return
	 */
	public T getProcessor() {
		return processor;
	}
	
	/**
	 * Possibly sends child messages to related elements
	 * @param publisher
	 */
	public List<Message<?>> process() {
		children = processor.processMessage(this);
		return children;
	};
	
	/**
	 * Used to prevent infinite loops. It a message path
	 * already contains the argument processor this method shall return true. 
	 * @param processor
	 * @return
	 */
	public boolean hasSeen(BaseProcessor<?> processor) {
		if (this.processor == processor) {
			return true;
		};
		return getParent() != null && getParent().hasSeen(processor);
	}
	
	public Message<?> getParent() {
		return parent;
	}
	
	public RootMessage getRoot() {
		if (this instanceof RootMessage) {
			return (RootMessage) this;
		}
		return getParent().getRoot();
	}
	
	/**
	 * Message path starting from the root message.
	 * @return
	 */
	public List<Message<?>> getPath() {
		List<Message<?>> path = getParent() == null ? getParent().getPath() : new ArrayList<>();
		path.add(this);
		return path;			
	}
	
	public List<Message<?>> getChildren() {
		return children;
	}
	
	/**
	 * @param target
	 * @return Messages which reached the argument target from this message
	 */
	public List<Message<?>> getMessages(BaseProcessor<?> target) {
		List<Message<?>> ret = new ArrayList<>();
		if (target == processor) {
			ret.add(this);
		}
		for (Message<?> child: getChildren()) {
			ret.addAll(child.getMessages(target));
		}
		return ret;
	}
	
}	
