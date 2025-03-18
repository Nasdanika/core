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
	requires org.eclipse.jgit;
	requires transitive org.apache.poi.ooxml;
	requires spring.core;
	requires org.nasdanika.cli;
	
	opens org.nasdanika.emf to info.picocli;
	
	provides CapabilityFactory with 
		EObjectCapabilityFactory,
		ModelCommandFactory,
		ConfigurationLoadingDrawioResourceFactoryCapabilityFactory,
		ConfigurationLoadingDrawioResourceFactoryPngCapabilityFactory,
		EObjectLoaderYamlResourceFactoryCapabilityFactory;
	
}