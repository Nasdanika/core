module org.nasdanika.ncore {
	exports org.nasdanika.ncore;
	exports org.nasdanika.ncore.impl;
	exports org.nasdanika.ncore.util;	
	requires transitive org.nasdanika.persistence;
	requires org.eclipse.emf.ecore.xmi;
}