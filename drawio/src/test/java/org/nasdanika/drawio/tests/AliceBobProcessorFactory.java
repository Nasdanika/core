package org.nasdanika.drawio.tests;

import org.nasdanika.drawio.processor.ElementProcessor;

public class AliceBobProcessorFactory {
	
	@ElementProcessor
	public BobProcessor bob() {
		return new BobProcessor();
	}

}
