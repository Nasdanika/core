package org.nasdanika.drawio.emf;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.jsoup.Jsoup;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.emf.ResourceSetPropertySourceEObjectFactory;
import org.nasdanika.graph.processor.emf.SemanticProcessor;

/**
 * @author Pavel
 *
 */
public abstract class DrawioEObjectFactory<T extends EObject, P extends SemanticProcessor<T>> extends ResourceSetPropertySourceEObjectFactory<T,P> {
	
	@Override
	protected void setRegistry(
			ProcessorConfig config, 
			P processor,
			Function<Element, CompletionStage<P>> processorProvider, 
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {
		super.setRegistry(config, processor, processorProvider, stageConsumer, progressMonitor);
		Element element = config.getElement();
		if (element instanceof ModelElement) {
			Page linkedPage = ((ModelElement) element).getLinkedPage();
			if (linkedPage != null && !"false".equals(getPropertyValue(config.getElement(), getLinkPagePropertyName()))) {
				linkPage(config, processor, linkedPage, processorProvider, stageConsumer, progressMonitor);
			}
		}				
	}

	protected String getLinkPagePropertyName() {
		return "link-page";
	}	
	
	/**
	 * Processes elements from the linked page. This implementation collects all page semantic elements without container and processes them as children of this element.
	 * @param config
	 * @param semanticElement
	 * @param registry
	 * @param likedPageInfo
	 */
	protected void linkPage(
			ProcessorConfig config, 
			P processor, 
			Page linkedPage,
			Function<Element, CompletionStage<P>> processorProvider, 
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {
		linkedPage.accept(pe -> {
			CompletionStage<P> pecs = processorProvider.apply(pe);
			if (pecs != null) {
				stageConsumer.accept(pecs.thenAccept(prc -> {
					setParent(config, processor, config.getRegistry().get(pe), prc, progressMonitor);
				}));
			}
		});		
	}
	
	@Override
	protected Context createElementContext(ProcessorConfig config) {
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
	protected Object getElementQualifier(ProcessorConfig config) {
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
