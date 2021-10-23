/**
 */
package org.nasdanika.ncore.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
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
import org.nasdanika.ncore.NamedElement;
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
 *   <li>{@link org.nasdanika.ncore.impl.ModelElementImpl#getUuid <em>Uuid</em>}</li>
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
	protected static final String URI_EDEFAULT = null;

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
	 * The default value of the '{@link #getUuid() <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUuid()
	 * @generated
	 * @ordered
	 */
	protected static final String UUID_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected ModelElementImpl() {
		super();
		setUuid(UUID.randomUUID().toString());
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
	
	private static String toString(URI uri) {
		return uri == null ? null : uri.toString();
	}
	
	/**
	 * Computes URI from the containment reference.
	 * @param eObj
	 * @return
	 */
	public static URI getUri(EObject eObj) {
		if (eObj == null) {
			return null;
		}
		
		EObject container = eObj.eContainer();
		if (container == null) {
//			Leads to stack overflow during loading.			
//			for (EObject referrer: getReferrers(eObj, NcorePackage.Literals.REFERENCE__TARGET)) {
//				return getUri(referrer);
//			}
			if (eObj instanceof ModelElement) {
				String uuid = ((ModelElement) eObj).getUuid();
				if (!Util.isBlank(uuid)) {
					return URI.createURI("uuid:" + uuid);
				}
			}
			return null;
		}
		
		// EMap
		EReference eContainmentFeature = eObj.eContainmentFeature();
		if (isEMapEntry(container) && "value".equals(eContainmentFeature.getName())) {
			EObject superContainer = container.eContainer();
			if (superContainer == null) {
				return null;
			}
			String superContainerURI = superContainer instanceof ModelElement ? ((ModelElement) superContainer).getUri() : toString(getUri(superContainer));
			if (Util.isBlank(superContainerURI)) {
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
		
		String containerURI = container instanceof ModelElement ? ((ModelElement) container).getUri() : toString(getUri(container));
		if (Util.isBlank(containerURI)) {
			return null;
		}
		
		StringBuilder uriBuilder = new StringBuilder(containerURI.toString());
		if (uriBuilder.charAt(uriBuilder.length() - 1) != '/') {
			uriBuilder.append("/");
		}
		
		uriBuilder.append(eContainmentFeature.getName());
		if (eContainmentFeature.isMany()) {		
			EList<EAttribute> eKeys = eContainmentFeature.getEKeys();
			if (eKeys.isEmpty()) {
				int idx = ((List<?>) container.eGet(eContainmentFeature)).indexOf(eObj);
				uriBuilder.append("/").append(idx);
			} else {
				for (EAttribute eKey: eKeys) {
					uriBuilder.append("/").append(eObj.eGet(eKey)); // TODO - add support for passing eKey path computer.
				}
			}
		}
		return URI.createURI(uriBuilder.toString());			
	}

	/**
	 * @param eObj
	 * @return true if the argument is an entry for EMap
	 */
	public static boolean isEMapEntry(EObject eObj) {
		if (eObj instanceof Map.Entry) {
			EReference cf = eObj.eContainmentFeature();
			return cf != null && cf.isMany() && cf.getEType().getInstanceClass() == Map.Entry.class;
		}
		return false;
	}
	
	/**
	 * Containment path relative to the object container.
	 * @param eObject
	 * @return null if there is no container. Reference name concatenated with object key for {@link EMap}'s and with object index for many-references.
	 */
	public static String containmentPath(EObject eObj) {		
		EObject container = eObj.eContainer();
		if (container == null) {
			return null;
		}
		EReference eContainmentFeature = eObj.eContainmentFeature();
		
		// EMap
		if (isEMapEntry(container) && "value".equals(eContainmentFeature.getName())) {
			EObject superContainer = container.eContainer();
			if (superContainer == null) {
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
			return container.eContainmentFeature().getName() + "/" + strKey;
		}
		
		if (eContainmentFeature.isMany()) {		
			EList<EAttribute> eKeys = eContainmentFeature.getEKeys();
			if (eKeys.isEmpty()) {
				int idx = ((List<?>) container.eGet(eContainmentFeature)).indexOf(eObj);
				return eContainmentFeature.getName() + "/" + idx;
			}
			StringBuilder pathBuilder = new StringBuilder(eContainmentFeature.getName());
			for (EAttribute eKey: eKeys) {
				pathBuilder.append("/").append(eObj.eGet(eKey));
			}
			return pathBuilder.toString();
		}
		
		return eContainmentFeature.getName();		
	}
	
	public static String relativeContainmentPath(EObject obj, EObject base) {
		if (obj == null || base == null) {
			return null;
		}
		if (EcoreUtil.isAncestor(obj, base)) {
			// TODO
		}
		if (EcoreUtil.isAncestor(base, obj)) {
			// TODO
		}
		for (EObject ancestor = obj.eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
			if (EcoreUtil.isAncestor(ancestor, obj)) {
				// TODO
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
	public String getUri() {
		String uri = (String) eDynamicGet(NcorePackage.MODEL_ELEMENT__URI, NcorePackage.Literals.MODEL_ELEMENT__URI, true, true);
		return Util.isBlank(uri) ? toString(getUri(this)) : uri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUri(String newUri) {
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
	public String getUuid() {
		return (String)eDynamicGet(NcorePackage.MODEL_ELEMENT__UUID, NcorePackage.Literals.MODEL_ELEMENT__UUID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUuid(String newUuid) {
		eDynamicSet(NcorePackage.MODEL_ELEMENT__UUID, NcorePackage.Literals.MODEL_ELEMENT__UUID, newUuid);
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
			case NcorePackage.MODEL_ELEMENT__UUID:
				return getUuid();
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
				setUri((String)newValue);
				return;
			case NcorePackage.MODEL_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case NcorePackage.MODEL_ELEMENT__UUID:
				setUuid((String)newValue);
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
			case NcorePackage.MODEL_ELEMENT__UUID:
				setUuid(UUID_EDEFAULT);
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
			case NcorePackage.MODEL_ELEMENT__UUID:
				return UUID_EDEFAULT == null ? getUuid() != null : !UUID_EDEFAULT.equals(getUuid());
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
	
	/**
	 * Resolves proxy URI against this element URI - this allows to have both logical and physical proxies.
	 * a logical proxy URI shall be prefixed with ./ and can be relative.
	 */
	@Override
	public URI eProxyURI() {
		URI eProxyURI = super.eProxyURI();
		if (eProxyURI != null && eProxyURI.isRelative()) {
			String baseStr = getUri();
			if (!Util.isBlank(baseStr)) {
				URI base = URI.createURI(baseStr);
				if (!base.isRelative()) {
					return eProxyURI.resolve(base);
				}
			}
		}
		return eProxyURI;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T adaptTo(Class<T> type) {
		if (type.isInstance(this)) {
			return (T) this;
		}
		
		Adapter adapter = EcoreUtil.getRegisteredAdapter(this, type);
		if (type.isInstance(adapter)) {
			return (T) adapter;
		}
		return null;
	}
	
	/**
	 * Creates a proxy for a given type and URI and then attempts to resolve it in the context of this EObject.
	 * @param type
	 * @param uri
	 * @return
	 */
	protected EObject resolve(EClass type, URI uri) {
		return EcoreUtil.resolve(createProxy(type, uri), this);
	}
	
	/**
	 * Creates a proxy of given type with given URI 
	 * @param eClass
	 * @param uri
	 * @return
	 */
	protected static EObject createProxy(EClass type, URI uri) {
		EObject proxy = type.getEPackage().getEFactoryInstance().create(type);
		((InternalEObject) proxy).eSetProxyURI(uri);
		if (proxy instanceof NamedElement) {
			((NamedElement) proxy).setName(type.getName() + " proxy for " + uri);
		}
		return proxy;
	}

} //ModelElementImpl
