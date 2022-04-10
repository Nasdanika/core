/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EObject Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named/keyed object.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.EObjectProperty#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getEObjectProperty()
 * @model
 * @generated
 */
public interface EObjectProperty extends Property {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Property value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(EObject)
	 * @see org.nasdanika.ncore.NcorePackage#getEObjectProperty_Value()
	 * @model containment="true" required="true"
	 *        annotation="urn:org.nasdanika default-feature='true'"
	 * @generated
	 */
	EObject getValue();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.EObjectProperty#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(EObject value);

} // EObjectProperty
