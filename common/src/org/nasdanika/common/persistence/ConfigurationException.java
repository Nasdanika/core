package org.nasdanika.common.persistence;

import org.nasdanika.common.NasdanikaException;

/**
 * Exception to indicate error in configuration resources.
 * @author Pavel
 *
 */
@SuppressWarnings("serial")
public class ConfigurationException extends NasdanikaException {
	
	private Marker marker;

	public ConfigurationException(String message, Marker marker) {
		super(marker == null ? message : message + " at " + marker);
		this.marker = marker;
	}

	public ConfigurationException(String message, Throwable cause, Marker marker) {
		super(marker == null ? message : message + " at " + marker, cause);
		this.marker = marker;
	}
	
	public Marker getMarker() {
		return marker;
	}

}
