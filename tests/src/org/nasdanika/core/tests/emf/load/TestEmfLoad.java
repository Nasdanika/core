package org.nasdanika.core.tests.emf.load;

import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.emf.persistence.YamlResourceFactory;
import org.nasdanika.party.Party;
import org.nasdanika.party.PartyPackage;

public class TestEmfLoad {
	
	/**
	 * Tests loading of {@link Party} from YAML.
	 */
	@Test
	public void testPerson() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();		
		Resource.Factory.Registry resourceFactoryRegistry = new ResourceFactoryRegistryImpl();
		resourceSet.getPackageRegistry().put(PartyPackage.eNS_URI, PartyPackage.eINSTANCE);
		ObjectLoader loader = new EObjectLoader(resourceSet);		
		Context context = Context.EMPTY_CONTEXT;
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		resourceFactoryRegistry.getExtensionToFactoryMap().put("yml", new YamlResourceFactory(loader, context, progressMonitor));
		resourceSet.setResourceFactoryRegistry(resourceFactoryRegistry);
		URL resourceURL = getClass().getResource("person.yml");
		URI uri = URI.createURI(resourceURL.toString());
		Resource resource = resourceSet.getResource(uri, true);
		System.out.println(resource.getContents());
	}
	
}
