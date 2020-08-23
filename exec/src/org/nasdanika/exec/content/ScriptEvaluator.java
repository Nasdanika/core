package org.nasdanika.exec.content;

import java.net.URL;

import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;

public class ScriptEvaluator implements Marked {
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	public ScriptEvaluator(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) {
		this.marker = marker;
		throw new UnsupportedOperationException();
	}

}
