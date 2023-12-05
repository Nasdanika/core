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
 * 
 * Similarly, it maps features of connections or associations - objects connecting other objects.
 * @param <S>
 * @param <T>
 */
public abstract class FeatureMapper<S extends EObject, T extends EObject> implements Mapper<S,T> {

	private Mapper<S, T> chain;

	protected FeatureMapper() {}
	
	protected FeatureMapper(Mapper<S,T> chain) {
		this.chain = chain;
	}
	
	protected boolean isPassThrough(S connection, T connectionValue) {
		return connectionValue == null;
	}
	
	/**
	 * Selects zero or more target (semantic) elements for the source element. This implementation returns registry value.
	 * Override to support, for example, mapping to multiple semantic values. 
	 * @param source
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	protected Iterable<T> select(S source, Map<S,T> registry, ProgressMonitor progressMonitor) {
		return Collections.singleton(registry.get(source));
	}
	
	@Override
	public void wire(S source, Map<S,T> registry, ProgressMonitor progressMonitor) {
		for (T value: select(source, registry, progressMonitor)) {
			HashSet<EObject> tracker = new HashSet<>();			
			for (EObject contents: contents(source, tracker)) {
				LinkedList<EObject> path = new LinkedList<>();
				path.add(contents);
				Function<LinkedList<EObject>, Boolean> pathMapper = createPathMapper(source, value, registry, progressMonitor);
				wireContents(path, pathMapper, tracker);
			}
			
			List<EStructuralFeature> valueFeatures = value == null ? Collections.emptyList() : value.eClass().getEAllStructuralFeatures();		
			
			S connectionSource = getConnectionSource(source);
			for (T connectionSourceValue: connectionSource == null ? Collections.<T>singleton(null) : select(connectionSource, registry, progressMonitor)) {		
				List<EStructuralFeature> connectionSourceValueFeatures = connectionSourceValue == null ? Collections.emptyList() : connectionSourceValue.eClass().getEAllStructuralFeatures();
						
				S connectionTarget = getConnectionTarget(source);
				for (T connectionTargetValue: connectionTarget == null ? Collections.<T>singleton(null) : select(connectionTarget, registry, progressMonitor)) {		
					List<EStructuralFeature> connectionTargetValueFeatures = connectionTargetValue == null ? Collections.emptyList() : connectionTargetValue.eClass().getEAllStructuralFeatures();
					
					boolean isPassThrough = isPassThrough(source, value);
					
					// Connection source features		
					for (EStructuralFeature connectionSourceValueFeature: connectionSourceValueFeatures) {
						wireConnectionSourceFeature(
								source,	
								connectionSource,
								connectionSourceValue,
								connectionSourceValueFeature,
								isPassThrough ? connectionTarget : source,
								isPassThrough ? connectionTargetValue : value,
								registry,
								progressMonitor);
					}
					
					// Connection target features		
					for (EStructuralFeature connectionTargetValueFeature: connectionTargetValueFeatures) {
						wireConnectionTargetFeature(
								source,	
								connectionTarget,
								connectionTargetValue,
								connectionTargetValueFeature,
								isPassThrough ? connectionSource : source,
								isPassThrough ? connectionSourceValue : value,
								registry,
								progressMonitor);
					}
					
					// Own features		
					for (EStructuralFeature valueFeature: valueFeatures) {
						wireFeature(
								source, 
								value, 
								valueFeature, 
								registry, 
								progressMonitor);			
						
						if (connectionSource != null) {
							wireConnectionStartFeature(
									source,
									value,
									valueFeature,
									connectionSource,
									connectionSourceValue,
									registry,
									progressMonitor);
						}
						
						if (connectionTarget != null) {
							wireConnectionEndFeature(
									source,
									value,
									valueFeature,
									connectionTarget,
									connectionTargetValue,
									registry,
									progressMonitor);
						}
					}
				}
			}
		}
		
		if (chain != null) {
			chain.wire(source, registry, progressMonitor);
		}
	}
	
	protected void wireConnection(S source, T target, Map<S,T> registry, ProgressMonitor progressMonitor) {
		
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
	protected Function<LinkedList<EObject>,Boolean> createPathMapper(S container, T containerValue, Map<S,T> registry, ProgressMonitor progressMonitor) {
		return path -> {
			@SuppressWarnings("unchecked")
			S contents = (S) path.getLast();
			boolean result = true;
			for (T contentsValue: select(contents, registry, progressMonitor)) {
				if (contentsValue == null && containerValue == null) {
					continue; 
				}
				
				// Container (parent) features capable of accepting the child
				List<EStructuralFeature> containerFeatures = containerValue == null ? Collections.emptyList() : containerValue.eClass().getEAllStructuralFeatures();
				List<EStructuralFeature> contentsFeatures = contentsValue == null ? Collections.emptyList() : contentsValue.eClass().getEAllStructuralFeatures();
	
				result = result && containerFeatures.isEmpty() && contentsFeatures.isEmpty();
				
				for (EStructuralFeature containerFeature: containerFeatures) {					
					if (wireContainerFeature(
							container, 
							containerValue, 
							containerFeature, 
							contents, 
							contentsValue, 
							path, 
							registry, 
							progressMonitor)) {
						
						result = true;
					}
				}
							
				for (EStructuralFeature contentsFeature: contentsFeatures) {
					if (wireContentsFeature(
							contents, 
							contentsValue, 
							contentsFeature, 
							container, 
							containerValue, 
							path, 
							registry, 
							progressMonitor)) {
						
						result = true;
					}
				}
			}
			
			return result;
		};
	}
	
	/**
	 * Possibly wires a {@link EStructuralFeature} of the connection semantic element or connection target semantic element to the semantic element of connection source.
	 */
	protected abstract void wireFeature(
		S source,
		T value,
		EStructuralFeature valueFeature,
		Map<S, T> registry,
		ProgressMonitor progressMonitor);	
			
	/**
	 * Possibly wires a {@link EStructuralFeature} of the container's semantic target to the contentsTarget.
	 * For example sets target's reference to the contentsTarget or sets target's attribute value to the label of the contents. 
	 * Returns true to go down the contents hierarchy.
	 * @param container Source container
	 * @param containerTarget 
	 * @param containerTargetFeature
	 * @param contents
	 * @param sourcePath
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	protected abstract boolean wireContainerFeature(
			S container,
			T containerValue,
			EStructuralFeature containerValueFeature,
			S contents,
			T contentsValue,
			LinkedList<EObject> sourcePath,
			Map<S, T> registry,
			ProgressMonitor progressMonitor);
	
	
	/**
	 * Possibly wires a {@link EStructuralFeature} of the semantic target (contentsTarget) of the last element of the containment path
	 * to the target - the semantic element of the container.
	 * For example, sets contentsdTarget's reference to the target or sets contentsTarget's attribute to the value of the source label.
	 * Returns true to go down the contents hierarchy.
	 * @param contents
	 * @param contentsValue
	 * @param contentsValueFeature
	 * @param sourcePath
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	protected abstract boolean wireContentsFeature(
			S contents,
			T contentsValue,
			EStructuralFeature contentsValueFeature,
			S container,
			T containerValue,
			LinkedList<EObject> sourcePath,
			Map<S, T> registry,
			ProgressMonitor progressMonitor);
	
	// Connection wiring
	
	/**
	 * If the argument is a connection/association - returns its source. 
	 * Otherwise returns null.
	 * @param connection
	 * @return
	 */
	protected abstract S getConnectionSource(S connection);
	
	/**
	 * If the argument is a connection/association - returns its target.
	 * Otherwise returns null.
	 * @param connection
	 * @return
	 */
	protected abstract S getConnectionTarget(S connection);
		
	/**
	 * Possibly wires a {@link EStructuralFeature} of the connection source semantic element to the argument.
	 */
	protected abstract void wireConnectionSourceFeature(
		S connection,	
		S connectionSource,
		T connectionSourceValue,
		EStructuralFeature connectionSourceValueFeature,
		S argument,
		T argumentValue,
		Map<S, T> registry,
		ProgressMonitor progressMonitor);
	
	/**
 	 * Possibly wires a {@link EStructuralFeature} of the connection target semantic element to the argument.
	 */
	protected abstract void wireConnectionTargetFeature(
		S connection,	
		S connectionTarget,
		T connectionTargetValue,
		EStructuralFeature connectionTargetValueFeature,
		S argument,
		T argumentValue,
		Map<S, T> registry,
		ProgressMonitor progressMonitor);
	
	/**
	 * Possibly wires a {@link EStructuralFeature} of the connection semantic element or connection target semantic element to the semantic element of connection source.
	 */
	protected abstract void wireConnectionStartFeature(
		S connection,
		T connectionValue,
		EStructuralFeature connectionValueFeature,
		S connectionSource,
		T connectionSourceValue,
		Map<S, T> registry,
		ProgressMonitor progressMonitor);
	
	/**
	 * Possibly wires a {@link EStructuralFeature} of the connection semantic element or connection start semantic element to the semantic element of connection target.
	 */
	protected abstract void wireConnectionEndFeature(
		S connection,
		T connectionValue,
		EStructuralFeature connectionValueFeature,
		S connectionTarget,
		T connectionTargetValue,
		Map<S, T> registry,
		ProgressMonitor progressMonitor);

}
