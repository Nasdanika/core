module org.nasdanika.flow {
	exports org.nasdanika.flow;
	exports org.nasdanika.flow.impl;
	exports org.nasdanika.flow.util;
	
	requires transitive org.nasdanika.diagram;
	requires transitive org.nasdanika.emf;	
	requires transitive org.nasdanika.exec.gen;
}