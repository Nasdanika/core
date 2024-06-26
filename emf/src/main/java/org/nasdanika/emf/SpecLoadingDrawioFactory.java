package org.nasdanika.emf;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.emf.SemanticDrawioFactory;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;
import org.nasdanika.persistence.ObjectLoader;

/**
 * Loads specification from spec or spec-ref properties.
 * @param <D>
 * @param <S>
 */
public abstract class SpecLoadingDrawioFactory<S extends EObject> extends SemanticDrawioFactory<S> {
	
	private ResourceSet resourceSet;

	public SpecLoadingDrawioFactory(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	public ResourceSet getResourceSet() {
		return resourceSet;
	}
	
	protected String getSpecPropertyName() {
		return getPropertyNamespace() + "spec";
	}
	
	protected String getSpecRefPropertyName() {
		return getPropertyNamespace() + "spec-ref";
	}
	
	@Override
	protected void configureSemanticElement(
			EObject eObj, 
			S semanticElement, 
			Map<EObject, EObject> registry,
			boolean isPrototype,
			ProgressMonitor progressMonitor) {
		
		super.configureSemanticElement(
				eObj,
				semanticElement,
				registry,
				isPrototype,
				progressMonitor);
		
		EObjectLoader eObjectLoader = new EObjectLoader((ObjectLoader) null) {
		
			@Override
			public ResolutionResult resolveEClass(String type) {
				EClass eClass = (EClass) SpecLoadingDrawioFactory.this.getType(type, eObj);
				return new ResolutionResult(eClass, null);
			}
			
			@Override
			public ResourceSet getResourceSet() {
				return SpecLoadingDrawioFactory.this.getResourceSet();
			}
			
		};
		
		Marked marked = asMarked(eObj);		
		
		URI baseURI = getBaseURI(eObj);		
		String spn = getSpecPropertyName();
		if (!Util.isBlank(spn)) {
			String specStr = getProperty(eObj, spn);
			if (!Util.isBlank(specStr)) {
				eObjectLoader.loadYaml(
						specStr, 
						semanticElement, 
						baseURI, 
						null, 
						marked == null ? Collections.emptyList() : marked.getMarkers(), 
						progressMonitor);
			}			
		}
		
		String srpn = getSpecRefPropertyName();
		if (!Util.isBlank(srpn)) {
			String ref = getProperty(eObj, srpn);
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
					throw new ConfigurationException("Error loading spec from " + refURI, e, marked);
				}
			}
		}
	}
	
	/**
	 * Override to de-dup epackages if needed.
	 * @param ePackage
	 * @return
	 */
	protected String getEPackageName(EPackage ePackage) {
		return NcoreUtil.getNasdanikaAnnotationDetail(ePackage, EObjectLoader.LOAD_KEY, ePackage.getName());
	}
	
	/**
	 * Returns a map with registered packages from resource set.
	 */
	@Override
	protected Map<String, EPackage> getEPackages() {
		Map<String, EPackage> ret = new LinkedHashMap<>();
		for (Object ep: resourceSet.getPackageRegistry().values()) {
			EPackage ePackage = (EPackage) ep;
			ret.put(getEPackageName(ePackage), ePackage);
		}
		return ret;
	}
	
}
