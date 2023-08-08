/**
 */
package org.nasdanika.drawio.model.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.nasdanika.drawio.model.Geometry;
import org.nasdanika.drawio.model.LayerElement;
import org.nasdanika.drawio.model.ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Layer Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.impl.LayerElementImpl#getGeometry <em>Geometry</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LayerElementImpl extends ModelElementImpl implements LayerElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LayerElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.LAYER_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Geometry getGeometry() {
		return (Geometry)eDynamicGet(ModelPackage.LAYER_ELEMENT__GEOMETRY, ModelPackage.Literals.LAYER_ELEMENT__GEOMETRY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGeometry(Geometry newGeometry, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newGeometry, ModelPackage.LAYER_ELEMENT__GEOMETRY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGeometry(Geometry newGeometry) {
		eDynamicSet(ModelPackage.LAYER_ELEMENT__GEOMETRY, ModelPackage.Literals.LAYER_ELEMENT__GEOMETRY, newGeometry);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.LAYER_ELEMENT__GEOMETRY:
				return basicSetGeometry(null, msgs);
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
			case ModelPackage.LAYER_ELEMENT__GEOMETRY:
				return getGeometry();
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
			case ModelPackage.LAYER_ELEMENT__GEOMETRY:
				setGeometry((Geometry)newValue);
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
			case ModelPackage.LAYER_ELEMENT__GEOMETRY:
				setGeometry((Geometry)null);
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
			case ModelPackage.LAYER_ELEMENT__GEOMETRY:
				return getGeometry() != null;
		}
		return super.eIsSet(featureID);
	}

} //LayerElementImpl
