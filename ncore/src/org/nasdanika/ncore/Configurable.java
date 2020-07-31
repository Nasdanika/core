/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configurable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Model element which contains configuration entries.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Configurable#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getConfigurable()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Configurable extends EObject {
	/**
	 * Returns the value of the '<em><b>Configuration</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Configuration entries.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Configuration</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getConfigurable_Configuration()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getConfiguration();

} // Configurable
