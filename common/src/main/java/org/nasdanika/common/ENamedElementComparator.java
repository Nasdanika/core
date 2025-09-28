package org.nasdanika.common;

import java.util.Comparator;

import org.eclipse.emf.ecore.ENamedElement;

public class ENamedElementComparator implements Comparator<ENamedElement> {
	
	public static ENamedElementComparator INSTANCE = new ENamedElementComparator();

	@Override
	public int compare(ENamedElement a, ENamedElement b) {
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
				return a.hashCode() - b.hashCode();
			}
			
			return 1;
		}
		
		if (Util.isBlank(b.getName())) {
			return -1;
		}
		
		return a.getName().compareTo(b.getName());
	}

}
