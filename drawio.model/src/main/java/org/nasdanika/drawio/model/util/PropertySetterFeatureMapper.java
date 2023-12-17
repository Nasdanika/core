package org.nasdanika.drawio.model.util;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.FeatureMapper;
import org.nasdanika.common.Mapper;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SetterFeatureMapper;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.model.Connection;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.drawio.model.comparators.LabelModelElementComparator;
import org.nasdanika.drawio.model.comparators.PropertyModelElementComparator;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;
import org.nasdanika.persistence.Marker;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

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
				String fmc = modelElement.getProperties().get(fmcpn);
				if (!Util.isBlank(fmc)) {
					return fmc;
				}
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
		
	protected String getMappingSelectorPropertyName() {
		return getPropertyNamespace() + "mapping-selector";
	}
	
	protected String getMappingSelectorRefPropertyName() {
		return getPropertyNamespace() + "mapping-selector-ref";
	}
	
	protected String getMappingSelectorStr(EObject source) {
		if (source instanceof ModelElement) {
			String mspn = getMappingSelectorPropertyName();
			ModelElement modelElement = (ModelElement) source;
			if (!Util.isBlank(mspn)) {
				String ms = modelElement.getProperties().get(mspn);
				if (!Util.isBlank(ms)) {
					return ms;
				}
			}
			
			String msrpn = getMappingSelectorRefPropertyName();
			if (!Util.isBlank(msrpn)) {
				String ref = modelElement.getProperties().get(msrpn);
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
						throwConfigurationException("Error loading mapping selector from " + refURI, e, source);
					}
				}
			}
		}
		return null;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	protected Iterable<T> select(
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
				Object result = evaluate(source, (String) mappingObj, null, Object.class, source);
				if (result instanceof EObject) {
					ret.add((T) result);
				} else if (result instanceof Iterable) {
					((Iterable<T>) result).forEach(ret::add);
				} else {
					throwConfigurationException("Usupported result type: " + result + " for mapping selector " + mappingSelector, null, source);					
				}
			} else if (mappingObj instanceof Iterable) {
				for (String mappingElement: (Iterable<String>) mappingObj) {
					Object result = evaluate(source, mappingElement, null, Object.class, source);
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
	
	private S getSource(Object target, Map<S, T> registry) {
		if (target == null) {
			return null;
		}
		for (Entry<S, T> re: registry.entrySet()) {
			if (Objects.equals(target, re.getValue())) {
				return re.getKey();
			}
		}
		return null;
	}

	/**
	 * <UL>
	 * <LI>label</LI>
	 * <LI>property</LI>
	 * <LI>clockwise</LI>
	 * <LI>counterclockwise</LI>
	 * <LI>right-down</LI>
	 * <LI>right-up</LI>
	 * <LI>left-down</LI>
	 * <LI>left-up</LI>
	 * <LI>down-right</LI>
	 * <LI>down-left</LI>
	 * <LI>up-right</LI>
	 * <LI>up-left</LI>
	 * </UI>
	 */	
	@Override
	protected Comparator<Object> createComparator(Object comparatorConfig, Map<S, T> registry, EObject context) {
		if ("label".equals(comparatorConfig)) {
			LabelModelElementComparator labelComparator = new LabelModelElementComparator(false);
			return (a, b) -> {
				S as = getSource(a, registry); 
				S bs = getSource(b, registry); 
				
				return as instanceof org.nasdanika.drawio.model.ModelElement && bs instanceof org.nasdanika.drawio.model.ModelElement ? labelComparator.compare((org.nasdanika.drawio.model.ModelElement) as, (org.nasdanika.drawio.model.ModelElement) bs) : NATURAL_COMPARATOR.compare(as,bs);				
			};
		} 
		if ("label-descending".equals(comparatorConfig)) {
			LabelModelElementComparator labelComparator = new LabelModelElementComparator(true);
			return (a, b) -> {
				S as = getSource(a, registry); 
				S bs = getSource(b, registry); 
				
				return as instanceof org.nasdanika.drawio.model.ModelElement && bs instanceof org.nasdanika.drawio.model.ModelElement ? labelComparator.compare((org.nasdanika.drawio.model.ModelElement) as, (org.nasdanika.drawio.model.ModelElement) bs) : NATURAL_COMPARATOR.compare(as,bs);				
			};
		} 
		
		// TODO cartesian, angular with the container as base and default angle
		
		if (comparatorConfig instanceof Map) {
			Map<?, ?> comparatorConfigMap = (Map<?,?>) comparatorConfig;
			if (comparatorConfigMap.size() == 1) {
				for (Entry<?, ?> cce: comparatorConfigMap.entrySet()) {
					if ("property".equals(cce.getKey()) && cce.getValue() instanceof String) {
						PropertyModelElementComparator propertyComparator = new PropertyModelElementComparator((String) cce.getValue(),  false);
						return (a, b) -> {
							S as = getSource(a, registry); 
							S bs = getSource(b, registry); 
							
							return as instanceof org.nasdanika.drawio.model.ModelElement && bs instanceof org.nasdanika.drawio.model.ModelElement ? propertyComparator.compare((org.nasdanika.drawio.model.ModelElement) as, (org.nasdanika.drawio.model.ModelElement) bs) : NATURAL_COMPARATOR.compare(as,bs);				
						};
					} 
					if ("property-descending".equals(cce.getKey()) && cce.getValue() instanceof String) {
						PropertyModelElementComparator propertyComparator = new PropertyModelElementComparator((String) cce.getValue(),  true);
						return (a, b) -> {
							S as = getSource(a, registry); 
							S bs = getSource(b, registry); 
							
							return as instanceof org.nasdanika.drawio.model.ModelElement && bs instanceof org.nasdanika.drawio.model.ModelElement ? propertyComparator.compare((org.nasdanika.drawio.model.ModelElement) as, (org.nasdanika.drawio.model.ModelElement) bs) : NATURAL_COMPARATOR.compare(as,bs);				
						};
					} 
					// TODO - angular
				}
			}
		}
		
		
		
		// TODO Auto-generated method stub
		return super.createComparator(comparatorConfig, registry, context);
	}

}
