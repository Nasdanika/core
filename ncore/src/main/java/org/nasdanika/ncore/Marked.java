/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Marked</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Marked#getMarker <em>Marker</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getMarked()
 * @model interface="true" abstract="true"
 *        annotation="urn:org.nasdanika documentation-reference='doc/marked.md'"
 * @generated
 */
public interface Marked extends EObject {
	/**
	 * Returns the value of the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Marker</em>' containment reference.
	 * @see #setMarker(Marker)
	 * @see org.nasdanika.ncore.NcorePackage#getMarked_Marker()
	 * @model containment="true"
	 * @generated
	 */
	Marker getMarker();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Marked#getMarker <em>Marker</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Marker</em>' containment reference.
	 * @see #getMarker()
	 * @generated
	 */
	void setMarker(Marker value);

} // Marked
