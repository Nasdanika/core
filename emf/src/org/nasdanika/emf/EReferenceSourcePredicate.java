package org.nasdanika.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * {@link EObject}s can implement this interface to filter out 
 * objects and their {@link EReference}s to which it can be added. 
 * @author Pavel
 *
 */
public interface EReferenceSourcePredicate {
	
	/**
	 * @param source EObject to test for eligibility to add this object to its eReference.
	 * @param eReference EReference to which this object is tested for addition.
	 * @return
	 */
	boolean acceptSource(EObject source, EReference eReference);

}
