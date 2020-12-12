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
	
	/**
	 * Adapts source to requested type.
	 * @param source Source object.
	 * @param type Type to adapt to.
	 * @return Instance of the type or null.
	 */
	@SuppressWarnings("unchecked")
	static <T> T adaptTo(Object source, Class<T> type) {
		if (source == null) {
			return (T) source;
		}
		if (type.isInstance(source)) {
			return (T) source;
		}
		if (source instanceof Adaptable) {
			return ((Adaptable) source).adaptTo(type);
		}
		return null;
	}
	

}
