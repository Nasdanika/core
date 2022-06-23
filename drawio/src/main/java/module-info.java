module org.nasdanika.drawio {
	
	requires transitive java.xml;
	requires transitive java.desktop;
	requires org.apache.commons.codec;
	requires org.json;
	requires org.apache.commons.text;
	requires org.nasdanika.common;
	
	exports org.nasdanika.drawio;
	
}