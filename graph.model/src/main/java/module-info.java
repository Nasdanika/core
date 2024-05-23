import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.graph.model.util.GraphEPackageResourceSetCapabilityFactory;

module org.nasdanika.graph.model {
	exports org.nasdanika.graph.model;
	exports org.nasdanika.graph.model.adapters;
	exports org.nasdanika.graph.model.impl;
	exports org.nasdanika.graph.model.util;
	
	requires transitive org.nasdanika.exec;
	
	provides CapabilityFactory with
		GraphEPackageResourceSetCapabilityFactory;
	
}