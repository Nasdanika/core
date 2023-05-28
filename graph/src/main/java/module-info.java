module org.nasdanika.graph {

	requires transitive org.nasdanika.persistence;
	requires transitive org.nasdanika.ncore;
	requires transitive spring.expression;
	requires org.yaml.snakeyaml;
	
	exports org.nasdanika.graph;
	exports org.nasdanika.graph.emf;
	exports org.nasdanika.graph.processor;
	exports org.nasdanika.graph.processor.emf;
	
}