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
	 * @param direct If true, an object is considered for direct addition and may be a subject for capacity limitations, e.g. single reference. 
	 * If false, the target is considered for type compatibility by filters. E.g. if reference A can contain only instances of B "semantically" but can also contain a predicate P for conditional
	 * inclusion of B, then P may call accept() with direct set to false to select semantic candidates which are compatible with the reference A.
	 * @return
	 */
	boolean accept(EObject source, EReference eReference, EObject target, boolean direct);

}
