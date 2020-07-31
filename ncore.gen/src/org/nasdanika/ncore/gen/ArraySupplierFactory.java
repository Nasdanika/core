package org.nasdanika.ncore.gen;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ListCompoundSupplier;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.ncore.Array;

public class ArraySupplierFactory implements SupplierFactory<EList<Object>> {
	
	private Array target;

	public ArraySupplierFactory(Array target) {
		this.target = target;
	}
	
	@Override
	public Supplier<EList<Object>> create(Context context) throws Exception {
		ListCompoundSupplier<Object> ret = new ListCompoundSupplier<Object>(target.getTitle());
		for (EObject e: target.getElements()) {
			ret.add(EObjectAdaptable.adaptToSupplierFactory(e, Object.class).create(context));
		}
		
		return ret.then(BasicEList::new);
	}	
	
}