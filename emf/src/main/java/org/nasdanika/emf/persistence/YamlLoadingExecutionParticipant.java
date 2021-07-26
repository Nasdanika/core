package org.nasdanika.emf.persistence;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Registers {@link YamlResourceFactory} with the resource set.
 * Also registers {@link EPackage}'s
 * @author Pavel
 *
 */
public abstract class YamlLoadingExecutionParticipant extends LoadingExecutionParticipant {

	public YamlLoadingExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected ResourceSet createResourceSet(ProgressMonitor progressMonitor) {
		ResourceSet ret = super.createResourceSet(progressMonitor);
		
		Registry packageRegistry = ret.getPackageRegistry();
		for (EPackage ePackage: getEPackages()) {
			packageRegistry.put(ePackage.getNsURI(), ePackage);
		}		
		
		Map<String, Object> extensionToFactoryMap = ret.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put("yml", createYamlResorceFactory(ret, progressMonitor));
		
		return ret;
	}

	protected YamlResourceFactory createYamlResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		return new YamlResourceFactory(createLoader(resourceSet), context, progressMonitor);
	}
	
	protected abstract Collection<EPackage> getEPackages();

	protected ObjectLoader createLoader(ResourceSet resourceSet) {
		return new EObjectLoader(null, null, resourceSet);
	}

}
