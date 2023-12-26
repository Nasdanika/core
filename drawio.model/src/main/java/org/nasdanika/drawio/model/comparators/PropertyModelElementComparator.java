package org.nasdanika.drawio.model.comparators;

import java.util.Comparator;
import java.util.Objects;

import org.nasdanika.drawio.model.ModelElement;

/**
 * Compares {@link ModelElement}s by property value.
 * @author Pavel
 *
 */
public class PropertyModelElementComparator implements Comparator<ModelElement> {
	
	private String property;

	public PropertyModelElementComparator(String property) {
		this.property = property;
	}
	
	@Override
	public int compare(ModelElement o1, ModelElement o2) {				
		if (Objects.equals(o1, o2)) {
			return 0;
		}
		
		if (o1 instanceof ModelElement && o2 instanceof ModelElement) {
			String p1 = ((ModelElement) o1).getProperties().get(property);
			String p2 = ((ModelElement) o2).getProperties().get(property);
			
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

}
