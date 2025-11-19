package org.nasdanika.graph.tests;

import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.graph.processor.ProcessorInfo;

public class ReflectiveProcessorFactory {
	
	@Processor(type = EObjectNode.class)
	public Object createEObjectNodeProcessor(
			NodeProcessorConfig<Function<Element,Element>,Function<Element,Element>> config, 
			boolean parallel, 
			Function<Element,CompletionStage<ProcessorInfo<Object,Object,Object>>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Function<ProgressMonitor, Object> next,
			ProgressMonitor progressMonitor) {

		return null;
	}

}
