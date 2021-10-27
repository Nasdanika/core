/**
 */
package org.nasdanika.flow.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.ArtifactParticipantResponsibility;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Artifact Participant Responsibility</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactParticipantResponsibilityImpl#getArtifactKey <em>Artifact Key</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactParticipantResponsibilityImpl#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.ArtifactParticipantResponsibilityImpl#isSuppress <em>Suppress</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArtifactParticipantResponsibilityImpl extends ParticipantResponsibilityImpl<ArtifactParticipantResponsibility> implements ArtifactParticipantResponsibility {
	/**
	 * The default value of the '{@link #getArtifactKey() <em>Artifact Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactKey()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACT_KEY_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isSuppress() <em>Suppress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSuppress()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPRESS_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArtifactParticipantResponsibilityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.ARTIFACT_PARTICIPANT_RESPONSIBILITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getArtifactKey() {
		return (String)eDynamicGet(FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT_KEY, FlowPackage.Literals.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT_KEY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setArtifactKey(String newArtifactKey) {
		eDynamicSet(FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT_KEY, FlowPackage.Literals.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT_KEY, newArtifactKey);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Artifact getArtifact() {
		URI artifactURI = URI.createURI(getArtifactKey());
		URI pkgURI = getPackageFeatureURI(FlowPackage.Literals.PACKAGE__ARTIFACTS);
		if (pkgURI != null) {
			artifactURI = artifactURI.resolve(pkgURI);
		}
		return resolveArtifact(artifactURI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Artifact basicGetArtifact() {
		return (Artifact)eDynamicGet(FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT, FlowPackage.Literals.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArtifact(Artifact newArtifact, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newArtifact, FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSuppress() {
		return (Boolean)eDynamicGet(FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__SUPPRESS, FlowPackage.Literals.ARTIFACT_PARTICIPANT_RESPONSIBILITY__SUPPRESS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSuppress(boolean newSuppress) {
		eDynamicSet(FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__SUPPRESS, FlowPackage.Literals.ARTIFACT_PARTICIPANT_RESPONSIBILITY__SUPPRESS, newSuppress);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT:
				Artifact artifact = basicGetArtifact();
				if (artifact != null)
					msgs = ((InternalEObject)artifact).eInverseRemove(this, FlowPackage.ARTIFACT__RESPONSIBILITIES, Artifact.class, msgs);
				return basicSetArtifact((Artifact)otherEnd, msgs);
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
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT:
				return basicSetArtifact(null, msgs);
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
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT_KEY:
				return getArtifactKey();
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT:
				if (resolve) return getArtifact();
				return basicGetArtifact();
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__SUPPRESS:
				return isSuppress();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT_KEY:
				setArtifactKey((String)newValue);
				return;
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__SUPPRESS:
				setSuppress((Boolean)newValue);
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
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT_KEY:
				setArtifactKey(ARTIFACT_KEY_EDEFAULT);
				return;
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__SUPPRESS:
				setSuppress(SUPPRESS_EDEFAULT);
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
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT_KEY:
				return ARTIFACT_KEY_EDEFAULT == null ? getArtifactKey() != null : !ARTIFACT_KEY_EDEFAULT.equals(getArtifactKey());
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT:
				return basicGetArtifact() != null;
			case FlowPackage.ARTIFACT_PARTICIPANT_RESPONSIBILITY__SUPPRESS:
				return isSuppress() != SUPPRESS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public EList<ArtifactParticipantResponsibility> getExtends() {
		EList<ArtifactParticipantResponsibility> ret = ECollections.newBasicEList();
		if (eContainmentFeature() == FlowPackage.Literals.FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES) {
			String key = getArtifactKey();
			FlowElement<?> container = (FlowElement<?>) eContainer();
			for (FlowElement<?> cExtends: container.getExtends()) {
				for (ArtifactParticipantResponsibility apr: cExtends.getArtifactResponsibilities()) {
					if (key.equals(apr.getArtifactKey())) {
						ret.add(apr);
					}
				}
			}
		}
		
		return ret;
	}		

} //ArtifactParticipantResponsibilityImpl
