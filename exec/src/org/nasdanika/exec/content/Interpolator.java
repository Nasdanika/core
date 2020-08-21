package org.nasdanika.exec.content;

import java.net.URL;

import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marker;

public class Interpolator extends Filter {

	public Interpolator(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
	}

	@Override
	protected String filter(Context context, String input) {
		return context.interpolateToString(input);
	}

}
