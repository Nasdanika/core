package org.nasdanika.graph.emf;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.graph.Element;

/**
 * {@link ResourceSet} element. Resolves cross-references between {@link EObjectNode}s.
 * @author Pavel
 *
 */
public class ResourceSetElement implements Element {
	
	private ResourceSet resourceSet;	
	private List<ResourceElement> resourceElements = new ArrayList<>();

	public ResourceSetElement(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		for (Resource resource: resourceSet.getResources()) {
			resourceElements.add(new ResourceElement(resource, this::createNode));
		}
		
		Function<EObject, EObjectNode> registry = accept(Util::buildRegistry)::get;
		accept(e -> resolve(e, registry));
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
	
	protected EReferenceConnection createConnection(EObjectNode source, EObjectNode target, EReference reference, int index) {
		return new EReferenceConnection(source, target, reference, index, path(source, target, reference, index));
	}
	
	protected String path(EObjectNode source, EObjectNode target, EReference reference, int index) {
		return Util.path(source, target, reference, index, this::eKeyToPathSegment);
	}
	
	protected Object eKeyToPathSegment(EAttribute keyAttribute, Object keyValue) {
		return keyValue;
	}	

}


//if (reference.isMany()) {
//EList<EAttribute> eKeys = reference.getEKeys();
//if (eKeys.isEmpty()) {
//	path = String.valueOf(index);
//} else {
//	path = eKeyPath(target.getTarget(), eKeys, String.valueOf(index));
//}
//}
//	
//protected String eKeyPath(EObject eObj, EList<EAttribute> eKeys, String position) {
//StringBuilder pathBuilder = new StringBuilder();
//for (EAttribute eKey: eKeys) {
//	Object value = eObj.eGet(eKey);
//	if (value == null || (value instanceof String && Util.isBlank((String) value))) {
//		return pathBuilder.length() == 0 ? position : pathBuilder.toString();
//	}
//	if (pathBuilder.length() > 0) {
//		pathBuilder.append("/");
//	}
//	pathBuilder.append(eKeyToPathSegment(value));
//}
//return pathBuilder.toString();		
//}
//
//protected Object eKeyToPathSegment(Object eKey) {
//return eKey;
//}
