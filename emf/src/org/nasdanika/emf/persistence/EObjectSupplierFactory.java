package org.nasdanika.emf.persistence;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.DelegatingSupplierFactoryFeature;
import org.nasdanika.common.persistence.Feature;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.emf.EmfUtil;

/**
 * Loads {@link EObject} {@link EStructuralFeature}s from configuration.
 * @author Pavel
 *
 */
public class EObjectSupplierFactory extends SupplierFactoryFeatureObject<EObject> {
	
	private Map<String, EStructuralFeature> featureMap = new LinkedHashMap<>();

	private EClass eClass;

	public EObjectSupplierFactory(
			EObjectLoader loader, 
			EClass eClass, 
			java.util.function.Function<ENamedElement,String> keyProvider) {
		
		this.eClass = eClass;	
		if (keyProvider == null) {
			keyProvider = EObjectLoader.LOAD_KEY_PROVIDER; 
		}		
		for (EStructuralFeature feature: eClass.getEAllStructuralFeatures()) {
			if (!feature.isChangeable()) {
				continue;
			}
			String featureKey = keyProvider.apply(feature);
			if (featureKey == null) {
				continue;
			}
			featureMap.put(featureKey, feature);
			addFeature(wrapFeature(featureKey, feature, loader, keyProvider));
		}			
		
	}

	/**
	 * Wraps {@link EStructuralFeature} into {@link Feature}
	 * @param featureKey
	 * @param feature
	 * @return
	 */
	protected Feature<?> wrapFeature(
			String featureKey, 
			EStructuralFeature feature, 
			EObjectLoader loader, 
			java.util.function.Function<ENamedElement,String> keyProvider) {
		
		boolean isDefault = "true".equals(EmfUtil.getNasdanikaAnnotationDetail(feature, EObjectLoader.IS_DEFAULT_FEATURE));;
		String documentation = EmfUtil.getDocumentation(feature);
		if (feature instanceof EAttribute) {
			if (feature.isMany()) {
				return new ListSupplierFactoryAttribute<>(new org.nasdanika.common.persistence.ReferenceList<>(featureKey, isDefault, feature.isRequired(), null, documentation), true);
			}
			
			return new StringSupplierFactoryAttribute(new org.nasdanika.common.persistence.Reference(featureKey, isDefault, feature.isRequired(), null, documentation), true);
		}
		
		if (feature instanceof EReference) {
			EReference eReference = (EReference) feature;
			boolean isHomogenous = !eReference.getEReferenceType().isAbstract() && "true".equals(EmfUtil.getNasdanikaAnnotationDetail(feature, EObjectLoader.IS_HOMOGENOUS));			
			boolean isStrictContainment = isHomogenous && "true".equals(EmfUtil.getNasdanikaAnnotationDetail(feature, EObjectLoader.IS_STRICT_CONTAINMENT));			
			if (feature.isMany()) {
				return new ListSupplierFactoryAttribute<>(new ReferenceList<>(
						featureKey, 
						isDefault, 
						feature.isRequired(), 
						null, 
						documentation, 
						loader.getResourceSet(),
						eReference.isResolveProxies() ? null : loader,
						isHomogenous ? eReference.getEReferenceType() : null,
						isStrictContainment,
						keyProvider), true);
			}
			
			return new DelegatingSupplierFactoryFeature<>(new Reference<>(
					featureKey, 
					isDefault, 
					feature.isRequired(), 
					null, 
					documentation, 
					loader.getResourceSet(),
					eReference.isResolveProxies() ? null : loader,
					isHomogenous ? eReference.getEReferenceType() : null,
					isStrictContainment,
					keyProvider));
			
		}
		
		throw new UnsupportedOperationException("Unusupported feature type: " + feature);
	}

	@Override
	protected Function<Map<Object, Object>, EObject> createResultFunction(Context context) {
		return new Function<Map<Object,Object>, EObject>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Creating " + eClass.getName();
			}
			
			@Override
			public EObject execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {
				EObject ret = eClass.getEPackage().getEFactoryInstance().create(eClass);
				if (getMarker() != null) {
					ret.eAdapters().add(new MarkedAdapter(getMarker()));
				}
				for (Feature<?> feature: features) {
					if (feature.isLoaded()) {
						ret.eSet(featureMap.get(feature.getKey()), feature.get(data));
					}
				}
				return ret;
			}
		};
	}
	
}