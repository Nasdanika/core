/**
 */
package org.nasdanika.drawio.model.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.nasdanika.drawio.model.Connection;
import org.nasdanika.drawio.model.ModelPackage;
import org.nasdanika.drawio.model.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.impl.ConnectionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ConnectionImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectionImpl extends LayerElementImpl implements Connection {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Node getSource() {
		return (Node)eDynamicGet(ModelPackage.CONNECTION__SOURCE, ModelPackage.Literals.CONNECTION__SOURCE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetSource() {
		return (Node)eDynamicGet(ModelPackage.CONNECTION__SOURCE, ModelPackage.Literals.CONNECTION__SOURCE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(Node newSource, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newSource, ModelPackage.CONNECTION__SOURCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSource(Node newSource) {
		eDynamicSet(ModelPackage.CONNECTION__SOURCE, ModelPackage.Literals.CONNECTION__SOURCE, newSource);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Node getTarget() {
		return (Node)eDynamicGet(ModelPackage.CONNECTION__TARGET, ModelPackage.Literals.CONNECTION__TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetTarget() {
		return (Node)eDynamicGet(ModelPackage.CONNECTION__TARGET, ModelPackage.Literals.CONNECTION__TARGET, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Node newTarget, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newTarget, ModelPackage.CONNECTION__TARGET, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(Node newTarget) {
		eDynamicSet(ModelPackage.CONNECTION__TARGET, ModelPackage.Literals.CONNECTION__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.CONNECTION__SOURCE:
				Node source = basicGetSource();
				if (source != null)
					msgs = ((InternalEObject)source).eInverseRemove(this, ModelPackage.NODE__OUTGOING, Node.class, msgs);
				return basicSetSource((Node)otherEnd, msgs);
			case ModelPackage.CONNECTION__TARGET:
				Node target = basicGetTarget();
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, ModelPackage.NODE__INCOMING, Node.class, msgs);
				return basicSetTarget((Node)otherEnd, msgs);
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
			case ModelPackage.CONNECTION__SOURCE:
				return basicSetSource(null, msgs);
			case ModelPackage.CONNECTION__TARGET:
				return basicSetTarget(null, msgs);
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
			case ModelPackage.CONNECTION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case ModelPackage.CONNECTION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
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
			case ModelPackage.CONNECTION__SOURCE:
				setSource((Node)newValue);
				return;
			case ModelPackage.CONNECTION__TARGET:
				setTarget((Node)newValue);
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
			case ModelPackage.CONNECTION__SOURCE:
				setSource((Node)null);
				return;
			case ModelPackage.CONNECTION__TARGET:
				setTarget((Node)null);
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
			case ModelPackage.CONNECTION__SOURCE:
				return basicGetSource() != null;
			case ModelPackage.CONNECTION__TARGET:
				return basicGetTarget() != null;
		}
		return super.eIsSet(featureID);
	}

} //ConnectionImpl
