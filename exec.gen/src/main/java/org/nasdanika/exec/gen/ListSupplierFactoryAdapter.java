package org.nasdanika.exec.gen;

import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;

public class ListSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<List<Object>> {
	
	public ListSupplierFactoryAdapter(org.nasdanika.exec.List list) {
		setTarget(list);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
		
	@Override
	public Supplier<List<Object>> create(Context context) throws Exception {
		ListCompoundSupplierFactory<Object> elementsFactory = new ListCompoundSupplierFactory<Object>("List");
		for (EObject e: ((org.nasdanika.exec.List) getTarget()).getElements()) {
			elementsFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(e, Object.class), "Cannot adapt to SupplierFactory"));
		}		
		
		return elementsFactory.create(context);
	}	

}
