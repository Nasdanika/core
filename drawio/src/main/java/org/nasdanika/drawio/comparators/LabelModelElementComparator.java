package org.nasdanika.drawio.comparators;

import java.util.Comparator;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.nasdanika.drawio.ModelElement;

/**
 * Compares {@link ModelElement}s by label.
 * @author Pavel
 *
 */
public class LabelModelElementComparator implements Comparator<ModelElement> {
	
	private boolean descending;

	public LabelModelElementComparator(boolean descending) {
		this.descending = descending;
	}

	@Override
	public int compare(ModelElement o1, ModelElement o2) {				
		if (Objects.equals(o1, o2)) {
			return 0;
		}
		
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
				return descending ? -cmp : cmp;
			}
		}       					

		return o1.hashCode() - o2.hashCode();
	}

}
