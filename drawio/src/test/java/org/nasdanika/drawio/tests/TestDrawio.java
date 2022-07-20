package org.nasdanika.drawio.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Rectangle;
import org.nasdanika.drawio.Root;

public class TestDrawio {

	@Test
	public void testCompressed() throws Exception {
		Document document = Document.load(getClass().getResource("compressed.drawio"));
		assertThat(document.getElement().getTagName()).isEqualTo("mxfile");
		BiFunction<Element, Map<Element, String>, String> visitor = (element, childResults) -> {
			return element.getElement().getTagName() + "[" + element.getElement().getAttribute("id") + "] " + childResults;
		};
		String result = document.accept(visitor, null);
		System.out.println(result);
		
		for (Page page: document.getPages()) {
			System.out.println("Page: " + page.getName());
			Model model = page.getModel();
			Root root = model.getRoot();
			for (Layer layer: root.getLayers()) {
				System.out.println("\tLayer: " + layer.getLabel());
				for (ModelElement modelElement: layer.getElements()) {
					modelElement.setTooltip("Karamba");
					modelElement.setLink("https://nasdanika.org");
				}
				
			}
		}
		
		Files.writeString(new File("target/compressed.drawio").toPath(), document.save(null));
		Files.writeString(new File("target/compressed.html").toPath(), document.toHtml(null, "https://cdn.jsdelivr.net/gh/Nasdanika/drawio@dev/src/main/webapp/js/viewer-static.min.js"));

		Files.writeString(new File("target/decompressed.drawio").toPath(), document.save(false));
		Files.writeString(new File("target/decompressed.html").toPath(), document.toHtml(false, "https://cdn.jsdelivr.net/gh/Nasdanika/drawio@dev/src/main/webapp/js/viewer-static.min.js"));
		
	}
	
	@Test
	public void testUncompressed() throws Exception {
		Document document = Document.load(getClass().getResource("uncompressed.drawio"));
		assertThat(document.getElement().getTagName()).isEqualTo("mxfile");
		assertThat(document.getPages().size()).isEqualTo(2);
		
		BiFunction<Element, Map<Element, String>, String> visitor = (element, childResults) -> {
			return element.getElement().getTagName() + "[" + element.getElement().getAttribute("id") + "] " + childResults;
		};
		String result = document.accept(visitor, null);
		System.out.println(result);
		
		for (Page page: document.getPages()) {
			System.out.println("Page: " + page.getName());
			Model model = page.getModel();
			Root root = model.getRoot();
			for (Layer layer: root.getLayers()) {
				System.out.println("\tLayer: " + layer.getLabel());
				for (ModelElement modelElement: layer.getElements()) {
					dump(modelElement, "\t\t");
				}
				
			}
		}
		Files.writeString(new File("target/uncompressed.drawio").toPath(), document.save(null));
		Files.writeString(new File("target/uncompressed.html").toPath(), document.toHtml(null, "https://cdn.jsdelivr.net/gh/Nasdanika/drawio@dev/src/main/webapp/js/viewer-static.min.js"));
		Files.writeString(new File("target/recompressed.drawio").toPath(), document.save(true));
		Files.writeString(new File("target/recompressed.html").toPath(), document.toHtml(true, "https://cdn.jsdelivr.net/gh/Nasdanika/drawio@dev/src/main/webapp/js/viewer-static.min.js"));
	}
	
	@Test
	public void testNewUncompressed() throws Exception {
		Document document = Document.create(false, null);
		Page page = document.createPage();
		page.setName("My first new page");
		
		Model model = page.getModel();
		Root root = model.getRoot();
		List<Layer> layers = root.getLayers();
		assertThat(layers).singleElement();
		
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
				
		Files.writeString(new File("target/new-uncompressed.drawio").toPath(), document.save(null));
	}	
	
	private void dump(ModelElement modelElement, String indent) throws TransformerException, IOException {
		modelElement.setTooltip("Kurumba");
		modelElement.setLink("https://nasdanika.org");
		
		System.out.print(indent);
		System.out.println(modelElement.getClass() + " " + modelElement.getLabel());
		if (modelElement instanceof Node) {
			Node node = (Node) modelElement;
			List<Connection> outboundConnections = node.getOutboundConnections();
			if (!outboundConnections.isEmpty()) {
				System.out.println(indent + "\tOutbound connections:");
				for (Connection connection: outboundConnections) {
					Node target = connection.getTarget();
					System.out.println(indent + "\t\t" + connection.getLabel() + " -> " + target.getLabel());
				}
			}
			List<Connection> inboundConnections = node.getInboundConnections();
			if (!inboundConnections.isEmpty()) {
				System.out.println(indent + "\tInbound connections:");
				for (Connection connection: inboundConnections) {
					System.out.println(indent + "\t\t" + connection.getLabel() + " <- " + connection.getSource().getLabel());
				}
			}
			List<ModelElement> children = node.getElements();
			if (!children.isEmpty()) {
				System.out.println(indent + "\tChildren:");
				for (ModelElement child: children) {
					dump(child, indent + "\t\t");
				}
			}
		} else if (modelElement instanceof Connection) {
			Connection connection = (Connection) modelElement;
			System.out.println(indent + "\tSource: " + connection.getSource().getLabel());
			System.out.println(indent + "\tTarget: " + connection.getTarget().getLabel());			
		}		
	}	
	
	@Test
	public void testStream() throws Exception {
		Document document = Document.load(getClass().getResource("uncompressed.drawio"));
		long actors = document.stream(ConnectionBase.SOURCE).filter(ModelElement.class::isInstance).map(ModelElement.class::cast).filter(me -> "Actor".equals(me.getLabel())).count();
		assertThat(actors).isEqualTo(1);
	}
	
	@Test
	public void testURI() throws Exception {
		Document document = Document.load(getClass().getResource("uris.drawio"));
		URI base = URI.createURI("nasdanika://drawio/tests/");
		Function<ModelElement, URI> uriProvider = e -> {
			String uriProperty = e.getProperty("uri");
			if (Util.isBlank(uriProperty)) {
				return null;
			}
			URI uri = URI.createURI(uriProperty);
			return uri.appendSegment(""); // Adding a hanging / for "under the parent" resolution.
		};
		
		Optional<ModelElement> actorOptional = document.stream(null).filter(ModelElement.class::isInstance).map(ModelElement.class::cast).filter(me -> "Actor".equals(me.getLabel())).findFirst();
		assertThat(actorOptional.isPresent()).isEqualTo(true);
		URI actorURI = actorOptional.get().resolveURI(base, uriProvider, null);
		assertThat(actorURI).isEqualTo(URI.createURI("nasdanika://drawio/tests/root/container/actor/"));
		
		Optional<ModelElement> simpleConnectionOptional = document.stream(ConnectionBase.SOURCE).filter(ModelElement.class::isInstance).map(ModelElement.class::cast).filter(me -> "Simple Connection".equals(me.getLabel())).findFirst();
		assertThat(simpleConnectionOptional.isPresent()).isEqualTo(true);
		URI simpleConnectionURI = simpleConnectionOptional.get().resolveURI(base, uriProvider, ConnectionBase.SOURCE);
		assertThat(simpleConnectionURI).isEqualTo(URI.createURI("nasdanika://drawio/tests/root/simple-shape/simple-connection/"));
		
		Optional<ModelElement> toActorConnectionOptional = document.stream(ConnectionBase.SOURCE).filter(ModelElement.class::isInstance).map(ModelElement.class::cast).filter(me -> "To Actor".equals(me.getLabel())).findFirst();
		assertThat(toActorConnectionOptional.isPresent()).isEqualTo(true);
		URI toActorConnectionURI = toActorConnectionOptional.get().resolveURI(base, uriProvider, ConnectionBase.TARGET);
		assertThat(toActorConnectionURI).isEqualTo(URI.createURI("nasdanika://drawio/tests/root/container/actor/to-actor/"));
	}
	
	@Test 
	public void testUpOffsetGenerator() {
		Supplier<Point> upOffsetGenerator = org.nasdanika.drawio.Util.createOffsetGenerator(10, false);
		int x = 0;
		int y = 0;
		Set<java.awt.Point> visited = new HashSet<>();
		for (int i = 0; i < 10000; ++i) {
			Point offset = upOffsetGenerator.get();
			x += offset.getX();
			y += offset.getY();
			if (!visited.add(new java.awt.Point(x, y))) {
				fail("Point already visited: "+ x + " " + y);
			}
		}
	}
	
	@Test 
	public void testDownOffsetGenerator() {
		Supplier<Point> upOffsetGenerator = org.nasdanika.drawio.Util.createOffsetGenerator(10, true);
		int x = 0;
		int y = 0;
		Set<java.awt.Point> visited = new HashSet<>();
		for (int i = 0; i < 10000; ++i) {
			Point offset = upOffsetGenerator.get();
			x += offset.getX();
			y += offset.getY();
			if (!visited.add(new java.awt.Point(x, y))) {
				fail("Point already visited: "+ x + " " + y);
			}
		}
	}
	
	@Test
	public void testLayout() throws Exception {
		Document document = Document.create(false, null);
		Page page = document.createPage();
		page.setName("My first new page");
		
		Model model = page.getModel();
		Root root = model.getRoot();
		List<Layer> layers = root.getLayers();
		assertThat(layers).singleElement();
		
		// Add layer
		Layer newLayer = root.createLayer();
		newLayer.setLabel("My new layer");
				
		// Add nodes
		Node source = newLayer.createNode();
		source.setLabel("My source node");
		Rectangle sourceGeometry = source.getGeometry();
		sourceGeometry.setWidth(120);
		sourceGeometry.setHeight(50);
		source.getTags().add("aws");
				
		Node target = newLayer.createNode();
		target.setLabel("My target node");
		target.getGeometry().setBounds(0, 0, 100, 30);
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
		
		Node target2 = newLayer.createNode();
		target2.setLabel("My second target");
		target2.getGeometry().setBounds(0, 0, 120, 20);
		
		// Add connection 
		Connection connection2 = newLayer.createConnection(source, target2);
		connection2.setLabel("Second connection");
		connectionStyle = connection2.getStyle();
		connectionStyle.put("edgeStyle", "orthogonalEdgeStyle");
		connectionStyle.put("rounded", "1");
		connectionStyle.put("orthogonalLoop", "1");
		connectionStyle.put("jettySize", "auto");
		connectionStyle.put("html", "1");		
		
		org.nasdanika.drawio.Util.layout(root, 20);
		
		Files.writeString(new File("target/layout.drawio").toPath(), document.save(null));
	}
	
	@Test 
	public void testLoadFromPngMetadata() throws Exception {
		List<Document> documents = Document.loadFromPngMetadata(getClass().getResource("illustration.png"));
		assertThat(documents).singleElement();
	}
		
	@Test 
	public void testInternalPageLink() throws Exception {
		Document document = Document.load(getClass().getResource("links.drawio"));
		Optional<Node> linkToPage2 = document.stream(null).filter(Node.class::isInstance).map(Node.class::cast).filter(n -> "Link to Page 2".equals(n.getLabel())).findFirst();
		assertThat(linkToPage2.isPresent()).isEqualTo(true);		
		Page linkedPage = linkToPage2.get().getLinkedPage();
		assertThat(linkedPage).isNotNull();
		assertThat(linkedPage.getName()).isEqualTo("Page 2");
	}
	
	@Test 
	public void testExternalPageLink() throws Exception {
		Document document = Document.load(getClass().getResource("links.drawio"));
		Optional<Node> linkToPage = document.stream(null).filter(Node.class::isInstance).map(Node.class::cast).filter(n -> "Link to compressed second page".equals(n.getLabel())).findFirst();
		assertThat(linkToPage.isPresent()).isEqualTo(true);		
		Page linkedPage = linkToPage.get().getLinkedPage();
		assertThat(linkedPage).isNotNull();
		assertThat(linkedPage.getName()).isEqualTo("Page 2");
		URI linkedDocURI = linkedPage.getDocument().getURI();
		assertThat(linkedDocURI.toString().endsWith("compressed.drawio#Page+2")).isEqualTo(true);
	}
	
	@Test 
	public void testDocumentFirstPageLink() throws Exception {
		Document document = Document.load(getClass().getResource("links.drawio"));
		Optional<Node> linkToPage = document.stream(null).filter(Node.class::isInstance).map(Node.class::cast).filter(n -> "Link to compressed first page".equals(n.getLabel())).findFirst();
		assertThat(linkToPage.isPresent()).isEqualTo(true);		
		Page linkedPage = ((Node) linkToPage.get()).getLinkedPage();
		assertThat(linkedPage).isNotNull();
		assertThat(linkedPage.getName()).isEqualTo("Page-1");
		assertThat(linkedPage.getDocument().getURI().toString().endsWith("compressed.drawio")).isEqualTo(true);
	}
		
	@Test 
	public void testLinkedPagesTraversal() throws Exception {
		Document document = Document.load(getClass().getResource("links.drawio"));
		Consumer<Element> visitor = e -> {
			if (e instanceof ModelElement) {
//				System.out.println(((ModelElement) e).getLabel());
			} else if (e instanceof Page) {
				Page page = (Page) e;
				System.out.println(page.getName() + " " + page.getId() + " " + page.getDocument().getURI());				
			}
		};
		
		document.accept(visitor, null);
		System.out.println("===");
		document.accept(org.nasdanika.drawio.Util.withLinkedPages(visitor, null), null);
	}
	
	
}
