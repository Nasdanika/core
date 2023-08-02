/**
 * Java API for defining diagrams.
 */
module org.nasdanika.diagram {
	
	requires transitive org.nasdanika.graph;
	
	exports org.nasdanika.diagram.plantuml;
	exports org.nasdanika.diagram.plantuml.clazz;
		
}