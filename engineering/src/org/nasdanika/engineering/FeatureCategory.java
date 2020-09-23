/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Grouping of features.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.FeatureCategory#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getFeatureCategory()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface FeatureCategory extends ModelElement, FeatureCategoryElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.FeatureCategoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Category elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getFeatureCategory_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<FeatureCategoryElement> getElements();

} // FeatureCategory
