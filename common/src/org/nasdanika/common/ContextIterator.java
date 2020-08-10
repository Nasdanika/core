package org.nasdanika.common;

import java.util.Iterator;

/**
 * Binding of {@link Iterator} to {@link Context}. The primary purpose of this interface is to use as an adapter type 
 * in control flow in models. E.g. a model element has an iterator attribute which specifies a named adapter factory
 * which adapts the model element to ContextIterator.Supplier.Factory. The factory is used to create to create ContextIterator
 * and the model element is processed (e.g. code is generated) for each iterator element with context provided by the iterator.
 * @author Pavel
 *
 */
public interface ContextIterator extends Iterator<Context> {
	
	interface Factory {
		
		ContextIterator create(Context context);
		
	}

}
