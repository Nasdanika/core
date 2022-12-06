package org.nasdanika.graph.processor.emf;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

/**
 * A processor which maps graph elements to elements of a semantic model. 
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
