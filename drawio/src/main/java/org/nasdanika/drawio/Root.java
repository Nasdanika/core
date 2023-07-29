package org.nasdanika.drawio;

import java.util.List;

public interface Root extends ModelElement {
	
	List<Layer> getLayers();
	
	Layer createLayer();
	
	@Override
	List<Layer> getChildren();

}
