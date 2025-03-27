package org.nasdanika.cli;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.capability.emf.ResourceSetContributor;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;

/**
 * Mixes in a {@link ResourceSet} loaded by {@link CapabilityLoader}.
 * @author Pavel
 *
 */
public class ResourceSetMixIn {
	
	@Spec(Spec.Target.MIXEE) CommandSpec mixee;	
	
	@Option(
			names = "--extension-resource-factory",
			description = "Maps extension to resource factory class")
    Map<String, Class<?>> extensionResourceFactories;	
	
	@Option(
			names = "--protocol-resource-factory",
			description = "Maps protocol to resource factory class")
    Map<String, Class<?>> protocolResourceFactories;
	
	@Option(
			names = "--content-type-resource-factory",
			description = "Maps content type to resource factory class")
    Map<String, Class<?>> contentTypeResourceFactories;
		
	public ResourceSet createResourceSet(ProgressMonitor progressMonitor) {
		CapabilityLoader capabilityLoader = getCapabilityLoader();
		ResourceSetRequirement serviceRequirement = new ResourceSetRequirement(createRequirementResourceSet(), this::configureResourceSet, this::testContributor);		
		Requirement<ResourceSetRequirement, ResourceSet> requirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class, this::testFactory, serviceRequirement);		
		for (CapabilityProvider<?> cp: capabilityLoader.load(requirement, progressMonitor)) {
			return (ResourceSet) cp.getPublisher().blockFirst();
		}	
		
		ResourceSetImpl defaultResourceSet = createDefaultResourceSet();
		configureResourceSet(defaultResourceSet);
		return defaultResourceSet; // Fall-back if there are no capability providers.
	}
	
	protected void configureResourceSet(ResourceSet resourceSet) {
		if (extensionResourceFactories != null) {
			Map<String, Object> extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
			for (Entry<String, Class<?>> ee: extensionResourceFactories.entrySet()) {
				try {
					extensionToFactoryMap.put(ee.getKey(), ee.getValue().getDeclaredConstructor().newInstance());
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException	| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					throw new NasdanikaException("Could not instantiate resource factory " + ee.getValue() + ": " + e, e);
				}
			}
		}
		if (protocolResourceFactories != null) {
			Map<String, Object> protocolToFactoryMap = resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap();
			for (Entry<String, Class<?>> pe: protocolResourceFactories.entrySet()) {
				try {
					protocolToFactoryMap.put(pe.getKey(), pe.getValue().getDeclaredConstructor().newInstance());
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException	| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					throw new NasdanikaException("Could not instantiate resource factory " + pe.getValue() + ": " + e, e);
				}
			}
		}
		if (contentTypeResourceFactories != null) {
			Map<String, Object> contentTypeToFactoryMap = resourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap();
			for (Entry<String, Class<?>> ce: contentTypeResourceFactories.entrySet()) {
				try {
					contentTypeToFactoryMap.put(ce.getKey(), ce.getValue().getDeclaredConstructor().newInstance());
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException	| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					throw new NasdanikaException("Could not instantiate resource factory " + ce.getValue() + ": " + e, e);
				}
			}
		}
	}

	protected ResourceSetImpl createDefaultResourceSet() {
		return new ResourceSetImpl();
	}

	protected CapabilityLoader getCapabilityLoader() {
		if (mixee != null) {
			Object uo = mixee.userObject();
			if (uo instanceof CommandBase) {
				CapabilityLoader capabilityLoader = ((CommandBase) uo).getCapabilityLoader();
				if (capabilityLoader != null) {
					return capabilityLoader;
				}
			}
		}
		return new CapabilityLoader();
	}	
	
	/**
	 * Override to customize {@link ResourceSet} implementation type. This implementation returns null. 
	 * @return {@link ResourceSet} instance to pass to {@link ResourceSetRequirement}. 
	 */
	protected ResourceSet createRequirementResourceSet() {
		return null;
	}
		
	protected boolean testContributor(ResourceSetContributor contributor) {
		return true; 
	}
	
	protected boolean testFactory(ServiceCapabilityFactory<ResourceSetRequirement, ResourceSet> factory) {
		return true;
	}

}
