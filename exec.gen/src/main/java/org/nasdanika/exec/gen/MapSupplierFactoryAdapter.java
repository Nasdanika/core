package org.nasdanika.exec.gen;

import java.util.Map.Entry;
import java.util.Objects;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;

public class MapSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<java.util.Map<String,Object>> {
	
	public MapSupplierFactoryAdapter(org.nasdanika.exec.Map map) {
		setTarget(map);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
		
	@Override
	public Supplier<java.util.Map<String,Object>> create(Context context) {
		MapCompoundSupplierFactory<String,Object> entriesFactory = new MapCompoundSupplierFactory<>("Map");
		for (Entry<String, EObject> e: ((org.nasdanika.exec.Map) getTarget()).getEntries()) {
			EObject value = e.getValue();
			entriesFactory.put(e.getKey(), Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(value, Object.class), "Cannot adapt to SupplierFactory: " + value));
		}		
		
		return entriesFactory.create(context);
	}	

}
