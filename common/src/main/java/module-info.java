module org.nasdanika.common {
	
	requires org.apache.commons.codec;
	requires transitive org.json;
	requires org.apache.commons.text;
	requires transitive org.eclipse.emf.common;
	requires transitive org.eclipse.emf.ecore;
	requires transitive org.yaml.snakeyaml;
	requires java.scripting;
	requires flexmark;
	requires flexmark.profile.pegdown;
	requires flexmark.util.ast;
	requires flexmark.util.data;
	requires flexmark.util.misc;
	requires flexmark.util.sequence;
	requires flexmark.util.builder;
	requires org.jsoup;
	requires org.apache.commons.lang3;
	requires transitive java.xml;
	requires transitive spring.expression;
	requires java.desktop;
	requires net.sourceforge.plantuml;
	
	exports org.nasdanika.common;
	exports org.nasdanika.common.descriptors;
	
}