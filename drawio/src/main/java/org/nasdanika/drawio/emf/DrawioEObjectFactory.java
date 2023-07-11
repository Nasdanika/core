package org.nasdanika.drawio.emf;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import org.eclipse.emf.ecore.EObject;
import org.jsoup.Jsoup;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.emf.ResourceSetPropertySourceEObjectFactory;
import org.nasdanika.graph.processor.emf.SemanticProcessor;

/**
 * @author Pavel
 *
 */
public abstract class DrawioEObjectFactory<T extends EObject, P extends SemanticProcessor<T>, R> extends ResourceSetPropertySourceEObjectFactory<T,P,R> {
	
	@Override
	protected List<CompletionStage<ProcessorEntryRecord<P>>> setRegistry(ProcessorConfig<P, R> config, P processor,	R registry, ProgressMonitor progressMonitor) {
		List<CompletionStage<ProcessorEntryRecord<P>>> ret = super.setRegistry(config, processor, registry, progressMonitor);
		Element element = config.getElement();
		if (element instanceof ModelElement) {
			Page linkedPage = ((ModelElement) element).getLinkedPage();
			if (linkedPage != null && !"false".equals(getPropertyValue(config.getElement(), getLinkPagePropertyName()))) {
				linkPage(config, processor, registry, getProcessorInfo(registry, linkedPage), progressMonitor);
			}
		}						
		return ret;
	}
	
	protected abstract ProcessorInfo<P, R> getProcessorInfo(R registry, Element element);

	protected String getLinkPagePropertyName() {
		return "link-page";
	}	
	
	/**
	 * Processes elements from the linked page. This implementation collects all page semantic elements without container and processes then as children of this element.
	 * @param config
	 * @param semanticElement
	 * @param registry
	 * @param likedPageInfo
	 */
	protected void linkPage(
			ProcessorConfig<P, R> config, 
			P processor, 
			R registry,
			ProcessorInfo<P, ?> processorInfo,
			ProgressMonitor progressMonitor) {
		ProcessorInfo<P, R> thisInfo = ProcessorInfo.of(config, processor);
		processorInfo.getConfig().getElement().accept(pe -> {
			ProcessorInfo<P, ?> re = getProcessorInfo(registry, pe);
			if (re.getProcessor() != null) {
				throw new UnsupportedOperationException("TODO: Complete refactoring to completion stages");
//				setParent((ProcessorConfig) re.getConfig(), re.getProcessor(), thisInfo, progressMonitor);
			}
		});		
	}
	
	@Override
	protected Context createElementContext(ProcessorConfig<P, R> config) {
		Context superContext = super.createElementContext(config);
		
		Context context = new Context() {

			@Override
			public Object get(String key) {
				if (Util.isBlank(key)) {
					return null;
				}
				
				// page name
				if (config.getElement() instanceof Page) {
					Page page = (Page) config.getElement();
					if ("name".equals(key)) {
						return page.getName();
					}
					if ("id".equals(key)) {
						return page.getId();
					}
				} else if (config.getElement() instanceof ModelElement) {
					ModelElement modelElement = (ModelElement) config.getElement();

					if ("id".equals(key)) {
						return modelElement.getId();
					}

					String label = modelElement.getLabel();
					if ("label".equals(key)) {
						return label;
					}

					if ("label-text".equals(key)) { // Plain text label							
						return (Util.isBlank(label) ? label : Jsoup.parse(label).text());
					}

					if ("link".equals(key)) {
						return modelElement.getLink();
					}

					if ("tooltip".equals(key)) {
						return modelElement.getTooltip();
					}
				}
				
				return null;
			}

			@Override
			public <ST> ST get(Class<ST> type) {
				return null;
			}
			
		};
		return context.compose(superContext);
	}
	
	@Override
	protected Object getElementQualifier(ProcessorConfig<P, R> config) {
		Map<String,Object> qualifier = new LinkedHashMap<>();
		Element element = config.getElement();
		qualifier.put("type", element.getClass().getCanonicalName());
		if (element instanceof Page) {
			qualifier.put("id", ((Page) element).getId());
		} else if (element instanceof ModelElement) {
			qualifier.put("id", ((ModelElement) element).getId());			
		} else {
			qualifier.put("hash", element.hashCode());
		}
		return qualifier;
	}
	
}
