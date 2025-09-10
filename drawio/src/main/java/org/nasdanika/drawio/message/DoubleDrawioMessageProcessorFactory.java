package org.nasdanika.drawio.message;

import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;

/**
 * Message factory passing double values with default implementations for sorting diagram elements
 * in the order of dependency
 */
public class DoubleDrawioMessageProcessorFactory extends DrawioMessageProcessorFactory<Double> {
	
	/**
	 * parent message value is multiplied by this method return value before it is "divided" between child messages.
	 * @param element
	 * @return 
	 */
	protected double getElementCoefficient(Element element) {
		return 0.3;
	}

	@Override
	protected Double linkTargetValue(Double messageValue, ModelElement referrer, LinkTarget linkTarget) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double referrerValue(Double messageValue, LinkTarget linkTarget, ModelElement referrer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double childValue(Double messageValue, Element parent, Element child) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double sourceValue(Double messageValue, Node node, Connection outgoingConnection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double targetValue(Double messageValue, Node node, Connection incomingConnection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double incomingValue(Double messageValue, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double outgoingValue(Double messageValue, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

}
