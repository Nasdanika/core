/**
 */
package org.nasdanika.ncore;

import java.time.Duration;
import java.time.Instant;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Temporal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Temporal#getInstant <em>Instant</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Temporal#getBase <em>Base</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Temporal#getOffset <em>Offset</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Temporal#getDerivatives <em>Derivatives</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Temporal#getLowerBounds <em>Lower Bounds</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Temporal#getUpperBounds <em>Upper Bounds</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getTemporal()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/temporal.md'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='bounds offset'"
 * @generated
 */
public interface Temporal extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Instant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An absolute point on the time-line. E.g. ``2021/07/04``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Instant</em>' attribute.
	 * @see #setInstant(Instant)
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_Instant()
	 * @model dataType="org.nasdanika.ncore.Instant"
	 *        annotation="urn:org.nasdanika default-feature='true' exclusive-with='base'"
	 * @generated
	 */
	Instant getInstant();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Temporal#getInstant <em>Instant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instant</em>' attribute.
	 * @see #getInstant()
	 * @generated
	 */
	void setInstant(Instant value);

	/**
	 * Returns the value of the '<em><b>Base</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.ncore.Temporal#getDerivatives <em>Derivatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base of this temporal.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base</em>' reference.
	 * @see #setBase(Temporal)
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_Base()
	 * @see org.nasdanika.ncore.Temporal#getDerivatives
	 * @model opposite="derivatives"
	 *        annotation="urn:org.nasdanika exclusive-with='instant'"
	 * @generated
	 */
	Temporal getBase();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Temporal#getBase <em>Base</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base</em>' reference.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(Temporal value);

	/**
	 * Returns the value of the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time offset from the base in [ISO-8601 durations](https://en.wikipedia.org/wiki/ISO_8601#Durations) format. 
	 * 
	 * Examples:
	 * 
	 * * ``P1H`` for one hour later.
	 * * ``-P20D`` or ``P-20D`` for 20 days before. Can be null (zero), e.g. if one [period](Period.html) starts right after another period ends.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Offset</em>' attribute.
	 * @see #setOffset(Duration)
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_Offset()
	 * @model dataType="org.nasdanika.ncore.Duration"
	 * @generated
	 */
	Duration getOffset();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Temporal#getOffset <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset</em>' attribute.
	 * @see #getOffset()
	 * @generated
	 */
	void setOffset(Duration value);

	/**
	 * Returns the value of the '<em><b>Derivatives</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Temporal}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.ncore.Temporal#getBase <em>Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Temporals which are based on this temporal.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Derivatives</em>' reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_Derivatives()
	 * @see org.nasdanika.ncore.Temporal#getBase
	 * @model opposite="base" derived="true"
	 * @generated
	 */
	EList<Temporal> getDerivatives();

	/**
	 * Returns the value of the '<em><b>Lower Bounds</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Temporal}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Lower bounds of a temporal. E.g. exact time of some temporal might not be known at a moment, but it might be known that it should not be before than some other temporal - a lower bound. Lower bounds are used in validations and before/after computations. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lower Bounds</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_LowerBounds()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EList<Temporal> getLowerBounds();

	/**
	 * Returns the value of the '<em><b>Upper Bounds</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Temporal}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Upper bounds of a temporal. E.g. exact time of some temporal might not be known at a moment, but it might be known that it should not be after some other temporal - an upper bound. Upper bounds are used in validations and before/after computations. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Upper Bounds</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_UpperBounds()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EList<Temporal> getUpperBounds();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Tests if this temporal is after the specified temporal. Returns null if unknown, e.g. two unrelated events.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	Boolean after(Temporal when);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Tests if this temporal is before the specified temporal. Returns null if unknown, e.g. two unrelated events.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	Boolean before(Temporal when);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Tests if this temporal occurs at the same point on the time-line as the specified temporal. Returns null if unknown, e.g. two unrelated events.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	Boolean coincides(Temporal when);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns a normalized instance of this temporal not contained in the model. Normalization walks through the temporal chain to the root temporal. If that root temporal is instant/absolute then the normalized instance would be instant/absolute. Otherwise the normalized instance would contain the root temporal as its base and offset would be the sum of all offsets.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	Temporal normalize();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns [duration](Duration.html) difference between this temporal and the argument temporal - how much this temporal is after the argument on the time-line, if difference can be computed, e.g. both this temporal and the argument temporal are instant or trace to the same base temporal. Returns null otherwise.
	 * <!-- end-model-doc -->
	 * @model dataType="org.nasdanika.ncore.Duration"
	 * @generated
	 */
	Duration minus(Temporal when);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns a temporal based on this one offset by negation of the argument [duration](Duration.html). Null duration is treated as zero and set as-is (not negated).
	 * <!-- end-model-doc -->
	 * @model offsetDataType="org.nasdanika.ncore.Duration"
	 * @generated
	 */
	Temporal minus(Duration offset);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns a temporal based on this one offset by the argument [duration](Duration.html). Duration can be null.
	 * <!-- end-model-doc -->
	 * @model offsetDataType="org.nasdanika.ncore.Duration"
	 * @generated
	 */
	Temporal plus(Duration offset);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns a deep copy of self with bounds copied. Other containment references are not set.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	Temporal copy();

} // Temporal
