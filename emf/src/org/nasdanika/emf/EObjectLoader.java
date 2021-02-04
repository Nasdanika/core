package org.nasdanika.emf;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

public class EObjectLoader implements ObjectLoader {

	private org.nasdanika.common.persistence.ObjectLoader chain;
	
	private class EPackageEntry {
		
		EPackage ePackage;
		
		java.util.function.Function<EClass,String> keyProvider = ec -> {
			String[] ns = StringUtils.splitByCharacterTypeCamelCase(ec.getName());
			for (int i = 0; i < ns.length; ++i) {
				ns[i] = ns[i].toLowerCase();
			}
			return String.join("-", ns);
		};
				
		EPackageEntry(EPackage ePackage, java.util.function.Function<EClass,String> keyProvider) {
			this.ePackage = ePackage;
			if (keyProvider != null) {
				this.keyProvider = keyProvider;
			}
		}
		
	}
	
	private Map<String,EPackageEntry> registry = new HashMap<>();

	public EObjectLoader(ObjectLoader chain) {
		this.chain = chain;
	}
	
	public EObjectLoader() {	}
	
	public void register(EPackage ePackage, String prefix, java.util.function.Function<EClass,String> keyProvider) {
		registry.put(prefix, new EPackageEntry(ePackage, keyProvider));
	}
	
	public void register(EPackage ePackage) {
		register(ePackage, ePackage.getName(), null);
	}

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
		}
		if (chain == null) {
			throw new ConfigurationException("Unsupported type: " + type, marker);
		}
		
		return chain.create(loader, type, config, base, progressMonitor, marker);		
	}
		
	// TODO - Epackage registration with optional prefixes, package name is the default prefix, throw an exception if already registered.
	// Loading - leverage supplier features and annotations. Something like new EObjectLoader(config).create(...) as in App 
	// Chaining
	// Doc - code snippet.

}
