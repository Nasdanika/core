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
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.emf.AbstractDrawioFactory;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.mapping.ContentProvider;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;
import org.nasdanika.persistence.ObjectLoader;

/**
 * Loads target configuration from configuration or configuration-ref properties.
 * @param <D>
 * @param <S>
 */
public abstract class ConfigurationLoadingDrawioFactory<T extends EObject> extends AbstractDrawioFactory<T> {
	
	private static final String CONFIGURATION_REF_PROPERTY = "configuration-ref";
	private static final String CONFIGURATION_PROPERTY = "configuration";
	private ResourceSet resourceSet;
			
	protected ConfigurationLoadingDrawioFactory(
			ContentProvider<Element> contentProvider, 
			CapabilityLoader capabilityLoader,
			ResourceSet resourceSet) {
		super(contentProvider, capabilityLoader);
		this.resourceSet = resourceSet;
	}

	protected ConfigurationLoadingDrawioFactory(
			ContentProvider<Element> contentProvider,
			ResourceSet resourceSet) {
		super(contentProvider);
		this.resourceSet = resourceSet;
	}

	public ResourceSet getResourceSet() {
		return resourceSet;
	}
	
	protected String getConfigurationPropertyName() {
		return CONFIGURATION_PROPERTY;
	}
	
	protected String getConfigurationRefPropertyName() {
		return CONFIGURATION_REF_PROPERTY;
	}
	
	@Override
	protected void configureTarget(
			Element obj, 
			T target, 
			Map<Element, T> registry, 
			boolean isPrototype,
			ProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		super.configureTarget(obj, target, registry, isPrototype, progressMonitor);
		
		EObjectLoader eObjectLoader = new EObjectLoader((ObjectLoader) null) {
		
			@Override
			public ResolutionResult resolveEClass(String type) {
				EClass eClass = (EClass) ConfigurationLoadingDrawioFactory.this.getType(type, obj);
				return new ResolutionResult(eClass, null);
			}
			
			@Override
			public ResourceSet getResourceSet() {
				return ConfigurationLoadingDrawioFactory.this.getResourceSet();
			}
			
		};
		
		Marked marked = getContentProvider().asMarked(obj);		
		
		URI baseURI = getContentProvider().getBaseURI(obj);		
		String cpn = getConfigurationPropertyName();
		if (!Util.isBlank(cpn)) {
			Object configVal = getContentProvider().getProperty(obj, cpn);
			if (configVal instanceof String) {
				String configStr = (String) configVal;
				if (!Util.isBlank(configStr)) {
					eObjectLoader.loadYaml(
							configStr, 
							target, 
							baseURI, 
							null, 
							marked == null ? Collections.emptyList() : marked.getMarkers(), 
							progressMonitor);
				}			
			} else if (configVal != null) {
				eObjectLoader.load(
						eObjectLoader,						
						configVal, 
						target, 
						baseURI, 
						null, 
						marked == null ? Collections.emptyList() : marked.getMarkers(), 
						progressMonitor);
				
			}
		}
		
		String srpn = getConfigurationRefPropertyName();
		if (!Util.isBlank(srpn)) {
			Object refVal = getContentProvider().getProperty(obj, srpn);
			if (refVal != null) {
				URI refURI = null;
				if (refVal instanceof URI) {
					refURI = (URI) refVal;
				} else if (refVal instanceof String) {
					String ref = (String) refVal;
					if (!Util.isBlank(ref)) {
						refURI = URI.createURI(ref);
					}
				} else {
					throw new ConfigurationException("Unsupported spec ref type: " + refVal, marked);					
				}
				if (refURI != null) {
					if (baseURI != null && !baseURI.isRelative()) {
						refURI = refURI.resolve(baseURI);
					}				
					try {
						eObjectLoader.loadYaml(
								new URL(refURI.toString()),
								target, 
								null, 
								progressMonitor);
					} catch (Exception e) {
						throw new ConfigurationException("Error loading spec from " + refURI, e, marked);
					}
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
		return NcoreUtil.getNasdanikaAnnotationDetail(ePackage, NcoreUtil.LOAD_KEY, ePackage.getName());
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
