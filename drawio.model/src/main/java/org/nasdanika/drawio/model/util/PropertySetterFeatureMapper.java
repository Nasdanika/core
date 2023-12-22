package org.nasdanika.drawio.model.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.model.Connection;
import org.nasdanika.drawio.model.Page;
import org.nasdanika.drawio.model.Tag;
import org.nasdanika.drawio.model.comparators.LabelModelElementComparator;
import org.nasdanika.drawio.model.comparators.PropertyModelElementComparator;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;
import org.nasdanika.persistence.Marker;

/**
 * Loads feature mapping configuration from properties
 * @param <S>
 * @param <T>
 */
public abstract class PropertySetterFeatureMapper<S extends EObject, T extends EObject> extends org.nasdanika.common.PropertySetterFeatureMapper<S, T> {
	
	
	@Override
	protected List<? extends EObject> contents(EObject eObject, Predicate<EObject> tracker) {
		if (eObject instanceof Tag) {
			return ((Tag) eObject).getElements();
		}
		if (eObject instanceof org.nasdanika.drawio.model.ModelElement) {
			Page linkedPage = ((org.nasdanika.drawio.model.ModelElement) eObject).getLinkedPage();
			if (linkedPage != null && tracker.test(linkedPage)) {
				List<EObject> ret = new ArrayList<>(super.contents(eObject, tracker));
				ret.add(linkedPage);
				return ret;
			}
		}
		return super.contents(eObject, tracker);
	}	
	
	/**
	 * Returns eObject property. This implementation uses ModelElement.getProperties().get() for instances of model element. 
	 * Returns null otherwise.
	 * @param eObj
	 * @return
	 */
	@Override
	protected String getProperty(EObject eObj, String property) {
		return eObj instanceof org.nasdanika.drawio.model.ModelElement ? ((org.nasdanika.drawio.model.ModelElement) eObj).getProperties().get(property) : null;
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
