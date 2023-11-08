/**
 */
package org.nasdanika.graph.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.graph.model.Connection;
import org.nasdanika.graph.model.ConnectionTarget;
import org.nasdanika.graph.model.ModelPackage;
import org.nasdanika.graph.model.Tunnel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tunnel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.graph.model.impl.TunnelImpl#getConnections <em>Connections</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TunnelImpl<T extends ConnectionTarget<?>, C extends Connection<?>> extends ConnectionImpl<T> implements Tunnel<T, C> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TunnelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TUNNEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<C> getConnections() {
		return (EList<C>)eDynamicGet(ModelPackage.TUNNEL__CONNECTIONS, ModelPackage.Literals.TUNNEL__CONNECTIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TUNNEL__CONNECTIONS:
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
			case ModelPackage.TUNNEL__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection<? extends C>)newValue);
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
			case ModelPackage.TUNNEL__CONNECTIONS:
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
			case ModelPackage.TUNNEL__CONNECTIONS:
				return !getConnections().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TunnelImpl
