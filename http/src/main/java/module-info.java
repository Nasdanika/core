import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.http.HttpServerCommandFactory;

module org.nasdanika.http {
	
	exports org.nasdanika.http;
	
	requires transitive org.nasdanika.cli;
	requires reactor.netty.http;
	requires reactor.netty.core;
	
	opens org.nasdanika.http to info.picocli;
	
	provides CapabilityFactory with 
		HttpServerCommandFactory;
	
}