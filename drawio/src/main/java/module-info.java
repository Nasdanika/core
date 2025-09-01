/**
 * Java API for reading/creating, manipulating, and writing <a href="https://drawio.net">Drawio</a> documents.
 * See <a href="https://docs.nasdanika.org/modules/core/modules/drawio/index.html">Online Documentation</a> for more details.
 */

import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.drawio.processor.DiagramCapabilityFactory;

module org.nasdanika.drawio {
	
	requires transitive java.desktop;
	requires transitive org.nasdanika.graph;
	requires transitive org.nasdanika.drawio.model;
	requires transitive org.nasdanika.mapping;
	requires org.jsoup;
	requires org.apache.commons.text;
	requires org.apache.commons.lang3;
	requires org.eclipse.emf.common;
	requires org.apache.commons.codec;
	
	exports org.nasdanika.drawio;
	exports org.nasdanika.drawio.comparators;
	exports org.nasdanika.drawio.emf;
	exports org.nasdanika.drawio.processor;
	exports org.nasdanika.drawio.gen.section;
	exports org.nasdanika.drawio.gen.section.messages;
	
	provides CapabilityFactory with DiagramCapabilityFactory;
		
}