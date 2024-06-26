import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.capability.emf.ClassPathURIHandlerResourceSetCapabilityFactory;
import org.nasdanika.capability.emf.ResourceSetCapabilityFactory;
import org.nasdanika.capability.emf.XMIResourceFactoryCapabilityFactory;

module org.nasdanika.capability {
	
	requires transitive org.nasdanika.common;
	requires transitive reactor.core;
	requires transitive org.reactivestreams;
	requires org.eclipse.emf.ecore.xmi;
	
	exports org.nasdanika.capability;
	exports org.nasdanika.capability.emf;
	
	uses CapabilityFactory;
	
	provides CapabilityFactory with 
		ResourceSetCapabilityFactory,
		XMIResourceFactoryCapabilityFactory,
		ClassPathURIHandlerResourceSetCapabilityFactory;
		
}