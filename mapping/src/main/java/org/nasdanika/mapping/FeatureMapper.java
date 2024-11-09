package org.nasdanika.mapping;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;

/**
 * This mapper iterates over the target (semantic) element features calls <code>wireFeature()</code> for each feature.
 * For containment, direct and transitive, it calls <code>wireContentsFeature()</code> and <code>wireContainerFeature()</code> for every permutation of arguments.
 * For connections it calls <code>wireConnectionEndFeature()</code>, <code>wireConnectionSourceFeature()</code>,
<code>wireConnectionStartFeature()</code>,
<code>wireConnectionTargetFeature()</code>
 * 
 * @param <S>
 * @param <T>
 */
public abstract class FeatureMapper<S, T extends EObject> implements Mapper<S,T> {

	private Mapper<S, T> chain;
	protected ContentProvider<S> contentProvider;

	protected FeatureMapper(ContentProvider<S> contentProvider) {
		this.contentProvider = contentProvider;
	}
	
	protected FeatureMapper(Mapper<S,T> chain, ContentProvider<S> contentProvider) {
		this.chain = chain;
		this.contentProvider = contentProvider;
	}
	
	public ContentProvider<S> getContentProvider() {
		return contentProvider;
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
	public Iterable<T> select(S source, Map<S,T> registry, ProgressMonitor progressMonitor) {
		return Collections.singleton(registry.get(source));
	}
	
	@Override
	public void wire(S source, Map<S,T> registry, ProgressMonitor progressMonitor) {
		for (T value: select(source, registry, progressMonitor)) {
			HashSet<S> tracker = new HashSet<>();			
			for (S contents: getContentProvider().getContents(source, tracker::add)) {
				LinkedList<S> path = new LinkedList<>();
				path.add(contents);
				Function<LinkedList<S>, Boolean> pathMapper = createPathMapper(source, value, registry, progressMonitor);
				wireContents(path, pathMapper, tracker::add);
			}
			
			/*
			 * TODO Traversal of non-containment references similar to contents but with tracing of the feature used to traverse
			 * and avoiding loops.
			 * 
			 * Add wireReferenceTarget and wireReferenceSource or Reference/Referrer. 
			 * Implement support in sub-classes such as setter mapper
			 */
			
			List<EStructuralFeature> valueFeatures = value == null ? Collections.emptyList() : value.eClass().getEAllStructuralFeatures();		
			
			// Own features, no permutations
			for (EStructuralFeature valueFeature: valueFeatures) {
				wireFeature(
						source, 
						value, 
						valueFeature, 
						registry, 
						progressMonitor);			
			}
			
			S connectionSource = getContentProvider().getConnectionSource(source);
			for (T connectionSourceValue: connectionSource == null ? Collections.<T>singleton(null) : select(connectionSource, registry, progressMonitor)) {		
				List<EStructuralFeature> connectionSourceValueFeatures = connectionSourceValue == null ? Collections.emptyList() : connectionSourceValue.eClass().getEAllStructuralFeatures();
						
				S connectionTarget = getContentProvider().getConnectionTarget(source);
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
					
					// Connection start and connection end	
					for (EStructuralFeature valueFeature: valueFeatures) {
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
		
	/**
	 * Recursive wiring of source contents 
	 * 
	 * @param path containment path with the first element being the immediate child of the source element 
	 * @param tracker Tracker to avoid infinite loops
	 */
	protected void wireContents(LinkedList<S> path, Function<LinkedList<S>,Boolean> pathMapper, Predicate<S> tracker) {
		if (pathMapper.apply(path)) {
			for (S child: getContentProvider().getContents(path.getLast(), tracker)) {
				LinkedList<S> subPath = new LinkedList<>(path);
				subPath.add(child);
				wireContents(subPath, pathMapper, tracker);
			}			
		}
	}
	
	/**
	 * Creates a function which maps a given containment path. 
	 * If the mapper returns true then traversal down the contents tree continues.
	 * This implementation calls createParentMappers() and createChildMappers() and then iterates over the returned mappers.
	 * @param source
	 * @param registry
	 * @return
	 */
	protected Function<LinkedList<S>,Boolean> createPathMapper(S container, T containerValue, Map<S,T> registry, ProgressMonitor progressMonitor) {
		return path -> {
			S contents = path.getLast();
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
			LinkedList<S> sourcePath,
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
			LinkedList<S> sourcePath,
			Map<S, T> registry,
			ProgressMonitor progressMonitor);
	
	// Connection wiring
		
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
