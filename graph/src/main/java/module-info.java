module org.nasdanika.graph {

	requires transitive org.nasdanika.common;
	requires spring.expression;
	
	exports org.nasdanika.graph;
	exports org.nasdanika.graph.processor;
	exports org.nasdanika.graph.processor.emf;
	
}