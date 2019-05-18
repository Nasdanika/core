package org.nasdanika.html.emf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EStructuralFeatureLabel<T extends EStructuralFeature> extends ENamedElementLabel<T> {

	public EStructuralFeatureLabel(T eNamedElement) {
		super(eNamedElement);
	}
	
	@Override
	public Object getId() {
		List<String> modelPath = new ArrayList<>();
		EClass eClass = modelElement.getEContainingClass();
		modelPath.add(eClass.getEPackage().getNsURI());
		modelPath.add(eClass.getName());
		modelPath.add(modelElement.getName());
		return modelPath;
	}

}
