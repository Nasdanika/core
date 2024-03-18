import org.nasdanika.capability.CapabilityFactory;

module org.nasdanika.capability {
	
	requires transitive org.nasdanika.common;
	requires transitive reactor.core;
	requires transitive org.reactivestreams;
	
	exports org.nasdanika.capability;
	
	uses CapabilityFactory;
		
}