package org.nasdanika.graph.processor.emf;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

/**
 * A processor which operates on a collection of semantic elements crated from a graph element. 
 * @author Pavel
 *
 * @param <T>
 */
public interface SemanticProcessor<T extends EObject> {

	/**
	 * @return Semantic elements of this processor.
	 */
	Collection<T> getSemanticElements();

}
