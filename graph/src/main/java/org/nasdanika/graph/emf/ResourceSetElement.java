package org.nasdanika.graph.emf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.graph.Element;

public class ResourceSetElement implements Element {
	
	private ResourceSet resourceSet;	
	private List<ResourceElement> resourceElements = new ArrayList<>();

	public ResourceSetElement(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		for (Resource resource: resourceSet.getResources()) {
			resourceElements.add(new ResourceElement(resource, this::createNode));
		}
		
		Function<EObject, EObjectNode> registry = accept(this::buildRegistry)::get;
		accept(e -> resolve(e, registry));
	}

	private Map<EObject, EObjectNode> buildRegistry(Element element, Map<? extends Element, Map<EObject, EObjectNode>> childRegistries) {
		Map<EObject, EObjectNode> result = new HashMap<>();
		if (element instanceof EObjectNode) {
			result.put(((EObjectNode) element).getTarget(), (EObjectNode) element);
		}
		for (Map<EObject, EObjectNode> cr: childRegistries.values()) {
			result.putAll(cr);
		}
		return result;
	}
	
	private void resolve(Element element, Function<EObject, EObjectNode> registry) {
		if (element instanceof EObjectNode) {
			((EObjectNode) element).resolve(registry, this::createConnection);
		}
	}
	
	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		Map<ResourceElement, T> results = new LinkedHashMap<>();
		for (ResourceElement resourceElement: resourceElements) {
			results.put(resourceElement, resourceElement.accept(visitor));
		}
		return visitor.apply(this, results);
	}
	
	protected EObjectNode createNode(EObject eObject) {
		return new EObjectNode(eObject, this::createNode, this::createConnection);
	}
	
	protected EReferenceConnection createConnection(EObjectNode source, EObjectNode taret, EReference reference, int index) {
		return new EReferenceConnection(source, taret, reference, index);
	}

}
