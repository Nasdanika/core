package org.nasdanika.exec.gen.resources;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.exec.resources.Resource;

public abstract class ResourceConsumerFactoryAdapter<T extends Resource> extends AdapterImpl implements ConsumerFactory<BinaryEntityContainer> {

	protected ResourceConsumerFactoryAdapter(T resource) {
		setTarget(resource);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == ConsumerFactory.class;
	}

	/**
	 * Produces final (physical) resource name from a a name (logical). E.g. "logical" Java package name is dot separated, but container name is slash separated.
	 * @param name
	 * @return
	 */
	protected String finalName(String name) {
		return name;
	}	

}
