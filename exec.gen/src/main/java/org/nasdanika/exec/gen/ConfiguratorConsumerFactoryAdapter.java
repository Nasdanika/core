package org.nasdanika.exec.gen;

import java.util.Objects;

import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.Configurator;
import org.nasdanika.resources.BinaryEntityContainer;

public class ConfiguratorConsumerFactoryAdapter extends ConfiguratorExecutionParticipantAdapter implements ConsumerFactory<BinaryEntityContainer> {
	
	public ConfiguratorConsumerFactoryAdapter(Configurator configurator) {
		super(configurator);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	@Override
	public Consumer<BinaryEntityContainer> create(Context context) {
		SupplierFactory<Context> contextSupplierFactory = createContextSupplierFactory();
		Configurator configurator = (Configurator) getTarget();
		ConsumerFactory<BinaryEntityContainer> consumerFactory = Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(configurator.getTarget(), BinaryEntityContainer.class), "Cannot adapt " + configurator.getTarget() + " to ConsumerFactory");		
		if (contextSupplierFactory == null) {
			return consumerFactory.create(context);
		}
		
		return consumerFactory.contextify(contextSupplierFactory).create(context);
	}	

}
