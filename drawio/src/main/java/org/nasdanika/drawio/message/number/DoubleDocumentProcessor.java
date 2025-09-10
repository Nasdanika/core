package org.nasdanika.drawio.message.number;

import org.nasdanika.drawio.message.DocumentProcessor;

public abstract class DoubleDocumentProcessor extends DocumentProcessor<Double> {
	
	private DoubleMessageProcessorFactory factory;

	public DoubleDocumentProcessor(DoubleMessageProcessorFactory factory) {
		this.factory = factory;
	}

}
