package org.nasdanika.html.emf;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.html.app.Delta;
import org.nasdanika.html.app.Diagnostic;
import org.nasdanika.html.app.MultiValueDataSource;

public class EStructuralFeatureMultiValueDataSource<T extends EObject, F extends EStructuralFeature> extends EObjectAdaptable<T> implements MultiValueDataSource {
	
	protected F feature;

	public EStructuralFeatureMultiValueDataSource(T target, F feature) {
		super(target);
		if (!feature.isMany()) {
			throw new IllegalArgumentException("Single feature");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getValues() {
		return (List<Object>) target.eGet(feature);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getValues(Map<Object, String> filter, Map<Object, Boolean> sort) {
		Stream<Object> stream = ((List<Object>) target.eGet(feature)).stream().filter(createFilter(filter));
		if (sort != null && !sort.isEmpty()) {
			stream = stream.sorted(createComparator(sort));
		}
		return stream.collect(Collectors.toList());
	}

	protected Comparator<Object> createComparator(Map<Object, Boolean> sort) {
		throw new UnsupportedOperationException("Implement");
	}

	protected Predicate<? super Object> createFilter(Map<Object, String> filter) {
		if (filter == null || filter.isEmpty()) {
			return obj -> true;
		}

		throw new UnsupportedOperationException("Implement");
	}

}
