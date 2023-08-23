package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nasdanika.graph.Connection;

public class ClassDiagram {
	
	private List<DiagramElement> diagramElements = Collections.synchronizedList(new ArrayList<>());
	
	public List<DiagramElement> getDiagramElements() {
		return diagramElements;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		List<Relation> relations = new ArrayList<>();
		for (DiagramElement de: diagramElements) {
			for (String line: de.generate()) {
				sb.append(line).append(System.lineSeparator());
			}
			de.accept(e -> {
				if (e instanceof DiagramElement) {
					for (Connection oc: ((DiagramElement) e).getOutgoingConnections()) {
						if (oc instanceof Relation) {
							relations.add((Relation) oc);
						}
					}
				}
			});
		}
		
		for (Relation relation: relations) {
			sb.append(relation).append(System.lineSeparator());
		}
		
		return sb.toString();
	}

}
