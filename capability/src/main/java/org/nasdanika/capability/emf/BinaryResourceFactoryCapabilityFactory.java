package org.nasdanika.capability.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.nasdanika.common.ProgressMonitor;

public class BinaryResourceFactoryCapabilityFactory extends ResourceFactoryCapabilityFactory {

	public static final String BINARY_RESOURCE_EXTENSION = "ebin";

	@Override
	protected Factory getResourceFactory(
			ResourceSet resourceSet,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return new Resource.Factory() {

			@Override
			public Resource createResource(URI uri) {
				return new BinaryResourceImpl(uri);
			}
			
		};
	}
	
	@Override
	protected String getExtension() {
		return BINARY_RESOURCE_EXTENSION;
	}

}
