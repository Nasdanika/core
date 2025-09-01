package org.nasdanika.drawio.gen.section;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Section;
import org.nasdanika.common.Transformer;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;

import reactor.core.publisher.Flux;

/**
 * Generates {@link Section}s with descriptions of diagram elements.
 */
public class DrawioSectionGenerator extends Configuration {
		
	public DrawioSectionGenerator() {
	}
	
	@SuppressWarnings("unchecked")
	public Flux<Section> creatSectionsAsync(Document document, ProgressMonitor progressMonitor) {
		NopEndpointProcessorConfigFactory<BaseProcessor<?>> processorConfigFactory = new NopEndpointProcessorConfigFactory<BaseProcessor<?>>() {
			
			@Override
			protected boolean isPassThrough(Connection incomingConnection) {
				return false;
			}
			
		};
		
		Transformer<Element,ProcessorConfig> processorConfigTransformer = new Transformer<>(processorConfigFactory);				
		
		Collection<Element> elements = new ArrayList<>();
		Consumer<org.nasdanika.drawio.Element> consumer = org.nasdanika.drawio.Util.withLinkTargets(elements::add, ConnectionBase.SOURCE);
		document.accept(consumer, null);
		Map<Element, ProcessorConfig> configs = processorConfigTransformer.transform(elements, false, progressMonitor);
		
		SectionProcessorFactory processorFactory = createProcessorFactory(progressMonitor);
		ReflectiveProcessorFactoryProvider<BaseProcessor<?>, BaseProcessor<?>, BaseProcessor<?>> rpfp = new ReflectiveProcessorFactoryProvider<>(processorFactory);
		Map<Element, ProcessorInfo<BaseProcessor<?>>> processors = rpfp.getFactory().createProcessors(configs.values(), false, progressMonitor);
		
		processors
			.keySet()
			.stream()
			.filter(ModelElement.class::isInstance)
			.map(ModelElement.class::cast)
			.filter(ModelElement::isTargetLink)
			.map(DrawioSectionGenerator::getLinkTargetRecursive)
			.forEach(entry ->  ((LinkTargetProcessor<LinkTarget>) processors.get(entry.getValue()).getProcessor()).addReferrer(entry.getKey()));
		
		processors
			.values()
			.stream()
			.map(ProcessorInfo::getProcessor)
			.filter(Objects::nonNull)
			.forEach(BaseProcessor::sendMessages);
		
//		
//		List<Map.Entry<Element, ProcessorInfo<BaseProcessor<?>>>> sortedProcessors = new ArrayList<>(); 
//		processors.entrySet()
//				.stream()
//				.filter(e -> e.getValue() != null && e.getValue().getProcessor() != null && !e.getValue().getProcessor().isAggregated())
//				.forEach(sortedProcessors::add);
//		
//		sortedProcessors.sort((a,b) -> a.getValue().getProcessor().compareTo(b.getValue().getProcessor()));
//		
//		
//		Set<String> ids = new HashSet<>();
//		sortedProcessors.forEach(e -> e.getValue().getProcessor().dedupIds(ids::add));
//		
//		return Flux.fromIterable(sortedProcessors)
//			.flatMap(e -> e.getValue().getProcessor().createSectionAsync());
		
		return null; // TODO
	}

	protected SectionProcessorFactory createProcessorFactory(ProgressMonitor progressMonitor) {
		return new SectionProcessorFactory() {
			
		};
	}

	private static Map.Entry<ModelElement, LinkTarget> getLinkTargetRecursive(ModelElement source) {				
		// Preventing infinite loops
		HashSet<ModelElement> tracker = new HashSet<ModelElement>();
		ModelElement modelElement = source; 
		while (tracker.add(modelElement) && modelElement.isTargetLink() && modelElement.getLinkTarget() instanceof ModelElement) { 
			modelElement = (ModelElement) modelElement.getLinkTarget();			
		}
		
		if (source != modelElement) {
			return Map.entry(source, modelElement); // Not going to pages
		}
		
		if (source.isTargetLink()) {
			return Map.entry(source, source.getLinkTarget());
		}
		
		return null;
	}
				
}
