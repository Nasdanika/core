package org.nasdanika.common.resources;

/**
 * Root container which keeps contents in memory.
 * @author Pavel
 *
 * @param <T>
 */
public class MemoryContainer<T> extends AbstractMemoryContainer<T> {

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Container<T> getParent() {
		return null;
	}


}
