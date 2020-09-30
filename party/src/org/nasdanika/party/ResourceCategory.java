/**
 */
package org.nasdanika.party;

import org.eclipse.emf.common.util.EList;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Grouping of persona needs.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.ResourceCategory#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.party.PartyPackage#getResourceCategory()
 * @model
 * @generated
 */
public interface ResourceCategory extends ModelElement, ResourceCategoryElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.party.ResourceCategoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Category elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.party.PartyPackage#getResourceCategory_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<ResourceCategoryElement> getElements();

} // ResourceCategory
