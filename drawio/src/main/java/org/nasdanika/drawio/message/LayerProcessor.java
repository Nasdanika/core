package org.nasdanika.drawio.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.RegistryEntry;

public abstract class LayerProcessor<V> extends BaseProcessor<Layer,V> {
	
	@RegistryEntry("#element.root == #this")
	public RootProcessor<V> rootProcessor;

	@ChildProcessors
	public Map<LayerElement, ProcessorInfo<LayerElementProcessor<LayerElement,V>>> childInfos;
	
	@Override
	public List<ElementMessage<?, V, ?>> processMessage(ElementMessage<?,V,?> message) {
		List<ElementMessage<?, V, ?>> ret = new ArrayList<>();
		
		Root root = rootProcessor.getElement();
		if (!message.hasSeen(root)) {
			V rootValue = rootValue(message.getValue(), root);
			if (rootValue != null) {
				ret.add(new ParentMessage<Root,V,RootProcessor<V>>(message, root, rootValue, rootProcessor));
			}
		}
				
		for (ProcessorInfo<LayerElementProcessor<LayerElement, V>> lepi: childInfos.values()) {
			LayerElementProcessor<LayerElement,V> layerElementProcessor = lepi.getProcessor();
			if (layerElementProcessor != null) {
				LayerElement layerElement = layerElementProcessor.getElement();
				if (!message.hasSeen(layerElement)) {
					V layerElementValue = layerElementValue(message.getValue(), layerElement);
					if (layerElementValue != null) {
						ret.add(new ChildMessage<LayerElement,V,LayerElementProcessor<LayerElement,V>>(message, layerElement, layerElementValue, layerElementProcessor));
					}
				}
			}
		}
		
		return ret;
	}

	protected abstract V rootValue(V messageValue, Root root);

	protected abstract V layerElementValue(V messageValue, LayerElement layerElement);

}
