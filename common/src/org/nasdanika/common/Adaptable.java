package org.nasdanika.common;

/**
 * Something that can be adapted to a different type.  Can be used to access contextual information.
 * @author Pavel
 *
 */
public interface Adaptable {
	
	/**
	 * Adapts to requested type.
	 * @param type Type to adapt to.
	 * @return Instance of the type or null.
	 */
	@SuppressWarnings("unchecked")
	default <T> T adaptTo(Class<T> type) {
		return type.isInstance(this) ? (T) this : null;
	}

}
