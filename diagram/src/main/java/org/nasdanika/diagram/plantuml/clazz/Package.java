package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;

public class Package extends DiagramElement {
	
	private List<Classifier> classifiers = new ArrayList<>();

	public List<Classifier> getClassifiers() {
		return classifiers;
	}

	@Override
	public Result generate(URI base) {
		
		// Lines and relations
		StringBuilder sb = new StringBuilder("package ");
		
		sb.append("{").append(System.lineSeparator());
		
		// Classifiers with indent
		
		sb.append(false)
		return null;
	}
	

}
