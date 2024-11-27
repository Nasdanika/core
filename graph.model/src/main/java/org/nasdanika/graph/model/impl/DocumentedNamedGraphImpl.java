/**
 */
package org.nasdanika.graph.model.impl;

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

import org.nasdanika.common.Adaptable;

import org.nasdanika.graph.model.DocumentedNamedGraph;
import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelPackage;

import org.nasdanika.ncore.Documented;
import org.nasdanika.ncore.DocumentedNamedElement;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Property;

import org.nasdanika.persistence.Marked;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Documented Named Graph</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl#getMarkers <em>Markers</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl#getUris <em>Uris</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl#getLabelPrototype <em>Label Prototype</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl#getRepresentations <em>Representations</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl#getContextHelp <em>Context Help</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DocumentedNamedGraphImpl<E extends GraphElement> extends GraphImpl<E> implements DocumentedNamedGraph<E> {
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
	 * @generated
	 */
	protected DocumentedNamedGraphImpl() {
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
		return ModelPackage.Literals.DOCUMENTED_NAMED_GRAPH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Marker> getMarkers() {
		return (EList<Marker>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_GRAPH__MARKERS, NcorePackage.Literals.MARKED__MARKERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getUris() {
		return (EList<String>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_GRAPH__URIS, NcorePackage.Literals.MODEL_ELEMENT__URIS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return (String)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_GRAPH__DESCRIPTION, NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		eDynamicSet(ModelPackage.DOCUMENTED_NAMED_GRAPH__DESCRIPTION, NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUuid() {
		return (String)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_GRAPH__UUID, NcorePackage.Literals.MODEL_ELEMENT__UUID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUuid(String newUuid) {
		eDynamicSet(ModelPackage.DOCUMENTED_NAMED_GRAPH__UUID, NcorePackage.Literals.MODEL_ELEMENT__UUID, newUuid);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject getLabelPrototype() {
		return (EObject)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_GRAPH__LABEL_PROTOTYPE, NcorePackage.Literals.MODEL_ELEMENT__LABEL_PROTOTYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabelPrototype(EObject newLabelPrototype, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newLabelPrototype, ModelPackage.DOCUMENTED_NAMED_GRAPH__LABEL_PROTOTYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLabelPrototype(EObject newLabelPrototype) {
		eDynamicSet(ModelPackage.DOCUMENTED_NAMED_GRAPH__LABEL_PROTOTYPE, NcorePackage.Literals.MODEL_ELEMENT__LABEL_PROTOTYPE, newLabelPrototype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, String> getRepresentations() {
		return (EMap<String, String>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_GRAPH__REPRESENTATIONS, NcorePackage.Literals.MODEL_ELEMENT__REPRESENTATIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Property> getAnnotations() {
		return (EList<Property>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_GRAPH__ANNOTATIONS, NcorePackage.Literals.MODEL_ELEMENT__ANNOTATIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_GRAPH__NAME, NcorePackage.Literals.NAMED_ELEMENT__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(ModelPackage.DOCUMENTED_NAMED_GRAPH__NAME, NcorePackage.Literals.NAMED_ELEMENT__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getDocumentation() {
		return (EList<EObject>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_GRAPH__DOCUMENTATION, NcorePackage.Literals.DOCUMENTED__DOCUMENTATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getContextHelp() {
		return (EList<EObject>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_GRAPH__CONTEXT_HELP, NcorePackage.Literals.DOCUMENTED__CONTEXT_HELP, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__MARKERS:
				return ((InternalEList<?>)getMarkers()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__LABEL_PROTOTYPE:
				return basicSetLabelPrototype(null, msgs);
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__REPRESENTATIONS:
				return ((InternalEList<?>)getRepresentations()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__DOCUMENTATION:
				return ((InternalEList<?>)getDocumentation()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__CONTEXT_HELP:
				return ((InternalEList<?>)getContextHelp()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__MARKERS:
				return getMarkers();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__URIS:
				return getUris();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__DESCRIPTION:
				return getDescription();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__UUID:
				return getUuid();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__LABEL_PROTOTYPE:
				return getLabelPrototype();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__REPRESENTATIONS:
				if (coreType) return getRepresentations();
				else return getRepresentations().map();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__ANNOTATIONS:
				return getAnnotations();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__NAME:
				return getName();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__DOCUMENTATION:
				return getDocumentation();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__CONTEXT_HELP:
				return getContextHelp();
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
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__MARKERS:
				getMarkers().clear();
				getMarkers().addAll((Collection<? extends Marker>)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__URIS:
				getUris().clear();
				getUris().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__UUID:
				setUuid((String)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__LABEL_PROTOTYPE:
				setLabelPrototype((EObject)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__REPRESENTATIONS:
				((EStructuralFeature.Setting)getRepresentations()).set(newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__ANNOTATIONS:
				getAnnotations().clear();
				getAnnotations().addAll((Collection<? extends Property>)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__DOCUMENTATION:
				getDocumentation().clear();
				getDocumentation().addAll((Collection<? extends EObject>)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__CONTEXT_HELP:
				getContextHelp().clear();
				getContextHelp().addAll((Collection<? extends EObject>)newValue);
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
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__MARKERS:
				getMarkers().clear();
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__URIS:
				getUris().clear();
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__LABEL_PROTOTYPE:
				setLabelPrototype((EObject)null);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__REPRESENTATIONS:
				getRepresentations().clear();
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__ANNOTATIONS:
				getAnnotations().clear();
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__DOCUMENTATION:
				getDocumentation().clear();
				return;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__CONTEXT_HELP:
				getContextHelp().clear();
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
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__MARKERS:
				return !getMarkers().isEmpty();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__URIS:
				return !getUris().isEmpty();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__UUID:
				return UUID_EDEFAULT == null ? getUuid() != null : !UUID_EDEFAULT.equals(getUuid());
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__LABEL_PROTOTYPE:
				return getLabelPrototype() != null;
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__REPRESENTATIONS:
				return !getRepresentations().isEmpty();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__ANNOTATIONS:
				return !getAnnotations().isEmpty();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__DOCUMENTATION:
				return !getDocumentation().isEmpty();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH__CONTEXT_HELP:
				return !getContextHelp().isEmpty();
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
		if (baseClass == Marked.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == org.nasdanika.ncore.Marked.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOCUMENTED_NAMED_GRAPH__MARKERS: return NcorePackage.MARKED__MARKERS;
				default: return -1;
			}
		}
		if (baseClass == Adaptable.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ModelElement.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOCUMENTED_NAMED_GRAPH__URIS: return NcorePackage.MODEL_ELEMENT__URIS;
				case ModelPackage.DOCUMENTED_NAMED_GRAPH__DESCRIPTION: return NcorePackage.MODEL_ELEMENT__DESCRIPTION;
				case ModelPackage.DOCUMENTED_NAMED_GRAPH__UUID: return NcorePackage.MODEL_ELEMENT__UUID;
				case ModelPackage.DOCUMENTED_NAMED_GRAPH__LABEL_PROTOTYPE: return NcorePackage.MODEL_ELEMENT__LABEL_PROTOTYPE;
				case ModelPackage.DOCUMENTED_NAMED_GRAPH__REPRESENTATIONS: return NcorePackage.MODEL_ELEMENT__REPRESENTATIONS;
				case ModelPackage.DOCUMENTED_NAMED_GRAPH__ANNOTATIONS: return NcorePackage.MODEL_ELEMENT__ANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOCUMENTED_NAMED_GRAPH__NAME: return NcorePackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == Documented.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOCUMENTED_NAMED_GRAPH__DOCUMENTATION: return NcorePackage.DOCUMENTED__DOCUMENTATION;
				case ModelPackage.DOCUMENTED_NAMED_GRAPH__CONTEXT_HELP: return NcorePackage.DOCUMENTED__CONTEXT_HELP;
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedElement.class) {
			switch (derivedFeatureID) {
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
		if (baseClass == Marked.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == org.nasdanika.ncore.Marked.class) {
			switch (baseFeatureID) {
				case NcorePackage.MARKED__MARKERS: return ModelPackage.DOCUMENTED_NAMED_GRAPH__MARKERS;
				default: return -1;
			}
		}
		if (baseClass == Adaptable.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ModelElement.class) {
			switch (baseFeatureID) {
				case NcorePackage.MODEL_ELEMENT__URIS: return ModelPackage.DOCUMENTED_NAMED_GRAPH__URIS;
				case NcorePackage.MODEL_ELEMENT__DESCRIPTION: return ModelPackage.DOCUMENTED_NAMED_GRAPH__DESCRIPTION;
				case NcorePackage.MODEL_ELEMENT__UUID: return ModelPackage.DOCUMENTED_NAMED_GRAPH__UUID;
				case NcorePackage.MODEL_ELEMENT__LABEL_PROTOTYPE: return ModelPackage.DOCUMENTED_NAMED_GRAPH__LABEL_PROTOTYPE;
				case NcorePackage.MODEL_ELEMENT__REPRESENTATIONS: return ModelPackage.DOCUMENTED_NAMED_GRAPH__REPRESENTATIONS;
				case NcorePackage.MODEL_ELEMENT__ANNOTATIONS: return ModelPackage.DOCUMENTED_NAMED_GRAPH__ANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case NcorePackage.NAMED_ELEMENT__NAME: return ModelPackage.DOCUMENTED_NAMED_GRAPH__NAME;
				default: return -1;
			}
		}
		if (baseClass == Documented.class) {
			switch (baseFeatureID) {
				case NcorePackage.DOCUMENTED__DOCUMENTATION: return ModelPackage.DOCUMENTED_NAMED_GRAPH__DOCUMENTATION;
				case NcorePackage.DOCUMENTED__CONTEXT_HELP: return ModelPackage.DOCUMENTED_NAMED_GRAPH__CONTEXT_HELP;
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //DocumentedNamedGraphImpl
