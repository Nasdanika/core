package org.nasdanika.graph.model.util;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.ContentMapper;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.model.Document;
import org.nasdanika.drawio.model.util.AbstractDrawioFactory;
import org.nasdanika.graph.model.DocumentedNamedGraph;
import org.nasdanika.graph.model.Graph;
import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelFactory;
import org.nasdanika.graph.model.ModelPackage;
import org.nasdanika.ncore.NcorePackage;

/**
 * Factory for mapping drawio model to graph model
 * @param <G>
 * @param <E>
 */
public class GraphDrawioFactory<G extends Graph<E>, E extends GraphElement> extends AbstractDrawioFactory<G, E> {

	public GraphDrawioFactory(
			ContentMapper<EObject, EObject> containmentContentMapper,
			ContentMapper<EObject, EObject> nonContainmentContentMapper) {
		super(containmentContentMapper, nonContainmentContentMapper);
	}

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

	/**
	 * Creates an instance of {@link DocumentedNamedGraph}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected G createDocumentElement(
			Document document,
			BiConsumer<EObject, BiConsumer<EObject, ProgressMonitor>> elementProvider,
			Consumer<BiConsumer<Map<EObject, EObject>, ProgressMonitor>> registry, 
			ProgressMonitor progressMonitor) {
		return (G) ModelFactory.eINSTANCE.createDocumentedNamedGraph();
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
			G graph = (G) deo.get();
			return find(graph, segments, 0);
		}
			
		throw new IllegalArgumentException("Document graph not found in the registry");
	}
	
	@SuppressWarnings("unchecked")
	private E find(Graph<E> graph, String[] path, int offset) {
		for (GraphElement graphElement: graph.getElements()) {
			if (path[offset].equals(graphElement.getId())) {
				if (offset == path.length - 1) {
					return (E) graphElement;
				}
				if (graphElement instanceof Graph) {
					return find((Graph<E>) graphElement, path, offset + 1);
				}
				throw new IllegalArgumentException("Element not found at path " + Arrays.toString(path) + " " + graphElement + " is not an instance of Graph at offset " + offset);				
			}
		}
		throw new IllegalArgumentException("Element not found at path " + Arrays.toString(path));
	}

}
