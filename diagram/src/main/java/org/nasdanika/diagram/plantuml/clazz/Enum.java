package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

public class Enum extends Classifier {
	
	private List<EnumLiteral> literals = new ArrayList<>();

	public List<EnumLiteral> getLiterals() {
		return literals;
	}
	
}
