package org.nasdanika.emf.persistence;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.emf.DrawioResourceFactory;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;

/**
 * This factory overrides configureSemanticElement() and injects name, description, and markers.
 * @author Pavel
 *
 * @param <T>
 */
public abstract class NcoreDrawioResourceFactory<T extends EObject> extends DrawioResourceFactory<T> {
	
	protected MarkerFactory getMarkerFactory() {
		return MarkerFactory.INSTANCE;
	}
		
	protected String getMarkerPosition(Element element) {
		if (element instanceof Page) {
			return "name: " + ((Page) element).getName() + ", id: " + ((Page) element).getId();
		} else if (element instanceof ModelElement) {
			StringBuilder positionBuilder = new StringBuilder();
			ModelElement modelElement = (ModelElement) element;
			Page page = modelElement.getModel().getPage();
			positionBuilder.append("page-name: " + page.getName() + ", page-id: " + page.getId());
			String label = modelElement.getLabel();
			if (!Util.isBlank(label)) {
				positionBuilder.append(", label: "+ label);
			}
			String id = modelElement.getId();
			if (!Util.isBlank(id)) {
				positionBuilder.append(", id:" + id);
			}
			return positionBuilder.toString();
		}		
		return null;
	}	
	
	@Override
	protected T configureSemanticElement(
			ProcessorConfig<T> config, 
			T semanticElement, 
			Resource resource,
			ProgressMonitor progressMonitor) {
		super.configureSemanticElement(config, semanticElement, resource, progressMonitor);
			
		Element element = config.getElement();
		URI uri = resource.getURI();
		if (uri != null && semanticElement instanceof Marked) {
			org.nasdanika.ncore.Marker marker = getMarkerFactory().createMarker(uri.toString(), progressMonitor);
			marker.setPosition(getMarkerPosition(element));
			((Marked) semanticElement).getMarkers().add(marker);
		}
		
		if (element instanceof ModelElement) {
			ModelElement modelElement = (ModelElement) element;
			String tooltip = modelElement.getTooltip();
			if (!Util.isBlank(tooltip) && semanticElement instanceof org.nasdanika.ncore.ModelElement && !semanticElement.eIsSet(NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION)) {
				((org.nasdanika.ncore.ModelElement) semanticElement).setDescription(tooltip);				
			}
			
			String label = modelElement.getLabel();
			if (!Util.isBlank(label) && semanticElement instanceof NamedElement && !semanticElement.eIsSet(NcorePackage.Literals.NAMED_ELEMENT__NAME)) {
				((NamedElement) semanticElement).setName(label);				
			}	
			
			if (semanticElement instanceof org.nasdanika.ncore.ModelElement) {
				String semanticUuid = ((org.nasdanika.ncore.ModelElement) semanticElement).getUuid();
				if (!Util.isBlank(semanticUuid)) {
					modelElement.setProperty("semantic-uuid", semanticUuid);
				}
			}
		}
		
		return semanticElement;		
	}

}
