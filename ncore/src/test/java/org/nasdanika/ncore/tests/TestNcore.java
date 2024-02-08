package org.nasdanika.ncore.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.DispatchingResourceFactory;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.DirectoryContentFileURIHandler;
import org.nasdanika.ncore.util.NcoreUtil;

class TestNcore {

	@Test
	void testECoreURI() {
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
	
	@Test
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

}
