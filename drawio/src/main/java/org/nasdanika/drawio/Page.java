package org.nasdanika.drawio;

public interface Page extends Element {

	Model getModel();

	String getName();
	
	void setName(String name);
	
	String getId();	

}
