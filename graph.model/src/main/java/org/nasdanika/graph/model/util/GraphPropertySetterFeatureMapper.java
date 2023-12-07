package org.nasdanika.graph.model.util;

import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiConsumer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.FeatureMapper;
import org.nasdanika.common.Mapper;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.model.Document;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.drawio.model.Page;
import org.nasdanika.drawio.model.Root;
import org.nasdanika.drawio.model.util.PropertySetterFeatureMapper;
import org.nasdanika.graph.model.Graph;
import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelPackage;

public abstract class GraphPropertySetterFeatureMapper extends PropertySetterFeatureMapper<EObject, EObject> {
	
	protected GraphPropertySetterFeatureMapper() {
		super();
	}

	protected GraphPropertySetterFeatureMapper(Mapper<EObject, EObject> chain) {
		super(chain);
	}

	protected GraphPropertySetterFeatureMapper(
			Mapper<EObject, EObject> chain,
			FeatureMapper<EObject, EObject> defaulFeaturetMapper) {
		super(chain, defaulFeaturetMapper);
	}

	/**
	 * Indicates a top level page which shall be a child of document element
	 * @param page
	 * @return
	 */
	protected abstract boolean isTopLevelPage(Page page);
	
	protected abstract boolean isPageElement(org.nasdanika.drawio.model.ModelElement drawioModelElement);
	
	protected boolean isTopLevelPageElement(org.nasdanika.drawio.model.ModelElement drawioModelElement) {
		if (isPageElement(drawioModelElement)) {
			for (EObject eContainer = drawioModelElement.eContainer(); eContainer != null; eContainer = eContainer.eContainer()) {
				if (eContainer instanceof Page) {
					return isTopLevelPage((Page) eContainer);
				} 
			}			
		}
		return false;
	}
	
	@Override
	protected Setter<EObject, EObject> getFeatureSetter(
			EObject source, 
			ConfigType configType, 
			ConfigSubType configSubType,
			EStructuralFeature feature,
			BiConsumer<String, EObject> featureNameValidator,
			Setter<EObject, EObject> chain) {
		
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
						if (argument instanceof Page && isTopLevelPage((Page) argument)) {
							Page page = (Page) argument;
							if (argumentValue == null) {
								Root root = page.getModel().getRoot();
								EObject rootValue = registry.get(root);
								if (feature.getEType().isInstance(rootValue) && !graph.getElements().contains(rootValue)) {
									graph.getElements().add((GraphElement) rootValue);
								}
							} else if (feature.getEType().isInstance(argumentValue) && !graph.getElements().contains(argumentValue)) {
								graph.getElements().add((GraphElement) argumentValue);
							}
						} 
						
						if (argument instanceof ModelElement
								&& argumentValue != null
								&& argumentValue.eContainer() == null // Taking only non-contained objects
								&& isTopLevelPageElement((ModelElement) argument)
								&& feature.getEType().isInstance(argumentValue)
								&& !graph.getElements().contains(argumentValue)) {
										
							graph.getElements().add((GraphElement) argumentValue);
						}
					}
					
					return true;
				}
			};
		}
		
		return super.getFeatureSetter(
				source, 
				configType, 
				configSubType, 
				feature, 
				featureNameValidator,
				chain);
	}
	
}
