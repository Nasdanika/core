package org.nasdanika.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.nasdanika.common.persistence.MarkedArrayList;
import org.nasdanika.common.persistence.MarkedLinkedHashMap;
import org.nasdanika.common.persistence.Marker;

public class Util {

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
		return StringUtils.join(cca, " ");
	}
	
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	/**
	 * Processes a path (string separated by slashes) by removing <code>segment/..</code> pieces. E.g. <code>../a/../b</code> would be compacted to <code>../b</code>    
	 * @param str
	 * @return
	 */
	public static String compact(String path) {
//		path
		return null;
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
	public static Marker getMarker(Map<String,Object > map, String key) {
		return map instanceof MarkedLinkedHashMap ? ((MarkedLinkedHashMap<String,Object>) map).getMarker(key) : null;
	}
		
	/**
	 * Returns marker if collection is {@link MarkedArrayList} and the index is present is marked, or null otherwise.
	 * @param map
	 * @param key
	 * @return
	 */
	public static Marker getMarker(Collection<?> collection, int index) {
		return collection instanceof MarkedArrayList && index > -1 && index < collection.size() ? ((MarkedArrayList<?>) collection).getMarkers().get(index) : null;
	}
		
	/**
	 * Convenience method for adding a marker to exception messages if source is {@link MarkedLinkedHashMap}.
	 * @param candidate
	 * @param prefix
	 * @return
	 */
	public static String mark(String prefix, Object source, String key) {
		if (source instanceof MarkedLinkedHashMap) {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Marker marker = ((MarkedLinkedHashMap) source).getMarker(key);
			if (marker != null) {
				return (prefix == null ? "" : prefix) + marker.toString();
			}
		}
		
		return "";
	}
		
}
