package org.nasdanika.drawio;

import java.util.Comparator;

/**
 * Comparator of elements.
 * @author Pavel
 *
 */
public interface ElementComparator extends Comparator<Element> {
	
	/**
	 * Service interface.
	 * @author Pavel
	 *
	 */
	interface Factory {
				
		boolean isForType(String type);
		
		ElementComparator create(String type, String config, Element parent);
		
	}

}
