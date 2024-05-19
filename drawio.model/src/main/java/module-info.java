import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.drawio.model.util.DrawioEPackageResourceSetCapabilityFactory;

module org.nasdanika.drawio.model {
	exports org.nasdanika.drawio.model;
	exports org.nasdanika.drawio.model.comparators;
	exports org.nasdanika.drawio.model.impl;
	exports org.nasdanika.drawio.model.util;
	
	requires transitive org.nasdanika.ncore;
	requires org.jsoup;
	requires transitive java.scripting;
	
	provides CapabilityFactory with
		DrawioEPackageResourceSetCapabilityFactory;

}