/**
 */
package org.nasdanika.party;

import org.eclipse.emf.common.util.EList;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Member Directory</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A grouping of members and member directories.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.MemberDirectory#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getMemberDirectory()
 * @model
 * @generated
 */
public interface MemberDirectory extends ModelElement, MemberDirectoryElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.party.MemberDirectoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Member directory elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.party.PartyPackage#getMemberDirectory_Elements()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<MemberDirectoryElement> getElements();

} // MemberDirectory
