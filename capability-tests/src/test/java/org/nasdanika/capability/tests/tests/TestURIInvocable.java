package org.nasdanika.capability.tests.tests;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;

public class TestURIInvocable {
		
	@Test
	public void testStringInvocable() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		URI requirement = URI.createURI("data:value/String,Hello");
		Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, requirement),
				progressMonitor);
		for (CapabilityProvider<Object> cp: cpi) {
			Invocable invocable = (Invocable) cp.getPublisher().blockFirst();
			Object result = invocable.invoke();
			System.out.println(result);
		}
	}
	
	@Test
	public void testStringBase64Invocable() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		URI requirement = URI.createURI("data:value/String;base64,SGVsbG8=");
		Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, requirement),
				progressMonitor);
		for (CapabilityProvider<Object> cp: cpi) {
			Invocable invocable = (Invocable) cp.getPublisher().blockFirst();
			Object result = invocable.invoke();
			System.out.println(result);
		}
	}
	
	@Test
	public void testStringConstructorInvocable() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		URI requirement = URI.createURI("data:java/String;base64,SGVsbG8=");
		Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, requirement),
				progressMonitor);
		for (CapabilityProvider<Object> cp: cpi) {
			Invocable invocable = (Invocable) cp.getPublisher().blockFirst();
			Object result = invocable.invoke();
			System.out.println(result);
		}
	}
	
	@Test
	public void testStringStaticMethodInvocable() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		URI requirement = URI.createURI("data:java/String::valueOf;base64,SGVsbG8=");
		Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, requirement),
				progressMonitor);
		for (CapabilityProvider<Object> cp: cpi) {
			Invocable invocable = (Invocable) cp.getPublisher().blockFirst();
			Object result = invocable.invoke();
			System.out.println(result);
		}
	}
	
	@Test
	public void testScriptInvocable() {
//		CapabilityLoader capabilityLoader = new CapabilityLoader();
//		ClassLoaderRequirement requirement = new ClassLoaderRequirement(
//				null, // String[] modulePath,
//				null, // String[] rootModules,
//				new ModuleLayer[] { getClass().getModule().getLayer() }, 
//				getClass().getClassLoader(), // ClassLoader parentClassLoader,
//				true, // boolean singleLayerClassLoader,				
//				new String[] { "org.apache.groovy:groovy-all:pom:4.0.22" }, 
//				null, 
//				null, 
//				"target/test-repo",
//				System.out::println);
//		
//		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
//		Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(
//				ServiceCapabilityFactory.createRequirement(ClassLoader.class, null, requirement),
//				progressMonitor);
//		for (CapabilityProvider<Object> cp: cpi) {
//			ClassLoader result = (ClassLoader) cp.getPublisher().blockFirst();
//			System.out.println(result == requirement.parentClassLoader());
//		}
	}

}
