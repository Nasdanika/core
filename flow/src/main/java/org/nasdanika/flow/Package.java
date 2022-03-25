/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Package#getSuperPackages <em>Super Packages</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getSubPackages <em>Sub Packages</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getActivities <em>Activities</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getPackage()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/package.md' load-doc-reference='doc/package-load-doc.md'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='test'"
 * @generated
 */
public interface Package extends PackageElement<Package> {
	/**
	 * Returns the value of the '<em><b>Super Packages</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Package}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Package can extend other packages and inherit their elements. 
	 * This reference is required because ``extends`` reference is already defined in [PackageElement](PackageElement.html) as derived and immutable.
	 * For top-level packages ``extends`` is the same as this reference. For nested packages ``extends`` is a union of containment-derived extensions and this reference.
	 * 
	 * Package own elements override inherited elements with the same keys. 
	 * To suppress an inheriIted element define an element with the same key and ``null`` value.
	 * 
	 * Multiple inheritance allows to have "mix-in" packages. 
	 * For example, the primary lineage can follow the organizational hierarchy with base packages defining generic flows 
	 * and then specializing at the lower levels of the organization, say to specific tools. 
	 * Then there can be a technology tree, for example different cloud platforms. 
	 * A set of flows defining development processes for a particular organization and a particular cloud technology would be a mix of the two inheritance hierarchies.
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Super Packages</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_SuperPackages()
	 * @model annotation="urn:org.nasdanika feature-key='extends'"
	 * @generated
	 */
	EList<Package> getSuperPackages();

	/**
	 * Returns the value of the '<em><b>Sub Packages</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Package},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Packages contained in this package.
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
	 * and the value is of type {@link org.nasdanika.flow.Activity<?>},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Activities and flows.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activities</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_Activities()
	 * @model mapType="org.nasdanika.flow.ActivityEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Activity&lt;?&gt;&gt;"
	 * @generated
	 */
	EMap<String, Activity<?>> getActivities();

} // Package
