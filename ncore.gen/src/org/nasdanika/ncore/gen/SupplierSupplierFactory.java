package org.nasdanika.ncore.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;

public class SupplierSupplierFactory<T extends org.nasdanika.ncore.Supplier>  implements SupplierFactory<Object> {
	
	protected T target;

	public SupplierSupplierFactory(T target) {
		this.target = target;
	}

	@Override
	public Supplier<Object> create(Context context) throws Exception {
		throw new UnsupportedOperationException("TODO: Implement in " + getClass() + " for " + target);
	}
	
}