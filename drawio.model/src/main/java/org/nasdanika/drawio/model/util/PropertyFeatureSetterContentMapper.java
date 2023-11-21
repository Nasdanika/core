package org.nasdanika.drawio.model.util;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.FeatureSetterContentMapper;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.model.ModelElement;


/**
 * Loads configuration from properties
 * @param <S>
 * @param <T>
 */
public class PropertyFeatureSetterContentMapper<S extends EObject, T extends EObject> extends FeatureSetterContentMapper<S, T> {
	
	protected String getFeatureMapConfigPropertyName() {
		return "feature-map";
	}
	
	protected String getFeatureMapConfigRefPropertyName() {
		return "feature-map-ref";
	}
	
	@Override
	protected String getFeatureMapConfigStr(EObject source) {
		if (source instanceof ModelElement) {
			String fmcpn = getFeatureMapConfigPropertyName();
			if (!Util.isBlank(fmcpn)) {
				return ((ModelElement) source).getProperties().get(fmcpn);
			}
			
			// TODO - config ref
		}
		return null;
	}

}
