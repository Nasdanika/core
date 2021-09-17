/**
 */
package org.nasdanika.exec.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.exec.Call;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.impl.CallImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.exec.impl.CallImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.nasdanika.exec.impl.CallImpl#getService <em>Service</em>}</li>
 *   <li>{@link org.nasdanika.exec.impl.CallImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link org.nasdanika.exec.impl.CallImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.nasdanika.exec.impl.CallImpl#getInit <em>Init</em>}</li>
 *   <li>{@link org.nasdanika.exec.impl.CallImpl#getArguments <em>Arguments</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CallImpl extends ModelElementImpl implements Call {
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
	 * The default value of the '{@link #getProperty() <em>Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected static final String PROPERTY_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getService() <em>Service</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getService()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVICE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getMethod() <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected static final String METHOD_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExecPackage.Literals.CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getType() {
		return (String)eDynamicGet(ExecPackage.CALL__TYPE, ExecPackage.Literals.CALL__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		eDynamicSet(ExecPackage.CALL__TYPE, ExecPackage.Literals.CALL__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProperty() {
		return (String)eDynamicGet(ExecPackage.CALL__PROPERTY, ExecPackage.Literals.CALL__PROPERTY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProperty(String newProperty) {
		eDynamicSet(ExecPackage.CALL__PROPERTY, ExecPackage.Literals.CALL__PROPERTY, newProperty);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getService() {
		return (String)eDynamicGet(ExecPackage.CALL__SERVICE, ExecPackage.Literals.CALL__SERVICE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setService(String newService) {
		eDynamicSet(ExecPackage.CALL__SERVICE, ExecPackage.Literals.CALL__SERVICE, newService);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMethod() {
		return (String)eDynamicGet(ExecPackage.CALL__METHOD, ExecPackage.Literals.CALL__METHOD, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMethod(String newMethod) {
		eDynamicSet(ExecPackage.CALL__METHOD, ExecPackage.Literals.CALL__METHOD, newMethod);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, EObject> getProperties() {
		return (EMap<String, EObject>)eDynamicGet(ExecPackage.CALL__PROPERTIES, ExecPackage.Literals.CALL__PROPERTIES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getInit() {
		return (EList<EObject>)eDynamicGet(ExecPackage.CALL__INIT, ExecPackage.Literals.CALL__INIT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getArguments() {
		return (EList<EObject>)eDynamicGet(ExecPackage.CALL__ARGUMENTS, ExecPackage.Literals.CALL__ARGUMENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExecPackage.CALL__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ExecPackage.CALL__INIT:
				return ((InternalEList<?>)getInit()).basicRemove(otherEnd, msgs);
			case ExecPackage.CALL__ARGUMENTS:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
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
			case ExecPackage.CALL__TYPE:
				return getType();
			case ExecPackage.CALL__PROPERTY:
				return getProperty();
			case ExecPackage.CALL__SERVICE:
				return getService();
			case ExecPackage.CALL__METHOD:
				return getMethod();
			case ExecPackage.CALL__PROPERTIES:
				if (coreType) return getProperties();
				else return getProperties().map();
			case ExecPackage.CALL__INIT:
				return getInit();
			case ExecPackage.CALL__ARGUMENTS:
				return getArguments();
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
			case ExecPackage.CALL__TYPE:
				setType((String)newValue);
				return;
			case ExecPackage.CALL__PROPERTY:
				setProperty((String)newValue);
				return;
			case ExecPackage.CALL__SERVICE:
				setService((String)newValue);
				return;
			case ExecPackage.CALL__METHOD:
				setMethod((String)newValue);
				return;
			case ExecPackage.CALL__PROPERTIES:
				((EStructuralFeature.Setting)getProperties()).set(newValue);
				return;
			case ExecPackage.CALL__INIT:
				getInit().clear();
				getInit().addAll((Collection<? extends EObject>)newValue);
				return;
			case ExecPackage.CALL__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends EObject>)newValue);
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
			case ExecPackage.CALL__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ExecPackage.CALL__PROPERTY:
				setProperty(PROPERTY_EDEFAULT);
				return;
			case ExecPackage.CALL__SERVICE:
				setService(SERVICE_EDEFAULT);
				return;
			case ExecPackage.CALL__METHOD:
				setMethod(METHOD_EDEFAULT);
				return;
			case ExecPackage.CALL__PROPERTIES:
				getProperties().clear();
				return;
			case ExecPackage.CALL__INIT:
				getInit().clear();
				return;
			case ExecPackage.CALL__ARGUMENTS:
				getArguments().clear();
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
			case ExecPackage.CALL__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
			case ExecPackage.CALL__PROPERTY:
				return PROPERTY_EDEFAULT == null ? getProperty() != null : !PROPERTY_EDEFAULT.equals(getProperty());
			case ExecPackage.CALL__SERVICE:
				return SERVICE_EDEFAULT == null ? getService() != null : !SERVICE_EDEFAULT.equals(getService());
			case ExecPackage.CALL__METHOD:
				return METHOD_EDEFAULT == null ? getMethod() != null : !METHOD_EDEFAULT.equals(getMethod());
			case ExecPackage.CALL__PROPERTIES:
				return !getProperties().isEmpty();
			case ExecPackage.CALL__INIT:
				return !getInit().isEmpty();
			case ExecPackage.CALL__ARGUMENTS:
				return !getArguments().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CallImpl
