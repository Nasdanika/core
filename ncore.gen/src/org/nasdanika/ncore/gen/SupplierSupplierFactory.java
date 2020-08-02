package org.nasdanika.ncore.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.Supplier;

public class SupplierSupplierFactory<T extends org.nasdanika.ncore.Supplier> extends TypedElementSupplierFactory<T> {
	
	public SupplierSupplierFactory(T target) {
		super(target);
	}

	@Override
	public Supplier<Object> create(Context context) throws Exception {
		throw new UnsupportedOperationException("TODO: Implement in " + getClass() + " for " + target);
	}
	
}