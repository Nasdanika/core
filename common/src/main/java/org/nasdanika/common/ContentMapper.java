package org.nasdanika.common;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * This class is intended to be used with maps produced by {@link Transformer}. 
 * For example, a map of Drawio elements to semantic elements (say process activities).
 * In such maps there is a source content hierarchy (e.g. nesting of Drawio elements Document / Page / Layer / Node / ...). 
 * Such nesting might be semantically significant and map to non-containment relationships in the semantic model. 
 * For example, an Activity node inside a Participant node signified a non-containment reference from Activity to Participant.
 * Also, there might be nodes/containers representing specific {@link EReference}s.
 * <P/>
 * This class computes permutations of source element contents with target (semantic) element references and invokes a method which 
 * "wires" the target (semantic) model based on the source model containment hierarchy.  
 * @param <S>
 * @param <T>
 */
public abstract class ContentMapper<S extends EObject, T extends EObject> {
	
	/**
	 * Wires all elements in the registry
	 * @param registry
	 * @param progressMonitor
	 */
	public void wire(Map<S,T> registry, ProgressMonitor progressMonitor) {
		for (S key: registry.keySet()) {
			wire(key, registry, progressMonitor);
		}
	}
	
	public void wire(S source, Map<S,T> registry, ProgressMonitor progressMonitor) {
		T target = registry.get(source);
		if (target != null) {
			EList<EReference> allReferences = target.eClass().getEAllContainments();
			HashSet<EObject> tracker = new HashSet<>();
			
			for (EObject sourceChild: contents(source, tracker)) {
				LinkedList<EObject> path = new LinkedList<>();
				path.add(sourceChild);
				Function<LinkedList<EObject>, Boolean> consumer = cPath -> {				
					return wireReferences(
							source, 
							target, 
							allReferences, 
							cPath, 
							registry, 
							progressMonitor);
				};
				
				wireContents(path, consumer, tracker);
			}
			
		}
	}	

	/**
	 * 
	 * @param source
	 * @param target
	 * @param references
	 * @param sourcePath
	 * @param registry
	 * @param progressMonitor
	 * @return true to go down the contents hierarchy, false if contents of the last source path element shall not be wired.
	 */
	protected boolean wireReferences(
		S source,
		T target,
		List<EReference> references,
		LinkedList<EObject> sourcePath,
		Map<S, T> registry,
		ProgressMonitor progressMonitor) {
		
		T childTarget = registry.get(sourcePath.getLast());
		if (childTarget == null) {
			return true; 
		}
				
		boolean result = references.isEmpty();
		for (EReference eReference: references) {
			if (wireReference(
					source,
					target,
					eReference,
					childTarget,
					sourcePath,
					registry,
					progressMonitor)) {
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * Possibly wires a reference. Returns true to go down the contents hierarchy.
	 * @param source
	 * @param target
	 * @param eReference
	 * @param childTarget
	 * @param sourcePath
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	protected abstract boolean wireReference(
			S source,
			T target,
			EReference eReference,
			T childTarget,
			LinkedList<EObject> sourcePath,
			Map<S, T> registry,
			ProgressMonitor progressMonitor);
	
	/**
	 * This method returns eObject.eContents(); Override to take into account object references, e.g. page links in Drawio.  
	 * @param eObject
	 * @param tracker prevents infinite loops in case of circular references
	 * @return Tree iterator which is aware of page links and fails on double-visits (circular references)
	 */
	protected List<EObject> contents(EObject eObject, Collection<EObject> tracker) {
    	return eObject.eContents();
	}	
	
	/**
	 * Recursive wiring of source contents 
	 * 
	 * @param path containment path with the first element being the immediate child of the source element 
	 * @param consumer Consumes descendant source path, returns true if traversal shall continue
	 * @param tracker Tracker to avoid infinite loops
	 */
	protected void wireContents(LinkedList<EObject> path, Function<LinkedList<EObject>,Boolean> consumer, Collection<EObject> tracker) {
		if (consumer.apply(path)) {
			for (EObject child: contents(path.getLast(), tracker)) {
				LinkedList<EObject> subPath = new LinkedList<>(path);
				subPath.add(child);
				wireContents(subPath, consumer, tracker);
			}			
		}
	}
	

}
