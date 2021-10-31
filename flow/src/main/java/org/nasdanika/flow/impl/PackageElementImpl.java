/**
 */
package org.nasdanika.flow.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.emf.persistence.FeatureCache;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.PackageElement;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Resource;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.impl.NamedElementImpl;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.PackageElementImpl#getPrototype <em>Prototype</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageElementImpl#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageElementImpl#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageElementImpl#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageElementImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageElementImpl#getRepresentations <em>Representations</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PackageElementImpl<T extends PackageElement<T>> extends NamedElementImpl implements PackageElement<T> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.PACKAGE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getPrototype() {
		return (T)eDynamicGet(FlowPackage.PACKAGE_ELEMENT__PROTOTYPE, FlowPackage.Literals.PACKAGE_ELEMENT__PROTOTYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public T basicGetPrototype() {
		return (T)eDynamicGet(FlowPackage.PACKAGE_ELEMENT__PROTOTYPE, FlowPackage.Literals.PACKAGE_ELEMENT__PROTOTYPE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrototype(T newPrototype) {
		eDynamicSet(FlowPackage.PACKAGE_ELEMENT__PROTOTYPE, FlowPackage.Literals.PACKAGE_ELEMENT__PROTOTYPE, newPrototype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<T> getExtensions() {
		return getReferrers(FlowPackage.Literals.PACKAGE_ELEMENT__EXTENDS);
	}	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<T> getExtends() {
		throw new UnsupportedOperationException("Override at subclasses");
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getModifiers() {
		return (EList<String>)eDynamicGet(FlowPackage.PACKAGE_ELEMENT__MODIFIERS, FlowPackage.Literals.PACKAGE_ELEMENT__MODIFIERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getDocumentation() {
		return (EList<EObject>)eDynamicGet(FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION, FlowPackage.Literals.PACKAGE_ELEMENT__DOCUMENTATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Diagram> getRepresentations() {
		return (EMap<String, Diagram>)eDynamicGet(FlowPackage.PACKAGE_ELEMENT__REPRESENTATIONS, FlowPackage.Literals.PACKAGE_ELEMENT__REPRESENTATIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T create() {
		T ret = (T) EcoreUtil.create(eClass());
		ret.setPrototype((T) this);
		return ret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void apply(T instance) {		
		// Calls apply for all bases.
		for (T base: resolveProxies(getExtends())) {
			base.apply(instance);
		}
		
		// Setting inherited attributes if they are not set yet.
		for (EAttribute attr: eClass().getEAllAttributes()) {
			if (attr.isChangeable() && eIsSet(attr)) {
				Object attrValue = eGet(attr);
				instance.eSet(attr, attrValue);
			}
		}
		
		// Documentation
		EList<EObject> documentation = getDocumentation();
		if (!documentation.isEmpty()) {
			EList<EObject> instanceDocumentation = instance.getDocumentation();
			instanceDocumentation.clear();
			instanceDocumentation.addAll(EcoreUtil.copyAll(documentation));
		}
		
		// Marker
		Marker marker = getMarker();
		if (marker != null) {
			instance.setMarker(EcoreUtil.copy(marker));
		}
		
		// Representations
		for (Entry<String, Diagram> re: getRepresentations().entrySet()) {
			Diagram representation = re.getValue();
			EMap<String, Diagram> instanceRepresentations = instance.getRepresentations();
			String representationKey = re.getKey();
			if (representation == null) {
				instanceRepresentations.removeKey(representationKey);
			} else {
				instanceRepresentations.put(representationKey, EcoreUtil.copy(representation));
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void resolve(T packageElement) {
		throw new UnsupportedOperationException();
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
			case FlowPackage.PACKAGE_ELEMENT__EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtensions()).basicAdd(otherEnd, msgs);
			case FlowPackage.PACKAGE_ELEMENT__EXTENDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtends()).basicAdd(otherEnd, msgs);
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
			case FlowPackage.PACKAGE_ELEMENT__EXTENSIONS:
				return ((InternalEList<?>)getExtensions()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE_ELEMENT__EXTENDS:
				return ((InternalEList<?>)getExtends()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION:
				return ((InternalEList<?>)getDocumentation()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE_ELEMENT__REPRESENTATIONS:
				return ((InternalEList<?>)getRepresentations()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.PACKAGE_ELEMENT__PROTOTYPE:
				if (resolve) return getPrototype();
				return basicGetPrototype();
			case FlowPackage.PACKAGE_ELEMENT__EXTENSIONS:
				return getExtensions();
			case FlowPackage.PACKAGE_ELEMENT__EXTENDS:
				return getExtends();
			case FlowPackage.PACKAGE_ELEMENT__MODIFIERS:
				return getModifiers();
			case FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION:
				return getDocumentation();
			case FlowPackage.PACKAGE_ELEMENT__REPRESENTATIONS:
				if (coreType) return getRepresentations();
				else return getRepresentations().map();
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
			case FlowPackage.PACKAGE_ELEMENT__PROTOTYPE:
				setPrototype((T)newValue);
				return;
			case FlowPackage.PACKAGE_ELEMENT__MODIFIERS:
				getModifiers().clear();
				getModifiers().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION:
				getDocumentation().clear();
				getDocumentation().addAll((Collection<? extends EObject>)newValue);
				return;
			case FlowPackage.PACKAGE_ELEMENT__REPRESENTATIONS:
				((EStructuralFeature.Setting)getRepresentations()).set(newValue);
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
			case FlowPackage.PACKAGE_ELEMENT__PROTOTYPE:
				setPrototype((T)null);
				return;
			case FlowPackage.PACKAGE_ELEMENT__MODIFIERS:
				getModifiers().clear();
				return;
			case FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION:
				getDocumentation().clear();
				return;
			case FlowPackage.PACKAGE_ELEMENT__REPRESENTATIONS:
				getRepresentations().clear();
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
			case FlowPackage.PACKAGE_ELEMENT__PROTOTYPE:
				return basicGetPrototype() != null;
			case FlowPackage.PACKAGE_ELEMENT__EXTENSIONS:
				return !getExtensions().isEmpty();
			case FlowPackage.PACKAGE_ELEMENT__EXTENDS:
				return !getExtends().isEmpty();
			case FlowPackage.PACKAGE_ELEMENT__MODIFIERS:
				return !getModifiers().isEmpty();
			case FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION:
				return !getDocumentation().isEmpty();
			case FlowPackage.PACKAGE_ELEMENT__REPRESENTATIONS:
				return !getRepresentations().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case FlowPackage.PACKAGE_ELEMENT___CREATE:
				return create();
			case FlowPackage.PACKAGE_ELEMENT___APPLY__PACKAGEELEMENT:
				apply((T)arguments.get(0));
				return null;
			case FlowPackage.PACKAGE_ELEMENT___RESOLVE__PACKAGEELEMENT:
				resolve((T)arguments.get(0));
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}
	
	@Override
	public String getName() {
		String name = super.getName();
		if (Util.isBlank(name)) {
			// TODO - derive name from key - kebab to label.
		}
		return name;
	}
	
	/**
	 * Caching opposites.
	 * @param <E>
	 * @param eReference
	 * @return
	 */
	protected <E extends EObject> EList<E> getOppositeReferrers(EReference reference) {
		return FeatureCache.get(this, Objects.requireNonNull(reference, "Reference is null"), (target, ref) -> super.getOppositeReferrers(ref), true);		
	}
	
	protected Object getCachedFeature(EStructuralFeature feature) {
		return FeatureCache.get(this, feature, (t,f) -> {
			if (t != PackageElementImpl.this) {
				throw new IllegalStateException("Invoking computer on a wrong target");
			}
			return computeCachedFeature(f);
		}, true);		
	}

	/**
	 * Implement computation in subclasses which call getCachedFeature(). 
	 * @param feature
	 * @return
	 */
	protected Object computeCachedFeature(EStructuralFeature feature) {
		throw new UnsupportedOperationException("Computation of feature " + feature + " is not implemented");
	}	
	
	// Helper methods to create and resolve package element proxies
	
	/**
	 * @param path
	 * @return URI relative to the containing flow Package or null if there is no containing package.
	 */
	protected URI getPackageFeatureURI(EStructuralFeature feature) {
		for (EObject ancestor = eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
			if (ancestor instanceof org.nasdanika.flow.Package) {
				URI uri = NcoreUtil.getUri(ancestor);
				if (feature != null) {
					uri = uri.appendSegment(feature.getName());					
				}
				return uri.appendSegment("");
			}
		}
		return null;
	}
	
	protected Artifact resolveArtifact(URI uri) {
		return (Artifact) resolve(FlowPackage.Literals.ARTIFACT, uri); 
	}
	
	protected <E> EList<E> resolvePackageElements(Collection<String> keys, EStructuralFeature feature, Function<URI,E> elementResolver) {
		URI referenceURI = getPackageFeatureURI(feature);
		return keys.stream()
			.map(URI::createURI)
			.map(eUri -> referenceURI == null ? eUri : eUri.resolve(referenceURI))
			.map(elementResolver)
			.collect(Collectors.toCollection(ECollections::newBasicEList));
	}
	
	protected EList<Artifact> resolveArtifacts(Collection<String> artifactKeys) {
		return resolvePackageElements(artifactKeys, FlowPackage.Literals.PACKAGE__ARTIFACTS, this::resolveArtifact);
	}
	
	protected Participant resolveParticipant(URI uri) {
		return (Participant) resolve(FlowPackage.Literals.PARTICIPANT, uri); 
	}
	
	protected EList<Participant> resolveParticipants(Collection<String> participantKeys) {
		return resolvePackageElements(participantKeys, FlowPackage.Literals.PACKAGE__PARTICIPANTS, this::resolveParticipant);
	}
	
	protected Resource resolveResource(URI uri) {
		return (Resource) resolve(FlowPackage.Literals.RESOURCE, uri); 
	}

	protected EList<Resource> resolveResources(Collection<String> resourceKeys) {
		return resolvePackageElements(resourceKeys, FlowPackage.Literals.PACKAGE__RESOURCES, this::resolveResource);
	}
	
	protected FlowElement<?> resolveFlowElement(URI uri) {
		return (FlowElement<?>) resolve(FlowPackage.Literals.FLOW_ELEMENT, uri); 
	}

	/**
	 * Resolves flow elements relative to the containing package. 
	 * Flow elements can be under activities or service providers - resources or participants.
	 * @param resourceKeys
	 * @return
	 */
	protected EList<FlowElement<?>> resolveFlowElements(Collection<String> elementKeys) {
		return resolvePackageElements(elementKeys, null, this::resolveFlowElement);
	}

} //PackageElementImpl
