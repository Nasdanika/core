package org.nasdanika.common.descriptors;

/**
 * Descriptor of a value choice which can be accessed and modified.
 * @author Pavel
 *
 * @param <T>
 */
public interface ChoiceDescriptor extends Descriptor {
		
	Object get();
	
}
