package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassDiagram {
	
	private List<DiagramElement> diagramElements = Collections.synchronizedList(new ArrayList<>());
	
	public List<DiagramElement> getDiagramElements() {
		return diagramElements;
	}

}
