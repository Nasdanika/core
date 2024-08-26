import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.emf.SpecLoadingDrawioResourceFactoryCapabilityFactory;
import org.nasdanika.emf.SpecLoadingDrawioResourceFactoryPngCapabilityFactory;
import org.nasdanika.emf.persistence.EObjectCapabilityFactory;
import org.nasdanika.emf.persistence.EObjectLoaderYamlResourceFactoryCapabilityFactory;

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
	requires info.picocli;
	
	opens org.nasdanika.emf to info.picocli;
	
	provides CapabilityFactory with 
		EObjectCapabilityFactory,
		SpecLoadingDrawioResourceFactoryCapabilityFactory,
		SpecLoadingDrawioResourceFactoryPngCapabilityFactory,
		EObjectLoaderYamlResourceFactoryCapabilityFactory;
	
}