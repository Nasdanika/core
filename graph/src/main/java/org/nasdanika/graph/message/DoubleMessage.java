package org.nasdanika.graph.message;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.HandlerType;

public class DoubleMessage extends Message<Double> {

	public DoubleMessage(HandlerType type, Message<Double> parent, Element target, Double value) {
		super(type, parent, target, value);
	}

	protected DoubleMessage(HandlerType type, Element target, Double value) {
		super(type, target, value);
	}

}
