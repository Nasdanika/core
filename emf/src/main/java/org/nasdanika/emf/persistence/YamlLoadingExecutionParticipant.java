package org.nasdanika.emf.persistence;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Registers {@link YamlResourceFactory} with the resource set.
 * Also registers {@link EPackage}'s
 * @author Pavel
 *
 */
public abstract class YamlLoadingExecutionParticipant extends LoadingExecutionParticipant {

	@Override
	protected ResourceSet createResourceSet(ProgressMonitor progressMonitor) {
		ResourceSet ret = super.createResourceSet(progressMonitor);
		
		Registry packageRegistry = ret.getPackageRegistry();
		for (EPackage ePackage: getEPackages()) {
			packageRegistry.put(ePackage.getNsURI(), ePackage);
		}		
		
		Resource.Factory.Registry resourceFactoryRegistry = new ResourceFactoryRegistryImpl();				
		resourceFactoryRegistry.getExtensionToFactoryMap().put("yml", new YamlResourceFactory(createLoader(ret), context, progressMonitor));
		ret.setResourceFactoryRegistry(resourceFactoryRegistry);
		
		return ret;
	}
	
	protected abstract Collection<EPackage> getEPackages();

	protected ObjectLoader createLoader(ResourceSet resourceSet) {
		return new EObjectLoader(null, null, resourceSet);
	}

}
