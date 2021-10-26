/**
 */
package org.nasdanika.diagram.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.diagram.impl.ConnectionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.ConnectionImpl#getThickness <em>Thickness</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectionImpl extends StyleImpl implements Connection {
	/**
	 * The default value of the '{@link #getThickness() <em>Thickness</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThickness()
	 * @generated
	 * @ordered
	 */
	protected static final int THICKNESS_EDEFAULT = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected ConnectionImpl() {
		super();
		setType("-${style}->");
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiagramPackage.Literals.CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DiagramElement getTarget() {
		return (DiagramElement)eDynamicGet(DiagramPackage.CONNECTION__TARGET, DiagramPackage.Literals.CONNECTION__TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramElement basicGetTarget() {
		return (DiagramElement)eDynamicGet(DiagramPackage.CONNECTION__TARGET, DiagramPackage.Literals.CONNECTION__TARGET, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(DiagramElement newTarget) {
		eDynamicSet(DiagramPackage.CONNECTION__TARGET, DiagramPackage.Literals.CONNECTION__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getThickness() {
		return (Integer)eDynamicGet(DiagramPackage.CONNECTION__THICKNESS, DiagramPackage.Literals.CONNECTION__THICKNESS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setThickness(int newThickness) {
		eDynamicSet(DiagramPackage.CONNECTION__THICKNESS, DiagramPackage.Literals.CONNECTION__THICKNESS, newThickness);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DiagramPackage.CONNECTION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case DiagramPackage.CONNECTION__THICKNESS:
				return getThickness();
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
			case DiagramPackage.CONNECTION__TARGET:
				setTarget((DiagramElement)newValue);
				return;
			case DiagramPackage.CONNECTION__THICKNESS:
				setThickness((Integer)newValue);
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
			case DiagramPackage.CONNECTION__TARGET:
				setTarget((DiagramElement)null);
				return;
			case DiagramPackage.CONNECTION__THICKNESS:
				setThickness(THICKNESS_EDEFAULT);
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
			case DiagramPackage.CONNECTION__TARGET:
				return basicGetTarget() != null;
			case DiagramPackage.CONNECTION__THICKNESS:
				return getThickness() != THICKNESS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //ConnectionImpl
