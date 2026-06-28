package org.nasdanika.groovy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
			
		};
	}

	@Override
	public EObject[] load(InputStream inputStream, Map<?, ?> options) throws IOException {
		CompiledScript compiledScript = sourceHandler.load(inputStream, options);

		DslContext dsl = new DslContext(resolver, resource);
		Bindings bindings = compiledScript.getEngine().createBindings();
		dsl.installInto(bindings);

		Object result;
		try {
			result = compiledScript.eval(bindings);
			dsl.resolveDeferred();
		} catch (ScriptException | RuntimeException e) {
			// Tag the failure with its source location (URI/line) before reporting it.
			DslException located = dsl.located(e);
			throw new IOException(located.getMessage(), located);
		}

		return normalize(result, dsl);
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

	@Override
	public Order getOrder() {
		return sourceHandler.getOrder().add(0);
	}

}
