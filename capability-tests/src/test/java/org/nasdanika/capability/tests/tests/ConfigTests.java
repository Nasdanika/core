package org.nasdanika.capability.tests.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ConfigurationRequirement;
import org.nasdanika.capability.tests.ConfigRecord;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;

public class ConfigTests {
		
	@Test
	public void testModuleConfig() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		ConfigurationRequirement req = new ConfigurationRequirement(getClass().getModule());
		Function<String,Object> config = capabilityLoader.loadOne(req, progressMonitor);
		assertEquals("Tests",config.apply("name"));		
	}
		
	@Test
	public void testGlobalConfig() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		ConfigurationRequirement req = new ConfigurationRequirement((String) null, "global", ConfigRecord.class);
		ConfigRecord config = capabilityLoader.loadOne(req, progressMonitor);
		assertEquals("Global",config.name());		
	}	
	
	@Test
	public void testDefaultConfig() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		ConfigurationRequirement req = new ConfigurationRequirement();
		Function<String,Object> config = capabilityLoader.loadOne(req, progressMonitor);
		assertEquals("Tests",config.apply("name"));		
	}
	
	@Test
	public void testDefaultTypedConfig() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		ConfigurationRequirement req = new ConfigurationRequirement(ConfigRecord.class);
		ConfigRecord config = capabilityLoader.loadOne(req, progressMonitor);
		assertEquals("Tests",config.name());		
	}
	
	@Test
	public void testMyAppConfig() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		ConfigurationRequirement req = new ConfigurationRequirement("my-app");
		Function<String,Object> config = capabilityLoader.loadOne(req, progressMonitor);
		assertEquals("My Application",config.apply("name"));		
	}
		
	@Test
	public void testMyAppTypedConfig() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		ConfigurationRequirement req = new ConfigurationRequirement("my-app",ConfigRecord.class);
		ConfigRecord config = capabilityLoader.loadOne(req, progressMonitor);
		assertEquals("My Application",config.name());		
	}
	
	@Test
	public void testNoConfig() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		ConfigurationRequirement req = new ConfigurationRequirement("no-config");
		Function<String,Object> config = capabilityLoader.loadOne(req, progressMonitor);
		assertNull(config.apply("name"));		
	}
	
	@Test
	public void testTypedNoConfig() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		ConfigurationRequirement req = new ConfigurationRequirement("no-config",ConfigRecord.class);
		ConfigRecord config = capabilityLoader.loadOne(req, progressMonitor);
		assertNull(config.name());		
	}

}
