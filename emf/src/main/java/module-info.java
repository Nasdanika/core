module org.nasdanika.emf {
	exports org.nasdanika.emf;
	exports org.nasdanika.emf.localization;
	exports org.nasdanika.emf.persistence;
	
	requires transitive org.nasdanika.ncore;
	requires transitive org.nasdanika.drawio;
	requires org.jsoup;
	requires org.eclipse.emf.ecore.xmi;
	requires spring.expression;	
	requires org.eclipse.jgit;
	requires transitive org.apache.poi.ooxml;
	requires spring.core;
	
}