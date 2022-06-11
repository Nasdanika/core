package org.nasdanika.diagram.gen.tests.drawio;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.Test;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramFactory;
import org.nasdanika.diagram.Layer;
import org.nasdanika.diagram.gen.DrawioGenerator;
import org.nasdanika.ncore.MapProperty;
import org.nasdanika.ncore.NcoreFactory;

public class TestDrawioDiagram {
	
	@Test
	public void generateDrawioDiagram() throws Exception {
		Diagram diagram = createAwsDiagram(null);
	
		DrawioGenerator generator = new DrawioGenerator(DiagramGenerator.INSTANCE);
		String diagramModel = generator.generateModel(diagram);
		File outputDir = new File("target/diagrams");
		outputDir.mkdirs();
		Files.write(new File(outputDir, "aws.drawio").toPath(), diagramModel.getBytes(StandardCharsets.UTF_8));		
	}

	protected Diagram createAwsDiagram(String pageUUID) {
		DiagramFactory diagramFactory = DiagramFactory.eINSTANCE;
		Diagram diagram = diagramFactory.createDiagram();
		diagram.setName("AWS");
		
		DiagramElement awsCloud = diagramFactory.createDiagramElement();
		diagram.getElements().add(awsCloud);
		awsCloud.setText("AWS Cloud");
		
		DiagramElement awsS3 = diagramFactory.createDiagramElement();
		awsCloud.getElements().add(awsS3);
		awsS3.setText("AWS S3 Bucket");
		awsS3.setLocation(Util.isBlank(pageUUID) ? "https://aws.amazon.com/s3/" : "data:page/id," + pageUUID);
		awsS3.setTooltip("Object storage built to retrieve any amount of data from anywhere");
		
		// Styling
		MapProperty drawioProperties = NcoreFactory.eINSTANCE.createMapProperty();
		awsS3.getProperties().add(drawioProperties);
		drawioProperties.setName("drawio");
		MapProperty awsS3Style = NcoreFactory.eINSTANCE.createMapProperty();
		awsS3Style.setName("style");
		drawioProperties.getValue().add(awsS3Style);
		awsS3Style.put("shape", "mxgraph.aws4.bucket");
		awsS3Style.put("fillColor", "#277116");
		awsS3Style.put("verticalLabelPosition", "bottom");
		awsS3Style.put("verticalAlign", "top");
		
		drawioProperties.put("height", 78);
		drawioProperties.put("width", 75);
						
		DiagramElement awsCodePipeline = diagramFactory.createDiagramElement();
		awsCloud.getElements().add(awsCodePipeline);
		awsCodePipeline.setText("AWS CodePipeline");
		awsCodePipeline.setLocation("https://aws.amazon.com/codepipeline/");
		awsCodePipeline.setTooltip("Automate continuous delivery pipelines for fast and reliable updates");
		
		Connection connection = diagramFactory.createConnection();
		awsS3.getConnections().add(connection);
		connection.setTarget(awsCodePipeline);
		return diagram;
	}
	
	@Test
	public void generateLayeredDrawioDiagram() throws Exception {
		Diagram diagram = createLayeredAwsDiagram();
	
		DrawioGenerator generator = new DrawioGenerator(DiagramGenerator.INSTANCE);
		String diagramModel = generator.generateModel(diagram);
		File outputDir = new File("target/diagrams");
		outputDir.mkdirs();
		Files.write(new File(outputDir, "aws-layered.drawio").toPath(), diagramModel.getBytes(StandardCharsets.UTF_8));		
	}

	protected Diagram createLayeredAwsDiagram() {
		DiagramFactory diagramFactory = DiagramFactory.eINSTANCE;
		Diagram diagram = diagramFactory.createDiagram();
		diagram.setName("Layered AWS");;
		
		Layer awsLayer = diagramFactory.createLayer();
		awsLayer.setName("AWS");
		diagram.getLayers().add(awsLayer);
		
		DiagramElement awsCloud = diagramFactory.createDiagramElement();
		awsLayer.getElements().add(awsCloud);
		awsCloud.setText("AWS Cloud");
		
		DiagramElement awsS3 = diagramFactory.createDiagramElement();
		awsCloud.getElements().add(awsS3);
		awsS3.setText("AWS S3 Bucket");
		awsS3.setLocation("https://aws.amazon.com/s3/");
		awsS3.setTooltip("Object storage built to retrieve any amount of data from anywhere");
		
		// Styling
		MapProperty drawioProperties = NcoreFactory.eINSTANCE.createMapProperty();
		awsS3.getProperties().add(drawioProperties);
		drawioProperties.setName("drawio");
		MapProperty awsS3Style = NcoreFactory.eINSTANCE.createMapProperty();
		awsS3Style.setName("style");
		drawioProperties.getValue().add(awsS3Style);
		awsS3Style.put("shape", "mxgraph.aws4.bucket");
		awsS3Style.put("fillColor", "#277116");
		awsS3Style.put("verticalLabelPosition", "bottom");
		awsS3Style.put("verticalAlign", "top");
		
		drawioProperties.put("height", 78);
		drawioProperties.put("width", 75);
						
		DiagramElement awsCodePipeline = diagramFactory.createDiagramElement();
		awsCloud.getElements().add(awsCodePipeline);
		awsCodePipeline.setText("AWS CodePipeline");
		awsCodePipeline.setLocation("https://aws.amazon.com/codepipeline/");
		awsCodePipeline.setTooltip("Automate continuous delivery pipelines for fast and reliable updates");
		
		Connection connection = diagramFactory.createConnection();
		awsS3.getConnections().add(connection);
		connection.setTarget(awsCodePipeline);
		return diagram;
	}
	
	@Test
	public void generateMultiPageDrawioDiagram() throws Exception {
		Diagram awsLayeredDiagram = createLayeredAwsDiagram();		
		Diagram awsDiagram = createAwsDiagram(awsLayeredDiagram.getUuid());
	
		DrawioGenerator generator = new DrawioGenerator(DiagramGenerator.INSTANCE);
		String diagramModel = generator.generateModel(awsDiagram, awsLayeredDiagram);
		File outputDir = new File("target/diagrams");
		outputDir.mkdirs();
		Files.write(new File(outputDir, "aws-multi-page.drawio").toPath(), diagramModel.getBytes(StandardCharsets.UTF_8));		
	}
	

}
