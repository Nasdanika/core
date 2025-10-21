package org.nasdanika.graph;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Node extends Element {
	
	Collection<? extends Connection> getIncomingConnections();
	
	Collection<? extends Connection> getOutgoingConnections();
	
	@Override
	default boolean traverse(Consumer<? super Element> visitor, Predicate<? super Element> predicate) {
		if (Element.super.traverse(visitor, predicate)) {
			for (Connection oc: getOutgoingConnections()) {
				oc.traverse(visitor, predicate);
			}
			for (Connection ic: getIncomingConnections()) {
				ic.traverse(visitor, predicate);
			}			
			return true;
		}
		
		return false;
	}
	

}
