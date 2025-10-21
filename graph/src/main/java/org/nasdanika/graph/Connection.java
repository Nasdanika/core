package org.nasdanika.graph;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Connection extends Element {
	
	Node getSource();
	
	Node getTarget();
	
	@Override
	default boolean traverse(Consumer<? super Element> visitor, Predicate<? super Element> predicate) {
		if (Element.super.traverse(visitor, predicate)) {
			Node source = getSource();
			if (source != null) {
				source.traverse(visitor, predicate);
			}
			
			Node target = getTarget();
			if (target != null) {
				target.traverse(visitor, predicate);
			}
			
			return true;
		}
		
		return false;
	}

}
