/**
 */
package org.nasdanika.diagram.impl;

import java.util.Collection;
import java.util.UUID;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramPackage;
import org.nasdanika.diagram.Note;
import org.nasdanika.diagram.Style;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#isDashed <em>Dashed</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#isDotted <em>Dotted</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#isBold <em>Bold</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getGradient <em>Gradient</em>}</li>
 *   <li>{@link org.nasdanika.diagram.impl.DiagramElementImpl#getBorder <em>Border</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DiagramElementImpl extends LinkImpl implements DiagramElement {
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
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotype()
	 * @generated
	 * @ordered
	 */
	protected static final String STEREOTYPE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getGradient() <em>Gradient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGradient()
	 * @generated
	 * @ordered
	 */
	protected static final String GRADIENT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getBorder() <em>Border</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBorder()
	 * @generated
	 * @ordered
	 */
	protected static final String BORDER_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected DiagramElementImpl() {
		super();
		setId(UUID.randomUUID().toString().replace('-', '_'));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiagramPackage.Literals.DIAGRAM_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return (String)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__ID, DiagramPackage.Literals.DIAGRAM_ELEMENT__ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eDynamicSet(DiagramPackage.DIAGRAM_ELEMENT__ID, DiagramPackage.Literals.DIAGRAM_ELEMENT__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getType() {
		return (String)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__TYPE, DiagramPackage.Literals.STYLE__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		eDynamicSet(DiagramPackage.DIAGRAM_ELEMENT__TYPE, DiagramPackage.Literals.STYLE__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getName() {
		return (EList<EObject>)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__NAME, DiagramPackage.Literals.DIAGRAM_ELEMENT__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<DiagramElement> getElements() {
		return (EList<DiagramElement>)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS, DiagramPackage.Literals.DIAGRAM_ELEMENT__ELEMENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Connection> getConnections() {
		return (EList<Connection>)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS, DiagramPackage.Literals.DIAGRAM_ELEMENT__CONNECTIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Note> getNotes() {
		return (EList<Note>)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__NOTES, DiagramPackage.Literals.STYLE__NOTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getDescription() {
		return (EList<EObject>)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__DESCRIPTION, DiagramPackage.Literals.STYLE__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getStereotype() {
		return (String)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__STEREOTYPE, DiagramPackage.Literals.DIAGRAM_ELEMENT__STEREOTYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStereotype(String newStereotype) {
		eDynamicSet(DiagramPackage.DIAGRAM_ELEMENT__STEREOTYPE, DiagramPackage.Literals.DIAGRAM_ELEMENT__STEREOTYPE, newStereotype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getColor() {
		return (String)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__COLOR, DiagramPackage.Literals.STYLE__COLOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(String newColor) {
		eDynamicSet(DiagramPackage.DIAGRAM_ELEMENT__COLOR, DiagramPackage.Literals.STYLE__COLOR, newColor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getGradient() {
		return (String)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__GRADIENT, DiagramPackage.Literals.DIAGRAM_ELEMENT__GRADIENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGradient(String newGradient) {
		eDynamicSet(DiagramPackage.DIAGRAM_ELEMENT__GRADIENT, DiagramPackage.Literals.DIAGRAM_ELEMENT__GRADIENT, newGradient);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBorder() {
		return (String)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__BORDER, DiagramPackage.Literals.DIAGRAM_ELEMENT__BORDER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBorder(String newBorder) {
		eDynamicSet(DiagramPackage.DIAGRAM_ELEMENT__BORDER, DiagramPackage.Literals.DIAGRAM_ELEMENT__BORDER, newBorder);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDashed() {
		return (Boolean)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__DASHED, DiagramPackage.Literals.STYLE__DASHED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDashed(boolean newDashed) {
		eDynamicSet(DiagramPackage.DIAGRAM_ELEMENT__DASHED, DiagramPackage.Literals.STYLE__DASHED, newDashed);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDotted() {
		return (Boolean)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__DOTTED, DiagramPackage.Literals.STYLE__DOTTED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDotted(boolean newDotted) {
		eDynamicSet(DiagramPackage.DIAGRAM_ELEMENT__DOTTED, DiagramPackage.Literals.STYLE__DOTTED, newDotted);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBold() {
		return (Boolean)eDynamicGet(DiagramPackage.DIAGRAM_ELEMENT__BOLD, DiagramPackage.Literals.STYLE__BOLD, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBold(boolean newBold) {
		eDynamicSet(DiagramPackage.DIAGRAM_ELEMENT__BOLD, DiagramPackage.Literals.STYLE__BOLD, newBold);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DiagramPackage.DIAGRAM_ELEMENT__NOTES:
				return ((InternalEList<?>)getNotes()).basicRemove(otherEnd, msgs);
			case DiagramPackage.DIAGRAM_ELEMENT__DESCRIPTION:
				return ((InternalEList<?>)getDescription()).basicRemove(otherEnd, msgs);
			case DiagramPackage.DIAGRAM_ELEMENT__NAME:
				return ((InternalEList<?>)getName()).basicRemove(otherEnd, msgs);
			case DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
			case DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS:
				return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
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
			case DiagramPackage.DIAGRAM_ELEMENT__TYPE:
				return getType();
			case DiagramPackage.DIAGRAM_ELEMENT__NOTES:
				return getNotes();
			case DiagramPackage.DIAGRAM_ELEMENT__DESCRIPTION:
				return getDescription();
			case DiagramPackage.DIAGRAM_ELEMENT__COLOR:
				return getColor();
			case DiagramPackage.DIAGRAM_ELEMENT__DASHED:
				return isDashed();
			case DiagramPackage.DIAGRAM_ELEMENT__DOTTED:
				return isDotted();
			case DiagramPackage.DIAGRAM_ELEMENT__BOLD:
				return isBold();
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				return getId();
			case DiagramPackage.DIAGRAM_ELEMENT__NAME:
				return getName();
			case DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS:
				return getElements();
			case DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS:
				return getConnections();
			case DiagramPackage.DIAGRAM_ELEMENT__STEREOTYPE:
				return getStereotype();
			case DiagramPackage.DIAGRAM_ELEMENT__GRADIENT:
				return getGradient();
			case DiagramPackage.DIAGRAM_ELEMENT__BORDER:
				return getBorder();
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
			case DiagramPackage.DIAGRAM_ELEMENT__TYPE:
				setType((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__NOTES:
				getNotes().clear();
				getNotes().addAll((Collection<? extends Note>)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends EObject>)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__COLOR:
				setColor((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__DASHED:
				setDashed((Boolean)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__DOTTED:
				setDotted((Boolean)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__BOLD:
				setBold((Boolean)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				setId((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__NAME:
				getName().clear();
				getName().addAll((Collection<? extends EObject>)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends DiagramElement>)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection<? extends Connection>)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__STEREOTYPE:
				setStereotype((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__GRADIENT:
				setGradient((String)newValue);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__BORDER:
				setBorder((String)newValue);
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
			case DiagramPackage.DIAGRAM_ELEMENT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__NOTES:
				getNotes().clear();
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__DESCRIPTION:
				getDescription().clear();
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__DASHED:
				setDashed(DASHED_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__DOTTED:
				setDotted(DOTTED_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__BOLD:
				setBold(BOLD_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__NAME:
				getName().clear();
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS:
				getElements().clear();
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS:
				getConnections().clear();
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__STEREOTYPE:
				setStereotype(STEREOTYPE_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__GRADIENT:
				setGradient(GRADIENT_EDEFAULT);
				return;
			case DiagramPackage.DIAGRAM_ELEMENT__BORDER:
				setBorder(BORDER_EDEFAULT);
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
			case DiagramPackage.DIAGRAM_ELEMENT__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
			case DiagramPackage.DIAGRAM_ELEMENT__NOTES:
				return !getNotes().isEmpty();
			case DiagramPackage.DIAGRAM_ELEMENT__DESCRIPTION:
				return !getDescription().isEmpty();
			case DiagramPackage.DIAGRAM_ELEMENT__COLOR:
				return COLOR_EDEFAULT == null ? getColor() != null : !COLOR_EDEFAULT.equals(getColor());
			case DiagramPackage.DIAGRAM_ELEMENT__DASHED:
				return isDashed() != DASHED_EDEFAULT;
			case DiagramPackage.DIAGRAM_ELEMENT__DOTTED:
				return isDotted() != DOTTED_EDEFAULT;
			case DiagramPackage.DIAGRAM_ELEMENT__BOLD:
				return isBold() != BOLD_EDEFAULT;
			case DiagramPackage.DIAGRAM_ELEMENT__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case DiagramPackage.DIAGRAM_ELEMENT__NAME:
				return !getName().isEmpty();
			case DiagramPackage.DIAGRAM_ELEMENT__ELEMENTS:
				return !getElements().isEmpty();
			case DiagramPackage.DIAGRAM_ELEMENT__CONNECTIONS:
				return !getConnections().isEmpty();
			case DiagramPackage.DIAGRAM_ELEMENT__STEREOTYPE:
				return STEREOTYPE_EDEFAULT == null ? getStereotype() != null : !STEREOTYPE_EDEFAULT.equals(getStereotype());
			case DiagramPackage.DIAGRAM_ELEMENT__GRADIENT:
				return GRADIENT_EDEFAULT == null ? getGradient() != null : !GRADIENT_EDEFAULT.equals(getGradient());
			case DiagramPackage.DIAGRAM_ELEMENT__BORDER:
				return BORDER_EDEFAULT == null ? getBorder() != null : !BORDER_EDEFAULT.equals(getBorder());
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
		if (baseClass == Style.class) {
			switch (derivedFeatureID) {
				case DiagramPackage.DIAGRAM_ELEMENT__TYPE: return DiagramPackage.STYLE__TYPE;
				case DiagramPackage.DIAGRAM_ELEMENT__NOTES: return DiagramPackage.STYLE__NOTES;
				case DiagramPackage.DIAGRAM_ELEMENT__DESCRIPTION: return DiagramPackage.STYLE__DESCRIPTION;
				case DiagramPackage.DIAGRAM_ELEMENT__COLOR: return DiagramPackage.STYLE__COLOR;
				case DiagramPackage.DIAGRAM_ELEMENT__DASHED: return DiagramPackage.STYLE__DASHED;
				case DiagramPackage.DIAGRAM_ELEMENT__DOTTED: return DiagramPackage.STYLE__DOTTED;
				case DiagramPackage.DIAGRAM_ELEMENT__BOLD: return DiagramPackage.STYLE__BOLD;
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
		if (baseClass == Style.class) {
			switch (baseFeatureID) {
				case DiagramPackage.STYLE__TYPE: return DiagramPackage.DIAGRAM_ELEMENT__TYPE;
				case DiagramPackage.STYLE__NOTES: return DiagramPackage.DIAGRAM_ELEMENT__NOTES;
				case DiagramPackage.STYLE__DESCRIPTION: return DiagramPackage.DIAGRAM_ELEMENT__DESCRIPTION;
				case DiagramPackage.STYLE__COLOR: return DiagramPackage.DIAGRAM_ELEMENT__COLOR;
				case DiagramPackage.STYLE__DASHED: return DiagramPackage.DIAGRAM_ELEMENT__DASHED;
				case DiagramPackage.STYLE__DOTTED: return DiagramPackage.DIAGRAM_ELEMENT__DOTTED;
				case DiagramPackage.STYLE__BOLD: return DiagramPackage.DIAGRAM_ELEMENT__BOLD;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //DiagramElementImpl
