package org.nasdanika.emf;

import java.net.URL;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.emf.SemanticDrawioFactory;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.ObjectLoader;

/**
 * Loads specification from spec or spec-ref properties.
 * @param <D>
 * @param <S>
 */
public abstract class SpecLoadingDrawioFactory<S extends EObject> extends SemanticDrawioFactory<S> {
	
	protected String getSpecPropertyName() {
		return getPropertyNamespace() + "spec";
	}
	
	protected String getSpecRefPropertyName() {
		return getPropertyNamespace() + "spec-ref";
	}
	
	@Override
	protected void configureSemanticElement(
			ModelElement modelElement, 
			S semanticElement, 
			Map<EObject, EObject> registry,
			boolean isPrototype,
			ProgressMonitor progressMonitor) {
		
		super.configureSemanticElement(
				modelElement,
				semanticElement,
				registry,
				isPrototype,
				progressMonitor);
		
		EObjectLoader eObjectLoader = new EObjectLoader((ObjectLoader) null) {
		
			@Override
			public ResolutionResult resolveEClass(String type) {
				EClass eClass = (EClass) SpecLoadingDrawioFactory.this.getType(type, modelElement);
				return new ResolutionResult(eClass, null);
			}
			
		};
		
		URI baseURI = getBaseURI(modelElement);		
		String spn = getSpecPropertyName();
		if (!Util.isBlank(spn)) {
			String specStr = modelElement.getProperties().get(spn);
			if (!Util.isBlank(specStr)) {
				eObjectLoader.loadYaml(
						specStr, 
						semanticElement, 
						baseURI, 
						null, 
						modelElement.getMarkers(), 
						progressMonitor);
			}			
		}
		
		String srpn = getSpecRefPropertyName();
		if (!Util.isBlank(srpn)) {
			String ref = modelElement.getProperties().get(srpn);
			if (!Util.isBlank(ref)) {
				URI refURI = URI.createURI(ref);
				if (baseURI != null && !baseURI.isRelative()) {
					refURI = refURI.resolve(baseURI);
				}				
				try {
					eObjectLoader.loadYaml(
							new URL(refURI.toString()),
							semanticElement, 
							null, 
							progressMonitor);
				} catch (Exception e) {
					throw new ConfigurationException("Error loading spec from " + refURI, e, modelElement);
				}
			}
		}
	}
	
}
