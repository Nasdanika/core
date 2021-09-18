package org.nasdanika.diagram.gen.tests.plantuml;

import java.io.File;
import java.nio.file.Files;

import org.junit.Test;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramFactory;
import org.nasdanika.diagram.End;
import org.nasdanika.diagram.Link;
import org.nasdanika.diagram.Start;
import org.nasdanika.diagram.gen.plantuml.Generator;

/**
 * Common methods for testing
 * @author Pavel
 *
 */
public class TestStateDiagram {
	
	@Test
	public void generateStateDiagram() throws Exception {
		DiagramFactory diagramFactory = DiagramFactory.eINSTANCE;
		Diagram diagram = diagramFactory.createDiagram();
		
		Start start = diagramFactory.createStart();
		diagram.getElements().add(start);
		
		DiagramElement source = diagramFactory.createDiagramElement();
		diagram.getElements().add(source);
		source.setType("state");
		source.setText("Source");
		source.setLocation("https://nasdanika.org");
		source.setTooltip("We start at the home page");
		
		Connection c1 = diagramFactory.createConnection();
		start.getConnections().add(c1);
		c1.setTarget(source);
		
		DiagramElement target = diagramFactory.createDiagramElement();
		diagram.getElements().add(target);
		target.setType("state");
		Link nameLink = diagramFactory.createLink();
		target.getName().add(nameLink);
		nameLink.setText("Target");
		nameLink.setLocation("javascript:alert('nospace')");
		nameLink.setTooltip("Achieve unachievable!");
		
		Connection c2 = diagramFactory.createConnection();
		source.getConnections().add(c2);
		c2.setTarget(target);				

		End end = diagramFactory.createEnd();
		diagram.getElements().add(end);

		Connection c3 = diagramFactory.createConnection();
		target.getConnections().add(c3);
		c3.setTarget(end);				
		
		
		Generator generator = new Generator();
		
		String spec = generator.generateSpec(diagram);
		System.out.println(spec);
		Files.writeString(new File("target/diagram.html").toPath(), generator.generateUmlDiagram(diagram));		
	}
	

}
