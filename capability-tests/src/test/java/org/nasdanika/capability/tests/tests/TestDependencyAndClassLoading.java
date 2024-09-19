package org.nasdanika.capability.tests.tests;

import java.io.File;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
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
				new String[] { "org.apache.groovy:groovy-all:pom:4.0.23" }, 
				null, 
				null, 
				"target/test-repo");
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Collection<File> result = capabilityLoader.loadOne(requirement, progressMonitor);
		System.out.println(result);
	}
	
	@Test
	public void testClassLoading() throws ClassNotFoundException {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ClassLoaderRequirement requirement = new ClassLoaderRequirement(
				null, // String[] modulePath,
				null, // String[] rootModules,
				new ModuleLayer[] { getClass().getModule().getLayer() }, 
				getClass().getClassLoader(), // ClassLoader parentClassLoader,
				true, // boolean singleLayerClassLoader,				
				new String[] { "org.apache.groovy:groovy-all:pom:4.0.23" }, 
				null, 
				null, 
				"target/test-repo",
				moduleLayer -> {
					System.out.println("Module layer: " + moduleLayer);
				});
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		ClassLoader result = capabilityLoader.loadOne(
				ServiceCapabilityFactory.createRequirement(ClassLoader.class, null, requirement),
				progressMonitor);
		
		Class<?> scriptEngineFactoryClass = result.loadClass("org.codehaus.groovy.jsr223.GroovyScriptEngineFactory");
		System.out.println(scriptEngineFactoryClass);
	}

}
