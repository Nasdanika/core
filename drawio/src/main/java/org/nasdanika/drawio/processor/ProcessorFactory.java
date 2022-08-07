package org.nasdanika.drawio.processor;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Element;

public interface ProcessorFactory<P,T,R,U,S> {
	
	default boolean isPassThrough(Connection connection) {
		return true;
	};
	
	Function<T,R> createEndpoint(Connection connection, Function<U,S> handler, EndpointType type);
	
	default ElementProcessorInfo<P> createProcessor(ElementProcessorConfig<P> config) {
		return new ElementProcessorInfo<P>() {

			@Override
			public ElementProcessorConfig<P> getConfig() {
				return config;
			}

			@Override
			public P getProcessor() {
				return null;
			}
		};
	}
	
	default Map<Element,ElementProcessorInfo<P>> createProcessors(Element element, ConnectionBase connectionBase) {
		ProcessorFactoryVisitor<P, T, R, U, S> visitor = new ProcessorFactoryVisitor<>(this);				
		element.accept(visitor::createElementProcessor, connectionBase);				
		return Collections.unmodifiableMap(visitor.getRegistry());		
	}

}
