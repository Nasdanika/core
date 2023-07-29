package org.nasdanika.drawio;

import java.util.List;

/**
 * Represents mxGraphModel element
 * @author Pavel
 *
 */
public interface Model extends Element {
	
	/**
	 * @return Diagram root which contains layers.
	 */
	Root getRoot();
	
	/**
	 * Containing page
	 * @return
	 */
	Page getPage();
	
	@Override
	List<Root> getChildren();

}
