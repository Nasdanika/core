package org.nasdanika.graph.message;

/**
 * Root message without a parent, type and target.
 */
public class RootDoubleMessage extends DoubleMessage {

	public RootDoubleMessage(Double value) {
		super(null, null, null, value);
	}

}
