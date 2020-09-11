/**
 */
package org.nasdanika.party;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Directory</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A group of parties and sub-directories.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.Directory#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getDirectory()
 * @model
 * @generated
 */
public interface Directory extends DirectoryElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.party.DirectoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Directory elements - parties and sub-directories.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.party.PartyPackage#getDirectory_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<DirectoryElement> getElements();

} // Directory
