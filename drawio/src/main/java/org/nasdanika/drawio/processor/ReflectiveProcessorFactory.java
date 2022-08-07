package org.nasdanika.drawio.processor;

import org.nasdanika.drawio.Element;

/**
 * Creates processor and wires hanlders and endpoints using annotations. 
 * @author Pavel
 *
 * @param <P>
 * @param <T>
 * @param <R>
 * @param <U>
 * @param <S>
 */
public interface ReflectiveProcessorFactory<P, T, R, U, S> extends ProcessorFactory<P, T, R, U, S> {
	
	@Override
	default ElementProcessorInfo<P> createProcessor(ElementProcessorConfig<P> config) {
		// Wire config to target using reflection.
		
		
		// TODO Auto-generated method stub
		return null; 
	}
	
	// Create processors - use reflection

}
