package org.nasdanika.mapping;

import java.util.Collection;
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
	
	/**
	 * Selects zero or more target (semantic) elements for the source element. This implementation returns registry value singleton or an empty list if the registry value is null.
	 * Override to support, for example, mapping to multiple semantic values. 
	 * @param source
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	public Collection<T> select(S source, Map<S,T> registry, ProgressMonitor progressMonitor) {
		T target = registry.get(source);
		return target == null ? Collections.emptyList() : Collections.singleton(target);
	}
	
	@Override
	public void wire(S source, Map<S,T> registry, ProgressMonitor progressMonitor) {
		Collection<T> targets = select(source, registry, progressMonitor);
		if (targets.isEmpty()) {
			// No target (pass-through) connection - source and target
			S connectionSource = getContentProvider().getConnectionSource(source);
			if (connectionSource != null) {
				for (T connectionSourceValue: select(connectionSource, registry, progressMonitor)) {		
					List<EStructuralFeature> connectionSourceValueFeatures = connectionSourceValue.eClass().getEAllStructuralFeatures();
							
					S connectionTarget = getContentProvider().getConnectionTarget(source);
					if (connectionTarget != null) {
						for (T connectionTargetValue: select(connectionTarget, registry, progressMonitor)) {		
							
							// Connection source features		
							for (EStructuralFeature connectionSourceValueFeature: connectionSourceValueFeatures) {
								wireConnectionSourceFeature(
										source,	
										connectionSource,
										connectionSourceValue,
										connectionSourceValueFeature,
										connectionTarget,
										connectionTargetValue,
										registry,
										progressMonitor);
							}
							
							// Connection target features		
							List<EStructuralFeature> connectionTargetValueFeatures = connectionTargetValue.eClass().getEAllStructuralFeatures();
							for (EStructuralFeature connectionTargetValueFeature: connectionTargetValueFeatures) {
								wireConnectionTargetFeature(
										source,	
										connectionTarget,
										connectionTargetValue,
										connectionTargetValueFeature,
										connectionSource,
										connectionSourceValue,
										registry,
										progressMonitor);
							}
						}
					}
				}
			}
		} else {
			for (T value: targets) {
				HashSet<S> tracker = new HashSet<>();			
				for (S contents: getContentProvider().getChildren(source)) {
					if (tracker.add(contents)) {
						LinkedList<S> path = new LinkedList<>();
						path.add(contents);
						Function<LinkedList<S>, Boolean> pathMapper = createPathMapper(source, value, registry, progressMonitor);
						wireContents(path, pathMapper, tracker::add);
					}
				}
				
				/*
				 * TODO Traversal of non-containment references similar to contents but with tracing of the feature used to traverse
				 * and avoiding loops.
				 * 
				 * Add wireReferenceTarget and wireReferenceSource or Reference/Referrer. 
				 * Implement support in sub-classes such as setter mapper
				 */
				
				List<EStructuralFeature> valueFeatures = value.eClass().getEAllStructuralFeatures();		
				
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
				if (connectionSource != null) {
					for (T connectionSourceValue: select(connectionSource, registry, progressMonitor)) {		
						List<EStructuralFeature> connectionSourceValueFeatures = connectionSourceValue.eClass().getEAllStructuralFeatures();
						
						for (EStructuralFeature valueFeature: valueFeatures) {
							wireConnectionStartFeature(
									source,
									value,
									valueFeature,
									connectionSource,
									connectionSourceValue,
									registry,
									progressMonitor);
						}
						
								
						S connectionTarget = getContentProvider().getConnectionTarget(source);
						if (connectionTarget != null) {
							for (T connectionTargetValue: select(connectionTarget, registry, progressMonitor)) {		
								List<EStructuralFeature> connectionTargetValueFeatures = connectionTargetValue.eClass().getEAllStructuralFeatures();
								
								// Connection source features		
								for (EStructuralFeature connectionSourceValueFeature: connectionSourceValueFeatures) {
									wireConnectionSourceFeature(
											source,	
											connectionSource,
											connectionSourceValue,
											connectionSourceValueFeature,
											source,
											value,
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
											source,
											value,
											registry,
											progressMonitor);
								}
								
								// Connection end	
								for (EStructuralFeature valueFeature: valueFeatures) {
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
			for (S child: getContentProvider().getChildren(path.getLast())) {
				if (tracker.test(child)) {
					LinkedList<S> subPath = new LinkedList<>(path);
					subPath.add(child);
					wireContents(subPath, pathMapper, tracker);
				}
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
