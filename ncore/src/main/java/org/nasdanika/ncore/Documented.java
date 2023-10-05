/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Documented</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Mix-in interface for classess with documentation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Documented#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Documented#getContextHelp <em>Context Help</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getDocumented()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Documented extends EObject {
	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element documentation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Documentation</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getDocumented_Documentation()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getDocumentation();

	/**
	 * Returns the value of the '<em><b>Context Help</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Context help. It may be different from documentation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Context Help</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getDocumented_ContextHelp()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getContextHelp();

} // Documented
