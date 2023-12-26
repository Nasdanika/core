package org.nasdanika.drawio.model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.BiPredicate;
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
import org.nasdanika.drawio.model.comparators.FlowNodeComparator;
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
	private Collection<S> getSources(Object target, Map<S, T> registry, Predicate<S>... predicates) {
		Collection<S> result = new ArrayList<>();
		if (target != null) {
			Z: for (Entry<S, T> re: registry.entrySet()) {
				if (Objects.equals(target, re.getValue())) {
					S source = re.getKey();
					for (Predicate<S> predicate: predicates) {
						if (!predicate.test(source)) {
							continue Z;
						}
					}
					result.add(source);
				}
			}
		}
		return result;
	}
		
	private enum Comparators {
		
		label("label"),
		labelDescending("label-descending"),
		
		property("property"),
		propertyDescending("property-descending"),
		
		clockwise("clockwise"),
		counterclockwise("counterclockwise"),
		
		flow("flow"),
		reverseFlow("reverse-flow"),

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
	
	protected boolean areSamePageNodes(S a, S b) {
		return a instanceof org.nasdanika.drawio.model.Node
				&& b instanceof org.nasdanika.drawio.model.Node
				&& Objects.equals(((org.nasdanika.drawio.model.Node) a).getPage(), ((org.nasdanika.drawio.model.Node) b).getPage());	
	}
	
	protected boolean areModelElements(S a, S b) {
		return a instanceof org.nasdanika.drawio.model.ModelElement && b instanceof org.nasdanika.drawio.model.ModelElement;
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
			return adapt(
					new LabelModelElementComparator(), 
					this::areModelElements, 
					registry);
		} 
		if (Comparators.labelDescending.key.equals(comparatorConfig)) {
			return adapt(
					new LabelModelElementComparator().reversed(), 
					this::areModelElements, 
					registry);
		} 
	
		if (Comparators.clockwise.key.equals(comparatorConfig)) {
			return createAngularComparator(semanticElement, feature, null, registry, context, NATURAL_COMPARATOR).reversed();
		}
		
		if (Comparators.counterclockwise.key.equals(comparatorConfig)) {
			return createAngularComparator(semanticElement, feature, null, registry, context, NATURAL_COMPARATOR);
		}
		
		for (CartesianNodeComparator.Direction direction: CartesianNodeComparator.Direction.values()) {
			if (Comparators.valueOf(direction.name()).key.equals(comparatorConfig)) {
				return adapt(
						new CartesianNodeComparator(direction, NATURAL_COMPARATOR), 
						this::areSamePageNodes,
						registry);
			}			
		}		
		
		if (comparatorConfig instanceof Map) {
			Map<?, ?> comparatorConfigMap = (Map<?,?>) comparatorConfig;
			if (comparatorConfigMap.size() == 1) {
				for (Entry<?, ?> cce: comparatorConfigMap.entrySet()) {
					if (Comparators.property.key.equals(cce.getKey()) && cce.getValue() instanceof String) {
						return adapt(
								new PropertyModelElementComparator((String) cce.getValue()),
								this::areModelElements, 
								registry);
					} 
					if (Comparators.propertyDescending.key.equals(cce.getKey()) && cce.getValue() instanceof String) {
						return adapt(
								new PropertyModelElementComparator((String) cce.getValue()).reversed(),
								this::areModelElements, 
								registry);
					}
					
					if (Comparators.clockwise.key.equals(cce.getKey())) {
						return createAngularComparator(semanticElement, feature, cce.getValue(), registry, context, NATURAL_COMPARATOR).reversed();
					}
					
					if (Comparators.counterclockwise.key.equals(cce.getKey())) {
						return createAngularComparator(semanticElement, feature, cce.getValue(), registry, context, NATURAL_COMPARATOR);
					}
					
					if (Comparators.flow.key.equals(cce.getKey())) {
						Comparator<Object> fallback = createComparator(semanticElement, feature, cce.getValue(), registry, context);						
						return adapt(
								new FlowNodeComparator(fallback),
								this::areSamePageNodes,
								registry);
					} 
					if (Comparators.reverseFlow.key.equals(cce.getKey())) {
						Comparator<Object> fallback = createComparator(semanticElement, feature, cce.getValue(), registry, context);						
						return adapt(
								new FlowNodeComparator(fallback),
								this::areSamePageNodes,
								registry).reversed();
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
	
	protected record JoinRecord<S>(S o1, S o2) {}

	protected Collection<JoinRecord<S>> join(
			Object ase, 
			Object bse,
			BiPredicate<? super S, ? super S> predicate,
			Map<S,T> registry) {
		
		Collection<S> as = new ArrayList<>(getSources(ase, registry));
		as.add(null); // outer join
		
		Collection<S> bs = new ArrayList<>(getSources(bse, registry));
		bs.add(null); // outer join
		
		Collection<JoinRecord<S>> result = new ArrayList<>();
		
		for (S aSource: as) {
			for (S bSource: bs) {
				if (predicate.test(aSource, bSource)) {
					result.add(new JoinRecord<S>(aSource, bSource));
				}
			}
		}	
		
		return result;
	}
	
	/**
	 * 
	 * @param <CT>
	 * @param comparator
	 * @param registry
	 * @param type
	 * @return
	 */
	protected Comparator<Object> createAngularComparator(
			EObject semanticElement,
			EStructuralFeature feature,
			Object angleConfig, 
			Map<S, T> registry,			
			EObject context,
			Comparator<Object> fallback) {

		return (a, b) -> {		
			for (S base: getSources(semanticElement, registry, org.nasdanika.drawio.model.Node.class::isInstance)) {
				if (base instanceof org.nasdanika.drawio.model.Node) {
					org.nasdanika.drawio.model.Node baseNode = (org.nasdanika.drawio.model.Node) base;
					Double angle = getAngle(angleConfig, baseNode, context);
					if (angle == null || angle != Double.NaN) {
						AngularNodeComparator angularComparator = new AngularNodeComparator(baseNode, angle);
						for (JoinRecord<S> jr: join(a, b, (o1, o2) -> areSamePageNodes(o1, base) && areSamePageNodes(o1, o2), registry)) {
							return angularComparator.compare((org.nasdanika.drawio.model.Node) jr.o1(), (org.nasdanika.drawio.model.Node) jr.o2());				
						}
					}
				}
			}
			return fallback.compare(a, b);
		};
	}

	/**
	 * 
	 * @param <CT>
	 * @param comparator
	 * @param registry
	 * @param type
	 * @return
	 */
	protected <CT> Comparator<Object> adapt(
			Comparator<CT> comparator,
			BiPredicate<? super S, ? super S> predicate,			
			Map<S, T> registry) {
		return adapt(comparator, predicate, registry, NATURAL_COMPARATOR);
	}
	
	/**
	 * 
	 * @param <CT>
	 * @param comparator
	 * @param registry
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <CT> Comparator<Object> adapt(
			Comparator<CT> comparator,
			BiPredicate<? super S, ? super S> predicate,			
			Map<S, T> registry,
			Comparator<Object> fallback) {
		return (a, b) -> {
			for (JoinRecord<S> jr: join(a, b, predicate, registry)) {
				return comparator.compare((CT) jr.o1(), (CT) jr.o2());				
			}
			
			return fallback.compare(a,b);				
		};
	}

}
