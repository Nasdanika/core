package org.nasdanika.exec.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.ncore.util.NcoreUtil;

class TestURIs {

	@Test
	void testECoreURI() {
		List<URI> mapIdentifiers = NcoreUtil.getIdentifiers(ExecPackage.Literals.CALL__METHOD);
		assertEquals(1, mapIdentifiers.size());
		assertEquals(URI.createURI("ecore://nasdanika.org/core/exec/Call/method"), mapIdentifiers.get(0));
		
		List<URI> periodStartIdentifiers = NcoreUtil.getIdentifiers(ContentPackage.Literals.MARKDOWN__STYLE);
		assertEquals(1, periodStartIdentifiers.size());
		assertEquals(URI.createURI("ecore://nasdanika.org/core/exec/content/Markdown/style"), periodStartIdentifiers.get(0));
	}

}
