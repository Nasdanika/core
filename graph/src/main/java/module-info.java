import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.graph.processor.ReflectiveProcessorServiceFactory;

module org.nasdanika.graph {

	requires transitive org.nasdanika.ncore;
	requires org.yaml.snakeyaml;
	requires transitive org.jgrapht.core;
	
	exports org.nasdanika.graph;
	exports org.nasdanika.graph.emf;
	exports org.nasdanika.graph.processor;
	exports org.nasdanika.graph.processor.emf;
	exports org.nasdanika.graph.processor.function;
	
	opens org.nasdanika.graph.emf;
	opens org.nasdanika.graph.processor.emf;
	
	provides CapabilityFactory with ReflectiveProcessorServiceFactory;
	
}