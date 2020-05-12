/**
 */
package org.nasdanika.rigel.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.rigel.Activity;
import org.nasdanika.rigel.Capability;
import org.nasdanika.rigel.Engineer;
import org.nasdanika.rigel.Issue;
import org.nasdanika.rigel.IssueImportance;
import org.nasdanika.rigel.IssueStatus;
import org.nasdanika.rigel.Requirement;
import org.nasdanika.rigel.RigelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Issue</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.impl.IssueImpl#getRequiredCapabilities <em>Required Capabilities</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.IssueImpl#getImportance <em>Importance</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.IssueImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.IssueImpl#getAssignedTo <em>Assigned To</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.IssueImpl#getSize <em>Size</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.IssueImpl#getBenefit <em>Benefit</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.IssueImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.rigel.impl.IssueImpl#getImplementation <em>Implementation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IssueImpl extends ModelElementImpl implements Issue {
	/**
	 * The default value of the '{@link #getImportance() <em>Importance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportance()
	 * @generated
	 * @ordered
	 */
	protected static final IssueImportance IMPORTANCE_EDEFAULT = IssueImportance.MEDIUM;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final IssueStatus STATUS_EDEFAULT = IssueStatus.OPEN;

	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final double SIZE_EDEFAULT = 0.0;

	/**
	 * The default value of the '{@link #getBenefit() <em>Benefit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBenefit()
	 * @generated
	 * @ordered
	 */
	protected static final double BENEFIT_EDEFAULT = 0.0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RigelPackage.Literals.ISSUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IssueImportance getImportance() {
		return (IssueImportance)eDynamicGet(RigelPackage.ISSUE__IMPORTANCE, RigelPackage.Literals.ISSUE__IMPORTANCE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImportance(IssueImportance newImportance) {
		eDynamicSet(RigelPackage.ISSUE__IMPORTANCE, RigelPackage.Literals.ISSUE__IMPORTANCE, newImportance);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IssueStatus getStatus() {
		return (IssueStatus)eDynamicGet(RigelPackage.ISSUE__STATUS, RigelPackage.Literals.ISSUE__STATUS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStatus(IssueStatus newStatus) {
		eDynamicSet(RigelPackage.ISSUE__STATUS, RigelPackage.Literals.ISSUE__STATUS, newStatus);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Engineer getAssignedTo() {
		return (Engineer)eDynamicGet(RigelPackage.ISSUE__ASSIGNED_TO, RigelPackage.Literals.ISSUE__ASSIGNED_TO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Engineer basicGetAssignedTo() {
		return (Engineer)eDynamicGet(RigelPackage.ISSUE__ASSIGNED_TO, RigelPackage.Literals.ISSUE__ASSIGNED_TO, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssignedTo(Engineer newAssignedTo, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newAssignedTo, RigelPackage.ISSUE__ASSIGNED_TO, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAssignedTo(Engineer newAssignedTo) {
		eDynamicSet(RigelPackage.ISSUE__ASSIGNED_TO, RigelPackage.Literals.ISSUE__ASSIGNED_TO, newAssignedTo);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getSize() {
		return (Double)eDynamicGet(RigelPackage.ISSUE__SIZE, RigelPackage.Literals.ISSUE__SIZE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSize(double newSize) {
		eDynamicSet(RigelPackage.ISSUE__SIZE, RigelPackage.Literals.ISSUE__SIZE, newSize);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getBenefit() {
		return (Double)eDynamicGet(RigelPackage.ISSUE__BENEFIT, RigelPackage.Literals.ISSUE__BENEFIT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBenefit(double newBenefit) {
		eDynamicSet(RigelPackage.ISSUE__BENEFIT, RigelPackage.Literals.ISSUE__BENEFIT, newBenefit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Issue> getChildren() {
		return (EList<Issue>)eDynamicGet(RigelPackage.ISSUE__CHILDREN, RigelPackage.Literals.ISSUE__CHILDREN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Activity getImplementation() {
		return (Activity)eDynamicGet(RigelPackage.ISSUE__IMPLEMENTATION, RigelPackage.Literals.ISSUE__IMPLEMENTATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity basicGetImplementation() {
		return (Activity)eDynamicGet(RigelPackage.ISSUE__IMPLEMENTATION, RigelPackage.Literals.ISSUE__IMPLEMENTATION, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImplementation(Activity newImplementation) {
		eDynamicSet(RigelPackage.ISSUE__IMPLEMENTATION, RigelPackage.Literals.ISSUE__IMPLEMENTATION, newImplementation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Capability> getRequiredCapabilities() {
		return (EList<Capability>)eDynamicGet(RigelPackage.ISSUE__REQUIRED_CAPABILITIES, RigelPackage.Literals.REQUIREMENT__REQUIRED_CAPABILITIES, true, true);
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
			case RigelPackage.ISSUE__REQUIRED_CAPABILITIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRequiredCapabilities()).basicAdd(otherEnd, msgs);
			case RigelPackage.ISSUE__ASSIGNED_TO:
				Engineer assignedTo = basicGetAssignedTo();
				if (assignedTo != null)
					msgs = ((InternalEObject)assignedTo).eInverseRemove(this, RigelPackage.ENGINEER__ASSIGNMENTS, Engineer.class, msgs);
				return basicSetAssignedTo((Engineer)otherEnd, msgs);
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
			case RigelPackage.ISSUE__REQUIRED_CAPABILITIES:
				return ((InternalEList<?>)getRequiredCapabilities()).basicRemove(otherEnd, msgs);
			case RigelPackage.ISSUE__ASSIGNED_TO:
				return basicSetAssignedTo(null, msgs);
			case RigelPackage.ISSUE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
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
			case RigelPackage.ISSUE__REQUIRED_CAPABILITIES:
				return getRequiredCapabilities();
			case RigelPackage.ISSUE__IMPORTANCE:
				return getImportance();
			case RigelPackage.ISSUE__STATUS:
				return getStatus();
			case RigelPackage.ISSUE__ASSIGNED_TO:
				if (resolve) return getAssignedTo();
				return basicGetAssignedTo();
			case RigelPackage.ISSUE__SIZE:
				return getSize();
			case RigelPackage.ISSUE__BENEFIT:
				return getBenefit();
			case RigelPackage.ISSUE__CHILDREN:
				return getChildren();
			case RigelPackage.ISSUE__IMPLEMENTATION:
				if (resolve) return getImplementation();
				return basicGetImplementation();
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
			case RigelPackage.ISSUE__REQUIRED_CAPABILITIES:
				getRequiredCapabilities().clear();
				getRequiredCapabilities().addAll((Collection<? extends Capability>)newValue);
				return;
			case RigelPackage.ISSUE__IMPORTANCE:
				setImportance((IssueImportance)newValue);
				return;
			case RigelPackage.ISSUE__STATUS:
				setStatus((IssueStatus)newValue);
				return;
			case RigelPackage.ISSUE__ASSIGNED_TO:
				setAssignedTo((Engineer)newValue);
				return;
			case RigelPackage.ISSUE__SIZE:
				setSize((Double)newValue);
				return;
			case RigelPackage.ISSUE__BENEFIT:
				setBenefit((Double)newValue);
				return;
			case RigelPackage.ISSUE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends Issue>)newValue);
				return;
			case RigelPackage.ISSUE__IMPLEMENTATION:
				setImplementation((Activity)newValue);
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
			case RigelPackage.ISSUE__REQUIRED_CAPABILITIES:
				getRequiredCapabilities().clear();
				return;
			case RigelPackage.ISSUE__IMPORTANCE:
				setImportance(IMPORTANCE_EDEFAULT);
				return;
			case RigelPackage.ISSUE__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case RigelPackage.ISSUE__ASSIGNED_TO:
				setAssignedTo((Engineer)null);
				return;
			case RigelPackage.ISSUE__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case RigelPackage.ISSUE__BENEFIT:
				setBenefit(BENEFIT_EDEFAULT);
				return;
			case RigelPackage.ISSUE__CHILDREN:
				getChildren().clear();
				return;
			case RigelPackage.ISSUE__IMPLEMENTATION:
				setImplementation((Activity)null);
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
			case RigelPackage.ISSUE__REQUIRED_CAPABILITIES:
				return !getRequiredCapabilities().isEmpty();
			case RigelPackage.ISSUE__IMPORTANCE:
				return getImportance() != IMPORTANCE_EDEFAULT;
			case RigelPackage.ISSUE__STATUS:
				return getStatus() != STATUS_EDEFAULT;
			case RigelPackage.ISSUE__ASSIGNED_TO:
				return basicGetAssignedTo() != null;
			case RigelPackage.ISSUE__SIZE:
				return getSize() != SIZE_EDEFAULT;
			case RigelPackage.ISSUE__BENEFIT:
				return getBenefit() != BENEFIT_EDEFAULT;
			case RigelPackage.ISSUE__CHILDREN:
				return !getChildren().isEmpty();
			case RigelPackage.ISSUE__IMPLEMENTATION:
				return basicGetImplementation() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Requirement.class) {
			switch (derivedFeatureID) {
				case RigelPackage.ISSUE__REQUIRED_CAPABILITIES: return RigelPackage.REQUIREMENT__REQUIRED_CAPABILITIES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Requirement.class) {
			switch (baseFeatureID) {
				case RigelPackage.REQUIREMENT__REQUIRED_CAPABILITIES: return RigelPackage.ISSUE__REQUIRED_CAPABILITIES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //IssueImpl
