package org.nasdanika.groovy;

import javax.script.CompiledScript;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.capability.emf.ResourceContentsHandler;

/**
 * Transform handler for the {@code .pm} qualifier over a {@code .groovy} source. It resolves the
 * upstream source handler to obtain a {@link CompiledSource}, builds and installs the Product
 * Management Groovy DSL helpers per call, evaluates the compiled script, resolves deferred
 * id-based references, then normalizes the result to {@code EObject[]}. Mirrors
 * {@code MarkdownToProductManagementResourceContentsHandler}.
 */
public class EcoreResourceContentsHandler extends DslResourceContentsHandler {

	public EcoreResourceContentsHandler(Resource resource, ResourceContentsHandler<CompiledScript> sourceHandler) {
		super(resource, sourceHandler, EcorePackage.eINSTANCE);
	}

}
