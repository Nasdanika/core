/**
 * Java API for reading/creating, manipulating, and writing <a href="https://drawio.net">Drawio</a> documents.
 * See <a href="https://docs.nasdanika.org/modules/core/modules/drawio/index.html">Online Documentation</a> for more details.
 */
module org.nasdanika.drawio {
	
	requires transitive java.xml;
	requires transitive java.desktop;
	requires transitive org.nasdanika.graph;
	requires org.apache.commons.codec;
	requires org.apache.commons.text;
	requires org.apache.commons.lang3;
	requires transitive org.nasdanika.common;
	requires org.eclipse.emf.common;
	requires org.jsoup;
	requires spring.expression;
	
	exports org.nasdanika.drawio;
	exports org.nasdanika.drawio.comparators;
		
}