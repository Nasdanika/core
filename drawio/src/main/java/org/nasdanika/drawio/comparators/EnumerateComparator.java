package org.nasdanika.drawio.comparators;

import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Pattern;

import org.nasdanika.drawio.ModelElement;

/**
 * Compares model elements using <code>enumerateValue</code>.
 * Elements without enumerate value are considered equal to any other elements including those
 * with enumerate value. 
 * This is done to allow chaining with, say, flow comparator. 
 * As a result, this comparator will violate the transitivity requirement if some elements don't have enumerateValue. 
 * Therefore, it shall be chained with other comparators. For example, flow and then position.
 *     
 * Enumerate value is treated as path of dot-separated values and two enumerate values are compared
 * element-by-element with elements containing only digits parsed and compared as integers.
 * For example, 20 would be greater than 3 and 1.1.1 greater than 1.1 and smaller than 2.5.6 or 3.
 * Numbers are considered smaller than strings 1.12 is smaller than 1.a
 * 
 * Practical use - ordering connections emanating from the same node. Say, excursions from the same location.  
 * If those excursions have multiple segments, then this comparator can be chained with the flow comparator
 * and possibly terminated by the position comparator just in case.
 * 
 * @author Pavel
 *
 */
public class EnumerateComparator implements Comparator<ModelElement> {
	
	private Object[] parse(Object enumerateValue) {
		if (enumerateValue instanceof String) {
			String[] sa = ((String) enumerateValue).split("\\.");
			Object[] ret = new Object[sa.length];
			for (int i = 0; i < sa.length; ++i) {
				if (Pattern.matches("\\d+", sa[i])) {
					ret[i] = Long.parseLong(sa[i]);
				} else {
					ret[i] = sa[i];
				}
			}
			return ret;
		}
		
		return new Object[] { enumerateValue };
	}
		
	@Override
	public int compare(ModelElement o1, ModelElement o2) {
		if (Objects.equals(o1, o2)) {
			return 0;
		}
		
		if (o1 == null) {
			return 1;
		}
		
		if (o2 == null) {
			return -1;
		}
	
		Object ev1 = o1.getEnumarateValue();
		Object ev2 = o2.getEnumarateValue();
		if (ev1 == null || ev2 == null) {
			return 0;
		}
		
		Object[] eva1 = parse(ev1);
		Object[] eva2 = parse(ev2);
		
		for (int i = 0; i < Math.min(eva1.length, eva2.length); ++i) {
			if (eva1[i] instanceof Long) {
				int cmp = eva2[i] instanceof Long ? ((Long) eva1[i]).compareTo((Long) eva2[i]) : -1;
				if (cmp != 0) {
					return cmp;
				}
				continue;
			}
			if (eva2[i] instanceof Long) {
				return 1; 
			}
			int cmp = ((String) eva1[i]).compareTo((String) eva2[i]);
			if (cmp != 0) {
				return cmp;
			}			
		}
		
		return eva1.length - eva2.length;
	}

}
