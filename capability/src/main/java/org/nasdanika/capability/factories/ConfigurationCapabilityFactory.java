package org.nasdanika.capability.factories;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.capability.AbstractCapabilityFactory;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ConfigurationRequirement;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

public class ConfigurationCapabilityFactory extends AbstractCapabilityFactory<ConfigurationRequirement, Object> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ConfigurationCapabilityFactory.class);

	@Override
	public boolean canHandle(Object requirement) {
		return requirement instanceof ConfigurationRequirement;
	}
	
	private String[] EXTENSIONS = { "yml", "yaml", "json" };

	@Override
	public CompletionStage<Iterable<CapabilityProvider<Object>>> create(
			ConfigurationRequirement requirement,
			Loader loader, 
			ProgressMonitor progressMonitor) {
		
		Requirement<ResourceSetRequirement, ResourceSet> resourceSetRequirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class);		
		CompletionStage<ResourceSet> resourceSetCS = loader.loadOne(resourceSetRequirement, progressMonitor);		
		return wrapCompletionStage(resourceSetCS.thenApply(rs -> loadConfig(requirement, rs))); 		
	}
	
	@SuppressWarnings("unchecked")
	private Object loadConfig(ConfigurationRequirement requirement, ResourceSet resourceSet) {		
		String moduleName = requirement.moduleName();		
		String reqName = requirement.name();

		if (Util.isBlank(requirement.moduleName())) {
			if (requirement.type() == null) {
				if (Util.isBlank(reqName)) {
					LOGGER.info("Loading global configuration");
				} else { 
					LOGGER.info("Loading global '{}' configuration", reqName);			
				}
			} else {
				if (Util.isBlank(reqName)) {
					LOGGER.info("Loading global configuration of type {}", requirement.type());
				} else { 
					LOGGER.info("Loading global '{}' configuration of type {}", reqName, requirement.type());			
				}			
			}			
		} else {
			if (requirement.type() == null) {
				if (Util.isBlank(reqName)) {
					LOGGER.info("Loading configuration for module {}", moduleName);
				} else { 
					LOGGER.info("Loading '{}' configuration for module {}", reqName, moduleName);			
				}
			} else {
				if (Util.isBlank(reqName)) {
					LOGGER.info("Loading configuration of type {} for module {}", requirement.type(), moduleName);
				} else { 
					LOGGER.info("Loading '{}' configuration of type {} for module {}", reqName, requirement.type(), moduleName);			
				}			
			}
		}
		
		for (String extension: EXTENSIONS) {
			String configName = System.getProperty("org.nasdanika.config.base", "config");
			if (!Util.isBlank(moduleName)) {
				configName += "/" + moduleName.replace('.', '/');
			}
			
			if (!Util.isBlank(reqName)) {
				configName += "/" + reqName;
			}
			configName += "." + extension;
			URI configURI = null;
			try {
				URI baseURI = URI.createFileURI(new File(".").getCanonicalPath()).appendSegment("");
				configURI = URI.createURI(configName).resolve(baseURI);
				LOGGER.info("Loading configuration from {}", configURI);				
				try (InputStream in = resourceSet.getURIConverter().createInputStream(configURI)) {
					switch (extension) {
					case "yml":
					case "yaml":
						Yaml yaml = new Yaml();
						Map<String, Object> config = ((Map<String,Object>) yaml.load(in));
						if (requirement.type() == null) {
							return (Function<String, Object>) config::get;
						}
						return Invocable.of(requirement.type()).call(config);
					case "json":	
						JSONObject jo = new JSONObject(new JSONTokener(in));
						if (requirement.type() == null) {
							return (Function<String, Object>) jo.toMap()::get;
						}
						return Invocable.of(requirement.type()).call(jo.toMap());
					}
				}
			} catch (IOException e) {
				LOGGER.info("Configuration resource not found: {}", configURI == null ? configName : configURI);				
			}
		}
		
		if (requirement.type() == null) {		
			return (Function<String, Object>) key -> null;
		}
		
		return Invocable.of(requirement.type()).call(Collections.emptyMap());
	}

}
