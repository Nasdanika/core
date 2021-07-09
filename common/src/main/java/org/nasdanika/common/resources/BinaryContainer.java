package org.nasdanika.common.resources;

import java.io.InputStream;

import org.nasdanika.common.ProgressMonitor;

/**
 * Binding of container to InputStream.
 * @author Pavel
 *
 */
public interface BinaryContainer extends Container<InputStream> {
	
	@Override
	BinaryContainer getContainer(String path, ProgressMonitor monitor);
	
	@Override
	BinaryContainer getParent();

}
