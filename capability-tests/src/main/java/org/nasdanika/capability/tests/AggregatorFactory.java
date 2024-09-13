package org.nasdanika.capability.tests;

import java.util.Collections;
import java.util.concurrent.CompletionStage;

import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

/**
 * 
 */
public class AggregatorFactory implements CapabilityFactory<AggregatorFactory.Requirement,String> {
	
	public record Requirement(String value){};
	
	@Override
		public boolean canHandle(Object requirement) {
			return requirement instanceof Requirement;
		}

	@Override
	public CompletionStage<Iterable<CapabilityProvider<String>>> create(
			Requirement requirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		String reqValue = ((Requirement) requirement).value();
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		CompletionStage<Iterable<CapabilityProvider<MyService>>> myServiceProviders = (CompletionStage) loader.load(MyService.class, progressMonitor);
		CompletionStage<Iterable<CapabilityProvider<Object>>> testProviders = loader.load(new TestCapabilityFactory.Requirement(reqValue) , progressMonitor);
		
		return myServiceProviders.thenCombine(testProviders, this::combine);
	}
	
	protected Iterable<CapabilityProvider<String>> combine(Iterable<CapabilityProvider<MyService>> myServiceProviders, Iterable<CapabilityProvider<Object>> testProviders) {
		Flux<MyService> myServiceCapabilityPublisher = myServiceProviders.iterator().next().getPublisher();
		
		return Collections.singleton(new CapabilityProvider<String>() {

			@Override
			public Flux<String> getPublisher() {
				return myServiceCapabilityPublisher.map(ms -> {
					return "Some result: " + testProviders;
				});
			}
			
		});
	}

}
