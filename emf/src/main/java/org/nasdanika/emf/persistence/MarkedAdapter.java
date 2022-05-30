package org.nasdanika.emf.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;

public class MarkedAdapter extends AdapterImpl implements Marked {
	
	private List<? extends Marker> markers;

	public MarkedAdapter(List<? extends Marker> markers) {
		this.markers = markers;
	}

	@Override
	public List<? extends Marker> getMarkers() {
		return markers;
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return Marked.class == type;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + markers == null ? "(empty)" : markers.stream().map(Object::toString).collect(Collectors.joining(", "));
	}

}
