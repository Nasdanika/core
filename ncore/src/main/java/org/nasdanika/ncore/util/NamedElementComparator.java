package org.nasdanika.ncore.util;

import java.util.Collection;
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
				Collection<URI> aUris = NcoreUtil.getUris(a);
				Collection<URI> bUris = NcoreUtil.getUris(b);
				if (aUris.isEmpty()) {
					if (bUris.isEmpty()) {
						return a.hashCode() - b.hashCode();
					}
					return 1;
				}
				if (bUris.isEmpty()) {
					return -1;
				}
				return aUris.toString().compareTo(bUris.toString());
			}
			
			return 1;
		}
		
		if (Util.isBlank(b.getName())) {
			return -1;
		}
		
		return a.getName().compareTo(b.getName());
	}

}
