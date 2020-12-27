package org.nasdanika.common.persistence;

import java.util.List;

import org.nasdanika.common.Context;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;

/**
 * 
 * @author Pavel
 *
 * @param <T>
 * @param <E> Not really required, but used to eliminate generics errors.
 */
public class ListSupplierFactoryAttribute<T,E> extends AbstractFeatureDelegate<Feature<List<E>>> implements SupplierFactoryFeature<List<T>> {

	private boolean interpolate;

	public ListSupplierFactoryAttribute(Feature<List<E>> delegate, boolean interpolate) {
		super(delegate);
		this.interpolate = interpolate;
	}

	@Override
	public SupplierFactory<List<T>> getValue() {
		ListCompoundSupplierFactory<T> ret = new ListCompoundSupplierFactory<>("Feature " + getKey());
		if (delegate.getValue() != null) {
			int idx = 0;
			for (Object e: delegate.getValue()) {
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
