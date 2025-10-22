import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.emf.ConfigurationLoadingDrawioResourceFactoryCapabilityFactory;
import org.nasdanika.emf.ConfigurationLoadingDrawioResourceFactoryPngCapabilityFactory;
import org.nasdanika.emf.persistence.EObjectCapabilityFactory;
import org.nasdanika.emf.persistence.EObjectLoaderYamlResourceFactoryCapabilityFactory;
import org.nasdanika.emf.ModelCommandFactory;

module org.nasdanika.emf {
	exports org.nasdanika.emf;
	exports org.nasdanika.emf.localization;
	exports org.nasdanika.emf.persistence;
	
	requires transitive org.nasdanika.drawio;
	requires org.jsoup;
	requires org.eclipse.emf.ecore.xmi;
	requires spring.expression;	
	requires transitive org.eclipse.jgit;
	requires transitive org.apache.poi.ooxml;
	requires spring.core;
	requires org.nasdanika.cli;
	requires org.apache.commons.lang3;
	requires transitive org.eclipse.emf.ecore.change; // For apply adapters
	
	opens org.nasdanika.emf to info.picocli, org.nasdanika.cli;
	
	provides CapabilityFactory with 
		EObjectCapabilityFactory,
		ModelCommandFactory,
		ConfigurationLoadingDrawioResourceFactoryCapabilityFactory,
		ConfigurationLoadingDrawioResourceFactoryPngCapabilityFactory,
		EObjectLoaderYamlResourceFactoryCapabilityFactory;
	
}