/**
 */
package org.nasdanika.drawio.model.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.drawio.model.Connection;
import org.nasdanika.drawio.model.Geometry;
import org.nasdanika.drawio.model.LayerElement;
import org.nasdanika.drawio.model.ModelPackage;
import org.nasdanika.drawio.model.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.impl.NodeImpl#getGeometry <em>Geometry</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.NodeImpl#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.NodeImpl#getOutgoing <em>Outgoing</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeImpl extends LayerImpl implements Node {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Geometry getGeometry() {
		return (Geometry)eDynamicGet(ModelPackage.NODE__GEOMETRY, ModelPackage.Literals.LAYER_ELEMENT__GEOMETRY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGeometry(Geometry newGeometry, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newGeometry, ModelPackage.NODE__GEOMETRY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGeometry(Geometry newGeometry) {
		eDynamicSet(ModelPackage.NODE__GEOMETRY, ModelPackage.Literals.LAYER_ELEMENT__GEOMETRY, newGeometry);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Connection> getIncoming() {
		return (EList<Connection>)eDynamicGet(ModelPackage.NODE__INCOMING, ModelPackage.Literals.NODE__INCOMING, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Connection> getOutgoing() {
		return (EList<Connection>)eDynamicGet(ModelPackage.NODE__OUTGOING, ModelPackage.Literals.NODE__OUTGOING, true, true);
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
			case ModelPackage.NODE__INCOMING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncoming()).basicAdd(otherEnd, msgs);
			case ModelPackage.NODE__OUTGOING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoing()).basicAdd(otherEnd, msgs);
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
			case ModelPackage.NODE__GEOMETRY:
				return basicSetGeometry(null, msgs);
			case ModelPackage.NODE__INCOMING:
				return ((InternalEList<?>)getIncoming()).basicRemove(otherEnd, msgs);
			case ModelPackage.NODE__OUTGOING:
				return ((InternalEList<?>)getOutgoing()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.NODE__GEOMETRY:
				return getGeometry();
			case ModelPackage.NODE__INCOMING:
				return getIncoming();
			case ModelPackage.NODE__OUTGOING:
				return getOutgoing();
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
			case ModelPackage.NODE__GEOMETRY:
				setGeometry((Geometry)newValue);
				return;
			case ModelPackage.NODE__INCOMING:
				getIncoming().clear();
				getIncoming().addAll((Collection<? extends Connection>)newValue);
				return;
			case ModelPackage.NODE__OUTGOING:
				getOutgoing().clear();
				getOutgoing().addAll((Collection<? extends Connection>)newValue);
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
			case ModelPackage.NODE__GEOMETRY:
				setGeometry((Geometry)null);
				return;
			case ModelPackage.NODE__INCOMING:
				getIncoming().clear();
				return;
			case ModelPackage.NODE__OUTGOING:
				getOutgoing().clear();
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
			case ModelPackage.NODE__GEOMETRY:
				return getGeometry() != null;
			case ModelPackage.NODE__INCOMING:
				return !getIncoming().isEmpty();
			case ModelPackage.NODE__OUTGOING:
				return !getOutgoing().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == LayerElement.class) {
			switch (derivedFeatureID) {
				case ModelPackage.NODE__GEOMETRY: return ModelPackage.LAYER_ELEMENT__GEOMETRY;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == LayerElement.class) {
			switch (baseFeatureID) {
				case ModelPackage.LAYER_ELEMENT__GEOMETRY: return ModelPackage.NODE__GEOMETRY;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //NodeImpl
