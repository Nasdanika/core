package org.nasdanika.common;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Util {

	private static final String WIDTH_ATTRIBUTE = "width";
	private static final String HEIGHT_ATTRIBUTE = "height";
	private static final String VIEW_BOX_ATTRIBUTE = "viewBox";	

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
	
	public static InputStream join(Stream<InputStream> streams) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		streams.forEach(in -> {
			if (in != null) {				
				try (BufferedInputStream bin = new BufferedInputStream(in)) {
					int b;
					while ((b = bin.read()) != -1) {
						baos.write(b);
					}						
				} catch (IOException e) {
					throw new NasdanikaException("Error joining streams: " + e, e);
				}
			}
		});
		baos.close();
		return new ByteArrayInputStream(baos.toByteArray());
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
		public InputStream execute(String text, ProgressMonitor progressMonitor) {
			try {
				return toStream(context, text);
			} catch (IOException e) {
				throw new ExecutionException(e, this);
			}
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
		public InputStream execute(Object object, ProgressMonitor progressMonitor) {
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
		public String execute(InputStream stream, ProgressMonitor progressMonitor) {
			try {
				return Util.toString(context, stream);
			} catch (IOException e) {
				throw new ExecutionException(e, this);
			}
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
		public String execute(String str, ProgressMonitor progressMonitor) {
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
		public String execute(String input, ProgressMonitor progressMonitor) {
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
		public Object execute(Object input, ProgressMonitor progressMonitor) {
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
		public Object execute(String input, ProgressMonitor progressMonitor) {
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
		public Map<?,?> execute(Map<?,?> input, ProgressMonitor progressMonitor) {
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
		public List<?> execute(Collection<?> input, ProgressMonitor progressMonitor) {
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
		public InputStream execute(List<InputStream> content, ProgressMonitor progressMonitor) {
			try {
				return join(content.toArray(new InputStream[content.size()]));
			} catch (IOException e) {
				throw new ExecutionException(e, this);
			}
		}
		
	};
	
	public static FunctionFactory<List<InputStream>, InputStream> JOIN_STREAMS_FACTORY = new FunctionFactory<List<InputStream>, InputStream>() {

		@Override
		public Function<List<InputStream>, InputStream> create(Context context) {
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
		public String execute(Object obj, ProgressMonitor progressMonitor) {
			if (obj == null) {
				return null;
			}
			if (obj instanceof InputStream) {
				try {
					return Util.toString(context, (InputStream) obj);
				} catch (IOException e) {
					throw new ExecutionException(e, this);
				}
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
	 * Extracts first sentence from text
	 * @param context
	 * @param text
	 * @return
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
	 */
	public static String firstPlainTextSentence(String html, int minSentenceLength, int maxSentenceLength, String... abbreviations) {
		if (html == null || html.length() < minSentenceLength) {
			return Jsoup.parse(html).text();
		}
		return firstSentence(Jsoup.parse(html).text(), minSentenceLength, maxSentenceLength, abbreviations);
	}	
	
	

	/**
	 * Executes full supplier lifecycle - diagnose, execute, commit/rollback, close.
	 * @param monitor
	 * @param diagnosticConsumer TODO
	 * @param context
	 * @param component
	 * @deprecated Use {@link Supplier}.call()
	 * @return
	 */
	@Deprecated(since = "2023.4.1", forRemoval = true)
	public static <T> T call(Supplier<T> supplier, ProgressMonitor monitor, Consumer<Diagnostic> diagnosticConsumer, Status... failOnStatuses) {
		return supplier.call(monitor, diagnosticConsumer, failOnStatuses);
	}
	
	/**
	 * Executes full Command lifecycle - diagnose, execute, commit/rollback, close.
	 * @param context
	 * @param monitor
	 * @param component
	 * @param failOnStatuses Status on which to throw {@link DiagnosticException}. Defaults to FAIL.
	 * @return
	 */
	public static Diagnostic call(Command command, ProgressMonitor monitor, Status... failOnStatuses) {
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
	 */
	public static <T> Diagnostic call(org.nasdanika.common.Consumer<T> consumer, T arg,  ProgressMonitor monitor, Status... failOnStatuses) {
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
	 */
	public static <T> SupplierFactory<T> asSupplierFactory(Object obj) {
		return asSupplierFactory(obj, null, null);
	}	

	/**
	 * Wraps object into a supplier factory. Handles adapters, and collections.
	 * @param obj
	 * @return
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
		
		if (obj instanceof EObject) {
			Adapter adapter = EcoreUtil.getRegisteredAdapter((EObject) obj, SupplierFactory.class);
			if (adapter instanceof SupplierFactory) {
				return (SupplierFactory<T>) adapter;
			}
		}
		
		return null;
	}

	/**
	 * Wraps object into an {@link InputStream} supplier factory. Handles adapters, and collection and scalar cases.
	 * @param obj
	 * @return
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
			public String execute(ProgressMonitor progressMonitor) {
				return context.interpolateToString(String.valueOf(obj));
			}
		
		};
		
		return textFactory.then(TO_STREAM);
	}

	/**
	 * Wraps object into an {@link BinaryEntityContainer} consumer factory. Handles adapters and collections.
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> ConsumerFactory<T> asConsumerFactory(Object obj) {
		if (obj instanceof Collection) {
			CollectionCompoundConsumerFactory<T> ret = new CollectionCompoundConsumerFactory<>("Consumer collection");
			for (Object e: (Collection<?>) obj) {
				ret.add(asConsumerFactory(e));
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
		
		if (obj instanceof EObject) {
			Adapter adapter = EcoreUtil.getRegisteredAdapter((EObject) obj, ConsumerFactory.class);
			if (adapter instanceof ConsumerFactory) {
				return (ConsumerFactory<T>) adapter;
			}
		}		
		
		throw new NasdanikaException(obj.getClass() + " cannot be wrapped/adapted to a consumer factory");
	}

	/**
	 * Wraps object into a {@link CommandFactory}. Handles adapters and collections.
	 * @param obj
	 * @return
	 */
	public static CommandFactory asCommandFactory(Object obj) {
		if (obj instanceof Collection) {
			CompoundCommandFactory ret = new CompoundCommandFactory("Command collection");
			for (Object e: (Collection<?>) obj) {
				ret.add(asCommandFactory(e));
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
		if (obj instanceof EObject) {
			Adapter adapter = EcoreUtil.getRegisteredAdapter((EObject) obj, CommandFactory.class);
			if (adapter instanceof CommandFactory) {
				return (CommandFactory) adapter;
			}
		}
				
		throw new NasdanikaException(obj.getClass() + " cannot be wrapped/adapted to a command factory");
	}
	
	/**
	 * Groups and then aggregates
	 * @param <K>
	 * @param <T>
	 * @param <V>
	 * @return
	 */
	public static <K, T, V> List<V> aggregate(
			Collection<T> elements, 
			java.util.function.Function<? super T, ? extends K> classifier,
			BiFunction<? super K, ? super List<? super T>, ? extends V> aggregator) {
		
		java.util.function.Function<Map.Entry<? extends K, ? extends List<T>>, V> mapper = e -> aggregator.apply(e.getKey(), e.getValue());
		return groupBy(elements, classifier)
				.entrySet()
				.stream()
				.map(mapper)
				.toList();
	}
	
	/**
	 * Grouping by with support of null keys.
	 * @param <K>
	 * @param <T>
	 * @param elements
	 * @param keyFeature
	 * @return
	 */
	public static <K, T> Map<K, List<T>> groupBy(
			Collection<? extends T> elements, 
			java.util.function.Function<? super T, ? extends K> classifier) {
		return groupBy(elements, classifier, new LinkedHashMap<>());
	}
	
	/**
	 * Grouping by with support of null keys.
	 * @param <K>
	 * @param <T>
	 * @param elements
	 * @param keyFeature
	 * @return
	 */
	public static <K, T> Map<K, List<T>> groupBy(
			Collection<? extends T> elements, 
			java.util.function.Function<? super T, ? extends K> classifier, 
			Map<K, List<T>> collector) {
		return groupBy(elements, classifier, collector, key -> new ArrayList<>());
	}	
	
	/**
	 * Grouping by with support of null keys.
	 * @param <K>
	 * @param <T>
	 * @param elements
	 * @param keyFeature
	 * @return Collector argument
	 */
	public static <K, T, C extends Collection<T>> Map<K, C> groupBy(
			Collection<? extends T> elements, 
			java.util.function.Function<? super T, ? extends K> classifier, 
			Map<K, C> collector, 
			java.util.function.Function<K,C> collectionFactory) {
		
		for (T e: elements) {
			K k = classifier.apply(e);
			collector.computeIfAbsent(k, collectionFactory).add(e);
		}
		return collector;
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
	 * @throws IOException 
	 */
	public static java.util.function.Function<String, String> createNasdanikaJavadocResolver(File gitDir, ProgressMonitor progressMonitor) throws IOException {
		Map<URL,URL> urls = new HashMap<>();

		URL java11 = new URL("https://docs.oracle.com/en/java/javase/11/docs/api/");
		urls.put(java11, java11);
		
		// Core
		addUrl(urls, new URL("https://docs.nasdanika.org/core/apidocs/"), gitDir, "core/target/site/apidocs");
		
		// HTML
		addUrl(urls, new URL("https://docs.nasdanika.org/html/apidocs/"), gitDir, "html/target/site/apidocs");
		
		// Architecture Framework		
		addUrl(urls, new URL("https://docs.nasdanika.org/architecture-framework/apidocs/"), gitDir, "architecture-framework/target/site/apidocs");
		
		return createJavadocResolver(urls, progressMonitor);
	}
	
	private static void addUrl(Map<URL,URL> urls, URL onlineURL, File gitDir, String offlinePath) throws IOException {
		URL offlineURL = onlineURL;
		if (gitDir != null && gitDir.isDirectory()) {
			File offlineDir = new File(gitDir, offlinePath);
			if (offlineDir.isDirectory()) {
				offlineURL = offlineDir.getCanonicalFile().toURI().toURL();
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

	// --- File utilities ---
	
	/**
	 * Walks the directory passing files and their paths to the listener.
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
	
	/**
	 * Walks the directory passing files and their paths to the visitor.
	 * For directories visits children recursively if visitor returns true 
	 */
	public static void walk(String path, BiPredicate<File,String> visitor, File... files) {
		if (files != null) {
			for (File file: files) {
				String filePath = path == null ? file.getName() : path + "/" + file.getName();
				if (visitor.test(file, filePath) && file.isDirectory()) {
					walk(filePath, visitor, file.listFiles());
				}
			}
		}
	}
	
	public static void copy(File source, File target, boolean cleanTarget, BiConsumer<File,File> listener) throws IOException {
		if (cleanTarget && target.isDirectory()) {
			delete(target.listFiles());
		}
		if (source.isDirectory()) {
			target.mkdirs();
			for (File sc: source.listFiles()) {
				copy(sc, new File(target, sc.getName()), false, listener);
			}
		} else if (source.isFile()) {
			Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);			
			if (listener != null) {
				listener.accept(source, target);
			}
		}
	}
	
	public static void delete(File... files) {
		for (File file: files) {
			if (file.exists()) {
				if (file.isDirectory()) {
					delete(file.listFiles());
				}
				file.delete();
			}
		}
	}
		
	public static void copy(File source, File target, boolean cleanTarget, Predicate<String> cleanPredicate, BiConsumer<File,File> listener) throws IOException {
		if (cleanTarget && target.isDirectory()) {
			delete(null, cleanPredicate, target.listFiles());
		}
		if (source.isDirectory()) {
			target.mkdirs();
			for (File sc: source.listFiles()) {
				copy(sc, new File(target, sc.getName()), false, listener);
			}
		} else if (source.isFile()) {
			Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);			
			if (listener != null) {
				listener.accept(source, target);
			}
		}
	}
	
	public static void delete(String path, Predicate<String> deletePredicate, File... files) {
		for (File file: files) {
			String filePath = path == null ? file.getName() : path + "/" + file.getName();
			if (file.exists() && (deletePredicate == null || deletePredicate.test(filePath))) {
				if (file.isDirectory()) {
					delete(filePath, deletePredicate, file.listFiles());
				}
				file.delete();
			}
		}
	}	
	
	/**
	 * Map of superclass/interface to the shortest distance from the argument class. 
	 * E.g. for List argument Iterable would be mapped to 2 and Collection to 1
	 * @param eClass
	 * @return
	 */
	public static Map<Class<?>, Integer> lineageMap(Class<?> clazz) {
		if (clazz == null) {
			return Collections.emptyMap();
		}		
		Map<Class<?>, Integer> ret = new LinkedHashMap<>();
		ret.put(clazz, 0);
		for (Map.Entry<Class<?>, Integer> scLineageEntry: lineageMap(clazz.getSuperclass()).entrySet()) {
			Integer eDist = ret.get(scLineageEntry.getKey());
			if (eDist == null || eDist >= scLineageEntry.getValue()) {
				ret.put(scLineageEntry.getKey(), scLineageEntry.getValue() + 1);
			}
		}
		
		for (Class<?> i: clazz.getInterfaces()) {
			for (Map.Entry<Class<?>, Integer> scLineageEntry: lineageMap(i).entrySet()) {
				Integer eDist = ret.get(scLineageEntry.getKey());
				if (eDist == null || eDist >= scLineageEntry.getValue()) {
					ret.put(scLineageEntry.getKey(), scLineageEntry.getValue() + 1);
				}
			}
		}
		return ret;
	}
	
	/**
	 * Flattened inheritance hierarchy from the argument class to all of its supertypes.
	 * @param eClass
	 * @return
	 */
	public static List<Class<?>> lineage(Class<?> clazz) {
		if (clazz == null) {
			return Collections.emptyList();
		}
		List<Class<?>> ret = new ArrayList<>();
		ret.add(clazz);
		ret.addAll(lineage(clazz.getSuperclass()));
		for (Class<?> i: clazz.getInterfaces()) {
			ret.addAll(lineage(i));
		}
		return ret.stream().distinct().toList();
	}
	
	// --- For reflection ---
	
	/**
	 * @return A stream of methods. Accessible methods for the introspection level ACCESSIBLE and declared methods from the class and all super classes and implemented interfaces for the introspection level DECLARED.
	 */
	public static Stream<Method> getMethods(Class<?> clazz) {
		if (clazz == null || Object.class.equals(clazz)) {
			return Stream.empty();
		}
		
		return Arrays.stream(clazz.getMethods());
	}
	
	/**
	 * @return A stream of fields. Accessible fields for the introspection level ACCESSIBLE and declared fields from the class and all super classes and implemented interfaces for the introspection level DECLARED.
	 */
	public static Stream<Field> getFields(Class<?> clazz) {
		if (clazz == null ||  Object.class.equals(clazz)) {
			return Stream.empty();
		}
		
		return Arrays.stream(clazz.getFields());
	}
	
	public static Stream<AccessibleObject> getFieldsAndMethods(Class<?> clazz) {
		return Stream.concat(getMethods(clazz), getFields(clazz));
	}		
	
	public static URI createClassURI(Class<?> clazz) {
		return URI.createURI(Util.CLASSPATH_URL_PREFIX + clazz.getName().replace('.', '/'));
	}
	
	interface InvocationListener<T> {
		
		void before(T target, T proxy, Method method, Object[] args);
		
		void after(T target, T proxy, Method method, Object[] args, Object result);
		
		void afterError(T target, T proxy, Method method, Object[] args, Throwable error);		
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T createListeningProxy(T target, InvocationListener<T> listener) {
		Class<? extends Object> targetClass = target.getClass();
		@SuppressWarnings("rawtypes")
		Class[] interfaces = lineage(targetClass).stream().filter(Class::isInterface).toList().toArray(new Class[0]);
		InvocationHandler invocationHandler = (proxy, method, args) -> {
			listener.before(target, (T) proxy, method, args);
			try {
				Object result = method.invoke(target, args);
				listener.after(target, (T) proxy, method, args, result);
				return result;
			} catch (Throwable th) {
				listener.afterError(target, (T) proxy, method, args, th);
				throw th;
			}
		};
		return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces, invocationHandler);
	}
	
	public static <T> T createListeningProxy(T target, java.util.function.Consumer<InvocationRecord<T>> invocationRecordConsumer) {
		return createListeningProxy(target, new InvocationListener<T>() {

			private AtomicLong counter = new AtomicLong();
			
			@Override
			public void before(T target, T proxy, Method method, Object[] args) {
				invocationRecordConsumer.accept(new InvocationRecord<T>(target, proxy, method, args, false, null, null, Thread.currentThread(), new Throwable().getStackTrace(), counter.incrementAndGet(), System.nanoTime()));				
			}

			@Override
			public void after(T target, T proxy, Method method, Object[] args, Object result) {
				invocationRecordConsumer.accept(new InvocationRecord<T>(target, proxy, method, args, true, result, null, Thread.currentThread(), new Throwable().getStackTrace(), counter.incrementAndGet(), System.nanoTime()));				
			}

			@Override
			public void afterError(T target, T proxy, Method method, Object[] args, Throwable error) {
				invocationRecordConsumer.accept(new InvocationRecord<T>(target, proxy, method, args, true, null, error, Thread.currentThread(), new Throwable().getStackTrace(), counter.incrementAndGet(), System.nanoTime()));				
			}
		});				
	}
	
	public static final String DATA_IMAGE_PREFIX = "data:image/";
	public static final String BASE64_SUFFIX = ";base64,";
	public static final String DATA_IMAGE_PNG_BASE64_PREFIX = DATA_IMAGE_PREFIX + "png" + BASE64_SUFFIX;
	public static final String DATA_IMAGE_JPEG_BASE64_PREFIX = DATA_IMAGE_PREFIX + "jpeg" + BASE64_SUFFIX;
	public static final String DATA_IMAGE_SVG_BASE64_PREFIX = DATA_IMAGE_PREFIX + "svg+xml" + BASE64_SUFFIX;

	/**
	 * Scales image with PNG format for raster images.
	 * @param imageDataURL
	 * @param height
	 * @return
	 * @throws IOException
	 */
	public static String scaleImage(String imageDataURL, int height) throws IOException {
		return scaleImage(imageDataURL, height, "png");
	}	
	
	/**
	 * Scales data encoded png to specified height keeping aspect ratio.
	 * @param encodedImage
	 * @param height
	 * @return
	 * @throws IOException 
	 */
	public static String scaleImage(String imageURL, int height, String format) throws IOException {
		if (imageURL.startsWith(DATA_IMAGE_PNG_BASE64_PREFIX)) {
			byte[] imageData = java.util.Base64.getDecoder().decode(imageURL.substring(DATA_IMAGE_PNG_BASE64_PREFIX.length()));
			return scaleImage(new ByteArrayInputStream(imageData), height, format);
		} 
		
		if (imageURL.startsWith(DATA_IMAGE_JPEG_BASE64_PREFIX)) {
			byte[] imageData = java.util.Base64.getDecoder().decode(imageURL.substring(DATA_IMAGE_JPEG_BASE64_PREFIX.length()));
			return scaleImage(new ByteArrayInputStream(imageData), height, format);
		} 
		
		if (imageURL.startsWith(DATA_IMAGE_SVG_BASE64_PREFIX)) {
			byte[] imageData = java.util.Base64.getDecoder().decode(imageURL.substring(DATA_IMAGE_JPEG_BASE64_PREFIX.length()));
			return scaleSVG(new ByteArrayInputStream(imageData), height);
		} 
		
		// Treating as URL
		try (InputStream inputStream = new URL(imageURL).openStream()) {
			if (imageURL.toLowerCase().endsWith(".svg")) {
				return scaleSVG(inputStream, height);				
			}
			
			return scaleImage(inputStream, height, format);
		} 
	}
	
	/**
	 * Scales image, writes it in specified format and encodes as data URL. 
	 * @param encodedImage
	 * @param height Height of the scaled image. -1 if no scaling, just data encoding.
	 * @return
	 * @throws IOException 
	 */
	public static String scaleImage(InputStream inputStream, int height, String format) throws IOException {
		BufferedImage inputImage = ImageIO.read(inputStream);
		Image scaledImage = inputImage;
		if (height > 0) { 
			int inputHeight = inputImage.getHeight();
			int inputWidth = inputImage.getWidth();
			int scaledWidth = (height * inputWidth) / inputHeight;
			scaledImage = inputImage.getScaledInstance(scaledWidth, height, Image.SCALE_SMOOTH);
		}
		
	    BufferedImage scaledBufferedImage = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D bGr = scaledBufferedImage.createGraphics();
	    bGr.drawImage(scaledImage, 0, 0, null);
	    bGr.dispose();			
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ImageOutputStream ios = new MemoryCacheImageOutputStream(baos)) {
			ImageIO.write(scaledBufferedImage, format, ios);
		}
		baos.close();
		
		return DATA_IMAGE_PREFIX + ("JPG".equalsIgnoreCase(format) ? "jpeg" : format) + BASE64_SUFFIX + java.util.Base64.getEncoder().encodeToString(baos.toByteArray());			
	}
	
	public static String scaleSVG(InputStream inputStream, int height) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputStream);
			if (height != -1) {
				Element element = document.getDocumentElement();
				String svgHeightStr = element.getAttribute(HEIGHT_ATTRIBUTE);
				double svgHeight = Double.parseDouble(svgHeightStr);
				
				String svgWidthStr = element.getAttribute(WIDTH_ATTRIBUTE);
				double svgWidth = Double.parseDouble(svgWidthStr);
				
				element.setAttribute(HEIGHT_ATTRIBUTE, String.valueOf(height));
				element.setAttribute(WIDTH_ATTRIBUTE, String.valueOf((svgWidth * height) / svgHeight));		
				
				if (isBlank(element.getAttribute(VIEW_BOX_ATTRIBUTE))) {
					element.setAttribute(VIEW_BOX_ATTRIBUTE, "0 0 " + svgWidthStr + " " + svgHeightStr);
				}
			}
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer();
		    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    
		    DOMSource source = new DOMSource(document);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    try (baos) {
		    	transformer.transform(source, new StreamResult(baos));
		    }
			return DATA_IMAGE_SVG_BASE64_PREFIX + java.util.Base64.getEncoder().encodeToString(baos.toByteArray());
		} catch (TransformerException | ParserConfigurationException | SAXException | IOException e) {
			throw new NasdanikaException("Error scaling SVG: " + e, e);
		}
	}
		
	static Pattern EXPANDER_PATTERN = Pattern.compile("\\$\\{(.+?)\\}");	
	
	/**
	 * Expands tokens in the form of ``${token name|default value}`` to their values.
	 * If a token is not found expansion is not processed.
	 * @param input
	 * @param env
	 * @return
	 */
	public static String interpolate(String input, java.util.function.Function<String, String> tokenSource) {
		if (tokenSource==null || isBlank(input)) {
			return input;
		}
		Matcher matcher = EXPANDER_PATTERN.matcher(input);
		StringBuilder output = new StringBuilder();
		int i = 0;
		while (matcher.find()) {
		    String token = matcher.group();
			Object replacement = tokenSource.apply(token.substring(2, token.length()-1));
		    if (replacement != null) {
			    output.append(input.substring(i, matcher.start())).append(replacement);			    
			    i = matcher.end();
		    }
		}
		output.append(input.substring(i, input.length()));
		return output.toString();
	}

}
