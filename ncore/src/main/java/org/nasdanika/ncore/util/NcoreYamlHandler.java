package org.nasdanika.ncore.util;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.DefaultConverter;

/**
 * Loads YAML to Ncore Map, List and scalars
 */
public class NcoreYamlHandler implements YamlHandler {

	@SuppressWarnings("unchecked")
	@Override
	public Optional<EObject> load(YamlResource resource, Object obj) {
		if (obj == null) {
			return null;
		}
		
		if (obj instanceof Map) {
			return Optional.of(org.nasdanika.ncore.Map.wrap((Map<Object,Object>) obj));
		} 
		
		if (obj instanceof Iterable<?>) {
			return Optional.of(org.nasdanika.ncore.List.wrap((Iterable<?>) obj));
		}
		
		if (obj instanceof Date) {
			return Optional.of(org.nasdanika.ncore.Date.wrap((Date) obj));
		}
				
		if (obj instanceof Double) {
			return Optional.of(org.nasdanika.ncore.Double.wrap((Double) obj));
		}
		
		if (obj instanceof Integer) {
			return Optional.of(org.nasdanika.ncore.Integer.wrap((Integer) obj));
		}
		
		if (obj instanceof Long) {
			return Optional.of(org.nasdanika.ncore.Long.wrap((Long) obj));
		}
				
		if (obj instanceof String) {
			return Optional.of(org.nasdanika.ncore.String.wrap((String) obj));
		}
		
		return Optional.of(org.nasdanika.ncore.String.wrap(DefaultConverter.INSTANCE.toString(obj)));
	}

	@Override
	public Optional<Object> save(YamlResource resource, EObject obj) {
		// TODO 
		throw new UnsupportedOperationException();
	}

}
