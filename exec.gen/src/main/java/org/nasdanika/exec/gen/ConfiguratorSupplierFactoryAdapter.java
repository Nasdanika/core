package org.nasdanika.exec.gen;

import java.io.InputStream;
import java.util.Objects;

import org.nasdanika.common.Context;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.Configurator;

public class ConfiguratorSupplierFactoryAdapter extends ConfiguratorExecutionParticipantAdapter implements SupplierFactory<InputStream> {
	
	public ConfiguratorSupplierFactoryAdapter(Configurator configurator) {
		super(configurator);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	@Override
	public Supplier<InputStream> create(Context context) {
		SupplierFactory<Context> contextSupplierFactory = createContextSupplierFactory();
		Configurator configurator = (Configurator) getTarget();
		SupplierFactory<InputStream> supplierFactory = Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(configurator.getTarget(), InputStream.class), "Cannot adapt " + configurator.getTarget() + " to SupplierFactory");		
		if (contextSupplierFactory == null) {
			return supplierFactory.create(context);
		}
		
		return supplierFactory.contextify(contextSupplierFactory).create(context);
	}	

}
