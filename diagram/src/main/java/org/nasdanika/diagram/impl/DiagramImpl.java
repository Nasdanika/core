/**
 */
package org.nasdanika.diagram.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramImpl#isVertical <em>Vertical</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramImpl#isHideEmptyDescription <em>Hide Empty Description</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramImpl#isHideFootbox <em>Hide Footbox</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DiagramImpl extends MinimalEObjectImpl.Container implements Diagram {
	/**
	 * The default value of the '{@link #isVertical() <em>Vertical</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVertical()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VERTICAL_EDEFAULT = true;

	/**
	 * The default value of the '{@link #isHideEmptyDescription() <em>Hide Empty Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHideEmptyDescription()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIDE_EMPTY_DESCRIPTION_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isHideFootbox() <em>Hide Footbox</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHideFootbox()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIDE_FOOTBOX_EDEFAULT = true;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = "plantuml:uml";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiagramPackage.Literals.DIAGRAM;
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
	@SuppressWarnings("unchecked")
	@Override
	public EList<DiagramElement> getElements() {
		return (EList<DiagramElement>)eDynamicGet(DiagramPackage.DIAGRAM__ELEMENTS, DiagramPackage.Literals.DIAGRAM__ELEMENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isVertical() {
		return (Boolean)eDynamicGet(DiagramPackage.DIAGRAM__VERTICAL, DiagramPackage.Literals.DIAGRAM__VERTICAL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVertical(boolean newVertical) {
		eDynamicSet(DiagramPackage.DIAGRAM__VERTICAL, DiagramPackage.Literals.DIAGRAM__VERTICAL, newVertical);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHideEmptyDescription() {
		return (Boolean)eDynamicGet(DiagramPackage.DIAGRAM__HIDE_EMPTY_DESCRIPTION, DiagramPackage.Literals.DIAGRAM__HIDE_EMPTY_DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHideEmptyDescription(boolean newHideEmptyDescription) {
		eDynamicSet(DiagramPackage.DIAGRAM__HIDE_EMPTY_DESCRIPTION, DiagramPackage.Literals.DIAGRAM__HIDE_EMPTY_DESCRIPTION, newHideEmptyDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHideFootbox() {
		return (Boolean)eDynamicGet(DiagramPackage.DIAGRAM__HIDE_FOOTBOX, DiagramPackage.Literals.DIAGRAM__HIDE_FOOTBOX, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHideFootbox(boolean newHideFootbox) {
		eDynamicSet(DiagramPackage.DIAGRAM__HIDE_FOOTBOX, DiagramPackage.Literals.DIAGRAM__HIDE_FOOTBOX, newHideFootbox);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getType() {
		return (String)eDynamicGet(DiagramPackage.DIAGRAM__TYPE, DiagramPackage.Literals.DIAGRAM__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		eDynamicSet(DiagramPackage.DIAGRAM__TYPE, DiagramPackage.Literals.DIAGRAM__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DiagramPackage.DIAGRAM__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
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
			case DiagramPackage.DIAGRAM__ELEMENTS:
				return getElements();
			case DiagramPackage.DIAGRAM__VERTICAL:
				return isVertical();
			case DiagramPackage.DIAGRAM__HIDE_EMPTY_DESCRIPTION:
				return isHideEmptyDescription();
			case DiagramPackage.DIAGRAM__HIDE_FOOTBOX:
				return isHideFootbox();
			case DiagramPackage.DIAGRAM__TYPE:
				return getType();
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
			case DiagramPackage.DIAGRAM__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends DiagramElement>)newValue);
				return;
			case DiagramPackage.DIAGRAM__VERTICAL:
				setVertical((Boolean)newValue);
				return;
			case DiagramPackage.DIAGRAM__HIDE_EMPTY_DESCRIPTION:
				setHideEmptyDescription((Boolean)newValue);
				return;
			case DiagramPackage.DIAGRAM__HIDE_FOOTBOX:
				setHideFootbox((Boolean)newValue);
				return;
			case DiagramPackage.DIAGRAM__TYPE:
				setType((String)newValue);
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
			case DiagramPackage.DIAGRAM__ELEMENTS:
				getElements().clear();
				return;
			case DiagramPackage.DIAGRAM__VERTICAL:
				setVertical(VERTICAL_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM__HIDE_EMPTY_DESCRIPTION:
				setHideEmptyDescription(HIDE_EMPTY_DESCRIPTION_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM__HIDE_FOOTBOX:
				setHideFootbox(HIDE_FOOTBOX_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM__TYPE:
				setType(TYPE_EDEFAULT);
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
			case DiagramPackage.DIAGRAM__ELEMENTS:
				return !getElements().isEmpty();
			case DiagramPackage.DIAGRAM__VERTICAL:
				return isVertical() != VERTICAL_EDEFAULT;
			case DiagramPackage.DIAGRAM__HIDE_EMPTY_DESCRIPTION:
				return isHideEmptyDescription() != HIDE_EMPTY_DESCRIPTION_EDEFAULT;
			case DiagramPackage.DIAGRAM__HIDE_FOOTBOX:
				return isHideFootbox() != HIDE_FOOTBOX_EDEFAULT;
			case DiagramPackage.DIAGRAM__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
		}
		return super.eIsSet(featureID);
	}

} //DiagramImpl
