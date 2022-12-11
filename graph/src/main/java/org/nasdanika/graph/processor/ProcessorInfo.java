package org.nasdanika.graph.processor;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

public interface ProcessorInfo<P> {
	
	ProcessorConfig<P> getConfig();
	
	P getProcessor();
	
	/**
	 * @return Wiring failures. 
	 */
	Collection<Throwable> getFailures();
	
	static <P> ProcessorInfo<P> of(ProcessorConfig<P> config, P processor, Supplier<Collection<Throwable>> failuresSupplier) {
		
		return new ProcessorInfo<P>() {

			@Override
			public ProcessorConfig<P> getConfig() {
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
