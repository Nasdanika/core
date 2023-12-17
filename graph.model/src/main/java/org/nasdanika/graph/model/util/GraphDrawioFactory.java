package org.nasdanika.graph.model.util;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.drawio.model.Document;
import org.nasdanika.exec.util.DocLoadingDrawioFactory;
import org.nasdanika.graph.model.Graph;
import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelPackage;
import org.nasdanika.ncore.NcorePackage;

/**
 * Factory for mapping drawio model to graph model
 * @param <G>
 * @param <E>
 */
public class GraphDrawioFactory<E extends EObject> extends DocLoadingDrawioFactory<E> {
	
	/**
	 * Returns a map with graph and ncore entries.
	 */
	@Override
	protected Map<String, EPackage> getEPackages() {
		Map<String, EPackage> ret = new LinkedHashMap<>();
		ret.put("graph", ModelPackage.eINSTANCE);
		ret.put("ncore", NcorePackage.eINSTANCE);
		return ret;
	}

	@Override
	protected E getByRefId(String refId, int pass, Map<EObject, EObject> registry) {
		String[] segments = refId.split("/");
		if (segments.length > pass) {
			return null; // Might not have been resolved yet
		}
		Optional<EObject> deo = registry
			.entrySet()
			.stream()
			.filter(re -> re.getKey() instanceof Document)
			.map(re -> re.getValue())
			.findFirst();
		
		if (deo.isPresent()) {
			@SuppressWarnings("unchecked")
			E graph = (E) deo.get();
			return find((Graph<?>) graph, segments, 0);
		}
			
		throw new IllegalArgumentException("Document graph not found in the registry");
	}
	
	@SuppressWarnings("unchecked")
	private E find(Graph<?> graph, String[] path, int offset) {
		for (GraphElement graphElement: graph.getElements()) {
			if (path[offset].equals(graphElement.getId())) {
				if (offset == path.length - 1) {
					return (E) graphElement;
				}
				if (graphElement instanceof Graph) {
					return find((Graph<?>) graphElement, path, offset + 1);
				}
				throw new IllegalArgumentException("Element not found at path " + Arrays.toString(path) + " " + graphElement + " is not an instance of Graph at offset " + offset);				
			}
		}
		throw new IllegalArgumentException("Element not found at path " + Arrays.toString(path));
	}
		
}
