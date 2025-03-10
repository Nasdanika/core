import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.http.DrawioHttpServerCommandFactory;
import org.nasdanika.http.HttpServerCommandFactory;

module org.nasdanika.http {
	
	exports org.nasdanika.http;
	
	requires transitive org.nasdanika.cli;
	requires transitive reactor.netty.http;
	requires transitive reactor.netty.core;
//	requires static transitive io.netty.buffer; // To make Eclipse happy
	requires transitive io.netty.codec.http;
	requires org.apache.commons.lang3;
	
	opens org.nasdanika.http;
	
	provides CapabilityFactory with 
		HttpServerCommandFactory,
		DrawioHttpServerCommandFactory;
	
}