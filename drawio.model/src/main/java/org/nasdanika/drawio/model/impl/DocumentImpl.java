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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.drawio.model.Document;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.drawio.model.ModelPackage;
import org.nasdanika.drawio.model.Page;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.impl.DocumentImpl#getMarkers <em>Markers</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.DocumentImpl#getPages <em>Pages</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.DocumentImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.DocumentImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DocumentImpl extends MinimalEObjectImpl.Container implements Document {
	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DOCUMENT;
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
		return (EList<Marker>)eDynamicGet(ModelPackage.DOCUMENT__MARKERS, NcorePackage.Literals.MARKED__MARKERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Page> getPages() {
		return (EList<Page>)eDynamicGet(ModelPackage.DOCUMENT__PAGES, ModelPackage.Literals.DOCUMENT__PAGES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUri() {
		return (String)eDynamicGet(ModelPackage.DOCUMENT__URI, ModelPackage.Literals.DOCUMENT__URI, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUri(String newUri) {
		eDynamicSet(ModelPackage.DOCUMENT__URI, ModelPackage.Literals.DOCUMENT__URI, newUri);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSource() {
		return (String)eDynamicGet(ModelPackage.DOCUMENT__SOURCE, ModelPackage.Literals.DOCUMENT__SOURCE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSource(String newSource) {
		eDynamicSet(ModelPackage.DOCUMENT__SOURCE, ModelPackage.Literals.DOCUMENT__SOURCE, newSource);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Page getPageByName(String pageName) {
		for (Page page: getPages()) {
			if (pageName.equals(page.getName())) {
				return page;
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
	public Page getPageById(String pageId) {
		for (Page page: getPages()) {
			if (pageId.equals(page.getId())) {
				return page;
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
		for (Page page: getPages()) {
			ModelElement ret = page.getModelElementByProperty(name, value);
			if (ret != null) {
				return ret;
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
		getPages()
			.stream()
			.flatMap(p -> p.getModelElementsByProperty(name, value).stream())
			.forEach(ret::add);
		
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
			case ModelPackage.DOCUMENT__MARKERS:
				return ((InternalEList<?>)getMarkers()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENT__PAGES:
				return ((InternalEList<?>)getPages()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.DOCUMENT__MARKERS:
				return getMarkers();
			case ModelPackage.DOCUMENT__PAGES:
				return getPages();
			case ModelPackage.DOCUMENT__URI:
				return getUri();
			case ModelPackage.DOCUMENT__SOURCE:
				return getSource();
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
			case ModelPackage.DOCUMENT__MARKERS:
				getMarkers().clear();
				getMarkers().addAll((Collection<? extends Marker>)newValue);
				return;
			case ModelPackage.DOCUMENT__PAGES:
				getPages().clear();
				getPages().addAll((Collection<? extends Page>)newValue);
				return;
			case ModelPackage.DOCUMENT__URI:
				setUri((String)newValue);
				return;
			case ModelPackage.DOCUMENT__SOURCE:
				setSource((String)newValue);
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
			case ModelPackage.DOCUMENT__MARKERS:
				getMarkers().clear();
				return;
			case ModelPackage.DOCUMENT__PAGES:
				getPages().clear();
				return;
			case ModelPackage.DOCUMENT__URI:
				setUri(URI_EDEFAULT);
				return;
			case ModelPackage.DOCUMENT__SOURCE:
				setSource(SOURCE_EDEFAULT);
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
			case ModelPackage.DOCUMENT__MARKERS:
				return !getMarkers().isEmpty();
			case ModelPackage.DOCUMENT__PAGES:
				return !getPages().isEmpty();
			case ModelPackage.DOCUMENT__URI:
				return URI_EDEFAULT == null ? getUri() != null : !URI_EDEFAULT.equals(getUri());
			case ModelPackage.DOCUMENT__SOURCE:
				return SOURCE_EDEFAULT == null ? getSource() != null : !SOURCE_EDEFAULT.equals(getSource());
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
			case ModelPackage.DOCUMENT___GET_PAGE_BY_NAME__STRING:
				return getPageByName((String)arguments.get(0));
			case ModelPackage.DOCUMENT___GET_PAGE_BY_ID__STRING:
				return getPageById((String)arguments.get(0));
			case ModelPackage.DOCUMENT___GET_MODEL_ELEMENT_BY_ID__STRING:
				return getModelElementById((String)arguments.get(0));
			case ModelPackage.DOCUMENT___GET_MODEL_ELEMENT_BY_PROPERTY__STRING_STRING:
				return getModelElementByProperty((String)arguments.get(0), (String)arguments.get(1));
			case ModelPackage.DOCUMENT___GET_MODEL_ELEMENTS_BY_PROPERTY__STRING_STRING:
				return getModelElementsByProperty((String)arguments.get(0), (String)arguments.get(1));
		}
		return super.eInvoke(operationID, arguments);
	}

} //DocumentImpl
