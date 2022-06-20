package org.nasdanika.drawio.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.AbstractList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.Page;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


class PageImpl extends ElementImpl implements Page {
	
	/**
	 * Decompressed and parsed model document for compressed diagrams.
	 * Document root is the model.
	 */
	private org.w3c.dom.Document modelDocument;
	
	/**
	 * Model elements for uncompressed documents
	 */
	private List<org.w3c.dom.Element> modelElements;
	
	PageImpl(org.w3c.dom.Element element) throws IOException, ParserConfigurationException, SAXException {
		this.element = element;
		modelElements = DocumentImpl.getChildrenElements(element, "mxGraphModel");
		if (modelElements.isEmpty()) {
			String textContent = element.getTextContent();
			if (!Base64.isBase64(textContent)) {
				throw new IllegalArgumentException("Compressed diagram is not Base64 encoded");
			}
		    byte[] compressed = Base64.decodeBase64(textContent);
		    byte[] decompressed = inflate(compressed);
		    String decompressedStr = new String(decompressed, StandardCharsets.UTF_8);
		    String decodedStr = URLDecoder.decode(decompressedStr, StandardCharsets.UTF_8.name());
		    
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			modelDocument = dBuilder.parse(new InputSource(new StringReader(decodedStr)));
		}		
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
	protected List<? extends Element> getChildren() {
		return getModels();
	}

	@Override
	public List<Model> getModels() {
		return new AbstractList<Model>() {

			@Override
			public Model get(int index) {
				if (modelDocument == null) {
					return new ModelImpl(modelElements.get(index));
				}
				if (index == 0) {
					return new ModelImpl(modelDocument.getDocumentElement());
				}
				throw new IndexOutOfBoundsException(index);
			}

			@Override
			public int size() {
				return modelDocument == null ? modelElements.size() : 1;
			}
			
		};
	}

	@Override
	public String getName() {
		return element.getAttribute("name");
	}

	@Override
	public void setName(String name) {
		element.setAttribute("name", name);
	}	

}
