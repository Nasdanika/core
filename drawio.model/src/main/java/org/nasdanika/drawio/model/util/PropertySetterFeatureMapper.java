package org.nasdanika.drawio.model.util;

import java.io.IOException;
import java.io.Reader;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.FeatureMapper;
import org.nasdanika.common.Mapper;
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
	
	protected PropertySetterFeatureMapper() {
		super();
	}

	protected PropertySetterFeatureMapper(Mapper<S, T> chain) {
		super(chain);
	}

	protected PropertySetterFeatureMapper(Mapper<S, T> chain, FeatureMapper<S, T> defaulFeaturetMapper) {
		super(chain, defaulFeaturetMapper);
	}

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
			ModelElement modelElement = (ModelElement) source;
			if (!Util.isBlank(fmcpn)) {
				return modelElement.getProperties().get(fmcpn);
			}
			
			String fmcrpn = getFeatureMapConfigRefPropertyName();
			if (!Util.isBlank(fmcrpn)) {
				String ref = modelElement.getProperties().get(fmcrpn);
				if (!Util.isBlank(ref)) {
					URI refURI = URI.createURI(ref);
					URI baseURI = getBaseURI(modelElement);
					if (baseURI != null && !baseURI.isRelative()) {
						refURI = refURI.resolve(baseURI);
					}
					try {
						DefaultConverter converter = DefaultConverter.INSTANCE;
						Reader reader = converter.toReader(refURI);
						return converter.toString(reader);
					} catch (IOException e) {
						throwConfigurationException("Error loading feature map from " + refURI, e, source);
					}
				}
			}
		}
		return null;
	}
	
	protected abstract URI getBaseURI(ModelElement source);
	
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
