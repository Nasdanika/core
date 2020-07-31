package org.nasdanika.ncore.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.Supplier;
import org.nasdanika.ncore.Operation;

public class OperationSupplierFactory extends SupplierSupplierFactory<Operation> {
	
	public OperationSupplierFactory(Operation target) {
		super(target);
	}

	@Override
	public Supplier<Object> create(Context context) throws Exception {
		throw new UnsupportedOperationException();
	}
	
}