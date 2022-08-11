package org.nasdanika.common.resources;

import java.io.InputStream;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

/**
 * Merges new and old content of a {@link BinaryEntity}.
 * @author Pavel Vlasov
 *
 */
public interface Merger {
	
	InputStream merge(Context context, BinaryEntity entity, InputStream oldContent, InputStream newContent, ProgressMonitor progressMonitor);
	
}
