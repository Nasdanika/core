package org.nasdanika.exec.gen;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.Configurator;

public abstract class ConfiguratorExecutionParticipantAdapter extends AdapterImpl {
	
	protected ConfiguratorExecutionParticipantAdapter(Configurator configurator) {
		setTarget(configurator);
	}
	
	/**
	 * Creates context supplier factory from configuration for contextification.
	 * 
	 * @return Context supplier factory or null if configuration is empty.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected org.nasdanika.common.SupplierFactory<Context> createContextSupplierFactory() {
		Configurator configurator = (Configurator) getTarget();
		EMap<String, EObject> properties = configurator.getProperties();
		if (properties.isEmpty()) {
			return null;
		}
		
		MapCompoundSupplierFactory<String, Object> entriesFactory = new MapCompoundSupplierFactory<String, Object>("Properties");
		for (Entry<String, EObject> ce: properties.entrySet()) {
			EObject value = ce.getValue();
			SupplierFactory<?> sf = Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(value, Object.class), "Cannot adapt " + value + " to SupplierFactory");				
			entriesFactory.put(ce.getKey(), (SupplierFactory) sf.then(Util.OBJECT_TO_STRING_FACTORY));
		}
		
		FunctionFactory<Map<String, java.lang.Object>, org.nasdanika.common.Context> contextFactory = new FunctionFactory<Map<String,Object>, org.nasdanika.common.Context>() {
			
			@Override
			public Function<Map<String, java.lang.Object>, org.nasdanika.common.Context> create(org.nasdanika.common.Context context) throws Exception {
				return Function.fromBiFunction((map, progressMonitor) -> {
					return org.nasdanika.common.Context.wrap(map::get).compose(context);
				}, "Contextifier", 1);
			}
			
		};
		
		return entriesFactory.then(contextFactory);
	}
	

}
