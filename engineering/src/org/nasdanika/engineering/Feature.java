/**
 */
package org.nasdanika.engineering;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Unit of product functionality which satisfies persona needs. Maybe included in multiple editions. Scheduled for a release. May depend on other features and on product components releases or issues. I.e. an issue is owned by an engineered element, but it contributes to a feature. Benefit - explicit value and computed from needs and scenarios etc.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Feature#getPlannedFor <em>Planned For</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getFeature()
 * @model
 * @generated
 */
public interface Feature extends FeatureCategoryElement {

	/**
	 * Returns the value of the '<em><b>Planned For</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Planned For</em>' reference.
	 * @see #setPlannedFor(Release)
	 * @see org.nasdanika.engineering.EngineeringPackage#getFeature_PlannedFor()
	 * @model
	 * @generated
	 */
	Release getPlannedFor();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Feature#getPlannedFor <em>Planned For</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Planned For</em>' reference.
	 * @see #getPlannedFor()
	 * @generated
	 */
	void setPlannedFor(Release value);
} // Feature
