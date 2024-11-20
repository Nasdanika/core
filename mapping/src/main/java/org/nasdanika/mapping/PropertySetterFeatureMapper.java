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
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.persistence.ConfigurationException;

/**
 * Loads feature mapping configuration from properties
 * @param <S>
 * @param <T>
 */
public abstract class PropertySetterFeatureMapper<S, T extends EObject> extends SetterFeatureMapper<S, T> {
	
	private static final String MAPPING_SELECTOR_REF_PROPERTY = "mapping-selector-ref";
	private static final String MAPPING_SELECTOR_PROPERTY = "mapping-selector";
	private static final String FEATURE_MAP_REF_PROPERTY = "features-ref";
	private static final String FEATURE_MAP_PROPERTY = "features";

	protected PropertySetterFeatureMapper(ContentProvider<S> contentProvider, CapabilityLoader capabilityLoader) {
		super(contentProvider, capabilityLoader);
	}

	protected PropertySetterFeatureMapper(Mapper<S, T> chain, ContentProvider<S> contentProvider, CapabilityLoader capabilityLoader) {
		super(chain, contentProvider, capabilityLoader);
	}

	protected PropertySetterFeatureMapper(
			Mapper<S, T> chain,
			ContentProvider<S> contentProvider, 
			CapabilityLoader capabilityLoader,
			FeatureMapper<S, T> defaulFeaturetMapper) {
		super(chain, contentProvider, capabilityLoader, defaulFeaturetMapper);
	}
	
	protected String getFeatureMapConfigPropertyName() {
		return FEATURE_MAP_PROPERTY;
	}
	
	protected String getFeatureMapConfigRefPropertyName() {
		return FEATURE_MAP_REF_PROPERTY;
	}
		
	@Override
	protected Object getFeatureMapConfig(S source) {
		String fmcpn = getFeatureMapConfigPropertyName();
		if (!Util.isBlank(fmcpn)) {
			Object fmc = getContentProvider().getProperty(source, fmcpn);
			if (fmc != null && !(fmc instanceof String && Util.isBlank((String) fmc))) {
				return fmc;
			}
		}
		
		String fmcrpn = getFeatureMapConfigRefPropertyName();
		if (!Util.isBlank(fmcrpn)) {
			Object ref = getContentProvider().getProperty(source, fmcrpn);
			URI refURI = null;
			if (ref instanceof URI) {
				refURI = (URI) ref;
			} else if (ref instanceof String && !Util.isBlank((String) ref)) {
				refURI = URI.createURI((String) ref);
			}
			
			if (refURI != null) {
				URI baseURI = getContentProvider().getBaseURI(source);
				if (baseURI != null && !baseURI.isRelative()) {
					refURI = refURI.resolve(baseURI);
				}
				try {
					DefaultConverter converter = DefaultConverter.INSTANCE;
					Reader reader = converter.toReader(refURI);
					return converter.toString(reader);
				} catch (IOException e) {
					throw new ConfigurationException("Error loading feature map from " + refURI, e, getContentProvider().asMarked(source));
				}
			}
		}
		return null;
	}
		
	protected String getMappingSelectorPropertyName() {
		return MAPPING_SELECTOR_PROPERTY;
	}
	
	protected String getMappingSelectorRefPropertyName() {
		return MAPPING_SELECTOR_REF_PROPERTY;
	}
	
	protected Object getMappingSelector(S source) {
		String mspn = getMappingSelectorPropertyName();
		if (!Util.isBlank(mspn)) {
			Object ms = getContentProvider().getProperty(source, mspn);
			if (ms != null) {
				return ms;
			}
		}
		
		String msrpn = getMappingSelectorRefPropertyName();
		if (!Util.isBlank(msrpn)) {
			Object ref = getContentProvider().getProperty(source, msrpn);
			URI refURI = null;
			if (ref instanceof URI) {
				refURI = (URI) ref;
			} else if (ref instanceof String && !Util.isBlank((String) ref)) {
				refURI = URI.createURI((String) ref);
				URI baseURI = getContentProvider().getBaseURI(source);
				if (baseURI != null && !baseURI.isRelative()) {
					refURI = refURI.resolve(baseURI);
				}
				try {
					DefaultConverter converter = DefaultConverter.INSTANCE;
					Reader reader = converter.toReader(refURI);
					return converter.toString(reader);
				} catch (IOException e) {
					throw new ConfigurationException("Error loading mapping selector from " + refURI, e, getContentProvider().asMarked(source));
				}
			}
		}
		return null;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> select(
			S source, 
			Map<S, T> registry, 
			ProgressMonitor progressMonitor) {

		Object mappingSelector = getMappingSelector(source);
		if (mappingSelector == null || (mappingSelector instanceof String &&  Util.isBlank((String) mappingSelector))) {
			return super.select(source, registry, progressMonitor);
		}
						
		Collection<T> ret = new ArrayList<>();
		try {
			Yaml yaml = new Yaml();
			Object mappingObj = mappingSelector instanceof String ?  yaml.load((String) mappingSelector) : mappingSelector;
			Map<String, Object> variables = Map.of("registry", registry, "progressMonitor", progressMonitor);
			if (mappingObj instanceof String) {
				Object result = evaluate(
						source, 
						(String) mappingObj, 
						variables, 
						Object.class, 
						source);
				if (result instanceof EObject) {
					ret.add((T) result);
				} else if (result instanceof Iterable) {
					((Iterable<T>) result).forEach(ret::add);
				} else {
					throw new ConfigurationException("Usupported result type: " + result + " for mapping selector " + mappingSelector, null, getContentProvider().asMarked(source));					
				}
			} else if (mappingObj instanceof Iterable) {
				for (String mappingElement: (Iterable<String>) mappingObj) {
					Object result = evaluate(
							source, 
							mappingElement, 
							variables, 
							Object.class, 
							source);
					if (result instanceof EObject) {
						ret.add((T) result);
					} else if (result instanceof Iterable) {
						((Iterable<T>) result).forEach(ret::add);
					} else {
						throw new ConfigurationException("Usupported result type: " + result + " for mapping element " + mappingElement, null, getContentProvider().asMarked(source));					
					}					
				}				
			} else {			
				throw new ConfigurationException("Usupported configuration type: " + mappingObj, null, getContentProvider().asMarked(source));
			}
		} catch (YAMLException yamlException) {
			throw new ConfigurationException(null, yamlException, getContentProvider().asMarked(source));
		}
		return ret;		
	}	

}
