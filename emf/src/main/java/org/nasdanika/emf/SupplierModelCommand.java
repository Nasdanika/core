package org.nasdanika.emf;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.SupplierFactory;

/**
 * Base class for commands which adapt models to {@link SupplierFactory} and then combine supplier with output consumer and execute.
 * @author Pavel
 *
 */
public abstract class SupplierModelCommand<T> extends AdapterModelCommand<EObject,SupplierFactory<T>> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Class<SupplierFactory<T>> getAdapterType() {
		return (Class) SupplierFactory.class;
	}

	@Override
	protected CommandFactory createCommandFactory(SupplierFactory<T> adapter) {
		return adapter.then(createOutputConsumerFactory());
	}
	
	/**
	 * @return Factory for output consumer to combine with supplier to create a command.
	 */
	protected abstract ConsumerFactory<? super T> createOutputConsumerFactory();

}
