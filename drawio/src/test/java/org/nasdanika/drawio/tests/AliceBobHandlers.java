package org.nasdanika.drawio.tests;

import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Handler;

public class AliceBobHandlers {
	
	@Handler("getProperty('my-property') == 'xyz'")
	public String bob(Node bob) {
		System.out.println(bob.getLabel());
		return bob.getLabel();
	}

}
