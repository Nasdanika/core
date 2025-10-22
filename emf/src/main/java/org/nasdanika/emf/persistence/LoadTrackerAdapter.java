package org.nasdanika.emf.persistence;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.persistence.LoadTracker;

public abstract class LoadTrackerAdapter extends AdapterImpl implements LoadTracker {
	
	@Override
	public boolean isAdapterForType(Object type) {
		return LoadTracker.class == type;
	}

}
