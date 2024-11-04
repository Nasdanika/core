package org.nasdanika.mapping;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;
import org.nasdanika.common.Util;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.persistence.ConfigurationException;

/**
 * Loads feature mapping configuration from properties
 * @param <S>
 * @param <T>
 */
public abstract class PropertySetterFeatureMapperBase<S, T extends EObject> extends SetterFeatureMapper<S, T> {
	
	private static final String MAPPING_SELECTOR_REF_PROPERTY = "mapping-selector-ref";
	private static final String MAPPING_SELECTOR_PROPERTY = "mapping-selector";
	private static final String FEATURE_MAP_REF_PROPERTY = "feature-map-ref";
	private static final String FEATURE_MAP_PROPERTY = "feature-map";

	protected PropertySetterFeatureMapperBase() {
		super();
	}

	protected PropertySetterFeatureMapperBase(Mapper<S, T> chain) {
		super(chain);
	}

	protected PropertySetterFeatureMapperBase(Mapper<S, T> chain, FeatureMapper<S, T> defaulFeaturetMapper) {
		super(chain, defaulFeaturetMapper);
	}

	protected String getPropertyNamespace() {
		return "";
	}
	
	protected String getFeatureMapConfigPropertyName() {
		return getPropertyNamespace() + FEATURE_MAP_PROPERTY;
	}
	
	protected String getFeatureMapConfigRefPropertyName() {
		return getPropertyNamespace() + FEATURE_MAP_REF_PROPERTY;
	}
	
	/**
	 * Returns eObject property. This implementation uses ModelElement.getProperties().get() for instances of model element. 
	 * Returns null otherwise.
	 * @param eObj
	 * @return
	 */
	protected abstract String getProperty(S eObj, String property);
	
	@Override
	protected String getFeatureMapConfigStr(S source) {
		String fmcpn = getFeatureMapConfigPropertyName();
		if (!Util.isBlank(fmcpn)) {
			String fmc = getProperty(source, fmcpn);
			if (!Util.isBlank(fmc)) {
				return fmc;
			}
		}
		
		String fmcrpn = getFeatureMapConfigRefPropertyName();
		if (!Util.isBlank(fmcrpn)) {
			String ref = getProperty(source, fmcrpn);
			if (!Util.isBlank(ref)) {
				URI refURI = URI.createURI(ref);
				URI baseURI = getBaseURI(source);
				if (baseURI != null && !baseURI.isRelative()) {
					refURI = refURI.resolve(baseURI);
				}
				try {
					DefaultConverter converter = DefaultConverter.INSTANCE;
					Reader reader = converter.toReader(refURI);
					return converter.toString(reader);
				} catch (IOException e) {
					throw new ConfigurationException("Error loading feature map from " + refURI, e, asMarked(source));
				}
			}
		}
		return null;
	}
		
	protected String getMappingSelectorPropertyName() {
		return getPropertyNamespace() + MAPPING_SELECTOR_PROPERTY;
	}
	
	protected String getMappingSelectorRefPropertyName() {
		return getPropertyNamespace() + MAPPING_SELECTOR_REF_PROPERTY;
	}
	
	protected String getMappingSelectorStr(S source) {
		String mspn = getMappingSelectorPropertyName();
		if (!Util.isBlank(mspn)) {
			String ms = getProperty(source, mspn);
			if (!Util.isBlank(ms)) {
				return ms;
			}
		}
		
		String msrpn = getMappingSelectorRefPropertyName();
		if (!Util.isBlank(msrpn)) {
			String ref = getProperty(source, msrpn);
			if (!Util.isBlank(ref)) {
				URI refURI = URI.createURI(ref);
				URI baseURI = getBaseURI(source);
				if (baseURI != null && !baseURI.isRelative()) {
					refURI = refURI.resolve(baseURI);
				}
				try {
					DefaultConverter converter = DefaultConverter.INSTANCE;
					Reader reader = converter.toReader(refURI);
					return converter.toString(reader);
				} catch (IOException e) {
					throw new ConfigurationException("Error loading mapping selector from " + refURI, e, asMarked(source));
				}
			}
		}
		return null;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public Iterable<T> select(
			S source, 
			Map<S, T> registry, 
			ProgressMonitor progressMonitor) {

		String mappingSelector = getMappingSelectorStr(source);
		if (Util.isBlank(mappingSelector)) {
			return super.select(source, registry, progressMonitor);
		}
						
		Collection<T> ret = new ArrayList<>();
		try {
			Yaml yaml = new Yaml();
			Object mappingObj = yaml.load(mappingSelector);
			if (mappingObj instanceof String) {
				Object result = evaluate(
						source, 
						(String) mappingObj, 
						Collections.singletonMap("registry", registry), 
						Object.class, 
						source);
				if (result instanceof EObject) {
					ret.add((T) result);
				} else if (result instanceof Iterable) {
					((Iterable<T>) result).forEach(ret::add);
				} else {
					throw new ConfigurationException("Usupported result type: " + result + " for mapping selector " + mappingSelector, null, asMarked(source));					
				}
			} else if (mappingObj instanceof Iterable) {
				for (String mappingElement: (Iterable<String>) mappingObj) {
					Object result = evaluate(
							source, 
							mappingElement, 
							Collections.singletonMap("registry", registry), 
							Object.class, 
							source);
					if (result instanceof EObject) {
						ret.add((T) result);
					} else if (result instanceof Iterable) {
						((Iterable<T>) result).forEach(ret::add);
					} else {
						throw new ConfigurationException("Usupported result type: " + result + " for mapping element " + mappingElement, null, asMarked(source));					
					}					
				}				
			} else {			
				throw new ConfigurationException("Usupported configuration type: " + mappingObj, null, asMarked(source));
			}
		} catch (YAMLException yamlException) {
			throw new ConfigurationException(null, yamlException, asMarked(source));
		}
		return ret;		
	}	

}
