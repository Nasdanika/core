package org.nasdanika.emf.persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.persistence.MapAttribute;
import org.nasdanika.persistence.Marker;
import org.nasdanika.persistence.ObjectLoader;

/**
 * Loads a map of parameters.
 * @author Pavel
 *
 */
public class ParameterMap extends MapAttribute<String,Object> {

	private record ParameterRecord(EParameter parameter, TypedElementFactory factory) {}
	
	private Map<String,ParameterRecord> parameterRecords = new LinkedHashMap<>();
	
	/**
	 * @param key
	 * @param isDefault
	 * @param required
	 * @param defaultValue
	 * @param description
	 * @param resolver
	 * @param referenceType Reference type if the reference is homogenous, i.e. its type is known beforehand.
	 * @param exclusiveWith
	 */
	public ParameterMap(
			Object key, 
			boolean isDefault, 
			boolean isConstructor, 
			boolean isRequired, 
			Map<String,Object> defaultValue,
			String description, 
			EClass eClass,
			EOperation eOperation,
			EObjectLoader resolver,
			BiFunction<EClass,ENamedElement,String> keyProvider,
			Object... exclusiveWith) {
		super(key, isDefault, isConstructor, isRequired, defaultValue, description, exclusiveWith);
		for (EParameter eParameter: eOperation.getEParameters()) {
			if (eParameter.getEType() instanceof EClass) {
				parameterRecords.put(keyProvider.apply(eClass, eParameter), new ParameterRecord(eParameter, new TypedElementFactory(eClass, eParameter, eParameter.getName(), resolver, true, keyProvider)));
			}
		}
	}
	
	@Override
	protected Object createValue(ObjectLoader loader, String key, Object value, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		ParameterRecord parameterRecord = parameterRecords.get(key);
		if (parameterRecord == null) {
			return super.createValue(loader, key, value, base, progressMonitor, markers);
		}
		
		List<?> result = parameterRecord.factory().create(loader, value, base, progressMonitor, markers);
		if (result.size() == 0) {
			return null;
		}
		
		if (result.size() == 1) {
			return result.get(0);
		}
		throw new NasdanikaException("Expected result size of 0 or 1, got " + result.size() + ":" + result);		
	}
	
}

// --- Value conversion based on EObject or not, single or many, ...

//// Unwrapping singleton lists
//if (structuralFeature instanceof EReference) {
//	if (structuralFeature.isMany()) {
//		if (value instanceof List) {
//			List<Object> theValue = new ArrayList<>();
//			for (Object element: (List<?>) value) {
//				if (element instanceof List) {
//					theValue.addAll((List<Object>) element);
//				} else {
//					theValue.add(element);
//				}												
//			}
//			setFeature(ret, structuralFeature, theValue);
//		} else if (value instanceof Map) {
//			EClass refType = ((EReference) structuralFeature).getEReferenceType();
//			boolean isManyValue = false;
//			Class<?> refTypeInstanceClass = refType.getInstanceClass();
//			if (refTypeInstanceClass != null && Map.Entry.class.isAssignableFrom(refTypeInstanceClass)) {
//				EStructuralFeature valueFeature = refType.getEStructuralFeature("value");
//				isManyValue = valueFeature != null && valueFeature.isMany();
//			}
//			Map<Object,Object> theValue = new LinkedHashMap<>();
//			for (Entry<Object, Object> entry: ((Map<Object,Object>) value).entrySet()) {
//				Object entryValue = entry.getValue();
//				if (entryValue instanceof List) {
//					List<?> entryValueList = (List<?>) entryValue;
//					if (isManyValue) {
//						List<Object> theEntryValueList = new ArrayList<>();
//						for (Object entryValueElement: (List<?>) entryValueList) {
//							if (entryValueElement instanceof List) {
//								theEntryValueList.addAll((List<Object>) entryValueElement);
//							} else {
//								theEntryValueList.add(entryValueElement);
//							}												
//						}	
//						theValue.put(entry.getKey(), theEntryValueList);
//					} else {
//						if (entryValueList.size() == 1) {
//							theValue.put(entry.getKey(), entryValueList.get(0));														
//						} else if (!entryValueList.isEmpty()) {
//							throw new ConfigurationException("Map entry value list size is more than one: "+ entryValue, markers);
//						}
//					}
//				} else {
//					theValue.put(entry.getKey(), entryValue);
//				}												
//			}
//			setFeature(ret, structuralFeature, theValue);											
//		} else {
//			setFeature(ret, structuralFeature, value);											
//		}
//	} else {
//		if (value instanceof List && ((List<?>) value).size() == 1) {
//			setFeature(ret, structuralFeature, ((List<?>) value).get(0));
//		} else {
//			setFeature(ret, structuralFeature, value);
//		}
//	}
//} else {
//	setFeature(ret, structuralFeature, value);
//}
