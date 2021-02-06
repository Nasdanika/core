/**
 */
package org.nasdanika.party;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Organizational Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A unit of organization or another organizational unit.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.OrganizationalUnit#getOrganizationalUnits <em>Organizational Units</em>}</li>
 *   <li>{@link org.nasdanika.party.OrganizationalUnit#getRoles <em>Roles</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getOrganizationalUnit()
 * @model
 * @generated
 */
public interface OrganizationalUnit extends Party, AbstractOrganizationalUnit {
	/**
	 * Returns the value of the '<em><b>Organizational Units</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.party.AbstractOrganizationalUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sub-units of this unit.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Organizational Units</em>' containment reference list.
	 * @see org.nasdanika.party.PartyPackage#getOrganizationalUnit_OrganizationalUnits()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<AbstractOrganizationalUnit> getOrganizationalUnits();

	/**
	 * Returns the value of the '<em><b>Roles</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.party.Role}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Member roles in this organizational unit. For example, Product Owner, Scrum Master, Developers.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Roles</em>' containment reference list.
	 * @see org.nasdanika.party.PartyPackage#getOrganizationalUnit_Roles()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Role> getRoles();

} // OrganizationalUnit
