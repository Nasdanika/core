/**
 */
package org.nasdanika.ncore.impl;

import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.ModelElementImpl#getMarkers <em>Markers</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ModelElementImpl#getUris <em>Uris</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ModelElementImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ModelElementImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ModelElementImpl#getActionPrototype <em>Action Prototype</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ModelElementImpl#getRepresentations <em>Representations</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ModelElementImpl#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ModelElementImpl extends MinimalEObjectImpl.Container implements ModelElement {
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
	@SuppressWarnings("unchecked")
	@Override
	public EList<Marker> getMarkers() {
		return (EList<Marker>)eDynamicGet(NcorePackage.MODEL_ELEMENT__MARKERS, NcorePackage.Literals.MARKED__MARKERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getUris() {
		return (EList<String>)eDynamicGet(NcorePackage.MODEL_ELEMENT__URIS, NcorePackage.Literals.MODEL_ELEMENT__URIS, true, true);
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
	public EObject getActionPrototype() {
		return (EObject)eDynamicGet(NcorePackage.MODEL_ELEMENT__ACTION_PROTOTYPE, NcorePackage.Literals.MODEL_ELEMENT__ACTION_PROTOTYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActionPrototype(EObject newActionPrototype, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newActionPrototype, NcorePackage.MODEL_ELEMENT__ACTION_PROTOTYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActionPrototype(EObject newActionPrototype) {
		eDynamicSet(NcorePackage.MODEL_ELEMENT__ACTION_PROTOTYPE, NcorePackage.Literals.MODEL_ELEMENT__ACTION_PROTOTYPE, newActionPrototype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, String> getRepresentations() {
		return (EMap<String, String>)eDynamicGet(NcorePackage.MODEL_ELEMENT__REPRESENTATIONS, NcorePackage.Literals.MODEL_ELEMENT__REPRESENTATIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Property> getAnnotations() {
		return (EList<Property>)eDynamicGet(NcorePackage.MODEL_ELEMENT__ANNOTATIONS, NcorePackage.Literals.MODEL_ELEMENT__ANNOTATIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NcorePackage.MODEL_ELEMENT__MARKERS:
				return ((InternalEList<?>)getMarkers()).basicRemove(otherEnd, msgs);
			case NcorePackage.MODEL_ELEMENT__ACTION_PROTOTYPE:
				return basicSetActionPrototype(null, msgs);
			case NcorePackage.MODEL_ELEMENT__REPRESENTATIONS:
				return ((InternalEList<?>)getRepresentations()).basicRemove(otherEnd, msgs);
			case NcorePackage.MODEL_ELEMENT__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
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
			case NcorePackage.MODEL_ELEMENT__MARKERS:
				return getMarkers();
			case NcorePackage.MODEL_ELEMENT__URIS:
				return getUris();
			case NcorePackage.MODEL_ELEMENT__DESCRIPTION:
				return getDescription();
			case NcorePackage.MODEL_ELEMENT__UUID:
				return getUuid();
			case NcorePackage.MODEL_ELEMENT__ACTION_PROTOTYPE:
				return getActionPrototype();
			case NcorePackage.MODEL_ELEMENT__REPRESENTATIONS:
				if (coreType) return getRepresentations();
				else return getRepresentations().map();
			case NcorePackage.MODEL_ELEMENT__ANNOTATIONS:
				return getAnnotations();
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
			case NcorePackage.MODEL_ELEMENT__MARKERS:
				getMarkers().clear();
				getMarkers().addAll((Collection<? extends Marker>)newValue);
				return;
			case NcorePackage.MODEL_ELEMENT__URIS:
				getUris().clear();
				getUris().addAll((Collection<? extends String>)newValue);
				return;
			case NcorePackage.MODEL_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case NcorePackage.MODEL_ELEMENT__UUID:
				setUuid((String)newValue);
				return;
			case NcorePackage.MODEL_ELEMENT__ACTION_PROTOTYPE:
				setActionPrototype((EObject)newValue);
				return;
			case NcorePackage.MODEL_ELEMENT__REPRESENTATIONS:
				((EStructuralFeature.Setting)getRepresentations()).set(newValue);
				return;
			case NcorePackage.MODEL_ELEMENT__ANNOTATIONS:
				getAnnotations().clear();
				getAnnotations().addAll((Collection<? extends Property>)newValue);
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
			case NcorePackage.MODEL_ELEMENT__MARKERS:
				getMarkers().clear();
				return;
			case NcorePackage.MODEL_ELEMENT__URIS:
				getUris().clear();
				return;
			case NcorePackage.MODEL_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case NcorePackage.MODEL_ELEMENT__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case NcorePackage.MODEL_ELEMENT__ACTION_PROTOTYPE:
				setActionPrototype((EObject)null);
				return;
			case NcorePackage.MODEL_ELEMENT__REPRESENTATIONS:
				getRepresentations().clear();
				return;
			case NcorePackage.MODEL_ELEMENT__ANNOTATIONS:
				getAnnotations().clear();
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
			case NcorePackage.MODEL_ELEMENT__MARKERS:
				return !getMarkers().isEmpty();
			case NcorePackage.MODEL_ELEMENT__URIS:
				return !getUris().isEmpty();
			case NcorePackage.MODEL_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
			case NcorePackage.MODEL_ELEMENT__UUID:
				return UUID_EDEFAULT == null ? getUuid() != null : !UUID_EDEFAULT.equals(getUuid());
			case NcorePackage.MODEL_ELEMENT__ACTION_PROTOTYPE:
				return getActionPrototype() != null;
			case NcorePackage.MODEL_ELEMENT__REPRESENTATIONS:
				return !getRepresentations().isEmpty();
			case NcorePackage.MODEL_ELEMENT__ANNOTATIONS:
				return !getAnnotations().isEmpty();
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
				if (type.isInstance(next)) {
					for (URI nextURI: NcoreUtil.getIdentifiers((T) next)) {
						if (nextURI != null && nextURI.equals(uri)) {					
							return (T) next;
						}
					}
				}
			}
		}		
		List<? extends org.nasdanika.persistence.Marker> pMarkers = new ArrayList<>();
		List<? extends Marker> mMarkers = getMarkers();
		if (mMarkers == null || mMarkers.isEmpty()) {
			org.nasdanika.persistence.Marked pMarked = (org.nasdanika.persistence.Marked) EcoreUtil.getRegisteredAdapter(this, type);
			if (pMarked != null) {
				pMarkers = pMarked.getMarkers();
			}
		} else {
			pMarkers = mMarkers.stream().map(mMarker -> new org.nasdanika.persistence.MarkerImpl(mMarker.getLocation(), mMarker.getPosition())).collect(Collectors.toList());
		}		
		throw new ConfigurationException("Could not find " + type.getName() + " with uri " + uri, pMarkers);
	}
	
	/**
	 * Get a list of all objects in the resource set which point to this 
	 * object with the provided {@link EReference}'s opposite.
	 * @param <T>
	 * @param eReference
	 * @return
	 */
	protected <T extends EObject> EList<T> getOppositeReferrers(EReference reference) {
		return getReferrers(Objects.requireNonNull(NcoreUtil.getOpposite(reference), "Opposite is null: " + reference));
	}
	
	/**
	 * Traverses the resource set and finds all objects referring this one via the provided reference. 
	 * @param <T>
	 * @param eReference
	 * @return
	 */
	protected <T extends EObject> EList<T> getReferrers(EReference eReference) {
		return NcoreUtil.getReferrers(this, eReference);
	}
	
	/**
	 * Resolves proxy URI against this element URI - this allows to have both logical and physical proxies.
	 * a logical proxy URI shall be prefixed with ./ and can be relative.
	 */
	@Override
	public URI eProxyURI() {
		URI eProxyURI = super.eProxyURI();
		if (eProxyURI != null && eProxyURI.isRelative()) {
			for (URI base: NcoreUtil.getIdentifiers(this)) {
				if (base != null && !base.isRelative() && base.isHierarchical()) {
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
		return EcoreUtil.resolve(NcoreUtil.createProxy(type, uri), this);
	}
	
	protected <E extends EObject> List<E> resolveProxies(Collection<E> objects) {
		return objects.stream().map(this::resolveProxy).collect(Collectors.toList());
	}

	/**
	 * If argument is a proxy - resolves it. If it is not resolved throws {@link ConfigurationException} with a {@link org.nasdanika.common.persistence.Marker} from the proxy if it is {@link org.nasdanika.common.persistence.Marked} 
	 * and has a marker or from this object.  
	 * @param <E>
	 * @param eObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <E extends EObject> E resolveProxy(E eObject) {
		if (eObject.eIsProxy()) {
			eObject = (E) EcoreUtil.resolve(eObject, this);
			if (eObject.eIsProxy()) { // Not resolved.
				List<? extends org.nasdanika.persistence.Marker> markers = null;
				if (eObject instanceof org.nasdanika.persistence.Marked) {
					markers = ((org.nasdanika.persistence.Marked) eObject).getMarkers();
				}
				throw new ConfigurationException("Unresolved proxy: " + eObject, markers == null || markers.isEmpty() ? getMarkers() : markers);
			}
		}
		return eObject;	
	}

} //ModelElementImpl
