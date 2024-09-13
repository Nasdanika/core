package org.nasdanika.capability.tests.tests;

import java.io.File;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.requirements.ClassLoaderRequirement;
import org.nasdanika.capability.requirements.DependencyRequestRecord;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;

public class TestDependencyAndClassLoading {
	
	@Test
	public void testDependencyLoading() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		DependencyRequestRecord requirement = new DependencyRequestRecord(
				new String[] { "org.apache.groovy:groovy-all:pom:4.0.22" }, 
				null, 
				null, 
				"target/test-repo");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(requirement, progressMonitor);
		for (CapabilityProvider<Object> cp: cpi) {
			@SuppressWarnings("unchecked")
			Collection<File> result = (Collection<File>) cp.getPublisher().blockFirst();
			System.out.println(result);
		}
	}
	
	@Test
	public void testClassLoading() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ClassLoaderRequirement requirement = new ClassLoaderRequirement(
				null, // String[] modulePath,
				null, // String[] rootModules,
				new ModuleLayer[] { getClass().getModule().getLayer() }, 
				getClass().getClassLoader(), // ClassLoader parentClassLoader,
				true, // boolean singleLayerClassLoader,				
				new String[] { "org.apache.groovy:groovy-all:pom:4.0.22" }, 
				null, 
				null, 
				"target/test-repo",
				System.out::println);
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		ClassLoader result = capabilityLoader.loadOne(
				ServiceCapabilityFactory.createRequirement(ClassLoader.class, null, requirement),
				progressMonitor);
		System.out.println(result == requirement.parentClassLoader());
	}

}
