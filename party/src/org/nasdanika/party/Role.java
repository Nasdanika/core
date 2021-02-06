/**
 */
package org.nasdanika.party;

import org.eclipse.emf.common.util.EList;

import org.nasdanika.ncore.Entity;

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
 *   <li>{@link org.nasdanika.party.Role#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.nasdanika.party.Role#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.nasdanika.party.Role#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getRole()
 * @model
 * @generated
 */
public interface Role extends Entity {
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

	/**
	 * Returns the value of the '<em><b>Extends</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.party.Role}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A role can extend other roles. For example a Developer role may extend Employee role. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extends</em>' reference list.
	 * @see org.nasdanika.party.PartyPackage#getRole_Extends()
	 * @model
	 * @generated
	 */
	EList<Role> getExtends();

	/**
	 * Returns the value of the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An abstract role may be extended by may not have members. For example, an Employee role may be abstract and created to contain resources and provide description common for all employees. A concrete Developer role extends Employee role. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Abstract</em>' attribute.
	 * @see #setAbstract(boolean)
	 * @see org.nasdanika.party.PartyPackage#getRole_Abstract()
	 * @model
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.Role#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.party.ResourceCategoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resource/document library.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see org.nasdanika.party.PartyPackage#getRole_Resources()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ResourceCategoryElement> getResources();

} // Role
