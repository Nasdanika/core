/**
 */
package org.nasdanika.ncore;

import java.time.Duration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Period</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Period#getStart <em>Start</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Period#getEnd <em>End</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Period#getDuration <em>Duration</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getPeriod()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/period.md'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='start_end'"
 * @generated
 */
public interface Period extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Start</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Period start.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start</em>' containment reference.
	 * @see #setStart(Temporal)
	 * @see org.nasdanika.ncore.NcorePackage#getPeriod_Start()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	Temporal getStart();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Period#getStart <em>Start</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' containment reference.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(Temporal value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Period end.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>End</em>' containment reference.
	 * @see #setEnd(Temporal)
	 * @see org.nasdanika.ncore.NcorePackage#getPeriod_End()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	Temporal getEnd();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Period#getEnd <em>End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' containment reference.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(Temporal value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Positive period duration in [ISO-8601 durations](https://en.wikipedia.org/wiki/ISO_8601#Durations) format. E.g. ``P1M`` for one month or ``P20D`` for 20 days.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(Duration)
	 * @see org.nasdanika.ncore.NcorePackage#getPeriod_Duration()
	 * @model dataType="org.nasdanika.ncore.Duration"
	 * @generated
	 */
	Duration getDuration();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Period#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(Duration value);

} // Period
