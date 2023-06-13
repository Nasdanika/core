module org.nasdanika.graph {

	requires transitive org.nasdanika.persistence;
	requires transitive org.nasdanika.ncore;
	requires org.yaml.snakeyaml;
	requires transitive org.jgrapht.core;
	
	exports org.nasdanika.graph;
	exports org.nasdanika.graph.emf;
	exports org.nasdanika.graph.processor;
	exports org.nasdanika.graph.processor.emf;
	
}