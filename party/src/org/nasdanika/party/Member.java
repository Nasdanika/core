/**
 */
package org.nasdanika.party;

import org.nasdanika.ncore.ModelElement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Member</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A party may be a member of an organization.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.Member#getParty <em>Party</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getMember()
 * @model
 * @generated
 */
public interface Member extends ModelElement, MemberDirectoryElement {
	/**
	 * Returns the value of the '<em><b>Party</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to a party.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Party</em>' reference.
	 * @see #setParty(Party)
	 * @see org.nasdanika.party.PartyPackage#getMember_Party()
	 * @model required="true"
	 * @generated
	 */
	Party getParty();

	/**
	 * Sets the value of the '{@link org.nasdanika.party.Member#getParty <em>Party</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Party</em>' reference.
	 * @see #getParty()
	 * @generated
	 */
	void setParty(Party value);

} // Member
