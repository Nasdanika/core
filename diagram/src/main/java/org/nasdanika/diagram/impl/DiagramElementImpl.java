/**
 */
package org.nasdanika.diagram.impl;

import java.util.Collection;
import java.util.UUID;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getConnections <em>Connections</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DiagramElementImpl extends LinkImpl implements DiagramElement {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected DiagramElementImpl() {
		super();
		setId(UUID.randomUUID().toString().replace('-', '_'));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiagramPackage.Literals.DIAGRAM_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return (String)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__ID, DiagramPackage.Literals.DIAGRAM_ELEMENT__ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eDynamicSet(DiagramPackage.DIAGRAM_ELEMENT__ID, DiagramPackage.Literals.DIAGRAM_ELEMENT__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getType() {
		return (String)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__TYPE, DiagramPackage.Literals.DIAGRAM_ELEMENT__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		eDynamicSet(DiagramPackage.DIAGRAM_ELEMENT__TYPE, DiagramPackage.Literals.DIAGRAM_ELEMENT__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getName() {
		return (EList<EObject>)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__NAME, DiagramPackage.Literals.DIAGRAM_ELEMENT__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<DiagramElement> getElements() {
		return (EList<DiagramElement>)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS, DiagramPackage.Literals.DIAGRAM_ELEMENT__ELEMENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Connection> getConnections() {
		return (EList<Connection>)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS, DiagramPackage.Literals.DIAGRAM_ELEMENT__CONNECTIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DiagramPackage.DIAGRAM_ELEMENT__NAME:
				return ((InternalEList<?>)getName()).basicRemove(otherEnd, msgs);
			case DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
			case DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS:
				return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
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
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				return getId();
			case DiagramPackage.DIAGRAM_ELEMENT__TYPE:
				return getType();
			case DiagramPackage.DIAGRAM_ELEMENT__NAME:
				return getName();
			case DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS:
				return getElements();
			case DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS:
				return getConnections();
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
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				setId((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__TYPE:
				setType((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__NAME:
				getName().clear();
				getName().addAll((Collection<? extends EObject>)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends DiagramElement>)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection<? extends Connection>)newValue);
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
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__NAME:
				getName().clear();
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS:
				getElements().clear();
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS:
				getConnections().clear();
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
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case DiagramPackage.DIAGRAM_ELEMENT__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
			case DiagramPackage.DIAGRAM_ELEMENT__NAME:
				return !getName().isEmpty();
			case DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS:
				return !getElements().isEmpty();
			case DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS:
				return !getConnections().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DiagramElementImpl
