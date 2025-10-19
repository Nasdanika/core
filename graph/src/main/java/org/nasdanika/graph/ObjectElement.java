package org.nasdanika.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * An element which holds a value and children. Implements value-based hashCode() and equals(). 
 * @param <T>
 */
public class ObjectElement<T> implements Element, Supplier<T> {
	
	private T value;
	private Supplier<Collection<? extends Element>> childrenSupplier;

	public ObjectElement(T value) {
		this(value, Collections.emptyList());
	}

	public ObjectElement(T value, Collection<? extends Element> children) {
		this(value, () -> children == null ? Collections.emptyList() : children);
	}

	public ObjectElement(T value, Supplier<Collection<? extends Element>> childrenSupplier) {
		this.value = value;
		this.childrenSupplier = childrenSupplier == null ? () -> Collections.emptyList() : childrenSupplier;
	}
	
	@Override
	public Collection<? extends Element> getChildren() {
		Collection<? extends Element> children = childrenSupplier.get();
		return children == null ? Collections.emptyList() : children;
	}
	
	@Override
	public T get() {
		return value;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getClass(), value, getChildren());
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
		ObjectElement<T> other = (ObjectElement<T>) obj;
		return Objects.equals(value, other.value) && Objects.equals(getChildren(), other.getChildren());
	}

}
