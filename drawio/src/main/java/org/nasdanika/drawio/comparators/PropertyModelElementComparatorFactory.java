package org.nasdanika.drawio.comparators;

import java.util.Objects;

import org.nasdanika.common.Util;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ElementComparator;
import org.nasdanika.drawio.ElementComparator.Factory;
import org.nasdanika.drawio.ModelElement;

public class PropertyModelElementComparatorFactory implements Factory {

	@Override
	public ElementComparator create(String type, String config, Element parent) {
		if (Util.isBlank(config)) {
			throw new IllegalArgumentException("Property name is not provided");
		}
		return new ElementComparator() {
			
			@Override
			public int compare(Element o1, Element o2) {				
				if (Objects.equals(o1, o2)) {
					return 0;
				}
				
				if (o1 instanceof ModelElement && o2 instanceof ModelElement) {
					String p1 = ((ModelElement) o1).getProperty(config);
					String p2 = ((ModelElement) o2).getProperty(config);
					
					if (org.nasdanika.common.Util.isBlank(p1)) {
						if (!org.nasdanika.common.Util.isBlank(p2)) {
							return 1;
						}
					}
					
					if (org.nasdanika.common.Util.isBlank(p2)) {
						if (!org.nasdanika.common.Util.isBlank(p1)) {
							return -1;
						}
					}
					
					if (!org.nasdanika.common.Util.isBlank(p1) && !org.nasdanika.common.Util.isBlank(p2)) {
						int cmp = p1.compareTo(p2);
						if (cmp != 0) {
							return cmp;
						}
					}       					
				}

				return o1.hashCode() - o2.hashCode();
			}
			
		};
	}

	@Override
	public boolean isForType(String type) {
		return "property".equals(type);
	}

}
