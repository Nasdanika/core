package org.nasdanika.capability.emf;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

public interface ResourceFactoryResourceSetContributor extends ResourceSetContributor {
	
	Resource.Factory getResourceFactory();
	
	String getContentType(); 
	
	String getExtension();
	
	String getProtocol();
	
	@Override
	default void contribute(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		Registry registry = resourceSet.getResourceFactoryRegistry();
		String contentType = getContentType();
		Factory resourceFactory = getResourceFactory();
		if (!Util.isBlank(contentType)) {
			registry.getContentTypeToFactoryMap().put(contentType, resourceFactory);
		}
		String extension = getExtension();
		if (!Util.isBlank(extension)) {
			registry.getExtensionToFactoryMap().put(extension, resourceFactory);
		}
		String protocol = getProtocol();
		if (!Util.isBlank(protocol)) {
			registry.getProtocolToFactoryMap().put(protocol, resourceFactory);
		}
	}

}
