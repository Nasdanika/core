package org.nasdanika.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.util.Date;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.emf.common.util.URI;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Reflective converter providing some common conversions, some of them chained
 * @author Pavel
 *
 */
public class DefaultConverter extends ReflectiveConverter {
	
	public static DefaultConverter INSTANCE = new DefaultConverter();
	
	// String to JSONObject and JSONArray
	
	@ConverterMethod
	public JSONArray toJSONArray(String str) {
		return new JSONArray(new JSONTokener(str));
	}
	
	@ConverterMethod
	public JSONObject toJSONObject(String str) {
		return new JSONObject(new JSONTokener(str));
	}
	
	/**
	 * Constructor conversion is not working well, using just type and toString value.
	 * @param obj
	 * @return
	 */
	@ConverterMethod
	public JSONObject toJSONObject(Object obj) {
		JSONObject ret = new JSONObject();
		ret.put("type", obj.getClass().getName());
		ret.put("value", obj.toString());
		return ret;
	}
	
	// Reader to String, and JSONObject and JSONArray.
	
	@ConverterMethod
	public String toString(Reader reader) throws IOException {
		StringWriter sw = new StringWriter();
		try (BufferedReader br = new BufferedReader(reader)) {
			int ch;
			while ((ch = br.read()) != -1) {
				sw.write(ch);
			}
		}
		sw.close();
		return sw.toString();
	}
	
	@ConverterMethod
	public JSONArray toJSONArray(Reader reader) {
		return new JSONArray(new JSONTokener(reader));
	}
	
	@ConverterMethod
	public JSONObject toJSONObject(Reader reader) {
		return new JSONObject(new JSONTokener(reader));
	}

	// InputStream to Reader, String, and JSONObject and JSONArray.
	
	/**
	 * Reads input stream into a byte array. 
	 * @param in
	 * @return
	 * @throws IOException 
	 */
	@ConverterMethod
	public byte[] toByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (InputStream bin = new BufferedInputStream(in)) {
			int b;
			while ((b = bin.read()) != -1) {
				baos.write(b);
			}
		}
		baos.close();
		return baos.toByteArray();				
	}
	
	/**
	 * Always UTF-8.
	 * @param in
	 * @return
	 */
	@ConverterMethod
	public Reader toReader(InputStream in) {
		return new InputStreamReader(in, StandardCharsets.UTF_8);
	}
			
	@ConverterMethod
	public String toString(byte[] bytes) throws IOException {
		return new String(bytes, StandardCharsets.UTF_8);
	}
	
	@ConverterMethod
	public String toString(InputStream in) throws IOException {
		return toString(toReader(in));
	}
	
	@ConverterMethod
	public JSONArray toJSONArray(InputStream in) {
		return toJSONArray(toReader(in));
	}
	
	@ConverterMethod
	public JSONObject toJSONObject(InputStream in) {
		return toJSONObject(toReader(in));
	}
	
	// URL to InputStream, Reader, String, and JSONObject and JSONArray.
	
	@ConverterMethod
	public InputStream toInputStream(URL url) throws IOException {
		return url.openStream();
	}
	
	/**
	 * Reads input stream into a byte array. 
	 * @param in
	 * @return
	 * @throws IOException 
	 */
	@ConverterMethod
	public byte[] toByteArray(URL url) throws IOException {
		return toByteArray(toInputStream(url));
	}
	
	/**
	 * Always UTF-8.
	 * @param in
	 * @return
	 * @throws IOException 
	 */
	@ConverterMethod
	public Reader toReader(URL url) throws IOException {
		return toReader(toInputStream(url));
	}
	
	/**
	 * Reads URL to String
	 * @param url
	 * @return
	 * @throws IOException
	 */
	@ConverterMethod
	public String stringContent(URL url) throws IOException {
		return toString(toReader(url));
	}
	
	@ConverterMethod
	public JSONArray toJSONArray(URL url) throws IOException {
		return toJSONArray(toReader(url));
	}
	
	@ConverterMethod
	public JSONObject toJSONObject(URL url) throws IOException {
		return new JSONObject(toReader(url));
	}
		
	@ConverterMethod
	public InputStream toInputStream(byte[] bytes) throws IOException {
		return new ByteArrayInputStream(bytes);
	}
	
	@ConverterMethod
	public InputStream toInputStream(CharSequence charSequence) throws IOException {
		return toInputStream(toByteArray(charSequence));
	}
	
	/**
	 * Converts character sequence to byte array with UTF_8 encoding.
	 * @param charSequence
	 * @return
	 * @throws IOException
	 */
	@ConverterMethod
	public byte[] toByteArray(CharSequence charSequence) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (Writer writer = new OutputStreamWriter(baos, StandardCharsets.UTF_8)) {
			writer.write(charSequence.toString());
		}
		baos.close();
		return baos.toByteArray();				
	}
	
	/**
	 * @param text
	 * @return parsed duration.
	 */
	@ConverterMethod
	public Duration toDuration(CharSequence text) {
		return Duration.parse(text);
	}

	/**
	 * @param text
	 * @return parsed period.
	 */
	@ConverterMethod
	public Period toPeriod(CharSequence text) {
		return Period.parse(text);
	}

	/**
	 * @param text
	 * @return parsed duration.
	 */
	@ConverterMethod
	public Instant toInstant(String text) {
		if (text == null) {
			return null;
		}
		if (text.contains("Z")) {
			return Instant.parse(text);
		}
		// Parsing via Date for convenience
		Date date = convert(text, Date.class);
		return date == null ? null : date.toInstant();
	}

	/**
	 * @param text
	 * @return parsed duration.
	 */
	@ConverterMethod
	public Instant toInstant(Date date) {
		return Instant.ofEpochMilli(date.getTime());
	}
	
	@ConverterMethod
	public URI toURI(String str) {
		return URI.createURI(str);
	}
	
	
	// URI
	
	@ConverterMethod
	public InputStream toInputStream(URI uri) throws IOException {
		if (Util.CLASSPATH_SCHEME.equals(uri.scheme())) {
			String resource = uri.toString().substring(Util.CLASSPATH_URL_PREFIX.length());
			return Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(resource), "ClassLoader resource not found: " + resource);
		}
		return toInputStream(new URL(uri.toString()));
	}
	
	/**
	 * Reads input stream into a byte array. 
	 * @param in
	 * @return
	 * @throws IOException 
	 */
	@ConverterMethod
	public byte[] toByteArray(URI uri) throws IOException {
		return toByteArray(toInputStream(uri));
	}
	
	/**
	 * Always UTF-8.
	 * @param in
	 * @return
	 * @throws IOException 
	 */
	@ConverterMethod
	public Reader toReader(URI uri) throws IOException {
		return toReader(toInputStream(uri));
	}
	
	/**
	 * Reads URL to String
	 * @param url
	 * @return
	 * @throws IOException
	 */
//	@ConverterMethod - Not a converter method because conversion breaks getting base URI property.
	public String stringContent(URI uri) throws IOException {
		return toString(toReader(uri));
	}
	
	@ConverterMethod
	public JSONArray toJSONArray(URI uri) throws IOException {
		return toJSONArray(toReader(uri));
	}
	
	@ConverterMethod
	public JSONObject toJSONObject(URI uri) throws IOException {
		return new JSONObject(toReader(uri));
	}
	
	@ConverterMethod
	public String toString(Node node) throws TransformerException, IOException {
	    DOMSource source = new DOMSource(node);
	    StringWriter out = new StringWriter();
		TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    
	    try (out) {
	    	transformer.transform(source, new StreamResult(out));
	    }
		return out.toString();
	}
	@ConverterMethod
	public Document parseXML(Reader reader) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		return dBuilder.parse(new InputSource(reader));
	}
	
	@ConverterMethod
	public Document parseXML(String xmlStr) throws ParserConfigurationException, SAXException, IOException {
		return parseXML(new StringReader(xmlStr));
	}
	
}
