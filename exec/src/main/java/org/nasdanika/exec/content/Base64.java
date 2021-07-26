/**
 */
package org.nasdanika.exec.content;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.exec.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Base64</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.content.Base64#getSources <em>Sources</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.content.ContentPackage#getBase64()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/content/base-64.md'"
 * @generated
 */
public interface Base64 extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Sources</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sources</em>' containment reference list.
	 * @see org.nasdanika.exec.content.ContentPackage#getBase64_Sources()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getSources();

} // Base64
