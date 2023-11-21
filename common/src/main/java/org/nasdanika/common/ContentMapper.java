package org.nasdanika.common;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This class is intended to be used with maps produced by {@link Transformer}. 
 * For example, a map of Drawio elements to semantic elements (say, process activities).
 * In such maps there is a source content hierarchy (e.g. nesting of Drawio elements Document / Page / Layer / Node / ...). 
 * Such nesting might be semantically significant and map to features or operations in the semantic model. 
 * For example, an Activity node inside a Participant node signifies a non-containment reference from Activity to Participant.
 * <P/>
 * This class computes permutations of source element contents with target (semantic) elements (parent and child) features and operations
 * as well as "actions" which may not be feature or operation actions. 
 * It then invokes a method which "wires" the target (semantic) model based on the source model containment hierarchy.  
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
		HashSet<EObject> tracker = new HashSet<>();			
		for (EObject sourceChild: contents(source, tracker)) {
			LinkedList<EObject> path = new LinkedList<>();
			path.add(sourceChild);
			Function<LinkedList<EObject>, Boolean> pathMapper = createPathMapper(source, target, registry, progressMonitor);
			wireContents(path, pathMapper, tracker);
		}			
	}	
	
	/**
	 * Recursive wiring of source contents 
	 * 
	 * @param path containment path with the first element being the immediate child of the source element 
	 * @param consumer Consumes descendant source path, returns true if traversal shall continue
	 * @param tracker Tracker to avoid infinite loops
	 */
	protected void wireContents(LinkedList<EObject> path, Function<LinkedList<EObject>,Boolean> pathMapper, Collection<EObject> tracker) {
		if (pathMapper.apply(path)) {
			for (EObject child: contents(path.getLast(), tracker)) {
				LinkedList<EObject> subPath = new LinkedList<>(path);
				subPath.add(child);
				wireContents(subPath, pathMapper, tracker);
			}			
		}
	}
	
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
	 * Creates a function which maps a given containment path. 
	 * If the mapper returns true then traversal down the contents tree continues.
	 * This implementation calls createParentMappers() and createChildMappers() and then iterates over the returned mappers.
	 * @param source
	 * @param registry
	 * @return
	 */
	protected Function<LinkedList<EObject>,Boolean> createPathMapper(S source, T target, Map<S,T> registry, ProgressMonitor progressMonitor) {
		return path -> {
			T contentsTarget = registry.get(path.getLast());
			if (contentsTarget == null && target == null) {
				return true; 
			}
			
			// Container (parent) features capable of accepting the child
			List<EStructuralFeature> containerFeatures = target == null ? Collections.emptyList() : target.eClass().getEAllStructuralFeatures();
			List<EStructuralFeature> contentsFeatures = contentsTarget == null ? Collections.emptyList() : contentsTarget.eClass().getEAllStructuralFeatures();

			boolean result = containerFeatures.isEmpty() && contentsFeatures.isEmpty();
			
			for (EStructuralFeature containerFeature: containerFeatures) {
				if (wireContainerFeature(source, target, containerFeature, contentsTarget, path, registry, progressMonitor)) {
					result = true;
				}
			}
			
			for (EStructuralFeature contentsFeature: contentsFeatures) {
				if (wireContentsFeature(source, target, contentsTarget, contentsFeature, path, registry, progressMonitor)) {
					result = true;
				}
			}
			
			return result;
		};
	}
	
	/**
	 * Possibly wires a {@link EStructuralFeature} of the container's semantic target to the contentsTarget.
	 * For example sets target's reference to the contentsTarget or sets target's attribute value to the label of the contents. 
	 * Returns true to go down the contents hierarchy.
	 * @param source
	 * @param target
	 * @param eReference
	 * @param contentsTarget
	 * @param sourcePath
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	protected abstract boolean wireContainerFeature(
			S source,
			T target,
			EStructuralFeature targetFeature,
			T contentsTarget,
			LinkedList<EObject> sourcePath,
			Map<S, T> registry,
			ProgressMonitor progressMonitor);
	
	
	/**
	 * Possibly wires a {@link EStructuralFeature} of the semantic target (contentsTarget) of the last element of the containment path
	 * to the target - the semantic element of the container.
	 * For example, sets contentsdTarget's reference to the target or sets contentsTarget's attribute to the value of the source label.
	 * Returns true to go down the contents hierarchy.
	 * @param source
	 * @param target
	 * @param eReference
	 * @param contentsTarget
	 * @param sourcePath
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	protected abstract boolean wireContentsFeature(
			S source,
			T target,
			T contentsTarget,
			EStructuralFeature contentsTargetFeature,
			LinkedList<EObject> sourcePath,
			Map<S, T> registry,
			ProgressMonitor progressMonitor);

}
