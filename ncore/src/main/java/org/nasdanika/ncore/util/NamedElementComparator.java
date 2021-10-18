package org.nasdanika.ncore.util;

import java.util.Comparator;

import org.nasdanika.common.Util;
import org.nasdanika.ncore.NamedElement;

public class NamedElementComparator implements Comparator<NamedElement> {
	
	public static NamedElementComparator INSTANCE = new NamedElementComparator();

	@Override
	public int compare(NamedElement a, NamedElement b) {
		if (Util.isBlank(a.getName())) {
			if (Util.isBlank(b.getName())) {
				String aUri = a.getUri();
				String bUri = b.getUri();
				return Util.isBlank(aUri) || Util.isBlank(bUri) ? a.hashCode() - b.hashCode() : aUri.compareTo(bUri);
			}
			
			return 1;
		}
		
		if (Util.isBlank(b.getName())) {
			return -1;
		}
		
		return a.getName().compareTo(b.getName());
	}


}
