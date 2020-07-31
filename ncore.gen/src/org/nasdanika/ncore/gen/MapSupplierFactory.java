package org.nasdanika.ncore.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.ncore.Entry;
import org.nasdanika.ncore.Map;

public class MapSupplierFactory implements SupplierFactory<java.util.Map<String, Object>> {
	
	private Map target;

	public MapSupplierFactory(Map target) {
		this.target = target;
	}
	
	@Override
	public Supplier<java.util.Map<String, Object>> create(Context context) throws Exception {
		MapCompoundSupplier<String,Object> ret = new MapCompoundSupplier<>(target.getTitle());
		
		for (Entry e: target.getEntries()) {
			if (e.isEnabled()) {
				ret.put(e.getName(), EObjectAdaptable.adaptToSupplierFactory(e, Object.class).create(context));
			}
		}
		return ret;
	}
	
}