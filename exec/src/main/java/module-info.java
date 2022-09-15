module org.nasdanika.exec {
	exports org.nasdanika.exec;
	exports org.nasdanika.exec.content;
	exports org.nasdanika.exec.content.impl;
	exports org.nasdanika.exec.content.util;
	exports org.nasdanika.exec.impl;
	exports org.nasdanika.exec.resources;
	exports org.nasdanika.exec.resources.impl;
	exports org.nasdanika.exec.resources.util;
	exports org.nasdanika.exec.util;
	
	requires transitive org.nasdanika.emf;
}