import org.nasdanika.graph.model.util.ConnectionFactory;
import org.nasdanika.graph.model.util.NodeFactory;

module org.nasdanika.graph.model {
	exports org.nasdanika.graph.model;
	exports org.nasdanika.graph.model.adapters;
	exports org.nasdanika.graph.model.impl;
	exports org.nasdanika.graph.model.util;
	
	requires transitive org.nasdanika.exec;
	
	uses ConnectionFactory;
	uses NodeFactory;
	
}