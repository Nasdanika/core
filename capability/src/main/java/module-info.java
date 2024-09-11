import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.capability.emf.BinaryResourceFactoryCapabilityFactory;
import org.nasdanika.capability.emf.ClassPathURIHandlerResourceSetCapabilityFactory;
import org.nasdanika.capability.emf.GzipBinaryResourceFactoryCapabilityFactory;
import org.nasdanika.capability.emf.ResourceSetCapabilityFactory;
import org.nasdanika.capability.emf.XMIResourceFactoryCapabilityFactory;
import org.nasdanika.capability.factories.MarkdownHelperCapabilityFactory;

module org.nasdanika.capability {
	
	requires transitive org.nasdanika.common;
	requires transitive reactor.core;
	requires transitive org.reactivestreams;
	requires org.eclipse.emf.ecore.xmi;
	
	exports org.nasdanika.capability;
	exports org.nasdanika.capability.emf;
	exports org.nasdanika.capability.maven;	
	
	uses CapabilityFactory;
	
	provides CapabilityFactory with 
		ResourceSetCapabilityFactory,
		XMIResourceFactoryCapabilityFactory,
		BinaryResourceFactoryCapabilityFactory,
		GzipBinaryResourceFactoryCapabilityFactory,
		ClassPathURIHandlerResourceSetCapabilityFactory,
		MarkdownHelperCapabilityFactory;
		
}