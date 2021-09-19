package org.nasdanika.diagram.gen.tests.plantuml;

import java.io.File;
import java.nio.file.Files;

import org.junit.Test;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramFactory;
import org.nasdanika.diagram.Link;
import org.nasdanika.diagram.gen.plantuml.Generator;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;

/**
 * Common methods for testing
 * @author Pavel
 *
 */
public class TestSequenceDiagram {
	
	@Test
	public void generateSequenceDiagram() throws Exception {
		DiagramFactory diagramFactory = DiagramFactory.eINSTANCE;
		Diagram diagram = diagramFactory.createDiagram();
		
		DiagramElement source = diagramFactory.createDiagramElement();
		diagram.getElements().add(source);
		source.setType("actor");
		source.setText("Alice");
		source.setLocation("https://nasdanika.org");
		source.setTooltip("We start at the home page");
				
		DiagramElement target = diagramFactory.createDiagramElement();
		diagram.getElements().add(target);
		target.setType("actor");
		Link nameLink = diagramFactory.createLink();
		target.getName().add(nameLink);
		nameLink.setText("Bob");
		nameLink.setLocation("javascript:alert('nospace')");
		nameLink.setTooltip("Achieve unachievable!");
		
		Connection c2 = diagramFactory.createConnection();
		source.getConnections().add(c2);
		c2.setTarget(target);
		c2.setType("->");
		
		Link cDescriptionLink = diagramFactory.createLink();
		cDescriptionLink.setLocation("https://www.somewhere.com");
		cDescriptionLink.setText("Achieve your targets");
		c2.getDescription().add(cDescriptionLink);
		c2.setDashed(true);
		c2.setColor("blue");
		c2.setThickness(3);		
		
		Generator generator = new Generator();
		
		String spec = generator.generateSpec(diagram);
		System.out.println(spec);
		File outputDir = new File("target/diagrams");
		outputDir.mkdirs();
		Files.writeString(new File(outputDir, "sequence.html").toPath(), generator.generateUmlDiagram(diagram));		
	}

}
