/**
 */
package org.nasdanika.party;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Nasdanika party model
 * <!-- end-model-doc -->
 * @see org.nasdanika.party.PartyFactory
 * @model kind="package"
 * @generated
 */
public interface PartyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "party";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.party";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.party";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PartyPackage eINSTANCE = org.nasdanika.party.impl.PartyPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.party.impl.PartyImpl <em>Party</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.party.impl.PartyImpl
	 * @see org.nasdanika.party.impl.PartyPackageImpl#getParty()
	 * @generated
	 */
	int PARTY = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY__TITLE = NcorePackage.NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY__DESCRIPTION = NcorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY__NAME = NcorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY__ID = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contact Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY__CONTACT_METHODS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Party</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY_FEATURE_COUNT = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Party</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY_OPERATION_COUNT = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.party.impl.ContactMethodImpl <em>Contact Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.party.impl.ContactMethodImpl
	 * @see org.nasdanika.party.impl.PartyPackageImpl#getContactMethod()
	 * @generated
	 */
	int CONTACT_METHOD = 1;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_METHOD__TITLE = NcorePackage.NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_METHOD__DESCRIPTION = NcorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_METHOD__NAME = NcorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Contact Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_METHOD_FEATURE_COUNT = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Contact Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_METHOD_OPERATION_COUNT = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.party.impl.EMailImpl <em>EMail</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.party.impl.EMailImpl
	 * @see org.nasdanika.party.impl.PartyPackageImpl#getEMail()
	 * @generated
	 */
	int EMAIL = 2;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__TITLE = CONTACT_METHOD__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__DESCRIPTION = CONTACT_METHOD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__NAME = CONTACT_METHOD__NAME;

	/**
	 * The feature id for the '<em><b>EMail Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__EMAIL_ADDRESS = CONTACT_METHOD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EMail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FEATURE_COUNT = CONTACT_METHOD_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>EMail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_OPERATION_COUNT = CONTACT_METHOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.party.impl.PhoneImpl <em>Phone</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.party.impl.PhoneImpl
	 * @see org.nasdanika.party.impl.PartyPackageImpl#getPhone()
	 * @generated
	 */
	int PHONE = 3;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__TITLE = CONTACT_METHOD__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__DESCRIPTION = CONTACT_METHOD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__NAME = CONTACT_METHOD__NAME;

	/**
	 * The feature id for the '<em><b>Country Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__COUNTRY_CODE = CONTACT_METHOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Area Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__AREA_CODE = CONTACT_METHOD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Phone Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__PHONE_NUMBER = CONTACT_METHOD_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__EXTENSION = CONTACT_METHOD_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Phone</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_FEATURE_COUNT = CONTACT_METHOD_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Phone</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_OPERATION_COUNT = CONTACT_METHOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.party.impl.PostalAddressImpl <em>Postal Address</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.party.impl.PostalAddressImpl
	 * @see org.nasdanika.party.impl.PartyPackageImpl#getPostalAddress()
	 * @generated
	 */
	int POSTAL_ADDRESS = 4;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__TITLE = CONTACT_METHOD__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__DESCRIPTION = CONTACT_METHOD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__NAME = CONTACT_METHOD__NAME;

	/**
	 * The feature id for the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__COUNTRY = CONTACT_METHOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>State Province</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__STATE_PROVINCE = CONTACT_METHOD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__CITY = CONTACT_METHOD_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Postal Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__POSTAL_CODE = CONTACT_METHOD_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Line1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__LINE1 = CONTACT_METHOD_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Line2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__LINE2 = CONTACT_METHOD_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Postal Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS_FEATURE_COUNT = CONTACT_METHOD_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Postal Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS_OPERATION_COUNT = CONTACT_METHOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.party.impl.WebAddressImpl <em>Web Address</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.party.impl.WebAddressImpl
	 * @see org.nasdanika.party.impl.PartyPackageImpl#getWebAddress()
	 * @generated
	 */
	int WEB_ADDRESS = 5;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS__TITLE = CONTACT_METHOD__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS__DESCRIPTION = CONTACT_METHOD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS__NAME = CONTACT_METHOD__NAME;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS__URL = CONTACT_METHOD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Web Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS_FEATURE_COUNT = CONTACT_METHOD_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Web Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS_OPERATION_COUNT = CONTACT_METHOD_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.party.Party <em>Party</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Party</em>'.
	 * @see org.nasdanika.party.Party
	 * @generated
	 */
	EClass getParty();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.party.Party#getContactMethods <em>Contact Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contact Methods</em>'.
	 * @see org.nasdanika.party.Party#getContactMethods()
	 * @see #getParty()
	 * @generated
	 */
	EReference getParty_ContactMethods();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.party.ContactMethod <em>Contact Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contact Method</em>'.
	 * @see org.nasdanika.party.ContactMethod
	 * @generated
	 */
	EClass getContactMethod();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.party.EMail <em>EMail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EMail</em>'.
	 * @see org.nasdanika.party.EMail
	 * @generated
	 */
	EClass getEMail();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.EMail#getEMailAddress <em>EMail Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EMail Address</em>'.
	 * @see org.nasdanika.party.EMail#getEMailAddress()
	 * @see #getEMail()
	 * @generated
	 */
	EAttribute getEMail_EMailAddress();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.party.Phone <em>Phone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Phone</em>'.
	 * @see org.nasdanika.party.Phone
	 * @generated
	 */
	EClass getPhone();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.Phone#getCountryCode <em>Country Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Country Code</em>'.
	 * @see org.nasdanika.party.Phone#getCountryCode()
	 * @see #getPhone()
	 * @generated
	 */
	EAttribute getPhone_CountryCode();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.Phone#getAreaCode <em>Area Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Area Code</em>'.
	 * @see org.nasdanika.party.Phone#getAreaCode()
	 * @see #getPhone()
	 * @generated
	 */
	EAttribute getPhone_AreaCode();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.Phone#getPhoneNumber <em>Phone Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phone Number</em>'.
	 * @see org.nasdanika.party.Phone#getPhoneNumber()
	 * @see #getPhone()
	 * @generated
	 */
	EAttribute getPhone_PhoneNumber();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.Phone#getExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extension</em>'.
	 * @see org.nasdanika.party.Phone#getExtension()
	 * @see #getPhone()
	 * @generated
	 */
	EAttribute getPhone_Extension();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.party.PostalAddress <em>Postal Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Postal Address</em>'.
	 * @see org.nasdanika.party.PostalAddress
	 * @generated
	 */
	EClass getPostalAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.PostalAddress#getCountry <em>Country</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Country</em>'.
	 * @see org.nasdanika.party.PostalAddress#getCountry()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_Country();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.PostalAddress#getStateProvince <em>State Province</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State Province</em>'.
	 * @see org.nasdanika.party.PostalAddress#getStateProvince()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_StateProvince();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.PostalAddress#getCity <em>City</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>City</em>'.
	 * @see org.nasdanika.party.PostalAddress#getCity()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_City();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.PostalAddress#getPostalCode <em>Postal Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Postal Code</em>'.
	 * @see org.nasdanika.party.PostalAddress#getPostalCode()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_PostalCode();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.PostalAddress#getLine1 <em>Line1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line1</em>'.
	 * @see org.nasdanika.party.PostalAddress#getLine1()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_Line1();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.PostalAddress#getLine2 <em>Line2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line2</em>'.
	 * @see org.nasdanika.party.PostalAddress#getLine2()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_Line2();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.party.WebAddress <em>Web Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Web Address</em>'.
	 * @see org.nasdanika.party.WebAddress
	 * @generated
	 */
	EClass getWebAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.party.WebAddress#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see org.nasdanika.party.WebAddress#getUrl()
	 * @see #getWebAddress()
	 * @generated
	 */
	EAttribute getWebAddress_Url();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PartyFactory getPartyFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.nasdanika.party.impl.PartyImpl <em>Party</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.party.impl.PartyImpl
		 * @see org.nasdanika.party.impl.PartyPackageImpl#getParty()
		 * @generated
		 */
		EClass PARTY = eINSTANCE.getParty();

		/**
		 * The meta object literal for the '<em><b>Contact Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTY__CONTACT_METHODS = eINSTANCE.getParty_ContactMethods();

		/**
		 * The meta object literal for the '{@link org.nasdanika.party.impl.ContactMethodImpl <em>Contact Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.party.impl.ContactMethodImpl
		 * @see org.nasdanika.party.impl.PartyPackageImpl#getContactMethod()
		 * @generated
		 */
		EClass CONTACT_METHOD = eINSTANCE.getContactMethod();

		/**
		 * The meta object literal for the '{@link org.nasdanika.party.impl.EMailImpl <em>EMail</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.party.impl.EMailImpl
		 * @see org.nasdanika.party.impl.PartyPackageImpl#getEMail()
		 * @generated
		 */
		EClass EMAIL = eINSTANCE.getEMail();

		/**
		 * The meta object literal for the '<em><b>EMail Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMAIL__EMAIL_ADDRESS = eINSTANCE.getEMail_EMailAddress();

		/**
		 * The meta object literal for the '{@link org.nasdanika.party.impl.PhoneImpl <em>Phone</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.party.impl.PhoneImpl
		 * @see org.nasdanika.party.impl.PartyPackageImpl#getPhone()
		 * @generated
		 */
		EClass PHONE = eINSTANCE.getPhone();

		/**
		 * The meta object literal for the '<em><b>Country Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHONE__COUNTRY_CODE = eINSTANCE.getPhone_CountryCode();

		/**
		 * The meta object literal for the '<em><b>Area Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHONE__AREA_CODE = eINSTANCE.getPhone_AreaCode();

		/**
		 * The meta object literal for the '<em><b>Phone Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHONE__PHONE_NUMBER = eINSTANCE.getPhone_PhoneNumber();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHONE__EXTENSION = eINSTANCE.getPhone_Extension();

		/**
		 * The meta object literal for the '{@link org.nasdanika.party.impl.PostalAddressImpl <em>Postal Address</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.party.impl.PostalAddressImpl
		 * @see org.nasdanika.party.impl.PartyPackageImpl#getPostalAddress()
		 * @generated
		 */
		EClass POSTAL_ADDRESS = eINSTANCE.getPostalAddress();

		/**
		 * The meta object literal for the '<em><b>Country</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__COUNTRY = eINSTANCE.getPostalAddress_Country();

		/**
		 * The meta object literal for the '<em><b>State Province</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__STATE_PROVINCE = eINSTANCE.getPostalAddress_StateProvince();

		/**
		 * The meta object literal for the '<em><b>City</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__CITY = eINSTANCE.getPostalAddress_City();

		/**
		 * The meta object literal for the '<em><b>Postal Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__POSTAL_CODE = eINSTANCE.getPostalAddress_PostalCode();

		/**
		 * The meta object literal for the '<em><b>Line1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__LINE1 = eINSTANCE.getPostalAddress_Line1();

		/**
		 * The meta object literal for the '<em><b>Line2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__LINE2 = eINSTANCE.getPostalAddress_Line2();

		/**
		 * The meta object literal for the '{@link org.nasdanika.party.impl.WebAddressImpl <em>Web Address</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.party.impl.WebAddressImpl
		 * @see org.nasdanika.party.impl.PartyPackageImpl#getWebAddress()
		 * @generated
		 */
		EClass WEB_ADDRESS = eINSTANCE.getWebAddress();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_ADDRESS__URL = eINSTANCE.getWebAddress_Url();

	}

} //PartyPackage
