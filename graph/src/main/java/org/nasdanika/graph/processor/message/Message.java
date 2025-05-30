package org.nasdanika.graph.processor.message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.nasdanika.graph.Element;

public interface Message extends Element, Predicate<Element> {
	
	Message getParent();
	
	Element getSender();
	
	Element getRecipient();

	/**
	 * Call to this method indicates that there should be no further message processing like sending child messages.
	 */
	void prune();
	
	@Override
	default boolean test(Element t) {
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
