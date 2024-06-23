package org.nasdanika.drawio.tests;

import org.nasdanika.graph.processor.RegistryEntry;

public class AliceBobProcessorRegistry {
	
	@RegistryEntry("label == 'Alice'")
	public AliceProcessor aliceProcessor;
	
	@RegistryEntry("label == 'Bob''s House'")
	public void setBobHouseProcessor(Runnable bobHouse) {
		bobHouse.run();
//		throw new NasdanikaException("Not so fast!");
	}

}
