package org.nasdanika.drawio;

import java.util.List;

public interface Page extends Element {

	/**
	 * @return A list of models. Usually there is only one model, but technically multiple models are possible.
	 */
	List<Model> getModels();

	String getName();
	
	void setName(String name);

}
