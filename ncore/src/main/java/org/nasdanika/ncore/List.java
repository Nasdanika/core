/**
 */
package org.nasdanika.ncore;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.persistence.MarkedArrayList;
import org.nasdanika.persistence.Marker;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A list of objects.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.List#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getList()
 * @model
 * @generated
 */
public interface List extends Marked {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * List elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getList_Value()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika reference-type='map: Map\nlist: List\nstring: String'"
	 * @generated
	 */
	EList<EObject> getValue();
	
	default void add(boolean value) {
		getValue().add(Boolean.wrap(value));
	}
	
	default void add(int value) {
		getValue().add(Integer.wrap(value));
	}
	
	default void add(java.lang.String value) {
		getValue().add(String.wrap(value));
	}
	
	default void add(double value) {
		getValue().add(Double.wrap(value));
	}
	
	default void add(long value) {
		getValue().add(Long.wrap(value));
	}
	
	default void add(java.util.Date value) {
		getValue().add(Date.wrap(value));
	}
	
	default void add(EObject value) {
		getValue().add(value);
	}
	
	default void add(java.util.Map<Object,Object> map) {
		getValue().add(Map.wrap(map));
	}
	
	default void add(Iterable<?> iterable) {
		getValue().add(List.wrap(iterable));
	}
	
	static List wrap(Iterable<?> iterable) {
		List ret = NcoreFactory.eINSTANCE.createList();
		ret.mark((Object) iterable);
		for (Object element: iterable) {
			if (element instanceof EObject) {
				EObject value = (EObject) element;
				if (value instanceof Marked) {
					if (iterable instanceof MarkedArrayList) {
						java.util.List<? extends Marker> elementMarkers = ((MarkedArrayList<?>) iterable).getElementMarkers().get(ret.getValue().size());
						((Marked) value).mark(elementMarkers);
					}
				}
				ret.add(value);
			} else if (element instanceof Iterable) {
				ret.add(wrap((Iterable<?>) element));
			} else if (element instanceof java.util.Map) {
				ret.add(Map.wrap((java.util.Map<?,?>) element));
			} else if (element instanceof java.lang.Boolean) {
				Boolean value = Boolean.wrap((java.lang.Boolean) element);
				if (iterable instanceof MarkedArrayList) {
					java.util.List<? extends Marker> elementMarkers = ((MarkedArrayList<?>) iterable).getElementMarkers().get(ret.getValue().size());
					((Marked) value).mark(elementMarkers);
				}
				ret.add(value);
			} else if (element instanceof java.lang.Integer) {
				Integer value = Integer.wrap((java.lang.Integer) element);
				if (iterable instanceof MarkedArrayList) {
					java.util.List<? extends Marker> elementMarkers = ((MarkedArrayList<?>) iterable).getElementMarkers().get(ret.getValue().size());
					((Marked) value).mark(elementMarkers);
				}
				ret.add(value);
			} else if (element instanceof java.lang.Double) {
				Double value = Double.wrap((java.lang.Double) element);
				if (iterable instanceof MarkedArrayList) {
					java.util.List<? extends Marker> elementMarkers = ((MarkedArrayList<?>) iterable).getElementMarkers().get(ret.getValue().size());
					((Marked) value).mark(elementMarkers);
				}
				ret.add(value);
			} else if (element instanceof java.lang.Long) {
				Long value = Long.wrap((java.lang.Long) element);
				if (iterable instanceof MarkedArrayList) {
					java.util.List<? extends Marker> elementMarkers = ((MarkedArrayList<?>) iterable).getElementMarkers().get(ret.getValue().size());
					((Marked) value).mark(elementMarkers);
				}
				ret.add(value);
			} else if (element instanceof java.util.Date) {
				Date value = Date.wrap((java.util.Date) element);
				if (iterable instanceof MarkedArrayList) {
					java.util.List<? extends Marker> elementMarkers = ((MarkedArrayList<?>) iterable).getElementMarkers().get(ret.getValue().size());
					((Marked) value).mark(elementMarkers);
				}
				ret.add(value);
			} else if (element instanceof java.lang.String) {
				String value = String.wrap((java.lang.String) element);
				if (iterable instanceof MarkedArrayList) {
					java.util.List<? extends Marker> elementMarkers = ((MarkedArrayList<?>) iterable).getElementMarkers().get(ret.getValue().size());
					((Marked) value).mark(elementMarkers);
				}
				ret.add(value);
			} else if (element != null) {
				throw new IllegalArgumentException("Cannot add " + element.getClass() + " to List. Value: " + element);
			}
		}
		return ret;
	}	
		
	default java.util.List<Object> toList() {
		java.util.List<Object> ret = new ArrayList<>();
		for (EObject e: getValue()) {
			if (e instanceof List) {
				ret.add(((List) e).toList());
			} else if (e instanceof Map) {
				ret.add(((Map) e).toMap());
			} else if (e instanceof ValueObject) {
				ret.add(((ValueObject<?>) e).getValue());
			}
		}
		return ret;
	}	

} // List
