package org.nasdanika.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.jsoup.Jsoup;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.MarkedArrayList;
import org.nasdanika.common.persistence.MarkedLinkedHashMap;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;

public class Util {

	public static final Pattern SENTENCE_PATTERN = Pattern.compile(".+?[\\.?!]+\\s+");		
		
	public static final BiFunction<String, Object, InputStream> OBJECT_TO_INPUT_STREAM_ENCODER = (path, contents) -> {
		InputStream ret = DefaultConverter.INSTANCE.convert(contents, InputStream.class);
		if (ret == null) {
			// toString() conversion
			ret = DefaultConverter.INSTANCE.convert(String.valueOf(contents), InputStream.class);
		}
		return ret;
	};		
	
	public static final BiFunction<String, InputStream, String> INPUT_STREAM_TO_STRING_DECODER = (path, state) -> DefaultConverter.INSTANCE.convert(state, String.class);

	public static final String CLASSPATH_SCHEME = "classpath";

	public static final String CLASSPATH_URL_PREFIX = CLASSPATH_SCHEME + "://";

	private Util() {
		// Singleton
	}
	
	/**
	 * Converts name to label text by breaking the name by camel case, capitalizing the first word and lowsercasing the rest.
	 * @return
	 */
	public static String nameToLabel(String name) {
		String[] cca = StringUtils.splitByCharacterTypeCamelCase(name);
		cca[0] = StringUtils.capitalize(cca[0]);
		for (int i=1; i<cca.length; ++i) {
			cca[i] = cca[i].toLowerCase();
		}
		return String.join(" ", cca);
	}
		
	/**
	 * Converts string in camel case, e.g. <code>firstName</code> to kebab case, e.g. <code>first-name</code>
	 * @return
	 */
	public static String camelToKebab(String str) {
		String[] ns = StringUtils.splitByCharacterTypeCamelCase(str);
		for (int i = 0; i < ns.length; ++i) {
			ns[i] = ns[i].toLowerCase();
		}
		return String.join("-", ns);
	}
	
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	/**
	 * Creates a function for context mapping which recognizes path navigation with ..
	 * @param prefix
	 * @return
	 */
	public static java.util.function.Function<String,String> hierarchicalMapper(String prefix) {		
		return key -> {
			StringTokenizer st = new StringTokenizer(prefix + key, "/", true);
			LinkedList<String> collector = new LinkedList<>();
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				if ("..".equals(token) && collector.size() > 1 && !"..".equals(collector.get(collector.size() - 2))) {
					collector.removeLast();
					collector.removeLast();
					if (st.hasMoreTokens()) {
						st.nextToken(); 
					}
				} else {
					collector.add(token);
				}
			}
			StringBuilder sb = new StringBuilder();
			collector.forEach(sb::append);
			return sb.toString();			
		};
	}
	
	public static InputStream join(InputStream... streams) throws IOException {
		if (streams.length == 1) {
			return streams[0];
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (InputStream in: streams) {
			if (in != null) {
				try (BufferedInputStream bin = new BufferedInputStream(in)) {
					int b;
					while ((b = bin.read()) != -1) {
						baos.write(b);
					}						
				}
			}
		}
		baos.close();
		return new ByteArrayInputStream(baos.toByteArray());
	}
	
	public static InputStream toStream(Context context, String text) throws IOException {
		Charset charset = context.get(Charset.class, StandardCharsets.UTF_8);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (text != null) {
			try (OutputStreamWriter writer = new OutputStreamWriter(baos, charset)) {
				writer.write(text);
			}
		}
		baos.close();
		return new ByteArrayInputStream(baos.toByteArray());
	}	
	
	public static FunctionFactory<String,InputStream> TO_STREAM = context -> new Function<String, InputStream>() {
		
		@Override
		public double size() {
			return 1;
		}
		
		@Override
		public String name() {
			return "String to InputStream";
		}
		
		@Override
		public InputStream execute(String text, ProgressMonitor progressMonitor) throws Exception {
			return toStream(context, text);
		}
		
	};
		
	public static FunctionFactory<Object,InputStream> OBJECT_TO_STREAM = context -> new Function<Object, InputStream>() {
		
		@Override
		public double size() {
			return 1;
		}
		
		@Override
		public String name() {
			return "Object to InputStream";
		}
		
		@Override
		public InputStream execute(Object object, ProgressMonitor progressMonitor) throws Exception {
			if (object == null) {
				return null;
			}
			if (object instanceof InputStream) {
				return (InputStream) object;
			}
			
			return asInputStreamSupplierFactory(object).create(context).execute(progressMonitor);
		}
		
	};
	
	
	public static String toString(Context context, InputStream in) throws IOException {
		if (in == null) {
			return "";
		}
		StringWriter sw = new StringWriter();
		Charset charset = context.get(Charset.class, StandardCharsets.UTF_8);
		try (Reader reader = new InputStreamReader(new BufferedInputStream(in), charset)) {
			int ch;
			while ((ch = reader.read()) != -1) {
				sw.write(ch);
			}
		}
		sw.close();
		return sw.toString();
	}	
	
	public static FunctionFactory<InputStream,String> TO_STRING = context -> new Function<InputStream,String>() {
		
		@Override
		public double size() {
			return 1;
		}
		
		@Override
		public String name() {
			return "InputStream to String";
		}
		
		@Override
		public String execute(InputStream stream, ProgressMonitor progressMonitor) throws Exception {
			return Util.toString(context, stream);
		}
		
	};
	
	public static FunctionFactory<String,String> TRIM = context -> new Function<String,String>() {
		
		@Override
		public double size() {
			return 1;
		}
		
		@Override
		public String name() {
			return "Trim";
		}
		
		@Override
		public String execute(String str, ProgressMonitor progressMonitor) throws Exception {
			return str == null ? "" : str.trim();
		}
		
	};
	
	public static FunctionFactory<String,String> INTERPOLATE_TO_STRING = context -> new Function<String,String>() {
		
		@Override
		public double size() {
			return 1;
		}
		
		@Override
		public String name() {
			return "String interpolation";
		}
		
		@Override
		public String execute(String input, ProgressMonitor progressMonitor) throws Exception {
			return context.interpolateToString(input);
		}
		
	};
	
	public static FunctionFactory<Object,Object> INTERPOLATE = context -> new Function<Object,Object>() {
		
		@Override
		public double size() {
			return 1;
		}
		
		@Override
		public String name() {
			return "Interpolation";
		}
		
		@Override
		public Object execute(Object input, ProgressMonitor progressMonitor) throws Exception {
			if (input instanceof String) {
				return context.interpolate((String) input);
			}
			if (input instanceof Map) {
				return context.interpolate((Map<?,?>) input);
			}
			if (input instanceof Collection) {
				return context.interpolate((Collection<?>) input);
			}
			
			throw new IllegalArgumentException("Cannot interpolate " + input);
		}
		
	};
	
	public static FunctionFactory<String,Object> INTERPOLATE_STRING = context -> new Function<String,Object>() {
		
		@Override
		public double size() {
			return 1;
		}
		
		@Override
		public String name() {
			return "String interpolation";
		}
		
		@Override
		public Object execute(String input, ProgressMonitor progressMonitor) throws Exception {
			return context.interpolate(input);
		}
		
	};
	
	public static FunctionFactory<Map<?,?>,Map<?,?>> INTERPOLATE_MAP = context -> new Function<Map<?,?>,Map<?,?>>() {
		
		@Override
		public double size() {
			return 1;
		}
		
		@Override
		public String name() {
			return "Map interpolation";
		}
		
		@Override
		public Map<?,?> execute(Map<?,?> input, ProgressMonitor progressMonitor) throws Exception {
			return context.interpolate(input);
		}
		
	};
	
	public static FunctionFactory<Collection<?>,List<?>> INTERPOLATE_COLLECTION = context -> new Function<Collection<?>, List<?>>() {
		
		@Override
		public double size() {
			return 1;
		}
		
		@Override
		public String name() {
			return "List interpolation";
		}
		
		@Override
		public List<?> execute(Collection<?> input, ProgressMonitor progressMonitor) throws Exception {
			return context.interpolate(input);
		}
		
	};
	
	public static InputStream filter(Context context, InputStream in, java.util.function.Function<String,String> filter) throws IOException {
		return toStream(context, filter.apply(toString(context, in)));
	}	
		
	public static Function<List<InputStream>, InputStream> JOIN_STREAMS = new Function<List<InputStream>, InputStream>() {

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Joining content";
		}

		@Override
		public InputStream execute(List<InputStream> content, ProgressMonitor progressMonitor) throws Exception {
			return join(content.toArray(new InputStream[content.size()]));
		}
		
	};
	
	public static FunctionFactory<List<InputStream>, InputStream> JOIN_STREAMS_FACTORY = new FunctionFactory<List<InputStream>, InputStream>() {

		@Override
		public Function<List<InputStream>, InputStream> create(Context context) throws Exception {
			return JOIN_STREAMS;
		}
		
	};
	
	/**
	 * Converts {@link Object} to {@link String} using {@link Util}.toString() if object is {@link InputStream} and using context converter, if it s present, otherwise.
	 */
	public static FunctionFactory<Object, String> OBJECT_TO_STRING_FACTORY = context -> new Function<Object,String>() {

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Object to String";
		}

		@Override
		public String execute(Object obj, ProgressMonitor progressMonitor) throws Exception {
			if (obj == null) {
				return null;
			}
			if (obj instanceof InputStream) {
				return Util.toString(context, (InputStream) obj);
			}
	    	Converter converter = context.get(Converter.class);
	    	if (converter != null) {
	    		String str = converter.convert(obj, String.class);
	    		if (str != null) {
	    			return str;
	    		}
	    	}
			return obj.toString();
		}
	};
		
	/**
	 * Returns marker if map is {@link MarkedLinkedHashMap} and the key is marked, or null otherwise.
	 * @param map
	 * @param key
	 * @return
	 */
	public static Marker getMarker(Map<?,?> map, Object key) {
		return map instanceof MarkedLinkedHashMap ? ((MarkedLinkedHashMap<?,?>) map).getMarker(key) : null;
	}
		
	/**
	 * Returns marker if collection is {@link MarkedArrayList} and the index is present is marked, or null otherwise.
	 * @param map
	 * @param key
	 * @return
	 */
	public static Marker getMarker(Collection<?> collection, int index) {
		return collection instanceof MarkedArrayList && index > -1 && index < ((MarkedArrayList<?>) collection).getMarkers().size() ? ((MarkedArrayList<?>) collection).getMarkers().get(index) : null;
	}
		
	/**
	 * Convenience method for adding a marker to exception messages if source is {@link MarkedLinkedHashMap}.
	 * @param candidate
	 * @param prefix
	 * @return
	 */
	public static String mark(String prefix, Object source, String key) {
		if (source instanceof MarkedLinkedHashMap) {
			@SuppressWarnings({"rawtypes" })
			Marker marker = ((MarkedLinkedHashMap) source).getMarker(key);
			if (marker != null) {
				return (prefix == null ? "" : prefix) + marker.toString();
			}
		}
		
		return "";
	}
	
	// --- Helper methods for loading configuration from maps

	/**
	 * Returns a {@link String} value or throws {@link ConfigurationException} if value is not null and not a string.
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getString(Map<?,?> map, Object key, String defaultValue) {
		Object val = map.get(key);
		if (val == null) {
			return defaultValue;
		}
		if (val instanceof String) {
			return (String) val;
		}
		throw new ConfigurationException(key + " value must be a string", getMarker(map, key));		
	}

	/**
	 * Returns a {@link String} value or throws {@link ConfigurationException} if value is not null and not a string.
	 * @param map
	 * @param key
	 * @return
	 */
	public static int getInt(Map<?,?> map, Object key, int defaultValue) {
		Object val = map.get(key);
		if (val == null) {
			return defaultValue;
		}
		if (val instanceof Number) {
			return ((Number) val).intValue();
		}
		if (val instanceof String) {
			try {
				return Integer.parseInt((String) val);
			} catch (NumberFormatException e) {
				throw new ConfigurationException(e.getMessage(), e, getMarker(map, key));						
			}
		}
		throw new ConfigurationException(key + " value must be a string", getMarker(map, key));		
	}

	/**
	 * Returns a {@link String} value or throws {@link ConfigurationException} if value is not null and not a string.
	 * @param map
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Map<?,?> map, Object key, boolean defaultValue) {
		Object val = map.get(key);
		if (val == null) {
			return defaultValue;
		}
		if (val instanceof Boolean) {
			return (Boolean) val;
		}
		throw new ConfigurationException(key + " value must be a boolean", getMarker(map, key));		
	}

	/**
	 * Returns a {@link Collection} value or throws {@link ConfigurationException} if value is not null and not a collection.
	 * @param map
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> getCollection(Map<String,?> map, Object key, Collection<T> defaultValue) {
		Object val = map.get(key);
		if (val == null) {
			return defaultValue;
		}
		if (val instanceof Collection) {
			return (Collection<T>) val;
		}
		throw new ConfigurationException(key + " value must be a collection", getMarker(map, key));		
	}

	/**
	 * Returns a {@link Map} value or throws {@link ConfigurationException} if value is not null and not a map.
	 * @param map
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K,V> Map<K,V> getMap(Map<?,?> map, Object key, Map<K,V> defaultValue) {
		Object val = map.get(key);
		if (val == null) {
			return defaultValue;
		}
		if (val instanceof Map) {
			return (Map<K,V>) val;
		}
		throw new ConfigurationException(key + " value must be a map", getMarker(map, key));		
	}
		
	/**
	 * Convenience method to evaluate Javascript
	 * @param script Script
	 * @param bindings Optional script bindings
	 * @return Evaluation result
	 * @throws ScriptException 
	 */
	public static Object eval(String script, Map<String,Object> bindings) throws Exception {
		org.mozilla.javascript.Context scriptContext = org.mozilla.javascript.Context.enter();
		try {
			Scriptable scope = scriptContext.initStandardObjects();
			for (Entry<String, Object> be: bindings.entrySet()) {
				ScriptableObject.putProperty(scope, be.getKey(), org.mozilla.javascript.Context.javaToJS(be.getValue(), scope));
			}

			return scriptContext.evaluateString(scope, script, "script", 1, null);
		} finally {
			org.mozilla.javascript.Context.exit();
		}
	}
	
	/**
	 * Convenience method to evaluate Javascript
	 * @param script Script
	 * @param bindings Optional script bindings
	 * @return Evaluation result
	 * @throws Exception 
	 */
	public static Object eval(InputStream script, Map<String,Object> bindings) throws Exception {
		return eval(DefaultConverter.INSTANCE.toString(script), bindings);
	}
	
	/**
	 * Convenience method to evaluate Javascript
	 * @param script Script
	 * @param bindings Optional script bindings
	 * @return Evaluation result
	 * @throws Exception 
	 */
	public static Object eval(URL script, Map<String,Object> bindings) throws Exception {
		return eval(DefaultConverter.INSTANCE.toString(script), bindings);
	}
		
	/**
	 * A convenience method to check for presence of required configuration keys
	 * @param config Configuration map.
	 * @param supportedKeys Supported keys.
	 * @param marker Map location.
	 * @return config argument
	 */
	public static Map<String,Object> checkRequiredKeys(Map<String,Object> config, String... requiredKeys) throws ConfigurationException {
		return checkRequiredKeys(config, Arrays.asList(requiredKeys));
	}
	
	/**
	 * A convenience method to check for presence of required configuration keys
	 * @param config Configuration map.
	 * @param supportedKeys Supported keys.
	 * @param marker Map location.
	 * @return config argument
	 */
	public static Map<String,Object> checkRequiredKeys(Map<String,Object> config, Collection<String> requiredKeys) throws ConfigurationException {
		StringBuilder missingKeyList = new StringBuilder();
		for (String rk: requiredKeys) {
			if (!config.containsKey(rk)) {
				if (missingKeyList.length() > 0) {
					missingKeyList.append(", ");
				}
				missingKeyList.append(rk);
			}
		}
		
		if (missingKeyList.length() == 0) {
			return config;
		}
		throw new ConfigurationException("Missing required configuration keys: " + missingKeyList, config instanceof Marked ? ((Marked) config).getMarker() : null);
	}

	/**
	 * A convenience method to check for presence of unsupported configuration keys
	 * @param config Configuration map.
	 * @param supportedKeys Supported keys.
	 * @param marker Map location.
	 * @return config argument.
	 */
	public static Map<?,?> checkUnsupportedKeys(Map<?,?> config, Collection<?> supportedKeys) throws ConfigurationException {
		if (config == null) {
			return config;
		}
		Collection<?> unsupportedKeys = new ArrayList<Object>(config.keySet());
		unsupportedKeys.removeAll(supportedKeys);
		if (unsupportedKeys.isEmpty()) {
			return config;
		}		
		
		if (unsupportedKeys.size() == 1) {
			Object unsupportedKey = unsupportedKeys.iterator().next();
			throw new ConfigurationException("Unsupported configuration key: " + unsupportedKey, getMarker(config, unsupportedKey));
		}
		
		StringBuilder keyList = new StringBuilder();
		for (Object uk: unsupportedKeys) {
			if (keyList.length() > 0) {
				keyList.append(", ");
			}
			keyList.append(uk);
		}
		throw new ConfigurationException("Unsupported configuration keys: " + keyList, config instanceof Marked ? ((Marked) config).getMarker() : null);
	}

	/**
	 * A convenience method to check for presence of unsupported configuration keys
	 * @param config Configuration map.
	 * @param supportedKeys Supported keys.
	 * @param marker Map location.
	 * @return config argument
	 */
	public static Map<?,?> checkUnsupportedKeys(Map<?,?> config, Object... supportedKeys) throws ConfigurationException {
		return checkUnsupportedKeys(config, Arrays.asList(supportedKeys));
	}
	
	/**
	 * Extracts first sentence from text
	 * @param context
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String firstSentence(String text, int minSentenceLength, int maxSentenceLength, String... abbreviations) {
		if (text == null || text.length() < minSentenceLength) {
			return text;
		}
		Matcher matcher = SENTENCE_PATTERN.matcher(text);		
		Z: while (matcher.find()) {
			String group = matcher.group();
			for (String abbr: abbreviations) {
				if (group.trim().endsWith(abbr)) {
					continue Z;
				}
			}
			if (matcher.end() > minSentenceLength && matcher.end() < maxSentenceLength) {
				return text.substring(0, matcher.end());
			}
		}
		
		return text.length() < maxSentenceLength ? text : text.substring(0, maxSentenceLength)+"...";
	}
	
	/**
	 * Extracts first sentence from text
	 * @param context
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static String firstPlainTextSentence(String html, int minSentenceLength, int maxSentenceLength, String... abbreviations) {
		if (html == null || html.length() < minSentenceLength) {
			return Jsoup.parse(html).text();
		}
		return firstSentence(Jsoup.parse(html).text(), minSentenceLength, maxSentenceLength, abbreviations);
	}	

	/**
	 * Gets string configuration value.
	 * @param configMap
	 * @param key
	 * @param required
	 * @return
	 */
	public static String getString(Map<?, ?> configMap, Object key, boolean required, Marker marker) {
		if (configMap.containsKey(key)) {
			Object val = configMap.get(key);
			if (val == null && !required) {
				return null;
			}
			if (val instanceof String) {
				return (String) val;
			} 
			
			throw new ConfigurationException(key + " value must be a string", getMarker(configMap, key));
		}
		
		if (required) {
			throw new ConfigurationException(key + " is missing", marker);			
		}
		
		return null;
	}

	/**
	 * Loads values from a key which can be either a string (single value) or a list of strings (multi-value)
	 * @param configMap
	 * @param key
	 * @param consumer
	 */
	public static void loadMultiString(Map<?,?> configMap, Object key, Consumer<String> consumer) {
		if (configMap.containsKey(key)) {
			Object val = configMap.get(key);
			if (val instanceof String) {
				consumer.accept((String) val);
			} else if (val instanceof Collection) {
				int idx = 0;
				for (Object ve: (Collection<?>) val) {
					if (ve instanceof String) {
						consumer.accept((String) ve);
					} else {
						throw new ConfigurationException(key + " element must be a string", getMarker((Collection<?>) val, idx));							
					}
					++idx;
				}
			} else {
				throw new ConfigurationException(key + " value must be a string or list", getMarker(configMap, key));
			}
		}		
	}

	/**
	 * Executes full supplier lifecycle - diagnose, execute, commit/rollback, close.
	 * @param monitor
	 * @param diagnosticConsumer TODO
	 * @param context
	 * @param component
	 * @return
	 * @throws Exception
	 */
	public static <T> T call(Supplier<T> supplier, ProgressMonitor monitor, Consumer<Diagnostic> diagnosticConsumer, Status... failOnStatuses) throws Exception {
		try (ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling supplier", 3)) {
			Diagnostic diagnostic = supplier.splitAndDiagnose(progressMonitor);
			if (diagnosticConsumer != null) {
				diagnosticConsumer.accept(diagnostic);
			}
			Status status = diagnostic.getStatus();
			if (failOnStatuses.length == 0) {
				if (status == Status.FAIL) {
					throw new DiagnosticException(diagnostic);
				}
			} else {
				for (Status failStatus: failOnStatuses) {
					if (status == failStatus) {
						throw new DiagnosticException(diagnostic);
					}					
				}
			}
			
			try {
				T result = supplier.splitAndExecute(progressMonitor);
				supplier.splitAndCommit(progressMonitor);
				return result;
			} catch (Exception e) {
				supplier.splitAndRollback(progressMonitor);
				throw e;
			}
		} finally {
			supplier.close();
		}
	}
	
	/**
	 * Executes full Command lifecycle - diagnose, execute, commit/rollback, close.
	 * @param context
	 * @param monitor
	 * @param component
	 * @param failOnStatuses Status on which to throw {@link DiagnosticException}. Defaults to FAIL.
	 * @return
	 * @throws Exception
	 */
	public static Diagnostic call(Command command, ProgressMonitor monitor, Status... failOnStatuses) throws Exception {
		try (ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling command", 3)) {
			Diagnostic diagnostic = command.splitAndDiagnose(progressMonitor);			
			Status status = diagnostic.getStatus();
			if (failOnStatuses.length == 0) {
				if (status == Status.FAIL) {
					throw new DiagnosticException(diagnostic);
				}
			} else {
				for (Status failStatus: failOnStatuses) {
					if (status == failStatus) {
						throw new DiagnosticException(diagnostic);
					}					
				}
			}
			
			try {
				command.splitAndExecute(progressMonitor);
				command.splitAndCommit(progressMonitor);
			} catch (Exception e) {
				command.splitAndRollback(progressMonitor);
				throw e;
			}
			return diagnostic;
		} finally {
			command.close();
		}
	}
	
	/**
	 * Executes full Consumer lifecycle - diagnose, execute, commit/rollback, close.
	 * @param context
	 * @param monitor
	 * @param component
	 * @param failOnStatuses Status on which to throw {@link DiagnosticException}. Defaults to FAIL.
	 * @return
	 * @throws Exception
	 */
	public static <T> Diagnostic call(org.nasdanika.common.Consumer<T> consumer, T arg,  ProgressMonitor monitor, Status... failOnStatuses) throws Exception {
		try (ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling consumer", 3)) {
			Diagnostic diagnostic = consumer.splitAndDiagnose(progressMonitor);			
			Status status = diagnostic.getStatus();
			if (failOnStatuses.length == 0) {
				if (status == Status.FAIL) {
					throw new DiagnosticException(diagnostic);
				}
			} else {
				for (Status failStatus: failOnStatuses) {
					if (status == failStatus) {
						throw new DiagnosticException(diagnostic);
					}					
				}
			}
			
			try {
				consumer.splitAndExecute(arg, progressMonitor);
				consumer.splitAndCommit(progressMonitor);
			} catch (Exception e) {
				consumer.splitAndRollback(progressMonitor);
				throw e;
			}
			return diagnostic;
		} finally {
			consumer.close();
		}
	}
	
	/**
	 * Calls asSupplierFactory(obj, null, null)
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T> SupplierFactory<T> asSupplierFactory(Object obj) {
		return asSupplierFactory(obj, null, null);
	}	

	/**
	 * Wraps object into a supplier factory. Handles adapters, and collections.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> SupplierFactory<T> asSupplierFactory(Object obj, Class<T> type, FunctionFactory<List<T>, T> joinFactory) {
		if (obj instanceof Collection && joinFactory != null) {
			ListCompoundSupplierFactory<T> ret = new ListCompoundSupplierFactory<>("Supplier collection");
			for (Object e: (Collection<?>) obj) {
				SupplierFactory<T> esp = type == InputStream.class ? (SupplierFactory<T>) asInputStreamSupplierFactory(e) : asSupplierFactory(e, type, joinFactory);
				if (esp != null) {
					ret.add(esp);
				}
			}
			return ret.then(joinFactory);
		}
		
		if (obj instanceof SupplierFactory) {		
			return (SupplierFactory<T>) obj;
		}
		
		if (obj instanceof SupplierFactory.Provider && type != null) {
			SupplierFactory<T> factory = ((SupplierFactory.Provider) obj).getFactory(type);
			if (factory != null) {
				return factory;
			}
		}
		
		if (obj instanceof Adaptable) {
			Adaptable adaptable = (Adaptable) obj;
			if (type != null) {
				SupplierFactory.Provider provider = adaptable.adaptTo(SupplierFactory.Provider.class);
				if (provider != null) {
					SupplierFactory<T> factory = provider.getFactory(type);
					if (factory != null) {
						return factory;
					}
				}
			}
			
			SupplierFactory<T> adapter = adaptable.adaptTo(SupplierFactory.class);
			if (adapter != null) {
				return adapter;
			}
		}
		
		return null;
	}

	/**
	 * Wraps object into an {@link InputStream} supplier factory. Handles adapters, and collection and scalar cases.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static SupplierFactory<InputStream> asInputStreamSupplierFactory(Object obj) {
		SupplierFactory<InputStream> ret = asSupplierFactory(obj, InputStream.class, JOIN_STREAMS_FACTORY);
		if (ret != null) {			
			return ret.then(OBJECT_TO_STREAM);
		}
		
		// Converting to string, interpolating, streaming
		SupplierFactory<String> textFactory = context -> new Supplier<String>() {
	
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Scalar";
			}
	
			@Override
			public String execute(ProgressMonitor progressMonitor) throws Exception {
				return context.interpolateToString(String.valueOf(obj));
			}
		
		};
		
		return textFactory.then(TO_STREAM);
	}

	/**
	 * Wraps object into an {@link BinaryEntityContainer} consumer factory. Handles adapters and collections.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static ConsumerFactory<BinaryEntityContainer> asConsumerFactory(Object obj)  {
		return asConsumerFactory(obj, obj instanceof Marked ? ((Marked) obj).getMarker() : null);
	}

	/**
	 * Wraps object into an {@link BinaryEntityContainer} consumer factory. Handles adapters and collections.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> ConsumerFactory<T> asConsumerFactory(Object obj, Marker marker) {
		if (obj instanceof Collection) {
			CollectionCompoundConsumerFactory<T> ret = new CollectionCompoundConsumerFactory<>("Consumer collection");
			int idx = 0;
			for (Object e: (Collection<?>) obj) {
				ret.add(asConsumerFactory(e, getMarker((Collection<?>) obj, idx++)));
			}
			return ret;
		}
		
		if (obj instanceof ConsumerFactory) {		
			return (ConsumerFactory<T>) obj;
		}		
		
		if (obj instanceof Adaptable) {
			ConsumerFactory<T> adapter = ((Adaptable) obj).adaptTo(ConsumerFactory.class);
			if (adapter != null) {
				return adapter;
			}
		}
		
		throw new ConfigurationException(obj.getClass() + " cannot be wrapped/adapted to a consumer factory", marker);
	}

	/**
	 * Wraps object into a {@link CommandFactory}. Handles adapters and collections.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static CommandFactory asCommandFactory(Object obj) {
		return asCommandFactory(obj, obj instanceof Marked ? ((Marked) obj).getMarker() : null);
	}

	/**
	 * Wraps object into a {@link CommandFactory}. Handles adapters and collections.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static CommandFactory asCommandFactory(Object obj, Marker marker) {
		if (obj instanceof Collection) {
			CompoundCommandFactory ret = new CompoundCommandFactory("Command collection");
			int idx = 0;
			for (Object e: (Collection<?>) obj) {
				ret.add(asCommandFactory(e, getMarker((Collection<?>) obj, idx++)));
			}
			return ret;
		}
		
		if (obj instanceof CommandFactory) {		
			return (CommandFactory) obj;
		}
		
		if (obj instanceof Adaptable) {
			CommandFactory adapter = ((Adaptable) obj).adaptTo(CommandFactory.class);
			if (adapter != null) {
				return adapter;
			}
		}
				
		throw new ConfigurationException(obj.getClass() + " cannot be wrapped/adapted to a command factory", marker);
	}
	
	/**
	 * Grouping by with support of null keys.
	 * @param <K>
	 * @param <T>
	 * @param elements
	 * @param keyFeature
	 * @return
	 */
	public static <K, T> Map<K, List<T>> groupBy(Collection<T> elements, java.util.function.Function<? super T, ? extends K> classifier) {
		Map<K, List<T>> ret = new LinkedHashMap<>();
		for (T e: elements) {
			K k = classifier.apply(e);
			ret.computeIfAbsent(k, key -> new ArrayList<>()).add(e);
		}
		return ret;
	}	

	// --- Property computers
	
	public static PropertyComputer createEmbeddedImagePropertyComputer(java.util.function.Function<String,URL> resolver) {
		return new PropertyComputer() {
			
			@Override
			public <T> T compute(Context context, String key, String path, Class<T> type) {
				return computeEmbeddedImage(context, key, path, type, resolver);
			}
			
		};
	}
	
	@SuppressWarnings("unchecked")
	public static <U> U computeEmbeddedImage(Context context, String key, String path, Class<U> type, java.util.function.Function<String,URL> resolver) { 
		if (type == null || type == String.class) { 
			int idx = path.indexOf("/"); 
			if (idx == -1) { 
				return null;
			}
			StringBuilder imageTag = new StringBuilder("<img src=\"");
			imageTag.append(computeEmbeddedImageData(context, key, path, type, resolver));
			imageTag.append("\"/>"); 
			String imagePath = path.substring(idx + 1).trim(); 
			int spaceIdx = imagePath.indexOf(' ');
			if (spaceIdx == -1) {
				return (U) imageTag.toString();
			}
			
			StringBuilder tableBuilder = new StringBuilder("<table style=\"width:auto\" class=\"table\">").append(System.lineSeparator())
					.append("  <tbody>").append(System.lineSeparator())
					.append("    <tr>").append(System.lineSeparator())
					.append("      <td>").append(imageTag).append("</td>").append(System.lineSeparator())
					.append("    </tr>").append(System.lineSeparator())
					.append("    <tr class=\"bg-light\">").append(System.lineSeparator())
					.append("      <td>").append(imagePath.substring(spaceIdx + 1)).append("</td>").append(System.lineSeparator())
					.append("    </tr>").append(System.lineSeparator())
					.append("  </tbody>").append(System.lineSeparator())
					.append("</table>").append(System.lineSeparator());
			return (U) tableBuilder.toString();
		}
		return null;
	}
	
	public static PropertyComputer createEmbeddedImageDataPropertyComputer(java.util.function.Function<String,URL> resolver) {
		return new PropertyComputer() {
			
			@Override
			public <T> T compute(Context context, String key, String path, Class<T> type) {
				return computeEmbeddedImageData(context, key, path, type, resolver);
			}
			
		};
	}
		
	@SuppressWarnings("unchecked")
	public static <U> U computeEmbeddedImageData(Context context, String key, String path, Class<U> type, java.util.function.Function<String,URL> resolver) { 
		if (type == null || type == String.class) { 
			int idx = path.indexOf("/"); 
			if (idx == -1) { 
				return null;
			}
			try {
				StringBuilder imageData = new StringBuilder("data:image/" + path.substring(0, idx) + ";base64,");
				String imagePath = path.substring(idx + 1).trim(); 
				int spaceIdx = imagePath.indexOf(' ');
				URL imageURL = resolver.apply(spaceIdx == -1 ? imagePath : imagePath.substring(0, spaceIdx));
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE); 
				byte[] imageBytes = converter.convert(imageURL.openStream(), byte[].class); 
				imageData.append(Base64.getEncoder().encodeToString(imageBytes)); 
				return (U) imageData.toString();
			} catch (Exception e) {
				throw new NasdanikaException("Error embedding image data '" + path + "': " + e, e);
			}
		}
		return null;
	}
	
	public static PropertyComputer createIncludePropertyComputer(java.util.function.Function<String,URL> resolver) {
		return new PropertyComputer() {
			
			@Override
			public <T> T compute(Context context, String key, String path, Class<T> type) {
				return computeInclude(context, key, path, type, resolver);
			}
			
		};
	}
	
	@SuppressWarnings("unchecked")
	public static <U> U computeInclude(Context context, String key, String path, Class<U> type, java.util.function.Function<String,URL> resolver) { 
		if (type == null || type == String.class) { 
			try {
				URL includeURL = resolver.apply(path);
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				String includeContent = converter.convert(includeURL.openStream(), String.class); 
				return (U) context.interpolateToString(includeContent);
			} catch (Exception e) {
				throw new NasdanikaException("Error including '" + path +	"': " + e, e);
			}
		}
		return null;
	}
	
	public static PropertyComputer createIncludeMarkdownPropertyComputer(java.util.function.Function<String,URL> resolver) {
		return new PropertyComputer() {
			
			@Override
			public <T> T compute(Context context, String key, String path, Class<T> type) {
				return computeIncludeMarkdown(context, key, path, type, resolver);
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	public static <U> U computeIncludeMarkdown(Context context, String key, String path, Class<U> type, java.util.function.Function<String,URL> resolver) { 
		if (type == null || type == String.class) {
			try {
				URL includeURL = resolver.apply(path);
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				String markdown = converter.convert(includeURL.openStream(), String.class);
				String html = context.get(MarkdownHelper.class, MarkdownHelper.INSTANCE).markdownToHtml(markdown);
				return (U) context. interpolateToString(html) ;
			} catch (Exception e) {
				throw new NasdanikaException("Error including markdown '" + path + "': " + e, e);
			}
		}
		return null;
	}
	
	/**
	 * A method to get compiler happy without strange casts and suppressing warnings
	 * @param <T>
	 * @param type
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Class<SupplierFactory<T>> getSupplierFactoryClass(Class<T> type) {
		return (Class<SupplierFactory<T>>) (Class) SupplierFactory.class;
	}
	
	/**
	 * A method to get compiler happy without strange casts and suppressing warnings
	 * @param <T>
	 * @param type
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Class<ConsumerFactory<T>> getConsumerFactoryClass(Class<T> type) {
		return (Class<ConsumerFactory<T>>) (Class) ConsumerFactory.class;
	}
	
	/**
	 * A method to get compiler happy without strange casts and suppressing warnings
	 * @param <T>
	 * @param type
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T,R> Class<FunctionFactory<T,R>> getFunctionFactoryClass(Class<T> type, Class<R> returnType) {
		return (Class<FunctionFactory<T,R>>) (Class) FunctionFactory.class;
	}

	/**
	 * Wraps texts so its line width grows proportionally as the number of lines increases. 
	 * @param text
	 * @param initialLineWidth
	 * @param separator
	 * @return
	 */
	public static String wrap(String text, int initialLineWidth, int widthIncrementPerLine, String separator) {
		if (Util.isBlank(text) || text.length() < initialLineWidth) { 
			return text;
		}
		double lines = quadraticRoot(widthIncrementPerLine, initialLineWidth - widthIncrementPerLine, -text.length()); 
		return WordUtils.wrap(text, initialLineWidth + (int) (widthIncrementPerLine * Math.ceil(lines)), separator, false);
	}
	
	/**
	 * Inserts separators into a list of elements so when these elements are rendered as text the text is wrapped in such a way that
	 * line width grows proportionally as the number of lines increases. 
	 * @param elements Elements to wrap 
	 * @param initialLineWidth
	 * @param separatorSupplier creates separators to insert into the resulting list.
	 * @return
	 */
	public static <T> List<T> wrap(
			List<T> elements, 
			ToIntFunction<T> elementLengthComputer,  
			int initialLineWidth, 
			int widthIncrementPerLine, 
			java.util.function.Supplier<T> wordSeparatorSupplier,			
			java.util.function.Supplier<T> lineSeparatorSupplier) {
		int length = elements.stream().mapToInt(elementLengthComputer).sum();		
		int wrapLength = initialLineWidth;
		if (length > initialLineWidth) { 
			wrapLength += (int) (widthIncrementPerLine * Math.ceil(quadraticRoot(widthIncrementPerLine, initialLineWidth - widthIncrementPerLine, -length)));
		}
		List<T> ret = new ArrayList<T>();
		int lineLength = 0;
		for (T element: elements) {
			if (lineLength > wrapLength) {
				ret.add(lineSeparatorSupplier.get());
				lineLength = 0;
			} 
			if (lineLength > 0) {
				ret.add(wordSeparatorSupplier.get());
			}
			ret.add(element);
			lineLength += elementLengthComputer.applyAsInt(element);
		}
		return ret;
	}	
	
	private static double quadraticRoot(double a, double b, double c) { 
		return (-b + Math.sqrt(b*b - 4 * a * c)) / (2 * a);
	}
	
	private static final String PACKAGE_SUMMARY_HTML = "package-summary.html";
	
	/**
	 * Java 8 uses package-list, Java 11 uses element-list.
	 */
	private static final String[] PACKAGE_LIST_LOCATIONS = { "package-list", "element-list" };	
	
	private static final String MODULE_PREFIX = "module:";
	
	/**
	 * @param urls Mapping of online URL's (to build links) to offline URL's (to load package lists from).  
	 * @return Function which given a fully qualified class name returns an anchor linking to Javadoc page for the class. The argument may contain a hash symbol (fragment). 
	 * Everything after the hash gets transfered to the computed URL providing ability to address fields and methods.
	 * 
	 */
	public static java.util.function.Function<String, String> createJavadocResolver(Map<URL,URL> urls, ProgressMonitor progressMonitor) {
		// package -> Base URL.
		Map<String,String> unsortedPackageMap = new HashMap<>();		
		progressMonitor.setWorkRemaining(urls.size());
		Z: for (Map.Entry<URL, URL> urlEntry: urls.entrySet()) {
			for (String packageListLocation: PACKAGE_LIST_LOCATIONS) {
				try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(urlEntry.getValue(), packageListLocation).openStream()))) {
					String module = null;
					String line;
					while ((line = br.readLine()) != null) {
						if (line.startsWith(MODULE_PREFIX)) {
							module = line.substring(MODULE_PREFIX.length()); 
						} else {
							String packageLocation = urlEntry.getKey().toString() + (module == null ? "index.html?" : module + "/");
							unsortedPackageMap.put(line.trim(), packageLocation);
						}
					}
					progressMonitor.worked(Status.SUCCESS, 1, "Loaded package list from " + urlEntry.getValue() + " for " + urlEntry.getKey());
					continue Z;
				} catch (Exception e) {
					progressMonitor.worked(Status.ERROR, 1, "Could not download package list from " + urlEntry.getValue() + " - " + e);
				}
			}
		}
		
		Map<String,String> sortedPackageMap = new LinkedHashMap<>();
		unsortedPackageMap.keySet().stream().sorted((k1,k2) -> k2.length() - k1.length()).forEach(k -> sortedPackageMap.put(k, unsortedPackageMap.get(k)));
		
		return new java.util.function.Function<String, String>() {
			
			private String getRef(String spec) {
				int hashIdx = spec.indexOf("#");
				if (hashIdx != -1) {
					spec = spec.substring(0, hashIdx);
				}
				for (Entry<String, String> location: sortedPackageMap.entrySet()) {
					String key = location.getKey();
					String value = location.getValue();
					if (key.equals(spec)) { // Package
						return value + spec.replace('.', '/') + "/" + PACKAGE_SUMMARY_HTML;
					}
					
					int idx = spec.lastIndexOf('.');
					if (idx != -1 && spec.substring(0, idx).equals(key)) { // Class
						return value + spec.replace('.', '/').replace('$', '.') + ".html";				
					}
				}
				return null;				
			}

			@Override
			public String apply(String key) {
				if (key == null) {
					return key;
				}
				
				int spaceIdx = key.indexOf(' ');
				String spec = spaceIdx == -1 ? key : key.substring(0, spaceIdx);
				String text = spaceIdx == -1 ? null : key.substring(spaceIdx + 1);
				
				String href = getRef(spec);
				if (href == null) {
					return spec;
				}
								
				int hashIdx = spec.indexOf("#");
				if (hashIdx != -1) {
					String fragment = spec.substring(hashIdx+1);
					int firstParenthesisIdx = fragment.indexOf("(");
					if (firstParenthesisIdx > 0 && fragment.endsWith(")")) {
						// Convert (type[,type]) to -type-type-						
						StringBuilder fragmentBuilder = new StringBuilder(fragment.substring(0, firstParenthesisIdx)).append("-");
						String[] pTypes = fragment.substring(firstParenthesisIdx+1, fragment.length()-1).split(",");
						for (String pType: pTypes) {
							pType = pType.trim();
							int bIdx = pType.indexOf("[]");
							if (bIdx == -1) {
								fragmentBuilder.append(pType);
							} else {
								fragmentBuilder.append(pType.substring(0, bIdx));
								for (int bc = bIdx; bc < pType.length(); bc+=2) {
									fragmentBuilder.append(":A");
								}
							}
							fragmentBuilder.append("-");
						}
						fragment = fragmentBuilder.toString();
					} 
					href += "#" + fragment;
				}
				return new StringBuilder("<a href='")
						.append(href)
						.append("'>")
						.append(Util.isBlank(text) ? spec.replace('#', '.').replace('$', '.') : text)
						.append("</a>").toString();
			}
			
		};
		
	}
	
	/**
	 * @param urls List of URL's.  
	 * @return Function which given a fully qualified class name returns a URL of Javadoc page for the class.
	 */
	public static java.util.function.Function<String, String> createJavadocResolver(Collection<URL> urls, ProgressMonitor progressMonitor) {
		Map<URL,URL> urlMap = new HashMap<>();
		urls.forEach(url -> urlMap.put(url, url));
		return createJavadocResolver(urlMap, progressMonitor);
	}
	
	/**
	 * Creates a Javadoc resolver for Nasdanika products and their dependencies.
	 * @param gitDir Git directory with Nasdanika products locally built using Maven so they contain local API docs in their target/apidocs folders to use as offline URL's. If null of if some of apidocs directories do not exist, online URL's are used as offline URL's for those cases.  
	 * @param progressMonitor
	 * @return
	 */
	public static java.util.function.Function<String, String> createNasdanikaJavadocResolver(File gitDir, ProgressMonitor progressMonitor) throws MalformedURLException {
		Map<URL,URL> urls = new HashMap<>();

		URL java8 = new URL("https://docs.oracle.com/javase/8/docs/api/");
		urls.put(java8, java8);
		
		// Core
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/core/modules/common/apidocs/"), gitDir, "core/common/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/core/modules/ncore/apidocs/"), gitDir, "core/ncore/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/core/modules/flow/apidocs/"), gitDir, "core/flow/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/core/modules/diagram/modules/model/apidocs/"), gitDir, "core/diagram/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/core/modules/diagram/modules/gen/apidocs/"), gitDir, "core/diagram.gen/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/core/modules/exec/modules/model/apidocs/"), gitDir, "core/exec/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/core/modules/exec/modules/gen/apidocs/"), gitDir, "core/exec.gen/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/core/modules/cli/apidocs/"), gitDir, "core/cli/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/core/modules/emf/apidocs/"), gitDir, "core/emf/target/apidocs");
		
		// HTML
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/html/apidocs/"), gitDir, "html/html/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/bootstrap/apidocs/"), gitDir, "html/bootstrap/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/jstree/apidocs/"), gitDir, "html/jstree/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/emf/apidocs/"), gitDir, "html/emf/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/models/modules/html/modules/model/apidocs/"), gitDir, "html/model/html/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/models/modules/html/modules/gen/apidocs/"), gitDir, "html/model/html.gen/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/models/modules/bootstrap/modules/model/apidocs/"), gitDir, "html/model/bootstrap/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/models/modules/bootstrap/modules/gen/apidocs/"), gitDir, "html/model/bootstrap.gen/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/models/modules/app/modules/model/apidocs/"), gitDir, "html/model/app/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/models/modules/app/modules/gen/apidocs/"), gitDir, "html/model/app.gen/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/ecore/apidocs/"), gitDir, "html/ecore/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/html/modules/flow/apidocs/"), gitDir, "html/flow/target/apidocs");
		
		// Engineering		
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/engineering/modules/model/apidocs/"), gitDir, "engineering/model/target/apidocs");
		addUrl(urls, new URL("https://docs.nasdanika.org/modules/engineerng/modules/gen/apidocs/"), gitDir, "engineering/gen/target/apidocs");
		
		return createJavadocResolver(urls, progressMonitor);
	}
	
	private static void addUrl(Map<URL,URL> urls, URL onlineURL, File gitDir, String offlinePath) throws MalformedURLException {
		URL offlineURL = onlineURL;
		if (gitDir != null && gitDir.isDirectory()) {
			File offlineDir = new File(gitDir, offlinePath);
			if (offlineDir.isDirectory()) {
				offlineURL = offlineDir.toURI().toURL();
			}
		}
		urls.put(onlineURL, offlineURL);
	}
	
	public static PropertyComputer createJavadocPropertyComputer(java.util.function.Function<String, String> javadocResolver) {
		return new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> T compute(Context context, String key, String path, Class<T> type) {
				if (type == null || type.isAssignableFrom(String.class)) {
					return (T) javadocResolver.apply(path);
				}
				return null;
			}
		};
	}

	/**
	 * Walks the directory passing files and their paths to the listener.
	 * @param source
	 * @param target
	 * @param cleanTarget
	 * @param cleanPredicate
	 * @param listener
	 * @throws IOException
	 */
	public static void walk(String path, BiConsumer<File,String> listener, File... files) {
		if (files != null) {
			for (File file: files) {
				String filePath = path == null ? file.getName() : path + "/" + file.getName();
				if (file.isDirectory()) {
					walk(filePath, listener, file.listFiles());
				} else if (file.isFile() && listener != null) {
					listener.accept(file, filePath);
				}
			}
		}
	}
	
}
