/**
 */
package org.nasdanika.party;

import org.eclipse.emf.common.util.EList;

import org.nasdanika.ncore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Parties can play roles, e.g. a person "Joe Doe" can play a role of an engineer of some flow activity.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.Role#getParties <em>Parties</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getRole()
 * @model abstract="true"
 * @generated
 */
public interface Role extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Parties</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.party.Party}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Parties in role. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parties</em>' reference list.
	 * @see org.nasdanika.party.PartyPackage#getRole_Parties()
	 * @model
	 * @generated
	 */
	EList<Party> getParties();

} // Role
