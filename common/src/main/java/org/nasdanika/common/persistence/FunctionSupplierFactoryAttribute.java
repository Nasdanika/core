package org.nasdanika.common.persistence;

import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.SupplierFactory;

/**
 * Applies a function to the delegate value to produce this feature value.
 * @author Pavel
 *
 * @param <T>
 */
public class FunctionSupplierFactoryAttribute<T,R> extends AbstractFeatureDelegate<SupplierFactoryFeature<T>> implements SupplierFactoryFeature<R> {

	private FunctionFactory<T, R> functionFactory;

	public FunctionSupplierFactoryAttribute(SupplierFactoryFeature<T> delegate, FunctionFactory<T,R> functionFactory) {
		super(delegate);
		this.functionFactory = functionFactory;
	}

	@Override
	public SupplierFactory<R> getValue() {
		return delegate.getValue().then(functionFactory);
	}

}
