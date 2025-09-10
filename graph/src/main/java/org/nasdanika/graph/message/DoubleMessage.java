package org.nasdanika.graph.message;

public class DoubleMessage<T> extends Message<T,Double> {

	public DoubleMessage(Message<?, Double> parent, T target, Double value) {
		super(parent, target, value);
	}

	protected DoubleMessage(T target, Double value) {
		super(target, value);
	}

}
