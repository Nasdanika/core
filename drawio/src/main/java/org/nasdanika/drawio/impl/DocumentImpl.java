package org.nasdanika.drawio.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Page;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DocumentImpl extends ElementImpl implements Document {
	
	private org.w3c.dom.Document document;

	public DocumentImpl(InputStream in) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		document = dBuilder.parse(in);
	}
	public DocumentImpl(Reader reader) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		document = dBuilder.parse(new InputSource(reader));
	}
	public DocumentImpl(String docStr) throws ParserConfigurationException, SAXException, IOException {
		this(new StringReader(docStr));
	}

	@Override
	public Element getElement() {
		return document.getDocumentElement();
	}

	@Override
	public <T> T accept(BiFunction<org.nasdanika.drawio.Element, Map<org.nasdanika.drawio.Element, T>, T> visitor) {
		Map<org.nasdanika.drawio.Element, T> pageResults = new LinkedHashMap<>();
		for (Page page: getPages()) {
			pageResults.put(page, page.accept(visitor));
		}
		return visitor.apply(this, pageResults);
	}

	@Override
	public List<Page> getPages() {
		// List backed by the XML document. Currently unmodifiable
		return new AbstractList<Page>() {

			@Override
			public Page get(int index) {
				try {
					return new PageImpl(getChildrenElements(getElement(), "diagram").get(index));
				} catch (IOException | ParserConfigurationException | SAXException e) {
					throw new IllegalArgumentException("Error loading compressed page", e);
				}
			}

			@Override
			public int size() {
				return getChildrenElements(getElement(), "diagram").size();
			}
			
		};
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
		for (Page page: getPages()) {
			((PageImpl) page).save(compress);
		}
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    
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
	@Override
	public Page createPage() {
		// TODO Auto-generated method stub
		return null;
	}

}
