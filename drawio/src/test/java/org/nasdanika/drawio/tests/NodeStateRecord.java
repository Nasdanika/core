package org.nasdanika.drawio.tests;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.graph.Connection;

record NodeStateRecord(Connection activator, String input, String result, NodeStateRecord prev, String type) {
	
	List<NodeStateRecord> toList() {
		if (prev == null) {
			List<NodeStateRecord> ret = new ArrayList<>();
			ret.add(this);
			return ret;
		}
		
		List<NodeStateRecord> ret = prev.toList();
		ret.add(this);
		return ret;
	}
	
	
}