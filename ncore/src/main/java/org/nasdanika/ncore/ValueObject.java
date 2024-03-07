/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.ValueObject#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getValueObject()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ValueObject<T> extends Marked {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * String value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(Object)
	 * @see org.nasdanika.ncore.NcorePackage#getValueObject_Value()
	 * @model annotation="urn:org.nasdanika default-feature='true'"
	 * @generated
	 */
	T getValue();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ValueObject#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(T value);
	
	static EObject wrap(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof java.lang.Boolean) {
			return Boolean.wrap((java.lang.Boolean) value);
		} 
		if (value instanceof EObject) {
			return (EObject) value;
		} 
		if (value instanceof java.lang.Integer) {
			return Integer.wrap((java.lang.Integer) value);
		} 
		if (value instanceof java.lang.Long) {
			return Long.wrap((java.lang.Long) value);
		} 
		if (value instanceof java.lang.Double) {
			return Double.wrap((java.lang.Double) value);
		} 
		if (value instanceof java.util.Date) {
			return Date.wrap((java.util.Date) value);
		} 
		if (value instanceof Iterable) {
			return List.wrap((Iterable<?>) value);
		} 
		if (value instanceof java.lang.String) {
			return String.wrap((java.lang.String) value);
		} 
		if (value instanceof java.util.Map) {
			return Map.wrap((java.util.Map<?,?>) value);
		} 
		throw new IllegalArgumentException("Cannot wrap " + value.getClass() + ". Value: " + value);
		
	}

} // ValueObject
