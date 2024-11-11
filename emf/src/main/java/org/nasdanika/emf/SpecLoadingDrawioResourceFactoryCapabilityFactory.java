package org.nasdanika.emf;

import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.CapabilityFactory.Loader;
import org.nasdanika.capability.emf.ResourceFactoryCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

public class SpecLoadingDrawioResourceFactoryCapabilityFactory extends ResourceFactoryCapabilityFactory {

	@Override
	protected Factory getResourceFactory(
			ResourceSet resourceSet,
			Loader loader,
			ProgressMonitor progressMonitor) {
		// TODO - representation filters capability providers
		return new SpecLoadingDrawioResourceFactory(loader.getCapabilityLoader(), uri -> resourceSet.getEObject(uri, true));
	}
	
	@Override
	protected String getExtension() {
		return "drawio";
	}

}
