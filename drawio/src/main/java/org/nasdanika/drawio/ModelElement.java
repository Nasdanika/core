package org.nasdanika.drawio;

import java.util.Map;

public interface ModelElement extends Element {
	
	ModelElement getParent();
	
	String getLabel();
	
	void setLabel(String label);

	String getLink();
	
	void setLink(String link);
	
	String getTooltip();
	
	void setTooltip(String tooltip);
	
	Map<String,String> getStyle();
	
	String getProperty(String name);
	
	void setProperty(String name, String value);
	
}
