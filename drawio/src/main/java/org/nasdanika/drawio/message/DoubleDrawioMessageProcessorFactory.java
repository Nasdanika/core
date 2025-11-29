package org.nasdanika.drawio.message;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.message.Message;

/**
 * Message factory passing double values with default implementations for sorting diagram elements
 * in the order of dependency
 */
public class DoubleDrawioMessageProcessorFactory<K> extends DrawioMessageProcessorFactory<Double,K> {
	
	/**
	 * parent message value is multiplied by this method return value before it is "divided" between child messages.
	 * @param element
	 * @return 
	 */
	protected double getElementCoefficient(Element element) {
		return 0.3;
	}

	@Override
	protected Double linkTargetValue(Element element, Message<Double> message, K clientKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double referrerValue(Element element, Message<Double> message, K clientKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double parentValue(Element element, Message<Double> message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double childValue(Element element, Message<Double> message, Element child) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double clientValue(Element element, Message<Double> message, K clientKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double sourceValue(Connection connection, Message<Double> message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double targetValue(Connection connection, Message<Double> message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double outgoingValue(Node node, Message<Double> message, Connection outgoingConnection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Double incomingValue(Node node, Message<Double> message, Connection incomingConnection) {
		// TODO Auto-generated method stub
		return null;
	}

}
