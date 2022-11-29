package org.nasdanika.persistence;

import org.nasdanika.common.Util;

/**
 * Position in a text file - yaml, json, ...
 * @author Pavel Vlasov
 *
 */
public interface Marker {
	
	/**
	 * Position within resource, e.g line number, line and column number, URI fragment, some kind of path, character/byte offset, sheet row and column in Excel documents, ....
	 * @return
	 */
	String getPosition();
	
	/**
	 * Location of resource. E.g. file path or URL.
	 * @return
	 */
	String getLocation();
	
	static String toString(Marker marker) {
		if (marker == null) {
			return "";
		}
		return (Util.isBlank(marker.getLocation()) ? "" : marker.getLocation()) + " " + (Util.isBlank(marker.getPosition()) ? "" : marker.getPosition());
	}	

}
