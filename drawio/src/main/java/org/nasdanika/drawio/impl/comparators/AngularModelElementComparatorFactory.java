package org.nasdanika.drawio.impl.comparators;

import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ElementComparator;
import org.nasdanika.drawio.ElementComparator.Factory;

public class AngularModelElementComparatorFactory implements Factory {

	@Override
	public ElementComparator create(String type, String config, Element parent) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isForType(String type) {
		return "clockwise".equals(type) || "counterclockwise".equals(type);
	}

}
