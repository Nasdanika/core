import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.http.DrawioHttpServerCommandFactory;
import org.nasdanika.http.HttpServerCommandFactory;

module org.nasdanika.http {
	
	exports org.nasdanika.http;
	
	requires transitive org.nasdanika.cli;
	requires transitive reactor.netty.http;
	requires transitive reactor.netty.core;
	requires transitive io.netty.codec.http;
	requires org.apache.commons.lang3;
	requires io.netty.buffer;
	requires transitive io.opentelemetry.context;
	requires org.reactivestreams; // For testing
	
	opens org.nasdanika.http;
	
	provides CapabilityFactory with 
		HttpServerCommandFactory,
		DrawioHttpServerCommandFactory;
	
}