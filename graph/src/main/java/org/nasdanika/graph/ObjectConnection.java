package org.nasdanika.graph;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * A connection which holds a value object. Implements value-based hashCode() and equals. 
 */
public class ObjectConnection<T> extends SimpleConnection implements Supplier<T> {
	
	private T value;

	public ObjectConnection(Node source, Node target, boolean visitTarget, T value) {
		super(source, target, visitTarget);
		this.value = value;
	}
	
	@Override
	public T get() {
		return value;
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
		@SuppressWarnings("unchecked")
		ObjectConnection<T> other = (ObjectConnection<T>) obj;
		if (!Objects.equals(value, other.get())) {
			return false;
		}
		if (!Objects.equals(getSource(), other.getSource())) {
			return false;
		}
		return Objects.equals(getTarget(), other.getTarget());
	}	
		
}
