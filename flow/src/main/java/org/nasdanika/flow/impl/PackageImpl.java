/**
 */
package org.nasdanika.flow.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.flow.Activity;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.FlowPackage;

import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Resource;
import org.nasdanika.ncore.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getSubPackages <em>Sub Packages</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageImpl#getActivities <em>Activities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PackageImpl extends NamedElementImpl implements org.nasdanika.flow.Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<org.nasdanika.flow.Package> getExtends() {
		return (EList<org.nasdanika.flow.Package>)eDynamicGet(FlowPackage.PACKAGE__EXTENDS, FlowPackage.Literals.PACKAGE__EXTENDS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<org.nasdanika.flow.Package> getExtensions() {
		return (EList<org.nasdanika.flow.Package>)eDynamicGet(FlowPackage.PACKAGE__EXTENSIONS, FlowPackage.Literals.PACKAGE__EXTENSIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, org.nasdanika.flow.Package> getSubPackages() {
		return (EMap<String, org.nasdanika.flow.Package>)eDynamicGet(FlowPackage.PACKAGE__SUB_PACKAGES, FlowPackage.Literals.PACKAGE__SUB_PACKAGES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Participant> getParticipants() {
		return (EMap<String, Participant>)eDynamicGet(FlowPackage.PACKAGE__PARTICIPANTS, FlowPackage.Literals.PACKAGE__PARTICIPANTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Resource> getResources() {
		return (EMap<String, Resource>)eDynamicGet(FlowPackage.PACKAGE__RESOURCES, FlowPackage.Literals.PACKAGE__RESOURCES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Artifact> getArtifacts() {
		return (EMap<String, Artifact>)eDynamicGet(FlowPackage.PACKAGE__ARTIFACTS, FlowPackage.Literals.PACKAGE__ARTIFACTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Activity> getActivities() {
		return (EMap<String, Activity>)eDynamicGet(FlowPackage.PACKAGE__ACTIVITIES, FlowPackage.Literals.PACKAGE__ACTIVITIES, true, true);
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
			case FlowPackage.PACKAGE__EXTENDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtends()).basicAdd(otherEnd, msgs);
			case FlowPackage.PACKAGE__EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtensions()).basicAdd(otherEnd, msgs);
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
			case FlowPackage.PACKAGE__EXTENDS:
				return ((InternalEList<?>)getExtends()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE__EXTENSIONS:
				return ((InternalEList<?>)getExtensions()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE__SUB_PACKAGES:
				return ((InternalEList<?>)getSubPackages()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE__PARTICIPANTS:
				return ((InternalEList<?>)getParticipants()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE__RESOURCES:
				return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE__ARTIFACTS:
				return ((InternalEList<?>)getArtifacts()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE__ACTIVITIES:
				return ((InternalEList<?>)getActivities()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.PACKAGE__EXTENDS:
				return getExtends();
			case FlowPackage.PACKAGE__EXTENSIONS:
				return getExtensions();
			case FlowPackage.PACKAGE__SUB_PACKAGES:
				if (coreType) return getSubPackages();
				else return getSubPackages().map();
			case FlowPackage.PACKAGE__PARTICIPANTS:
				if (coreType) return getParticipants();
				else return getParticipants().map();
			case FlowPackage.PACKAGE__RESOURCES:
				if (coreType) return getResources();
				else return getResources().map();
			case FlowPackage.PACKAGE__ARTIFACTS:
				if (coreType) return getArtifacts();
				else return getArtifacts().map();
			case FlowPackage.PACKAGE__ACTIVITIES:
				if (coreType) return getActivities();
				else return getActivities().map();
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
			case FlowPackage.PACKAGE__EXTENDS:
				getExtends().clear();
				getExtends().addAll((Collection<? extends org.nasdanika.flow.Package>)newValue);
				return;
			case FlowPackage.PACKAGE__SUB_PACKAGES:
				((EStructuralFeature.Setting)getSubPackages()).set(newValue);
				return;
			case FlowPackage.PACKAGE__PARTICIPANTS:
				((EStructuralFeature.Setting)getParticipants()).set(newValue);
				return;
			case FlowPackage.PACKAGE__RESOURCES:
				((EStructuralFeature.Setting)getResources()).set(newValue);
				return;
			case FlowPackage.PACKAGE__ARTIFACTS:
				((EStructuralFeature.Setting)getArtifacts()).set(newValue);
				return;
			case FlowPackage.PACKAGE__ACTIVITIES:
				((EStructuralFeature.Setting)getActivities()).set(newValue);
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
			case FlowPackage.PACKAGE__EXTENDS:
				getExtends().clear();
				return;
			case FlowPackage.PACKAGE__SUB_PACKAGES:
				getSubPackages().clear();
				return;
			case FlowPackage.PACKAGE__PARTICIPANTS:
				getParticipants().clear();
				return;
			case FlowPackage.PACKAGE__RESOURCES:
				getResources().clear();
				return;
			case FlowPackage.PACKAGE__ARTIFACTS:
				getArtifacts().clear();
				return;
			case FlowPackage.PACKAGE__ACTIVITIES:
				getActivities().clear();
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
			case FlowPackage.PACKAGE__EXTENDS:
				return !getExtends().isEmpty();
			case FlowPackage.PACKAGE__EXTENSIONS:
				return !getExtensions().isEmpty();
			case FlowPackage.PACKAGE__SUB_PACKAGES:
				return !getSubPackages().isEmpty();
			case FlowPackage.PACKAGE__PARTICIPANTS:
				return !getParticipants().isEmpty();
			case FlowPackage.PACKAGE__RESOURCES:
				return !getResources().isEmpty();
			case FlowPackage.PACKAGE__ARTIFACTS:
				return !getArtifacts().isEmpty();
			case FlowPackage.PACKAGE__ACTIVITIES:
				return !getActivities().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PackageImpl
