import org.nasdanika.drawio.ElementComparator;
import org.nasdanika.drawio.impl.comparators.AngularModelElementComparatorFactory;
import org.nasdanika.drawio.impl.comparators.CartesianModelElementComparatorFactory;
import org.nasdanika.drawio.impl.comparators.LabelModelElementComparatorFactory;
import org.nasdanika.drawio.impl.comparators.PropertyModelElementComparatorFactory;

/**
 * Java API for reading/creating, manipulating, and writing <a href="https://drawio.net">Drawio</a> documents.
 * See <a href="https://docs.nasdanika.org/modules/core/modules/drawio/index.html">Online Documentation</a> for more details.
 */
module org.nasdanika.drawio {
	
	requires transitive java.xml;
	requires transitive java.desktop;
	requires org.apache.commons.codec;
	requires org.apache.commons.text;
	requires transitive org.nasdanika.common;
	requires org.eclipse.emf.common;
	requires org.jsoup;
	
	exports org.nasdanika.drawio;
	
	provides ElementComparator.Factory with LabelModelElementComparatorFactory, PropertyModelElementComparatorFactory, CartesianModelElementComparatorFactory, AngularModelElementComparatorFactory;
	
	uses ElementComparator.Factory;
	
}