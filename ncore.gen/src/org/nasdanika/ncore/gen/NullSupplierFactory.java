package org.nasdanika.ncore.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.Supplier;
import org.nasdanika.ncore.Null;

public class NullSupplierFactory extends TypedElementSupplierFactory<Null> {
	
	public NullSupplierFactory(Null target) {
		super(target);
	}

	@Override
	public Supplier<Object> create(Context context) throws Exception {
		return Supplier.EMPTY;
	}
	
}