package org.nasdanika.emf.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
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
import org.nasdanika.emf.EmfUtil.EModelElementDocumentation;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * Loads {@link EObject} {@link EStructuralFeature}s from configuration.
 * @author Pavel
 *
 */
public class EObjectSupplierFactory extends SupplierFactoryFeatureObject<EObject> {
	
	private Map<String, EStructuralFeature> featureMap = new LinkedHashMap<>();

	private EClass eClass;
	private EObjectLoader loader;
		
	private java.util.function.Function<EClass, EObject> constructor = this::instantiate;

	/**
	 * 
	 * @param loader
	 * @param eClass
	 * @param keyProvider
	 * @param constructor Constructs a new EObject instance for EClass. If null, EPackage factory is used to create a new object.
	 */
	public EObjectSupplierFactory(
			EObjectLoader loader, 
			EClass eClass, 
			BiFunction<EClass,ENamedElement,String> keyProvider,
			java.util.function.Function<EClass, EObject> constructor) {

		this.loader = loader;
		this.eClass = eClass;	// TODO - handling prototype - if there is an annotation - chain - may need to handle @ super?
		if (keyProvider == null) {
			keyProvider = EObjectLoader.LOAD_KEY_PROVIDER; 
		}		
		for (EStructuralFeature feature: eClass.getEAllStructuralFeatures()) {
			if (!feature.isChangeable() && !"true".equals(NcoreUtil.getNasdanikaAnnotationDetail(feature, EObjectLoader.IS_COMPUTED))) {
				continue;
			}
			String featureKey = keyProvider.apply(eClass, feature);
			if (featureKey == null) {
				continue;
			}
			featureMap.put(featureKey, feature);
			addFeature(wrapFeature(featureKey, feature, loader, keyProvider));
		}
		if (constructor != null) {
			this.constructor = constructor;
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
			BiFunction<EClass,ENamedElement,String> keyProvider) {
		
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
			BiFunction<EClass,ENamedElement,String> keyProvider,
			boolean isDefault) {
		
		Object[] exclusiveWith = EObjectLoader.getExclusiveWith(eClass, feature, keyProvider);
		
		EModelElementDocumentation documentation = EmfUtil.getDocumentation(feature);
		if (feature instanceof EAttribute) {
			if (feature.isMany()) {
				return new ListSupplierFactoryAttribute<>(new org.nasdanika.common.persistence.ReferenceList<>(featureKey, isDefault, feature.isRequired(), null, documentation == null ? null : documentation.getDocumentation(), exclusiveWith), true);
			}
			
			EClassifier featureType = feature.getEType();
			Class<?> featureClass = featureType.getInstanceClass();
			boolean interpolate = "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(feature, "interpolate", "true"));
			if (String.class == featureClass) {
				org.nasdanika.common.persistence.Reference delegate = new org.nasdanika.common.persistence.Reference(featureKey, isDefault, feature.isRequired(), null, documentation == null ? null : documentation.getDocumentation(), exclusiveWith) {
					
					@Override
					public Object create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor,	List<? extends Marker> markers) throws Exception {
						Object ret = super.create(loader, config, base, progressMonitor, markers);
						if (base != null && base.hasAbsolutePath() && ret instanceof String && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(feature, EObjectLoader.IS_RESOLVE_URI))) {
							return URI.createURI((String) ret).resolve(base).toString();
						}
						return ret;
					}
					
				};
				return new StringSupplierFactoryAttribute(delegate, interpolate);
			}
			
			if (featureClass.isEnum()) {
				StringSupplierFactoryAttribute stringAttribute = new StringSupplierFactoryAttribute(new org.nasdanika.common.persistence.Reference(featureKey, isDefault, feature.isRequired(), null, documentation == null ? null : documentation.getDocumentation(), exclusiveWith), interpolate);
				return new EnumSupplierFactoryAttribute(stringAttribute, featureClass, null);
			}
			
			if (EClass.class == featureClass) {
				StringSupplierFactoryAttribute stringAttribute = new StringSupplierFactoryAttribute(new org.nasdanika.common.persistence.Reference(featureKey, isDefault, feature.isRequired(), null, documentation == null ? null : documentation.getDocumentation(), exclusiveWith), interpolate);
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
								BiSupplier<EClass, BiFunction<EClass,ENamedElement, String>> result = loader.resolveEClass(type);
								if (result == null) {
									throw new ConfigurationException("Cannot resolve " + type+ " to EClass", EObjectSupplierFactory.this.getMarkers());
								}
								return result.getFirst();
							}
							
						};
					}
					
				};
				return new FunctionSupplierFactoryAttribute(stringAttribute, functionFactory);
			}
			
			Function converter = null; // TODO: From annotations for, say, dates - parse pattern - SimpleDateFormat?
			return new TypedSupplierFactoryAttribute(featureClass, new org.nasdanika.common.persistence.Reference(featureKey, isDefault, feature.isRequired(), null, documentation == null ? null : documentation.getDocumentation(), exclusiveWith), interpolate, converter);			
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
							documentation == null ? null : documentation.getDocumentation(),
							eClass,
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
						documentation == null ? null : documentation.getDocumentation(),
						eClass,
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
					documentation == null ? null : documentation.getDocumentation(),
					eClass,
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
			
			@SuppressWarnings("unchecked")
			@Override
			public EObject execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {
				EObject ret = constructor.apply(eClass);
				if (ret == null) {
					ret = instantiate(eClass);
				}
				List<? extends Marker> markers = getMarkers();
				loader.mark(ret, markers, progressMonitor);
				Map<EStructuralFeature, Object> loadedFeatures = new HashMap<>();
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
								// Unwrapping singleton lists
								if (structuralFeature instanceof EReference) {
									if (structuralFeature.isMany()) {
										if (value instanceof List) {
											List<Object> theValue = new ArrayList<>();
											for (Object element: (List<?>) value) {
												if (element instanceof List) {
													theValue.addAll((List<Object>) element);
												} else {
													theValue.add(element);
												}												
											}
											setFeature(ret, structuralFeature, theValue);
										} else if (value instanceof Map) {
											EClass refType = ((EReference) structuralFeature).getEReferenceType();
											boolean isManyValue = false;
											Class<?> refTypeInstanceClass = refType.getInstanceClass();
											if (refTypeInstanceClass != null && Map.Entry.class.isAssignableFrom(refTypeInstanceClass)) {
												EStructuralFeature valueFeature = refType.getEStructuralFeature("value");
												isManyValue = valueFeature != null && valueFeature.isMany();
											}
											Map<Object,Object> theValue = new LinkedHashMap<>();
											for (Entry<Object, Object> entry: ((Map<Object,Object>) value).entrySet()) {
												Object entryValue = entry.getValue();
												if (entryValue instanceof List) {
													List<?> entryValueList = (List<?>) entryValue;
													if (isManyValue) {
														List<Object> theEntryValueList = new ArrayList<>();
														for (Object entryValueElement: (List<?>) entryValueList) {
															if (entryValueElement instanceof List) {
																theEntryValueList.addAll((List<Object>) entryValueElement);
															} else {
																theEntryValueList.add(entryValueElement);
															}												
														}	
														theValue.put(entry.getKey(), theEntryValueList);
													} else {
														if (entryValueList.size() == 1) {
															theValue.put(entry.getKey(), entryValueList.get(0));														
														} else if (!entryValueList.isEmpty()) {
															throw new ConfigurationException("Map entry value list size is more than one: "+ entryValue, markers);
														}
													}
												} else {
													theValue.put(entry.getKey(), entryValue);
												}												
											}
											setFeature(ret, structuralFeature, theValue);											
										} else {
											setFeature(ret, structuralFeature, value);											
										}
									} else {
										if (value instanceof List && ((List<?>) value).size() == 1) {
											setFeature(ret, structuralFeature, ((List<?>) value).get(0));
										} else {
											setFeature(ret, structuralFeature, value);
										}
									}
								} else {
									setFeature(ret, structuralFeature, value);
								}
							}
							loadedFeatures.put(structuralFeature, value);
						} catch (ConfigurationException e) {
							throw e;
						} catch (Exception e) {
							throw markers == null || markers.isEmpty() ? e : new ConfigurationException("Error setting feature " + feature.getKey() + " in " + ret + ": " + e, e, markers);
						} finally {
							loadingFeature[0] = null;
						}
					}
				}
				return ret;
			}
		};
	}
	
	protected EObject instantiate(EClass eClazz) {
		return eClazz.getEPackage().getEFactoryInstance().create(eClazz);
	}	

	/**
	 * Sets structural feature. This implementation calls eObj.eSet().  
	 * @param eObj
	 * @param structuralFeature
	 * @param value
	 */
	protected void setFeature(EObject eObj, EStructuralFeature structuralFeature, Object value) {
		eObj.eSet(structuralFeature, value);
	}
	
}
