/**
 */
package org.nasdanika.engineering.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.engineering.Engineer;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Increment;
import org.nasdanika.engineering.Issue;
import org.nasdanika.engineering.IssueImportance;
import org.nasdanika.engineering.IssueStatus;
import org.nasdanika.ncore.impl.EntityImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Issue</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getImportance <em>Importance</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getAssignedTo <em>Assigned To</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getSize <em>Size</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getBenefit <em>Benefit</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getPlannedFor <em>Planned For</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IssueImpl extends EntityImpl implements Issue {
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
		return EngineeringPackage.Literals.ISSUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IssueImportance getImportance() {
		return (IssueImportance)eDynamicGet(EngineeringPackage.ISSUE__IMPORTANCE, EngineeringPackage.Literals.ISSUE__IMPORTANCE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImportance(IssueImportance newImportance) {
		eDynamicSet(EngineeringPackage.ISSUE__IMPORTANCE, EngineeringPackage.Literals.ISSUE__IMPORTANCE, newImportance);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IssueStatus getStatus() {
		return (IssueStatus)eDynamicGet(EngineeringPackage.ISSUE__STATUS, EngineeringPackage.Literals.ISSUE__STATUS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStatus(IssueStatus newStatus) {
		eDynamicSet(EngineeringPackage.ISSUE__STATUS, EngineeringPackage.Literals.ISSUE__STATUS, newStatus);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Engineer getAssignedTo() {
		return (Engineer)eDynamicGet(EngineeringPackage.ISSUE__ASSIGNED_TO, EngineeringPackage.Literals.ISSUE__ASSIGNED_TO, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Engineer basicGetAssignedTo() {
		return (Engineer)eDynamicGet(EngineeringPackage.ISSUE__ASSIGNED_TO, EngineeringPackage.Literals.ISSUE__ASSIGNED_TO, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAssignedTo(Engineer newAssignedTo) {
		eDynamicSet(EngineeringPackage.ISSUE__ASSIGNED_TO, EngineeringPackage.Literals.ISSUE__ASSIGNED_TO, newAssignedTo);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getSize() {
		return (Double)eDynamicGet(EngineeringPackage.ISSUE__SIZE, EngineeringPackage.Literals.ISSUE__SIZE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSize(double newSize) {
		eDynamicSet(EngineeringPackage.ISSUE__SIZE, EngineeringPackage.Literals.ISSUE__SIZE, newSize);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getBenefit() {
		return (Double)eDynamicGet(EngineeringPackage.ISSUE__BENEFIT, EngineeringPackage.Literals.ISSUE__BENEFIT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBenefit(double newBenefit) {
		eDynamicSet(EngineeringPackage.ISSUE__BENEFIT, EngineeringPackage.Literals.ISSUE__BENEFIT, newBenefit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Issue> getChildren() {
		return (EList<Issue>)eDynamicGet(EngineeringPackage.ISSUE__CHILDREN, EngineeringPackage.Literals.ISSUE__CHILDREN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Increment getPlannedFor() {
		return (Increment)eDynamicGet(EngineeringPackage.ISSUE__PLANNED_FOR, EngineeringPackage.Literals.ISSUE__PLANNED_FOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Increment basicGetPlannedFor() {
		return (Increment)eDynamicGet(EngineeringPackage.ISSUE__PLANNED_FOR, EngineeringPackage.Literals.ISSUE__PLANNED_FOR, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPlannedFor(Increment newPlannedFor) {
		eDynamicSet(EngineeringPackage.ISSUE__PLANNED_FOR, EngineeringPackage.Literals.ISSUE__PLANNED_FOR, newPlannedFor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EngineeringPackage.ISSUE__CHILDREN:
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
			case EngineeringPackage.ISSUE__IMPORTANCE:
				return getImportance();
			case EngineeringPackage.ISSUE__STATUS:
				return getStatus();
			case EngineeringPackage.ISSUE__ASSIGNED_TO:
				if (resolve) return getAssignedTo();
				return basicGetAssignedTo();
			case EngineeringPackage.ISSUE__SIZE:
				return getSize();
			case EngineeringPackage.ISSUE__BENEFIT:
				return getBenefit();
			case EngineeringPackage.ISSUE__CHILDREN:
				return getChildren();
			case EngineeringPackage.ISSUE__PLANNED_FOR:
				if (resolve) return getPlannedFor();
				return basicGetPlannedFor();
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
			case EngineeringPackage.ISSUE__IMPORTANCE:
				setImportance((IssueImportance)newValue);
				return;
			case EngineeringPackage.ISSUE__STATUS:
				setStatus((IssueStatus)newValue);
				return;
			case EngineeringPackage.ISSUE__ASSIGNED_TO:
				setAssignedTo((Engineer)newValue);
				return;
			case EngineeringPackage.ISSUE__SIZE:
				setSize((Double)newValue);
				return;
			case EngineeringPackage.ISSUE__BENEFIT:
				setBenefit((Double)newValue);
				return;
			case EngineeringPackage.ISSUE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends Issue>)newValue);
				return;
			case EngineeringPackage.ISSUE__PLANNED_FOR:
				setPlannedFor((Increment)newValue);
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
			case EngineeringPackage.ISSUE__IMPORTANCE:
				setImportance(IMPORTANCE_EDEFAULT);
				return;
			case EngineeringPackage.ISSUE__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case EngineeringPackage.ISSUE__ASSIGNED_TO:
				setAssignedTo((Engineer)null);
				return;
			case EngineeringPackage.ISSUE__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case EngineeringPackage.ISSUE__BENEFIT:
				setBenefit(BENEFIT_EDEFAULT);
				return;
			case EngineeringPackage.ISSUE__CHILDREN:
				getChildren().clear();
				return;
			case EngineeringPackage.ISSUE__PLANNED_FOR:
				setPlannedFor((Increment)null);
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
			case EngineeringPackage.ISSUE__IMPORTANCE:
				return getImportance() != IMPORTANCE_EDEFAULT;
			case EngineeringPackage.ISSUE__STATUS:
				return getStatus() != STATUS_EDEFAULT;
			case EngineeringPackage.ISSUE__ASSIGNED_TO:
				return basicGetAssignedTo() != null;
			case EngineeringPackage.ISSUE__SIZE:
				return getSize() != SIZE_EDEFAULT;
			case EngineeringPackage.ISSUE__BENEFIT:
				return getBenefit() != BENEFIT_EDEFAULT;
			case EngineeringPackage.ISSUE__CHILDREN:
				return !getChildren().isEmpty();
			case EngineeringPackage.ISSUE__PLANNED_FOR:
				return basicGetPlannedFor() != null;
		}
		return super.eIsSet(featureID);
	}

} //IssueImpl
