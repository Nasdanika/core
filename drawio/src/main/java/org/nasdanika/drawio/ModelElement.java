package org.nasdanika.drawio;

public interface ModelElement extends Element {
	
	ModelElement getParent();
	
	String getLabel();
	
	void setLabel(String label);

	String getLink();
	
	void setLink(String link);
	
	String getTooltip();
	
	void setTooltip(String tooltip);
	
}
