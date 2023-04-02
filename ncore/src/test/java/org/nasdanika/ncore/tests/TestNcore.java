package org.nasdanika.ncore.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.NcoreUtil;

class TestNcore {

	@Test
	void testECoreURI() {
		List<URI> mapIdentifiers = NcoreUtil.getIdentifiers(NcorePackage.Literals.MAP);
		assertEquals(1, mapIdentifiers.size());
		assertEquals(URI.createURI("ecore://nasdanika.org/core/ncore/Map"), mapIdentifiers.get(0));
		
		List<URI> periodStartIdentifiers = NcoreUtil.getIdentifiers(NcorePackage.Literals.PERIOD__START);
		assertEquals(1, periodStartIdentifiers.size());
		assertEquals(URI.createURI("ecore://nasdanika.org/core/ncore/Period/start"), periodStartIdentifiers.get(0));
	}

}
