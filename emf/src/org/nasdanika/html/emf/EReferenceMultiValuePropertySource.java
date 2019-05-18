package org.nasdanika.html.emf;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.html.app.PropertyDescriptor;

public class EReferenceMultiValuePropertySource<T extends EObject> extends EStructuralFeatureMultiValuePropertySource<T, EReference> {

	protected EClassPropertySource propertySourceDelegate;

	public EReferenceMultiValuePropertySource(T target, EReference feature) {
		super(target, feature);
		propertySourceDelegate = new EClassPropertySource(feature.getEReferenceType(), () -> adaptTo(AccessController.class));
	}

	@Override
	public List<PropertyDescriptor> getPropertyDescriptors() {
		return propertySourceDelegate.getPropertyDescriptors();
	}

}
