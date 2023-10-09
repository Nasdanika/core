/**
 */
package org.nasdanika.drawio.model.impl;

import java.util.Collection;
import java.util.UUID;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.drawio.model.ModelPackage;
import org.nasdanika.drawio.model.Page;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getMarkers <em>Markers</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getLink <em>Link</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getLinkedPage <em>Linked Page</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#isVisible <em>Visible</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ModelElementImpl extends MinimalEObjectImpl.Container implements ModelElement {
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
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;
	/**
	 * The default value of the '{@link #getLink() <em>Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected static final String LINK_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTooltip() <em>Tooltip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTooltip()
	 * @generated
	 * @ordered
	 */
	protected static final String TOOLTIP_EDEFAULT = null;
	/**
	 * The default value of the '{@link #isVisible() <em>Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVisible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VISIBLE_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected ModelElementImpl() {
		super();
		setId(UUID.randomUUID().toString());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.MODEL_ELEMENT;
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
	public EList<Marker> getMarkers() {
		return (EList<Marker>)eDynamicGet(ModelPackage.MODEL_ELEMENT__MARKERS, NcorePackage.Literals.MARKED__MARKERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, String> getProperties() {
		return (EMap<String, String>)eDynamicGet(ModelPackage.MODEL_ELEMENT__PROPERTIES, ModelPackage.Literals.MODEL_ELEMENT__PROPERTIES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return (String)eDynamicGet(ModelPackage.MODEL_ELEMENT__ID, ModelPackage.Literals.MODEL_ELEMENT__ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eDynamicSet(ModelPackage.MODEL_ELEMENT__ID, ModelPackage.Literals.MODEL_ELEMENT__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLabel() {
		return (String)eDynamicGet(ModelPackage.MODEL_ELEMENT__LABEL, ModelPackage.Literals.MODEL_ELEMENT__LABEL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLabel(String newLabel) {
		eDynamicSet(ModelPackage.MODEL_ELEMENT__LABEL, ModelPackage.Literals.MODEL_ELEMENT__LABEL, newLabel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLink() {
		return (String)eDynamicGet(ModelPackage.MODEL_ELEMENT__LINK, ModelPackage.Literals.MODEL_ELEMENT__LINK, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLink(String newLink) {
		eDynamicSet(ModelPackage.MODEL_ELEMENT__LINK, ModelPackage.Literals.MODEL_ELEMENT__LINK, newLink);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Page getLinkedPage() {
		return (Page)eDynamicGet(ModelPackage.MODEL_ELEMENT__LINKED_PAGE, ModelPackage.Literals.MODEL_ELEMENT__LINKED_PAGE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page basicGetLinkedPage() {
		return (Page)eDynamicGet(ModelPackage.MODEL_ELEMENT__LINKED_PAGE, ModelPackage.Literals.MODEL_ELEMENT__LINKED_PAGE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLinkedPage(Page newLinkedPage, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newLinkedPage, ModelPackage.MODEL_ELEMENT__LINKED_PAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLinkedPage(Page newLinkedPage) {
		eDynamicSet(ModelPackage.MODEL_ELEMENT__LINKED_PAGE, ModelPackage.Literals.MODEL_ELEMENT__LINKED_PAGE, newLinkedPage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, String> getStyle() {
		return (EMap<String, String>)eDynamicGet(ModelPackage.MODEL_ELEMENT__STYLE, ModelPackage.Literals.MODEL_ELEMENT__STYLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getTags() {
		return (EList<String>)eDynamicGet(ModelPackage.MODEL_ELEMENT__TAGS, ModelPackage.Literals.MODEL_ELEMENT__TAGS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTooltip() {
		return (String)eDynamicGet(ModelPackage.MODEL_ELEMENT__TOOLTIP, ModelPackage.Literals.MODEL_ELEMENT__TOOLTIP, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTooltip(String newTooltip) {
		eDynamicSet(ModelPackage.MODEL_ELEMENT__TOOLTIP, ModelPackage.Literals.MODEL_ELEMENT__TOOLTIP, newTooltip);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isVisible() {
		return (Boolean)eDynamicGet(ModelPackage.MODEL_ELEMENT__VISIBLE, ModelPackage.Literals.MODEL_ELEMENT__VISIBLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVisible(boolean newVisible) {
		eDynamicSet(ModelPackage.MODEL_ELEMENT__VISIBLE, ModelPackage.Literals.MODEL_ELEMENT__VISIBLE, newVisible);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.MODEL_ELEMENT__LINKED_PAGE:
				Page linkedPage = basicGetLinkedPage();
				if (linkedPage != null)
					msgs = ((InternalEObject)linkedPage).eInverseRemove(this, ModelPackage.PAGE__LINKS, Page.class, msgs);
				return basicSetLinkedPage((Page)otherEnd, msgs);
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
			case ModelPackage.MODEL_ELEMENT__MARKERS:
				return ((InternalEList<?>)getMarkers()).basicRemove(otherEnd, msgs);
			case ModelPackage.MODEL_ELEMENT__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ModelPackage.MODEL_ELEMENT__LINKED_PAGE:
				return basicSetLinkedPage(null, msgs);
			case ModelPackage.MODEL_ELEMENT__STYLE:
				return ((InternalEList<?>)getStyle()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.MODEL_ELEMENT__MARKERS:
				return getMarkers();
			case ModelPackage.MODEL_ELEMENT__PROPERTIES:
				if (coreType) return getProperties();
				else return getProperties().map();
			case ModelPackage.MODEL_ELEMENT__ID:
				return getId();
			case ModelPackage.MODEL_ELEMENT__LABEL:
				return getLabel();
			case ModelPackage.MODEL_ELEMENT__LINK:
				return getLink();
			case ModelPackage.MODEL_ELEMENT__LINKED_PAGE:
				if (resolve) return getLinkedPage();
				return basicGetLinkedPage();
			case ModelPackage.MODEL_ELEMENT__STYLE:
				if (coreType) return getStyle();
				else return getStyle().map();
			case ModelPackage.MODEL_ELEMENT__TAGS:
				return getTags();
			case ModelPackage.MODEL_ELEMENT__TOOLTIP:
				return getTooltip();
			case ModelPackage.MODEL_ELEMENT__VISIBLE:
				return isVisible();
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
			case ModelPackage.MODEL_ELEMENT__MARKERS:
				getMarkers().clear();
				getMarkers().addAll((Collection<? extends Marker>)newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__PROPERTIES:
				((EStructuralFeature.Setting)getProperties()).set(newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__ID:
				setId((String)newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__LABEL:
				setLabel((String)newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__LINK:
				setLink((String)newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__LINKED_PAGE:
				setLinkedPage((Page)newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__STYLE:
				((EStructuralFeature.Setting)getStyle()).set(newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__TAGS:
				getTags().clear();
				getTags().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__TOOLTIP:
				setTooltip((String)newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__VISIBLE:
				setVisible((Boolean)newValue);
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
			case ModelPackage.MODEL_ELEMENT__MARKERS:
				getMarkers().clear();
				return;
			case ModelPackage.MODEL_ELEMENT__PROPERTIES:
				getProperties().clear();
				return;
			case ModelPackage.MODEL_ELEMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelPackage.MODEL_ELEMENT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case ModelPackage.MODEL_ELEMENT__LINK:
				setLink(LINK_EDEFAULT);
				return;
			case ModelPackage.MODEL_ELEMENT__LINKED_PAGE:
				setLinkedPage((Page)null);
				return;
			case ModelPackage.MODEL_ELEMENT__STYLE:
				getStyle().clear();
				return;
			case ModelPackage.MODEL_ELEMENT__TAGS:
				getTags().clear();
				return;
			case ModelPackage.MODEL_ELEMENT__TOOLTIP:
				setTooltip(TOOLTIP_EDEFAULT);
				return;
			case ModelPackage.MODEL_ELEMENT__VISIBLE:
				setVisible(VISIBLE_EDEFAULT);
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
			case ModelPackage.MODEL_ELEMENT__MARKERS:
				return !getMarkers().isEmpty();
			case ModelPackage.MODEL_ELEMENT__PROPERTIES:
				return !getProperties().isEmpty();
			case ModelPackage.MODEL_ELEMENT__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case ModelPackage.MODEL_ELEMENT__LABEL:
				return LABEL_EDEFAULT == null ? getLabel() != null : !LABEL_EDEFAULT.equals(getLabel());
			case ModelPackage.MODEL_ELEMENT__LINK:
				return LINK_EDEFAULT == null ? getLink() != null : !LINK_EDEFAULT.equals(getLink());
			case ModelPackage.MODEL_ELEMENT__LINKED_PAGE:
				return basicGetLinkedPage() != null;
			case ModelPackage.MODEL_ELEMENT__STYLE:
				return !getStyle().isEmpty();
			case ModelPackage.MODEL_ELEMENT__TAGS:
				return !getTags().isEmpty();
			case ModelPackage.MODEL_ELEMENT__TOOLTIP:
				return TOOLTIP_EDEFAULT == null ? getTooltip() != null : !TOOLTIP_EDEFAULT.equals(getTooltip());
			case ModelPackage.MODEL_ELEMENT__VISIBLE:
				return isVisible() != VISIBLE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //ModelElementImpl
