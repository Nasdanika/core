/**
 */
package org.nasdanika.diagram.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;
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
 *   <li>{@link org.nasdanika.diagram.impl.ConnectionImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.ConnectionImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.ConnectionImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.ConnectionImpl#isDashed <em>Dashed</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.ConnectionImpl#isDotted <em>Dotted</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.ConnectionImpl#isBold <em>Bold</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.ConnectionImpl#getThickness <em>Thickness</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectionImpl extends MinimalEObjectImpl.Container implements Connection {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = "-${style}->";

	/**
	 * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected static final String COLOR_EDEFAULT = null;
	/**
	 * The default value of the '{@link #isDashed() <em>Dashed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDashed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DASHED_EDEFAULT = false;
	/**
	 * The default value of the '{@link #isDotted() <em>Dotted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDotted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DOTTED_EDEFAULT = false;
	/**
	 * The default value of the '{@link #isBold() <em>Bold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBold()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOLD_EDEFAULT = false;
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
		return DiagramPackage.Literals.CONNECTION;
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
	public String getType() {
		return (String)eDynamicGet(DiagramPackage.CONNECTION__TYPE, DiagramPackage.Literals.CONNECTION__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		eDynamicSet(DiagramPackage.CONNECTION__TYPE, DiagramPackage.Literals.CONNECTION__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getDescription() {
		return (EList<EObject>)eDynamicGet(DiagramPackage.CONNECTION__DESCRIPTION, DiagramPackage.Literals.CONNECTION__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getColor() {
		return (String)eDynamicGet(DiagramPackage.CONNECTION__COLOR, DiagramPackage.Literals.CONNECTION__COLOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(String newColor) {
		eDynamicSet(DiagramPackage.CONNECTION__COLOR, DiagramPackage.Literals.CONNECTION__COLOR, newColor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDashed() {
		return (Boolean)eDynamicGet(DiagramPackage.CONNECTION__DASHED, DiagramPackage.Literals.CONNECTION__DASHED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDashed(boolean newDashed) {
		eDynamicSet(DiagramPackage.CONNECTION__DASHED, DiagramPackage.Literals.CONNECTION__DASHED, newDashed);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDotted() {
		return (Boolean)eDynamicGet(DiagramPackage.CONNECTION__DOTTED, DiagramPackage.Literals.CONNECTION__DOTTED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDotted(boolean newDotted) {
		eDynamicSet(DiagramPackage.CONNECTION__DOTTED, DiagramPackage.Literals.CONNECTION__DOTTED, newDotted);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBold() {
		return (Boolean)eDynamicGet(DiagramPackage.CONNECTION__BOLD, DiagramPackage.Literals.CONNECTION__BOLD, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBold(boolean newBold) {
		eDynamicSet(DiagramPackage.CONNECTION__BOLD, DiagramPackage.Literals.CONNECTION__BOLD, newBold);
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DiagramPackage.CONNECTION__DESCRIPTION:
				return ((InternalEList<?>)getDescription()).basicRemove(otherEnd, msgs);
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
			case DiagramPackage.CONNECTION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case DiagramPackage.CONNECTION__TYPE:
				return getType();
			case DiagramPackage.CONNECTION__DESCRIPTION:
				return getDescription();
			case DiagramPackage.CONNECTION__COLOR:
				return getColor();
			case DiagramPackage.CONNECTION__DASHED:
				return isDashed();
			case DiagramPackage.CONNECTION__DOTTED:
				return isDotted();
			case DiagramPackage.CONNECTION__BOLD:
				return isBold();
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
			case DiagramPackage.CONNECTION__TYPE:
				setType((String)newValue);
				return;
			case DiagramPackage.CONNECTION__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends EObject>)newValue);
				return;
			case DiagramPackage.CONNECTION__COLOR:
				setColor((String)newValue);
				return;
			case DiagramPackage.CONNECTION__DASHED:
				setDashed((Boolean)newValue);
				return;
			case DiagramPackage.CONNECTION__DOTTED:
				setDotted((Boolean)newValue);
				return;
			case DiagramPackage.CONNECTION__BOLD:
				setBold((Boolean)newValue);
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
			case DiagramPackage.CONNECTION__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case DiagramPackage.CONNECTION__DESCRIPTION:
				getDescription().clear();
				return;
			case DiagramPackage.CONNECTION__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case DiagramPackage.CONNECTION__DASHED:
				setDashed(DASHED_EDEFAULT);
				return;
			case DiagramPackage.CONNECTION__DOTTED:
				setDotted(DOTTED_EDEFAULT);
				return;
			case DiagramPackage.CONNECTION__BOLD:
				setBold(BOLD_EDEFAULT);
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
			case DiagramPackage.CONNECTION__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
			case DiagramPackage.CONNECTION__DESCRIPTION:
				return !getDescription().isEmpty();
			case DiagramPackage.CONNECTION__COLOR:
				return COLOR_EDEFAULT == null ? getColor() != null : !COLOR_EDEFAULT.equals(getColor());
			case DiagramPackage.CONNECTION__DASHED:
				return isDashed() != DASHED_EDEFAULT;
			case DiagramPackage.CONNECTION__DOTTED:
				return isDotted() != DOTTED_EDEFAULT;
			case DiagramPackage.CONNECTION__BOLD:
				return isBold() != BOLD_EDEFAULT;
			case DiagramPackage.CONNECTION__THICKNESS:
				return getThickness() != THICKNESS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //ConnectionImpl
