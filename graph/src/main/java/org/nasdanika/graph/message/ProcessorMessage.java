package org.nasdanika.graph.message;

import java.util.Collection;

/**
 * Messages which can be processed to send child messages shall implement this interface.
 * @param <T>
 * @param <V>
 */
public interface ProcessorMessage<C extends Message<?,?>>  {
	
	public abstract Collection<C> process(); 

}
