package org.nasdanika.common.persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * List with markers for elements.
 * @author Pavel Vlasov
 *
 * @param <E>
 */
@SuppressWarnings("serial")
public class MarkedArrayList<E> extends ArrayList<E> implements Marked, Markable {
	
	private List<Marker> markers = new ArrayList<>();
	
	public List<Marker> getMarkers() {
		return markers;
	}
	
	private Marker marker;

	@Override
	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	@Override
	public Marker getMarker() {
		return marker;
	}

}
