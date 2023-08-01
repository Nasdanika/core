package org.nasdanika.graph;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * A node which holds a value. Implements value-based hashCode() and equals(). 
 * @param <T>
 */
public class ObjectNode<T> extends SimpleNode implements Supplier<T> {
	
	private T value;

	public ObjectNode(T value) {
		this.value = value;
	}
	
	@Override
	public T get() {
		return value;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getClass(), value);
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
		ObjectNode<T> other = (ObjectNode<T>) obj;
		return Objects.equals(value, other.value);
	}

}
