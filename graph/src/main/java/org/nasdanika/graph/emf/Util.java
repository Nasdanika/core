package org.nasdanika.graph.emf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Reference;
import org.nasdanika.graph.Element;

/**
 * A container of elements. Resolves cross-references between {@link EObjectNode}s.
 * @author Pavel
 *
 */
public final class Util {
	
	private Util() {
		// Singleton
	}
	
	public static List<EObjectNode> load(Iterable<? extends EObject> elements) {
		EReferenceConnection.Factory connectionFactory = EReferenceConnection::new;
		Reference<Function<EObject,EObjectNode>> nodeFactory = new Reference<>();
		nodeFactory.set(target -> new EObjectNode(target, nodeFactory.get(), connectionFactory));		
		return load(elements, nodeFactory.get(), EReferenceConnection::new);
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
	

}
