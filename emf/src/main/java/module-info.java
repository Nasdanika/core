module org.nasdanika.emf {
	exports org.nasdanika.emf;
	exports org.nasdanika.emf.localization;
	exports org.nasdanika.emf.persistence;
	
	requires transitive org.nasdanika.ncore;
	requires transitive org.nasdanika.drawio;
	requires transitive org.nasdanika.cli;
	requires transitive info.picocli;	
//	requires org.apache.commons.codec;
	requires org.jsoup;
	requires org.eclipse.emf.ecore.xmi;
	requires spring.expression;	
	requires org.eclipse.jgit;
	requires ant.style.path.matcher;
	requires transitive org.apache.poi.ooxml;
	
}