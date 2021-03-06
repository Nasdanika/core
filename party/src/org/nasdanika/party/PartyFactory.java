/**
 */
package org.nasdanika.party;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.party.PartyPackage
 * @generated
 */
public interface PartyFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PartyFactory eINSTANCE = org.nasdanika.party.impl.PartyFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Directory</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Directory</em>'.
	 * @generated
	 */
	Directory createDirectory();

	/**
	 * Returns a new object of class '<em>Organizational Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Organizational Unit</em>'.
	 * @generated
	 */
	OrganizationalUnit createOrganizationalUnit();

	/**
	 * Returns a new object of class '<em>Organizational Unit Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Organizational Unit Reference</em>'.
	 * @generated
	 */
	OrganizationalUnitReference createOrganizationalUnitReference();

	/**
	 * Returns a new object of class '<em>Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role</em>'.
	 * @generated
	 */
	Role createRole();

	/**
	 * Returns a new object of class '<em>Organization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Organization</em>'.
	 * @generated
	 */
	Organization createOrganization();

	/**
	 * Returns a new object of class '<em>Member Directory</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Member Directory</em>'.
	 * @generated
	 */
	MemberDirectory createMemberDirectory();

	/**
	 * Returns a new object of class '<em>Member</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Member</em>'.
	 * @generated
	 */
	Member createMember();

	/**
	 * Returns a new object of class '<em>Person</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Person</em>'.
	 * @generated
	 */
	Person createPerson();

	/**
	 * Returns a new object of class '<em>Contact Method</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Contact Method</em>'.
	 * @generated
	 */
	ContactMethod createContactMethod();

	/**
	 * Returns a new object of class '<em>EMail</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EMail</em>'.
	 * @generated
	 */
	EMail createEMail();

	/**
	 * Returns a new object of class '<em>Phone</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Phone</em>'.
	 * @generated
	 */
	Phone createPhone();

	/**
	 * Returns a new object of class '<em>Postal Address</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Postal Address</em>'.
	 * @generated
	 */
	PostalAddress createPostalAddress();

	/**
	 * Returns a new object of class '<em>Web Address</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Web Address</em>'.
	 * @generated
	 */
	WebAddress createWebAddress();

	/**
	 * Returns a new object of class '<em>Resource Category</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Category</em>'.
	 * @generated
	 */
	ResourceCategory createResourceCategory();

	/**
	 * Returns a new object of class '<em>Markdown Text</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Markdown Text</em>'.
	 * @generated
	 */
	MarkdownText createMarkdownText();

	/**
	 * Returns a new object of class '<em>Markdown Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Markdown Resource</em>'.
	 * @generated
	 */
	MarkdownResource createMarkdownResource();

	/**
	 * Returns a new object of class '<em>Resource Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Reference</em>'.
	 * @generated
	 */
	ResourceReference createResourceReference();

	/**
	 * Returns a new object of class '<em>Html Text</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Html Text</em>'.
	 * @generated
	 */
	HtmlText createHtmlText();

	/**
	 * Returns a new object of class '<em>Html Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Html Resource</em>'.
	 * @generated
	 */
	HtmlResource createHtmlResource();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PartyPackage getPartyPackage();

} //PartyFactory
