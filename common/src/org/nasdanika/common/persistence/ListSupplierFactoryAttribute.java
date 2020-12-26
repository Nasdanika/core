package org.nasdanika.common.persistence;

import java.util.List;

import org.nasdanika.common.Context;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;

public class ListSupplierFactoryAttribute<T> extends AbstractFeatureDelegate<Feature<List<T>>> implements SupplierFactoryFeature<List<T>> {

	private boolean interpolate;

	public ListSupplierFactoryAttribute(Feature<List<T>> delegate, boolean interpolate) {
		super(delegate);
		this.interpolate = interpolate;
	}

	@Override
	public SupplierFactory<List<T>> getValue() {
		ListCompoundSupplierFactory<T> ret = new ListCompoundSupplierFactory<>("Feature " + getKey());
		if (delegate.getValue() != null) {
			int idx = 0;
			for (T e: delegate.getValue()) {
				if (e instanceof String && interpolate) {
					ret.add(new SupplierFactory<T>() {

						@Override
						public Supplier<T> create(Context context) throws Exception {
							Object ie = context.interpolate((String) e);
							return SupplierFactory.<T>adapt(ie, "Interpolated feature " + getKey() + "[" + e + "]").create(context);							
						}
						
					});
					
				} else {
					ret.add(SupplierFactory.<T>adapt(e, "Feature " + getKey() + "[" + (idx) + "]"));
				}
				++idx;
			}
		}		
		return ret;
	}

}
