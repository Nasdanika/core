package org.nasdanika.capability.tests.tests;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.tests.AggregatorFactory;
import org.nasdanika.capability.tests.MyService;
import org.nasdanika.capability.tests.TestCapabilityFactory;
import org.nasdanika.capability.tests.TestServiceFactory;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

public class CapabilityTests {
	
	@Test
	public void testServiceCapability() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		capabilityLoader.getFactories().add(new TestServiceFactory<Object>());
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		for (CapabilityProvider<?> cp: capabilityLoader.load(MyService.class, progressMonitor)) {
			System.out.println(cp);
			Flux<?> publisher = cp.getPublisher();
			
			publisher.subscribe(System.out::println);
			publisher.subscribe(ms -> System.out.println(((MyService) ms).count("Hello")));
		}
	}
		
	@Test
	public void testCapabilityFactory() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		capabilityLoader.getFactories().add(new TestServiceFactory<Object>());
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		for (CapabilityProvider<?> cp: capabilityLoader.load(new TestCapabilityFactory.Requirement("Hello World"), progressMonitor)) {
			System.out.println(cp);
			Flux<?> publisher = cp.getPublisher();
			
			publisher.subscribe(System.out::println);
		}
	}
	
	@Test
	public void testAggregator() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		capabilityLoader.getFactories().add(new TestServiceFactory<Object>());
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		for (CapabilityProvider<?> cp: capabilityLoader.load(new AggregatorFactory.Requirement("Hello World"), progressMonitor)) {
			System.out.println(cp);
			Flux<?> publisher = cp.getPublisher();
			
			publisher.subscribe(System.out::println);
		}
	}
	
	@Test
	public void testListFactory() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		capabilityLoader.getFactories().add(new TestServiceFactory<Object>());
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		@SuppressWarnings({ "unchecked", "rawtypes"})
		ServiceCapabilityFactory.Requirement<List<Double>, Double> requirement = (ServiceCapabilityFactory.Requirement) ServiceCapabilityFactory.createRequirement(List.class, null,  33.0);
		for (CapabilityProvider<?> cp: capabilityLoader.load(requirement, progressMonitor)) {
			System.out.println(cp);
			Flux<?> publisher = cp.getPublisher();
			
			publisher.subscribe(System.out::println);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testLoadServiceListFactory() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		capabilityLoader.getFactories().add(new TestServiceFactory<Object>());
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		for (CapabilityProvider<List> cp: capabilityLoader.loadServices(List.class, null, 33.0, progressMonitor)) {
			System.out.println(cp);
			Flux<?> publisher = cp.getPublisher();
			
			publisher.subscribe(System.out::println);
		}
	}
	
}
