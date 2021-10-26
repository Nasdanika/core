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

import org.nasdanika.diagram.DiagramPackage;
import org.nasdanika.diagram.Note;
import org.nasdanika.diagram.Style;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.diagram.impl.StyleImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.StyleImpl#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.StyleImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.StyleImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.StyleImpl#isDashed <em>Dashed</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.StyleImpl#isDotted <em>Dotted</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.StyleImpl#isBold <em>Bold</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StyleImpl extends MinimalEObjectImpl.Container implements Style {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StyleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiagramPackage.Literals.STYLE;
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
	public String getType() {
		return (String)eDynamicGet(DiagramPackage.STYLE__TYPE, DiagramPackage.Literals.STYLE__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		eDynamicSet(DiagramPackage.STYLE__TYPE, DiagramPackage.Literals.STYLE__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Note> getNotes() {
		return (EList<Note>)eDynamicGet(DiagramPackage.STYLE__NOTES, DiagramPackage.Literals.STYLE__NOTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getDescription() {
		return (EList<EObject>)eDynamicGet(DiagramPackage.STYLE__DESCRIPTION, DiagramPackage.Literals.STYLE__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getColor() {
		return (String)eDynamicGet(DiagramPackage.STYLE__COLOR, DiagramPackage.Literals.STYLE__COLOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(String newColor) {
		eDynamicSet(DiagramPackage.STYLE__COLOR, DiagramPackage.Literals.STYLE__COLOR, newColor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDashed() {
		return (Boolean)eDynamicGet(DiagramPackage.STYLE__DASHED, DiagramPackage.Literals.STYLE__DASHED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDashed(boolean newDashed) {
		eDynamicSet(DiagramPackage.STYLE__DASHED, DiagramPackage.Literals.STYLE__DASHED, newDashed);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDotted() {
		return (Boolean)eDynamicGet(DiagramPackage.STYLE__DOTTED, DiagramPackage.Literals.STYLE__DOTTED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDotted(boolean newDotted) {
		eDynamicSet(DiagramPackage.STYLE__DOTTED, DiagramPackage.Literals.STYLE__DOTTED, newDotted);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBold() {
		return (Boolean)eDynamicGet(DiagramPackage.STYLE__BOLD, DiagramPackage.Literals.STYLE__BOLD, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBold(boolean newBold) {
		eDynamicSet(DiagramPackage.STYLE__BOLD, DiagramPackage.Literals.STYLE__BOLD, newBold);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DiagramPackage.STYLE__NOTES:
				return ((InternalEList<?>)getNotes()).basicRemove(otherEnd, msgs);
			case DiagramPackage.STYLE__DESCRIPTION:
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
			case DiagramPackage.STYLE__TYPE:
				return getType();
			case DiagramPackage.STYLE__NOTES:
				return getNotes();
			case DiagramPackage.STYLE__DESCRIPTION:
				return getDescription();
			case DiagramPackage.STYLE__COLOR:
				return getColor();
			case DiagramPackage.STYLE__DASHED:
				return isDashed();
			case DiagramPackage.STYLE__DOTTED:
				return isDotted();
			case DiagramPackage.STYLE__BOLD:
				return isBold();
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
			case DiagramPackage.STYLE__TYPE:
				setType((String)newValue);
				return;
			case DiagramPackage.STYLE__NOTES:
				getNotes().clear();
				getNotes().addAll((Collection<? extends Note>)newValue);
				return;
			case DiagramPackage.STYLE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends EObject>)newValue);
				return;
			case DiagramPackage.STYLE__COLOR:
				setColor((String)newValue);
				return;
			case DiagramPackage.STYLE__DASHED:
				setDashed((Boolean)newValue);
				return;
			case DiagramPackage.STYLE__DOTTED:
				setDotted((Boolean)newValue);
				return;
			case DiagramPackage.STYLE__BOLD:
				setBold((Boolean)newValue);
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
			case DiagramPackage.STYLE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case DiagramPackage.STYLE__NOTES:
				getNotes().clear();
				return;
			case DiagramPackage.STYLE__DESCRIPTION:
				getDescription().clear();
				return;
			case DiagramPackage.STYLE__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case DiagramPackage.STYLE__DASHED:
				setDashed(DASHED_EDEFAULT);
				return;
			case DiagramPackage.STYLE__DOTTED:
				setDotted(DOTTED_EDEFAULT);
				return;
			case DiagramPackage.STYLE__BOLD:
				setBold(BOLD_EDEFAULT);
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
			case DiagramPackage.STYLE__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
			case DiagramPackage.STYLE__NOTES:
				return !getNotes().isEmpty();
			case DiagramPackage.STYLE__DESCRIPTION:
				return !getDescription().isEmpty();
			case DiagramPackage.STYLE__COLOR:
				return COLOR_EDEFAULT == null ? getColor() != null : !COLOR_EDEFAULT.equals(getColor());
			case DiagramPackage.STYLE__DASHED:
				return isDashed() != DASHED_EDEFAULT;
			case DiagramPackage.STYLE__DOTTED:
				return isDotted() != DOTTED_EDEFAULT;
			case DiagramPackage.STYLE__BOLD:
				return isBold() != BOLD_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //StyleImpl
