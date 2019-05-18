package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Delta;
import org.nasdanika.html.app.Diagnostic;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.PropertySource;

/**
 * {@link EClass} {@link PropertySource} to delegate label methods and getPropertyDescriptors() to. 
 * @author Pavel Vlasov
 *
 */
public class EClassPropertySource extends EClassLabel implements PropertySource {

	private Supplier<AccessController> accessControllerSupplier;
	
	public EClassPropertySource(EClass eClass, Supplier<AccessController> accessControllerSupplier) {
		super(eClass);
		this.accessControllerSupplier = accessControllerSupplier == null ? () -> AccessController.ALLOW_ALL : accessControllerSupplier;
	}
	

	@Override
	public Object getVersion(Object obj) {
		throw new UnsupportedOperationException("Wrong delegation - delegate to data source");
	}

	@Override
	public Diagnostic update(Object obj, Object version, List<Delta> deltas) {
		throw new UnsupportedOperationException("Wrong delegation - delegate to data source");
	}

	/**
	 * Returns property descriptors for single-value {@link EStructuralFeature}s.
	 */
	@Override
	public List<PropertyDescriptor> getPropertyDescriptors() {
		return getPropertyDescriptorFeatures().stream().map(this::createFeaturePropertyDescriptor).collect(Collectors.toList());
	}
	
	/**
	 * Creates a property descriptor for a feature.
	 * This implementation instantiates {@link EStructuralFeaturePropertyDescriptor}
	 * @param feature
	 * @return
	 */
	protected PropertyDescriptor createFeaturePropertyDescriptor(EStructuralFeature feature) {
		return new EStructuralFeaturePropertyDescriptor(feature);
	}
	
	/**
	 * This implementation returns single value non containment features sorted by name
	 * Override to customize, e.g. sort.
	 * @return features to wrap into property descriptors.
	 */
	protected List<EStructuralFeature> getPropertyDescriptorFeatures() {
		AccessController accessController = accessControllerSupplier.get();
		return modelElement.getEAllStructuralFeatures()
				.stream()
				.filter(f ->  accessController == null || accessController.canRead(f.getName()))
				.filter(this::isPropertyDescriptorFeature)
				.sorted((fa, fb) -> fa.getName().compareTo(fb.getName()))
				.collect(Collectors.toList());		
	}

	protected boolean isPropertyDescriptorFeature(EStructuralFeature feature) {
		return !feature.isMany() && !(feature instanceof EReference && ((EReference) feature).isContainment());
	}

	@Override
	public List<Action> getActions() {
		return Collections.emptyList();
	}

}
