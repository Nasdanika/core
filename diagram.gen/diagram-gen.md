This module provides two diagram generators:

* ${javadoc/org.nasdanika.diagram.gen.DrawioGenerator} to generate [Draw.io](https://diagrams.net) diagrams.
* ${javadoc/org.nasdanika.diagram.gen.PlantumlGenerator} to generate [PlantUML](https://plantuml.com) diagrams.

It also provides ${javadoc/org.nasdanika.diagram.gen.Generator} class which delegates to the above classes to generate HTML for embedding diagrams into web pages.

Below are examples of generating Drawio and PlantUML diagrams.

## Drawio

[TestDrawioDiagram](https://github.com/Nasdanika/core/blob/master/diagram.gen/src/test/java/org/nasdanika/diagram/gen/tests/drawio/TestDrawioDiagram.java#L21):

```java
DiagramFactory diagramFactory = DiagramFactory.eINSTANCE;
Diagram diagram = diagramFactory.createDiagram();

DiagramElement awsCloud = diagramFactory.createDiagramElement();
diagram.getElements().add(awsCloud);
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

DrawioGenerator generator = new DrawioGenerator(DiagramGenerator.INSTANCE);
String diagramModel = generator.generateModel(diagram);
File outputDir = new File("target/diagrams");
outputDir.mkdirs();
Files.write(new File(outputDir, "aws.drawio").toPath(), diagramModel.getBytes(StandardCharsets.UTF_8));		
```

## PlantUML

### Sequence

[TestSequenceDiagram](https://github.com/Nasdanika/core/blob/master/diagram.gen/src/test/java/org/nasdanika/diagram/gen/tests/plantuml/TestSequenceDiagram.java#L24):

```java
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

PlantumlGenerator generator = new PlantumlGenerator();

String spec = generator.generateSpec(diagram);
System.out.println(spec);
File outputDir = new File("target/diagrams");
outputDir.mkdirs();
Files.write(new File(outputDir, "sequence.html").toPath(), generator.generateUmlDiagram(diagram).getBytes(StandardCharsets.UTF_8));		
```

### State

[TestStateDiagram](https://github.com/Nasdanika/core/blob/master/diagram.gen/src/test/java/org/nasdanika/diagram/gen/tests/plantuml/TestStateDiagram.java#L28):

```java
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
```