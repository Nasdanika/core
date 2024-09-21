package org.nasdanika.capability.tests;

import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

/**
 * Constructed from test-specs/java.json
 */
public class MyTestClass {
	
	public MyTestClass(
			CapabilityFactory.Loader loader, 
			ProgressMonitor progressMonitor) {
		System.out.println("Here I am: " + loader + ", " + progressMonitor);
	}
	
	public MyTestClass(
			CapabilityFactory.Loader loader, 
			ProgressMonitor progressMonitor, 
			String binding) {
		System.out.println("Here I am: " + loader + ", " + progressMonitor + ", " + binding);
	}
	
	public MyTestClass(
			CapabilityFactory.Loader loader, 
			ProgressMonitor progressMonitor, 
			String fragment,
			String bind) {
		System.out.println("Here I am: " + loader + ", " + progressMonitor + ", " + fragment + ", " + bind);
	}	
	
	public MyTestClass(
			CapabilityFactory.Loader loader, 
			ProgressMonitor progressMonitor, 
			byte[] binding) {
		System.out.println("Here I am: " + loader + ", " + progressMonitor + ", " + binding);
	}
	
	
	public MyTestClass(
			CapabilityFactory.Loader loader, 
			ProgressMonitor progressMonitor, 
			byte[] binding, 
			int arg) {
		System.out.println("Here I am: " + loader + ", " + progressMonitor + ", " + binding + ", " + arg);
	}	
	
	public static MyTestClass factory(
			CapabilityFactory.Loader loader, 
			ProgressMonitor progressMonitor, 
			byte[] binding) {
		return new MyTestClass(loader, progressMonitor, binding);
	}
	
	public static MyTestClass factory(
			CapabilityFactory.Loader loader, 
			ProgressMonitor progressMonitor, 
			byte[] binding,
			int arg) {
		return new MyTestClass(loader, progressMonitor, binding, arg);
	}

}
