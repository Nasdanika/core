package org.nasdanika.drawio.comparators;

import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ElementComparator;
import org.nasdanika.drawio.ElementComparator.Factory;

public class CartesianModelElementComparatorFactory implements Factory {

	@Override
	public ElementComparator create(String type, String config, Element parent) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isForType(String type) {
		return "vertilcal".equals(type) || "horizontal".equals(type);
	}

}
