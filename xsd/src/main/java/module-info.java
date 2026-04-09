import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.xsd.XsdEPackageResourceSetCapabilityFactory;
import org.nasdanika.xsd.XsdResourceFactoryCapabilityFactory;
import org.nasdanika.xsd.XsdToEcoreCommandFactory;

module org.nasdanika.xsd {
	
	requires transitive org.nasdanika.cli;
	requires org.eclipse.xsd;
	
	opens org.nasdanika.xsd to info.picocli, org.nasdanika.cli;

	provides CapabilityFactory with 
		XsdEPackageResourceSetCapabilityFactory,
		XsdResourceFactoryCapabilityFactory,
		XsdToEcoreCommandFactory;
		
}