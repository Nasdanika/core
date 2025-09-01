package org.nasdanika.common;

/**
 * Root message without a parent.
 */
public class RootDoubleMessage<T> extends DoubleMessage<T> {

	public RootDoubleMessage(T target, Double value) {
		super(target, value);
	}

}
