package org.nasdanika.maven;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.requirements.ClassLoaderRequirement;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * Creates a classloader from Maven dependency and loads resource from the classloader.
 * Uses default configuration as explained at https://docs.nasdanika.org/core/maven/index.html#default-configuration
 * URI format: <code>maven://groupId/artifactId/extension/version/path[?classifier=&lt;classifier&gt;</code>
 * <groupId>:<artifactId>[:<extension>[:<classifier>]]:<version>}
 */
public class MavenURIHandler implements URIHandler {
	
	public static final String MAVEN_URI_SCHEME = "maven";
	private CapabilityLoader loader;
	private ProgressMonitor progressMonitor;
	private Map<String, ClassLoader> classLoaders = new ConcurrentHashMap<>();
	
	private static String decode(final String str) {
		return Util.isBlank(str) ? str : URLDecoder.decode(str, StandardCharsets.UTF_8);
	}		
		
	public MavenURIHandler(CapabilityLoader loader, ProgressMonitor progressMonitor) {
		this.loader = loader;
		this.progressMonitor = progressMonitor;
	}
	
	@Override
	public boolean canHandle(URI uri) {
		return MAVEN_URI_SCHEME.equals(uri.scheme());
	}

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		String authority = uri.authority();
		if (Util.isBlank(authority)) {
			throw new IllegalArgumentException("No authority: " + uri);
		}
		String[] segments = uri.segments();
		if (segments.length < 4) {
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
		String coords = authority + ":" + segments[0] + ":" + segments[1];
		String query = uri.query();
		if (!Util.isBlank(query)) {
			Optional<String> classifierOpt = Pattern.compile("&")
			   .splitAsStream(query)
			   .map(s -> Arrays.copyOf(s.split("=", 2), 2))
			   .filter(entry -> "classifier".equals(decode(entry[0])))
			   .map(entry -> decode(entry[1]))
			   .findFirst();
			if (classifierOpt.isPresent()) {
				coords += ":" + classifierOpt.get();
			}			
		}
		coords += ":" + segments[2];
		ClassLoader classLoader = classLoaders.computeIfAbsent(coords, this::createClasLoader);
		StringBuilder path = new StringBuilder();
		for (int i = 3; i < segments.length; ++i) {
			if (!path.isEmpty()) {
				path.append("/");
			}
			path.append(segments[i]);
		}
		return Objects.requireNonNull(classLoader.getResourceAsStream(path.toString()), "Resource '" + path + "' not found in " + coords);
	}
	
	private ClassLoader createClasLoader(String coords) {
		ClassLoaderRequirement classLoaderRequirement = new ClassLoaderRequirement(
				null,
				null,
				null,
				null,
				true,
				new String[] { coords },
				null,
				null,
				null,
				null);
		
		ClassLoader result = loader.loadOne(
				ServiceCapabilityFactory.createRequirement(ClassLoader.class, null, classLoaderRequirement),
				progressMonitor);
		
		return result;
	}

	@Override
	public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(URI uri, Map<?, ?> options) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException {
		return Collections.emptyMap();
	}

	@Override
	public boolean exists(URI uri, Map<?, ?> options) {
		return true; // Need a better way
	}

	@Override
	public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
		return Collections.emptyMap();
	}

	@Override
	public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
		throw new UnsupportedOperationException();		
	}

}
