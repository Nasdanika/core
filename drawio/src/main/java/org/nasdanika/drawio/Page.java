package org.nasdanika.drawio;

import java.util.List;

public interface Page extends LinkTarget {

	Model getModel();

	String getName();
	
	void setName(String name);
	
	String getId();	
	
	Document getDocument();
	
	@Override
	List<Model> getChildren();

}
