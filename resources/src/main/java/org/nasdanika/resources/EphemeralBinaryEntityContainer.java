package org.nasdanika.resources;

import org.nasdanika.common.ProgressMonitor;

/**
 * {@link MapContainer} of {@link EphemeralEntity}ies.
 * @author Pavel
 *
 * @param <T>
 */
public class EphemeralBinaryEntityContainer extends MapContainer<BinaryEntity> implements BinaryEntityContainer {

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

	@Override
	protected MapContainer<BinaryEntity> createSubContainer(String path) {
		return new EphemeralBinaryEntityContainer() {

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
	
	
	@Override
	public BinaryEntityContainer getParent() {
		return (BinaryEntityContainer) super.getParent();
	}
	
	@Override
	public BinaryEntityContainer getContainer(String path, ProgressMonitor monitor) {
		return (BinaryEntityContainer) super.getContainer(path, monitor);
	}
	
	@Override
	public BinaryResource find(String path, ProgressMonitor monitor) {
		return (BinaryResource) super.find(path, monitor);
	}
	
}
