package org.nasdanika.emf.json;

import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emfcloud.jackson.module.EMFModule;
import org.eclipse.emfcloud.jackson.resource.JsonResourceFactory;
import org.nasdanika.capability.emf.ResourceFactoryCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlResourceFactoryCapabilityFactory extends ResourceFactoryCapabilityFactory {

	public static final String YAML_RESOURCE_EXTENSION = "yaml";

	@Override
	protected Factory getResourceFactory(
			ResourceSet resourceSet,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.registerModule(new EMFModule());
        return new JsonResourceFactory(yamlMapper);
	}
	
	@Override
	protected String getExtension() {
		return YAML_RESOURCE_EXTENSION;
	}

}
