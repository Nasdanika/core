package org.nasdanika.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;

public class ClassLoaderURIHandler extends URIHandlerImpl {
	
	public interface ResourceLoader {
		
		InputStream loadResource(String name) throws IOException;
		
	}	

	private ResourceLoader resourceLoader;

	public ClassLoaderURIHandler(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public boolean canHandle(URI uri) {
		return uri != null && Util.CLASSPATH_SCHEME.equals(uri.scheme());
	}

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		if (Util.CLASSPATH_SCHEME.equals(uri.scheme())) {
			String resource = uri.toString().substring(Util.CLASSPATH_URL_PREFIX.length());
			return Objects.requireNonNull(resourceLoader.loadResource(resource), "Loader resource not found: " + resource);
		}
		throw new IllegalArgumentException("Unsupported URI: " + uri);
	}
	
}
