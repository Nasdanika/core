package org.nasdanika.drawio;

import java.util.Collection;

/**
 * Tags are contained by {@link Page}s, {@link ModelElement} can have zero or more tags.
 */
public interface Tag {
	
	String getName();
	
	/**
	 * @return tagged elements on the page
	 */
	Collection<ModelElement<?>> getElements();

}
