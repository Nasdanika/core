/**
 */
package org.nasdanika.party.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.nasdanika.ncore.Entity;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.party.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.nasdanika.party.PartyPackage
 * @generated
 */
public class PartySwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PartyPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartySwitch() {
		if (modelPackage == null) {
			modelPackage = PartyPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case PartyPackage.DIRECTORY_ELEMENT: {
				DirectoryElement directoryElement = (DirectoryElement)theEObject;
				T result = caseDirectoryElement(directoryElement);
				if (result == null) result = caseModelElement(directoryElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.DIRECTORY: {
				Directory directory = (Directory)theEObject;
				T result = caseDirectory(directory);
				if (result == null) result = caseDirectoryElement(directory);
				if (result == null) result = caseModelElement(directory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.PARTY: {
				Party party = (Party)theEObject;
				T result = caseParty(party);
				if (result == null) result = caseDirectoryElement(party);
				if (result == null) result = caseEntity(party);
				if (result == null) result = caseModelElement(party);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.ABSTRACT_ORGANIZATIONAL_UNIT: {
				AbstractOrganizationalUnit abstractOrganizationalUnit = (AbstractOrganizationalUnit)theEObject;
				T result = caseAbstractOrganizationalUnit(abstractOrganizationalUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.ORGANIZATIONAL_UNIT: {
				OrganizationalUnit organizationalUnit = (OrganizationalUnit)theEObject;
				T result = caseOrganizationalUnit(organizationalUnit);
				if (result == null) result = caseParty(organizationalUnit);
				if (result == null) result = caseAbstractOrganizationalUnit(organizationalUnit);
				if (result == null) result = caseDirectoryElement(organizationalUnit);
				if (result == null) result = caseEntity(organizationalUnit);
				if (result == null) result = caseModelElement(organizationalUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.ORGANIZATIONAL_UNIT_REFERENCE: {
				OrganizationalUnitReference organizationalUnitReference = (OrganizationalUnitReference)theEObject;
				T result = caseOrganizationalUnitReference(organizationalUnitReference);
				if (result == null) result = caseAbstractOrganizationalUnit(organizationalUnitReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.ROLE: {
				Role role = (Role)theEObject;
				T result = caseRole(role);
				if (result == null) result = caseEntity(role);
				if (result == null) result = caseModelElement(role);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.ORGANIZATION: {
				Organization organization = (Organization)theEObject;
				T result = caseOrganization(organization);
				if (result == null) result = caseOrganizationalUnit(organization);
				if (result == null) result = caseParty(organization);
				if (result == null) result = caseAbstractOrganizationalUnit(organization);
				if (result == null) result = caseDirectoryElement(organization);
				if (result == null) result = caseEntity(organization);
				if (result == null) result = caseModelElement(organization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.MEMBER_DIRECTORY_ELEMENT: {
				MemberDirectoryElement memberDirectoryElement = (MemberDirectoryElement)theEObject;
				T result = caseMemberDirectoryElement(memberDirectoryElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.MEMBER_DIRECTORY: {
				MemberDirectory memberDirectory = (MemberDirectory)theEObject;
				T result = caseMemberDirectory(memberDirectory);
				if (result == null) result = caseModelElement(memberDirectory);
				if (result == null) result = caseMemberDirectoryElement(memberDirectory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.MEMBER: {
				Member member = (Member)theEObject;
				T result = caseMember(member);
				if (result == null) result = caseModelElement(member);
				if (result == null) result = caseMemberDirectoryElement(member);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.PERSON: {
				Person person = (Person)theEObject;
				T result = casePerson(person);
				if (result == null) result = caseParty(person);
				if (result == null) result = caseDirectoryElement(person);
				if (result == null) result = caseEntity(person);
				if (result == null) result = caseModelElement(person);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.CONTACT_METHOD: {
				ContactMethod contactMethod = (ContactMethod)theEObject;
				T result = caseContactMethod(contactMethod);
				if (result == null) result = caseModelElement(contactMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.EMAIL: {
				EMail eMail = (EMail)theEObject;
				T result = caseEMail(eMail);
				if (result == null) result = caseContactMethod(eMail);
				if (result == null) result = caseModelElement(eMail);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.PHONE: {
				Phone phone = (Phone)theEObject;
				T result = casePhone(phone);
				if (result == null) result = caseContactMethod(phone);
				if (result == null) result = caseModelElement(phone);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.POSTAL_ADDRESS: {
				PostalAddress postalAddress = (PostalAddress)theEObject;
				T result = casePostalAddress(postalAddress);
				if (result == null) result = caseContactMethod(postalAddress);
				if (result == null) result = caseModelElement(postalAddress);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.WEB_ADDRESS: {
				WebAddress webAddress = (WebAddress)theEObject;
				T result = caseWebAddress(webAddress);
				if (result == null) result = caseContactMethod(webAddress);
				if (result == null) result = caseModelElement(webAddress);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.RESOURCE_CATEGORY_ELEMENT: {
				ResourceCategoryElement resourceCategoryElement = (ResourceCategoryElement)theEObject;
				T result = caseResourceCategoryElement(resourceCategoryElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.RESOURCE_CATEGORY: {
				ResourceCategory resourceCategory = (ResourceCategory)theEObject;
				T result = caseResourceCategory(resourceCategory);
				if (result == null) result = caseModelElement(resourceCategory);
				if (result == null) result = caseResourceCategoryElement(resourceCategory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.RESOURCE: {
				Resource resource = (Resource)theEObject;
				T result = caseResource(resource);
				if (result == null) result = caseEntity(resource);
				if (result == null) result = caseResourceCategoryElement(resource);
				if (result == null) result = caseModelElement(resource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.MARKDOWN: {
				Markdown markdown = (Markdown)theEObject;
				T result = caseMarkdown(markdown);
				if (result == null) result = caseResource(markdown);
				if (result == null) result = caseEntity(markdown);
				if (result == null) result = caseResourceCategoryElement(markdown);
				if (result == null) result = caseModelElement(markdown);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.MARKDOWN_TEXT: {
				MarkdownText markdownText = (MarkdownText)theEObject;
				T result = caseMarkdownText(markdownText);
				if (result == null) result = caseMarkdown(markdownText);
				if (result == null) result = caseResource(markdownText);
				if (result == null) result = caseEntity(markdownText);
				if (result == null) result = caseResourceCategoryElement(markdownText);
				if (result == null) result = caseModelElement(markdownText);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.MARKDOWN_RESOURCE: {
				MarkdownResource markdownResource = (MarkdownResource)theEObject;
				T result = caseMarkdownResource(markdownResource);
				if (result == null) result = caseMarkdown(markdownResource);
				if (result == null) result = caseResource(markdownResource);
				if (result == null) result = caseEntity(markdownResource);
				if (result == null) result = caseResourceCategoryElement(markdownResource);
				if (result == null) result = caseModelElement(markdownResource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.RESOURCE_REFERENCE: {
				ResourceReference resourceReference = (ResourceReference)theEObject;
				T result = caseResourceReference(resourceReference);
				if (result == null) result = caseResource(resourceReference);
				if (result == null) result = caseEntity(resourceReference);
				if (result == null) result = caseResourceCategoryElement(resourceReference);
				if (result == null) result = caseModelElement(resourceReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.HTML: {
				Html html = (Html)theEObject;
				T result = caseHtml(html);
				if (result == null) result = caseResource(html);
				if (result == null) result = caseEntity(html);
				if (result == null) result = caseResourceCategoryElement(html);
				if (result == null) result = caseModelElement(html);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.HTML_TEXT: {
				HtmlText htmlText = (HtmlText)theEObject;
				T result = caseHtmlText(htmlText);
				if (result == null) result = caseHtml(htmlText);
				if (result == null) result = caseResource(htmlText);
				if (result == null) result = caseEntity(htmlText);
				if (result == null) result = caseResourceCategoryElement(htmlText);
				if (result == null) result = caseModelElement(htmlText);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PartyPackage.HTML_RESOURCE: {
				HtmlResource htmlResource = (HtmlResource)theEObject;
				T result = caseHtmlResource(htmlResource);
				if (result == null) result = caseHtml(htmlResource);
				if (result == null) result = caseResource(htmlResource);
				if (result == null) result = caseEntity(htmlResource);
				if (result == null) result = caseResourceCategoryElement(htmlResource);
				if (result == null) result = caseModelElement(htmlResource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Directory Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Directory Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDirectoryElement(DirectoryElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Directory</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Directory</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDirectory(Directory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Party</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Party</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParty(Party object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Organizational Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Organizational Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractOrganizationalUnit(AbstractOrganizationalUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Organizational Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Organizational Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrganizationalUnit(OrganizationalUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Organizational Unit Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Organizational Unit Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrganizationalUnitReference(OrganizationalUnitReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contact Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contact Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContactMethod(ContactMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EMail</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EMail</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEMail(EMail object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Phone</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Phone</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePhone(Phone object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Postal Address</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Postal Address</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePostalAddress(PostalAddress object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Web Address</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Web Address</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWebAddress(WebAddress object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Category Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Category Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceCategoryElement(ResourceCategoryElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceCategory(ResourceCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResource(Resource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Markdown</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Markdown</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMarkdown(Markdown object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Markdown Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Markdown Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMarkdownText(MarkdownText object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Markdown Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Markdown Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMarkdownResource(MarkdownResource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceReference(ResourceReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Html</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Html</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHtml(Html object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Html Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Html Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHtmlText(HtmlText object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Html Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Html Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHtmlResource(HtmlResource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRole(Role object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Organization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Organization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrganization(Organization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Member Directory Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Member Directory Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMemberDirectoryElement(MemberDirectoryElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Member Directory</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Member Directory</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMemberDirectory(MemberDirectory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Member</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Member</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMember(Member object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Person</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Person</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePerson(Person object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntity(Entity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //PartySwitch
