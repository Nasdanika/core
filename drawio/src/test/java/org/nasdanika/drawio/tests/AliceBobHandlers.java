package org.nasdanika.drawio.tests;

import org.nasdanika.drawio.ElementHandler;
import org.nasdanika.drawio.Node;

public class AliceBobHandlers {
	
	@ElementHandler(selector="my-property=x.z")
	public String bob(Node bob) {
		System.out.println(bob.getLabel());
		return bob.getLabel();
	}

}
