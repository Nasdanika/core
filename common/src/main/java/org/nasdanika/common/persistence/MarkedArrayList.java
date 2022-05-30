package org.nasdanika.common.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * List with markers for elements.
 * @author Pavel Vlasov
 *
 * @param <E>
 */
@SuppressWarnings("serial")
public class MarkedArrayList<E> extends ArrayList<E> implements Marked, Markable {
	
	private List<List<? extends Marker>> elementMarkers = new ArrayList<>();
	private List<? extends Marker> markers;
	
	public List<List<? extends Marker>> getElementMarkers() {
		return elementMarkers;
	}
	
	@Override
	public List<? extends Marker> getMarkers() {
		return Collections.unmodifiableList(markers);
	}

	@Override
	public void mark(List<? extends Marker> markers) {
		this.markers = markers;
	}

}
