package org.nasdanika.emf;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.nasdanika.cli.DelegatingCommand;

import picocli.CommandLine.Option;

/**
 * Base class for command which use {@link ResourceSet}. This command creates a resource set,
 * registers all known packages, 
 * loads resources specified with -r option, and registers the global nasdanika adapter factory.
 * @author Pavel
 *
 */
public abstract class ResourceSetCommand extends DelegatingCommand {
		
	@Option(			
			names = {"-r", "--resource"},
			paramLabel = "URL",
			description = "URL of a resource to load to the resource set relative to the current directory. ")
	private List<String> resources = new ArrayList<>();
	
	/**
	 * @return {@link EPackage}'s to register with the resource set for loading the model.
	 * This implementation returns all registered packages.
	 */
	protected Collection<EPackage> getEPackages() {
		Registry registry = EPackage.Registry.INSTANCE;
		return new ArrayList<String>(registry.keySet()).stream().map(nsURI -> registry.getEPackage(nsURI)).collect(Collectors.toList());
	}
	
	/**
	 * Creates an empty resource set with registered packages and {@link XMIResourceFactoryImpl} and loads resource to it.
	 * @return
	 */
	protected ResourceSet createResourceSet() {
		ResourceSet resourceSet = createEmptyResourceSet();

		for (String uri: resources) {
			URI resourceUri = URI.createURI(new File(".").toURI().resolve(uri).toString());
			resourceSet.getResource(resourceUri, true);
		}
		return resourceSet;		
	}

	/**
	 * Creates a {@link ResourceSet} with all known packages registered and with {@link XMIResourceFactoryImpl}
	 * @return
	 */
	protected ResourceSet createEmptyResourceSet() {
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		for (EPackage ePackage: getEPackages()) {
			resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
		}
		return resourceSet;
	}	
	
}
