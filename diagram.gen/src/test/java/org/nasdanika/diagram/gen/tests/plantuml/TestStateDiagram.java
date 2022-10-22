package org.nasdanika.diagram.gen.tests.plantuml;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramFactory;
import org.nasdanika.diagram.End;
import org.nasdanika.diagram.Link;
import org.nasdanika.diagram.Start;
import org.nasdanika.diagram.gen.PlantumlGenerator;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;

public class TestStateDiagram {
	
	@Ignore("No UML dialect support for now")
	@Test
	public void generateStateDiagram() throws Exception {
		DiagramFactory diagramFactory = DiagramFactory.eINSTANCE;
		Diagram diagram = diagramFactory.createDiagram();
		diagram.setHideEmptyDescription(true);
		
		Start start = diagramFactory.createStart();
		diagram.getElements().add(start);
		
		DiagramElement source = diagramFactory.createDiagramElement();
		diagram.getElements().add(source);
		source.setType("state");
		source.setText("Source");
		source.setLocation("https://nasdanika.org");
		source.setTooltip("We start at the home page");
		
		DiagramElement nested = diagramFactory.createDiagramElement();
		source.getElements().add(nested);
		nested.setType("state");
		nested.setText("Nested");	
		nested.setBorder("red");
		nested.setDashed(true);
		
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
		
		Text targetDescriptionText = ContentFactory.eINSTANCE.createText();
		targetDescriptionText.setContent("Description of the target");
		target.getDescription().add(targetDescriptionText);
		
		Connection c2 = diagramFactory.createConnection();
		nested.getConnections().add(c2);
		c2.setTarget(target);				
		
		Link cDescriptionLink = diagramFactory.createLink();
		cDescriptionLink.setLocation("https://www.somewhere.com");
		cDescriptionLink.setText("Achieve your targets");
		c2.getDescription().add(cDescriptionLink);
		c2.setDashed(true);
		c2.setColor("blue");
		c2.setThickness(3);		

		End end = diagramFactory.createEnd();
		diagram.getElements().add(end);

		Connection c3 = diagramFactory.createConnection();
		target.getConnections().add(c3);
		c3.setTarget(end);				
		
		
		PlantumlGenerator generator = new PlantumlGenerator();
		
		String spec = generator.generateSpec(diagram);
		System.out.println(spec);
		File outputDir = new File("target/diagrams");
		outputDir.mkdirs();
		Files.write(new File(outputDir, "state.html").toPath(), generator.generateUmlDiagram(diagram).getBytes(StandardCharsets.UTF_8));		
	}

}
