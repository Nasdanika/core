package org.nasdanika.persistence;

import org.nasdanika.common.Util;

/**
 * Position in a text file - yaml, json, ...
 * @author Pavel Vlasov
 *
 */
public interface Marker {
	
	int getLine();
	
	int getColumn();
	
	String getLocation();
	
	static String toString(Marker marker) {
		if (marker == null) {
			return "";
		}
		return (Util.isBlank(marker.getLocation()) ? "" : marker.getLocation()) + " " + marker.getLine() + ":" + marker.getColumn();
	}	

}
