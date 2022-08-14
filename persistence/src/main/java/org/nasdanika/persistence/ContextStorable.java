package org.nasdanika.persistence;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

/**
 * Objects which know how to store themselves to a {@link Map}, {@link List}, or scalar like String, Number, Boolean or Date
 * may implement this interface so they can be stored to a hierarchical structure like YAML or JSON.
 * Also this interface may be used as an adapter interface. In this case an object to be stored is adapted to this interface.
 * @author Pavel
 *
 */
public interface ContextStorable {
	
	Object store(Context context, URL base, ProgressMonitor progressMonitor) throws Exception;	

}
