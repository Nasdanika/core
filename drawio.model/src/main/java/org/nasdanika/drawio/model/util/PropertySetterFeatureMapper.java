package org.nasdanika.drawio.model.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.model.Connection;
import org.nasdanika.drawio.model.Node;
import org.nasdanika.drawio.model.Page;
import org.nasdanika.drawio.model.Tag;
import org.nasdanika.drawio.model.comparators.AngularNodeComparator;
import org.nasdanika.drawio.model.comparators.CartesianNodeComparator;
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
	
	@SafeVarargs
	private S getSource(Object target, Map<S, T> registry, Predicate<S>... predicates) {
		if (target == null) {
			return null;
		}
		Z: for (Entry<S, T> re: registry.entrySet()) {
			if (Objects.equals(target, re.getValue())) {
				S result = re.getKey();
				for (Predicate<S> predicate: predicates) {
					if (!predicate.test(result)) {
						continue Z;
					}
				}
				return result;
			}
		}
		return null;
	}
		
	private enum Comparators {
		
		label("label"),
		labelDescending("label-descending"),
		
		property("property"),
		propertyDescending("property-descending"),
		
		clockwise("clockwise"),
		counterclockwise("counterclockwise"),

		rightDown("right-down"),
		rightUp("rightUp"),
		
		leftDown("left-down"),
		leftUp("left-up"),
		
		downRight("down-right"),
		downLeft("down-left"),
		
		upRight("up-right"),
		upLeft("up-left");
		
		Comparators(String key) {
			this.key = key;
		}
		public final String key;
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
	protected Comparator<Object> createComparator(
			EObject semanticElement,
			EStructuralFeature feature,
			Object comparatorConfig, 
			Map<S, T> registry, 
			EObject context) {
		if (Comparators.label.key.equals(comparatorConfig)) {
			return adapt(new LabelModelElementComparator(false), registry);
		} 
		if (Comparators.labelDescending.key.equals(comparatorConfig)) {
			return adapt(new LabelModelElementComparator(true), registry);
		} 
	
		if (Comparators.clockwise.key.equals(comparatorConfig)) {
			Node base = (Node) getSource(semanticElement, registry, org.nasdanika.drawio.model.Node.class::isInstance);
			return adapt(new AngularNodeComparator(base, true, null), registry);
		}
		
		if (Comparators.counterclockwise.key.equals(comparatorConfig)) {
			Node base = (Node) getSource(semanticElement, registry, org.nasdanika.drawio.model.Node.class::isInstance);
			return adapt(new AngularNodeComparator(base, false, null), registry);			
		}
		
		for (CartesianNodeComparator.Direction direction: CartesianNodeComparator.Direction.values()) {
			if (Comparators.valueOf(direction.name()).key.equals(comparatorConfig)) {
				return adapt(new CartesianNodeComparator(direction), registry);
			}			
		}		
		
		if (comparatorConfig instanceof Map) {
			Map<?, ?> comparatorConfigMap = (Map<?,?>) comparatorConfig;
			if (comparatorConfigMap.size() == 1) {
				for (Entry<?, ?> cce: comparatorConfigMap.entrySet()) {
					if (Comparators.property.key.equals(cce.getKey()) && cce.getValue() instanceof String) {
						return adapt(new PropertyModelElementComparator((String) cce.getValue(),  false), registry);
					} 
					if (Comparators.propertyDescending.key.equals(cce.getKey()) && cce.getValue() instanceof String) {
						return adapt(new PropertyModelElementComparator((String) cce.getValue(),  true), registry);
					}
					
					if (Comparators.clockwise.key.equals(cce.getKey())) {
						Node base = (Node) getSource(semanticElement, registry, org.nasdanika.drawio.model.Node.class::isInstance);
						return adapt(new AngularNodeComparator(base, true, getAngle(cce.getValue(), base, context)), registry);
					}
					
					if (Comparators.counterclockwise.key.equals(cce.getKey())) {
						Node base = (Node) getSource(semanticElement, registry, org.nasdanika.drawio.model.Node.class::isInstance);
						return adapt(new AngularNodeComparator(base, false, getAngle(cce.getValue(), base, context)), registry);			
					}
				}
			}
		}
		
		return super.createComparator(semanticElement, feature, comparatorConfig, registry, context);
	}
	
	protected Double getAngle(Object value, Node base, EObject context) {
		if (value == null) {
			return null;
		}
		if (value instanceof Number) {
			return ((Number) value).doubleValue();
		}
		if (value instanceof String) {
			Object result = evaluate(base, (String) value, null, Object.class, context);
			if (result instanceof Number) {
				return ((Number) result).doubleValue();
			}
			if (result instanceof Node) {
				return AngularNodeComparator.angle(base, (Node) result);
			}
			throwConfigurationException("Unsupported angle expression result type: " + result + " for expression " + value, null, context);
		}

		throwConfigurationException("Unsupported angle value type: " + value, null, context);
		return null;
	}

	@SuppressWarnings("unchecked")
	protected <CT> Comparator<Object> adapt(Comparator<CT> comparator, Map<S, T> registry) {
		return (a, b) -> {
			S as = getSource(a, registry); 
			S bs = getSource(b, registry); 
			
			return as instanceof org.nasdanika.drawio.model.ModelElement && bs instanceof org.nasdanika.drawio.model.ModelElement ? comparator.compare((CT) as, (CT) bs) : NATURAL_COMPARATOR.compare(as,bs);				
		};
	}

}
