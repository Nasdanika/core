package org.nasdanika.common.descriptors;

import org.nasdanika.common.Diagnosable;

/**
 * Describes something.
 * @author Pavel
 *
 */
public interface Descriptor extends Diagnosable {
	
	String getIcon();
	
	String getLabel();
	
	String getDescription();
	
	/**
	 * @return true if this descriptor can be used to collect information and false otherwise. A descriptor can be disabled if it depends on other descriptors. 
	 * E.g. configuration descriptors for component A can be disabled if the component A is not selected.
	 */
	boolean isEnabled();

}
