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

import org.eclipse.emf.common.util.URI;
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
	 * @return a list of pages which represent nested diagram elements
	 */
	List<Page> getPages();
	
	Page createPage();
	
	/**
	 * Loads document from an XML string.
	 * @param docStr
	 * @param base
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
	 * @param uri
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
	 * @param base
	 * @return
	 * @throws ParserConfigurationException
	 */
	static Document create(boolean compressed, URI uri) throws ParserConfigurationException {
		return new DocumentImpl(compressed, uri);
	}

	/**
	 * Loads document using UTF-8 encoding.
	 * @param in
	 * @param uri
	 * @return
	 * @throws IOException
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	static Document load(InputStream in, URI uri) throws IOException, ParserConfigurationException, SAXException {
		return new DocumentImpl(in, uri);
	}
	
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
	 * Saves the document to String.
	 * @param compress If null keeps the original compression setting. If not null forces either compressed (TRUE) or uncompressed (FALSE).
	 * This can be used to generate HTML tags with compressed document while keeping the source document uncompressed so it can be manually inspected, edited, diffed, and merged.  
	 * @return Document string.
	 */
	String save(Boolean compress) throws TransformerException, IOException;

}
