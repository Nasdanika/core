package org.nasdanika.ncore.util;

import java.util.Comparator;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.NamedElement;

public class NamedElementComparator implements Comparator<NamedElement> {
	
	public static NamedElementComparator INSTANCE = new NamedElementComparator();

	@Override
	public int compare(NamedElement a, NamedElement b) {
		if (a == b) {
			return 0;
		}
		if (a == null) {
			return 1;
		}
		if (b == null) {
			return -1;
		}
		if (Util.isBlank(a.getName())) {
			if (Util.isBlank(b.getName())) {
				URI aUri = NcoreUtil.getUri(a);
				URI bUri = NcoreUtil.getUri(b);
				if (aUri == null) {
					if (bUri == null) {
						return a.hashCode() - b.hashCode();
					}
					return 1;
				}
				if (bUri == null) {
					return -1;
				}
				return aUri.toString().compareTo(bUri.toString());
			}
			
			return 1;
		}
		
		if (Util.isBlank(b.getName())) {
			return -1;
		}
		
		return a.getName().compareTo(b.getName());
	}

}
