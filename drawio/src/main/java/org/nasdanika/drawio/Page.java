package org.nasdanika.drawio;

import java.util.List;
import java.util.Map;

public interface Page extends LinkTarget {

	Model getModel();

	String getName();
	
	void setName(String name);
	
	String getId();	
	
	Document getDocument();
	
	@Override
	List<Model> getChildren();
	
	/**
	 * @return Unmodifiable map of tags.
	 */
	Map<String,Tag> getTags();
	
	Tag createTag(String name);

}
