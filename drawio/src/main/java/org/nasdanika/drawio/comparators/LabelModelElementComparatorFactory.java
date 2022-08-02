package org.nasdanika.drawio.comparators;

import java.util.Objects;

import org.jsoup.Jsoup;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ElementComparator;
import org.nasdanika.drawio.ElementComparator.Factory;
import org.nasdanika.drawio.ModelElement;

public class LabelModelElementComparatorFactory implements Factory {

	@Override
	public ElementComparator create(String type, String config, Element parent) {
		return new ElementComparator() {
			
			@Override
			public int compare(Element o1, Element o2) {				
				if (Objects.equals(o1, o2)) {
					return 0;
				}
				
				if (o1 instanceof ModelElement && o2 instanceof ModelElement) {
					String l1 = ((ModelElement) o1).getLabel();
					if (l1 != null) {
						l1 = Jsoup.parse(l1).text();
					}
					String l2 = ((ModelElement) o2).getLabel();
					if (l2 != null) {
						l2 = Jsoup.parse(l2).text();
					}
					
					if (org.nasdanika.common.Util.isBlank(l1)) {
						if (!org.nasdanika.common.Util.isBlank(l2)) {
							return 1;
						}
					}
					
					if (org.nasdanika.common.Util.isBlank(l2)) {
						if (!org.nasdanika.common.Util.isBlank(l1)) {
							return -1;
						}
					}
					
					if (!org.nasdanika.common.Util.isBlank(l1) && !org.nasdanika.common.Util.isBlank(l2)) {
						int cmp = l1.compareTo(l2);
						if (cmp != 0) {
							return "descending".equals(config) ? -cmp : cmp;
						}
					}       					
				}

				return o1.hashCode() - o2.hashCode();
			}
			
		};
	}

	@Override
	public boolean isForType(String type) {
		return "label".equals(type);
	}

}
