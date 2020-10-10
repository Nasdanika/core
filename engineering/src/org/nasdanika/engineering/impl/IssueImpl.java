/**
 */
package org.nasdanika.engineering.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.engineering.AbstractEngineer;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Increment;
import org.nasdanika.engineering.Issue;
import org.nasdanika.engineering.IssueCategory;
import org.nasdanika.engineering.IssueNote;
import org.nasdanika.engineering.IssueRelationship;
import org.nasdanika.engineering.IssueResolution;
import org.nasdanika.engineering.IssueStatus;
import org.nasdanika.engineering.IssueType;
import org.nasdanika.engineering.Release;
import org.nasdanika.ncore.impl.EntityImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Issue</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getAssignedTo <em>Assigned To</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getSize <em>Size</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getCost <em>Cost</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getBenefit <em>Benefit</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getPlannedFor <em>Planned For</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getResolution <em>Resolution</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getReleases <em>Releases</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getRelationships <em>Relationships</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#getRequires <em>Requires</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.IssueImpl#isActionable <em>Actionable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IssueImpl extends EntityImpl implements Issue {
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
	 * The default value of the '{@link #getCost() <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCost()
	 * @generated
	 * @ordered
	 */
	protected static final double COST_EDEFAULT = 0.0;

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
	 * The default value of the '{@link #isActionable() <em>Actionable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActionable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIONABLE_EDEFAULT = false;

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
	@SuppressWarnings("unchecked")
	@Override
	public EList<AbstractEngineer> getAssignedTo() {
		return (EList<AbstractEngineer>)eDynamicGet(EngineeringPackage.ISSUE__ASSIGNED_TO, EngineeringPackage.Literals.ISSUE__ASSIGNED_TO, true, true);
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
	public double getCost() {
		return (Double)eDynamicGet(EngineeringPackage.ISSUE__COST, EngineeringPackage.Literals.ISSUE__COST, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCost(double newCost) {
		eDynamicSet(EngineeringPackage.ISSUE__COST, EngineeringPackage.Literals.ISSUE__COST, newCost);
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
	public IssueType getType() {
		return (IssueType)eDynamicGet(EngineeringPackage.ISSUE__TYPE, EngineeringPackage.Literals.ISSUE__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IssueType basicGetType() {
		return (IssueType)eDynamicGet(EngineeringPackage.ISSUE__TYPE, EngineeringPackage.Literals.ISSUE__TYPE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(IssueType newType) {
		eDynamicSet(EngineeringPackage.ISSUE__TYPE, EngineeringPackage.Literals.ISSUE__TYPE, newType);
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
	public IssueStatus basicGetStatus() {
		return (IssueStatus)eDynamicGet(EngineeringPackage.ISSUE__STATUS, EngineeringPackage.Literals.ISSUE__STATUS, false, true);
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
	public IssueResolution getResolution() {
		return (IssueResolution)eDynamicGet(EngineeringPackage.ISSUE__RESOLUTION, EngineeringPackage.Literals.ISSUE__RESOLUTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IssueResolution basicGetResolution() {
		return (IssueResolution)eDynamicGet(EngineeringPackage.ISSUE__RESOLUTION, EngineeringPackage.Literals.ISSUE__RESOLUTION, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResolution(IssueResolution newResolution) {
		eDynamicSet(EngineeringPackage.ISSUE__RESOLUTION, EngineeringPackage.Literals.ISSUE__RESOLUTION, newResolution);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<IssueCategory> getCategories() {
		return (EList<IssueCategory>)eDynamicGet(EngineeringPackage.ISSUE__CATEGORIES, EngineeringPackage.Literals.ISSUE__CATEGORIES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<IssueNote> getNotes() {
		return (EList<IssueNote>)eDynamicGet(EngineeringPackage.ISSUE__NOTES, EngineeringPackage.Literals.ISSUE__NOTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Release> getReleases() {
		return (EList<Release>)eDynamicGet(EngineeringPackage.ISSUE__RELEASES, EngineeringPackage.Literals.ISSUE__RELEASES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<IssueRelationship> getRelationships() {
		return (EList<IssueRelationship>)eDynamicGet(EngineeringPackage.ISSUE__RELATIONSHIPS, EngineeringPackage.Literals.ISSUE__RELATIONSHIPS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Release> getRequires() {
		return (EList<Release>)eDynamicGet(EngineeringPackage.ISSUE__REQUIRES, EngineeringPackage.Literals.ISSUE__REQUIRES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isActionable() {
		TreeIterator<EObject> tit = eAllContents();
		while (tit.hasNext()) {
			EObject next = tit.next();
			if (next instanceof IssueRelationship) {
				IssueRelationship rel = (IssueRelationship) next;
				if (rel.getType() != null && rel.getType().isBlocks()) {
					Issue source = rel.getSource();
					if (source != null && !EcoreUtil.isAncestor(this, source) && (source.getResolution() == null || !source.getResolution().isCompleted())) {
						return false;
					}
				}
			}
		}
		for (IssueRelationship rel: getRelationships()) {
			if (rel.getType() != null && rel.getType().isBlocks()) {
				Issue source = rel.getSource();
				if (source.getResolution() == null || !source.getResolution().isCompleted()) {
					return false;
				}
			}
		}
		for (Release rel: getRequires()) {
			if (!rel.isReleased()) {
				return false;
			}
		}
		
		return true;
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
			case EngineeringPackage.ISSUE__NOTES:
				return ((InternalEList<?>)getNotes()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.ISSUE__RELATIONSHIPS:
				return ((InternalEList<?>)getRelationships()).basicRemove(otherEnd, msgs);
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
			case EngineeringPackage.ISSUE__ASSIGNED_TO:
				return getAssignedTo();
			case EngineeringPackage.ISSUE__SIZE:
				return getSize();
			case EngineeringPackage.ISSUE__COST:
				return getCost();
			case EngineeringPackage.ISSUE__BENEFIT:
				return getBenefit();
			case EngineeringPackage.ISSUE__PLANNED_FOR:
				if (resolve) return getPlannedFor();
				return basicGetPlannedFor();
			case EngineeringPackage.ISSUE__CHILDREN:
				return getChildren();
			case EngineeringPackage.ISSUE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case EngineeringPackage.ISSUE__STATUS:
				if (resolve) return getStatus();
				return basicGetStatus();
			case EngineeringPackage.ISSUE__RESOLUTION:
				if (resolve) return getResolution();
				return basicGetResolution();
			case EngineeringPackage.ISSUE__CATEGORIES:
				return getCategories();
			case EngineeringPackage.ISSUE__NOTES:
				return getNotes();
			case EngineeringPackage.ISSUE__RELEASES:
				return getReleases();
			case EngineeringPackage.ISSUE__RELATIONSHIPS:
				return getRelationships();
			case EngineeringPackage.ISSUE__REQUIRES:
				return getRequires();
			case EngineeringPackage.ISSUE__ACTIONABLE:
				return isActionable();
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
			case EngineeringPackage.ISSUE__ASSIGNED_TO:
				getAssignedTo().clear();
				getAssignedTo().addAll((Collection<? extends AbstractEngineer>)newValue);
				return;
			case EngineeringPackage.ISSUE__SIZE:
				setSize((Double)newValue);
				return;
			case EngineeringPackage.ISSUE__COST:
				setCost((Double)newValue);
				return;
			case EngineeringPackage.ISSUE__BENEFIT:
				setBenefit((Double)newValue);
				return;
			case EngineeringPackage.ISSUE__PLANNED_FOR:
				setPlannedFor((Increment)newValue);
				return;
			case EngineeringPackage.ISSUE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends Issue>)newValue);
				return;
			case EngineeringPackage.ISSUE__TYPE:
				setType((IssueType)newValue);
				return;
			case EngineeringPackage.ISSUE__STATUS:
				setStatus((IssueStatus)newValue);
				return;
			case EngineeringPackage.ISSUE__RESOLUTION:
				setResolution((IssueResolution)newValue);
				return;
			case EngineeringPackage.ISSUE__CATEGORIES:
				getCategories().clear();
				getCategories().addAll((Collection<? extends IssueCategory>)newValue);
				return;
			case EngineeringPackage.ISSUE__NOTES:
				getNotes().clear();
				getNotes().addAll((Collection<? extends IssueNote>)newValue);
				return;
			case EngineeringPackage.ISSUE__RELEASES:
				getReleases().clear();
				getReleases().addAll((Collection<? extends Release>)newValue);
				return;
			case EngineeringPackage.ISSUE__RELATIONSHIPS:
				getRelationships().clear();
				getRelationships().addAll((Collection<? extends IssueRelationship>)newValue);
				return;
			case EngineeringPackage.ISSUE__REQUIRES:
				getRequires().clear();
				getRequires().addAll((Collection<? extends Release>)newValue);
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
			case EngineeringPackage.ISSUE__ASSIGNED_TO:
				getAssignedTo().clear();
				return;
			case EngineeringPackage.ISSUE__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case EngineeringPackage.ISSUE__COST:
				setCost(COST_EDEFAULT);
				return;
			case EngineeringPackage.ISSUE__BENEFIT:
				setBenefit(BENEFIT_EDEFAULT);
				return;
			case EngineeringPackage.ISSUE__PLANNED_FOR:
				setPlannedFor((Increment)null);
				return;
			case EngineeringPackage.ISSUE__CHILDREN:
				getChildren().clear();
				return;
			case EngineeringPackage.ISSUE__TYPE:
				setType((IssueType)null);
				return;
			case EngineeringPackage.ISSUE__STATUS:
				setStatus((IssueStatus)null);
				return;
			case EngineeringPackage.ISSUE__RESOLUTION:
				setResolution((IssueResolution)null);
				return;
			case EngineeringPackage.ISSUE__CATEGORIES:
				getCategories().clear();
				return;
			case EngineeringPackage.ISSUE__NOTES:
				getNotes().clear();
				return;
			case EngineeringPackage.ISSUE__RELEASES:
				getReleases().clear();
				return;
			case EngineeringPackage.ISSUE__RELATIONSHIPS:
				getRelationships().clear();
				return;
			case EngineeringPackage.ISSUE__REQUIRES:
				getRequires().clear();
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
			case EngineeringPackage.ISSUE__ASSIGNED_TO:
				return !getAssignedTo().isEmpty();
			case EngineeringPackage.ISSUE__SIZE:
				return getSize() != SIZE_EDEFAULT;
			case EngineeringPackage.ISSUE__COST:
				return getCost() != COST_EDEFAULT;
			case EngineeringPackage.ISSUE__BENEFIT:
				return getBenefit() != BENEFIT_EDEFAULT;
			case EngineeringPackage.ISSUE__PLANNED_FOR:
				return basicGetPlannedFor() != null;
			case EngineeringPackage.ISSUE__CHILDREN:
				return !getChildren().isEmpty();
			case EngineeringPackage.ISSUE__TYPE:
				return basicGetType() != null;
			case EngineeringPackage.ISSUE__STATUS:
				return basicGetStatus() != null;
			case EngineeringPackage.ISSUE__RESOLUTION:
				return basicGetResolution() != null;
			case EngineeringPackage.ISSUE__CATEGORIES:
				return !getCategories().isEmpty();
			case EngineeringPackage.ISSUE__NOTES:
				return !getNotes().isEmpty();
			case EngineeringPackage.ISSUE__RELEASES:
				return !getReleases().isEmpty();
			case EngineeringPackage.ISSUE__RELATIONSHIPS:
				return !getRelationships().isEmpty();
			case EngineeringPackage.ISSUE__REQUIRES:
				return !getRequires().isEmpty();
			case EngineeringPackage.ISSUE__ACTIONABLE:
				return isActionable() != ACTIONABLE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //IssueImpl
