package org.nasdanika.emf;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.SupplierFactory;

/**
 * Base class for commands which adapt models to {@link FunctionFactory} and then combine input supplier with function and output consumer to execute.
 * @author Pavel
 *
 */
public abstract class FunctionModelCommand<T,R> extends AdapterModelCommand<EObject,FunctionFactory<T,R>> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Class<FunctionFactory<T,R>> getAdapterType() {
		return (Class) FunctionFactory.class;
	}

	@Override
	protected CommandFactory createCommandFactory(FunctionFactory<T,R> adapter) {
		return createInputSupplierFactory().then(adapter).then(createOutputConsumerFactory());
	}
	
	/**
	 * @return Factory for input supplier to combine with function and output consumer to create a command.
	 */
	protected abstract SupplierFactory<T> createInputSupplierFactory();
	
	/**
	 * @return Factory for output consumer to combine with input supplier and function to create a command.
	 */
	protected abstract ConsumerFactory<? super R> createOutputConsumerFactory();

}
