package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.common.Util;
import org.nasdanika.diagram.plantuml.Link;

public abstract class Classifier extends DiagramElement {
	
	private String stereotype;
	
	public Classifier(String name) {
		getName().add(new Link(name));	
	}

	public String getStereotype() {
		return stereotype;
	}

	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}
		
	protected abstract String getType();
	
	@Override
	public List<String> generate() {
		List<String> ret = new ArrayList<>();
		ret.add(generateDeclaration());
		ret.add("}");
		return ret;
	}

	protected String generateDeclaration() {
		StringBuilder ret = new StringBuilder();
		ret.append(getType()).append(" ");
		if (Util.isBlank(getId())) {
			getName().forEach(ret::append);
			ret.append(" ");
		} else {
			ret.append(getId()).append(" as \"");
			getName().forEach(ret::append);
			ret.append("\" ");
		}
		
		// TODO - generic parameters and styling
		
		ret.append(getLinkString());
		
		if (getStyle() != null) {
			ret.append(" ").append(getStyle());
		}
		
		return ret.append(" {").toString();
	}

}
