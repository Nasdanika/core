package org.nasdanika.emf.persistence;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;

public class MarkedAdapter extends AdapterImpl implements Marked {
	
	private Marker marker;

	public MarkedAdapter(Marker marker) {
		this.marker = marker;
	}

	@Override
	public Marker getMarker() {
		return marker;
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return Marked.class == type;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + marker;
	}

}
