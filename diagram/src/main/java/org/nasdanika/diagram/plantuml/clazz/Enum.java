package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

public class Enum extends DataType {
	
	public Enum(String name) {
		super(name);
	}

	private List<EnumLiteral> literals = new ArrayList<>();

	public List<EnumLiteral> getLiterals() {
		return literals;
	}
	
	@Override
	protected String getType() {
		return "enum";
	}	
	
}
