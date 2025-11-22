package org.nasdanika.graph.tests;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.ObjectElement;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.Pipe;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.Synapse;

public class TestProcessorConfig {
	
	@Test
	public void testPipe() {
		Pipe<Consumer<String>, Consumer<String>> pipe = Pipe.create();
		
		Synapse<Consumer<String>, Consumer<String>> sourceSynapse = pipe.getSource();
		sourceSynapse.getEndpoint().thenAccept(ep -> ep.accept("Hello from source"));
		
		Synapse<Consumer<String>, Consumer<String>> targetSynapse = pipe.getTarget();
		targetSynapse.setHandler(str -> System.out.println("[Target] " + str));		
		targetSynapse.getEndpoint().thenAccept(ep -> ep.accept("Hello from target"));
		
		sourceSynapse.setHandler(str -> System.out.println("[Source] " + str));		
	}	
	
	@Test
	public void testClientHandlerAndEndpoint() {
		ObjectElement<String> element = new ObjectElement<String>("Hello");				
		ProcessorConfigFactory<Supplier<String>, Supplier<String>> processorConfigFactory = new NopEndpointProcessorConfigFactory<Supplier<String>>();		
		Transformer<Element,ProcessorConfig<Supplier<String>,Supplier<String>>> processorConfigTransformer = new Transformer<>(processorConfigFactory);				
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorConfig<Supplier<String>,Supplier<String>>> configs = processorConfigTransformer.transform(Collections.singleton(element), false, progressMonitor);
		System.out.println("Configs: " + configs.size());
			
		ProcessorFactory<Supplier<String>,Supplier<String>,Object> processorFactory = new ProcessorFactory<Supplier<String>,Supplier<String>,Object>() {
			
			@Override
			protected ProcessorInfo<Supplier<String>, Supplier<String>, Object> createProcessor(
					ProcessorConfig<Supplier<String>, Supplier<String>> config, 
					boolean parallel,
					BiConsumer<Element, BiConsumer<ProcessorInfo<Supplier<String>, Supplier<String>, Object>, ProgressMonitor>> infoProvider,
					Consumer<CompletionStage<?>> endpointWiringStageConsumer, ProgressMonitor progressMonitor) {
				
				config.setProcessorSynapseConsumer((cs,ps) -> {
					ps.getEndpoint().thenAccept(supplier -> ps.setHandler(() -> "{%s} through the processor ".formatted(cs) + supplier.get())); 
				});
				
				return super.createProcessor(config, parallel, infoProvider, endpointWiringStageConsumer, progressMonitor);
			}
						
		};
		
		Map<Element, ProcessorInfo<Supplier<String>, Supplier<String>,Object>> processors = processorFactory.createProcessors(configs.values(), false, progressMonitor);
		
		for (Entry<Element, ProcessorInfo<Supplier<String>, Supplier<String>, Object>> pe: processors.entrySet()) {
			Synapse<Supplier<String>, Supplier<String>> clientSynapse = pe.getValue().getClientSynapse("Test client");
			clientSynapse.setHandler(() -> "From test client");
			clientSynapse.getEndpoint().thenAccept(supplier -> System.out.println("[Test client] " + supplier.get()));						
		}
	}	

}
