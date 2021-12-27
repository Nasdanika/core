/**
 */
package org.nasdanika.ncore.impl;

import java.lang.Boolean;
import java.lang.reflect.InvocationTargetException;

import java.time.Duration;
import java.time.Instant;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Temporal;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Temporal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.TemporalImpl#getInstant <em>Instant</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.TemporalImpl#getBase <em>Base</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.TemporalImpl#getOffset <em>Offset</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.TemporalImpl#getDerivatives <em>Derivatives</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.TemporalImpl#getLowerBounds <em>Lower Bounds</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.TemporalImpl#getUpperBounds <em>Upper Bounds</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TemporalImpl extends ModelElementImpl implements Temporal {
	/**
	 * The default value of the '{@link #getInstant() <em>Instant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstant()
	 * @generated
	 * @ordered
	 */
	protected static final Instant INSTANT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getOffset() <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffset()
	 * @generated
	 * @ordered
	 */
	protected static final Duration OFFSET_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemporalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.TEMPORAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instant getInstant() {
		return (Instant)eDynamicGet(NcorePackage.TEMPORAL__INSTANT, NcorePackage.Literals.TEMPORAL__INSTANT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInstant(Instant newInstant) {
		eDynamicSet(NcorePackage.TEMPORAL__INSTANT, NcorePackage.Literals.TEMPORAL__INSTANT, newInstant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Temporal getBase() {
		return (Temporal)eDynamicGet(NcorePackage.TEMPORAL__BASE, NcorePackage.Literals.TEMPORAL__BASE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Temporal basicGetBase() {
		return (Temporal)eDynamicGet(NcorePackage.TEMPORAL__BASE, NcorePackage.Literals.TEMPORAL__BASE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBase(Temporal newBase, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newBase, NcorePackage.TEMPORAL__BASE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBase(Temporal newBase) {
		eDynamicSet(NcorePackage.TEMPORAL__BASE, NcorePackage.Literals.TEMPORAL__BASE, newBase);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Duration getOffset() {
		return (Duration)eDynamicGet(NcorePackage.TEMPORAL__OFFSET, NcorePackage.Literals.TEMPORAL__OFFSET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOffset(Duration newOffset) {
		eDynamicSet(NcorePackage.TEMPORAL__OFFSET, NcorePackage.Literals.TEMPORAL__OFFSET, newOffset);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Temporal> getDerivatives() {
		return (EList<Temporal>)eDynamicGet(NcorePackage.TEMPORAL__DERIVATIVES, NcorePackage.Literals.TEMPORAL__DERIVATIVES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Temporal> getLowerBounds() {
		return (EList<Temporal>)eDynamicGet(NcorePackage.TEMPORAL__LOWER_BOUNDS, NcorePackage.Literals.TEMPORAL__LOWER_BOUNDS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Temporal> getUpperBounds() {
		return (EList<Temporal>)eDynamicGet(NcorePackage.TEMPORAL__UPPER_BOUNDS, NcorePackage.Literals.TEMPORAL__UPPER_BOUNDS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Boolean after(Temporal when) {
		return Temporal.super.after(when);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Boolean before(Temporal when) {
		return Temporal.super.before(when);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Boolean coincides(Temporal when) {
		return Temporal.super.coincides(when);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Temporal normalize() {
		return Temporal.super.normalize();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Duration minus(Temporal when) {
		return Temporal.super.minus(when);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Temporal minus(Duration offset) {
		return Temporal.super.minus(offset);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Temporal plus(Duration offset) {
		return Temporal.super.plus(offset);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Temporal copy() {
		return Temporal.super.copy();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NcorePackage.TEMPORAL__BASE:
				Temporal base = basicGetBase();
				if (base != null)
					msgs = ((InternalEObject)base).eInverseRemove(this, NcorePackage.TEMPORAL__DERIVATIVES, Temporal.class, msgs);
				return basicSetBase((Temporal)otherEnd, msgs);
			case NcorePackage.TEMPORAL__DERIVATIVES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDerivatives()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NcorePackage.TEMPORAL__BASE:
				return basicSetBase(null, msgs);
			case NcorePackage.TEMPORAL__DERIVATIVES:
				return ((InternalEList<?>)getDerivatives()).basicRemove(otherEnd, msgs);
			case NcorePackage.TEMPORAL__LOWER_BOUNDS:
				return ((InternalEList<?>)getLowerBounds()).basicRemove(otherEnd, msgs);
			case NcorePackage.TEMPORAL__UPPER_BOUNDS:
				return ((InternalEList<?>)getUpperBounds()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NcorePackage.TEMPORAL__INSTANT:
				return getInstant();
			case NcorePackage.TEMPORAL__BASE:
				if (resolve) return getBase();
				return basicGetBase();
			case NcorePackage.TEMPORAL__OFFSET:
				return getOffset();
			case NcorePackage.TEMPORAL__DERIVATIVES:
				return getDerivatives();
			case NcorePackage.TEMPORAL__LOWER_BOUNDS:
				return getLowerBounds();
			case NcorePackage.TEMPORAL__UPPER_BOUNDS:
				return getUpperBounds();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NcorePackage.TEMPORAL__INSTANT:
				setInstant((Instant)newValue);
				return;
			case NcorePackage.TEMPORAL__BASE:
				setBase((Temporal)newValue);
				return;
			case NcorePackage.TEMPORAL__OFFSET:
				setOffset((Duration)newValue);
				return;
			case NcorePackage.TEMPORAL__DERIVATIVES:
				getDerivatives().clear();
				getDerivatives().addAll((Collection<? extends Temporal>)newValue);
				return;
			case NcorePackage.TEMPORAL__LOWER_BOUNDS:
				getLowerBounds().clear();
				getLowerBounds().addAll((Collection<? extends Temporal>)newValue);
				return;
			case NcorePackage.TEMPORAL__UPPER_BOUNDS:
				getUpperBounds().clear();
				getUpperBounds().addAll((Collection<? extends Temporal>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case NcorePackage.TEMPORAL__INSTANT:
				setInstant(INSTANT_EDEFAULT);
				return;
			case NcorePackage.TEMPORAL__BASE:
				setBase((Temporal)null);
				return;
			case NcorePackage.TEMPORAL__OFFSET:
				setOffset(OFFSET_EDEFAULT);
				return;
			case NcorePackage.TEMPORAL__DERIVATIVES:
				getDerivatives().clear();
				return;
			case NcorePackage.TEMPORAL__LOWER_BOUNDS:
				getLowerBounds().clear();
				return;
			case NcorePackage.TEMPORAL__UPPER_BOUNDS:
				getUpperBounds().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NcorePackage.TEMPORAL__INSTANT:
				return INSTANT_EDEFAULT == null ? getInstant() != null : !INSTANT_EDEFAULT.equals(getInstant());
			case NcorePackage.TEMPORAL__BASE:
				return basicGetBase() != null;
			case NcorePackage.TEMPORAL__OFFSET:
				return OFFSET_EDEFAULT == null ? getOffset() != null : !OFFSET_EDEFAULT.equals(getOffset());
			case NcorePackage.TEMPORAL__DERIVATIVES:
				return !getDerivatives().isEmpty();
			case NcorePackage.TEMPORAL__LOWER_BOUNDS:
				return !getLowerBounds().isEmpty();
			case NcorePackage.TEMPORAL__UPPER_BOUNDS:
				return !getUpperBounds().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case NcorePackage.TEMPORAL___AFTER__TEMPORAL:
				return after((Temporal)arguments.get(0));
			case NcorePackage.TEMPORAL___BEFORE__TEMPORAL:
				return before((Temporal)arguments.get(0));
			case NcorePackage.TEMPORAL___COINCIDES__TEMPORAL:
				return coincides((Temporal)arguments.get(0));
			case NcorePackage.TEMPORAL___NORMALIZE:
				return normalize();
			case NcorePackage.TEMPORAL___MINUS__TEMPORAL:
				return minus((Temporal)arguments.get(0));
			case NcorePackage.TEMPORAL___MINUS__DURATION:
				return minus((Duration)arguments.get(0));
			case NcorePackage.TEMPORAL___PLUS__DURATION:
				return plus((Duration)arguments.get(0));
			case NcorePackage.TEMPORAL___COPY:
				return copy();
		}
		return super.eInvoke(operationID, arguments);
	}

} //TemporalImpl
