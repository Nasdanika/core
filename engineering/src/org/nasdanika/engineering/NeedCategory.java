/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Need Category</b></em>'.
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
 *   <li>{@link org.nasdanika.engineering.NeedCategory#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getNeedCategory()
 * @model
 * @generated
 */
public interface NeedCategory extends ModelElement, NeedCategoryElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.NeedCategoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Category elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getNeedCategory_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<NeedCategoryElement> getElements();

} // NeedCategory
