package org.nasdanika.drawio.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Rectangle;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

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
			List<Connection> outcomingConnections = node.getOutgoingConnections();
			if (!outcomingConnections.isEmpty()) {
				System.out.println(indent + "\tOutgoing connections:");
				for (Connection connection: outcomingConnections) {
					Node target = connection.getTarget();
					System.out.println(indent + "\t\t" + connection.getLabel() + " -> " + target.getLabel());
				}
			}
			List<Connection> inboundConnections = node.getIncomingConnections();
			if (!inboundConnections.isEmpty()) {
				System.out.println(indent + "\tIncoming connections:");
				for (Connection connection: inboundConnections) {
					System.out.println(indent + "\t\t" + connection.getLabel() + " <- " + connection.getSource().getLabel());
				}
			}
			List<LayerElement> children = node.getElements();
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
	
	@Test 
	public void testProcessor() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		org.nasdanika.graph.processor.NopEndpointProcessorFactory<Object, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>> processorFactory = new org.nasdanika.graph.processor.NopEndpointProcessorFactory<>() {
			
			@Override
			public ProcessorInfo<Object, Map<Element, ProcessorInfo<Object,?>>> createProcessor(ProcessorConfig<Object, Map<Element, ProcessorInfo<Object,?>>> config, ProgressMonitor progressMonitor) {
				if (config instanceof NodeProcessorConfig) {
					NodeProcessorConfig<Object, Function<String, String>, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>> nodeProcessorConfig = (NodeProcessorConfig<Object, Function<String, String>, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>>) config;
					if ("Bob".equals(((Node) nodeProcessorConfig.getElement()).getLabel())) {
						// Wiring
						assertThat(nodeProcessorConfig.getChildProcessorsInfo() == null || nodeProcessorConfig.getChildProcessorsInfo().isEmpty()).isTrue();
						assertThat(nodeProcessorConfig.getOutgoingEndpoints()).isEmpty();
						assertThat(nodeProcessorConfig.getOutgoingHandlerConsumers()).isEmpty();
						assertThat(nodeProcessorConfig.getIncomingEndpoints().size()).isEqualTo(1);
						assertThat(nodeProcessorConfig.getIncomingHandlerConsumers().size()).isEqualTo(1);
						
						// Bob ask Alice and then replies to Alice
						nodeProcessorConfig.getIncomingHandlerConsumers().forEach((connection, handlerConsumer) -> {
							handlerConsumer.accept(request -> {
								StringBuilder sb = new StringBuilder(request).append(System.lineSeparator());
								for (Entry<org.nasdanika.graph.Connection, CompletionStage<Function<String, String>>> incomingEndpointCompletionStageEntry: nodeProcessorConfig.getIncomingEndpoints().entrySet()) {
									incomingEndpointCompletionStageEntry.getValue().thenAccept(incomingEndpoint -> {
										String myName = ((Node) connection.getTarget()).getLabel();
										sb.append(incomingEndpoint.apply("[" + myName + "] Hi, my name is " + myName + ". What is your name?"));										
									});
								}
								
								return sb.toString();
							});
						});
						
					}
				}
				
				return org.nasdanika.graph.processor.NopEndpointProcessorFactory.super.createProcessor(config, progressMonitor);
			}

			@SuppressWarnings("unchecked")
			@Override
			public Map<Element, ProcessorInfo<Object, ?>> createRegistry(Map<org.nasdanika.graph.Element, ProcessorInfo<Object, Map<Element, ProcessorInfo<Object, ?>>>> registry) {				
				return (Map) registry;
			}

			
		};
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<Object, ?>> registry = processorFactory.createProcessors(progressMonitor, document);
		Optional<?> aliceProcessorOptional = registry.entrySet().stream().filter(e -> e.getKey() instanceof Node && "Alice".equals(((Node) e.getKey()).getLabel())).map(Map.Entry::getValue).findAny();
		assertThat(aliceProcessorOptional.isPresent()).isTrue();
		ProcessorInfo<Object, Map<Element, ProcessorInfo<Object,?>>> aliceProcessor = (ProcessorInfo<Object, Map<Element, ProcessorInfo<Object,?>>>) aliceProcessorOptional.get();
		NodeProcessorConfig<Object, Function<String, String>, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>> aliceNodeProcessorConfig = (NodeProcessorConfig<Object, Function<String, String>, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>>) aliceProcessor.getConfig();
		assertThat(aliceNodeProcessorConfig.getChildProcessorsInfo() == null || aliceNodeProcessorConfig.getChildProcessorsInfo().isEmpty()).isTrue();
		assertThat(aliceNodeProcessorConfig.getIncomingEndpoints()).isEmpty();
		assertThat(aliceNodeProcessorConfig.getIncomingHandlerConsumers()).isEmpty();
		assertThat(aliceNodeProcessorConfig.getOutgoingEndpoints().size()).isEqualTo(1);
		assertThat(aliceNodeProcessorConfig.getOutgoingHandlerConsumers().size()).isEqualTo(1);
		
		aliceNodeProcessorConfig.getOutgoingHandlerConsumers().forEach((connection, handlerConsumer) -> {
			handlerConsumer.accept(request -> {
				String myName = ((Node) connection.getSource()).getLabel();
				return request + System.lineSeparator() + "[" + myName + "] My name is " + myName + ".";
			});
		});

		for (Entry<org.nasdanika.graph.Connection, CompletionStage<Function<String, String>>> outgoingEndpointCompletionStageEntry: aliceNodeProcessorConfig.getOutgoingEndpoints().entrySet()) {
			outgoingEndpointCompletionStageEntry.getValue().thenAccept(outgoingEndpoint -> {
				String dialog = outgoingEndpoint.apply("[" + ((Node) outgoingEndpointCompletionStageEntry.getKey().getSource()).getLabel() + "] Hello!");
				System.out.println(dialog);				
			});
		}
	}
		
	@Test 
	public void testConnectionProcessor() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		org.nasdanika.graph.processor.NopEndpointProcessorFactory<Object, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>> processorFactory = new org.nasdanika.graph.processor.NopEndpointProcessorFactory<>() {

			@Override
			public ProcessorInfo<Object, Map<Element, ProcessorInfo<Object,?>>> createProcessor(ProcessorConfig<Object, Map<Element, ProcessorInfo<Object,?>>> config, ProgressMonitor progressMonitor) {
				
				if (config instanceof NodeProcessorConfig) {
					NodeProcessorConfig<Object, Function<String, String>, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>> nodeProcessorConfig = (NodeProcessorConfig<Object, Function<String, String>, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>>) config;
					if ("Bob".equals(((Node) nodeProcessorConfig.getElement()).getLabel())) {
						// Wiring
						assertThat(nodeProcessorConfig.getChildProcessorsInfo() == null || nodeProcessorConfig.getChildProcessorsInfo().isEmpty()).isTrue();
						assertThat(nodeProcessorConfig.getOutgoingEndpoints()).isEmpty();
						assertThat(nodeProcessorConfig.getOutgoingHandlerConsumers()).isEmpty();
						assertThat(nodeProcessorConfig.getIncomingEndpoints().size()).isEqualTo(1);
						assertThat(nodeProcessorConfig.getIncomingHandlerConsumers().size()).isEqualTo(1);
						
						// Bob ask Alice and then replies to Alice
						nodeProcessorConfig.getIncomingHandlerConsumers().forEach((connection, handlerConsumer) -> {
							handlerConsumer.accept(request -> {
								StringBuilder sb = new StringBuilder(request).append(System.lineSeparator());
								for (Entry<org.nasdanika.graph.Connection, CompletionStage<Function<String, String>>> incomingEndpointCompletionStageEntry: nodeProcessorConfig.getIncomingEndpoints().entrySet()) {
									incomingEndpointCompletionStageEntry.getValue().thenAccept(incomingEndpoint -> {
										String myName = ((Node) connection.getTarget()).getLabel();
										sb.append(incomingEndpoint.apply("[" + myName + "] Hi, my name is " + myName + ". What is your name?"));
									});
								}
								
								return sb.toString();
							});
						});
						
					}
				} else if (config instanceof ConnectionProcessorConfig) {
					ConnectionProcessorConfig<Object, Function<String, String>, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>> connectionProcessorConfig = (ConnectionProcessorConfig<Object, Function<String, String>, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>>) config;
					
					connectionProcessorConfig.setSourceHandler(new Function<String, String>() {
						
						@Override
						public String apply(String str) {
							try {
								return ">> " + connectionProcessorConfig.getTargetEndpoint().toCompletableFuture().get().apply(str);
							} catch (InterruptedException | ExecutionException e) {
								throw new NasdanikaException(e);
							}
						}
						
					});
					connectionProcessorConfig.setTargetHandler(new Function<String, String>() {
						
						@Override
						public String apply(String str) {
							try {
								return "<< " + connectionProcessorConfig.getSourceEndpoint().toCompletableFuture().get().apply(str);
							} catch (InterruptedException | ExecutionException e) {
								throw new NasdanikaException(e);
							}
						}
					});					
					
				}
				
				return org.nasdanika.graph.processor.NopEndpointProcessorFactory.super.createProcessor(config, progressMonitor);
			}
			
			@Override
			public boolean isPassThrough(org.nasdanika.graph.Connection connection) {
				return false;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Map<Element, ProcessorInfo<Object, ?>> createRegistry(Map<org.nasdanika.graph.Element, ProcessorInfo<Object, Map<Element, ProcessorInfo<Object, ?>>>> registry) {
				return (Map) registry;
			}
			
		};
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Map<Element, ProcessorInfo<Object, ?>> registry = processorFactory.compose(processorFactory).createProcessors(progressMonitor, document);

		Optional<?> aliceProcessorInfoOptional = registry.entrySet().stream().filter(e -> e.getKey() instanceof Node && "Alice".equals(((Node) e.getKey()).getLabel())).map(Map.Entry::getValue).findAny();
		assertThat(aliceProcessorInfoOptional.isPresent()).isTrue();
		ProcessorInfo<Object, Map<Element, ProcessorInfo<Object,?>>> aliceProcessorInfo = (ProcessorInfo<Object, Map<Element, ProcessorInfo<Object,?>>>) aliceProcessorInfoOptional.get();
		NodeProcessorConfig<Object, Function<String, String>, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>> aliceNodeProcessorConfig = (NodeProcessorConfig<Object, Function<String, String>, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>>) aliceProcessorInfo.getConfig();
		assertThat(aliceNodeProcessorConfig.getChildProcessorsInfo() == null || aliceNodeProcessorConfig.getChildProcessorsInfo().isEmpty()).isTrue();
		assertThat(aliceNodeProcessorConfig.getIncomingEndpoints()).isEmpty();
		assertThat(aliceNodeProcessorConfig.getIncomingHandlerConsumers()).isEmpty();
		assertThat(aliceNodeProcessorConfig.getOutgoingEndpoints().size()).isEqualTo(1);
		assertThat(aliceNodeProcessorConfig.getOutgoingHandlerConsumers().size()).isEqualTo(1);
		
		aliceNodeProcessorConfig.getOutgoingHandlerConsumers().forEach((connection, handlerConsumer) -> {
			handlerConsumer.accept(request -> {
				String myName = ((Node) connection.getSource()).getLabel();
				return request + System.lineSeparator() + "[" + myName + "] My name is " + myName + ".";
			});
		});

		for (Entry<org.nasdanika.graph.Connection, CompletionStage<Function<String, String>>> outcomingEndpoint: aliceNodeProcessorConfig.getOutgoingEndpoints().entrySet()) {
			String dialog = outcomingEndpoint.getValue().toCompletableFuture().get().apply("[" + ((Node) outcomingEndpoint.getKey().getSource()).getLabel() + "] Hello!");
			System.out.println(dialog);
		}
	}		
	
	@Test 
	public void testReflectiveProcessorFactory() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
				
		org.nasdanika.graph.processor.NopEndpointReflectiveProcessorFactory<Object, Function<String, String>, Map<Element, ProcessorInfo<Object,?>>> processorFactory = new org.nasdanika.graph.processor.NopEndpointReflectiveProcessorFactory<>(new AliceBobProcessorFactory()) {
			
			/**
			 * A trick around module things - test classes are not exported.
			 * @param target
			 * @param method
			 * @param args
			 * @return
			 */
			@Override
			protected Object invokeMethod(Object target, Method method, Object... args) {
				try {
					return method.invoke(target, args);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new NasdanikaException("Error invoking " + method + " of " + target + ": " + e, e);
				}		
			}
			
			/**
			 * A trick around module things - test classes are not exported.
			 * @param target
			 * @param field
			 * @return
			 */
			@Override
			protected Object getFieldValue(Object target, Field field) {
				boolean canAccess = field.canAccess(target);
				try {
					if (!canAccess) {
						field.setAccessible(true);
					}
					return  field.get(target);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new NasdanikaException("Cannot access field " + field + " of " + target + ": " + e, e);
				} finally {
					if (!canAccess) {
						field.setAccessible(false);
					}
				}	
			}

			/**
			 * A trick around module things - test classes are not exported.
			 * @param target
			 * @param field
			 * @param value
			 */
			@Override
			protected void setFieldValue(Object target, Field field, Object value) {
				boolean canAccess = field.canAccess(target);
				try {
					if (!canAccess) {
						field.setAccessible(true);
					}
					field.set(target, value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new NasdanikaException("Cannot access field " + field + " of " + target + ": " + e, e);
				} finally {
					if (!canAccess) {
						field.setAccessible(false);
					}
				}	
			}
			
			@Override
			public boolean isPassThrough(org.nasdanika.graph.Connection connection) {
				return false;
			}

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Map<Element, ProcessorInfo<Object, ?>> createRegistry(Map<org.nasdanika.graph.Element, ProcessorInfo<Object, Map<Element, ProcessorInfo<Object, ?>>>> registry) {
				return (Map) registry;
			}

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			protected Iterable<Entry<org.nasdanika.graph.Element, ProcessorInfo<Object, Map<Element, ProcessorInfo<Object, ?>>>>> registryEntries(Map<Element, ProcessorInfo<Object, ?>> registry) {
				return (Iterable) registry.entrySet();
			}
			
		};	
		
		AliceBobProcessorRegistry registry = new AliceBobProcessorRegistry();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();		
		processorFactory.createProcessors(document, progressMonitor, registry);
		
		System.out.println(registry.aliceProcessor.talkToBob("Hi!"));
	}	
	
	@Disabled("Does not work because AlibBobHandlers is not exported and reflective method invocation fails")
	@Test 
	public void testDispatch() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		
		AliceBobHandlers aliceBobHandlers = new AliceBobHandlers();		
		Object result = document.dispatch(aliceBobHandlers);
		System.out.println(result);
	}	

	/**
	 * Tests a synchronous compute graph
	 * @throws Exception
	 */
	@Test 
	public void testComputeGraph() throws Exception {
		Document document = Document.load(getClass().getResource("compute-graph.drawio"));		
		GraphComputer graphComputer = new GraphComputer();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();		
		@SuppressWarnings("unchecked")
		Map<Element, ProcessorInfo<BiFunction<String, ProgressMonitor, String>, Object>> registry = (Map<Element, ProcessorInfo<BiFunction<String, ProgressMonitor, String>, Object>>) graphComputer.createProcessors(progressMonitor, document);
		
		// Failures
		registry.entrySet().stream().flatMap(e -> e.getValue().getFailures().stream()).forEach(Throwable::printStackTrace);			
		
		Optional<ProcessorInfo<BiFunction<String, ProgressMonitor, String>, Object>> startProcessorInfoOptional = registry.entrySet().stream().filter(re -> re.getKey() instanceof Node && "Start".equals(((Node) re.getKey()).getLabel())).map(Entry::getValue).findFirst();
		if (startProcessorInfoOptional.isPresent()) {
			BiFunction<String, ProgressMonitor, String> processor = startProcessorInfoOptional.get().getProcessor();
			System.out.println(processor.apply("First", progressMonitor));
			System.out.println(processor.apply("Second", progressMonitor));
			System.out.println(processor.apply("Third", progressMonitor));			
		}
	}		
	
}
