package org.nasdanika.graph.model.util;

import java.util.LinkedList;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.model.Document;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.drawio.model.Page;
import org.nasdanika.drawio.model.util.PropertyFeatureSetterContentMapper;
import org.nasdanika.graph.model.Graph;
import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelPackage;

public class GraphPropertyFeatureSetterContentMapper extends PropertyFeatureSetterContentMapper<EObject, EObject> {
	
	/**
	 * Indicates a top level page which shall be a child of document element
	 * @param page
	 * @return
	 */
	protected boolean isTopLevelPage(Page page) {
		return page.getLinks().isEmpty();
	}
	
	
	protected String getPageElementProperty() {
		return "page-element";
	}
	
	protected boolean isPageElement(org.nasdanika.drawio.model.ModelElement drawioModelElement) {		
		String pageElementProperty = getPageElementProperty();
		if (Util.isBlank(pageElementProperty)) {
			return false;
		}
		
		String pev = drawioModelElement.getProperties().get(pageElementProperty);
		return !Util.isBlank(pev) && "true".equals(pev.trim());
	}
	
	@Override
	protected FeatureSetter<EObject, EObject> getFeatureSetter(
			EObject source, 
			boolean isContents, 
			boolean isArgument,
			EStructuralFeature feature) {
		
		if (!isContents 
				&& !isArgument
				&& source instanceof Document 
				&& ModelPackage.Literals.GRAPH__ELEMENTS == feature) {
			
			return new FeatureSetter<EObject, EObject>() {

				@Override
				public boolean setFeature(
						EObject eObj, 
						EStructuralFeature feature, 
						EObject value,
						LinkedList<EObject> sourcePath, 
						Map<EObject, EObject> registry,
						ProgressMonitor progressMonitor) {
					
					if (eObj instanceof Graph) {
						@SuppressWarnings("unchecked")
						Graph<GraphElement> graph = (Graph<GraphElement>) eObj;
						EObject sourceChild = sourcePath.getLast();	
						if (sourceChild instanceof Page 
								&& isTopLevelPage((Page) sourceChild)
								&& feature.getEType().isInstance(value)
								&& !graph.getElements().contains(value)) {
							
							graph.getElements().add((GraphElement) value);
						} 
						
						if (sourceChild instanceof ModelElement 
								&& isPageElement((ModelElement) sourceChild)
								&& feature.getEType().isInstance(value)
								&& !graph.getElements().contains(value)) {
										
							graph.getElements().add((GraphElement) value);
						}
					}					
					
					return true;
				}
			};
		}
		
		return super.getFeatureSetter(source, isContents, isArgument, feature);
	}

}
