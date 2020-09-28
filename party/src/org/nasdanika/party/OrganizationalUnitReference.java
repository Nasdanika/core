/**
 */
package org.nasdanika.party;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Organizational Unit Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.OrganizationalUnitReference#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getOrganizationalUnitReference()
 * @model
 * @generated
 */
public interface OrganizationalUnitReference extends AbstractOrganizationalUnit {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(OrganizationalUnit)
	 * @see org.nasdanika.party.PartyPackage#getOrganizationalUnitReference_Target()
	 * @model
	 * @generated
	 */
	OrganizationalUnit getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.OrganizationalUnitReference#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(OrganizationalUnit value);

} // OrganizationalUnitReference
