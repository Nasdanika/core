package org.nasdanika.xsd;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xsd.XSDPackage;
import org.nasdanika.capability.emf.EPackageCapabilityFactory;

public class XsdEPackageResourceSetCapabilityFactory extends EPackageCapabilityFactory {

	@Override
	protected EPackage getEPackage() {
		return XSDPackage.eINSTANCE;
	}

	@Override
	protected URI getDocumentationURI() {
		return URI.createURI("https://xsd.models.nasdanika.org/");
	}

}
