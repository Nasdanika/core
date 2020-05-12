/**
 */
package org.nasdanika.provisioning;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requirement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Requirement of a zero or more capabilities.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.provisioning.Requirement#getRequiredCapabilities <em>Required Capabilities</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.provisioning.ProvisioningPackage#getRequirement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Requirement extends EObject {
	/**
	 * Returns the value of the '<em><b>Required Capabilities</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.provisioning.Capability}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.provisioning.Capability#getRequiredBy <em>Required By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Capabilities needed to satisfy the requirement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Required Capabilities</em>' reference list.
	 * @see org.nasdanika.provisioning.ProvisioningPackage#getRequirement_RequiredCapabilities()
	 * @see org.nasdanika.provisioning.Capability#getRequiredBy
	 * @model opposite="requiredBy"
	 * @generated
	 */
	EList<Capability> getRequiredCapabilities();

} // Requirement
