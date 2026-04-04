package org.nasdanika.emf.json;

public class YmlResourceFactoryCapabilityFactory extends YamlResourceFactoryCapabilityFactory {

	public static final String YML_RESOURCE_EXTENSION = "yml";
	
	@Override
	protected String getExtension() {
		return YML_RESOURCE_EXTENSION;
	}

}
