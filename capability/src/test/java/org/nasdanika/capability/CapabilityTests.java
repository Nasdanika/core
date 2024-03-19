package org.nasdanika.capability;

import org.junit.jupiter.api.Test;
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
	

}
