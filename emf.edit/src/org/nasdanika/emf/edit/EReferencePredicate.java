package org.nasdanika.emf.edit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * {@link EObject}s can implement this interface to filter out 
 * objects and their {@link EReference}s to which it can be added. 
 * @author Pavel
 *
 */
public interface EReferencePredicate {
	
	/**
	 * @param source EObject to test for eligibility to add target object to its eReference.
	 * @param eReference EReference to which the target object is tested for addition.
	 * @param target Target object being tested for addition to the source object {@link EReference}.
	 * @return
	 */
	boolean accept(EObject source, EReference eReference, EObject target);

}
