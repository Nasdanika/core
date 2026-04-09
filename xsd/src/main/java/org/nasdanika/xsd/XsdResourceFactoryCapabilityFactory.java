package org.nasdanika.xsd;

import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.nasdanika.capability.CapabilityFactory.Loader;
import org.nasdanika.capability.emf.ResourceFactoryCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

public class XsdResourceFactoryCapabilityFactory extends ResourceFactoryCapabilityFactory {

	@Override
	protected Factory getResourceFactory(
			ResourceSet resourceSet, 
			Loader loader,
			ProgressMonitor progressMonitor) {
		return new XSDResourceFactoryImpl();
	}
	
	@Override
	protected String getExtension() {
		return "xsd";
	}

}
