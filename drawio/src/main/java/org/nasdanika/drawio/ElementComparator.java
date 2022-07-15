package org.nasdanika.drawio;

import java.util.Comparator;

public interface ElementComparator extends Comparator<Element> {
	
	interface Factory {
		
		boolean isForType(String type);
		
		ElementComparator create(String type, String config, Element parent);
		
	}

}
