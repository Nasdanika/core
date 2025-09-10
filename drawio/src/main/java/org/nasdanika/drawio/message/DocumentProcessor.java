package org.nasdanika.drawio.message;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.message.ChildMessage;
import org.nasdanika.graph.message.ElementMessage;
import org.nasdanika.graph.processor.ProcessorInfo;

public abstract class DocumentProcessor<V> extends BaseProcessor<Document,V> {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ElementMessage<?, V, ?>> processMessage(ElementMessage<?,V,?> message) {
		List<ElementMessage<?, V, ?>> ret = new ArrayList<>();
		for (Page page: element.getPages()) {
			if (!message.hasSeen(page)) {
				ProcessorInfo<BaseProcessor<? extends Element, V>> pi = registry.get(page);
				if (pi != null && pi.getProcessor() != null) {
					V pageValue = pageValue(message.getValue(), page);
					if (pageValue != null) {
						ret.add(new ChildMessage<Page,V,PageProcessor<V>>(message, page, pageValue, (PageProcessor<V>) pi.getProcessor()));
					}
				}
			}
		}
		
		return ret;
	}

	protected abstract V pageValue(V messageValue, Page page);

}
