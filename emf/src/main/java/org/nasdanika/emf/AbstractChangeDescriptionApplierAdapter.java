package org.nasdanika.emf;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

/**
 * Applies change description to the mapping source or some underlying data behind a resource
 */
public abstract class AbstractChangeDescriptionApplierAdapter extends AdapterImpl implements ChangeDescriptionApplier {
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == ChangeDescriptionApplier.class;
	}	

}
