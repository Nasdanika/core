package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

public class Operation extends Member {
	
	private List<Parameter> parameters = new ArrayList<>();

	public List<Parameter> getParameters() {
		return parameters;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getNameString()).append("(");
		boolean isFirst = true;
		for (Parameter parameter: getParameters()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(", ");
			}
			sb.append(parameter);
		}
		
		sb.append(")");
		
		if (!getType().isEmpty()) {
			sb.append(" : ").append(getTypeString());
		}
		
		return sb.append(getLinkString()).toString();
	}

}
