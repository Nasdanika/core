/**
 */
package org.nasdanika.graph.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.common.Adaptable;
import org.nasdanika.graph.model.Connection;
import org.nasdanika.graph.model.DocumentedNamedCompositeNode;
import org.nasdanika.graph.model.DocumentedNamedConnectionSource;
import org.nasdanika.graph.model.DocumentedNamedConnectionTarget;
import org.nasdanika.graph.model.DocumentedNamedGraphElement;
import org.nasdanika.graph.model.DocumentedNamedNode;
import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelPackage;
import org.nasdanika.ncore.Documented;
import org.nasdanika.ncore.DocumentedNamedElement;
import org.nasdanika.ncore.DocumentedNamedStringIdentity;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Property;
import org.nasdanika.persistence.Marked;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Documented Named Composite Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl#getMarkers <em>Markers</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl#getUris <em>Uris</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl#getLabelPrototype <em>Label Prototype</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl#getRepresentations <em>Representations</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl#getContextHelp <em>Context Help</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DocumentedNamedCompositeNodeImpl<E extends GraphElement, C extends Connection<?>> extends CompositeNodeImpl<E, C> implements DocumentedNamedCompositeNode<E, C> {
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
	protected DocumentedNamedCompositeNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DOCUMENTED_NAMED_COMPOSITE_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Marker> getMarkers() {
		return (EList<Marker>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__MARKERS, NcorePackage.Literals.MARKED__MARKERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getUris() {
		return (EList<String>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__URIS, NcorePackage.Literals.MODEL_ELEMENT__URIS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return (String)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DESCRIPTION, NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		eDynamicSet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DESCRIPTION, NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUuid() {
		return (String)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__UUID, NcorePackage.Literals.MODEL_ELEMENT__UUID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUuid(String newUuid) {
		eDynamicSet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__UUID, NcorePackage.Literals.MODEL_ELEMENT__UUID, newUuid);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject getLabelPrototype() {
		return (EObject)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__LABEL_PROTOTYPE, NcorePackage.Literals.MODEL_ELEMENT__LABEL_PROTOTYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabelPrototype(EObject newLabelPrototype, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newLabelPrototype, ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__LABEL_PROTOTYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLabelPrototype(EObject newLabelPrototype) {
		eDynamicSet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__LABEL_PROTOTYPE, NcorePackage.Literals.MODEL_ELEMENT__LABEL_PROTOTYPE, newLabelPrototype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, String> getRepresentations() {
		return (EMap<String, String>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__REPRESENTATIONS, NcorePackage.Literals.MODEL_ELEMENT__REPRESENTATIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Property> getAnnotations() {
		return (EList<Property>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__ANNOTATIONS, NcorePackage.Literals.MODEL_ELEMENT__ANNOTATIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__NAME, NcorePackage.Literals.NAMED_ELEMENT__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__NAME, NcorePackage.Literals.NAMED_ELEMENT__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getDocumentation() {
		return (EList<EObject>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DOCUMENTATION, NcorePackage.Literals.DOCUMENTED__DOCUMENTATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getContextHelp() {
		return (EList<EObject>)eDynamicGet(ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__CONTEXT_HELP, NcorePackage.Literals.DOCUMENTED__CONTEXT_HELP, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__MARKERS:
				return ((InternalEList<?>)getMarkers()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__LABEL_PROTOTYPE:
				return basicSetLabelPrototype(null, msgs);
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__REPRESENTATIONS:
				return ((InternalEList<?>)getRepresentations()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DOCUMENTATION:
				return ((InternalEList<?>)getDocumentation()).basicRemove(otherEnd, msgs);
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__CONTEXT_HELP:
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
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__MARKERS:
				return getMarkers();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__URIS:
				return getUris();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DESCRIPTION:
				return getDescription();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__UUID:
				return getUuid();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__LABEL_PROTOTYPE:
				return getLabelPrototype();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__REPRESENTATIONS:
				if (coreType) return getRepresentations();
				else return getRepresentations().map();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__ANNOTATIONS:
				return getAnnotations();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__NAME:
				return getName();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DOCUMENTATION:
				return getDocumentation();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__CONTEXT_HELP:
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
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__MARKERS:
				getMarkers().clear();
				getMarkers().addAll((Collection<? extends Marker>)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__URIS:
				getUris().clear();
				getUris().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__UUID:
				setUuid((String)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__LABEL_PROTOTYPE:
				setLabelPrototype((EObject)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__REPRESENTATIONS:
				((EStructuralFeature.Setting)getRepresentations()).set(newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__ANNOTATIONS:
				getAnnotations().clear();
				getAnnotations().addAll((Collection<? extends Property>)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DOCUMENTATION:
				getDocumentation().clear();
				getDocumentation().addAll((Collection<? extends EObject>)newValue);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__CONTEXT_HELP:
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
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__MARKERS:
				getMarkers().clear();
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__URIS:
				getUris().clear();
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__LABEL_PROTOTYPE:
				setLabelPrototype((EObject)null);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__REPRESENTATIONS:
				getRepresentations().clear();
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__ANNOTATIONS:
				getAnnotations().clear();
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DOCUMENTATION:
				getDocumentation().clear();
				return;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__CONTEXT_HELP:
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
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__MARKERS:
				return !getMarkers().isEmpty();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__URIS:
				return !getUris().isEmpty();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__UUID:
				return UUID_EDEFAULT == null ? getUuid() != null : !UUID_EDEFAULT.equals(getUuid());
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__LABEL_PROTOTYPE:
				return getLabelPrototype() != null;
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__REPRESENTATIONS:
				return !getRepresentations().isEmpty();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__ANNOTATIONS:
				return !getAnnotations().isEmpty();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DOCUMENTATION:
				return !getDocumentation().isEmpty();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__CONTEXT_HELP:
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
				case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__MARKERS: return NcorePackage.MARKED__MARKERS;
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
				case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__URIS: return NcorePackage.MODEL_ELEMENT__URIS;
				case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DESCRIPTION: return NcorePackage.MODEL_ELEMENT__DESCRIPTION;
				case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__UUID: return NcorePackage.MODEL_ELEMENT__UUID;
				case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__LABEL_PROTOTYPE: return NcorePackage.MODEL_ELEMENT__LABEL_PROTOTYPE;
				case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__REPRESENTATIONS: return NcorePackage.MODEL_ELEMENT__REPRESENTATIONS;
				case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__ANNOTATIONS: return NcorePackage.MODEL_ELEMENT__ANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__NAME: return NcorePackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == Documented.class) {
			switch (derivedFeatureID) {
				case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DOCUMENTATION: return NcorePackage.DOCUMENTED__DOCUMENTATION;
				case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__CONTEXT_HELP: return NcorePackage.DOCUMENTED__CONTEXT_HELP;
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedStringIdentity.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedGraphElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedConnectionSource.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedConnectionTarget.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedNode.class) {
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
				case NcorePackage.MARKED__MARKERS: return ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__MARKERS;
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
				case NcorePackage.MODEL_ELEMENT__URIS: return ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__URIS;
				case NcorePackage.MODEL_ELEMENT__DESCRIPTION: return ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DESCRIPTION;
				case NcorePackage.MODEL_ELEMENT__UUID: return ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__UUID;
				case NcorePackage.MODEL_ELEMENT__LABEL_PROTOTYPE: return ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__LABEL_PROTOTYPE;
				case NcorePackage.MODEL_ELEMENT__REPRESENTATIONS: return ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__REPRESENTATIONS;
				case NcorePackage.MODEL_ELEMENT__ANNOTATIONS: return ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__ANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case NcorePackage.NAMED_ELEMENT__NAME: return ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__NAME;
				default: return -1;
			}
		}
		if (baseClass == Documented.class) {
			switch (baseFeatureID) {
				case NcorePackage.DOCUMENTED__DOCUMENTATION: return ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__DOCUMENTATION;
				case NcorePackage.DOCUMENTED__CONTEXT_HELP: return ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE__CONTEXT_HELP;
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedStringIdentity.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedGraphElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedConnectionSource.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedConnectionTarget.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DocumentedNamedNode.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //DocumentedNamedCompositeNodeImpl
