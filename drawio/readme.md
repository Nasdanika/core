Nasdanika Drawio module provides [Java API](https://docs.nasdanika.org/modules/core/apidocs/org.nasdanika.drawio/module-summary.html) for reading and manipulating [Drawio](https://www.diagrams.net/) diagrams.
It is built on top of [Graph](../graph/index.html).

To use the module add the below dependency to your ``pom.xml``:

```xml
<dependency>
	<groupId>org.nasdanika.core</groupId>
	<artifactId>drawio</artifactId>
	<version>...</version>
</dependency>
```

See https://mvnrepository.com/artifact/org.nasdanika.core/drawio for the latest version.

The module requires Java 11 or above.

[TOC levels=6]

## Overview

The module provides the following interfaces representing elements of a diagram file:

* ${javadoc/org.nasdanika.drawio.Document} - the root object of the API representing a file/resource which contains one or more pages.
* ${javadoc/org.nasdanika.drawio.Page} - a page containing a diagram (Model).
* ${javadoc/org.nasdanika.drawio.Model} - a diagram model containing diagram root.
* ${javadoc/org.nasdanika.drawio.Root} - the root of the model containing layers.
* ${javadoc/org.nasdanika.drawio.Layer} - a diagram may have one or more layers. Layers contain Nodes and Connections.
* ${javadoc/org.nasdanika.drawio.Node} - a node can be connected to other nodes with connections. A node may contain other nodes and connections.
* ${javadoc/org.nasdanika.drawio.Connection} - a connection between two nodes. 

The below diagram shows relationships between the above interfaces including their super-interfaces:

```drawio-resource
./classes.drawio
```

${javadoc/org.nasdanika.drawio.Util} provides utility methods such as ``layout()`` and methods to navigate and query documents and their elements.

## Examples

* [Actions](https://docs.nasdanika.org/demo-drawio-actions/) - Demo of generation of a web site from a drawio diagram
* [Flow](https://docs.nasdanika.org/demo-drawio-flow-actions) - Demo of generation of a web site from a flow drawio diagram
* [Mind map](https://docs.nasdanika.org/demo-drawio-map) - Demo of generation of a web site from a (mind) map drawio diagram

## Creating a new document

To create a new document use ``Document.create()``:

```java
Document document = Document.create(false, null);
Page page = document.createPage();
page.setName("My first new page");
		
Model model = page.getModel();
Root root = model.getRoot();
List<Layer> layers = root.getLayers();
		
// Add layer
Layer newLayer = root.createLayer();
newLayer.setLabel("My new layer");
				
// Add nodes
Node source = newLayer.createNode();
source.setLabel("My source node");
Rectangle sourceGeometry = source.getGeometry();
sourceGeometry.setX(200);
sourceGeometry.setX(100);
sourceGeometry.setWidth(70);
sourceGeometry.setHeight(30);
source.getTags().add("aws");
				
Node target = newLayer.createNode();
target.setLabel("My target node");
target.getGeometry().setBounds(300, 150, 100, 30);
Set<String> targetTags = target.getTags();
targetTags.add("aws");
targetTags.add("azure");
		
// Add connection 
Connection connection = newLayer.createConnection(source, target);
connection.setLabel("My connection");
Map<String, String> connectionStyle = connection.getStyle();
connectionStyle.put("edgeStyle", "orthogonalEdgeStyle");
connectionStyle.put("rounded", "1");
connectionStyle.put("orthogonalLoop", "1");
connectionStyle.put("jettySize", "auto");
connectionStyle.put("html", "1");
				
Files.writeString(new File("new-uncompressed.drawio").toPath(), document.save(null));
```

## Loading

To load use one of ``load()`` methods. The below snippet loads from a class loader resource.

```java
Document document = Document.load(getClass().getResource("compressed.drawio"));
```

It is also possible to load a document from PNG metadata - this can be used with PNG files generated by Drawio editors with "Include copy of my diagram" checked.

```java
List<Document> documents = Document.loadFromPngMetadata(getClass().getResource("illustration.png"));
```

## Saving

Document content can be retrieved as a String using ``save()`` method:

```java
Files.writeString(new File("compressed.drawio").toPath(), document.save(null));
```

To embed a diagram into HTML use ``toHtml()`` method:

```java
Files.writeString(new File("compressed.html").toPath(), document.toHtml(null, "https://cdn.jsdelivr.net/gh/Nasdanika/drawio@dev/src/main/webapp/js/viewer-static.min.js"));
```

## Layout

The API provide a simple layout algorithm which arranges nodes so that they don't overlap. 
It can be used with generated diagrams to make them easier to layout manually after generation.

```java
org.nasdanika.drawio.Util.layout(root, 20);
```

## Page linking

The API extends the page linking concept of Drawio to link an element in one document to a page in another document.
This allows to create a tree/network of linked diagrams. For example, a complex system or process may be modeled in multiple diagrams 
maintained by multiple teams.

To link a page in another document (file):

* To link to the first page in the document set link to ``data:page,<URL>``, where ``<URL>`` is resolved relative to the URL of the current document. Example: ``data:page,compressed.drawio``.
* To link to a specific page set link to ``data:page/name,<URL>#<URL encoded page name>``. Example: ``data:page/name,compressed.drawio#Page+2``

## Traversing

To traverse document elements you can use either ``accept(<visitor>)`` methods or ``stream()`` method.

## EMF

### DrawioResource

${javadoc/org.nasdanika.drawio.emf.DrawioResource} is a base class for mapping of diagram elements to [EMF](https://www.eclipse.org/modeling/emf/) Ecore model elements. 
With DrawioResource Drawio files are treated as model resources which can be loaded into a resource set and as such reference model elements in other resources and be referenced from other resources.
${javadoc/org.nasdanika.html.model.app.drawio.ResourceFactory} is a concrete implementation for mapping diagram elements to  [application model](https://docs.nasdanika.org/modules/html/modules/models/modules/app/modules/model/index.html) [actions](https://docs.nasdanika.org/modules/html/modules/models/modules/app/modules/model/Action.html).

### DrawioEObjectFactory

${javadoc/org.nasdanika.drawio.emf.DrawioEObjectFactory} is a specialization of ${javadoc/org.nasdanika.graph.processor.emf.PropertySourceEObjectFactory} for Drawio diagrams.
It loads semantic information from properties of diagram elements as explained below.
``DrawioEObjectFactory`` is abstract. It does not dictate semantic specification format - subclasses shall implement ``T load(String spec, URI specBase, ProcessorConfig<T> config, ProgressMonitor progressMonitor)`` method.

#### Conifguration properties

##### link-page                

By default semantic elements from linked pages are wired in the same way as element children.
To disable wiring of linked page elements set this property to ``false``.

## Sorting

${javadoc/org.nasdanika.drawio.comparators} package contains comparators for sorting diagram elements based on their label, properties, and geometry.

## Executable diagrams

[Graph](../graph/index.html) documentation explains how to implement graph processors which can be used to make diagrams executable.

## Applications

This section lists some possible applications of Drawio Java API and semantic mappings:

* High Level Design -> Low Level Design -> Implementation. Define High Level Design in diagrams. Then generate a web site with documentation for Low Level Design. Then use semantic mapping or executable diagrams approach to either generate code from diagrams or execute diagrams directly.
* Documentation/books/video courses - create a mind map, generate a site from it, add documentation to diagram elements.
* Reporting - create a diagram of your system/business/effort, generate a documentation site. Enrich diagrams with status - implementation status for systems being built pulled from issue tracker(s), system health status pulled from monitoring systems.

## Semantic mapping

This section explains how to map a Drawio Ecore model loaded from a Drawio diagram to a semantic model describing a particular problem domain. For example, a model describing [architecture of a (software) system](https://architecture.models.nasdanika.org/).
This approach to establishing a correspondence between diagram elements and domain model elements is the opposite to the "traditional" approach where domain model elements are mapped to graphical representations. 

This approach can be useful in the following situations:

* A large body of pre-existing Drawio diagrams needs to be mapped to a semantic model because the expressiveness of the diagrams along is not sufficient - diagram elements need to be associated with additional information.
* Constrained environment: Drawio is available to users/modelers (as a Confluence or VS Code plug-in, desktop or web applications), but introduction of a new tool is problematic.
* Rich behavior is desired in generated HTML documentation. Drawio diagrams can be embedded into web pages and provide behavior such as selection of layers or filtering by tags, but other tools might provide just images with image maps at best.
* Users/modelers may be familiar with Drawio already and modelers would need to learn mapping properties, which can be done gradually. Modeling and mapping activities may be separated - and SME may create a diagram and then it can be mapped to the domain model by a different person. Introduction of a new tool will require both modelers and users (consumers) become familiar with the notation.
* The problem domain is not yet well defined or mapping to multiple problem domains.

Diagram elements can be mapped Ecore EObjects, references, attributes, and operations as explained below. 
For the purposes of mapping ``Page``, ``Model`` and ``Root`` are considered as a single source (representation) element with ``name`` and ``id`` taken from the page and the rest from the root. 
Elements with a linked page are treated as elements containing page elements in addition to any elements they contain on their page. 
If the linked page has an element with ``page-element`` property set to ``true`` then the source element is logically "merged" with the page element instead of the page/model/root.

### EObject

A diagram element can be mapped to a semantic element in the following ways:

* Create a new element
* Reference an element created as part of mapping
* Reference a pre-existing element (look-up)

For connections - incoming-reference/position, source, target, outgoing

For nodes - parent reference, child reference

### EReference

#### Contained element (path, greediness - priority)

parent / child references

#### Connection - reference name, position

source / target references


### EAttribute

* Contained element with value
* Connection target with value

### EOperation

TODO - invoke EOperation with target / contained as an argument, additional arguments
