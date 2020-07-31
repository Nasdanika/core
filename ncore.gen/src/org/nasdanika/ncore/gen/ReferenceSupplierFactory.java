package org.nasdanika.ncore.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.ncore.Reference;

public class ReferenceSupplierFactory implements SupplierFactory<Object> {
	
	private Reference target;

	public ReferenceSupplierFactory(Reference target) {
		this.target = target;
	}

	@Override
	public Supplier<Object> create(Context context) throws Exception {
		return EObjectAdaptable.adaptToSupplierFactory(target.getTarget(), Object.class).create(context);
	}
	
}