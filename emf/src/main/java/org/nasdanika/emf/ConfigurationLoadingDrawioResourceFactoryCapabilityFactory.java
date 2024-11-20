package org.nasdanika.emf;

import org.eclipse.emf.ecore.resource.Resource.Factory;

import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.emf.ResourceFactoryCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Element;
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
				new GitMarkerFactory()) {
			
			@Override
			protected Map<String, Object> getVariables(ConfigurationLoadingDrawioResource resource,	Element context) {
				return Map.of(
						"resourceSet", resourceSet,
						"capabilityLoader", loader.getCapabilityLoader());
			}
			
		};
	}
	
	@Override
	protected String getExtension() {
		return "drawio";
	}

}
