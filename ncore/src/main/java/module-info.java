import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.ncore.util.NcoreEPackageResourceSetCapabilityFactory;

module org.nasdanika.ncore {
	exports org.nasdanika.ncore;
	exports org.nasdanika.ncore.impl;
	exports org.nasdanika.ncore.util;	
	requires transitive org.nasdanika.persistence;
	requires org.eclipse.emf.ecore.xmi;
	requires transitive org.nasdanika.capability;
	
	provides CapabilityFactory with
		NcoreEPackageResourceSetCapabilityFactory;
}