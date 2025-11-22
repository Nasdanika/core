package org.nasdanika.drawio.tests;

import java.util.function.Function;

import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.RegistryEntry;

public class AliceBobProcessorRegistry {
	
	@RegistryEntry("label == 'Alice'")
	public AliceProcessor aliceProcessor;
	
	
	@RegistryEntry("label == 'Bob'")
	public ProcessorInfo<Function<String,String>,Function<String,String>,BobProcessor> bobInfo;	
	
	@RegistryEntry("label == 'Bob''s House'")
	public void setBobHouseProcessor(Runnable bobHouse) {
		bobHouse.run();
//		throw new NasdanikaException("Not so fast!");
	}

}
