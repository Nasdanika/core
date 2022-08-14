package org.nasdanika.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ConverterMethod;
import org.nasdanika.common.DefaultConverter;

/**
 * Reflective converter providing some common conversions, some of them chained
 * @author Pavel
 *
 */
public class Converter extends DefaultConverter {
	
	public static Converter INSTANCE = new Converter();
	
	/**
	 * Parses string as YAML.
	 * @param string
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ConverterMethod
	public Map<String, Object> toYamlMap(String string) {
		Object data = MarkingYamlConstructor.createMarkingYaml(null).load(string);
		return data instanceof Map ? (Map<String, Object>) data : null;
	}
	
	@SuppressWarnings("unchecked")
	@ConverterMethod
	public Map<String, Object> toYamlMap(Reader reader) {
		Object data = MarkingYamlConstructor.createMarkingYaml(null).load(reader);
		return data instanceof Map ? (Map<String, Object>) data : null;		
	}
		
	@SuppressWarnings("unchecked")
	@ConverterMethod
	public Map<String, Object> toYamlMap(InputStream inputStream) {
		Object data = MarkingYamlConstructor.createMarkingYaml(null).load(inputStream);
		return data instanceof Map ? (Map<String, Object>) data : null;		
	}
	
	@SuppressWarnings("unchecked")
	@ConverterMethod
	public Map<String, Object> toYamlMap(URL url) throws IOException {
		Object data = MarkingYamlConstructor.createMarkingYaml(url.toString()).load(url.openStream());
		return data instanceof Map ? (Map<String, Object>) data : null;		
	}
	
	@SuppressWarnings("unchecked")
	@ConverterMethod
	public Map<String, Object> toYamlMap(URI uri) throws IOException {
		Object data = MarkingYamlConstructor.createMarkingYaml(uri.toString()).load(toInputStream(uri));
		return data instanceof Map ? (Map<String, Object>) data : null;		
	}

	/**
	 * Parses string as YAML.
	 * @param string
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ConverterMethod
	public List<Object> toYamlList(String string) {
		Object data = MarkingYamlConstructor.createMarkingYaml(null).load(string);
		return data instanceof List ? (List<Object>) data : null;
	}
	
	@SuppressWarnings("unchecked")
	@ConverterMethod
	public List<Object> toYamlList(Reader reader) {
		Object data = MarkingYamlConstructor.createMarkingYaml(null).load(reader);
		return data instanceof List ? (List<Object>) data : null;
	}
		
	@SuppressWarnings("unchecked")
	@ConverterMethod
	public List<Object> toYamlList(InputStream inputStream) {
		Object data = MarkingYamlConstructor.createMarkingYaml(null).load(inputStream);
		return data instanceof List ? (List<Object>) data : null;
	}
	
	@SuppressWarnings("unchecked")
	@ConverterMethod
	public List<Object> toYamlList(URL url) throws IOException {
		Object data = MarkingYamlConstructor.createMarkingYaml(url.toString()).load(url.openStream());
		return data instanceof List ? (List<Object>) data : null;
	}
	
	@SuppressWarnings("unchecked")
	@ConverterMethod
	public List<Object> toYamlList(URI uri) throws IOException {
		Object data = MarkingYamlConstructor.createMarkingYaml(uri.toString()).load(toInputStream(uri));
		return data instanceof List ? (List<Object>) data : null;
	}
	
}
