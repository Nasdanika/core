/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;

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
 *   <li>{@link org.nasdanika.engineering.Feature#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Feature#getRequires <em>Requires</em>}</li>
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

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(FeatureType)
	 * @see org.nasdanika.engineering.EngineeringPackage#getFeature_Type()
	 * @model
	 * @generated
	 */
	FeatureType getType();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Feature#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(FeatureType value);

	/**
	 * Returns the value of the '<em><b>Requires</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Feature}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * One feature may require another feature to build on/extend.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Requires</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getFeature_Requires()
	 * @model
	 * @generated
	 */
	EList<Feature> getRequires();
} // Feature
