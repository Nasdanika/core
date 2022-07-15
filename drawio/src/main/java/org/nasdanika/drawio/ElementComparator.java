package org.nasdanika.drawio;

import java.util.Comparator;

public interface ElementComparator extends Comparator<Element> {
	
	interface Factory {
		
		String getName();
		
		ElementComparator create(Element parent, String config);
		
	}

}
