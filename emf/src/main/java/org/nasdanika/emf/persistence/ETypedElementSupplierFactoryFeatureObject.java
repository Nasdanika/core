package org.nasdanika.emf.persistence;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.persistence.SupplierFactoryFeatureObject;

public abstract class ETypedElementSupplierFactoryFeatureObject<T> extends SupplierFactoryFeatureObject<T> {
	
	protected Map<String, ETypedElement> featureMap = new LinkedHashMap<>();
	protected EObjectLoader loader;

	protected ETypedElementSupplierFactoryFeatureObject(EObjectLoader loader) {
		this.loader = loader;
	}
	
	

}
