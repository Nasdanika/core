/**
 */
package org.nasdanika.drawio.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.UUID;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.drawio.model.Model;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.drawio.model.ModelPackage;
import org.nasdanika.drawio.model.Page;
import org.nasdanika.drawio.model.Tag;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.StringIdentity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.impl.PageImpl#getMarkers <em>Markers</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.PageImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.PageImpl#getModel <em>Model</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.PageImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.PageImpl#getLinks <em>Links</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.PageImpl#getTags <em>Tags</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PageImpl extends MinimalEObjectImpl.Container implements Page {
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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected PageImpl() {
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
		return ModelPackage.Literals.PAGE;
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
		return (EList<Marker>)eDynamicGet(ModelPackage.PAGE__MARKERS, NcorePackage.Literals.MARKED__MARKERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Model getModel() {
		return (Model)eDynamicGet(ModelPackage.PAGE__MODEL, ModelPackage.Literals.PAGE__MODEL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(Model newModel, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newModel, ModelPackage.PAGE__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModel(Model newModel) {
		eDynamicSet(ModelPackage.PAGE__MODEL, ModelPackage.Literals.PAGE__MODEL, newModel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(ModelPackage.PAGE__NAME, ModelPackage.Literals.PAGE__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(ModelPackage.PAGE__NAME, ModelPackage.Literals.PAGE__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return (String)eDynamicGet(ModelPackage.PAGE__ID, NcorePackage.Literals.STRING_IDENTITY__ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eDynamicSet(ModelPackage.PAGE__ID, NcorePackage.Literals.STRING_IDENTITY__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<ModelElement> getLinks() {
		return (EList<ModelElement>)eDynamicGet(ModelPackage.PAGE__LINKS, ModelPackage.Literals.PAGE__LINKS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Tag> getTags() {
		return (EList<Tag>)eDynamicGet(ModelPackage.PAGE__TAGS, ModelPackage.Literals.PAGE__TAGS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public ModelElement getModelElementById(String modelElementId) {
		TreeIterator<EObject> cit = eAllContents();
		while (cit.hasNext()) {
			EObject next = cit.next();
			if (next instanceof ModelElement && ((ModelElement) next).getId().equals(modelElementId)) {
				return (ModelElement) next;
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
	public Tag getTag(String tagName) {
		for (Tag tag: getTags()) {
			if (tag.getName().equals(tagName)) {
				return tag;
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
	public ModelElement getModelElementByProperty(String name, String value) {
		TreeIterator<EObject> cit = eAllContents();
		while (cit.hasNext()) {
			EObject next = cit.next();
			if (next instanceof ModelElement) {
				ModelElement nextModelElement = (ModelElement) next;
				if (value.equals(nextModelElement.getProperties().get(name))) {
					return nextModelElement;
				}
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
	public EList<ModelElement> getModelElementsByProperty(String name, String value) {
		EList<ModelElement> ret = ECollections.newBasicEList();
		TreeIterator<EObject> cit = eAllContents();
		while (cit.hasNext()) {
			EObject next = cit.next();
			if (next instanceof ModelElement) {
				ModelElement nextModelElement = (ModelElement) next;
				if (value.equals(nextModelElement.getProperties().get(name))) {
					ret.add(nextModelElement);
				}
			}
		}
		return ret;
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
			case ModelPackage.PAGE__LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLinks()).basicAdd(otherEnd, msgs);
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
			case ModelPackage.PAGE__MARKERS:
				return ((InternalEList<?>)getMarkers()).basicRemove(otherEnd, msgs);
			case ModelPackage.PAGE__MODEL:
				return basicSetModel(null, msgs);
			case ModelPackage.PAGE__LINKS:
				return ((InternalEList<?>)getLinks()).basicRemove(otherEnd, msgs);
			case ModelPackage.PAGE__TAGS:
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
			case ModelPackage.PAGE__MARKERS:
				return getMarkers();
			case ModelPackage.PAGE__ID:
				return getId();
			case ModelPackage.PAGE__MODEL:
				return getModel();
			case ModelPackage.PAGE__NAME:
				return getName();
			case ModelPackage.PAGE__LINKS:
				return getLinks();
			case ModelPackage.PAGE__TAGS:
				return getTags();
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
			case ModelPackage.PAGE__MARKERS:
				getMarkers().clear();
				getMarkers().addAll((Collection<? extends Marker>)newValue);
				return;
			case ModelPackage.PAGE__ID:
				setId((String)newValue);
				return;
			case ModelPackage.PAGE__MODEL:
				setModel((Model)newValue);
				return;
			case ModelPackage.PAGE__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.PAGE__LINKS:
				getLinks().clear();
				getLinks().addAll((Collection<? extends ModelElement>)newValue);
				return;
			case ModelPackage.PAGE__TAGS:
				getTags().clear();
				getTags().addAll((Collection<? extends Tag>)newValue);
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
			case ModelPackage.PAGE__MARKERS:
				getMarkers().clear();
				return;
			case ModelPackage.PAGE__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelPackage.PAGE__MODEL:
				setModel((Model)null);
				return;
			case ModelPackage.PAGE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.PAGE__LINKS:
				getLinks().clear();
				return;
			case ModelPackage.PAGE__TAGS:
				getTags().clear();
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
			case ModelPackage.PAGE__MARKERS:
				return !getMarkers().isEmpty();
			case ModelPackage.PAGE__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case ModelPackage.PAGE__MODEL:
				return getModel() != null;
			case ModelPackage.PAGE__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case ModelPackage.PAGE__LINKS:
				return !getLinks().isEmpty();
			case ModelPackage.PAGE__TAGS:
				return !getTags().isEmpty();
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
		if (baseClass == StringIdentity.class) {
			switch (derivedFeatureID) {
				case ModelPackage.PAGE__ID: return NcorePackage.STRING_IDENTITY__ID;
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
		if (baseClass == StringIdentity.class) {
			switch (baseFeatureID) {
				case NcorePackage.STRING_IDENTITY__ID: return ModelPackage.PAGE__ID;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case ModelPackage.PAGE___GET_MODEL_ELEMENT_BY_ID__STRING:
				return getModelElementById((String)arguments.get(0));
			case ModelPackage.PAGE___GET_TAG__STRING:
				return getTag((String)arguments.get(0));
			case ModelPackage.PAGE___GET_MODEL_ELEMENT_BY_PROPERTY__STRING_STRING:
				return getModelElementByProperty((String)arguments.get(0), (String)arguments.get(1));
			case ModelPackage.PAGE___GET_MODEL_ELEMENTS_BY_PROPERTY__STRING_STRING:
				return getModelElementsByProperty((String)arguments.get(0), (String)arguments.get(1));
		}
		return super.eInvoke(operationID, arguments);
	}

} //PageImpl
