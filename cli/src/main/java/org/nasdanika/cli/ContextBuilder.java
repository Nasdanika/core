package org.nasdanika.cli;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

/**
 * Builds context.
 * @author Pavel
 *
 */
public interface ContextBuilder {
	
	/**
	 * @param context Context built so far, can be used for interpolation
	 * @param progressMonitor Progress monitor
	 * @return New context built or the context passed as an argument.
	 */
	Context build(Context context, ProgressMonitor progressMonitor) throws Exception;

}
