package org.nasdanika.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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

}
