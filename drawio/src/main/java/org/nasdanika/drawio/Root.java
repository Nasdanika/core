package org.nasdanika.drawio;

import java.util.List;

public interface Root extends ModelElement<Root> {
	
	List<Layer<?>> getLayers();
	
	Layer<?> createLayer();
	
	@Override
	List<Layer<?>> getChildren();

}
