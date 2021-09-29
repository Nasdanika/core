/**
 */
package org.nasdanika.flow.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.Call;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getCalls <em>Calls</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getInputArtifactKeys <em>Input Artifact Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getInputArtifacts <em>Input Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getOutputArtifacts <em>Output Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getOutputArtifactKeys <em>Output Artifact Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getParticipantsKeys <em>Participants Keys</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowElementImpl#getResourcesKeys <em>Resources Keys</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FlowElementImpl<T extends FlowElement<T>> extends PackageElementImpl<T> implements FlowElement<T> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlowElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.FLOW_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setPrototype(T newPrototype) {
		super.setPrototype(newPrototype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Transition> getOutputs() {
		return (EMap<String, Transition>)eDynamicGet(FlowPackage.FLOW_ELEMENT__OUTPUTS, FlowPackage.Literals.FLOW_ELEMENT__OUTPUTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, Call> getCalls() {
		return (EMap<String, Call>)eDynamicGet(FlowPackage.FLOW_ELEMENT__CALLS, FlowPackage.Literals.FLOW_ELEMENT__CALLS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getInputArtifactKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS, FlowPackage.Literals.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Artifact> getInputArtifacts() {
		EList<Artifact> ret = ECollections.newBasicEList();
		org.eclipse.emf.ecore.resource.Resource res = eResource();
		if (res != null) {
			ResourceSet resourceSet = res.getResourceSet();
			if (resourceSet != null) {
				for (EObject ancestor = eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
					if (ancestor instanceof org.nasdanika.flow.Package) {
						URI artifactsURI = URI.createURI(((org.nasdanika.flow.Package) ancestor).getUri() + "/artifacts/");
						for (String key: getInputArtifactKeys()) {
							URI aURI = URI.createURI(key).resolve(artifactsURI);
							EObject target = resourceSet.getEObject(aURI, false);
							if (target == null) {
								throw new ConfigurationException("Invalid artifact reference: " + key + " (" + aURI + ")", EObjectAdaptable.adaptTo(this, Marked.class));
							}
							
							if (target instanceof Artifact) {
								ret.add((Artifact) target);
							} else {
								throw new ConfigurationException("Expected artifact at: " + key + " (" + aURI + "), got " + target, EObjectAdaptable.adaptTo(this, Marked.class));
							}
						}
						break;
					}
				}
			}
		}
		return ret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Artifact> getOutputArtifacts() {
		EList<Artifact> ret = ECollections.newBasicEList();
		org.eclipse.emf.ecore.resource.Resource res = eResource();
		if (res != null) {
			ResourceSet resourceSet = res.getResourceSet();
			if (resourceSet != null) {
				for (EObject ancestor = eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
					if (ancestor instanceof org.nasdanika.flow.Package) {
						URI artifactsURI = URI.createURI(((org.nasdanika.flow.Package) ancestor).getUri() + "/artifacts/");
						for (String key: getOutputArtifactKeys()) {
							URI aURI = URI.createURI(key).resolve(artifactsURI);
							EObject target = resourceSet.getEObject(aURI, false);
							if (target == null) {
								throw new ConfigurationException("Invalid artifact reference: " + key + " (" + aURI + ")", EObjectAdaptable.adaptTo(this, Marked.class));
							}
							
							if (target instanceof Artifact) {
								ret.add((Artifact) target);
							} else {
								throw new ConfigurationException("Expected artifact at: " + key + " (" + aURI + "), got " + target, EObjectAdaptable.adaptTo(this, Marked.class));
							}
						}
						break;
					}
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
	public EList<String> getOutputArtifactKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS, FlowPackage.Literals.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Participant> getParticipants() {
		EList<Participant> ret = ECollections.newBasicEList();
		org.eclipse.emf.ecore.resource.Resource res = eResource();
		if (res != null) {
			ResourceSet resourceSet = res.getResourceSet();
			if (resourceSet != null) {
				for (EObject ancestor = eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
					if (ancestor instanceof org.nasdanika.flow.Package) {
						URI participantsURI = URI.createURI(((org.nasdanika.flow.Package) ancestor).getUri() + "/participants/");
						for (String key: getParticipantsKeys()) {
							URI pURI = URI.createURI(key).resolve(participantsURI);
							EObject target = resourceSet.getEObject(pURI, false);
							if (target == null) {
								throw new ConfigurationException("Invalid participant reference: " + key + " (" + pURI + ")", EObjectAdaptable.adaptTo(this, Marked.class));
							}
							
							if (target instanceof Participant) {
								ret.add((Participant) target);
							} else {
								throw new ConfigurationException("Expected participant at: " + key + " (" + pURI + "), got " + target, EObjectAdaptable.adaptTo(this, Marked.class));
							}
						}
						break;
					}
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
	public EList<String> getParticipantsKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.FLOW_ELEMENT__PARTICIPANTS_KEYS, FlowPackage.Literals.FLOW_ELEMENT__PARTICIPANTS_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Resource> getResources() {
		EList<Resource> ret = ECollections.newBasicEList();
		org.eclipse.emf.ecore.resource.Resource res = eResource();
		if (res != null) {
			ResourceSet resourceSet = res.getResourceSet();
			if (resourceSet != null) {
				for (EObject ancestor = eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
					if (ancestor instanceof org.nasdanika.flow.Package) {
						URI resourcesURI = URI.createURI(((org.nasdanika.flow.Package) ancestor).getUri() + "/resources/");
						for (String key: getResourcesKeys()) {
							URI rURI = URI.createURI(key).resolve(resourcesURI);
							EObject target = resourceSet.getEObject(rURI, false);
							if (target == null) {
								throw new ConfigurationException("Invalid resource reference: " + key + " (" + rURI + ")", EObjectAdaptable.adaptTo(this, Marked.class));
							}
							
							if (target instanceof Resource) {
								ret.add((Resource) target);
							} else {
								throw new ConfigurationException("Expected resource at: " + key + " (" + rURI + "), got " + target, EObjectAdaptable.adaptTo(this, Marked.class));
							}
						}
						break;
					}
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
	public EList<String> getResourcesKeys() {
		return (EList<String>)eDynamicGet(FlowPackage.FLOW_ELEMENT__RESOURCES_KEYS, FlowPackage.Literals.FLOW_ELEMENT__RESOURCES_KEYS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.FLOW_ELEMENT__OUTPUTS:
				return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
			case FlowPackage.FLOW_ELEMENT__CALLS:
				return ((InternalEList<?>)getCalls()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.FLOW_ELEMENT__OUTPUTS:
				if (coreType) return getOutputs();
				else return getOutputs().map();
			case FlowPackage.FLOW_ELEMENT__CALLS:
				if (coreType) return getCalls();
				else return getCalls().map();
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS:
				return getInputArtifactKeys();
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACTS:
				return getInputArtifacts();
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACTS:
				return getOutputArtifacts();
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS:
				return getOutputArtifactKeys();
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS:
				return getParticipants();
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS_KEYS:
				return getParticipantsKeys();
			case FlowPackage.FLOW_ELEMENT__RESOURCES:
				return getResources();
			case FlowPackage.FLOW_ELEMENT__RESOURCES_KEYS:
				return getResourcesKeys();
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
			case FlowPackage.FLOW_ELEMENT__OUTPUTS:
				((EStructuralFeature.Setting)getOutputs()).set(newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__CALLS:
				((EStructuralFeature.Setting)getCalls()).set(newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS:
				getInputArtifactKeys().clear();
				getInputArtifactKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS:
				getOutputArtifactKeys().clear();
				getOutputArtifactKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS_KEYS:
				getParticipantsKeys().clear();
				getParticipantsKeys().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.FLOW_ELEMENT__RESOURCES_KEYS:
				getResourcesKeys().clear();
				getResourcesKeys().addAll((Collection<? extends String>)newValue);
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
			case FlowPackage.FLOW_ELEMENT__OUTPUTS:
				getOutputs().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__CALLS:
				getCalls().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS:
				getInputArtifactKeys().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS:
				getOutputArtifactKeys().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS_KEYS:
				getParticipantsKeys().clear();
				return;
			case FlowPackage.FLOW_ELEMENT__RESOURCES_KEYS:
				getResourcesKeys().clear();
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
			case FlowPackage.FLOW_ELEMENT__OUTPUTS:
				return !getOutputs().isEmpty();
			case FlowPackage.FLOW_ELEMENT__CALLS:
				return !getCalls().isEmpty();
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACT_KEYS:
				return !getInputArtifactKeys().isEmpty();
			case FlowPackage.FLOW_ELEMENT__INPUT_ARTIFACTS:
				return !getInputArtifacts().isEmpty();
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACTS:
				return !getOutputArtifacts().isEmpty();
			case FlowPackage.FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS:
				return !getOutputArtifactKeys().isEmpty();
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS:
				return !getParticipants().isEmpty();
			case FlowPackage.FLOW_ELEMENT__PARTICIPANTS_KEYS:
				return !getParticipantsKeys().isEmpty();
			case FlowPackage.FLOW_ELEMENT__RESOURCES:
				return !getResources().isEmpty();
			case FlowPackage.FLOW_ELEMENT__RESOURCES_KEYS:
				return !getResourcesKeys().isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@Override
	public void apply(T instance) {
		super.apply(instance);
		
		// Outputs
		for (Entry<String, Transition> outputEntry: getOutputs().entrySet()) {
			Transition output = outputEntry.getValue();
			EMap<String, Transition> instanceOutputs = instance.getOutputs();
			String outputKey = outputEntry.getKey();
			if (output == null) {
				instanceOutputs.removeKey(outputKey);
			} else {
				Transition instanceOutput = output.create();
				instanceOutputs.put(outputKey, instanceOutput);
				output.apply(instanceOutput);
			}
		}
		
		// Calls
		for (Entry<String, Call> callEntry: getCalls().entrySet()) {
			Call call = callEntry.getValue();
			EMap<String, Call> instanceCalls = instance.getCalls();
			String callKey = callEntry.getKey();
			if (call == null) {
				instanceCalls.removeKey(callKey);
			} else {
				Call instanceCall = (Call) call.create();
				instanceCalls.put(callKey, instanceCall);
				call.apply(instanceCall);
			}
		}
	}
	
	/**
	 * Flow containment
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<T> getExtends() {
		EList<T> ret = ECollections.newBasicEList();
		if (eContainmentFeature() == FlowPackage.Literals.FLOW_ELEMENT_ENTRY__VALUE) {
			String key = ((Map.Entry<String, ?>) eContainer()).getKey();
			Flow container = (Flow) eContainer().eContainer();
			for (Flow cExtends: container.getExtends()) {
				FlowElement<?> ext = cExtends.getElements().get(key);
				if (ext != null) {
					ret.add((T) ext);
				}
			}
		}
		
		return ret;
	}	
	

} //FlowElementImpl
