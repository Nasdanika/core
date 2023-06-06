package org.nasdanika.emf.persistence;

import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * Creates reference value/element - {@link EObject} or proxy.
 * May create zero or more elements for filesets and resourcesets.
 * @author Pavel
 *
 */
public class ReferenceFactory extends TypedElementFactory {
	
	/**
	 * 
	 * @param eClass
	 * @param eReference
	 * @param eReferenceKey Reference key for loading type from annotations. If null, defautls to eReference name. Used by EMap's.
	 * @param resolver
	 * @param referenceSupplierFactory
	 * @param keyProvider
	 */
	public ReferenceFactory(
			EClass eClass,
			EReference eReference,
			String eReferenceKey,  
			EObjectLoader resolver,
			boolean referenceSupplierFactory,
			BiFunction<EClass,ENamedElement,String> keyProvider) {
		
		super(eClass, eReference, eReferenceKey, resolver, referenceSupplierFactory, keyProvider);
		this.resolveProxies = !eReference.isResolveProxies();
	}
	
}
