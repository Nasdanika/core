import org.nasdanika.graph.model.util.ConnectionProcessorFactory;
import org.nasdanika.graph.model.util.NodeProcessorFactory;

module org.nasdanika.graph.model {
	exports org.nasdanika.graph.model;
	exports org.nasdanika.graph.model.adapters;
	exports org.nasdanika.graph.model.impl;
	exports org.nasdanika.graph.model.util;
	
	requires transitive org.nasdanika.exec;
	
	uses ConnectionProcessorFactory;
	uses NodeProcessorFactory;
	
}