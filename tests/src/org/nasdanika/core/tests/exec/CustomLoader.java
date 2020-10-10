package org.nasdanika.core.tests.exec;

import java.net.URL;
import java.util.function.Consumer;

import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;

public class CustomLoader extends ObjectLoader {
	
	private Consumer<String> resultsCollector;

	public CustomLoader(java.util.function.Consumer<String> resultsCollector, ObjectLoader chain) {
		this.resultsCollector = resultsCollector;
		this.chain = chain;
	}
		
	private org.nasdanika.common.ObjectLoader chain;

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
			switch (type) {
			case "custom-component":
				return new CustomSupplierFactory(resultsCollector);			
			default:
				if (chain == null) {
					throw new ConfigurationException("Unsupported type: " + type, marker);
				}
				
				return chain.create(loader, type, config, base, subMonitor, marker);
			}
		}
	}

}
