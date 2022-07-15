import org.nasdanika.drawio.ElementComparator;
import org.nasdanika.drawio.impl.comparators.LabelModelElementComparatorFactory;

module org.nasdanika.drawio {
	
	requires transitive java.xml;
	requires transitive java.desktop;
	requires org.apache.commons.codec;
	requires org.apache.commons.text;
	requires transitive org.nasdanika.common;
	requires org.eclipse.emf.common;
	requires org.jsoup;
	
	exports org.nasdanika.drawio;
	
	provides ElementComparator.Factory with LabelModelElementComparatorFactory;
	
	uses ElementComparator.Factory;
	
}