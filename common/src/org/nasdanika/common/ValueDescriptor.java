package org.nasdanika.common;

import java.util.List;

/**
 * Descriptor of a value which can be accessed and modified.
 * @author Pavel
 *
 * @param <T>
 */
public interface ValueDescriptor<T> extends Descriptor {
	
	void set(T value);
	
	T get();
	
	T getType();
	
	List<T> getChoices();
	
	/**
	 * Indicates that get() method shall return non-null value.
	 * @return
	 */
	boolean isRequired();

}
