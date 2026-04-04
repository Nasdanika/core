package org.nasdanika.emf.json;

import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emfcloud.jackson.resource.JsonResourceFactory;
import org.nasdanika.capability.emf.ResourceFactoryCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

public class JsonResourceFactoryCapabilityFactory extends ResourceFactoryCapabilityFactory {

	public static final String JSON_RESOURCE_EXTENSION = "json";

	@Override
	protected Factory getResourceFactory(
			ResourceSet resourceSet,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		return new JsonResourceFactory();
	}
	
	@Override
	protected String getExtension() {
		return JSON_RESOURCE_EXTENSION;
	}

}
