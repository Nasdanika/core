/**
 */
package org.nasdanika.telemetry.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.telemetry.model.InstrumentationScope;
import org.nasdanika.telemetry.model.KeyValue;
import org.nasdanika.telemetry.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instrumentation Scope</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.impl.InstrumentationScopeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.InstrumentationScopeImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.InstrumentationScopeImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.InstrumentationScopeImpl#getDroppedAttributesCount <em>Dropped Attributes Count</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.InstrumentationScopeImpl#getSchemaUrl <em>Schema Url</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InstrumentationScopeImpl extends MinimalEObjectImpl.Container implements InstrumentationScope {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDroppedAttributesCount() <em>Dropped Attributes Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDroppedAttributesCount()
	 * @generated
	 * @ordered
	 */
	protected static final int DROPPED_ATTRIBUTES_COUNT_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getSchemaUrl() <em>Schema Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchemaUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String SCHEMA_URL_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstrumentationScopeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.INSTRUMENTATION_SCOPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(ModelPackage.INSTRUMENTATION_SCOPE__NAME, ModelPackage.Literals.INSTRUMENTATION_SCOPE__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(ModelPackage.INSTRUMENTATION_SCOPE__NAME, ModelPackage.Literals.INSTRUMENTATION_SCOPE__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return (String)eDynamicGet(ModelPackage.INSTRUMENTATION_SCOPE__VERSION, ModelPackage.Literals.INSTRUMENTATION_SCOPE__VERSION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		eDynamicSet(ModelPackage.INSTRUMENTATION_SCOPE__VERSION, ModelPackage.Literals.INSTRUMENTATION_SCOPE__VERSION, newVersion);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<KeyValue> getAttributes() {
		return (EList<KeyValue>)eDynamicGet(ModelPackage.INSTRUMENTATION_SCOPE__ATTRIBUTES, ModelPackage.Literals.INSTRUMENTATION_SCOPE__ATTRIBUTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getDroppedAttributesCount() {
		return (Integer)eDynamicGet(ModelPackage.INSTRUMENTATION_SCOPE__DROPPED_ATTRIBUTES_COUNT, ModelPackage.Literals.INSTRUMENTATION_SCOPE__DROPPED_ATTRIBUTES_COUNT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDroppedAttributesCount(int newDroppedAttributesCount) {
		eDynamicSet(ModelPackage.INSTRUMENTATION_SCOPE__DROPPED_ATTRIBUTES_COUNT, ModelPackage.Literals.INSTRUMENTATION_SCOPE__DROPPED_ATTRIBUTES_COUNT, newDroppedAttributesCount);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSchemaUrl() {
		return (String)eDynamicGet(ModelPackage.INSTRUMENTATION_SCOPE__SCHEMA_URL, ModelPackage.Literals.INSTRUMENTATION_SCOPE__SCHEMA_URL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSchemaUrl(String newSchemaUrl) {
		eDynamicSet(ModelPackage.INSTRUMENTATION_SCOPE__SCHEMA_URL, ModelPackage.Literals.INSTRUMENTATION_SCOPE__SCHEMA_URL, newSchemaUrl);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.INSTRUMENTATION_SCOPE__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.INSTRUMENTATION_SCOPE__NAME:
				return getName();
			case ModelPackage.INSTRUMENTATION_SCOPE__VERSION:
				return getVersion();
			case ModelPackage.INSTRUMENTATION_SCOPE__ATTRIBUTES:
				return getAttributes();
			case ModelPackage.INSTRUMENTATION_SCOPE__DROPPED_ATTRIBUTES_COUNT:
				return getDroppedAttributesCount();
			case ModelPackage.INSTRUMENTATION_SCOPE__SCHEMA_URL:
				return getSchemaUrl();
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
			case ModelPackage.INSTRUMENTATION_SCOPE__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.INSTRUMENTATION_SCOPE__VERSION:
				setVersion((String)newValue);
				return;
			case ModelPackage.INSTRUMENTATION_SCOPE__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends KeyValue>)newValue);
				return;
			case ModelPackage.INSTRUMENTATION_SCOPE__DROPPED_ATTRIBUTES_COUNT:
				setDroppedAttributesCount((Integer)newValue);
				return;
			case ModelPackage.INSTRUMENTATION_SCOPE__SCHEMA_URL:
				setSchemaUrl((String)newValue);
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
			case ModelPackage.INSTRUMENTATION_SCOPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.INSTRUMENTATION_SCOPE__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case ModelPackage.INSTRUMENTATION_SCOPE__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ModelPackage.INSTRUMENTATION_SCOPE__DROPPED_ATTRIBUTES_COUNT:
				setDroppedAttributesCount(DROPPED_ATTRIBUTES_COUNT_EDEFAULT);
				return;
			case ModelPackage.INSTRUMENTATION_SCOPE__SCHEMA_URL:
				setSchemaUrl(SCHEMA_URL_EDEFAULT);
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
			case ModelPackage.INSTRUMENTATION_SCOPE__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case ModelPackage.INSTRUMENTATION_SCOPE__VERSION:
				return VERSION_EDEFAULT == null ? getVersion() != null : !VERSION_EDEFAULT.equals(getVersion());
			case ModelPackage.INSTRUMENTATION_SCOPE__ATTRIBUTES:
				return !getAttributes().isEmpty();
			case ModelPackage.INSTRUMENTATION_SCOPE__DROPPED_ATTRIBUTES_COUNT:
				return getDroppedAttributesCount() != DROPPED_ATTRIBUTES_COUNT_EDEFAULT;
			case ModelPackage.INSTRUMENTATION_SCOPE__SCHEMA_URL:
				return SCHEMA_URL_EDEFAULT == null ? getSchemaUrl() != null : !SCHEMA_URL_EDEFAULT.equals(getSchemaUrl());
		}
		return super.eIsSet(featureID);
	}

} //InstrumentationScopeImpl
