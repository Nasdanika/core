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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
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
import org.nasdanika.drawio.Tag;
import org.nasdanika.drawio.comparators.EnumerateComparator;
import org.nasdanika.graph.ContentProvider;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;
import org.nasdanika.graph.processor.Synapse;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory;
import org.nasdanika.graph.processor.function.MessageProcessorFactory;

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
		source.getTags().add(page.createTag("aws"));		
		
		Node target = newLayer.createNode();
		target.setLabel("My target node");
		target.getGeometry().setBounds(300, 150, 100, 30);
		Set<Tag> targetTags = target.getTags();
		targetTags.add(page.createTag("aws"));
		targetTags.add(page.createTag("azure"));
		
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
		source.getTags().add(page.createTag("aws"));
				
		Node target = newLayer.createNode();
		target.setLabel("My target node");
		target.getGeometry().setBounds(0, 0, 100, 30);
		Set<Tag> targetTags = target.getTags();
		targetTags.add(page.createTag("aws"));
		targetTags.add(page.createTag("azure"));
		
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
	public void testPointsLayout() throws Exception {
		Document document = Document.create(false, null);
		Page page = document.createPage();
		page.setName("My first new page with points");

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
		source.getTags().add(page.createTag("aws"));

		Node target = newLayer.createNode();
		target.setLabel("My target node");
		target.getGeometry().setBounds(60, 140, 100, 30);

		// Add connection
		Connection connection = newLayer.createConnection(source, target);
		connection.setLabel("My connection");
		Map<String, String> connectionStyle = connection.getStyle();
		connectionStyle.put("rounded", "1");
		connectionStyle.put("orthogonalLoop", "1");
		connectionStyle.put("jettySize", "auto");
		connectionStyle.put("html", "1");
		connection.addPoint(80,110);
		connection.addPoint(310,110);
		connection.addPoint(310,260);
		connection.addPoint(125,260);

		org.nasdanika.drawio.Util.layout(root, 20);

		Files.writeString(new File("target/layout_points.drawio").toPath(), document.save(null));
	}
	
	@Test 
	public void testLoadFromPngMetadata() throws Exception {
		List<Document> documents = Document.loadFromPngMetadata(getClass().getResource("illustration.png"));
		assertThat(documents).singleElement();
	}
	
	/**
	 * Does not create processor instances - wires endpoints and handlers in code.
	 * @throws Exception
	 */
	@Test 
	public void testProcessor() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		Optional<Node> aliceOptional = document
			.stream()
			.filter(Node.class::isInstance)
			.map(Node.class::cast)
			.filter(n -> "Alice".equals(n.getLabel()))
			.findAny();
		
		assertThat(aliceOptional.isPresent()).isTrue();		
		Node aliceNode = aliceOptional.get();
		
		Optional<Node> bobOptional = document
				.stream()
				.filter(Node.class::isInstance)
				.map(Node.class::cast)
				.filter(n -> "Bob".equals(n.getLabel()))
				.findAny();
			
		assertThat(bobOptional.isPresent()).isTrue();		
//		Node bobNode = bobOptional.get();		
		
		NopEndpointProcessorConfigFactory<Function<String,String>,String> processorConfigFactory = new NopEndpointProcessorConfigFactory<>();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		Transformer<org.nasdanika.graph.Element, ProcessorConfig<Function<String,String>,Function<String,String>,String>> transformer = new Transformer<>(processorConfigFactory);
		Map<org.nasdanika.graph.Element, ProcessorConfig<Function<String,String>,Function<String,String>,String>> configs = transformer.transform(Collections.singleton(document), false, progressMonitor);
		
		@SuppressWarnings("unchecked")
		NodeProcessorConfig<Function<String, String>, Function<String, String>, String> aliceProcessorConfig = (NodeProcessorConfig<Function<String, String>, Function<String, String>, String>) configs.get(aliceNode);
		assertThat(aliceProcessorConfig.getChildProcessorConfigs() == null || aliceProcessorConfig.getChildProcessorConfigs().isEmpty()).isTrue();
		assertThat(aliceProcessorConfig.getIncomingSynapses()).isEmpty();
		assertThat(aliceProcessorConfig.getOutgoingSynapses().size()).isEqualTo(1);
		
		aliceProcessorConfig.getOutgoingSynapses().forEach((connection, synapse) -> {
			synapse.setHandler(request -> {
				String myName = ((Node) connection.getSource()).getLabel();
				return request + System.lineSeparator() + "[" + myName + "] My name is " + myName + ".";
			});
		});
		
		for (Entry<org.nasdanika.graph.Connection, Synapse<Function<String, String>, Function<String, String>>> outgoingEndpointCompletionStageEntry: aliceProcessorConfig.getOutgoingSynapses().entrySet()) {
			outgoingEndpointCompletionStageEntry.getValue().getEndpoint().thenAccept(outgoingEndpoint -> {
				String dialog = outgoingEndpoint.apply("[" + ((Node) outgoingEndpointCompletionStageEntry.getKey().getSource()).getLabel() + "] Hello!");
				System.out.println(dialog);				
			});
		}
		
		ProcessorFactory<Function<String,String>,Function<String,String>,String,Object> processorFactory = new ProcessorFactory<Function<String,String>,Function<String,String>,String,Object>() {
			
			@Override
			protected ProcessorInfo<Function<String,String>,Function<String,String>,String,Object> createProcessor(
					ProcessorConfig<Function<String,String>,Function<String,String>,String> config, 
					boolean parallel, 
					BiConsumer<org.nasdanika.graph.Element,BiConsumer<ProcessorInfo<Function<String,String>,Function<String,String>,String,Object>,ProgressMonitor>> inforProvider,
					Consumer<java.util.concurrent.CompletionStage<?>> endpointWiringStageConsumer, 
					ProgressMonitor progressMonitor) {
				
				if (config instanceof NodeProcessorConfig) {
					@SuppressWarnings("unchecked")
					NodeProcessorConfig<Function<String, String>, Function<String, String>, String> nodeProcessorConfig = (NodeProcessorConfig<Function<String, String>, Function<String, String>, String>) config;
					if ("Bob".equals(((Node) nodeProcessorConfig.getElement()).getLabel())) {
						// Wiring
						assertThat(nodeProcessorConfig.getChildProcessorConfigs() == null || nodeProcessorConfig.getChildProcessorConfigs().isEmpty()).isTrue();
						assertThat(nodeProcessorConfig.getOutgoingSynapses()).isEmpty();
						assertThat(nodeProcessorConfig.getIncomingSynapses().size()).isEqualTo(1);
						
						// Bob ask Alice and then replies to Alice
						nodeProcessorConfig.getIncomingSynapses().forEach((connection, synapse) -> {
							synapse.setHandler(request -> {
								StringBuilder sb = new StringBuilder(request).append(System.lineSeparator());
								for (Entry<org.nasdanika.graph.Connection, Synapse<Function<String, String>, Function<String, String>>> incomingSynapseEntry: nodeProcessorConfig.getIncomingSynapses().entrySet()) {
									incomingSynapseEntry.getValue().getEndpoint().thenAccept(incomingEndpoint -> {
										String myName = ((Node) connection.getTarget()).getLabel();
										sb.append(incomingEndpoint.apply("[" + myName + "] Hi, my name is " + myName + ". What is your name?"));										
									});
								}
								
								return sb.toString();
							});
						});
						
					}
				}

				return super.createProcessor(config, parallel, inforProvider, endpointWiringStageConsumer, progressMonitor);
			}
		};
		
		Map<org.nasdanika.graph.Element, ProcessorInfo<Function<String,String>,Function<String,String>,String,Object>> processors = processorFactory.createProcessors(
				configs
					.values()
					.stream()
					.filter(Objects::nonNull)
					.toList(), 
				false, 
				progressMonitor);
		
		for (Entry<org.nasdanika.graph.Connection, Synapse<Function<String, String>, Function<String, String>>> outgoingSynapseEntry: aliceProcessorConfig.getOutgoingSynapses().entrySet()) {
			outgoingSynapseEntry.getValue().getEndpoint().thenAccept(outgoingEndpoint -> {
				String dialog = outgoingEndpoint.apply("[" + ((Node) outgoingSynapseEntry.getKey().getSource()).getLabel() + "] Hello!");
				System.out.println(dialog);				
			});
		}
	}
		
	@Test 
	public void testConnectionProcessor() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		Optional<Node> aliceOptional = document
			.stream()
			.filter(Node.class::isInstance)
			.map(Node.class::cast)
			.filter(n -> "Alice".equals(n.getLabel()))
			.findAny();
		
		assertThat(aliceOptional.isPresent()).isTrue();		
		Node aliceNode = aliceOptional.get();
		
		Optional<Node> bobOptional = document
				.stream()
				.filter(Node.class::isInstance)
				.map(Node.class::cast)
				.filter(n -> "Bob".equals(n.getLabel()))
				.findAny();
			
		assertThat(bobOptional.isPresent()).isTrue();		
//		Node bobNode = bobOptional.get();		
		
		NopEndpointProcessorConfigFactory<Function<String,String>, String> processorConfigFactory = new NopEndpointProcessorConfigFactory<>() {
			
			protected boolean isPassThrough(org.nasdanika.graph.Connection connection) {
				return false;
			};
			
		};
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Transformer<org.nasdanika.graph.Element, ProcessorConfig<Function<String,String>,Function<String,String>,String>> transformer = new Transformer<>(processorConfigFactory);
		Map<org.nasdanika.graph.Element, ProcessorConfig<Function<String,String>,Function<String,String>,String>> configs = transformer.transform(Collections.singleton(document), false, progressMonitor);
		
		@SuppressWarnings("unchecked")
		NodeProcessorConfig<Function<String, String>, Function<String, String>, String> aliceProcessorConfig = (NodeProcessorConfig<Function<String, String>, Function<String, String>, String>) configs.get(aliceNode);
		assertThat(aliceProcessorConfig.getChildProcessorConfigs() == null || aliceProcessorConfig.getChildProcessorConfigs().isEmpty()).isTrue();
		assertThat(aliceProcessorConfig.getIncomingSynapses()).isEmpty();
		assertThat(aliceProcessorConfig.getOutgoingSynapses().size()).isEqualTo(1);
		
		aliceProcessorConfig.getOutgoingSynapses().forEach((connection, synapse) -> {
			synapse.setHandler(request -> {
				String myName = ((Node) connection.getSource()).getLabel();
				return request + System.lineSeparator() + "[" + myName + "] My name is " + myName + ".";
			});
		});
		
		for (Entry<org.nasdanika.graph.Connection, Synapse<Function<String, String>, Function<String, String>>> outgoingSynapseEntry: aliceProcessorConfig.getOutgoingSynapses().entrySet()) {
			outgoingSynapseEntry.getValue().getEndpoint().thenAccept(outgoingEndpoint -> {
				String dialog = outgoingEndpoint.apply("[" + ((Node) outgoingSynapseEntry.getKey().getSource()).getLabel() + "] Hello!");
				System.out.println(dialog);				
			});
		}
		
		ProcessorFactory<Function<String,String>,Function<String,String>,String,Object> processorFactory = new ProcessorFactory<Function<String,String>,Function<String,String>,String,Object>() {
			
			@Override
			protected ProcessorInfo<Function<String,String>,Function<String,String>,String,Object> createProcessor(
					ProcessorConfig<Function<String,String>,Function<String,String>,String> config, 
					boolean parallel, 
					BiConsumer<org.nasdanika.graph.Element,BiConsumer<ProcessorInfo<Function<String,String>,Function<String,String>,String,Object>,ProgressMonitor>> inforProvider,
					Consumer<java.util.concurrent.CompletionStage<?>> endpointWiringStageConsumer, 
					ProgressMonitor progressMonitor) {
				
				if (config instanceof NodeProcessorConfig) {
					@SuppressWarnings("unchecked")
					NodeProcessorConfig<Function<String, String>, Function<String, String>, String> nodeProcessorConfig = (NodeProcessorConfig<Function<String, String>, Function<String, String>, String>) config;
					if ("Bob".equals(((Node) nodeProcessorConfig.getElement()).getLabel())) {
						// Wiring
						assertThat(nodeProcessorConfig.getChildProcessorConfigs() == null || nodeProcessorConfig.getChildProcessorConfigs().isEmpty()).isTrue();
						assertThat(nodeProcessorConfig.getOutgoingSynapses()).isEmpty();
						assertThat(nodeProcessorConfig.getIncomingSynapses().size()).isEqualTo(1);
						
						// Bob ask Alice and then replies to Alice
						nodeProcessorConfig.getIncomingSynapses().forEach((connection, synapse) -> {
							synapse.setHandler(request -> {
								StringBuilder sb = new StringBuilder(request).append(System.lineSeparator());
								for (Entry<org.nasdanika.graph.Connection, Synapse<Function<String, String>, Function<String, String>>> incomingSynapseEntry: nodeProcessorConfig.getIncomingSynapses().entrySet()) {
									incomingSynapseEntry.getValue().getEndpoint().thenAccept(incomingEndpoint -> {
										String myName = ((Node) connection.getTarget()).getLabel();
										sb.append(incomingEndpoint.apply("[" + myName + "] Hi, my name is " + myName + ". What is your name?"));										
									});
								}
								
								return sb.toString();
							});
						});
						
					}
				} else if (config instanceof ConnectionProcessorConfig) {
					@SuppressWarnings("unchecked")
					ConnectionProcessorConfig<Function<String, String>, Function<String, String>, String> connectionProcessorConfig = (ConnectionProcessorConfig<Function<String, String>, Function<String, String>, String>) config;
					endpointWiringStageConsumer.accept(connectionProcessorConfig.getTargetSynapse().getEndpoint().thenAccept(targetEndpoint -> {
						connectionProcessorConfig.getSourceSynapse().setHandler(new Function<String, String>() {
							
							@Override
							public String apply(String str) {
								return ">> " + targetEndpoint.apply(str);
							}
							
						});						
					}));
					
					endpointWiringStageConsumer.accept(connectionProcessorConfig.getSourceSynapse().getEndpoint().thenAccept(sourceEndpoint -> {
						connectionProcessorConfig.getTargetSynapse().setHandler(new Function<String, String>() {
							
							@Override
							public String apply(String str) {
								return "<< " + sourceEndpoint.apply(str);
							}
						});					
						
					}));
					
				}

				return super.createProcessor(config, parallel, inforProvider, endpointWiringStageConsumer, progressMonitor);
			}
		};
		
		Map<org.nasdanika.graph.Element, ProcessorInfo<Function<String,String>,Function<String,String>,String,Object>> processors = processorFactory.createProcessors(configs.values(), false, progressMonitor);
		
		for (Entry<org.nasdanika.graph.Connection, Synapse<Function<String, String>, Function<String, String>>> outgoingSynapseEntry: aliceProcessorConfig.getOutgoingSynapses().entrySet()) {
			outgoingSynapseEntry.getValue().getEndpoint().thenAccept(outgoingEndpoint -> {
				String dialog = outgoingEndpoint.apply("[" + ((Node) outgoingSynapseEntry.getKey().getSource()).getLabel() + "] Hello!");
				System.out.println(dialog);				
			});
		}
	}		
	
	@Test 
	public void testReflectiveProcessorFactory() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		NopEndpointProcessorConfigFactory<Function<String,String>, String> processorConfigFactory = new NopEndpointProcessorConfigFactory<>() {
			
			protected boolean isPassThrough(org.nasdanika.graph.Connection connection) {
				return false;
			};
			
		};
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Transformer<org.nasdanika.graph.Element, ProcessorConfig<Function<String,String>,Function<String,String>, String>> transformer = new Transformer<>(processorConfigFactory);
		Map<org.nasdanika.graph.Element, ProcessorConfig<Function<String,String>,Function<String,String>, String>> configs = transformer.transform(Collections.singleton(document), false, progressMonitor);
		
		ReflectiveProcessorFactoryProvider<Function<String, String>,Function<String, String>,String,Object> processorFactoryProvider = new ReflectiveProcessorFactoryProvider<>(new AliceBobProcessorFactory()) {
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
					throw new NasdanikaException("Cannot set field " + field + " of " + target + ": " + e, e);
				} finally {
					if (!canAccess) {
						field.setAccessible(false);
					}
				}	
			}
			
		};
		
		AliceBobProcessorRegistry registry = new AliceBobProcessorRegistry();
		ProcessorFactory<Function<String,String>,Function<String,String>,String,Object> processorFactory = processorFactoryProvider.getFactory(registry);				
		Map<org.nasdanika.graph.Element, ProcessorInfo<Function<String,String>,Function<String,String>,String,Object>> processors = processorFactory.createProcessors(configs.values(), false, progressMonitor);
		for (Entry<org.nasdanika.graph.Element, ProcessorInfo<Function<String,String>,Function<String,String>,String,Object>> pe: processors.entrySet()) {
			org.nasdanika.graph.Element element = pe.getKey();
			if (element instanceof ModelElement) {
				System.out.println(((ModelElement) element).getLabel());
			}
		}
		System.out.println(processors.size());
		
		System.out.println(registry.aliceProcessor.talkToBob("Hi!"));
		
		Synapse<Function<String, String>, Function<String, String>> bobClient = registry.bobInfo.getClientSynapse("Bob's client");
		
		bobClient.getEndpoint().thenAccept(ep -> {
			System.out.println("Bob's endpoint: " + ep);
			System.out.println(ep.apply("Calling Bob's endpoint"));
		});
		
		bobClient.setHandler(str -> "{Bob client hanler } " + str);		
	}	
	
	@Disabled("Does not work unless the module is open so AliceBobHandlers can be accessed using reflection")
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
	@Disabled // Fails occasionally because DOM is not thread safe 
	public void testAsyncComputeGraph() throws Exception {
		Document document = Document.load(getClass().getResource("compute-graph.drawio"));
		
		Optional<Node> startOptional = document
				.stream()
				.filter(Node.class::isInstance)
				.map(Node.class::cast)
				.filter(n -> "Start".equals(n.getLabel()))
				.findAny();
			
			assertThat(startOptional.isPresent()).isTrue();		
			Node startNode = startOptional.get();		
		
		NopEndpointProcessorConfigFactory<Function<String,String>, String> processorConfigFactory = new NopEndpointProcessorConfigFactory<>() {
			
			protected boolean isPassThrough(org.nasdanika.graph.Connection connection) {
				return false;
			};
			
		};
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();				
		Transformer<org.nasdanika.graph.Element, ProcessorConfig<BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, String>> transformer = new Transformer<>(processorConfigFactory);
		Map<org.nasdanika.graph.Element, ProcessorConfig<BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, String>> configs = transformer.transform(Collections.singleton(document), false, progressMonitor);

		BiFunctionProcessorFactory<String, CompletionStage<String>, String, CompletionStage<String>, String> processorFactory = new BiFunctionProcessorFactory<String, CompletionStage<String>, String, CompletionStage<String>, String>() {

			@Override
			protected ConnectionProcessor<String, CompletionStage<String>, String, CompletionStage<String>> createConnectionProcessor(
					ConnectionProcessorConfig<BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, String> connectionProcessorConfig,
					boolean parallel,
					BiConsumer<
						org.nasdanika.graph.Element, 
						BiConsumer<
							ProcessorInfo<
								BiFunction<String, ProgressMonitor, CompletionStage<String>>, 
								BiFunction<String, ProgressMonitor, CompletionStage<String>>,
								String,
								BiFunction<String, ProgressMonitor, CompletionStage<String>>>,
							ProgressMonitor>> infoProvider,
					Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
					ProgressMonitor progressMonitor) {

				return new AsyncStatefulConnectionProcessor();
			}

			@Override
			protected NodeProcessor<String, CompletionStage<String>, String, CompletionStage<String>> createNodeProcessor(
					NodeProcessorConfig<BiFunction<String, ProgressMonitor, CompletionStage<String>>, BiFunction<String, ProgressMonitor, CompletionStage<String>>, String> nodeProcessorConfig,
					boolean parallel,
					BiConsumer<
						org.nasdanika.graph.Element, 
						BiConsumer<
							ProcessorInfo<
								BiFunction<String, ProgressMonitor, CompletionStage<String>>, 
								BiFunction<String, ProgressMonitor, CompletionStage<String>>,
								String,
								BiFunction<String, ProgressMonitor, CompletionStage<String>>>,
							ProgressMonitor>> processorInfo,
					Consumer<CompletionStage<?>> endpointWiringStageConsumer,
					Map<org.nasdanika.graph.Connection, BiFunction<String, ProgressMonitor, CompletionStage<String>>> incomingEndpoints,
					Map<org.nasdanika.graph.Connection, BiFunction<String, ProgressMonitor, CompletionStage<String>>> outgoingEndpoints,
					ProgressMonitor progressMonitor) {

				return new AsyncStatefulNodeProcessor() {
										
					@Override
					protected Collection<BiFunction<String, ProgressMonitor, CompletionStage<String>>> getOutgoingEndpoints() {
						return outgoingEndpoints.values();
					}
				};
			}
		};
				
		Map<
			org.nasdanika.graph.Element, 
			ProcessorInfo<
				BiFunction<String, ProgressMonitor, CompletionStage<String>>,
				BiFunction<String, ProgressMonitor, CompletionStage<String>>,
				String,
				BiFunction<String, ProgressMonitor, CompletionStage<String>>>> processors = processorFactory.createProcessors(configs.values(), true, progressMonitor);
		
		BiFunction<String, ProgressMonitor, CompletionStage<String>> processor = processors.get(startNode).getProcessor();
		processor.apply("First", progressMonitor).thenAccept(System.out::println);
		processor.apply("Second", progressMonitor).thenAccept(System.out::println);
		processor.apply("Third", progressMonitor).thenAccept(System.out::println);			
	}
	
	/**
	 * Tests a synchronous compute graph
	 * @throws Exception
	 */	
	@Test
	@Disabled("Does not work unless the module is open so ReflectiveTarget can be accessed using reflection")
	public void testAsyncReflectiveComputeGraph() throws Exception {
//		Document document = Document.load(getClass().getResource("compute-graph.drawio"));
//		
//		Optional<Node> startOptional = document
//				.stream()
//				.filter(Node.class::isInstance)
//				.map(Node.class::cast)
//				.filter(n -> "Start".equals(n.getLabel()))
//				.findAny();
//			
//			assertThat(startOptional.isPresent()).isTrue();		
//			Node startNode = startOptional.get();		
//		
//		NopEndpointProcessorConfigFactory<Function<String,String>> processorConfigFactory = new NopEndpointProcessorConfigFactory<>() {
//			
//			protected boolean isPassThrough(org.nasdanika.graph.Connection connection) {
//				return false;
//			};
//			
//		};
//		
//		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();				
//		Transformer<org.nasdanika.graph.Element, ProcessorConfig<Object,Object>> transformer = new Transformer<>(processorConfigFactory);
//		Map<org.nasdanika.graph.Element, ProcessorConfig<Object,Object>> configs = transformer.transform(Collections.singleton(document), false, progressMonitor);
//
//		ReflectiveTarget reflectiveTarget = new ReflectiveTarget();
//		ReflectiveBiFunctionProcessorFactoryProvider<String, CompletionStage<String>, String, CompletionStage<String>> processorFactoryProvider = new ReflectiveBiFunctionProcessorFactoryProvider<>(reflectiveTarget);
//		BiFunctionProcessorFactory<String, CompletionStage<String>, String, CompletionStage<String>> processorFactory = processorFactoryProvider.getFactory();
//		Map<org.nasdanika.graph.Element, ProcessorInfo<Object,Object,BiFunction<String, ProgressMonitor, CompletionStage<String>>>> processors = processorFactory.createProcessors(configs.values(), true, progressMonitor);
//		
//		BiFunction<String, ProgressMonitor, CompletionStage<String>> processor = processors.get(startNode).getProcessor();
//		processor.apply("First", progressMonitor).thenAccept(System.out::println);
//		processor.apply("Second", progressMonitor).thenAccept(System.out::println);
//		processor.apply("Third", progressMonitor).thenAccept(System.out::println);			
	}
	
	
	/**
	 * Drawio document -> Ecore model
	 * @throws Exception
	 */
	@Test 
	public void testModel() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		org.nasdanika.drawio.model.Document modelDocument = document.toModelDocument();
		EList<org.nasdanika.drawio.model.LayerElement> elements = modelDocument.getPages().get(0).getModel().getRoot().getLayers().get(0).getElements();
		for (org.nasdanika.drawio.model.LayerElement el: elements) {
			System.out.println(el);			
		}
	}
	
	@Test
	public void testContainer() throws Exception {
		Document document = Document.create(false, null);
		Page page = document.createPage();
		page.setName("My first new page");
		
		Model model = page.getModel();
		Root root = model.getRoot();
		List<Layer> layers = root.getLayers();
		assertThat(layers).singleElement();
		
		Layer backgroundLayer = root.getLayers().get(0);
		
		Node container = backgroundLayer.createNode();
		Map<String, String> containerStyle = container.getStyle();
		containerStyle.put("swimlane", null);
		containerStyle.put("whitespace", "wrap");
		containerStyle.put("html", "1");
		containerStyle.put("collapsible", "0");
		container.getGeometry().setBounds(200, 100, 300, 400);
		container.setLabel("My Container");
		
		Node person = container.createNode();
		person.getGeometry().setBounds(100, 100, 48, 48);
		person.style("shape", "image");
		Map<String, String> personStyle = person.getStyle();
		personStyle.put("verticalLabelPosition", "bottom");
		personStyle.put("labelBackgroundColor", "default");
		personStyle.put("verticalAlign", "top");
		personStyle.put("aspect", "fixed");
		personStyle.put("imageAspect", "0");
		personStyle.put("image", "https://img.icons8.com/color/48/user-male.png");
		
		Files.writeString(new File("target/container.drawio").toPath(), document.save(null));
	}
	
	@Test 
	public void testDanglingConnections() throws Exception {
		Document document = Document.load(getClass().getResource("compute-graph.drawio"));
		NopEndpointProcessorConfigFactory<Function<String,String>, String> processorConfigFactory = new NopEndpointProcessorConfigFactory<>() {
			
			@Override
			protected boolean isPassThrough(org.nasdanika.graph.Connection incomingConnection) {
				return false;
			}
			
		};
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();				
		Transformer<org.nasdanika.graph.Element, ProcessorConfig<Object,Object,String>> transformer = new Transformer<>(processorConfigFactory);
		Map<org.nasdanika.graph.Element, ProcessorConfig<Object,Object,String>> configs = transformer.transform(Collections.singleton(document), false, progressMonitor);
		System.out.println(configs.size());
	}
	
	// Element deletion tests
		
	@Test 
	public void testDeleteAliceFromLayer() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		for (Page page: document.getPages()) {
			for (Layer layer: page.getModel().getRoot().getLayers()) {
				for (LayerElement layerElement: layer.getElements()) {
					if ("Alice".equals(layerElement.getLabel())) {
						layer.getElements().remove(layerElement);
						break;
					}
				}
			}
		}
		Files.writeString(new File("target/no-alice.drawio").toPath(), document.save(null));
	}	
	
	@Test 
	public void testDeleteBob() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		for (Page page: document.getPages()) {
			for (Layer layer: page.getModel().getRoot().getLayers()) {
				for (LayerElement layerElement: layer.getElements()) {
					if ("bobs-house".equals(layerElement.getId())) {
						layerElement.getChildren().clear();
						break;
					}
				}
			}
		}
		Files.writeString(new File("target/no-bob.drawio").toPath(), document.save(null));
	}
	
	@Test
	public void testLoadStyle() {
		String styleStr = "rounded=1;whiteSpace=wrap;html=1;fillColor=#f5f5f5;strokeColor=#666666;gradientColor=#b3b3b3;";
		Map<String, String> styleMap = org.nasdanika.drawio.Util.loadStyle(styleStr);
		styleMap.entrySet().forEach(System.out::println);
	}
		
	public class TestMessage {
		
		String value;
		
		private TestMessage parent;

		private org.nasdanika.graph.Element sender;

		private int hops;
		
		public TestMessage(
			TestMessage parent,
			org.nasdanika.graph.Element sender,
			String value) {
			
			this.hops = parent == null ? 0 : parent.getHops() + 1;
			this.parent = parent;
			this.sender = sender;
			this.value = value;			
			System.out.println("[" + Thread.currentThread().getName() + "] Created a message: " + hops + " " + sender + " " + value);
		}
		
		public int getHops() {
			return hops;
		}
		
		public String getValue() {
			return value;
		}
		
		public TestMessage getParent() {
			return parent;
		}
		
		public org.nasdanika.graph.Element getSender() {
			return sender;
		}
		
		@Override
		public String toString() {
			return "[" + hops + "] " + value + " (" + sender + ")";
		}
		
	}		

	/**
	 * Tests message sending between diagram elements
	 * @throws Exception
	 */
	@Test 
	public void testMessaging() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		
		ProcessorConfigFactory<BiFunction<TestMessage, ProgressMonitor, Void>, BiFunction<TestMessage, ProgressMonitor, Void>, String> processorConfigFactory = new ProcessorConfigFactory<>() {
		
			@Override
			protected boolean isPassThrough(org.nasdanika.graph.Connection connection) {
				return false;
			}
			
			@Override
			public BiFunction<TestMessage, ProgressMonitor, Void> createEndpoint(
					org.nasdanika.graph.Element element, 
					BiFunction<TestMessage, ProgressMonitor, Void> handler,
					HandlerType type) {
				return (m, p) -> {
					System.out.println("Passing message through " + element + ", handler type: " + type);					
					handler.apply(m, p);
					return null;
				};
			}
			
		};
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		Transformer<org.nasdanika.graph.Element, ProcessorConfig<BiFunction<TestMessage, ProgressMonitor, Void>, BiFunction<TestMessage, ProgressMonitor, Void>, String>> transformer = new Transformer<>(processorConfigFactory);
		Map<org.nasdanika.graph.Element, ProcessorConfig<BiFunction<TestMessage, ProgressMonitor, Void>, BiFunction<TestMessage, ProgressMonitor, Void>, String>> configs = transformer.transform(Collections.singleton(document), false, progressMonitor);
				
		MessageProcessorFactory<TestMessage,Void,TestMessage,Void,Void,Void,String> processorFactory = new MessageProcessorFactory<TestMessage,Void,TestMessage,Void,Void,Void,String>() {

			@Override
			protected TestMessage createSourceMessage(
					Void state,
					org.nasdanika.graph.Connection sender, 
					TestMessage parent,
					CompletionStage<Void> result, 
					ProgressMonitor progressMonitor) {
				return null;
			}

			@Override
			protected TestMessage createTargetMessage(
					Void state,
					org.nasdanika.graph.Connection sender, 
					TestMessage parent,
					CompletionStage<Void> result, 
					ProgressMonitor progressMonitor) {
				return new TestMessage(parent, sender, "Target message");
			}

			@Override
			protected TestMessage toEndpointArgument(TestMessage message) {
				return message;
			}

			@Override
			protected Void createConnectionResult(Void endpointResult) {
				return endpointResult;
			}

			@Override
			protected TestMessage createConnectionMessage(
					Void state,
					org.nasdanika.graph.Connection activator,
					boolean incomingActivator, 
					org.nasdanika.graph.Node sender,
					org.nasdanika.graph.Connection recipient, 
					boolean incomingRrecipient, TestMessage parent,
					CompletionStage<Void> result, 
					ProgressMonitor progressMonitor) {
				
				return new TestMessage(parent, sender, "Node message to " + recipient);
			}

			@Override
			protected Void createNodeResult(
				Map<org.nasdanika.graph.Connection, Void> incomingResults,
				Map<org.nasdanika.graph.Connection, Void> outgoingResults) {
				return null;
			}
			
		};
		
		Map<org.nasdanika.graph.Element, ProcessorInfo<BiFunction<TestMessage, ProgressMonitor, Void>, BiFunction<TestMessage, ProgressMonitor, Void>, String, BiFunction<TestMessage, ProgressMonitor, Void>>> processors = processorFactory.createProcessors(
				configs					
					.values()
					.stream()
					.filter(Objects::nonNull)
					.toList(), 
				false, 
				progressMonitor);
		
		for (Entry<org.nasdanika.graph.Element, ProcessorInfo<BiFunction<TestMessage, ProgressMonitor, Void>, BiFunction<TestMessage, ProgressMonitor, Void>, String, BiFunction<TestMessage, ProgressMonitor, Void>>> pe: processors.entrySet()) {
			if (pe.getKey() instanceof Node) {
				System.out.println("===");
				TestMessage nodeMessage = new TestMessage(null, null, "Root message to " + pe.getKey());
				processors.get(pe.getKey()).getProcessor().apply(nodeMessage, progressMonitor);
			}
		}
	}
		
	@Test 
	public void testStyleMagicProperty() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		document.accept(el -> {
			if (el instanceof ModelElement) {
				ModelElement mel = (ModelElement) el;
				if ("Alice".equals(mel.getLabel())) {
					System.out.println(mel.getProperty("$style:fillColor"));
				}
			}
		});	
	}
	
	@Test 
	public void testSpelMagicProperty() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		document.accept(el -> {
			if (el instanceof ModelElement) {
				ModelElement mel = (ModelElement) el;
				if ("Alice".equals(mel.getLabel())) {
					System.out.println(mel.getProperty("$spel:style[\"fillColor\"]"));
				}
			}
		});	
	}
	
	
	@Test
	public void testFromGraphUncompressed() throws Exception {
		ContentProvider<Object,Object> cp = ContentProvider.fromYaml(
				"""
				- value: Alice
				  outgoingConnections:
				    - target: bobRef
				      value: AliceToBob
				- value: Bob
				  refId: bobRef    
				""");

		org.nasdanika.graph.Element graph = org.nasdanika.graph.Element.from(cp);
		
		Document document = Document.create(false, null);
		Page page = document.createPage();
		page.setName("My first new page");
		
		Model model = page.getModel();
		Root root = model.getRoot();
		List<Layer> layers = root.getLayers();
//		layers.get(0).getModel().getPage().getmo.getDocument().getModelElementById(null)
		assertThat(layers).singleElement();
		
		layers.get(0).populate(graph, (g, m) -> {
			if ("Bob".equals(m.getLabel())) {
				Map<String, String> style = m.getStyle();
				style.put("fillColor", "#dae8fc");
				style.put("strokeColor", "#6c8ebf");
			}
		});
		
		org.nasdanika.drawio.Util.forceLayout(root, 800, 600);
				
		Files.writeString(new File("target/from-graph-uncompressed.drawio").toPath(), document.save(null));
	}	
	
	@Test 
	public void testEnumerate() throws Exception {
		Document document = Document.load(getClass().getResource("alice-bob-enumerate.drawio"));
		document
			.stream()
			.filter(ModelElement.class::isInstance)
			.map(ModelElement.class::cast)
			.sorted(new EnumerateComparator())
			.forEach(me -> {
				Object ev = me.getEnumarateValue();
				if (ev != null) {
					System.out.println(ev + " " + ev.getClass() + " " + me);
				}
			});
	}	
}
