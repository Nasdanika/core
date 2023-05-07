package org.nasdanika.graph.emf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Reference;
import org.nasdanika.graph.Element;

/**
 * A container of elements. Resolves cross-references between {@link EObjectNode}s.
 * @author Pavel
 *
 */
public final class Util {
	
	public interface ReferencePathComputer {
		
		String path(EObjectNode source, EObjectNode target, EReference reference, int index);
	}
	
	private Util() {
		// Singleton
	}
	
	public static List<EObjectNode> load(Iterable<? extends EObject> elements) {		 
		return load(elements, (source, target, reference, index) -> path(source, target, reference, index, null));
	}
	
	public static List<EObjectNode> load(Iterable<? extends EObject> elements, ReferencePathComputer referencePathComputer) {
		EReferenceConnection.Factory connectionFactory = (source, target, reference, index) -> new EReferenceConnection(
				source, 
				target, 
				reference, 
				index, 
				referencePathComputer == null ? path(source, target, reference, index, null) : referencePathComputer.path(source, target, reference, index));
		Reference<Function<EObject,EObjectNode>> nodeFactory = new Reference<>();
		nodeFactory.set(target -> new EObjectNode(target, nodeFactory.get(), connectionFactory));		
		return load(elements, nodeFactory.get(), connectionFactory);
	}
	
	public static List<EObjectNode> load(
			Iterable<? extends EObject> elements,
			Function<EObject,EObjectNode> nodeFactory,
			EReferenceConnection.Factory connectionFactory) {
		List<EObjectNode> contents = new ArrayList<>();
		Map<EObject, EObjectNode> registry = new HashMap<>();
		for (EObject element: elements) {
			EObjectNode node = nodeFactory.apply(element);
			contents.add(node);
			registry.putAll(node.accept(Util::buildRegistry));					
		}
		
		for (EObjectNode node: contents) {
			node.accept(e -> {
				if (e instanceof EObjectNode) {
					((EObjectNode) e).resolve(registry::get, connectionFactory);
				}
			});
		}
		
		return contents;
	}
	
	/**
	 * Traverses elements and collects mapping from {@link EObject}s to {@link EObjectNode}s. 
	 * @param element
	 * @param childRegistries
	 * @return
	 */
	public static Map<EObject, EObjectNode> buildRegistry(Element element, Map<? extends Element, Map<EObject, EObjectNode>> childRegistries) {
		Map<EObject, EObjectNode> result = new HashMap<>();
		if (element instanceof EObjectNode) {
			result.put(((EObjectNode) element).getTarget(), (EObjectNode) element);
		}
		for (Map<EObject, EObjectNode> cr: childRegistries.values()) {
			result.putAll(cr);
		}
		return result;
	}
		
	public static String path(EObjectNode source, EObjectNode target, EReference reference, int index, BiFunction<EAttribute, Object, Object> eKeyToPathSegment) {
		String position = String.valueOf(index);
		if (reference.isMany()) {
			EList<EAttribute> eKeys = reference.getEKeys();
			if (eKeys.isEmpty()) {
				return position;
			} else {
				StringBuilder pathBuilder = new StringBuilder();
				for (EAttribute eKey: eKeys) {
					Object value = eKeyToPathSegment == null ? target.getTarget().eGet(eKey) : eKeyToPathSegment.apply(eKey, target.getTarget().eGet(eKey));
					if (value == null || (value instanceof String && org.nasdanika.common.Util.isBlank((String) value))) {
						return pathBuilder.length() == 0 ? position : pathBuilder.toString();
					}
					if (pathBuilder.length() > 0) {
						pathBuilder.append("/");
					}
					pathBuilder.append(value);
				}
				return pathBuilder.toString();		
			}
		}
		return null;
	}

}
