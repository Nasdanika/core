/**
 */
package org.nasdanika.drawio.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.UUID;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.drawio.model.Document;
import org.nasdanika.drawio.model.LinkTarget;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.drawio.model.ModelPackage;
import org.nasdanika.drawio.model.Page;
import org.nasdanika.drawio.model.Tag;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getLink <em>Link</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getLinkTarget <em>Link Target</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.ModelElementImpl#isVisible <em>Visible</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ModelElementImpl extends LinkTargetImpl implements ModelElement {
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
	public LinkTarget getLinkTarget() {
		return (LinkTarget)eDynamicGet(ModelPackage.MODEL_ELEMENT__LINK_TARGET, ModelPackage.Literals.MODEL_ELEMENT__LINK_TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkTarget basicGetLinkTarget() {
		return (LinkTarget)eDynamicGet(ModelPackage.MODEL_ELEMENT__LINK_TARGET, ModelPackage.Literals.MODEL_ELEMENT__LINK_TARGET, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLinkTarget(LinkTarget newLinkTarget, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newLinkTarget, ModelPackage.MODEL_ELEMENT__LINK_TARGET, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLinkTarget(LinkTarget newLinkTarget) {
		eDynamicSet(ModelPackage.MODEL_ELEMENT__LINK_TARGET, ModelPackage.Literals.MODEL_ELEMENT__LINK_TARGET, newLinkTarget);
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
	public EList<Tag> getTags() {
		return (EList<Tag>)eDynamicGet(ModelPackage.MODEL_ELEMENT__TAGS, ModelPackage.Literals.MODEL_ELEMENT__TAGS, true, true);
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
	 * @generated NOT
	 */
	@Override
	public Document getDocument() {
		for (EObject ec = eContainer(); ec != null; ec = ec.eContainer()) {
			if (ec instanceof Document) {
				return (Document) ec;
			} 
		}			
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Page getPage() {
		for (EObject ec = eContainer(); ec != null; ec = ec.eContainer()) {
			if (ec instanceof Page) {
				return (Page) ec;
			} 
		}			
		return null;
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
			case ModelPackage.MODEL_ELEMENT__LINK_TARGET:
				LinkTarget linkTarget = basicGetLinkTarget();
				if (linkTarget != null)
					msgs = ((InternalEObject)linkTarget).eInverseRemove(this, ModelPackage.LINK_TARGET__LINKS, LinkTarget.class, msgs);
				return basicSetLinkTarget((LinkTarget)otherEnd, msgs);
			case ModelPackage.MODEL_ELEMENT__TAGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTags()).basicAdd(otherEnd, msgs);
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
			case ModelPackage.MODEL_ELEMENT__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case ModelPackage.MODEL_ELEMENT__LINK_TARGET:
				return basicSetLinkTarget(null, msgs);
			case ModelPackage.MODEL_ELEMENT__STYLE:
				return ((InternalEList<?>)getStyle()).basicRemove(otherEnd, msgs);
			case ModelPackage.MODEL_ELEMENT__TAGS:
				return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.MODEL_ELEMENT__PROPERTIES:
				if (coreType) return getProperties();
				else return getProperties().map();
			case ModelPackage.MODEL_ELEMENT__LABEL:
				return getLabel();
			case ModelPackage.MODEL_ELEMENT__LINK:
				return getLink();
			case ModelPackage.MODEL_ELEMENT__LINK_TARGET:
				if (resolve) return getLinkTarget();
				return basicGetLinkTarget();
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
			case ModelPackage.MODEL_ELEMENT__PROPERTIES:
				((EStructuralFeature.Setting)getProperties()).set(newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__LABEL:
				setLabel((String)newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__LINK:
				setLink((String)newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__LINK_TARGET:
				setLinkTarget((LinkTarget)newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__STYLE:
				((EStructuralFeature.Setting)getStyle()).set(newValue);
				return;
			case ModelPackage.MODEL_ELEMENT__TAGS:
				getTags().clear();
				getTags().addAll((Collection<? extends Tag>)newValue);
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
			case ModelPackage.MODEL_ELEMENT__PROPERTIES:
				getProperties().clear();
				return;
			case ModelPackage.MODEL_ELEMENT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case ModelPackage.MODEL_ELEMENT__LINK:
				setLink(LINK_EDEFAULT);
				return;
			case ModelPackage.MODEL_ELEMENT__LINK_TARGET:
				setLinkTarget((LinkTarget)null);
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
			case ModelPackage.MODEL_ELEMENT__PROPERTIES:
				return !getProperties().isEmpty();
			case ModelPackage.MODEL_ELEMENT__LABEL:
				return LABEL_EDEFAULT == null ? getLabel() != null : !LABEL_EDEFAULT.equals(getLabel());
			case ModelPackage.MODEL_ELEMENT__LINK:
				return LINK_EDEFAULT == null ? getLink() != null : !LINK_EDEFAULT.equals(getLink());
			case ModelPackage.MODEL_ELEMENT__LINK_TARGET:
				return basicGetLinkTarget() != null;
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case ModelPackage.MODEL_ELEMENT___GET_DOCUMENT:
				return getDocument();
			case ModelPackage.MODEL_ELEMENT___GET_PAGE:
				return getPage();
		}
		return super.eInvoke(operationID, arguments);
	}

} //ModelElementImpl
