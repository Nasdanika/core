package org.nasdanika.html.emf;

import java.util.List;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.html.app.Delta;
import org.nasdanika.html.app.Diagnostic;
import org.nasdanika.html.app.SingleValueDataSource;

public class EStructuralFeatureSingleValueDataSource<T extends EObject, F extends EStructuralFeature> extends EObjectAdaptable<T> implements SingleValueDataSource {
	
	protected F feature;

	public EStructuralFeatureSingleValueDataSource(T target, F feature) {
		super(target);
		if (feature.isMany()) {
			throw new IllegalArgumentException("Many feature");
		}
		this.feature = feature;
	}

	@Override
	public Object getVersion(Object obj) {
		if (obj instanceof CDOObject) {
			return ((CDOObject) obj).cdoRevision();			
		}
		
		if (obj instanceof EObject) {
			return ((EObject) obj).eResource().getTimeStamp();
		}
		
		return null;
	}

	@Override
	public Diagnostic update(Object obj, Object version, List<Delta> deltas) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Object getValue() {
		return target.eGet(feature);
	}

}
