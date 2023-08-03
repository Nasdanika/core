package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

public abstract class Type extends Classifier {

	public Type(String name) {
		super(name);
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
		for (Attribute attribute: getAttributes()) {
			ret.add(INDENT + attribute.generate());
		}
		for (Operation operation: getOperations()) {
			ret.add(INDENT + operation.generate());
		}
		if (!getReferences().isEmpty()) {
			ret.add(INDENT + "--");
			for (Reference reference: getReferences()) {
				ret.add(INDENT + reference.generate());
			}
		}
		ret.add("}");
		return ret;
	}	

}
