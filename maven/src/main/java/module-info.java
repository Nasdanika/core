import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.maven.ClassLoaderCapabilityFactory;
import org.nasdanika.maven.DependencyCapabilityFactory;
import org.nasdanika.maven.MavenURIConverterContributorCapabilityFactory;
import org.nasdanika.maven.MavenURIHandlerFactory;

module org.nasdanika.maven {
	requires transitive org.apache.maven.resolver.supplier;
	
	requires transitive org.nasdanika.capability;
	requires org.apache.maven.resolver;
	requires maven.resolver.provider;
	requires org.apache.maven.resolver.util;
	
	exports org.nasdanika.maven;
	
	provides CapabilityFactory with 
		DependencyCapabilityFactory,
		ClassLoaderCapabilityFactory,
		MavenURIHandlerFactory,
		MavenURIConverterContributorCapabilityFactory;
	
}
