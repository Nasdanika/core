package org.nasdanika.emf.persistence;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.persistence.LoadTracker;

public abstract class LoadTrackerAdapter extends AdapterImpl implements LoadTracker {

	public LoadTrackerAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return LoadTracker.class == type;
	}

}
