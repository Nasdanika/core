/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Object reference. May be used in containment collections to point to an object contained elsewhere.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Reference#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getReference()
 * @model
 * @generated
 */
public interface Reference<T> extends EObject {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Object)
	 * @see org.nasdanika.ncore.NcorePackage#getReference_Target()
	 * @model kind="reference" required="true"
	 *        annotation="urn:org.nasdanika default-feature='true'"
	 * @generated
	 */
	T getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Reference#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(T value);

} // Reference
