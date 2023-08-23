package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

public abstract class Type extends Classifier {

	public Type(String name) {
		super(name);
	}
	
	private List<Supertype> supertypes = new ArrayList<>();
	
	public List<Supertype> getSupertypes() {
		return supertypes;
	}
	
	private List<Attribute> attributes = new ArrayList<>();
	
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	private List<Operation> operations = new ArrayList<>();
	
	public List<Operation> getOperations() {
		return operations;
	}
	
	private List<Reference> references = new ArrayList<>();
	
	public List<Reference> getReferences() {
		return references;
	}	
	
	@Override
	public List<String> generate() {
		List<String> ret = new ArrayList<>();
		ret.add(generateDeclaration());
		if (!getSupertypes().isEmpty()) {
			for (Supertype supertype: getSupertypes()) {
				ret.add(INDENT + supertype.toString());
			}
			ret.add("==");
		}
		for (Attribute attribute: getAttributes()) {
			ret.add(INDENT + attribute.toString());
		}
		
		if (!getAttributes().isEmpty() && !getOperations().isEmpty() && !getSupertypes().isEmpty()) {
			ret.add("--");
		}
		for (Operation operation: getOperations()) {
			ret.add(INDENT + operation.toString());
		}
		if (!getReferences().isEmpty()) {
			ret.add(INDENT + "--");
			for (Reference reference: getReferences()) {
				ret.add(INDENT + reference.toString());
			}
		}
		ret.add("}");
		return ret;
	}	

}
