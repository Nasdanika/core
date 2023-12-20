/**
 */
package org.nasdanika.drawio.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.drawio.model.Layer;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.drawio.model.ModelPackage;
import org.nasdanika.drawio.model.Root;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.impl.RootImpl#getLayers <em>Layers</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RootImpl extends ModelElementImpl implements Root {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Layer> getLayers() {
		return (EList<Layer>)eDynamicGet(ModelPackage.ROOT__LAYERS, ModelPackage.Literals.ROOT__LAYERS, true, true);
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.ROOT__LAYERS:
				return ((InternalEList<?>)getLayers()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.ROOT__LAYERS:
				return getLayers();
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
			case ModelPackage.ROOT__LAYERS:
				getLayers().clear();
				getLayers().addAll((Collection<? extends Layer>)newValue);
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
			case ModelPackage.ROOT__LAYERS:
				getLayers().clear();
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
			case ModelPackage.ROOT__LAYERS:
				return !getLayers().isEmpty();
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
			case ModelPackage.ROOT___GET_MODEL_ELEMENT_BY_ID__STRING:
				return getModelElementById((String)arguments.get(0));
			case ModelPackage.ROOT___GET_MODEL_ELEMENT_BY_PROPERTY__STRING_STRING:
				return getModelElementByProperty((String)arguments.get(0), (String)arguments.get(1));
			case ModelPackage.ROOT___GET_MODEL_ELEMENTS_BY_PROPERTY__STRING_STRING:
				return getModelElementsByProperty((String)arguments.get(0), (String)arguments.get(1));
		}
		return super.eInvoke(operationID, arguments);
	}

} //RootImpl
