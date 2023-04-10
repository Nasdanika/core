package org.nasdanika.graph.processor;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

public interface ProcessorInfo<P,R> {
	
	ProcessorConfig<P,R> getConfig();
	
	P getProcessor();
	
	/**
	 * @return Wiring failures. 
	 */
	Collection<Throwable> getFailures();
	
	static <P,R> ProcessorInfo<P,R> of(ProcessorConfig<P,R> config, P processor, Supplier<Collection<Throwable>> failuresSupplier) {
		
		return new ProcessorInfo<P,R>() {

			@Override
			public ProcessorConfig<P,R> getConfig() {
				return config;
			}

			@Override
			public P getProcessor() {
				return processor;
			}
			
			@Override
			public Collection<Throwable> getFailures() {
				return failuresSupplier == null ? Collections.emptyList() : failuresSupplier.get();
			}
			
		};
	}

}
