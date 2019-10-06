package org.nasdanika.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * {@link EObject}s can implement this interface to filter out 
 * objects which can be added to its {@link EReference}s. 
 * @author Pavel
 *
 */
public interface EReferenceTargetPredicate {
	
	/**
	 * @param candidate EObject to test for eligibility to be added to eReference
	 * @param eReference EReference to which the candidate EObject is tested for addition.
	 * @return
	 */
	boolean acceptTarget(EObject candidate, EReference eReference);

}
