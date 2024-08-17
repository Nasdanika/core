package org.nasdanika.graph.model.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.emf.SpecLoadingDrawioFactory;
import org.nasdanika.graph.model.ModelPackage;
import org.nasdanika.ncore.NcorePackage;

/**
 * Factory for mapping drawio model to graph model
 * @param <G>
 * @param <E>
 */
public abstract class GraphDrawioFactory<E extends EObject> extends SpecLoadingDrawioFactory<E> {
	
	public GraphDrawioFactory(ResourceSet resourceSet) {
		super(resourceSet);
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
		
}
