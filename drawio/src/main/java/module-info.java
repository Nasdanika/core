module org.nasdanika.drawio {
	
	requires transitive java.xml;
	requires transitive java.desktop;
	requires org.apache.commons.codec;
	requires org.apache.commons.text;
	requires transitive org.nasdanika.common;
	requires org.eclipse.emf.common;
	
	exports org.nasdanika.drawio;
	
}