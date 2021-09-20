/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.nasdanika.ncore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Package#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getSubPackages <em>Sub Packages</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getActivities <em>Activities</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getPackage()
 * @model
 * @generated
 */
public interface Package extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Extends</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Package}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Package#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Journey can  extend another journey and inherit its elements. Inherited elements can be overriden or suppressed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extends</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_Extends()
	 * @see org.nasdanika.flow.Package#getExtensions
	 * @model opposite="extensions"
	 * @generated
	 */
	EList<Package> getExtends();

	/**
	 * Returns the value of the '<em><b>Extensions</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Package}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Package#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Journeys extending this journey.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extensions</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_Extensions()
	 * @see org.nasdanika.flow.Package#getExtends
	 * @model opposite="extends" changeable="false" derived="true"
	 * @generated
	 */
	EList<Package> getExtensions();

	/**
	 * Returns the value of the '<em><b>Sub Packages</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Package},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Nested packages.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sub Packages</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_SubPackages()
	 * @model mapType="org.nasdanika.flow.PackageEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Package&gt;"
	 * @generated
	 */
	EMap<String, Package> getSubPackages();

	/**
	 * Returns the value of the '<em><b>Participants</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Participant},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flow participants.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Participants</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_Participants()
	 * @model mapType="org.nasdanika.flow.ParticipantEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Participant&gt;"
	 * @generated
	 */
	EMap<String, Participant> getParticipants();

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Resource},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flow resources.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resources</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_Resources()
	 * @model mapType="org.nasdanika.flow.ResourceEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Resource&gt;"
	 * @generated
	 */
	EMap<String, Resource> getResources();

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Artifact},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flow artifacts.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Artifacts</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_Artifacts()
	 * @model mapType="org.nasdanika.flow.ArtifactEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Artifact&gt;"
	 * @generated
	 */
	EMap<String, Artifact> getArtifacts();

	/**
	 * Returns the value of the '<em><b>Activities</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Activity},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Activities and flows.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activities</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_Activities()
	 * @model mapType="org.nasdanika.flow.ActivityEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Activity&gt;"
	 * @generated
	 */
	EMap<String, Activity> getActivities();

} // Package
