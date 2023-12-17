module org.nasdanika.drawio.model {
	exports org.nasdanika.drawio.model;
	exports org.nasdanika.drawio.model.comparators;
	exports org.nasdanika.drawio.model.impl;
	exports org.nasdanika.drawio.model.util;
	
	requires transitive org.nasdanika.ncore;
	requires org.jsoup;

}