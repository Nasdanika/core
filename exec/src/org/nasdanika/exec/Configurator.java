package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.nasdanika.common.ObjectLoader.Factory;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;

/**
 * Injects configuration entries into the target's context. map with two keys - configuration and target. 
 * @author Pavel
 *
 */
public class Configurator {

	public Configurator(Factory factory, String type, Object config, URL base, ProgressMonitor progressMonitor) {
		// TODO Auto-generated constructor stub
	}
	
	
	// --- Config ---
	
//	
//	/**
//	 * Creates context supplier factory from configuration for contextification.
//	 * 
//	 * @return Context supplier factory or null if configuration is empty.
//	 */
//	protected org.nasdanika.common.SupplierFactory<Context> createContextSupplierFactory() {
//		if (target.getConfiguration().isEmpty()) {
//			return null;
//		}
//		MapCompoundSupplierFactory<String, Object> entriesFactory = new MapCompoundSupplierFactory<String, Object>("Entries");
//		for (EObject ce: target.getConfiguration()) {
//			if (ce instanceof AbstractEntry) {
//				entriesFactory.put(((AbstractEntry) ce).getName(), EObjectAdaptable.adaptToSupplierFactory(ce, Object.class));
//			}
//		}
//		
//		FunctionFactory<Map<String, java.lang.Object>, org.nasdanika.common.Context> contextFactory = new FunctionFactory<Map<String,Object>, org.nasdanika.common.Context>() {
//			
//			@Override
//			public Function<Map<String, java.lang.Object>, org.nasdanika.common.Context> create(org.nasdanika.common.Context context) throws Exception {
//				return Function.fromBiFunction((map, progressMonitor) -> {
//					return org.nasdanika.common.Context.wrap(map::get).compose(context);
//				}, "Contextifier", 1);
//			}
//			
//		};
//		
//		return entriesFactory.then(contextFactory);
//	}
//	
//	
//	protected Supplier<InputStream> configure(Context context, SupplierFactory<Context> contextSupplierFactory, SupplierFactory<InputStream> supplierFactory) throws Exception {
//		if (contextSupplierFactory == null) {
//			return supplierFactory.create(context);
//		}
//		
//		return supplierFactory.contextify(contextSupplierFactory).create(context);
//	}
//
//
//	protected Consumer<BinaryEntityContainer> configure(Context context, SupplierFactory<Context> contextSupplierFactory, ConsumerFactory<BinaryEntityContainer> consumerFactory) throws Exception {
//		if (contextSupplierFactory == null) {
//			return consumerFactory.create(context);
//		}
//		
//		return consumerFactory.contextify(contextSupplierFactory).create(context);
//	}	
	
}
