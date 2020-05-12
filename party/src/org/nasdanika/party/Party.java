/**
 */
package org.nasdanika.party;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.Entity;
import org.nasdanika.ncore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Party</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.Party#getContactMethods <em>Contact Methods</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getParty()
 * @model abstract="true"
 * @generated
 */
public interface Party extends NamedElement, Entity {
	/**
	 * Returns the value of the '<em><b>Contact Methods</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.party.ContactMethod}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Party can have zero or more contact methods.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contact Methods</em>' containment reference list.
	 * @see org.nasdanika.party.PartyPackage#getParty_ContactMethods()
	 * @model containment="true"
	 * @generated
	 */
	EList<ContactMethod> getContactMethods();

} // Party
