package org.nasdanika.common.persistence;

import java.util.List;

import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.SupplierFactory;

public class SupplierFactoryListAttribute<T> extends AbstractFeatureDelegate<Feature<List<T>>> implements SupplierFactoryFeature<List<T>> {

	public SupplierFactoryListAttribute(Feature<List<T>> delegate) {
		super(delegate);
	}

	@Override
	public SupplierFactory<List<T>> getValue() {
		ListCompoundSupplierFactory<T> ret = new ListCompoundSupplierFactory<>("Feature " + getKey());
		int idx = 0;
		for (T e: delegate.getValue()) {
			ret.add(SupplierFactory.<T>adapt(e, "Feature " + getKey() + "[" + (idx++) + "]"));
		}
		
		return ret;
	}

}
