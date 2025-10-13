package org.nasdanika.drawio.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

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
import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.ncore.NcoreFactory;
import org.nasdanika.persistence.Marker;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DocumentImpl extends ElementImpl implements Document {
	
	private static final String ATTRIBUTE_COMPRESSED = "compressed";
	private org.w3c.dom.Document document;
	private URI uri;	
	private Function<URI, Document> uriHandler;
	private Function<String,String> propertySource;
	
	/**
	 * 
	 * @param in
	 * @param uri
	 * @param uriHandler URI handler for loading cross-document page links.
	 * @param propertySource source of properties to resolve %propertyName% placeholders
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public DocumentImpl(
			InputStream in, 
			URI uri, 
			Function<URI, Document> uriHandler,
			Function<String,String> propertySource) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		document = dBuilder.parse(in);
		element = document.getDocumentElement();
		this.uri = uri;
		this.uriHandler = uriHandler;
		this.propertySource = propertySource;
	}

	/**
	 * 
	 * @param reader
	 * @param uri
	 * @param uriHandler URI handler for loading cross-document page links.
	 * @param propertySource source of properties to resolve %propertyName% placeholders
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public DocumentImpl(
			Reader reader, 
			URI uri, 
			Function<URI, Document> uriHandler,
			Function<String,String> propertySource) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		document = dBuilder.parse(new InputSource(reader));
		element = document.getDocumentElement();
		this.uri = uri;
		this.uriHandler = uriHandler;
		this.propertySource = propertySource;
	}

	/**
	 * 
	 * @param compressed
	 * @param uri
	 * @param uriHandler URI handler for loading cross-document page links.
	 * @throws ParserConfigurationException
	 */
	public DocumentImpl(
			boolean compressed, 
			URI uri, 
			Function<URI, Document> uriHandler,
			Function<String,String> propertySource) throws ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		document = dBuilder.newDocument();		
		org.w3c.dom.Element mxFileElement = document.createElement("mxfile");
		mxFileElement.setAttribute("type", "device");
		if (!compressed) {
			mxFileElement.setAttribute(ATTRIBUTE_COMPRESSED, String.valueOf(compressed));
		}
		document.appendChild(mxFileElement);
		element = document.getDocumentElement();
		this.uri = uri;
		this.uriHandler = uriHandler;
		this.propertySource = propertySource;
	}
	
	public DocumentImpl(
			String docStr, 
			URI uri, 
			Function<URI, Document> uriHandler,
			Function<String,String> propertySource) throws ParserConfigurationException, SAXException, IOException {
		this(new StringReader(docStr), uri, uriHandler, propertySource);
	}
	
	/**
	 * Resolved source URI against this document URI.
	 * Returns this document if source is equal to this document URI.
	 * Then loads the document.
	 * @param uri
	 * @return
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 */
	@Override
	public Document getDocument(URI source) throws IOException, ParserConfigurationException, SAXException {
		if (source == null) {
			return null;
		}
		URI thisURI = getURI();
		if (Objects.equals(thisURI, source)) {
			return this;
		}
		if (source.isRelative() && thisURI != null && !thisURI.isRelative() && thisURI.isHierarchical()) {
			source = source.resolve(thisURI);
		}
		
		return uriHandler == null ? Document.load(source, null, this::getProperty) : uriHandler.apply(source);
	}

	@Override
	public <T> T accept(BiFunction<org.nasdanika.drawio.Element, Map<org.nasdanika.drawio.Element, T>, T> visitor, ConnectionBase connectionBase) {
		Map<org.nasdanika.drawio.Element, T> pageResults = new LinkedHashMap<>();
		for (Page page: getPages()) {
			pageResults.put(page, page.accept(visitor, connectionBase));
		}
		return visitor.apply(this, pageResults);
	}
	
	private Map<Integer, Page> pages = new LinkedHashMap<>();

	@Override
	public List<Page> getPages() {		
		// List backed by the XML document.
		return new AbstractList<Page>() {

			@Override
			public Page get(int index) {
				return pages.computeIfAbsent(index, idx -> {
					try {			
						return new PageImpl(DocumentImpl.this, getChildrenElements(getElement(), "diagram").get(idx), idx);
					} catch (IOException | ParserConfigurationException | SAXException e) {
						throw new IllegalArgumentException("Error loading compressed page", e);
					}
				});
			}

			@Override
			public int size() {
				return getChildrenElements(getElement(), "diagram").size();
			}

			/**
			 * Imports page node 
			 */
			@Override
			public void add(int index, Page page) {
				if (index == size()) {
					getElement().appendChild(cloneAndImportPageElement(page));
				} else {
					getElement().insertBefore(cloneAndImportPageElement(page), get(index).getElement());
				}
				pages.clear();
			}

			private Node cloneAndImportPageElement(Page page) {
				try {
					((PageImpl) page).save();
				} catch (TransformerException | IOException e) {
					throw new NasdanikaException(e);
				}
				Node pageElementClone = page.getElement().cloneNode(true);
				Node importedPageElementClone = document.importNode(pageElementClone, true);
				((org.w3c.dom.Element) importedPageElementClone).setAttribute(ATTRIBUTE_ID, UUID.randomUUID().toString()); // New ID.
				return importedPageElementClone;
			}
			
			@Override
			public Page set(int index, Page page) {
				Page oldPage = get(index);
				getElement().replaceChild(cloneAndImportPageElement(page), oldPage.getElement());
				pages.clear();
				return oldPage;
			}
			
			@Override
			public Page remove(int index) {
				Page page = get(index);
				getElement().removeChild(page.getElement());
				pages.clear();
				return page;
			}
		};
	}
	
	@Override
	public String getProperty(String name) {
		return propertySource == null ? null : propertySource.apply(name);
	}

	@Override
	public String toHtml(Boolean compress, String viewer) throws JSONException, TransformerException, IOException {
		JSONObject data = new JSONObject();
		data.put("highlight", "#0000ff");
		data.put("nav", true);
		data.put("resize",true);
		data.put("toolbar", "zoom layers tags lightbox");
		data.put("edit","_blank");
		
		data.put("xml", save(compress));
		
		String diagramDiv = "<div class=\"mxgraph\" style=\"max-width:100%;border:1px solid transparent;\" data-mxgraph=\"" + StringEscapeUtils.escapeHtml4(data.toString()) + "\"></div>";		
		if (viewer == null) {
			return diagramDiv;
		}
		String script = "<script type=\"text/javascript\" src=\"" + viewer + "\"></script>";
		return diagramDiv + System.lineSeparator() + script;		
	}

	@Override
	public String save(Boolean compress) throws TransformerException, IOException {
		int pageCount = 0;
		for (Page page: getPages()) {
			((PageImpl) page).save(compress);
			++pageCount;
		}
		
		element.setAttribute("pages", String.valueOf(pageCount));
		if (compress != null) {
			if (compress) {
				element.removeAttribute(ATTRIBUTE_COMPRESSED);
			} else {
				element.setAttribute(ATTRIBUTE_COMPRESSED, "false");
			}
		}
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	    if (compress == null ? element.hasAttribute(ATTRIBUTE_COMPRESSED) && element.getAttribute(ATTRIBUTE_COMPRESSED).equals("false")  : !compress) {
	    	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    }
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    
	    DOMSource source = new DOMSource(document);
	    StringWriter out = new StringWriter();
	    try (out) {
	    	transformer.transform(source, new StreamResult(out));
	    }
		return out.toString();
	}
		
	static List<org.w3c.dom.Element> getChildrenElements(org.w3c.dom.Element parent, String name) {
		List<org.w3c.dom.Element> ret = new ArrayList<>();
		NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			if (child instanceof org.w3c.dom.Element && ((org.w3c.dom.Element) child).getTagName().equals(name)) {
				ret.add((org.w3c.dom.Element) child);
			}
		}
		return ret;
	}
		
	static List<org.w3c.dom.Element> getChildrenElements(
			org.w3c.dom.Element parent, 
			String name, 
			Predicate<org.w3c.dom.Element> predicate) {
		
		List<org.w3c.dom.Element> childrenElements = getChildrenElements(parent, name);
		if (predicate == null) {
			return childrenElements;
		}
		return childrenElements.stream().filter(predicate).toList();
	}

	@Override
	public Page createPage() {
		org.w3c.dom.Element diagramElement = document.createElement("diagram");
	    element.appendChild(diagramElement);
    	diagramElement.setAttribute("id", UUID.randomUUID().toString());
    	org.w3c.dom.Element modelElement = document.createElement("mxGraphModel");
    	diagramElement.appendChild(modelElement);
    	
    	modelElement.setAttribute("page", "1");
    	modelElement.setAttribute("grid", "1");
    	modelElement.setAttribute("gridSize", "10");
    	modelElement.setAttribute("guides", "1");
    	modelElement.setAttribute("tooltips", "1");
    	modelElement.setAttribute("connect", "1");
    	modelElement.setAttribute("arrows", "1");
    	modelElement.setAttribute("fold", "1");
    	modelElement.setAttribute("pageScale", "1");
    	modelElement.setAttribute("math", "0");
    	modelElement.setAttribute("shadow", "0");
    	
    	org.w3c.dom.Element rootElement = document.createElement("root");
    	modelElement.appendChild(rootElement);
    	
    	org.w3c.dom.Element modelRootElement = document.createElement("mxCell");
    	modelRootElement.setAttribute("id", "0");
    	rootElement.appendChild(modelRootElement);
    	
    	org.w3c.dom.Element backgroundElement = document.createElement("mxCell");
    	backgroundElement.setAttribute("id", "1");
    	backgroundElement.setAttribute("parent", "0");
    	rootElement.appendChild(backgroundElement);
    	
    	List<Page> pageList = getPages();
    	return pageList.get(pageList.size() - 1);
	}
	
	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public URI toDataURI(Boolean compress) throws TransformerException, IOException {
		String docStr = save(compress);
		JSONObject payload = new JSONObject();
		payload.put("document", docStr);
		URI docURI = getURI();
		if (docURI != null) {
			payload.put("uri", docURI.toString());
		}
		String payloadStr = payload.toString();
		String base64docStr = Base64.encodeBase64String(payloadStr.getBytes(StandardCharsets.UTF_8));
		String uriStr = "data:drawio;base64," + URLEncoder.encode(base64docStr, StandardCharsets.UTF_8);
		return URI.createURI(uriStr);
	}
	
	@Override
	protected String getMarkerLocation() {
		return uri == null || "data".equals(uri.scheme()) ? null : uri.toString();
	}

	@Override
	public org.nasdanika.drawio.model.Document toModelDocument(ModelFactory factory, Function<org.nasdanika.persistence.Marker, org.nasdanika.ncore.Marker> markerFactory) throws IOException, TransformerException {	
		Map<Element, CompletableFuture<EObject>> registry = new HashMap<>();		
		Function<Element, CompletableFuture<EObject>> modelElementProvider = e -> registry.computeIfAbsent(e, f -> new CompletableFuture<EObject>());
		org.nasdanika.drawio.model.Document ret = factory.createDocument();
		modelElementProvider.apply(this).complete(ret);
		if (uri != null) {
			ret.setUri(uri.toString());
		}
		
		if (markerFactory == null) {
			markerFactory = marker -> {
				if (marker == null) {
					return null;
				}
				org.nasdanika.ncore.Marker mMarker = NcoreFactory.eINSTANCE.createMarker();
				mMarker.setLocation(marker.getLocation());
				mMarker.setPosition(marker.getPosition());
				return mMarker;
			};
		}
		for (Page page: getPages()) {
			org.nasdanika.drawio.model.Page mPage = ((PageImpl) page).toModelPage(factory, markerFactory, modelElementProvider);
			ret.getPages().add(mPage);
		}
		
		for (Marker marker: getMarkers()) {
			ret.getMarkers().add(markerFactory.apply(marker));
		}
		
		ret.setSource(toDataURI(true).toString());
		
		return ret;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + uri;
	}

}
