package org.nasdanika.drawio.model.util;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.SetterFeatureMapper;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.model.Connection;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;
import org.nasdanika.persistence.Marker;

/**
 * Loads feature mapping configuration from properties
 * @param <S>
 * @param <T>
 */
public abstract class PropertySetterFeatureMapper<S extends EObject, T extends EObject> extends SetterFeatureMapper<S, T> {
	
	protected String getPropertyNamespace() {
		return "";
	}
	
	protected String getFeatureMapConfigPropertyName() {
		return getPropertyNamespace() + "feature-map";
	}
	
	protected String getFeatureMapConfigRefPropertyName() {
		return getPropertyNamespace() + "feature-map-ref";
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
	
	@Override
	protected void throwConfigurationException(String message, Throwable cause, EObject context) {
		if (cause == null) {
			if (context instanceof Marker) {
				throw new ConfigurationException(message, (Marker) context);			
			}
			if (context instanceof Marked) {
				throw new ConfigurationException(message, (Marked) context);			
			}
			
			throw new ConfigurationException(message);
		}
		if (Util.isBlank(message)) {
			if (context instanceof Marker) {
				throw new ConfigurationException(cause, (Marker) context);			
			}
			if (context instanceof Marked) {
				throw new ConfigurationException(cause, (Marked) context);			
			}
			
			throw new ConfigurationException(cause);
		}
		
		
		if (context instanceof Marker) {
			throw new ConfigurationException(message, cause, (Marker) context);			
		}
		if (context instanceof Marked) {
			throw new ConfigurationException(message, cause, (Marked) context);			
		}
		
		throw new ConfigurationException(message, cause);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected S getConnectionSource(S connection) {
		if (connection instanceof Connection) {
			return (S) ((Connection) connection).getSource();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected S getConnectionTarget(S connection) {
		if (connection instanceof Connection) {
			return (S) ((Connection) connection).getTarget();
		}
		return null;
	}

}
