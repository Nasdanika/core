package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import org.nasdanika.graph.Element;

public interface ProcessorConfig<H,E> {
	
	Element getElement();
	
	Map<Element, ProcessorConfig<H,E>> getChildProcessorConfigs();
	
	Map<Element, CompletionStage<E>> getChildEndpoints();
	
	Map<Element, Consumer<H>> getChildHandlerConsumers();
			
	ProcessorConfig<H,E> getParentProcessorConfig();

	CompletionStage<E> getParentEndpoint();
	
	void setParentHandler(H parentHandler);
	
	CompletionStage<E> getClientEndpoint();
	
	void setClientHandler(H clientHandler);
	
	Map<Element,ProcessorConfig<H,E>> getRegistry();
	
	default <P> ProcessorInfo<H,E,P> toInfo(P processor) {
		return ProcessorInfo.of(this, processor);
	}
	
}
