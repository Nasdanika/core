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
	 * @param referenceType Reference type if the reference is homogeneous, i.e. its type is known beforehand.
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
	public Map<String, Object> create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		// Single parameter unqualified 
		if (parameterRecords.size() == 1) {
			return super.create(loader, Map.of(parameterRecords.keySet().iterator().next(), config), base, progressMonitor, markers); 
		}
		return super.create(loader, config, base, progressMonitor, markers);
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
