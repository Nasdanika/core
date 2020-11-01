package org.nasdanika.common.descriptors;

import org.nasdanika.common.Diagnostic;

/**
 * Descriptor of a value which can be accessed and modified.
 * @author Pavel
 *
 * @param <T>
 */
public interface ValueDescriptor extends Descriptor {
	
	/**
	 * Enumeration for giving UI generators a hint regarding which control to create for the value.
	 * Depending on a generator the hint may be ignored.
	 * Radio and check boxes may be used for boolean values or non-boolean values with choices. 
	 * E.g. check boxes can be used for multi-value descriptors. 
	 * @author Pavel
	 *
	 */
	enum Control {
		DATE, TIME, DROP_DOWN, CHECKBOX, RADIO, FILE, NUMBER, PASSWORD, TEXT, TEXT_AREA		
	}
	
	/**
	 * Sets new value.
	 * @param value
	 * @return Diagnostic, in particular an error diagnostic if value could not be set.
	 */
	Diagnostic set(Object value);
	
//	default boolean isPresent() {
//		return get() != null;
//	}
	
	Object get();
	
//	Class<?> getType();
	
	int getLowerBound();
	
	int getUpperBound();
	
	/**
	 * @return true if value can be set, false if it can only be read.
	 */
	boolean isEditable();
	
	Control getControlHint();

}
