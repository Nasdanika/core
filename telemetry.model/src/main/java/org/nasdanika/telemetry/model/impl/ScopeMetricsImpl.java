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
import org.nasdanika.telemetry.model.Metric;
import org.nasdanika.telemetry.model.ModelPackage;
import org.nasdanika.telemetry.model.ScopeMetrics;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scope Metrics</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ScopeMetricsImpl#getScope <em>Scope</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ScopeMetricsImpl#getMetrics <em>Metrics</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ScopeMetricsImpl#getSchemaUrl <em>Schema Url</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScopeMetricsImpl extends MinimalEObjectImpl.Container implements ScopeMetrics {
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
	protected ScopeMetricsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SCOPE_METRICS;
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
	public InstrumentationScope getScope() {
		return (InstrumentationScope)eDynamicGet(ModelPackage.SCOPE_METRICS__SCOPE, ModelPackage.Literals.SCOPE_METRICS__SCOPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScope(InstrumentationScope newScope, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newScope, ModelPackage.SCOPE_METRICS__SCOPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScope(InstrumentationScope newScope) {
		eDynamicSet(ModelPackage.SCOPE_METRICS__SCOPE, ModelPackage.Literals.SCOPE_METRICS__SCOPE, newScope);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Metric> getMetrics() {
		return (EList<Metric>)eDynamicGet(ModelPackage.SCOPE_METRICS__METRICS, ModelPackage.Literals.SCOPE_METRICS__METRICS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSchemaUrl() {
		return (String)eDynamicGet(ModelPackage.SCOPE_METRICS__SCHEMA_URL, ModelPackage.Literals.SCOPE_METRICS__SCHEMA_URL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSchemaUrl(String newSchemaUrl) {
		eDynamicSet(ModelPackage.SCOPE_METRICS__SCHEMA_URL, ModelPackage.Literals.SCOPE_METRICS__SCHEMA_URL, newSchemaUrl);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.SCOPE_METRICS__SCOPE:
				return basicSetScope(null, msgs);
			case ModelPackage.SCOPE_METRICS__METRICS:
				return ((InternalEList<?>)getMetrics()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.SCOPE_METRICS__SCOPE:
				return getScope();
			case ModelPackage.SCOPE_METRICS__METRICS:
				return getMetrics();
			case ModelPackage.SCOPE_METRICS__SCHEMA_URL:
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
			case ModelPackage.SCOPE_METRICS__SCOPE:
				setScope((InstrumentationScope)newValue);
				return;
			case ModelPackage.SCOPE_METRICS__METRICS:
				getMetrics().clear();
				getMetrics().addAll((Collection<? extends Metric>)newValue);
				return;
			case ModelPackage.SCOPE_METRICS__SCHEMA_URL:
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
			case ModelPackage.SCOPE_METRICS__SCOPE:
				setScope((InstrumentationScope)null);
				return;
			case ModelPackage.SCOPE_METRICS__METRICS:
				getMetrics().clear();
				return;
			case ModelPackage.SCOPE_METRICS__SCHEMA_URL:
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
			case ModelPackage.SCOPE_METRICS__SCOPE:
				return getScope() != null;
			case ModelPackage.SCOPE_METRICS__METRICS:
				return !getMetrics().isEmpty();
			case ModelPackage.SCOPE_METRICS__SCHEMA_URL:
				return SCHEMA_URL_EDEFAULT == null ? getSchemaUrl() != null : !SCHEMA_URL_EDEFAULT.equals(getSchemaUrl());
		}
		return super.eIsSet(featureID);
	}

} //ScopeMetricsImpl
