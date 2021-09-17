/**
 */
package org.nasdanika.ncore.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.ModelElementImpl#getMarker <em>Marker</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ModelElementImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ModelElementImpl#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ModelElementImpl extends MinimalEObjectImpl.Container implements ModelElement {
	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final URI URI_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.MODEL_ELEMENT;
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
	public Marker getMarker() {
		return (Marker)eDynamicGet(NcorePackage.MODEL_ELEMENT__MARKER, NcorePackage.Literals.MARKED__MARKER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMarker(Marker newMarker, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newMarker, NcorePackage.MODEL_ELEMENT__MARKER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMarker(Marker newMarker) {
		eDynamicSet(NcorePackage.MODEL_ELEMENT__MARKER, NcorePackage.Literals.MARKED__MARKER, newMarker);
	}
	
	/**
	 * Computes URI from the containment reference.
	 * @param eObj
	 * @return
	 */
	private static URI getContainmentUri(EObject eObj) {
		if (eObj == null) {
			return null;
		}
		
		EObject container = eObj.eContainer();
		if (container == null) {
			for (EObject referrer: getReferrers(eObj, NcorePackage.Literals.REFERENCE__TARGET)) {
				return getContainmentUri(referrer);
			}
			return null;
		}
		
		// EMap
		EReference eContainmentFeature = eObj.eContainmentFeature();
		if (container instanceof Map.Entry && "value".equals(eContainmentFeature.getName())) {
			EObject superContainer = container.eContainer();
			if (superContainer == null) {
				return null;
			}
			URI superContainerURI = superContainer instanceof ModelElement ? ((ModelElement) superContainer).getUri() : getContainmentUri(superContainer);
			if (superContainerURI == null) {
				return null;
			}
			Object key = ((Map.Entry<?,?>) container).getKey();
			if (key == null) {
				return null;
			}
			String strKey = String.valueOf(key);
			if (Util.isBlank(strKey)) {
				return null;
			}
			StringBuilder uriBuilder = new StringBuilder(superContainerURI.toString());
			if (uriBuilder.charAt(uriBuilder.length() - 1) != '/') {
				uriBuilder.append("/");
			}
			
			uriBuilder.append(container.eContainmentFeature().getName()).append("/").append(strKey);
			return URI.createURI(uriBuilder.toString());			
		}
		
		URI containerURI = container instanceof ModelElement ? ((ModelElement) container).getUri() : getContainmentUri(container);
		if (containerURI == null) {
			return null;
		}
		
		StringBuilder uriBuilder = new StringBuilder(containerURI.toString());
		if (uriBuilder.charAt(uriBuilder.length() - 1) != '/') {
			uriBuilder.append("/");
		}
		
		uriBuilder.append(eContainmentFeature.getName());
		if (eContainmentFeature.isMany()) {		
			int idx = ((List<?>) container.eGet(eContainmentFeature)).indexOf(eObj);
			uriBuilder.append("/").append(idx);
		}
		return URI.createURI(uriBuilder.toString());			
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public URI getUri() {
		URI uri = (URI)eDynamicGet(NcorePackage.MODEL_ELEMENT__URI, NcorePackage.Literals.MODEL_ELEMENT__URI, true, true);
		return uri == null ? getContainmentUri(this) : uri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUri(URI newUri) {
		eDynamicSet(NcorePackage.MODEL_ELEMENT__URI, NcorePackage.Literals.MODEL_ELEMENT__URI, newUri);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return (String)eDynamicGet(NcorePackage.MODEL_ELEMENT__DESCRIPTION, NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		eDynamicSet(NcorePackage.MODEL_ELEMENT__DESCRIPTION, NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NcorePackage.MODEL_ELEMENT__MARKER:
				return basicSetMarker(null, msgs);
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
			case NcorePackage.MODEL_ELEMENT__MARKER:
				return getMarker();
			case NcorePackage.MODEL_ELEMENT__URI:
				return getUri();
			case NcorePackage.MODEL_ELEMENT__DESCRIPTION:
				return getDescription();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NcorePackage.MODEL_ELEMENT__MARKER:
				setMarker((Marker)newValue);
				return;
			case NcorePackage.MODEL_ELEMENT__URI:
				setUri((URI)newValue);
				return;
			case NcorePackage.MODEL_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
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
			case NcorePackage.MODEL_ELEMENT__MARKER:
				setMarker((Marker)null);
				return;
			case NcorePackage.MODEL_ELEMENT__URI:
				setUri(URI_EDEFAULT);
				return;
			case NcorePackage.MODEL_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
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
			case NcorePackage.MODEL_ELEMENT__MARKER:
				return getMarker() != null;
			case NcorePackage.MODEL_ELEMENT__URI:
				return URI_EDEFAULT == null ? getUri() != null : !URI_EDEFAULT.equals(getUri());
			case NcorePackage.MODEL_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
		}
		return super.eIsSet(featureID);
	}
	
	protected <T extends ModelElement> EList<T> findByURI(Class<T> type, Collection<URI> uris) {
		EList<T> ret = new BasicInternalEList<>(type);
		uris.stream().map(uri -> findByURI(type, uri)).forEach(ret::add);
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends ModelElement> T findByURI(Class<T> type, URI uri) {
		Resource res = eResource(); 
		if (res != null) {
			ResourceSet rSet = res.getResourceSet();
			TreeIterator<?> cit = rSet == null ? res.getAllContents() : rSet. getAllContents();
			while (cit.hasNext()) {
				Object next = cit.next(); 
				if (type.isInstance(next) && uri.toString().equals(((T) next).getUri())) {
					return (T) next;
				}
			}
		}
		org.nasdanika.common.persistence.Marker pMarker = null; 
		Marker mMarker = getMarker();
		if (mMarker == null) {
			org.nasdanika.common.persistence.Marked pMarked = (org.nasdanika.common.persistence.Marked) EcoreUtil.getRegisteredAdapter(this, type);
			if (pMarked != null) {
				pMarker = pMarked.getMarker();
			}
		} else {
			pMarker = new org.nasdanika.common.persistence.MarkerImpl(mMarker.getLocation(), mMarker.getLine(), mMarker.getColumn());
		}		
		throw new ConfigurationException("Could not find " + type.getName() + " with uri " + uri, pMarker);
	}
	
	/**
	 * Get a list of all objects in the resource set which point to this 
	 * object with the provided {@link EReference}'s opposite.
	 * @param <T>
	 * @param eReference
	 * @return
	 */
	protected <T extends EObject> EList<T> getOppositeReferrers(EReference reference) {
		return getReferrers(Objects.requireNonNull(reference.getEOpposite(), "Opposite is null: " + reference));
	}
	
	/**
	 * Traverses the resource set and finds all objects referring this one via the provided reference. 
	 * @param <T>
	 * @param eReference
	 * @return
	 */
	protected <T extends EObject> EList<T> getReferrers(EReference eReference) {
		return getReferrers(this, eReference);
	}
	
	/**
	 * Finds objects referencing the argument object (target) via the specified reference.
	 * @param <T>
	 * @param target Referenced object
	 * @param eReference Reference
	 * @return Object referencing this one via the specified reference.
	 */
	public static <T extends EObject> EList<T> getReferrers(EObject target, EReference eReference) {
		EList<T> ret = new BasicInternalEList<>(EObject.class);
		
		Resource res = target.eResource(); 
		TreeIterator<?> cit = null;
		if (res == null) {
			EObject root = target;
			EObject rc;
			while ((rc = root.eContainer()) != null) {
				root = rc;
			}
			if (root != null) {
				collect(root, target, eReference, ret);
				cit = root.eAllContents();
			}			
		} else {
			ResourceSet rSet = res.getResourceSet();
			cit = rSet == null ? res.getAllContents() : rSet. getAllContents();
		}
		if (cit != null) {
			while (cit.hasNext()) {
				collect(cit.next(), target, eReference, ret);
			}			
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	private static <T extends EObject> void collect(
			Object source, 
			EObject target, 
			EReference eReference,
			EList<T> accumulator) {
		if (eReference.getEContainingClass().isInstance(source)) {
			Object value = ((EObject) source).eGet(eReference);
			if (eReference.isMany()) {
				if (((Collection<?>) value).contains(target)) {
					accumulator.add((T) source);
				}
			} else if (value == target) {
				accumulator.add((T) source);
			}					
		}
	}

} //ModelElementImpl
