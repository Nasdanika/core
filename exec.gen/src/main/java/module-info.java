module org.nasdanika.exec.gen{
	exports org.nasdanika.exec.gen;
	exports org.nasdanika.exec.gen.resources;
	exports org.nasdanika.exec.gen.content;
	
	requires transitive org.nasdanika.exec;
	requires transitive org.nasdanika.ncore.gen;
	requires org.apache.commons.codec;
}