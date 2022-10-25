package org.nasdanika.drawio;

import java.util.List;

public interface Layer extends ModelElement {

	List<LayerElement> getElements();
	
	Node createNode();
	
	Connection createConnection(Node source, Node target);
	
}
