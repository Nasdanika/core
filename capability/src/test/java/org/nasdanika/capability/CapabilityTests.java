package org.nasdanika.capability;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.jupiter.api.Test;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;

public class CapabilityTests {
		
	@Test
	public void testServiceCapability() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		MutableContext ctx = Context.EMPTY_CONTEXT.fork();
		ctx.register(String.class, "Hello");
		ctx.register(String.class, "World");
		capabilityLoader.getFactories().add(new ContextServiceFactory<Object>(ctx));
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		for (CapabilityProvider<?> cp: capabilityLoader.load(String.class, progressMonitor)) {
			System.out.println(cp);
			cp.getPublisher().subscribe(System.out::println);
		}
	}
	
	@Test
	public void testResourceSetCapabilityFactory() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		ResourceSetRequirement serviceRequirement = new ResourceSetRequirement(null, contributor -> {
			System.out.println(contributor);
			return true;
		});
		
		Requirement<ResourceSetRequirement, ResourceSet> requirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class, null, serviceRequirement);		
		for (CapabilityProvider<?> cp: capabilityLoader.load(requirement, progressMonitor)) {
			System.out.println(cp);
			cp.getPublisher().subscribe(rs -> {
				ResourceSet resSet = (ResourceSet) rs;
				System.out.println(resSet.getResourceFactoryRegistry().getExtensionToFactoryMap());
			});
		}
	}
	
	@Test
	public void testResourceSetCapabilityFactoryNoServiceRequirement() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Requirement<ResourceSetRequirement, ResourceSet> requirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class);		
		for (CapabilityProvider<?> cp: capabilityLoader.load(requirement, progressMonitor)) {
			System.out.println(cp);
			cp.getPublisher().subscribe(System.out::println);
		}
	}

}
