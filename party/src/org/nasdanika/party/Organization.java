/**
 */
package org.nasdanika.party;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Organization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Organization extends Organizational Unit and as such it may have sub-units and roles. It also may have members and contain a directory of parties.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.Organization#getMembers <em>Members</em>}</li>
 *   <li>{@link org.nasdanika.party.Organization#getDirectory <em>Directory</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getOrganization()
 * @model
 * @generated
 */
public interface Organization extends OrganizationalUnit {
	/**
	 * Returns the value of the '<em><b>Members</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.party.MemberDirectoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Organization members.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Members</em>' containment reference list.
	 * @see org.nasdanika.party.PartyPackage#getOrganization_Members()
	 * @model containment="true"
	 * @generated
	 */
	EList<MemberDirectoryElement> getMembers();

	/**
	 * Returns the value of the '<em><b>Directory</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.party.DirectoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Organization may define its own party directory to reference parties from members. Or it may reference parties defined elsewhere.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Directory</em>' containment reference list.
	 * @see org.nasdanika.party.PartyPackage#getOrganization_Directory()
	 * @model containment="true"
	 * @generated
	 */
	EList<DirectoryElement> getDirectory();

} // Organization
