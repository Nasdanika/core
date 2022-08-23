package org.nasdanika.graph.processor;

public interface ProcessorInfo<P> {
	
	ProcessorConfig<P> getConfig();
	
	P getProcessor();
	
	static <P> ProcessorInfo<P> of(ProcessorConfig<P> config, P processor) {
		return new ProcessorInfo<P>() {

			@Override
			public ProcessorConfig<P> getConfig() {
				return config;
			}

			@Override
			public P getProcessor() {
				return processor;
			}
		};
	}

}
