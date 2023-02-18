module org.nasdanika.maven {
	requires transitive maven.plugin.api;
	requires maven.plugin.annotations;
	requires transitive org.nasdanika.common;
	requires transitive maven.project; 
	
	exports org.nasdanika.maven;
	
}
