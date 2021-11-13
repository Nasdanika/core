/**
 */
package org.nasdanika.diagram.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramPackage;
import org.nasdanika.diagram.Note;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.impl.NamedElementImpl;

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
 *   <li>{@link org.nasdanika.diagram.impl.DiagramImpl#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramImpl#getDepth <em>Depth</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DiagramImpl extends NamedElementImpl implements Diagram {
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
	 * The default value of the '{@link #getContext() <em>Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContext()
	 * @generated
	 * @ordered
	 */
	protected static final int CONTEXT_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getDepth() <em>Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDepth()
	 * @generated
	 * @ordered
	 */
	protected static final int DEPTH_EDEFAULT = -1;

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
	@SuppressWarnings("unchecked")
	@Override
	public EList<Note> getNotes() {
		return (EList<Note>)eDynamicGet(DiagramPackage.DIAGRAM__NOTES, DiagramPackage.Literals.DIAGRAM__NOTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Property> getProperties() {
		return (EList<Property>)eDynamicGet(DiagramPackage.DIAGRAM__PROPERTIES, DiagramPackage.Literals.DIAGRAM__PROPERTIES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getContext() {
		return (Integer)eDynamicGet(DiagramPackage.DIAGRAM__CONTEXT, DiagramPackage.Literals.DIAGRAM__CONTEXT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContext(int newContext) {
		eDynamicSet(DiagramPackage.DIAGRAM__CONTEXT, DiagramPackage.Literals.DIAGRAM__CONTEXT, newContext);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getDepth() {
		return (Integer)eDynamicGet(DiagramPackage.DIAGRAM__DEPTH, DiagramPackage.Literals.DIAGRAM__DEPTH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDepth(int newDepth) {
		eDynamicSet(DiagramPackage.DIAGRAM__DEPTH, DiagramPackage.Literals.DIAGRAM__DEPTH, newDepth);
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
			case DiagramPackage.DIAGRAM__NOTES:
				return ((InternalEList<?>)getNotes()).basicRemove(otherEnd, msgs);
			case DiagramPackage.DIAGRAM__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
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
			case DiagramPackage.DIAGRAM__NOTES:
				return getNotes();
			case DiagramPackage.DIAGRAM__PROPERTIES:
				return getProperties();
			case DiagramPackage.DIAGRAM__CONTEXT:
				return getContext();
			case DiagramPackage.DIAGRAM__DEPTH:
				return getDepth();
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
			case DiagramPackage.DIAGRAM__NOTES:
				getNotes().clear();
				getNotes().addAll((Collection<? extends Note>)newValue);
				return;
			case DiagramPackage.DIAGRAM__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case DiagramPackage.DIAGRAM__CONTEXT:
				setContext((Integer)newValue);
				return;
			case DiagramPackage.DIAGRAM__DEPTH:
				setDepth((Integer)newValue);
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
			case DiagramPackage.DIAGRAM__NOTES:
				getNotes().clear();
				return;
			case DiagramPackage.DIAGRAM__PROPERTIES:
				getProperties().clear();
				return;
			case DiagramPackage.DIAGRAM__CONTEXT:
				setContext(CONTEXT_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM__DEPTH:
				setDepth(DEPTH_EDEFAULT);
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
			case DiagramPackage.DIAGRAM__NOTES:
				return !getNotes().isEmpty();
			case DiagramPackage.DIAGRAM__PROPERTIES:
				return !getProperties().isEmpty();
			case DiagramPackage.DIAGRAM__CONTEXT:
				return getContext() != CONTEXT_EDEFAULT;
			case DiagramPackage.DIAGRAM__DEPTH:
				return getDepth() != DEPTH_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //DiagramImpl
