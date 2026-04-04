import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.emf.json.JsonResourceFactoryCapabilityFactory;
import org.nasdanika.emf.json.YamlResourceFactoryCapabilityFactory;
import org.nasdanika.emf.json.YmlResourceFactoryCapabilityFactory;

module org.nasdanika.emf.json {
	
	requires org.nasdanika.capability;
	requires emfjson.jackson;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.dataformat.yaml;
	
	uses CapabilityFactory;
	
	provides CapabilityFactory with 
		JsonResourceFactoryCapabilityFactory,
		YamlResourceFactoryCapabilityFactory,
		YmlResourceFactoryCapabilityFactory;
		
}