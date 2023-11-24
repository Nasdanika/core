package org.nasdanika.graph.model.util;

import java.util.LinkedList;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.model.Document;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.drawio.model.Page;
import org.nasdanika.drawio.model.util.PropertySetterFeatureMapper;
import org.nasdanika.graph.model.Graph;
import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelPackage;

public abstract class GraphPropertySetterFeatureMapper extends PropertySetterFeatureMapper<EObject, EObject> {
	
	/**
	 * Indicates a top level page which shall be a child of document element
	 * @param page
	 * @return
	 */
	protected abstract boolean isTopLevelPage(Page page);
	
	protected abstract boolean isPageElement(org.nasdanika.drawio.model.ModelElement drawioModelElement);
	
	@Override
	protected Setter<EObject, EObject> getFeatureSetter(
			EObject source, 
			ConfigType configType, 
			ConfigSubType configSubType,
			EStructuralFeature feature) {
		
		if (configType == ConfigType.container  
				&& configSubType == ConfigSubType.self
				&& source instanceof Document 
				&& ModelPackage.Literals.GRAPH__ELEMENTS == feature) {
			
			return new Setter<EObject, EObject>() {

				@Override
				public boolean setFeature(
						EObject eObj, 
						EStructuralFeature feature,
						EObject argument,
						EObject argumentValue,
						LinkedList<EObject> sourcePath, 
						Map<EObject, EObject> registry, 
						ProgressMonitor progressMonitor) {
					
					if (eObj instanceof Graph && sourcePath != null) {
						@SuppressWarnings("unchecked")
						Graph<GraphElement> graph = (Graph<GraphElement>) eObj;
						if (argument instanceof Page 
								&& isTopLevelPage((Page) argument)
								&& feature.getEType().isInstance(argumentValue)
								&& !graph.getElements().contains(argumentValue)) {
							
							graph.getElements().add((GraphElement) argumentValue);
						} 
						
						if (argument instanceof ModelElement 
								&& isPageElement((ModelElement) argument)
								&& feature.getEType().isInstance(argumentValue)
								&& !graph.getElements().contains(argumentValue)) {
										
							graph.getElements().add((GraphElement) argumentValue);
						}
					}
					
					return true;
				}
			};
		}
		
		return super.getFeatureSetter(source, configType, configSubType, feature);
	}
	
}
