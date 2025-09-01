package org.nasdanika.drawio.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Message;
import org.nasdanika.common.SectionReference;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.message.ChildMessage;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;

public class DocumentProcessor extends BaseProcessor<Document> {
	
	public DocumentProcessor(MessageProcessorFactory factory) {
		super(factory);
	}
	
	@ProcessorElement	
	@Override
	public void setElement(Document element) {
		super.setElement(element);
		if (element.getURI() == null) {
			setId("document");
		} else {
			setId(element.getURI().lastSegment());
		}
	}
	
	@Override
	public SectionReference createSectionReference() {
		SectionReference sectionReference = super.createSectionReference();
		// TODO - page if single page
		
		sectionReference.setTitle(this.toString());
		return sectionReference;
	}

	@ChildProcessors
	public Map<Page, ProcessorInfo<PageProcessor>> pageProcessors;
	
	@Override
	public List<Message<?>> processMessage(Message<?> message) {
		List<Message<?>> ret = new ArrayList<>();
		for (ProcessorInfo<PageProcessor> ppi: pageProcessors.values()) {
			PageProcessor pp = ppi.getProcessor();
			if (pp != null && !message.hasSeen(pp)) {
				ret.add(new ChildMessage(message, pp));
			}
		}
		
		return ret;
	}
	
	// TODO - logically merge with the page if there is only one page
	
//		List<Map.Entry<Page, ProcessorInfo<PageProcessor>>> topLevelPageProcessors = pageProcessors
//			.entrySet()
//			.stream()
//			.filter(ppe -> ppe.getValue().getProcessor().getReferrers().isEmpty())
//			.toList();
//		
//		if (topLevelPageProcessors.size() == 1) {
//			topLevelPageProcessors.forEach(ppe -> ppe.getValue().getProcessor().resolve(base, progressMonitor));
//		} else {
//			topLevelPageProcessors.forEach(ppe -> ppe.getValue().getProcessor().resolve(URI.createURI(ppe.getKey().getId()).appendSegment("").resolve(base), progressMonitor));			
//		}
	
	
//		List<Supplier<Collection<Label>>> topLevelPageProcessorSuppliers = pageProcessors
//				.values()				
//				.stream()
//				.filter(pp -> pp.getProcessor().getReferrers().isEmpty())
//				.map(ProcessorInfo::getProcessor)
//				.map(WidgetFactory::createLabelsSupplier)
//				.toList();
//		
//		if (topLevelPageProcessorSuppliers.isEmpty()) {
//			PageProcessor firstPageProcessor = pageProcessors.get(element.getPages().iterator().next()).getProcessor();
//			firstPageProcessor.isTopLevelPage = true; // Forcing
//			return firstPageProcessor.createLabelsSupplier();			
//		}
//		
//		if (topLevelPageProcessorSuppliers.size() == 1) {
//			return topLevelPageProcessorSuppliers.iterator().next();
//		}
//		
//		BinaryOperator<Collection<Label>> accumulator = (a, b) -> {
//			if (a == null || a.isEmpty()) {
//				return b == null ? Collections.emptyList() : b; 
//			}
//			
//			if (b == null || b.isEmpty()) {
//				return a;
//			}
//			
//			return Stream.concat(a.stream(), b.stream()).toList();
//		};
//		
//		Supplier<Collection<Label>> compoundSupplier = new ReducingListCompoundSupplier<Collection<Label>>(
//				"Document labels supplier",
//				accumulator,
//				topLevelPageProcessorSuppliers);
//		
//		return compoundSupplier;

}
