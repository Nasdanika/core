module org.nasdanika.graph.model {
	exports org.nasdanika.graph.model;
	exports org.nasdanika.graph.model.adapters;
	exports org.nasdanika.graph.model.impl;
	exports org.nasdanika.graph.model.util;
	
	requires transitive org.nasdanika.exec;
	requires transitive org.nasdanika.capability;
	
}