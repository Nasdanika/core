package org.nasdanika.graph.processor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.nasdanika.graph.Element;

class ProcessorConfigImpl<H,E> implements ProcessorConfig<H,E> {
	
	private Element element;
	private Map<Element, ProcessorConfig<H,E>> childConfigs = new ConcurrentHashMap<>();
	private ProcessorConfig<H,E> parentConfig;
	private Map<Element, ProcessorConfig<H,E>> registry;

	ProcessorConfigImpl(Element element) {
		this.element = element;
		childEndpoints = element.getChildren().stream().collect(Collectors.toUnmodifiableMap(Function.identity(), c -> new CompletableFuture<E>()));

	}

	@Override
	public Element getElement() {
		return element;
	}

	@Override
	public Map<Element, ProcessorConfig<H,E>> getChildProcessorConfigs() {
		return Collections.unmodifiableMap(childConfigs);
	}

	@Override
	public ProcessorConfig<H,E> getParentProcessorConfig() {
		return parentConfig;
	}

	@Override
	public Map<Element, ProcessorConfig<H,E>> getRegistry() {
		return registry;
	}
	
	// --- Wiring methods
	
	void setRegistry(Map<Element, ProcessorConfig<H,E>> registry) {
		this.registry = registry;
	}
	
	void setParentConfig(ProcessorConfig<H,E> parentConfig) {
		this.parentConfig = parentConfig;
	}
	
	void putChildConfig(Element child, ProcessorConfig<H,E> childConfig) {
		if (childConfig != null) {
			childConfigs.put(child, childConfig);
		}
	}
	
	private CompletableFuture<E> parentEndpoint = new CompletableFuture<>();
	private CompletableFuture<E> clientEndpoint = new CompletableFuture<>();
	
	private Consumer<H> parentHandlerConsumer;	
	private Consumer<H> clientHandlerConsumer;
	
	@Override
	public CompletionStage<E> getParentEndpoint() {
		return parentEndpoint;
	}

	@Override
	public void setParentHandler(H parentHandler) {
		if (parentHandlerConsumer != null) {
			parentHandlerConsumer.accept(parentHandler);
		}
	}

	@Override
	public CompletionStage<E> getClientEndpoint() {
		return clientEndpoint;
	}

	@Override
	public void setClientHandler(H clientHandler) {
		if (clientHandlerConsumer != null) {
			clientHandlerConsumer.accept(clientHandler);
		}
	}
	
	void wireParentHandlerEndpoint(Function<H,E> endpointFactory, Consumer<E> endpointConsumer) {		
		parentHandlerConsumer =	handler ->  endpointConsumer.accept(endpointFactory.apply(handler));		
	}
	
	void wireClientHandlerEndpoint(Function<H,E> endpointFactory, Consumer<E> endpointConsumer) {		
		clientHandlerConsumer = handler ->  endpointConsumer.accept(endpointFactory.apply(handler));		
	}
	
	void setParentEndpoint(E se) {
		parentEndpoint.complete(se);
	}
	
	void setClientEndpoint(E te) {
		clientEndpoint.complete(te);
	}
		
	private Map<Element, CompletableFuture<E>> childEndpoints;
	private Map<Element, Consumer<H>> childHandlerConsumers = new ConcurrentHashMap<>();	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<Element, CompletionStage<E>> getChildEndpoints() {
		return (Map) childEndpoints; // Rude cast to make the compiler happy
	}

	@Override
	public Map<Element, Consumer<H>> getChildHandlerConsumers() {
		return Collections.unmodifiableMap(childHandlerConsumers);
	}
	
	void wireChildHandlerEndpoint(
			Element child, 
			Function<H,E> endpointFactory,
			Consumer<E> endpointConsumer) {
		
		childHandlerConsumers.put(
				child, 
				handler ->  endpointConsumer.accept(endpointFactory.apply(handler)));		
	}
		
	void setChildEndpoint(Element child, E ce) {		
		childEndpoints.get(child).complete(ce);
	}
	
}		
