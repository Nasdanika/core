/**
 */
package org.nasdanika.party.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.ncore.NcorePackage;

import org.nasdanika.party.AbstractOrganizationalUnit;
import org.nasdanika.party.ContactMethod;
import org.nasdanika.party.Directory;
import org.nasdanika.party.DirectoryElement;
import org.nasdanika.party.EMail;
import org.nasdanika.party.Html;
import org.nasdanika.party.HtmlResource;
import org.nasdanika.party.HtmlText;
import org.nasdanika.party.Markdown;
import org.nasdanika.party.MarkdownResource;
import org.nasdanika.party.MarkdownText;
import org.nasdanika.party.Member;
import org.nasdanika.party.MemberDirectory;
import org.nasdanika.party.MemberDirectoryElement;
import org.nasdanika.party.Organization;
import org.nasdanika.party.OrganizationalUnit;
import org.nasdanika.party.OrganizationalUnitReference;
import org.nasdanika.party.Party;
import org.nasdanika.party.PartyFactory;
import org.nasdanika.party.PartyPackage;
import org.nasdanika.party.Person;
import org.nasdanika.party.Phone;
import org.nasdanika.party.PostalAddress;
import org.nasdanika.party.Resource;
import org.nasdanika.party.ResourceCategory;
import org.nasdanika.party.ResourceCategoryElement;
import org.nasdanika.party.ResourceReference;
import org.nasdanika.party.Role;
import org.nasdanika.party.WebAddress;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PartyPackageImpl extends EPackageImpl implements PartyPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass directoryElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass directoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass partyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractOrganizationalUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass organizationalUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass organizationalUnitReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contactMethodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eMailEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass phoneEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass postalAddressEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webAddressEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceCategoryElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceCategoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass markdownEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass markdownTextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass markdownResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass htmlEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass htmlTextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass htmlResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass organizationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass memberDirectoryElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass memberDirectoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass memberEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass personEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.nasdanika.party.PartyPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PartyPackageImpl() {
		super(eNS_URI, PartyFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link PartyPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PartyPackage init() {
		if (isInited) return (PartyPackage)EPackage.Registry.INSTANCE.getEPackage(PartyPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredPartyPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		PartyPackageImpl thePartyPackage = registeredPartyPackage instanceof PartyPackageImpl ? (PartyPackageImpl)registeredPartyPackage : new PartyPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		NcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePartyPackage.createPackageContents();

		// Initialize created meta-data
		thePartyPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePartyPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PartyPackage.eNS_URI, thePartyPackage);
		return thePartyPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDirectoryElement() {
		return directoryElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDirectory() {
		return directoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDirectory_Elements() {
		return (EReference)directoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParty() {
		return partyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParty_ContactMethods() {
		return (EReference)partyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParty_Resources() {
		return (EReference)partyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractOrganizationalUnit() {
		return abstractOrganizationalUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOrganizationalUnit() {
		return organizationalUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOrganizationalUnit_OrganizationalUnits() {
		return (EReference)organizationalUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOrganizationalUnit_Roles() {
		return (EReference)organizationalUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOrganizationalUnitReference() {
		return organizationalUnitReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOrganizationalUnitReference_Target() {
		return (EReference)organizationalUnitReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContactMethod() {
		return contactMethodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEMail() {
		return eMailEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEMail_EMailAddress() {
		return (EAttribute)eMailEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPhone() {
		return phoneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPhone_CountryCode() {
		return (EAttribute)phoneEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPhone_AreaCode() {
		return (EAttribute)phoneEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPhone_PhoneNumber() {
		return (EAttribute)phoneEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPhone_Extension() {
		return (EAttribute)phoneEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPostalAddress() {
		return postalAddressEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPostalAddress_Country() {
		return (EAttribute)postalAddressEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPostalAddress_StateProvince() {
		return (EAttribute)postalAddressEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPostalAddress_City() {
		return (EAttribute)postalAddressEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPostalAddress_PostalCode() {
		return (EAttribute)postalAddressEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPostalAddress_Line1() {
		return (EAttribute)postalAddressEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPostalAddress_Line2() {
		return (EAttribute)postalAddressEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWebAddress() {
		return webAddressEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWebAddress_Url() {
		return (EAttribute)webAddressEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResourceCategoryElement() {
		return resourceCategoryElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResourceCategory() {
		return resourceCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResourceCategory_Elements() {
		return (EReference)resourceCategoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMarkdown() {
		return markdownEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMarkdownText() {
		return markdownTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMarkdownText_Markdown() {
		return (EAttribute)markdownTextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMarkdownResource() {
		return markdownResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMarkdownResource_Location() {
		return (EAttribute)markdownResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResourceReference() {
		return resourceReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResourceReference_Location() {
		return (EAttribute)resourceReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHtml() {
		return htmlEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHtmlText() {
		return htmlTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHtmlText_Content() {
		return (EAttribute)htmlTextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHtmlResource() {
		return htmlResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHtmlResource_Location() {
		return (EAttribute)htmlResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRole() {
		return roleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRole_Members() {
		return (EReference)roleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRole_Extends() {
		return (EReference)roleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRole_Abstract() {
		return (EAttribute)roleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRole_Resources() {
		return (EReference)roleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOrganization() {
		return organizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOrganization_Members() {
		return (EReference)organizationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOrganization_Directory() {
		return (EReference)organizationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMemberDirectoryElement() {
		return memberDirectoryElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMemberDirectory() {
		return memberDirectoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMemberDirectory_Elements() {
		return (EReference)memberDirectoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMember() {
		return memberEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMember_Party() {
		return (EReference)memberEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMember_Resources() {
		return (EReference)memberEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPerson() {
		return personEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PartyFactory getPartyFactory() {
		return (PartyFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		directoryElementEClass = createEClass(DIRECTORY_ELEMENT);

		directoryEClass = createEClass(DIRECTORY);
		createEReference(directoryEClass, DIRECTORY__ELEMENTS);

		partyEClass = createEClass(PARTY);
		createEReference(partyEClass, PARTY__CONTACT_METHODS);
		createEReference(partyEClass, PARTY__RESOURCES);

		abstractOrganizationalUnitEClass = createEClass(ABSTRACT_ORGANIZATIONAL_UNIT);

		organizationalUnitEClass = createEClass(ORGANIZATIONAL_UNIT);
		createEReference(organizationalUnitEClass, ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS);
		createEReference(organizationalUnitEClass, ORGANIZATIONAL_UNIT__ROLES);

		organizationalUnitReferenceEClass = createEClass(ORGANIZATIONAL_UNIT_REFERENCE);
		createEReference(organizationalUnitReferenceEClass, ORGANIZATIONAL_UNIT_REFERENCE__TARGET);

		roleEClass = createEClass(ROLE);
		createEReference(roleEClass, ROLE__MEMBERS);
		createEReference(roleEClass, ROLE__EXTENDS);
		createEAttribute(roleEClass, ROLE__ABSTRACT);
		createEReference(roleEClass, ROLE__RESOURCES);

		organizationEClass = createEClass(ORGANIZATION);
		createEReference(organizationEClass, ORGANIZATION__MEMBERS);
		createEReference(organizationEClass, ORGANIZATION__DIRECTORY);

		memberDirectoryElementEClass = createEClass(MEMBER_DIRECTORY_ELEMENT);

		memberDirectoryEClass = createEClass(MEMBER_DIRECTORY);
		createEReference(memberDirectoryEClass, MEMBER_DIRECTORY__ELEMENTS);

		memberEClass = createEClass(MEMBER);
		createEReference(memberEClass, MEMBER__PARTY);
		createEReference(memberEClass, MEMBER__RESOURCES);

		personEClass = createEClass(PERSON);

		contactMethodEClass = createEClass(CONTACT_METHOD);

		eMailEClass = createEClass(EMAIL);
		createEAttribute(eMailEClass, EMAIL__EMAIL_ADDRESS);

		phoneEClass = createEClass(PHONE);
		createEAttribute(phoneEClass, PHONE__COUNTRY_CODE);
		createEAttribute(phoneEClass, PHONE__AREA_CODE);
		createEAttribute(phoneEClass, PHONE__PHONE_NUMBER);
		createEAttribute(phoneEClass, PHONE__EXTENSION);

		postalAddressEClass = createEClass(POSTAL_ADDRESS);
		createEAttribute(postalAddressEClass, POSTAL_ADDRESS__COUNTRY);
		createEAttribute(postalAddressEClass, POSTAL_ADDRESS__STATE_PROVINCE);
		createEAttribute(postalAddressEClass, POSTAL_ADDRESS__CITY);
		createEAttribute(postalAddressEClass, POSTAL_ADDRESS__POSTAL_CODE);
		createEAttribute(postalAddressEClass, POSTAL_ADDRESS__LINE1);
		createEAttribute(postalAddressEClass, POSTAL_ADDRESS__LINE2);

		webAddressEClass = createEClass(WEB_ADDRESS);
		createEAttribute(webAddressEClass, WEB_ADDRESS__URL);

		resourceCategoryElementEClass = createEClass(RESOURCE_CATEGORY_ELEMENT);

		resourceCategoryEClass = createEClass(RESOURCE_CATEGORY);
		createEReference(resourceCategoryEClass, RESOURCE_CATEGORY__ELEMENTS);

		resourceEClass = createEClass(RESOURCE);

		markdownEClass = createEClass(MARKDOWN);

		markdownTextEClass = createEClass(MARKDOWN_TEXT);
		createEAttribute(markdownTextEClass, MARKDOWN_TEXT__MARKDOWN);

		markdownResourceEClass = createEClass(MARKDOWN_RESOURCE);
		createEAttribute(markdownResourceEClass, MARKDOWN_RESOURCE__LOCATION);

		resourceReferenceEClass = createEClass(RESOURCE_REFERENCE);
		createEAttribute(resourceReferenceEClass, RESOURCE_REFERENCE__LOCATION);

		htmlEClass = createEClass(HTML);

		htmlTextEClass = createEClass(HTML_TEXT);
		createEAttribute(htmlTextEClass, HTML_TEXT__CONTENT);

		htmlResourceEClass = createEClass(HTML_RESOURCE);
		createEAttribute(htmlResourceEClass, HTML_RESOURCE__LOCATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		NcorePackage theNcorePackage = (NcorePackage)EPackage.Registry.INSTANCE.getEPackage(NcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		directoryElementEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		directoryEClass.getESuperTypes().add(this.getDirectoryElement());
		partyEClass.getESuperTypes().add(this.getDirectoryElement());
		partyEClass.getESuperTypes().add(theNcorePackage.getEntity());
		organizationalUnitEClass.getESuperTypes().add(this.getParty());
		organizationalUnitEClass.getESuperTypes().add(this.getAbstractOrganizationalUnit());
		organizationalUnitReferenceEClass.getESuperTypes().add(this.getAbstractOrganizationalUnit());
		roleEClass.getESuperTypes().add(theNcorePackage.getEntity());
		organizationEClass.getESuperTypes().add(this.getOrganizationalUnit());
		memberDirectoryEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		memberDirectoryEClass.getESuperTypes().add(this.getMemberDirectoryElement());
		memberEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		memberEClass.getESuperTypes().add(this.getMemberDirectoryElement());
		personEClass.getESuperTypes().add(this.getParty());
		contactMethodEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		eMailEClass.getESuperTypes().add(this.getContactMethod());
		phoneEClass.getESuperTypes().add(this.getContactMethod());
		postalAddressEClass.getESuperTypes().add(this.getContactMethod());
		webAddressEClass.getESuperTypes().add(this.getContactMethod());
		resourceCategoryEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		resourceCategoryEClass.getESuperTypes().add(this.getResourceCategoryElement());
		resourceEClass.getESuperTypes().add(theNcorePackage.getEntity());
		resourceEClass.getESuperTypes().add(this.getResourceCategoryElement());
		markdownEClass.getESuperTypes().add(this.getResource());
		markdownTextEClass.getESuperTypes().add(this.getMarkdown());
		markdownResourceEClass.getESuperTypes().add(this.getMarkdown());
		resourceReferenceEClass.getESuperTypes().add(this.getResource());
		htmlEClass.getESuperTypes().add(this.getResource());
		htmlTextEClass.getESuperTypes().add(this.getHtml());
		htmlResourceEClass.getESuperTypes().add(this.getHtml());

		// Initialize classes, features, and operations; add parameters
		initEClass(directoryElementEClass, DirectoryElement.class, "DirectoryElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(directoryEClass, Directory.class, "Directory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDirectory_Elements(), this.getDirectoryElement(), null, "elements", null, 0, -1, Directory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(partyEClass, Party.class, "Party", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParty_ContactMethods(), this.getContactMethod(), null, "contactMethods", null, 0, -1, Party.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParty_Resources(), this.getResourceCategoryElement(), null, "resources", null, 0, -1, Party.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractOrganizationalUnitEClass, AbstractOrganizationalUnit.class, "AbstractOrganizationalUnit", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(organizationalUnitEClass, OrganizationalUnit.class, "OrganizationalUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOrganizationalUnit_OrganizationalUnits(), this.getAbstractOrganizationalUnit(), null, "organizationalUnits", null, 0, -1, OrganizationalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOrganizationalUnit_Roles(), this.getRole(), null, "roles", null, 0, -1, OrganizationalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(organizationalUnitReferenceEClass, OrganizationalUnitReference.class, "OrganizationalUnitReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOrganizationalUnitReference_Target(), this.getOrganizationalUnit(), null, "target", null, 0, 1, OrganizationalUnitReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(roleEClass, Role.class, "Role", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRole_Members(), this.getMember(), null, "members", null, 0, -1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRole_Extends(), this.getRole(), null, "extends", null, 0, -1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRole_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRole_Resources(), this.getResourceCategoryElement(), null, "resources", null, 0, -1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(organizationEClass, Organization.class, "Organization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOrganization_Members(), this.getMemberDirectoryElement(), null, "members", null, 0, -1, Organization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOrganization_Directory(), this.getDirectoryElement(), null, "directory", null, 0, -1, Organization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(memberDirectoryElementEClass, MemberDirectoryElement.class, "MemberDirectoryElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(memberDirectoryEClass, MemberDirectory.class, "MemberDirectory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMemberDirectory_Elements(), this.getMemberDirectoryElement(), null, "elements", null, 0, -1, MemberDirectory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(memberEClass, Member.class, "Member", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMember_Party(), this.getParty(), null, "party", null, 1, 1, Member.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMember_Resources(), this.getResourceCategoryElement(), null, "resources", null, 0, -1, Member.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(personEClass, Person.class, "Person", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(contactMethodEClass, ContactMethod.class, "ContactMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(eMailEClass, EMail.class, "EMail", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEMail_EMailAddress(), ecorePackage.getEString(), "eMailAddress", null, 0, 1, EMail.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(phoneEClass, Phone.class, "Phone", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPhone_CountryCode(), ecorePackage.getEInt(), "countryCode", null, 0, 1, Phone.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPhone_AreaCode(), ecorePackage.getEInt(), "areaCode", null, 0, 1, Phone.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPhone_PhoneNumber(), ecorePackage.getEInt(), "phoneNumber", null, 0, 1, Phone.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPhone_Extension(), ecorePackage.getEInt(), "extension", null, 0, 1, Phone.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(postalAddressEClass, PostalAddress.class, "PostalAddress", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPostalAddress_Country(), ecorePackage.getEString(), "country", null, 0, 1, PostalAddress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPostalAddress_StateProvince(), ecorePackage.getEString(), "stateProvince", null, 0, 1, PostalAddress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPostalAddress_City(), ecorePackage.getEString(), "city", null, 0, 1, PostalAddress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPostalAddress_PostalCode(), ecorePackage.getEString(), "postalCode", null, 0, 1, PostalAddress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPostalAddress_Line1(), ecorePackage.getEString(), "line1", null, 0, 1, PostalAddress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPostalAddress_Line2(), ecorePackage.getEString(), "line2", null, 0, 1, PostalAddress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(webAddressEClass, WebAddress.class, "WebAddress", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWebAddress_Url(), ecorePackage.getEString(), "url", null, 0, 1, WebAddress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceCategoryElementEClass, ResourceCategoryElement.class, "ResourceCategoryElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(resourceCategoryEClass, ResourceCategory.class, "ResourceCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceCategory_Elements(), this.getResourceCategoryElement(), null, "elements", null, 0, -1, ResourceCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(markdownEClass, Markdown.class, "Markdown", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(markdownTextEClass, MarkdownText.class, "MarkdownText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMarkdownText_Markdown(), ecorePackage.getEString(), "markdown", null, 1, 1, MarkdownText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(markdownResourceEClass, MarkdownResource.class, "MarkdownResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMarkdownResource_Location(), ecorePackage.getEString(), "location", null, 1, 1, MarkdownResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceReferenceEClass, ResourceReference.class, "ResourceReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceReference_Location(), ecorePackage.getEString(), "location", null, 1, 1, ResourceReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(htmlEClass, Html.class, "Html", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(htmlTextEClass, HtmlText.class, "HtmlText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHtmlText_Content(), ecorePackage.getEString(), "content", null, 1, 1, HtmlText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(htmlResourceEClass, HtmlResource.class, "HtmlResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHtmlResource_Location(), ecorePackage.getEString(), "location", null, 1, 1, HtmlResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
		// urn:org.nasdanika
		createUrnorgAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "documentation", "Nasdanika party model allows to model parties - [persons](Person.html), [organizations](Organization.html), and [organizational units](OrganizationalUnit.html). \n\nParties can have [contact methods](ContactMethod.html) and may be members of organizations. \nParties may be organized into a [directory](Directory.html).\nOrganization is a subclass of organizational unit and has [members](Member.html). Organization member is a party.\nOrganizations may contain a party directory or reference external directories.\nOrganizational unit may have sub-units and [roles](Role.html). A role may have zero or more members in it.\n\nFor example, an organization may have a directory of parties and a directory of members with sub-directories for employees and contractors.\nIt then may have organizational units such as Development, Marketing, Sales. \nIn the development group it may have roles such as Product Owner, Lead Developer, Junior Developer, etc. with different members in each role."
		   });
		addAnnotation
		  (directoryElementEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for directory elements - parties and (sub) directories."
		   });
		addAnnotation
		  (directoryEClass,
		   source,
		   new String[] {
			   "documentation", "A group of parties and sub-directories."
		   });
		addAnnotation
		  (getDirectory_Elements(),
		   source,
		   new String[] {
			   "documentation", "Directory elements - parties and sub-directories."
		   });
		addAnnotation
		  (partyEClass,
		   source,
		   new String[] {
			   "documentation", "A person or organizational unit/organization."
		   });
		addAnnotation
		  (getParty_ContactMethods(),
		   source,
		   new String[] {
			   "documentation", "Party can have zero or more contact methods."
		   });
		addAnnotation
		  (getParty_Resources(),
		   source,
		   new String[] {
			   "documentation", "Resource/document library."
		   });
		addAnnotation
		  (organizationalUnitEClass,
		   source,
		   new String[] {
			   "documentation", "A unit of organization or another organizational unit."
		   });
		addAnnotation
		  (getOrganizationalUnit_OrganizationalUnits(),
		   source,
		   new String[] {
			   "documentation", "Sub-units of this unit."
		   });
		addAnnotation
		  (getOrganizationalUnit_Roles(),
		   source,
		   new String[] {
			   "documentation", "Member roles in this organizational unit. For example, Product Owner, Scrum Master, Developers."
		   });
		addAnnotation
		  (roleEClass,
		   source,
		   new String[] {
			   "documentation", "Organization members may play different roles in organizational units. For example, Joe Doe may be in a Developer role in the Research And Development organizational unit."
		   });
		addAnnotation
		  (getRole_Members(),
		   source,
		   new String[] {
			   "documentation", "Organization members in the role."
		   });
		addAnnotation
		  (getRole_Extends(),
		   source,
		   new String[] {
			   "documentation", "A role can extend other roles. For example a Developer role may extend Employee role. "
		   });
		addAnnotation
		  (getRole_Abstract(),
		   source,
		   new String[] {
			   "documentation", "An abstract role may be extended by may not have members. For example, an Employee role may be abstract and created to contain resources and provide description common for all employees. A concrete Developer role extends Employee role. "
		   });
		addAnnotation
		  (getRole_Resources(),
		   source,
		   new String[] {
			   "documentation", "Resource/document library."
		   });
		addAnnotation
		  (organizationEClass,
		   source,
		   new String[] {
			   "documentation", "Organization extends Organizational Unit and as such it may have sub-units and roles. It also may have members and contain a directory of parties."
		   });
		addAnnotation
		  (getOrganization_Members(),
		   source,
		   new String[] {
			   "documentation", "Organization members."
		   });
		addAnnotation
		  (getOrganization_Directory(),
		   source,
		   new String[] {
			   "documentation", "Organization may define its own party directory to reference parties from members. Or it may reference parties defined elsewhere."
		   });
		addAnnotation
		  (memberDirectoryElementEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for member directory and member."
		   });
		addAnnotation
		  (memberDirectoryEClass,
		   source,
		   new String[] {
			   "documentation", "A grouping of members and member directories."
		   });
		addAnnotation
		  (getMemberDirectory_Elements(),
		   source,
		   new String[] {
			   "documentation", "Member directory elements."
		   });
		addAnnotation
		  (memberEClass,
		   source,
		   new String[] {
			   "documentation", "A party may be a member of an organization."
		   });
		addAnnotation
		  (getMember_Party(),
		   source,
		   new String[] {
			   "documentation", "Reference to a party."
		   });
		addAnnotation
		  (getMember_Resources(),
		   source,
		   new String[] {
			   "documentation", "Resource/document library."
		   });
		addAnnotation
		  (personEClass,
		   source,
		   new String[] {
			   "documentation", "A human party."
		   });
		addAnnotation
		  (contactMethodEClass,
		   source,
		   new String[] {
			   "documentation", "Generic contact method."
		   });
		addAnnotation
		  (eMailEClass,
		   source,
		   new String[] {
			   "documentation", "E-mail address."
		   });
		addAnnotation
		  (getEMail_EMailAddress(),
		   source,
		   new String[] {
			   "documentation", "E-Mail address."
		   });
		addAnnotation
		  (phoneEClass,
		   source,
		   new String[] {
			   "documentation", "Phone."
		   });
		addAnnotation
		  (getPhone_CountryCode(),
		   source,
		   new String[] {
			   "documentation", "Country code."
		   });
		addAnnotation
		  (getPhone_AreaCode(),
		   source,
		   new String[] {
			   "documentation", "Area code."
		   });
		addAnnotation
		  (getPhone_PhoneNumber(),
		   source,
		   new String[] {
			   "documentation", "Phone number."
		   });
		addAnnotation
		  (getPhone_Extension(),
		   source,
		   new String[] {
			   "documentation", "Optional extension."
		   });
		addAnnotation
		  (postalAddressEClass,
		   source,
		   new String[] {
			   "documentation", "Postal Address."
		   });
		addAnnotation
		  (getPostalAddress_Country(),
		   source,
		   new String[] {
			   "documentation", "Country."
		   });
		addAnnotation
		  (getPostalAddress_StateProvince(),
		   source,
		   new String[] {
			   "documentation", "State or province or region"
		   });
		addAnnotation
		  (getPostalAddress_City(),
		   source,
		   new String[] {
			   "documentation", "City/town"
		   });
		addAnnotation
		  (getPostalAddress_PostalCode(),
		   source,
		   new String[] {
			   "documentation", "Postal/zip code."
		   });
		addAnnotation
		  (getPostalAddress_Line1(),
		   source,
		   new String[] {
			   "documentation", "Address line 1."
		   });
		addAnnotation
		  (getPostalAddress_Line2(),
		   source,
		   new String[] {
			   "documentation", "Address line 2."
		   });
		addAnnotation
		  (webAddressEClass,
		   source,
		   new String[] {
			   "documentation", "URL."
		   });
		addAnnotation
		  (getWebAddress_Url(),
		   source,
		   new String[] {
			   "documentation", "URL."
		   });
		addAnnotation
		  (resourceCategoryElementEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for model elements which can be contained by need category - needs and sub-categories."
		   });
		addAnnotation
		  (resourceCategoryEClass,
		   source,
		   new String[] {
			   "documentation", "Grouping of persona needs."
		   });
		addAnnotation
		  (getResourceCategory_Elements(),
		   source,
		   new String[] {
			   "documentation", "Category elements."
		   });
		addAnnotation
		  (resourceEClass,
		   source,
		   new String[] {
			   "documentation", "Personas have needs which may be satisfied by organization offerings. Needs can be organized into a hierarchy and assigned weights either manually or using decision analysis techniques. Needs may be satisfied by offerings via scenarios explaining how a need is satisfied.\n\nMust have, need to have, delighter - here or at the offering level?"
		   });
		addAnnotation
		  (markdownEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for [Markdown](https://en.wikipedia.org/wiki/Markdown) resources."
		   });
		addAnnotation
		  (markdownTextEClass,
		   source,
		   new String[] {
			   "documentation", "Generates HTML from [Markdown](https://en.wikipedia.org/wiki/Markdown) text."
		   });
		addAnnotation
		  (getMarkdownText_Markdown(),
		   source,
		   new String[] {
			   "documentation", "Markdown text."
		   });
		addAnnotation
		  (markdownResourceEClass,
		   source,
		   new String[] {
			   "documentation", "Generates HTML from [Markdown](https://en.wikipedia.org/wiki/Markdown) resource."
		   });
		addAnnotation
		  (getMarkdownResource_Location(),
		   source,
		   new String[] {
			   "documentation", "Markdown resource location. The resource location is resolved relative to the model resource."
		   });
		addAnnotation
		  (resourceReferenceEClass,
		   source,
		   new String[] {
			   "documentation", "Reference to an external resource, e.g. a web page."
		   });
		addAnnotation
		  (getResourceReference_Location(),
		   source,
		   new String[] {
			   "documentation", "Resource location resolved relative to the model resource."
		   });
		addAnnotation
		  (htmlEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for HTML resources."
		   });
		addAnnotation
		  (htmlTextEClass,
		   source,
		   new String[] {
			   "documentation", "Generates HTML from [Markdown](https://en.wikipedia.org/wiki/Markdown) text."
		   });
		addAnnotation
		  (getHtmlText_Content(),
		   source,
		   new String[] {
			   "documentation", "HTML content."
		   });
		addAnnotation
		  (htmlResourceEClass,
		   source,
		   new String[] {
			   "documentation", "Loads HTML from a resource."
		   });
		addAnnotation
		  (getHtmlResource_Location(),
		   source,
		   new String[] {
			   "documentation", "HTML resource location resolved relative to the model resource."
		   });
	}

	/**
	 * Initializes the annotations for <b>urn:org.nasdanika</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createUrnorgAnnotations() {
		String source = "urn:org.nasdanika";
		addAnnotation
		  (getMarkdownText_Markdown(),
		   source,
		   new String[] {
			   "content-type", "text/markdown"
		   });
		addAnnotation
		  (getHtmlText_Content(),
		   source,
		   new String[] {
			   "content-type", "text/html"
		   });
	}

} //PartyPackageImpl
