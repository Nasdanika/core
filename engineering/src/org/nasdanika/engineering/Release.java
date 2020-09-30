/**
 */
package org.nasdanika.engineering;

import java.util.Date;
import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Release</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Release/version of component. Date, may include other releases of other components or depend/require other releases - in either case a given release cannot be released before the included/dependency release.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Release#getRequires <em>Requires</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Release#getIncludes <em>Includes</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Release#getDate <em>Date</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Release#getPlannedFor <em>Planned For</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Release#isReleased <em>Released</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getRelease()
 * @model
 * @generated
 */
public interface Release extends ModelElement {

	/**
	 * Returns the value of the '<em><b>Requires</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Release}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Releases required by this release. For example product B release 1.2.0 may require product or component A release 1.5.6
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Requires</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getRelease_Requires()
	 * @model
	 * @generated
	 */
	EList<Release> getRequires();

	/**
	 * Returns the value of the '<em><b>Includes</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Release}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Release included in this release. For example product release 2020-12 may include component release 1.2.0.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Includes</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getRelease_Includes()
	 * @model
	 * @generated
	 */
	EList<Release> getIncludes();

	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Release can be planned for a date, an increment, or both. In the latter case the date shall be within the increment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(Date)
	 * @see org.nasdanika.engineering.EngineeringPackage#getRelease_Date()
	 * @model
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Release#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);

	/**
	 * Returns the value of the '<em><b>Planned For</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Interment in which release is scheduled to be published.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Planned For</em>' reference.
	 * @see #setPlannedFor(Increment)
	 * @see org.nasdanika.engineering.EngineeringPackage#getRelease_PlannedFor()
	 * @model
	 * @generated
	 */
	Increment getPlannedFor();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Release#getPlannedFor <em>Planned For</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Planned For</em>' reference.
	 * @see #getPlannedFor()
	 * @generated
	 */
	void setPlannedFor(Increment value);

	/**
	 * Returns the value of the '<em><b>Released</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Released</em>' attribute.
	 * @see #setReleased(boolean)
	 * @see org.nasdanika.engineering.EngineeringPackage#getRelease_Released()
	 * @model
	 * @generated
	 */
	boolean isReleased();

	/**
	 * Sets the value of the '{@link org.nasdanika.engineering.Release#isReleased <em>Released</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Released</em>' attribute.
	 * @see #isReleased()
	 * @generated
	 */
	void setReleased(boolean value);
} // Release
