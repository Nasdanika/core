package org.nasdanika.common;

import java.util.function.Supplier;

/**
 * A helper class for exchanging data between anonymous classes.
 * @author Pavel
 *
 * @param <T>
 */
public class Reference<T> implements Supplier<T> {
	
	private T value;

	public Reference() {
		
	}

	public Reference(T value) {
		this.value = value;
	}

	@Override
	public T get() {
		return value;
	}
	
	public void set(T value) {
		this.value = value;
	}

}
