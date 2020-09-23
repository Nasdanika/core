/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Product is an offering and a component. It has features which satisfy personas neeeds. It may have editions, which are collections of features offered at different prices and or to different personas
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Product#getEditions <em>Editions</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Product#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getProduct()
 * @model
 * @generated
 */
public interface Product extends Component, Offering {

	/**
	 * Returns the value of the '<em><b>Editions</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Edition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editions</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getProduct_Editions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Edition> getEditions();

	/**
	 * Returns the value of the '<em><b>Features</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' reference.
	 * @see #setFeatures(FeatureCategoryElement)
	 * @see org.nasdanika.engineering.EngineeringPackage#getProduct_Features()
	 * @model
	 * @generated
	 */
	FeatureCategoryElement getFeatures();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Product#getFeatures <em>Features</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Features</em>' reference.
	 * @see #getFeatures()
	 * @generated
	 */
	void setFeatures(FeatureCategoryElement value);
} // Product
