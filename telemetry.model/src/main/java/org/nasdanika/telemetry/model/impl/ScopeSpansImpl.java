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
import org.nasdanika.telemetry.model.ModelPackage;
import org.nasdanika.telemetry.model.ScopeSpans;
import org.nasdanika.telemetry.model.Span;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scope Spans</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ScopeSpansImpl#getScope <em>Scope</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ScopeSpansImpl#getSpans <em>Spans</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.impl.ScopeSpansImpl#getSchemaUrl <em>Schema Url</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScopeSpansImpl extends MinimalEObjectImpl.Container implements ScopeSpans {
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
	protected ScopeSpansImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SCOPE_SPANS;
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
		return (InstrumentationScope)eDynamicGet(ModelPackage.SCOPE_SPANS__SCOPE, ModelPackage.Literals.SCOPE_SPANS__SCOPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScope(InstrumentationScope newScope, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newScope, ModelPackage.SCOPE_SPANS__SCOPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScope(InstrumentationScope newScope) {
		eDynamicSet(ModelPackage.SCOPE_SPANS__SCOPE, ModelPackage.Literals.SCOPE_SPANS__SCOPE, newScope);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Span> getSpans() {
		return (EList<Span>)eDynamicGet(ModelPackage.SCOPE_SPANS__SPANS, ModelPackage.Literals.SCOPE_SPANS__SPANS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSchemaUrl() {
		return (String)eDynamicGet(ModelPackage.SCOPE_SPANS__SCHEMA_URL, ModelPackage.Literals.SCOPE_SPANS__SCHEMA_URL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSchemaUrl(String newSchemaUrl) {
		eDynamicSet(ModelPackage.SCOPE_SPANS__SCHEMA_URL, ModelPackage.Literals.SCOPE_SPANS__SCHEMA_URL, newSchemaUrl);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.SCOPE_SPANS__SCOPE:
				return basicSetScope(null, msgs);
			case ModelPackage.SCOPE_SPANS__SPANS:
				return ((InternalEList<?>)getSpans()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.SCOPE_SPANS__SCOPE:
				return getScope();
			case ModelPackage.SCOPE_SPANS__SPANS:
				return getSpans();
			case ModelPackage.SCOPE_SPANS__SCHEMA_URL:
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
			case ModelPackage.SCOPE_SPANS__SCOPE:
				setScope((InstrumentationScope)newValue);
				return;
			case ModelPackage.SCOPE_SPANS__SPANS:
				getSpans().clear();
				getSpans().addAll((Collection<? extends Span>)newValue);
				return;
			case ModelPackage.SCOPE_SPANS__SCHEMA_URL:
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
			case ModelPackage.SCOPE_SPANS__SCOPE:
				setScope((InstrumentationScope)null);
				return;
			case ModelPackage.SCOPE_SPANS__SPANS:
				getSpans().clear();
				return;
			case ModelPackage.SCOPE_SPANS__SCHEMA_URL:
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
			case ModelPackage.SCOPE_SPANS__SCOPE:
				return getScope() != null;
			case ModelPackage.SCOPE_SPANS__SPANS:
				return !getSpans().isEmpty();
			case ModelPackage.SCOPE_SPANS__SCHEMA_URL:
				return SCHEMA_URL_EDEFAULT == null ? getSchemaUrl() != null : !SCHEMA_URL_EDEFAULT.equals(getSchemaUrl());
		}
		return super.eIsSet(featureID);
	}

} //ScopeSpansImpl
