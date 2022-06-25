module org.nasdanika.emf {
	exports org.nasdanika.emf;
	exports org.nasdanika.emf.localization;
	exports org.nasdanika.emf.persistence;
	
	requires transitive org.nasdanika.ncore;
	requires transitive org.nasdanika.cli;
	requires transitive info.picocli;
	requires org.eclipse.emf.ecore.xmi;
	requires org.eclipse.jgit;
	requires ant.style.path.matcher;
	
}