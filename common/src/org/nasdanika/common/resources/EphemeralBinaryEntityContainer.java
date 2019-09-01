package org.nasdanika.common.resources;

import org.nasdanika.common.ProgressMonitor;

/**
 * {@link MapContainer} of {@link EphemeralEntity}ies.
 * @author Pavel
 *
 * @param <T>
 */
public class EphemeralBinaryEntityContainer extends MapContainer<EphemeralBinaryEntity> implements BinaryEntityContainer<EphemeralBinaryEntity> {

	@Override
	protected EphemeralBinaryEntity createElement(String path, ProgressMonitor monitor) {
		return new EphemeralBinaryEntity() {

			@Override
			public String getName() {
				return path;
			}

			@Override
			public EphemeralBinaryEntityContainer getParent() {
				return EphemeralBinaryEntityContainer.this;
			}
			
		};
	}
	
}
