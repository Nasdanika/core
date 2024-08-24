package org.nasdanika.emf;

import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.emf.ResourceFactoryCapabilityFactory;

public class SpecLoadingDrawioResourceFactoryCapabilityFactory extends ResourceFactoryCapabilityFactory {

	@Override
	protected Factory getResourceFactory(ResourceSet resourceSet) {
		return new SpecLoadingDrawioResourceFactory(uri -> resourceSet.getEObject(uri, true));
	}
	
	@Override
	protected String getExtension() {
		return "drawio";
	}

}
