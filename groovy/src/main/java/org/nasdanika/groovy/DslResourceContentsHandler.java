package org.nasdanika.groovy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.emf.ResourceContentsHandler;
import org.nasdanika.groovy.dsl.DslContext;
import org.nasdanika.groovy.dsl.DslContext.Resolver;
import org.nasdanika.groovy.dsl.DslException;

/**
 * Transform handler for the {@code .pm} qualifier over a {@code .groovy} source. It resolves the
 * upstream source handler to obtain a {@link CompiledSource}, builds and installs the Product
 * Management Groovy DSL helpers per call, evaluates the compiled script, resolves deferred
 * id-based references, then normalizes the result to {@code EObject[]}. Mirrors
 * {@code MarkdownToProductManagementResourceContentsHandler}.
 */
public class DslResourceContentsHandler implements ResourceContentsHandler<EObject[]> {

	private ResourceContentsHandler<CompiledScript> sourceHandler;

	private Resource resource;

	private Resolver resolver;

	/**
	 * The {@link DslContext} from the most recent {@link #load(InputStream, Map)}. Retained so that
	 * {@link #save(EObject[], OutputStream, Map)} can delegate to an {@code onSave} callback the script
	 * registered during load. This is safe because a single handler instance serves both load and save
	 * for the lifetime of a resource (see {@code ContentsHandlingResource}).
	 */
	private DslContext dslContext;

	/**
	 * The original script source text captured during {@link #load(InputStream, Map)}. Handed to the
	 * {@code onSave} callback so a self-writing script can write it back verbatim or emit a modified
	 * version.
	 */
	private String source;

	public DslResourceContentsHandler(
			Resource resource, 
			ResourceContentsHandler<CompiledScript> sourceHandler,
			Resolver resolver) {
		this.resource = resource;
		this.sourceHandler = sourceHandler;
		this.resolver = resolver;
	}

	public DslResourceContentsHandler(
			Resource resource, 
			ResourceContentsHandler<CompiledScript> sourceHandler,
			EPackage... ePackages) {
		this.resource = resource;
		this.sourceHandler = sourceHandler;
		this.resolver = new EPackageResolver(ePackages) {
			
			@Override
			protected ResourceSet getResourceSet() {
				return resource.getResourceSet();
			}

			@Override
			public Map<String, Object> bindings() {
				return DslResourceContentsHandler.this.bindings();
			}

		};
	}

    protected Map<String, Object> bindings() {
        return Collections.emptyMap();
    }

	@Override
	public EObject[] load(InputStream inputStream, Map<?, ?> options) throws IOException {
		// Buffer the source so it can be handed to an onSave callback later (the source handler consumes
		// the stream to compile and does not retain the text).
		byte[] sourceBytes = inputStream.readAllBytes();
		this.source = new String(sourceBytes, StandardCharsets.UTF_8);
		CompiledScript compiledScript = sourceHandler.load(new ByteArrayInputStream(sourceBytes), options);

		dslContext = new DslContext(resolver, resource);
		Bindings bindings = compiledScript.getEngine().createBindings();
		dslContext.installInto(bindings);

		Object result;
		try {
			result = compiledScript.eval(bindings);
			dslContext.resolveDeferred();
		} catch (ScriptException | RuntimeException e) {
			// Tag the failure with its source location (URI/line) before reporting it.
			DslException located = dslContext.located(e);
			throw new IOException(located.getMessage(), located);
		}

		return normalize(result, dslContext);
	}
	
	/**
	 * Normalizes the script result into resource contents. A returned {@code EObject}, array or
	 * iterable of {@code EObject}s is flattened; if the script returned nothing usable, the elements
	 * built through the DSL entry points (e.g. {@code product { }}) are used instead.
	 */
	private EObject[] normalize(Object result, DslContext dsl) {
		List<EObject> contents = new ArrayList<>();
		collect(result, contents);
		if (contents.isEmpty()) {
			contents.addAll(dsl.getRoots());
		}
		return contents.toArray(EObject[]::new);
	}

	private void collect(Object value, List<EObject> contents) {
		if (value instanceof EObject eObject) {
			contents.add(eObject);
		} else if (value instanceof Object[] array) {
			for (Object element : array) {
				collect(element, contents);
			}
		} else if (value instanceof Iterable<?> iterable) {
			for (Object element : iterable) {
				collect(element, contents);
			}
		}
	}

	/**
	 * Delegates to the {@code onSave} callback registered by the script during {@link #load(InputStream, Map)},
	 * handing it the original script source so a self-writing script can write it back verbatim or emit a
	 * modified version. If the script registered no callback - the read-only case - throws
	 * {@link UnsupportedOperationException}, matching the default {@link ResourceContentsHandler#save}
	 * behaviour. The {@code contents} argument is ignored: the DSL script, not the in-memory model, is the
	 * source of truth for what gets written.
	 */
	@Override
	public void save(EObject[] contents, OutputStream outputStream, Map<?, ?> options) throws IOException {
		if (dslContext == null || !dslContext.isSaveSupported()) {
			throw new UnsupportedOperationException("Save operation is not supported");
		}
		try {
			dslContext.save(source, outputStream, options);
		} catch (RuntimeException e) {
			DslException located = dslContext.located(e);
			throw new IOException(located.getMessage(), located);
		}
	}

	@Override
	public Order getOrder() {
		return sourceHandler.getOrder().add(0);
	}

}
