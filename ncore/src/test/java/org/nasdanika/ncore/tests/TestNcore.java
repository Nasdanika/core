package org.nasdanika.ncore.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.Test;
import org.nasdanika.ncore.NcorePackage;
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

}
