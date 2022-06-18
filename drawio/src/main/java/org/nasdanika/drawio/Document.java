package org.nasdanika.drawio;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.nasdanika.drawio.impl.DocumentImpl;
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
	
	/**
	 * Loads document from an XML string.
	 * @param docStr
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	static Document load(String docStr) throws ParserConfigurationException, SAXException, IOException {
		return new DocumentImpl(docStr);
	}
	
	static Document load(Reader reader) throws IOException, ParserConfigurationException, SAXException {
		return new DocumentImpl(reader);
	}

	/**
	 * Loads document using UTF-8 encoding.
	 * @param in
	 * @return
	 * @throws IOException
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	static Document load(InputStream in) throws IOException, ParserConfigurationException, SAXException {
		return new DocumentImpl(in);
	}
	
	static Document load(URL source) throws IOException, ParserConfigurationException, SAXException {
		return load(source.openStream());
	}
	
	/**
	 * @param compress If null keeps the original compression setting. If not null forces either compressed (TRUE) or uncompressed (FALSE).
	 * This can be used to generate HTML tags with compressed document while keeping the source document uncompressed so it can be manually inspected, edited, diffed, and merged.  
	 * @return div tag with the embedded document which can be inserted into HTML pages.
	 */
	String toHtml(Boolean compress);

	/**
	 * Saves the document to String.
	 * @param compress If null keeps the original compression setting. If not null forces either compressed (TRUE) or uncompressed (FALSE).
	 * This can be used to generate HTML tags with compressed document while keeping the source document uncompressed so it can be manually inspected, edited, diffed, and merged.  
	 * @return Document string.
	 */
	String save(Boolean compress);

}
