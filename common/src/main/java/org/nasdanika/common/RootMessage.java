package org.nasdanika.common;

/**
 * Root message without a parent.
 */
public class RootMessage<T,V> extends Message<T,V> {

	public RootMessage(T target, V value) {
		super(target, value);
	}

}
