package org.nasdanika.mapping;

import java.util.Map;

import org.nasdanika.common.Composable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;

/**
 * This interface is intended to be used with maps produced by {@link Transformer}. 
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
public interface Mapper<S, T> extends Composable<Mapper<S,T>> {
	
	/**
	 * Wires all elements in the registry
	 * @param registry
	 * @param progressMonitor
	 */
	default void wire(Map<S,T> registry, ProgressMonitor progressMonitor) {
		for (S key: registry.keySet()) {
			wire(key, registry, progressMonitor);
		}
	}
	
	void wire(S source, Map<S,T> registry, ProgressMonitor progressMonitor);
	
	@Override
	default Mapper<S, T> compose(Mapper<S, T> other) {		
		return (source, registry, progressMonitor) -> {
			this.wire(source, registry, progressMonitor);
			other.wire(source, registry, progressMonitor);
		};
	}

}
