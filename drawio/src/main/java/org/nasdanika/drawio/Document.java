package org.nasdanika.drawio;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.emf.common.util.URI;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.drawio.impl.DocumentImpl;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Represents the root mxfile element.
 * 
 * @author Pavel
 *
 */
public interface Document extends Element {
	
	/**
	 * @return a list of pages. This list supports add() method to insert pages from other documents. The add() method creates a clone of the page passed as a parameter. 
	 */
	List<Page> getPages();
	
	@Override
	default List<Page> getChildren() {
		return getPages();
	}
	
	/**
	 * @return Creates a new page and adds it to the list of pages.
	 */
	Page createPage();
	
	/**
	 * Loads document from an XML string.
	 * @param docStr
	 * @param uri Optional document URI for resolution of relative URI's.
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	static Document load(String docStr, URI uri) throws ParserConfigurationException, SAXException, IOException {
		return new DocumentImpl(docStr, uri);
	}

	/**
	 * 
	 * @param reader
	 * @param uri Optional document URI for resolution of relative URI's.
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	static Document load(Reader reader, URI uri) throws IOException, ParserConfigurationException, SAXException {
		return new DocumentImpl(reader, uri);
	}
	
	/**
	 * 
	 * @param compressed
	 * @param uri Optional document URI for resolution of relative URI's.
	 * @return
	 * @throws ParserConfigurationException
	 */
	static Document create(boolean compressed, URI uri) throws ParserConfigurationException {
		return new DocumentImpl(compressed, uri);
	}

	/**
	 * Loads document using UTF-8 encoding.
	 * @param in
	 * @param uri Optional document URI for resolution of relative URI's.
	 * @return
	 * @throws IOException
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	static Document load(InputStream in, URI uri) throws IOException, ParserConfigurationException, SAXException {
		return new DocumentImpl(in, uri);
	}
	
	/**
	 * @param uri
	 * @return true if the argument URI is drawio data URI, i.e. is starts with data:drawio[;base64],
	 */
	static boolean isDataURI(URI uri) {
		if (uri != null && "data".equals(uri.scheme())) {
			String uriStr = uri.toString();
			String DRAWIO = "data:drawio";
			return uriStr.startsWith(DRAWIO +";base64,") || uriStr.startsWith(DRAWIO +",");
		}
		return false;
	}

	/**
	 * Loads document from an URI - supports data: schema.
	 * @param source
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	static Document load(URI source) throws IOException, ParserConfigurationException, SAXException {
		if (isDataURI(source)) {
			String uriStr = source.toString();
			uriStr = uriStr.substring("data:drawio".length());				
			String BASE64 = ";base64,";
			if (uriStr.startsWith(BASE64)) {
				uriStr = new String(Base64.decodeBase64(URLDecoder.decode(uriStr.substring(BASE64.length()), StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
			} else {
				uriStr = URLDecoder.decode(uriStr.substring(1), StandardCharsets.UTF_8);
			}
			JSONObject payload = new JSONObject(new JSONTokener(uriStr));
			return load(payload.getString("document"), payload.has("uri") ? URI.createURI(payload.getString("uri")) : null);
		}
			
		return load(new URL(source.toString()));
	}
	
	/**
	 * Loads document from URL by opening a stream.
	 * @param source
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	static Document load(URL source) throws IOException, ParserConfigurationException, SAXException {
		try (InputStream in = source.openStream()) {
			return load(in, URI.createURI(source.toString()));
		}
	}
	
	/**
	 * Loads documents stored in PNG metadata using UTF-8 encoding.
	 * @param in
	 * @return
	 * @throws IOException
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	static List<Document> loadFromPngMetadata(InputStream in, URI uri) throws IOException, ParserConfigurationException, SAXException {
		ImageReader imageReader = ImageIO.getImageReadersByFormatName("png").next();
		imageReader.setInput(ImageIO.createImageInputStream(in));
		IIOMetadata metadata = imageReader.getImageMetadata(0);
		List<Document> ret = new ArrayList<>();
		for (int imageCount = imageReader.getNumImages(true), minIndex = imageReader.getMinIndex(), idx = 0; idx < imageCount; ++idx) {
			IIOMetadata imageMetadata = imageReader.getImageMetadata(minIndex + idx);
			if (imageMetadata != null) {
				for (String formatName: imageMetadata.getMetadataFormatNames()) {
					Node tree = metadata.getAsTree(formatName);
					NodeList childNodes = tree.getChildNodes();
					for (int i = 0; i < childNodes.getLength(); ++i) {
						Node child = childNodes.item(i);
						if (child instanceof org.w3c.dom.Element && "Text".equals(((org.w3c.dom.Element) child).getTagName())) {
							NodeList grandChildNodes = child.getChildNodes();
							for (int j = 0; j < grandChildNodes.getLength(); ++j) {
								Node grandChild = grandChildNodes.item(j);
								if (grandChild instanceof org.w3c.dom.Element 
										&& "TextEntry".equals(((org.w3c.dom.Element) grandChild).getTagName())
										&& ((org.w3c.dom.Element) grandChild).hasAttribute("keyword")
										&& "mxfile".equals(((org.w3c.dom.Element) grandChild).getAttribute("keyword"))) {
									String value = ((org.w3c.dom.Element) grandChild).getAttribute("value");
									ret.add(load((URLDecoder.decode(value, StandardCharsets.UTF_8)), uri));									
								}
							}
						}
					}
				}
			}
		}
		return ret;
	}
	
	/**
	 * Loads documents stored in PNG metadata using UTF-8 encoding.
	 * @param source
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	static List<Document> loadFromPngMetadata(URL source) throws IOException, ParserConfigurationException, SAXException {
		try (InputStream in = source.openStream()) {
			return loadFromPngMetadata(in, URI.createURI(source.toString()));
		}
	}
	
	/**
	 * @param compress If null keeps the original compression setting. If not null forces either compressed (TRUE) or uncompressed (FALSE).
	 * This can be used to generate HTML tags with compressed document while keeping the source document uncompressed so it can be manually inspected, edited, diffed, and merged.
	 * @param viewer URL of the viewer script. Default value is used if null.  
	 * @return div tag with the embedded document which can be inserted into HTML pages.
	 */
	String toHtml(Boolean compress, String viewer) throws TransformerException, IOException;

	/**
	 * Encodes document as data: URI.
	 * @return
	 */
	URI toDataURI(Boolean compress) throws TransformerException, IOException;

	/**
	 * Saves the document to String.
	 * @param compress If null keeps the original compression setting. If not null forces either compressed (TRUE) or uncompressed (FALSE).
	 * This can be used to generate HTML tags with compressed document while keeping the source document uncompressed so it can be manually inspected, edited, diffed, and merged.  
	 * @return Document string.
	 */
	String save(Boolean compress) throws TransformerException, IOException;

}
