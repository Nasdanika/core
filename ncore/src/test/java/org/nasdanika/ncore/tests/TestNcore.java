package org.nasdanika.ncore.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.ClassLoaderURIHandler;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.DirectoryContentFileURIHandler;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.ncore.util.NcoreYamlHandler;
import org.nasdanika.ncore.util.YamlResource;
import org.nasdanika.ncore.util.YamlResourceFactory;
import org.nasdanika.common.Util;

class TestNcore {

	@Test
	public void testECoreURI() {
		List<URI> mapIdentifiers = NcoreUtil.getIdentifiers(NcorePackage.Literals.MAP);
		assertEquals(1, mapIdentifiers.size());
		assertEquals(URI.createURI("ecore://nasdanika.org/core/ncore/eClassifiers/Map"), mapIdentifiers.get(0));
		
		List<URI> periodStartIdentifiers = NcoreUtil.getIdentifiers(NcorePackage.Literals.PERIOD__START);
		assertEquals(1, periodStartIdentifiers.size());
		assertEquals(URI.createURI("ecore://nasdanika.org/core/ncore/eClassifiers/Period/eStructuralFeatures/start"), periodStartIdentifiers.get(0));
		
		List<URI> eOperationsIdentifiers = NcoreUtil.getIdentifiers(EcorePackage.Literals.ECLASS__EOPERATIONS);
		assertEquals(1, eOperationsIdentifiers.size());
		assertEquals(URI.createURI("http://www.eclipse.org/emf/2002/Ecore/eClassifiers/EClass/eStructuralFeatures/eOperations"), eOperationsIdentifiers.get(0));
	}
	
	public @Test
	void testFileDirectoryURIHandler() throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());		
		URIHandler fileDirectoryURIHandler = new DirectoryContentFileURIHandler();
		resourceSet.getURIConverter().getURIHandlers().add(0,fileDirectoryURIHandler);
		
		String currentDir = new File(".").getCanonicalPath();
		URI currentDirURI = URI.createFileURI(currentDir).appendSegment("");
		
		Resource dirResource = resourceSet.getResource(currentDirURI, true);
		dirResource.save(System.out, null);
	}
	
	@Test
	public void testGoodYAML() {
		URL goodYamlURL = getClass().getResource("yaml/good.yml");
		assertNotNull(goodYamlURL);
		System.out.println(goodYamlURL);
		
		InputStream goodYamlStream = getClass().getResourceAsStream("yaml/good.yml");
		assertNotNull(goodYamlStream);

//		URL goodYamlURL2 = getClass().getClassLoader().getResource("org/nasdanika/ncore/tests/good.yml");
//		assertNotNull(goodYamlURL2);
//		
//		InputStream goodYamlStream2 = getClass().getClassLoader().getResourceAsStream("org/nasdanika/ncore/tests/good.yml");
//		assertNotNull(goodYamlStream2);
		
		ResourceSet resourceSet = new ResourceSetImpl();		
		
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("yml", new YamlResourceFactory(new NcoreYamlHandler()));		
		resourceSet.getURIConverter().getURIHandlers().add(0, new ClassLoaderURIHandler(getClass()::getResourceAsStream));

		URI goodYamlURI = URI.createURI(Util.CLASSPATH_URL_PREFIX + "yaml/good.yml"); 
		
		Resource goodYamlResource = resourceSet.getResource(goodYamlURI, true);
		assertTrue(goodYamlResource instanceof YamlResource);
		assertEquals(1, goodYamlResource.getContents().size());
		Object root = goodYamlResource.getContents().get(0);
		assertTrue(root instanceof org.nasdanika.ncore.Map);
		org.nasdanika.ncore.Map rootMap = (org.nasdanika.ncore.Map) root;
		assertEquals(3, rootMap.getValue().size());
		
	}

}
