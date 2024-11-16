package org.nasdanika.emf;

import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.emf.ResourceFactoryCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.persistence.GitMarkerFactory;

public class ConfigurationLoadingDrawioResourceFactoryCapabilityFactory extends ResourceFactoryCapabilityFactory {

	@Override
	protected Factory getResourceFactory(
			ResourceSet resourceSet,
			Loader loader,
			ProgressMonitor progressMonitor) {
		// TODO - representation filters capability providers, marker factory from capability - composite?
		return new ConfigurationLoadingDrawioResourceFactory(
				loader.getCapabilityLoader(), 
				uri -> resourceSet.getEObject(uri, true),
				new GitMarkerFactory());
	}
	
	@Override
	protected String getExtension() {
		return "drawio";
	}

}
