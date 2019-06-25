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
	
	/**
	 * Basic implementation of size computation - 1 if contents is not null, zero otherwise.
	 * Otherwrite as needed.
	 * @param contents
	 * @return
	 */
	protected long size(T contents) {
		return contents == null ? 0 : 1;
	}

}
