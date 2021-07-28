package org.nasdanika.exec.gen;

import java.util.Objects;

import org.nasdanika.common.Command;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.Configurator;

public class ConfiguratorCommandFactoryAdapter extends ConfiguratorExecutionParticipantAdapter implements CommandFactory {
	
	public ConfiguratorCommandFactoryAdapter(Configurator configurator) {
		super(configurator);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	@Override
	public Command create(Context context) throws Exception {
		SupplierFactory<Context> contextSupplierFactory = createContextSupplierFactory();
		Configurator configurator = (Configurator) getTarget();
		CommandFactory commandFactory = Objects.requireNonNull(EObjectAdaptable.adaptTo(configurator.getTarget(), CommandFactory.class), "Cannot adapt " + configurator.getTarget() + " to CommandFactory");		
		if (contextSupplierFactory == null) {
			return commandFactory.create(context);
		}
		
		return commandFactory.contextify(contextSupplierFactory).create(context);
	}	

}
