package org.nasdanika.emf;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.SupplierFactory;

/**
 * Base class for commands which adapt models to {@link ConsumerFactory} and then combine input supplier with consumer and execute.
 * @author Pavel
 *
 */
public abstract class ConsumerModelCommand<T> extends AdapterModelCommand<EObject,ConsumerFactory<T>> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Class<ConsumerFactory<T>> getAdapterType() {
		return (Class) ConsumerFactory.class;
	}

	@Override
	protected CommandFactory createCommandFactory(ConsumerFactory<T> adapter) {
		return createInputSupplierFactory().then(adapter);
	}
	
	/**
	 * @return Factory for input supplier to combine with consumer to create a command.
	 */
	protected abstract SupplierFactory<T> createInputSupplierFactory();

}
