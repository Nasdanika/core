package org.nasdanika.drawio.gen.section;

import java.util.function.BiConsumer;
import java.util.function.Function;

import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

public class DrawioProcessorFactory extends Configuration {
	
	protected CapabilityLoader capabilityLoader;
				
	@Processor(type = org.nasdanika.drawio.Document.class)
	public DocumentProcessor createDocumentProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		DocumentProcessor processor = new DocumentProcessor(this);
		return filter(config, processor, infoProvider, progressMonitor);
	}
				
	@Processor(type = org.nasdanika.drawio.Page.class)
	public PageProcessor  createPageProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		PageProcessor processor = new PageProcessor(this);
		return filter(config, processor, infoProvider, progressMonitor);
	}
				
	@Processor(type = org.nasdanika.drawio.Root.class)
	public RootProcessor  createRootProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		RootProcessor processor = new RootProcessor(this);
		return filter(config, processor, infoProvider, progressMonitor);
	}
				
	@Processor(type = org.nasdanika.drawio.Layer.class)
	public LayerProcessor createLayerProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		LayerProcessor processor = new LayerProcessor(this);
		return filter(config, processor, infoProvider, progressMonitor);
	}
	
	@Processor(type = org.nasdanika.drawio.Node.class)
	public NodeProcessor createNodeProcessor(
		NodeProcessorConfig<?,?> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		org.nasdanika.drawio.Node node = (org.nasdanika.drawio.Node) config.getElement();
		if (node.isTargetLink() && node.getLinkTarget() instanceof ModelElement) {
			return null;			
		}
		NodeProcessor processor = new NodeProcessor(this);
		return filter(config, processor, infoProvider, progressMonitor);
	}
	
	@Processor(type = org.nasdanika.drawio.Connection.class)
	public ConnectionProcessor createConnectionProcessor(
		ConnectionProcessorConfig<?,?> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		org.nasdanika.drawio.Connection connection = (org.nasdanika.drawio.Connection) config.getElement();
		if (connection.isTargetLink() && connection.getLinkTarget() instanceof ModelElement) {
			return null;			
		}
		
		ConnectionProcessor processor = new ConnectionProcessor(this);
		return filter(config, processor, infoProvider, progressMonitor);
	}
	
}
