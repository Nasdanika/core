package org.nasdanika.core.tests.exec;

import java.net.URL;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

public class CustomLoaderFour implements ObjectLoader {

	public CustomLoaderFour(ObjectLoader chain) {
		this.chain = chain;
	}
		
	private org.nasdanika.common.persistence.ObjectLoader chain;

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
			switch (type) {
			case "custom-component":
				return new CustomSupplierFactoryFour();			
			default:
				if (chain == null) {
					throw new ConfigurationException("Unsupported type: " + type, marker);
				}
				
				return chain.create(loader, type, config, base, subMonitor, marker);
			}
		}
	}

}
