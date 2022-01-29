/**
 */
package org.nasdanika.ncore.impl;

import java.lang.String;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.ncore.GitMarker;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Git Marker</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.GitMarkerImpl#getPath <em>Path</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.GitMarkerImpl#getRemotes <em>Remotes</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.GitMarkerImpl#getBranch <em>Branch</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.GitMarkerImpl#getHead <em>Head</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.GitMarkerImpl#getHeadRefs <em>Head Refs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GitMarkerImpl extends MarkerImpl implements GitMarker {
	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getBranch() <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranch()
	 * @generated
	 * @ordered
	 */
	protected static final String BRANCH_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getHead() <em>Head</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHead()
	 * @generated
	 * @ordered
	 */
	protected static final String HEAD_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GitMarkerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.GIT_MARKER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPath() {
		return (String)eDynamicGet(NcorePackage.GIT_MARKER__PATH, NcorePackage.Literals.GIT_MARKER__PATH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPath(String newPath) {
		eDynamicSet(NcorePackage.GIT_MARKER__PATH, NcorePackage.Literals.GIT_MARKER__PATH, newPath);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, String> getRemotes() {
		return (EMap<String, String>)eDynamicGet(NcorePackage.GIT_MARKER__REMOTES, NcorePackage.Literals.GIT_MARKER__REMOTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBranch() {
		return (String)eDynamicGet(NcorePackage.GIT_MARKER__BRANCH, NcorePackage.Literals.GIT_MARKER__BRANCH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBranch(String newBranch) {
		eDynamicSet(NcorePackage.GIT_MARKER__BRANCH, NcorePackage.Literals.GIT_MARKER__BRANCH, newBranch);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHead() {
		return (String)eDynamicGet(NcorePackage.GIT_MARKER__HEAD, NcorePackage.Literals.GIT_MARKER__HEAD, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHead(String newHead) {
		eDynamicSet(NcorePackage.GIT_MARKER__HEAD, NcorePackage.Literals.GIT_MARKER__HEAD, newHead);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getHeadRefs() {
		return (EList<String>)eDynamicGet(NcorePackage.GIT_MARKER__HEAD_REFS, NcorePackage.Literals.GIT_MARKER__HEAD_REFS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NcorePackage.GIT_MARKER__REMOTES:
				return ((InternalEList<?>)getRemotes()).basicRemove(otherEnd, msgs);
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
			case NcorePackage.GIT_MARKER__PATH:
				return getPath();
			case NcorePackage.GIT_MARKER__REMOTES:
				if (coreType) return getRemotes();
				else return getRemotes().map();
			case NcorePackage.GIT_MARKER__BRANCH:
				return getBranch();
			case NcorePackage.GIT_MARKER__HEAD:
				return getHead();
			case NcorePackage.GIT_MARKER__HEAD_REFS:
				return getHeadRefs();
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
			case NcorePackage.GIT_MARKER__PATH:
				setPath((String)newValue);
				return;
			case NcorePackage.GIT_MARKER__REMOTES:
				((EStructuralFeature.Setting)getRemotes()).set(newValue);
				return;
			case NcorePackage.GIT_MARKER__BRANCH:
				setBranch((String)newValue);
				return;
			case NcorePackage.GIT_MARKER__HEAD:
				setHead((String)newValue);
				return;
			case NcorePackage.GIT_MARKER__HEAD_REFS:
				getHeadRefs().clear();
				getHeadRefs().addAll((Collection<? extends String>)newValue);
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
			case NcorePackage.GIT_MARKER__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case NcorePackage.GIT_MARKER__REMOTES:
				getRemotes().clear();
				return;
			case NcorePackage.GIT_MARKER__BRANCH:
				setBranch(BRANCH_EDEFAULT);
				return;
			case NcorePackage.GIT_MARKER__HEAD:
				setHead(HEAD_EDEFAULT);
				return;
			case NcorePackage.GIT_MARKER__HEAD_REFS:
				getHeadRefs().clear();
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
			case NcorePackage.GIT_MARKER__PATH:
				return PATH_EDEFAULT == null ? getPath() != null : !PATH_EDEFAULT.equals(getPath());
			case NcorePackage.GIT_MARKER__REMOTES:
				return !getRemotes().isEmpty();
			case NcorePackage.GIT_MARKER__BRANCH:
				return BRANCH_EDEFAULT == null ? getBranch() != null : !BRANCH_EDEFAULT.equals(getBranch());
			case NcorePackage.GIT_MARKER__HEAD:
				return HEAD_EDEFAULT == null ? getHead() != null : !HEAD_EDEFAULT.equals(getHead());
			case NcorePackage.GIT_MARKER__HEAD_REFS:
				return !getHeadRefs().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GitMarkerImpl
