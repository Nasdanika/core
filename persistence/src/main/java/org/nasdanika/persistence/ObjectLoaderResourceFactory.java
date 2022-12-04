package org.nasdanika.persistence;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;

/**
 * Creates {@link YamlResource}
 * @author Pavel
 *
 */
public abstract class ObjectLoaderResourceFactory extends ResourceFactoryImpl {
	
	@Override
	public Resource createResource(URI uri) {
		return new ObjectLoaderResource(uri) {

			@Override
			protected ObjectLoader getObjectLoader() {
				return ObjectLoaderResourceFactory.this.getObjectLoader(this);
			}

			@Override
			protected ProgressMonitor getProgressMonitor() {
				return ObjectLoaderResourceFactory.this.getProgressMonitor(this);
			}

			@Override
			protected Context getContext() {
				return ObjectLoaderResourceFactory.this.getContext(this);
			}

			@Override
			protected void onDiagnostic(org.nasdanika.common.Diagnostic diagnostic) {
				ObjectLoaderResourceFactory.this.onDiagnostic(getURI(), diagnostic);				
			}

			@Override
			protected boolean isJsonObject() {
				return ObjectLoaderResourceFactory.this.isJsonObject(this);
			}

			@Override
			protected boolean isJsonArray() {
				return ObjectLoaderResourceFactory.this.isJsonArray(this);
			}

			@Override
			protected Storable toStorable(EObject eObj) {
				return ObjectLoaderResourceFactory.this.toStorable(this, eObj);
			}
			
		};
	}
	
	protected boolean isJsonObject(Resource resource) {
		URI uri = resource.getURI();
		if (uri != null) {
			String lastSegment = uri.lastSegment();
			return lastSegment != null && lastSegment.toLowerCase().endsWith(".json");
		}
		return false;
	}

	protected boolean isJsonArray(Resource resource) {
		return false;
	}

	protected Storable toStorable(Resource resource, EObject eObj) {
		return (Storable) EcoreUtil.getRegisteredAdapter(eObj, Storable.class);
	}
	
	
	protected abstract ObjectLoader getObjectLoader(Resource resource);
	
	protected ProgressMonitor getProgressMonitor(Resource resource) {
		return new NullProgressMonitor();
	}
	
	protected Context getContext(Resource resource) {
		return Context.EMPTY_CONTEXT;
	}
		
	/**
	 * Called by the supplier factory.
	 * @param diagnostic
	 */
	protected void onDiagnostic(URI uri, org.nasdanika.common.Diagnostic diagnostic) {
		if (diagnostic.getStatus() == Status.FAIL || diagnostic.getStatus() == Status.ERROR) {
			System.err.println("***********************");
			System.err.println("*      Diagnostic     *");
			System.err.println("***********************");
			diagnostic.dump(System.err, 4, Status.FAIL, Status.ERROR);
		}
		if (diagnostic.getStatus() != Status.SUCCESS) {
			throw new DiagnosticException(diagnostic);
		};
	}
	
	/**
	 * Override to customize resource context, e.g. add uri-based properties or services.
	 * This imple
	 * @param uri
	 * @return
	 */
	protected Context resourceContext(Resource resource) {
		MutableContext ret = getContext(resource).fork();

		Function<String, URL> resolver = path -> {
			try {
				return new URL(new URL(resource.getURI().toString()), path);
			} catch (MalformedURLException e) {
				throw new NasdanikaException("Cannot resolve '" + path + "' relative to " + resource.getURI() + ": " + e, e);
			}
		};
		
		ret.put("embedded-image", org.nasdanika.common.Util.createEmbeddedImagePropertyComputer(resolver));
		ret.put("embedded-image-data", org.nasdanika.common.Util.createEmbeddedImageDataPropertyComputer(resolver));
		ret.put("include", org.nasdanika.common.Util.createIncludePropertyComputer(resolver));
		ret.put("include-markdown", org.nasdanika.common.Util.createIncludeMarkdownPropertyComputer(resolver));
		
		return ret;
	}	
	
}
