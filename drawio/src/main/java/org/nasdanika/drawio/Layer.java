package org.nasdanika.drawio;

import java.util.List;

public interface Layer extends ModelElement {

	List<ModelElement> getElements();
	
	Node createNode();
	
	Connection createConnection(Node source, Node target);
	
}
