package org.nasdanika.emf.persistence;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.emf.EmfUtil.EModelElementDocumentation;
import org.nasdanika.emf.persistence.EObjectLoader.ResolutionResult;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.DelegatingSupplierFactoryFeature;
import org.nasdanika.persistence.EnumSupplierFactoryAttribute;
import org.nasdanika.persistence.Feature;
import org.nasdanika.persistence.FunctionSupplierFactoryAttribute;
import org.nasdanika.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.persistence.MapSupplierFactoryAttribute;
import org.nasdanika.persistence.Marker;
import org.nasdanika.persistence.ObjectLoader;
import org.nasdanika.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.persistence.TypedSupplierFactoryAttribute;

/**
 * Loads {@link EObject} {@link EStructuralFeature}s from configuration.
 * @author Pavel
 */
public class EObjectSupplierFactory extends SupplierFactoryFeatureObject<EObject> {
	
	protected Map<String, ETypedElement> featureMap = new LinkedHashMap<>();
	protected EObjectLoader loader;

	private EClass eClass;
		
	private java.util.function.BiFunction<EClass, ProgressMonitor, EObject> constructor = this::instantiate;

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
			java.util.function.BiFunction<EClass, ProgressMonitor, EObject> constructor,
			boolean enforceRequired) {

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
			addFeature(wrapFeature(featureKey, feature, loader, keyProvider, enforceRequired));
		}

		Map<String, EOperation> opMap = new LinkedHashMap<>();
		for (EOperation operation: eClass.getEAllOperations()) {
			String operationKey = keyProvider.apply(eClass, operation);
			if (operationKey == null) {
				continue;
			}
						
			EOperation prevOp = opMap.get(operationKey);
			if (prevOp != null) {
				if (prevOp.isOverrideOf(operation)) {
					continue; // This operation is overridden, nothing to do
				}
				if (operation.isOverrideOf(prevOp)) {
					opMap.remove(operationKey); // Removing overridden operation, will add a new one
				} else {
					operationKey += "--" + operation.getOperationID(); // Overloading, using ID to dedup
				}
			}			
			if (opMap.containsKey(operationKey)) {
				throw new IllegalArgumentException("Duplicate operation key: " + operationKey);
			}
			opMap.put(operationKey, operation);
		}
		
		for (Entry<String, EOperation> oe: opMap.entrySet()) {
			String operationKey = oe.getKey();
			EOperation operation = oe.getValue();
			ETypedElement prevFeature = featureMap.get(operationKey);
			if (prevFeature != null) {
				operationKey = "--" + operation.getOperationID(); // Overlap with structural features, using ID to dedup
			}
			if (featureMap.containsKey(operationKey)) {
				throw new IllegalArgumentException("Duplicate feature key: " + operationKey);
			}
			
			featureMap.put(operationKey, operation);
			addFeature(wrapOperation(operationKey, operation, loader, keyProvider));			
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
			BiFunction<EClass,ENamedElement,String> keyProvider,
			boolean enforceRequired) {
		
		return wrapTypedElement(
				featureKey, 
				feature, 
				loader, 
				keyProvider, 
				EObjectLoader.isDefaultFeature(eClass, feature), 
				EObjectLoader.isConstructorFeature(eClass, feature),
				enforceRequired);
	}

	/**
	 * Wraps {@link ETypedElement} into {@link Feature}. 
	 * Static version is needed for {@link EMap} support. 
	 * @param featureKey
	 * @param feature
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Feature<?> wrapTypedElement(
			String featureKey, 
			ETypedElement feature, 
			EObjectLoader loader, 
			BiFunction<EClass,ENamedElement,String> keyProvider,
			boolean isDefault,
			boolean isConstructor, 
			boolean enforceRequired) {
		
		Object[] exclusiveWith = EObjectLoader.getExclusiveWith(eClass, feature, keyProvider);
		
		EModelElementDocumentation documentation = EmfUtil.getDocumentation(feature);
		if (feature instanceof EAttribute) {
			if (feature.isMany()) {
				return new ListSupplierFactoryAttribute<>(new org.nasdanika.persistence.ReferenceList<>(
						featureKey, 
						isDefault, 
						isConstructor, 
						enforceRequired && feature.isRequired(), 
						null, 
						documentation == null ? null : documentation.documentation(), 
						exclusiveWith),
					true);
			}
			
			EClassifier featureType = feature.getEType();
			Class<?> featureClass = featureType.getInstanceClass();
			boolean interpolate = "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(feature, "interpolate", "true"));
			if (String.class == featureClass) {
				org.nasdanika.persistence.Reference delegate = new org.nasdanika.persistence.Reference(
						featureKey, 
						isDefault, 
						isConstructor, 
						enforceRequired && feature.isRequired(), 
						null, 
						documentation == null ? null : documentation.documentation(), 
						exclusiveWith) {
					
					@Override
					public Object create(
							ObjectLoader loader, 
							Object config, 
							URI base,
							BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver,
							Collection<? extends Marker> markers, 
							ProgressMonitor progressMonitor) {
						Object ret = super.create(loader, config, base, resolver, markers, progressMonitor);
						if (base != null && !base.isRelative() && base.isHierarchical() && ret instanceof String && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(feature, EObjectLoader.IS_RESOLVE_URI))) {
							return URI.createURI((String) ret).resolve(base).toString();
						}
						return ret;
					}
					
				};
				return new StringSupplierFactoryAttribute(delegate, interpolate);
			}
			
			if (featureClass.isEnum()) {
				StringSupplierFactoryAttribute stringAttribute = new StringSupplierFactoryAttribute(new org.nasdanika.persistence.Reference(
						featureKey, 
						isDefault, 
						isConstructor, 
						enforceRequired && feature.isRequired(), 
						null, 
						documentation == null ? null : documentation.documentation(), 
						exclusiveWith), 
					interpolate);
				return new EnumSupplierFactoryAttribute(stringAttribute, featureClass, null);
			}
			
			if (EClass.class == featureClass) {
				StringSupplierFactoryAttribute stringAttribute = new StringSupplierFactoryAttribute(new org.nasdanika.persistence.Reference(
						featureKey, 
						isDefault, 
						isConstructor, 
						enforceRequired && feature.isRequired(), 
						null, 
						documentation == null ? null : documentation.documentation(), 
						exclusiveWith), 
					interpolate);
				
				FunctionFactory<String,EClass> functionFactory = new FunctionFactory<String, EClass>() {

					@Override
					public Function<String, EClass> create(Context ctx) {
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
							public EClass execute(String type, ProgressMonitor progressMonitor) {
								ResolutionResult result = loader.resolveEClass(type);
								if (result == null) {
									throw new ConfigurationException("Cannot resolve " + type+ " to EClass", EObjectSupplierFactory.this.getMarkers());
								}
								return result.eClass();
							}
							
						};
					}
					
				};
				return new FunctionSupplierFactoryAttribute(stringAttribute, functionFactory);
			}
			
			Function converter = null; // TODO: From annotations for, say, dates - parse pattern - SimpleDateFormat?
			return new TypedSupplierFactoryAttribute(featureClass, new org.nasdanika.persistence.Reference(
					featureKey, 
					isDefault, 
					isConstructor, 
					enforceRequired && feature.isRequired(), 
					null, 
					documentation == null ? null : documentation.documentation(), 
					exclusiveWith), 
				interpolate, 
				converter);			
		}
		
		if (feature instanceof EReference) {
			EReference eReference = (EReference) feature;
			if (feature.isMany()) {
				// EMap
				if (feature.getEType().getInstanceClass() == Map.Entry.class) {
					return new MapSupplierFactoryAttribute<>(new ReferenceMap<>(
							featureKey, 
							isDefault, 
							isConstructor,
							enforceRequired && feature.isRequired(), 
							null, 
							documentation == null ? null : documentation.documentation(),
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
						isConstructor,
						enforceRequired && feature.isRequired(), 
						null, 
						documentation == null ? null : documentation.documentation(),
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
					isConstructor,
					enforceRequired && feature.isRequired(), 
					null, 
					documentation == null ? null : documentation.documentation(),
					eClass,
					eReference,
					loader,
					true,					
					keyProvider,
					exclusiveWith));
			
		}
		
		throw new UnsupportedOperationException("Unusupported feature type: " + feature);
	}
	
	/**
	 * Wraps {@link EOperation} into {@link Feature}
	 * @param operationKey
	 * @param operation
	 * @return
	 */
	protected Feature<?> wrapOperation(
			String operationKey, 
			EOperation operation, 
			EObjectLoader loader, 
			BiFunction<EClass,ENamedElement,String> keyProvider) {
		
		return wrapOperation(operationKey, operation, loader, keyProvider, EObjectLoader.isDefaultFeature(eClass, operation), EObjectLoader.isConstructorFeature(eClass, operation));
	}
	
	/**
	 * Wraps {@link EStructuralFeature} into {@link Feature}. 
	 * Static version is needed for {@link EMap} support. 
	 * @param featureKey
	 * @param feature
	 * @return
	 */
	public Feature<?> wrapOperation(
			String operationKey, 
			EOperation operation, 
			EObjectLoader loader, 
			BiFunction<EClass,ENamedElement,String> keyProvider,
			boolean isDefault,
			boolean isConstructor) {
		
		Object[] exclusiveWith = EObjectLoader.getExclusiveWith(eClass, operation, keyProvider);		
		EModelElementDocumentation documentation = EmfUtil.getDocumentation(operation);
		
		ParameterMap parameterMap = new ParameterMap(
				operationKey, 
				isDefault, 
				isConstructor,
				false, // TODO - annotation for operations which are required to be called during loading 
				null,
				documentation == null ? null : documentation.documentation(),
				eClass,
				operation,
				loader,
				keyProvider,
				exclusiveWith);
		
		return new MapSupplierFactoryAttribute<>(parameterMap, true);
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
			public EObject execute(Map<Object, Object> data, ProgressMonitor progressMonitor) {
				EObject ret = constructor.apply(eClass, progressMonitor);
				if (ret == null) {
					ret = instantiate(eClass, progressMonitor);
				}
				List<? extends Marker> markers = new ArrayList<>(getMarkers());
				loader.mark(ret, markers, progressMonitor);
				Map<ETypedElement, Object> loadedFeatures = new HashMap<>();
				ETypedElement[] loadingFeature = { null };
				ret.eAdapters().add(new LoadTrackerAdapter() {

					@Override
					public boolean isLoaded(ETypedElement feature) {
						return loadedFeatures.containsKey(feature);
					}

					@Override
					public boolean isLoading(ETypedElement feature) {
						return feature == loadingFeature[0];
					}
					
					@Override
					public Object get(ETypedElement feature) {
						return loadedFeatures.get(feature);
					}
					
				});
				for (Feature<?> feature: features) {
					if (feature.isLoaded()) {
						try {
							ETypedElement featureTarget = featureMap.get(feature.getKey());
							loadingFeature[0] = featureTarget;
							Object value = feature.get(data);
							if (featureTarget instanceof EStructuralFeature) {
								EStructuralFeature structuralFeature = (EStructuralFeature) featureTarget;
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
							} else {
								EOperation operation = (EOperation) featureTarget;
								EList<Object> arguments = ECollections.newBasicEList();
								for (EParameter parameter: operation.getEParameters()) {
									Object pValue = ((Map<String,Object>) value).get(parameter.getName());
									if (parameter.isMany()) {
										if (pValue instanceof Iterable) {
											pValue = ECollections.newBasicEList((Iterable<?>) pValue);
										} else {
											pValue = ECollections.newBasicEList(pValue);
										}
									}
									arguments.add(pValue);
								}
								invokeOperation(ret, operation, arguments); 
							}
							loadedFeatures.put(featureTarget, value);
						} catch (ConfigurationException e) {
							throw e;
						} catch (Exception e) {
							throw markers == null || markers.isEmpty() ? new ExecutionException(e) : new ConfigurationException("Error setting feature " + feature.getKey() + " in " + ret + ": " + e, e, markers);
						} finally {
							loadingFeature[0] = null;
						}
					}
				}
				return ret;
			}
		};
	}
	
	protected EObject instantiate(EClass eClazz, ProgressMonitor progressMonitor) {
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
	
	/**
	 * Sets structural feature. This implementation calls eObj.eSet().  
	 * @param eObj
	 * @param structuralFeature
	 * @param value
	 * @throws InvocationTargetException 
	 */
	protected void invokeOperation(EObject eObj, EOperation operation, EList<Object> arguments) throws InvocationTargetException {
		eObj.eInvoke(operation, arguments);
	}	
	
}
