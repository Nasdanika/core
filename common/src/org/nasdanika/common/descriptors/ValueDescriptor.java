package org.nasdanika.common.descriptors;

/**
 * Descriptor of a value which can be accessed and modified.
 * @author Pavel
 *
 * @param <T>
 */
public interface ValueDescriptor<T> extends Descriptor {
	
	void set(T value);
	
	default boolean isPresent() {
		return get() != null;
	}
	
	T get();
	
	T getType();
	
	/**
	 * Indicates that get() method shall return non-null value.
	 * @return
	 */
	boolean isRequired();
	
	/**
	 * @return true if value can be set, false if it can only be read.
	 */
	boolean isEditable();

}
