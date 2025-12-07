import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.capability.tests.AggregatorFactory;
import org.nasdanika.capability.tests.ListFactory;
import org.nasdanika.capability.tests.MyService;
import org.nasdanika.capability.tests.MyServiceImpl;
import org.nasdanika.capability.tests.QualifiedFencedBlockProcessorProviderCapabilityFactory;
import org.nasdanika.capability.tests.TestCapabilityFactory;

module org.nasdanika.capability.tests {
	
	requires transitive org.nasdanika.maven;
	
	exports org.nasdanika.capability.tests;
	
	uses MyService;
	provides MyService with MyServiceImpl;
	provides CapabilityFactory with 
		TestCapabilityFactory, 
		AggregatorFactory, 
		ListFactory,
		QualifiedFencedBlockProcessorProviderCapabilityFactory;
		
}