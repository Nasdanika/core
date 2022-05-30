package org.nasdanika.common.persistence;

import java.util.Collections;
import java.util.List;

public interface Markable {
	
	default void mark(Marker marker) {
		mark(Collections.singletonList(marker));
	}
	
	void mark(List<? extends Marker> markers);

}
