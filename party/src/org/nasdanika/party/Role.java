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
 * Organization members may play different roles in organizational units. For example, Joe Doe may be in a Developer role in the Research And Development organizational unit.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.Role#getMembers <em>Members</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getRole()
 * @model abstract="true"
 * @generated
 */
public interface Role extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Members</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.party.Member}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Organization members in the role.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Members</em>' reference list.
	 * @see org.nasdanika.party.PartyPackage#getRole_Members()
	 * @model
	 * @generated
	 */
	EList<Member> getMembers();

} // Role
