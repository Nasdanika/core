package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

public abstract class Type extends Classifier {

	public Type(String name) {
		super(name);
	}
	
	private List<SuperType> superTypes = new ArrayList<>();
	
	public List<SuperType> getSuperTypes() {
		return superTypes;
	}
	
	private List<Attribute> attributes = new ArrayList<>();
	
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	private List<Operation> operations = new ArrayList<>();
	
	public List<Operation> getOperations() {
		return operations;
	}
	
	private List<Attribute> references = new ArrayList<>();
	
	/**
	 * References to elements not present on the diagram are shown as attributes in their own section below attributes and above operations. 
	 * @return
	 */
	public List<Attribute> getReferences() {
		return references;
	}	
	
	@Override
	public List<String> generate() {
		List<String> ret = new ArrayList<>();
		ret.add(generateDeclaration());
		if (!getSuperTypes().isEmpty()) {
			for (SuperType supertype: getSuperTypes()) {
				ret.add(INDENT + supertype.toString());
			}
			ret.add("==");
		}
		for (Attribute attribute: getAttributes()) {
			ret.add(INDENT + attribute.toString());
		}

		if (!getReferences().isEmpty()) {
			ret.add(INDENT + "--");
			for (Attribute reference: getReferences()) {
				ret.add(INDENT + reference.toString());
			}
		}
		
		ret.add("--");
		
		for (Operation operation: getOperations()) {
			ret.add(INDENT + operation.toString());
		}
		
		ret.add("}");
		return ret;
	}	

}
