package org.nasdanika.drawio.emf;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.jsoup.Jsoup;
import org.nasdanika.common.Context;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.emf.ResourceSetPropertySourceEObjectFactory;

/**
 * @author Pavel
 *
 */
public abstract class DrawioEObjectFactory<T extends EObject> extends ResourceSetPropertySourceEObjectFactory<T> {
	
	@Override
	protected void setRegistry(ProcessorConfig<T> config, T semanticElement, Map<Element, ProcessorInfo<T>> registry) {
		super.setRegistry(config, semanticElement, registry);
		Element element = config.getElement();
		if (element instanceof ModelElement) {
			Page linkedPage = ((ModelElement) element).getLinkedPage();
			if (linkedPage != null && !"false".equals(getPropertyValue(config.getElement(), getLinkPagePropertyName()))) {
				linkPage(config, semanticElement, registry, registry.get(linkedPage));
			}
		}				
	}
	

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
	protected void linkPage(ProcessorConfig<T> config, T semanticElement, Map<Element, ProcessorInfo<T>> registry, ProcessorInfo<T> linkedPageInfo) {
		ProcessorInfo<T> thisInfo = ProcessorInfo.of(config, semanticElement);
		linkedPageInfo.getConfig().getElement().accept(pe -> {
			ProcessorInfo<T> re = registry.get(pe);
			if (re.getProcessor() != null && re.getProcessor().eContainer() == null) {
				setParent(re.getConfig(), re.getProcessor(), thisInfo);
			}
		});		
	}
	
// ---
		
	@Override
	protected Context createElementContext(ProcessorConfig<T> config) {
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
	
	
}
