package org.nasdanika.ncore.gen;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.ncore.Configurable;
import org.nasdanika.ncore.Entry;

/**
 * Base class for adapters for subclasses of {@link Configurable}. 
 * @author Pavel
 *
 * @param <T>
 */
public abstract class ConfigurableAdapter<C extends Configurable> {
	
	protected C target;

	protected ConfigurableAdapter(C target) {
		this.target = target;
	}
			
	/**
	 * Contextifies the supplier factory passed as an argument with configuration entries.
	 * Returns the factory AS-IS if the configuration is empty. 
	 * @param supplierFactory
	 * @return
	 */
	protected <T> org.nasdanika.common.SupplierFactory<T> configure(SupplierFactory<T> supplierFactory) {
		if (target.getConfiguration().isEmpty()) {
			return supplierFactory;
		}
		MapCompoundSupplierFactory<String, Object> entriesFactory = new MapCompoundSupplierFactory<String, Object>("Entries");
		for (EObject ce: target.getConfiguration()) {
			if (ce instanceof Entry) {
				entriesFactory.put(((Entry) ce).getName(), EObjectAdaptable.adaptToSupplierFactory(ce, Object.class));
			}
		}
		
		FunctionFactory<Map<String, java.lang.Object>, org.nasdanika.common.Context> contextFactory = new FunctionFactory<Map<String,Object>, org.nasdanika.common.Context>() {
			
			@Override
			public Function<Map<String, java.lang.Object>, org.nasdanika.common.Context> create(org.nasdanika.common.Context context) throws Exception {
				return Function.fromBiFunction((map, progressMonitor) -> {
					return org.nasdanika.common.Context.wrap(map::get).compose(context);
				}, "Contextifier", 1);
			}
			
		};
		
		return supplierFactory.contextify(entriesFactory.then(contextFactory));
	}
	
}
