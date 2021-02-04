package org.nasdanika.cli.ext;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

/**
 * Builds context.
 * @author Pavel
 *
 */
public interface ConfigurableContextBuilder {
	
	/**
	 * 
	 * @param config Context builder configuration loaded from YAML
	 * @param context Context built so far, can be used for interpolation
	 * @param progressMonitor Progress monitor
	 * @return
	 */
	Context build(Object config, Context context, ProgressMonitor progressMonitor);

}
