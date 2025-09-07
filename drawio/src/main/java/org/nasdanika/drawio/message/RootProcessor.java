package org.nasdanika.drawio.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.RegistryEntry;

public abstract class RootProcessor<V> extends BaseProcessor<Root,V> {
		
	@RegistryEntry("#element.model.page == #this")
	public PageProcessor<V> pageProcessor;

	@ChildProcessors
	public Map<Layer, ProcessorInfo<LayerProcessor<V>>> layerProcessorInfos;
	
	@Override
	public List<ElementMessage<?, V, ?>> processMessage(ElementMessage<?,V,?> message) {
		List<ElementMessage<?, V, ?>> ret = new ArrayList<>();
		
		Page page = pageProcessor.getElement();
		if (!message.hasSeen(page)) {
			V pageValue = pageValue(message.getValue(), page);
			if (pageValue != null) {
				ret.add(new ParentMessage<Page,V,PageProcessor<V>>(message, page, pageValue, pageProcessor));
			}
		}
				
		for (ProcessorInfo<LayerProcessor<V>> lpi: layerProcessorInfos.values()) {
			LayerProcessor<V> layerProcessor = lpi.getProcessor();
			if (layerProcessor != null) {
				Layer layer = layerProcessor.getElement();
				if (!message.hasSeen(layer)) {
					V layerValue = layerValue(message.getValue(), layer);
					if (layerValue != null) {
						ret.add(new ChildMessage<Layer,V,LayerProcessor<V>>(message, layer, layerValue, layerProcessor));
					}
				}
			}
		}
		
		return ret;
	}

	protected abstract V pageValue(V messageValue, Page page);

	protected abstract V layerValue(V messageValue, Layer layer);
	
}
