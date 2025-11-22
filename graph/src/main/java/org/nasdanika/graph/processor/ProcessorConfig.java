package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.function.BiConsumer;

import org.nasdanika.graph.Element;

public interface ProcessorConfig<H,E> {
	
	Element getElement();
	
	Map<Element, ProcessorConfig<H,E>> getChildProcessorConfigs();

	/**
	 * Parent processor uses this map to communicate with child processors.
	 * Child synapses of the parent are wired to parent synapses of children setting a handler is a child synapse results in completion of parent's synapse endpoint and vice versa. 
	 * @return
	 */
	Map<Element, Synapse<H,E>> getChildSynapses();
			
	ProcessorConfig<H,E> getParentProcessorConfig();

	/**
	 * Child processor calls this method to obtain a synapse to communicate with the parent, if there is one.
	 * @return null if there is no parent.
	 */
	Synapse<H,E> getParentSynapse();
	
	/**
	 * Processor calls this method and passes a consumer for receiving processor synapses piped to client synapses 
	 * @return
	 */
	void setProcessorSynapseConsumer(BiConsumer<Object,Synapse<H,E>> processorSynapseConsumer);
		
	/**
	 * Client code calls this method to obtain a synapse to communicate with the processor.
	 * The returned synapse is wired with a synapse passed to the processor.
	 * @param clientKey
	 * @return
	 */
	Synapse<H,E> getClientSynapse(Object clientKey);	
	
	Map<Element,ProcessorConfig<H,E>> getRegistry();
	
	default <P> ProcessorInfo<H,E,P> toInfo(P processor) {
		return ProcessorInfo.of(this, processor);
	}
	
}
