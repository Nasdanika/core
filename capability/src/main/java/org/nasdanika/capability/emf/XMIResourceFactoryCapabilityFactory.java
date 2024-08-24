package org.nasdanika.capability.emf;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class XMIResourceFactoryCapabilityFactory extends ResourceFactoryCapabilityFactory {

	@Override
	protected Factory getResourceFactory(ResourceSet resourceSet) {
		return new XMIResourceFactoryImpl();
	}
	
	@Override
	protected String getExtension() {
		return Resource.Factory.Registry.DEFAULT_EXTENSION;
	}

}
