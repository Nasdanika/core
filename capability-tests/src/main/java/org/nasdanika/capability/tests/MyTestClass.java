package org.nasdanika.capability.tests;

import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

/**
 * Constructed from test-specs/java.json
 */
public class MyTestClass {
	
	public MyTestClass(CapabilityFactory.Loader loader, ProgressMonitor progressMonitor) {
		System.out.println("Here I am: " + loader + ", " + progressMonitor);
	}
	
	public MyTestClass(CapabilityFactory.Loader loader, ProgressMonitor progressMonitor, String binding) {
		System.out.println("Here I am: " + loader + ", " + progressMonitor + ", " + binding);
	}

}
