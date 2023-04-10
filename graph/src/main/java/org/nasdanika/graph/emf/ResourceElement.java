package org.nasdanika.graph.emf;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.graph.Element;

public class ResourceElement implements Element {
	
	private Resource resource;
	private List<EObjectNode> contents = new ArrayList<>();

	public ResourceElement(Resource resource) {
		this.resource = resource;
		
	}
	
	public Resource getResource() {
		return resource;
	}

	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		Map<EObjectNode, T> results = new LinkedHashMap<>();
		for (EObjectNode eObjectElement: contents) {
			results.put(eObjectElement, eObjectElement.accept(visitor));
		}
		return visitor.apply(this, results);
	}

}
