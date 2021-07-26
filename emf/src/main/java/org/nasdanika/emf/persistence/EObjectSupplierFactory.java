package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.DelegatingSupplierFactoryFeature;
import org.nasdanika.common.persistence.EnumSupplierFactoryAttribute;
import org.nasdanika.common.persistence.Feature;
import org.nasdanika.common.persistence.FunctionSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.MapSupplierFactoryAttribute;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.common.persistence.TypedSupplierFactoryAttribute;
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
			if (!feature.isChangeable() && !"true".equals(EmfUtil.getNasdanikaAnnotationDetail(feature, EObjectLoader.IS_COMPUTED))) {
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
		
		return wrapFeature(featureKey, feature, loader, keyProvider, EObjectLoader.isDefaultFeature(eClass, feature));
	}

	/**
	 * Wraps {@link EStructuralFeature} into {@link Feature}. 
	 * Static version is needed for {@link EMap} support. 
	 * @param featureKey
	 * @param feature
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Feature<?> wrapFeature(
			String featureKey, 
			EStructuralFeature feature, 
			EObjectLoader loader, 
			java.util.function.Function<ENamedElement,String> keyProvider,
			boolean isDefault) {

		String exclusiveWithStr = EmfUtil.getNasdanikaAnnotationDetail(feature, EObjectLoader.EXCLUSIVE_WITH);
		Object[] exclusiveWith = exclusiveWithStr == null ? new String[0] : exclusiveWithStr.split("\\s");
		
		String documentation = EmfUtil.getDocumentation(feature);
		if (feature instanceof EAttribute) {
			if (feature.isMany()) {
				return new ListSupplierFactoryAttribute<>(new org.nasdanika.common.persistence.ReferenceList<>(featureKey, isDefault, feature.isRequired(), null, documentation, exclusiveWith), true);
			}
			
			EClassifier featureType = feature.getEType();
			Class<?> featureClass = featureType.getInstanceClass();
			boolean interpolate = "true".equals(EmfUtil.getNasdanikaAnnotationDetail(feature, "interpolate", "true"));
			if (String.class == featureClass) {
				org.nasdanika.common.persistence.Reference delegate = new org.nasdanika.common.persistence.Reference(featureKey, isDefault, feature.isRequired(), null, documentation) {
					
					@Override
					public Object create(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor,	Marker marker) throws Exception {
						Object ret = super.create(loader, config, base, progressMonitor, marker);
						if (ret instanceof String && "true".equals(EmfUtil.getNasdanikaAnnotationDetail(feature, EObjectLoader.IS_RESOLVE_URI))) {
							return base.toURI().resolve((String) ret).toString();
						}
						return ret;
					}
					
				};
				return new StringSupplierFactoryAttribute(delegate, interpolate);
			}
			
			if (featureClass.isEnum()) {
				StringSupplierFactoryAttribute stringAttribute = new StringSupplierFactoryAttribute(new org.nasdanika.common.persistence.Reference(featureKey, isDefault, feature.isRequired(), null, documentation, exclusiveWith), interpolate);
				return new EnumSupplierFactoryAttribute(stringAttribute, featureClass, null);
			}
			
			if (EClass.class == featureClass) {
				StringSupplierFactoryAttribute stringAttribute = new StringSupplierFactoryAttribute(new org.nasdanika.common.persistence.Reference(featureKey, isDefault, feature.isRequired(), null, documentation, exclusiveWith), interpolate);
				FunctionFactory<String,EClass> functionFactory = new FunctionFactory<String, EClass>() {

					@Override
					public Function<String, EClass> create(Context ctx) throws Exception {
						return new Function<String, EClass>() {

							@Override
							public double size() {
								return 1;
							}

							@Override
							public String name() {
								return "Resolving type to EClass";
							}

							@Override
							public EClass execute(String type, ProgressMonitor progressMonitor) throws Exception {
								BiSupplier<EClass, java.util.function.Function<ENamedElement, String>> result = loader.resolveEClass(type);
								if (result == null) {
									throw new ConfigurationException("Cannot resolve " + type+ " to EClass", EObjectSupplierFactory.this.getMarker());
								}
								return result.getFirst();
							}
							
						};
					}
					
				};
				return new FunctionSupplierFactoryAttribute(stringAttribute, functionFactory);
			}
			
			Function converter = null; // TODO: From annotations for, say, dates - parse pattern - SimpleDateFormat?
			return new TypedSupplierFactoryAttribute(featureClass, new org.nasdanika.common.persistence.Reference(featureKey, isDefault, feature.isRequired(), null, documentation, exclusiveWith), interpolate, converter);			
		}
		
		if (feature instanceof EReference) {
			EReference eReference = (EReference) feature;
			if (feature.isMany()) {
				// EMap
				if (feature.getEType().getInstanceClass() == Map.Entry.class) {
					return new MapSupplierFactoryAttribute<>(new ReferenceMap<>(
							featureKey, 
							isDefault, 
							feature.isRequired(), 
							null, 
							documentation,
							eReference,
							loader,
							true,
							keyProvider,
							exclusiveWith), true);
				}
				return new ListSupplierFactoryAttribute<>(new ReferenceList<>(
						featureKey, 
						isDefault, 
						feature.isRequired(), 
						null, 
						documentation,
						eReference,
						loader,
						true,
						keyProvider,
						exclusiveWith), true);
			}
			
			return new DelegatingSupplierFactoryFeature<>(new Reference<>(
					featureKey, 
					isDefault, 
					feature.isRequired(), 
					null, 
					documentation,
					eReference,
					loader,
					true,					
					keyProvider,
					exclusiveWith));
			
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
				Marker marker = getMarker();
				Map<EStructuralFeature, Object> loadedFeatures = new HashMap<>();
				if (marker != null) {
					ret.eAdapters().add(new MarkedAdapter(marker));
				}
				EStructuralFeature[] loadingFeature = { null };
				ret.eAdapters().add(new LoadTrackerAdapter() {

					@Override
					public boolean isLoaded(EStructuralFeature feature) {
						return loadedFeatures.containsKey(feature);
					}

					@Override
					public boolean isLoading(EStructuralFeature feature) {
						return feature == loadingFeature[0];
					}
					
					@Override
					public Object get(EStructuralFeature feature) {
						return loadedFeatures.get(feature);
					}
					
				});
				for (Feature<?> feature: features) {
					if (feature.isLoaded()) {
						try {
							EStructuralFeature structuralFeature = featureMap.get(feature.getKey());
							loadingFeature[0] = structuralFeature;
							Object value = feature.get(data);
							if (structuralFeature.isChangeable()) {
								ret.eSet(structuralFeature, value);
							}
							loadedFeatures.put(structuralFeature, value);
						} catch (ConfigurationException e) {
							throw e;
						} catch (Exception e) {
							throw marker == null ? e : new ConfigurationException("Error setting feature " + feature.getKey() + " in " + ret + ": " + e, e, marker);
						} finally {
							loadingFeature[0] = null;
						}
					}
				}
				return ret;
			}
		};
	}
	
}
