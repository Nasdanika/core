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

/**
 * Loads feature mapping configuration from properties
 * @param <S>
 * @param <T>
 */
public abstract class PropertySetterFeatureMapperBase<S extends EObject, T extends EObject> extends SetterFeatureMapper<S, T> {
	
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
	
	/**
	 * Returns eObject property. This implementation uses ModelElement.getProperties().get() for instances of model element. 
	 * Returns null otherwise.
	 * @param eObj
	 * @return
	 */
	protected abstract String getProperty(EObject eObj, String property);
	
	@Override
	protected String getFeatureMapConfigStr(EObject source) {
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
					throwConfigurationException("Error loading feature map from " + refURI, e, source);
				}
			}
		}
		return null;
	}
		
	protected String getMappingSelectorPropertyName() {
		return getPropertyNamespace() + "mapping-selector";
	}
	
	protected String getMappingSelectorRefPropertyName() {
		return getPropertyNamespace() + "mapping-selector-ref";
	}
	
	protected String getMappingSelectorStr(EObject source) {
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
					throwConfigurationException("Error loading mapping selector from " + refURI, e, source);
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
					throwConfigurationException("Usupported result type: " + result + " for mapping selector " + mappingSelector, null, source);					
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
						throwConfigurationException("Usupported result type: " + result + " for mapping element " + mappingElement, null, source);					
					}					
				}				
			} else {			
				throwConfigurationException("Usupported configuration type: " + mappingObj, null, source);
			}
		} catch (YAMLException yamlException) {
			throwConfigurationException(null, yamlException, source);
		}
		return ret;		
	}	

}
