/**
 */
package org.nasdanika.graph.model.adapters;

import java.util.Objects;
import java.util.function.BiConsumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.model.Connection;

public class ConnectionAdapter implements org.nasdanika.graph.Connection, ElementAdapter<Connection<?>> {

	private Connection<?> value;
	
	public ConnectionAdapter(Connection<?> value, BiConsumer<EObject, BiConsumer<Element, ProgressMonitor>> elementProvider) {
		this.value = value;
		elementProvider.accept(value.eContainer(), (sourceAdapter, pm) -> source = (NodeAdapter) sourceAdapter);
		elementProvider.accept(value.getTarget(), (targetAdapter, pm) -> target = (NodeAdapter) targetAdapter);
	}

	@Override
	public Connection<?> get() {
		return value;
	}
	
	private NodeAdapter source;
	private NodeAdapter target;
	
	@Override
	public NodeAdapter getSource() {
		return source;
	}

	@Override
	public NodeAdapter getTarget() {
		return target;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getClass(), getSource(), getTarget(), value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConnectionAdapter other = (ConnectionAdapter) obj;
		if (!Objects.equals(value, other.get())) {
			return false;
		}
		if (!Objects.equals(getSource(), other.getSource())) {
			return false;
		}
		return Objects.equals(getTarget(), other.getTarget());
	}	
	
} 
