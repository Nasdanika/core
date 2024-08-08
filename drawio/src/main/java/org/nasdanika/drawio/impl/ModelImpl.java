package org.nasdanika.drawio.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.persistence.Marker;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

class ModelImpl extends ElementImpl implements Model {
	
	static final String ATTRIBUTE_VERTEX = "vertex";
	static final String ATTRIBUTE_EDGE = "edge";
	
	private Map<org.w3c.dom.Element, ModelElement> cache = new IdentityHashMap<>();
	private Page page;		
	
	ModelImpl(Page page, org.w3c.dom.Element element) {
		this.page = page;
		this.element = element;		
	}
	
	ModelImpl(Page page, String compressedStr) throws SAXException, IOException, ParserConfigurationException {
		this.page = page;
		if (!Base64.isBase64(compressedStr)) {
			throw new IllegalArgumentException("Compressed diagram is not Base64 encoded");
		}
	    byte[] compressed = Base64.decodeBase64(compressedStr);
	    byte[] decompressed = inflate(compressed);
	    String decompressedStr = new String(decompressed, StandardCharsets.UTF_8);
	    String decodedStr = URLDecoder.decode(decompressedStr, StandardCharsets.UTF_8.name());
	    
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		element = dBuilder.parse(new InputSource(new StringReader(decodedStr))).getDocumentElement();				
	}
	
	/**
	 * @return Compressed model string.
	 */
	String compress() throws TransformerException, IOException {
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
	    DOMSource source = new DOMSource(element);
	    StringWriter sw = new StringWriter();
	    try (sw) {
		    StreamResult out = new StreamResult(sw);
		    transformer.transform(source, out);
	    }
	    String urlEncodedStr = URLEncoder.encode(sw.toString(), StandardCharsets.UTF_8.name()).replace("+", "%20"); // Hackish replacement of + with %20 for drawio viewer to understand.
	    byte[] reCompressed = deflate(urlEncodedStr.getBytes(StandardCharsets.UTF_8));
	    return Base64.encodeBase64String(reCompressed);		
	}
	
	@Override
	public List<Root> getChildren() {
		return Collections.singletonList(getRoot());
	}

	@Override
	public Root getRoot() {
		List<ModelElement> result = collect(e -> !ModelElementImpl.getCellElement(e).hasAttribute(ModelElementImpl.ATTRIBUTE_PARENT));
		if (result.isEmpty()) {
			return null;
		}
		if (result.size() == 1) {
			return (Root) result.get(0);
		}
		throw new IllegalArgumentException("More than one root");		
	}
	
	private ModelElement create(org.w3c.dom.Element element) {
		org.w3c.dom.Element cellElement = ModelElementImpl.getCellElement(element);
		if (!cellElement.hasAttribute(ModelElementImpl.ATTRIBUTE_PARENT)) {
			return new RootImpl(element, this);
		}
		ModelElement parent = find(cellElement.getAttribute(ModelElementImpl.ATTRIBUTE_PARENT));
		if (parent instanceof Root) {
			return new LayerImpl(element, this);			
		}
		if (cellElement.hasAttribute(ATTRIBUTE_VERTEX) && "1".equals(cellElement.getAttribute(ATTRIBUTE_VERTEX))) {
			return new NodeImpl(element, this);
		}
		if (cellElement.hasAttribute(ATTRIBUTE_EDGE) && "1".equals(cellElement.getAttribute(ATTRIBUTE_EDGE))) {
			return new ConnectionImpl(element, this);
		}
		return new ModelElementImpl(element, this); // Generic model element, shall it ever happen?
	}
	
	ModelElement find(String id) {
		List<ModelElement> result = collect(e -> e.hasAttribute(ModelElementImpl.ATTRIBUTE_ID) && id.equals(e.getAttribute(ModelElementImpl.ATTRIBUTE_ID)));
		if (result.isEmpty()) {
			return null;
		}
		if (result.size() == 1) {
			return result.get(0);
		}
		throw new IllegalArgumentException("More than one element found with id: " + id);		
	}
	
	/**
	 * Collects elements children of root matching the predicate and transparently converts them to model elements on access.
	 * @param predicate DOM element predicate
	 * @return
	 */
	List<ModelElement> collect(Predicate<org.w3c.dom.Element> predicate) {
		List<org.w3c.dom.Element> rootElements = DocumentImpl.getChildrenElements(element, "root");
		if (rootElements.size() != 1) {
			throw new IllegalArgumentException("There should be only one root XML element, found " + rootElements.size());
		}
		
		List<org.w3c.dom.Element> elements = new ArrayList<>();
		org.w3c.dom.Element rootElement = rootElements.get(0);
		NodeList childNodes = rootElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node child = childNodes.item(i);
			if (child instanceof org.w3c.dom.Element) {
				org.w3c.dom.Element childElement = (org.w3c.dom.Element) child;
				if (predicate.test(childElement)) {
					elements.add(childElement);
				}
			}
		}
		
		return new AbstractList<ModelElement>() {

			@Override
			public ModelElement get(int index) {
				return cache.computeIfAbsent(elements.get(index), ModelImpl.this::create);
			}

			@Override
			public int size() {
				return elements.size();
			}
			
		};
	}	
	
	private static  byte[] inflate(byte[] content) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ByteArrayInputStream source = new ByteArrayInputStream(content); OutputStream target = new InflaterOutputStream(baos, new Inflater(true))) {
	        byte[] buf = new byte[8192];
	        int length;
	        while ((length = source.read(buf)) > 0) {
	            target.write(buf, 0, length);
	        }
		}
		
		return baos.toByteArray();
	}
	
	private static  byte[] deflate(byte[] content) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ByteArrayInputStream source = new ByteArrayInputStream(content); OutputStream target = new DeflaterOutputStream(baos, new Deflater(Deflater.DEFAULT_COMPRESSION, true))) {
	        byte[] buf = new byte[8192];
	        int length;
	        while ((length = source.read(buf)) > 0) {
	            target.write(buf, 0, length);
	        }
		}
		
		return baos.toByteArray();
	}

	@Override
	public Page getPage() {
		return page;
	}

	@Override
	public URI getURI() {
		URI pageURI = getPage().getURI();
		return pageURI == null ? URI.createURI("model") : pageURI.appendFragment(pageURI.fragment() + "/model");
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && getPage().equals(((Model) obj).getPage());
	}
	
	@Override
	public int hashCode() {
		return getPage().hashCode() ^ getClass().hashCode();
	}

	org.nasdanika.drawio.model.Model toModelModel(
			ModelFactory factory, 
			Function<org.nasdanika.persistence.Marker, org.nasdanika.ncore.Marker> markerFactory,
			Function<Element, CompletableFuture<EObject>> modelElementProvider,
			Function<String, org.nasdanika.drawio.model.Tag> tagProvider) {
		org.nasdanika.drawio.model.Model mModel = factory.createModel();		
		modelElementProvider.apply(this).complete(mModel);
		
		mModel.setRoot(((RootImpl) getRoot()).toModelRoot(factory, markerFactory, modelElementProvider, tagProvider));
		
		for (Marker marker: getMarkers()) {
			mModel.getMarkers().add(markerFactory.apply(marker));
		}
		return mModel;
	}
	
}
