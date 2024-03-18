package org.nasdanika.capability;

import java.util.Arrays;
import java.util.ServiceLoader.Provider;
import java.util.stream.Stream;

import org.nasdanika.common.Context;

public class ContextServiceFactory extends ServiceFactory {
	
	private Context context;

	public ContextServiceFactory(Context context) {
		this.context = context;
	}
	
	protected <S> Provider<S> createProvider(S service) {
		return new Provider<S>() {

			@SuppressWarnings("unchecked")
			@Override
			public Class<S> type() {
				return (Class<S>) service.getClass();
			}

			@Override
			public S get() {
				return service;
			}
		};		
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <S> Stream<Provider<S>> stream(Class<S> service) {
		Object services = context.get(service.arrayType());
		if (services != null) {
			return Arrays.stream((Object[]) services)
				.map(s -> (S) s)
				.map(this::createProvider);
					
		}
		return Stream.empty();
	}

}
