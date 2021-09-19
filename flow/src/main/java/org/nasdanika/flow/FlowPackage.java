/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.nasdanika.flow.FlowFactory
 * @model kind="package"
 *        annotation="urn:org.nasdanika documentation-reference='doc/package-summary.md'"
 * @generated
 */
public interface FlowPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "flow";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.flow";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.flow";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FlowPackage eINSTANCE = org.nasdanika.flow.impl.FlowPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.PackageImpl <em>Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.PackageImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPackage()
	 * @generated
	 */
	int PACKAGE = 0;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__MARKER = NcorePackage.NAMED_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__URI = NcorePackage.NAMED_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__DESCRIPTION = NcorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__UUID = NcorePackage.NAMED_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__NAME = NcorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__EXTENDS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__EXTENSIONS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sub Packages</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__SUB_PACKAGES = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FEATURE_COUNT = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_OPERATION_COUNT = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.PackageEntryImpl <em>Package Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.PackageEntryImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPackageEntry()
	 * @generated
	 */
	int PACKAGE_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Package Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Package Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ParticipantImpl <em>Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ParticipantImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getParticipant()
	 * @generated
	 */
	int PARTICIPANT = 2;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__MARKER = NcorePackage.NAMED_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__URI = NcorePackage.NAMED_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__DESCRIPTION = NcorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__UUID = NcorePackage.NAMED_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__NAME = NcorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__EXTENDS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__EXTENSIONS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__SERVICES = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_FEATURE_COUNT = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_OPERATION_COUNT = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ResourceImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 3;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__MARKER = NcorePackage.NAMED_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__URI = NcorePackage.NAMED_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__DESCRIPTION = NcorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__UUID = NcorePackage.NAMED_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__NAME = NcorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__SERVICES = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPERATION_COUNT = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ArtifactImpl <em>Artifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ArtifactImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getArtifact()
	 * @generated
	 */
	int ARTIFACT = 4;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__MARKER = NcorePackage.NAMED_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__URI = NcorePackage.NAMED_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__DESCRIPTION = NcorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__UUID = NcorePackage.NAMED_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NAME = NcorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Repositories</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__REPOSITORIES = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_OPERATION_COUNT = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.FlowElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.FlowElementImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFlowElement()
	 * @generated
	 */
	int FLOW_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__OUTPUTS = 0;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__CALLS = 1;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__INPUT_ARTIFACTS = 2;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__DELIVERABLES = 3;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__PARTICIPANTS = 4;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__OVERRIDES = 5;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__OVERRIDEN_BY = 6;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__MODIFIERS = 7;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__RESOURCES = 8;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT_FEATURE_COUNT = 9;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT___GET_INPUTS__ELIST = 0;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT___GET_INVOCATIONS__ELIST = 1;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT___GET_ALL_INPUTS__ELIST = 2;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT___GET_ALL_INVOCATIONS__ELIST = 3;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT___GET_ALL_OUTPUTS__ELIST = 4;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT___GET_ALL_CALLS__ELIST = 5;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT___OVERRIDES__FLOWELEMENT = 6;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT_OPERATION_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ActivityImpl <em>Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ActivityImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getActivity()
	 * @generated
	 */
	int ACTIVITY = 6;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__OUTPUTS = FLOW_ELEMENT__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__CALLS = FLOW_ELEMENT__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__INPUT_ARTIFACTS = FLOW_ELEMENT__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__DELIVERABLES = FLOW_ELEMENT__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PARTICIPANTS = FLOW_ELEMENT__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__OVERRIDES = FLOW_ELEMENT__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__OVERRIDEN_BY = FLOW_ELEMENT__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__MODIFIERS = FLOW_ELEMENT__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__RESOURCES = FLOW_ELEMENT__RESOURCES;

	/**
	 * The feature id for the '<em><b>Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__SERVICES = FLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY___GET_INPUTS__ELIST = FLOW_ELEMENT___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY___GET_INVOCATIONS__ELIST = FLOW_ELEMENT___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY___GET_ALL_INPUTS__ELIST = FLOW_ELEMENT___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY___GET_ALL_INVOCATIONS__ELIST = FLOW_ELEMENT___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY___GET_ALL_OUTPUTS__ELIST = FLOW_ELEMENT___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY___GET_ALL_CALLS__ELIST = FLOW_ELEMENT___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY___OVERRIDES__FLOWELEMENT = FLOW_ELEMENT___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ServiceImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 7;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__OUTPUTS = FLOW_ELEMENT__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__CALLS = FLOW_ELEMENT__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__INPUT_ARTIFACTS = FLOW_ELEMENT__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__DELIVERABLES = FLOW_ELEMENT__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__PARTICIPANTS = FLOW_ELEMENT__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__OVERRIDES = FLOW_ELEMENT__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__OVERRIDEN_BY = FLOW_ELEMENT__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__MODIFIERS = FLOW_ELEMENT__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__RESOURCES = FLOW_ELEMENT__RESOURCES;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__TARGET = FLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE___GET_INPUTS__ELIST = FLOW_ELEMENT___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE___GET_INVOCATIONS__ELIST = FLOW_ELEMENT___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE___GET_ALL_INPUTS__ELIST = FLOW_ELEMENT___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE___GET_ALL_INVOCATIONS__ELIST = FLOW_ELEMENT___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE___GET_ALL_OUTPUTS__ELIST = FLOW_ELEMENT___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE___GET_ALL_CALLS__ELIST = FLOW_ELEMENT___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE___OVERRIDES__FLOWELEMENT = FLOW_ELEMENT___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.TransitionImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 8;

	/**
	 * The feature id for the '<em><b>Payload</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__PAYLOAD = 0;

	/**
	 * The feature id for the '<em><b>Suppress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__SUPPRESS = 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TARGET = 2;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = 3;

	/**
	 * The operation id for the '<em>Get Target</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION___GET_TARGET__ELIST = 0;

	/**
	 * The number of operations of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.CallImpl <em>Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.CallImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getCall()
	 * @generated
	 */
	int CALL = 9;

	/**
	 * The feature id for the '<em><b>Payload</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__PAYLOAD = TRANSITION__PAYLOAD;

	/**
	 * The feature id for the '<em><b>Suppress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__SUPPRESS = TRANSITION__SUPPRESS;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__TARGET = TRANSITION__TARGET;

	/**
	 * The feature id for the '<em><b>Response</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__RESPONSE = TRANSITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_FEATURE_COUNT = TRANSITION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Target</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL___GET_TARGET__ELIST = TRANSITION___GET_TARGET__ELIST;

	/**
	 * The number of operations of the '<em>Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_COUNT = TRANSITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.FlowImpl <em>Flow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.FlowImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFlow()
	 * @generated
	 */
	int FLOW = 10;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__OUTPUTS = ACTIVITY__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__CALLS = ACTIVITY__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__INPUT_ARTIFACTS = ACTIVITY__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__DELIVERABLES = ACTIVITY__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__PARTICIPANTS = ACTIVITY__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__OVERRIDES = ACTIVITY__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__OVERRIDEN_BY = ACTIVITY__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__MODIFIERS = ACTIVITY__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__RESOURCES = ACTIVITY__RESOURCES;

	/**
	 * The feature id for the '<em><b>Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__SERVICES = ACTIVITY__SERVICES;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__ELEMENTS = ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__EXTENDS = ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__EXTENSIONS = ACTIVITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__ROOT = ACTIVITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>All Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__ALL_ELEMENTS = ACTIVITY_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 5;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW___GET_INPUTS__ELIST = ACTIVITY___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW___GET_INVOCATIONS__ELIST = ACTIVITY___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW___GET_ALL_INPUTS__ELIST = ACTIVITY___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW___GET_ALL_INVOCATIONS__ELIST = ACTIVITY___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW___GET_ALL_OUTPUTS__ELIST = ACTIVITY___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW___GET_ALL_CALLS__ELIST = ACTIVITY___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW___OVERRIDES__FLOWELEMENT = ACTIVITY___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_OPERATION_COUNT = ACTIVITY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.PseudoStateImpl <em>Pseudo State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.PseudoStateImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPseudoState()
	 * @generated
	 */
	int PSEUDO_STATE = 11;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__OUTPUTS = FLOW_ELEMENT__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__CALLS = FLOW_ELEMENT__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__INPUT_ARTIFACTS = FLOW_ELEMENT__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__DELIVERABLES = FLOW_ELEMENT__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__PARTICIPANTS = FLOW_ELEMENT__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__OVERRIDES = FLOW_ELEMENT__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__OVERRIDEN_BY = FLOW_ELEMENT__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__MODIFIERS = FLOW_ELEMENT__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__RESOURCES = FLOW_ELEMENT__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__TYPE = FLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Pseudo State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE___GET_INPUTS__ELIST = FLOW_ELEMENT___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE___GET_INVOCATIONS__ELIST = FLOW_ELEMENT___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE___GET_ALL_INPUTS__ELIST = FLOW_ELEMENT___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST = FLOW_ELEMENT___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST = FLOW_ELEMENT___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE___GET_ALL_CALLS__ELIST = FLOW_ELEMENT___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE___OVERRIDES__FLOWELEMENT = FLOW_ELEMENT___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Pseudo State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ChoiceImpl <em>Choice</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ChoiceImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getChoice()
	 * @generated
	 */
	int CHOICE = 12;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__DELIVERABLES = PSEUDO_STATE__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__OVERRIDES = PSEUDO_STATE__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__OVERRIDEN_BY = PSEUDO_STATE__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Choice</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE___GET_INPUTS__ELIST = PSEUDO_STATE___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE___GET_INVOCATIONS__ELIST = PSEUDO_STATE___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE___GET_ALL_INPUTS__ELIST = PSEUDO_STATE___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE___GET_ALL_INVOCATIONS__ELIST = PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE___GET_ALL_OUTPUTS__ELIST = PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE___GET_ALL_CALLS__ELIST = PSEUDO_STATE___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE___OVERRIDES__FLOWELEMENT = PSEUDO_STATE___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Choice</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.EndImpl <em>End</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.EndImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getEnd()
	 * @generated
	 */
	int END = 13;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__DELIVERABLES = PSEUDO_STATE__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__OVERRIDES = PSEUDO_STATE__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__OVERRIDEN_BY = PSEUDO_STATE__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>End</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END___GET_INPUTS__ELIST = PSEUDO_STATE___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END___GET_INVOCATIONS__ELIST = PSEUDO_STATE___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END___GET_ALL_INPUTS__ELIST = PSEUDO_STATE___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END___GET_ALL_INVOCATIONS__ELIST = PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END___GET_ALL_OUTPUTS__ELIST = PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END___GET_ALL_CALLS__ELIST = PSEUDO_STATE___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END___OVERRIDES__FLOWELEMENT = PSEUDO_STATE___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>End</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.EntryPointImpl <em>Entry Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.EntryPointImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getEntryPoint()
	 * @generated
	 */
	int ENTRY_POINT = 14;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__DELIVERABLES = PSEUDO_STATE__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__OVERRIDES = PSEUDO_STATE__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__OVERRIDEN_BY = PSEUDO_STATE__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Entry Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT___GET_INPUTS__ELIST = PSEUDO_STATE___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT___GET_INVOCATIONS__ELIST = PSEUDO_STATE___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT___GET_ALL_INPUTS__ELIST = PSEUDO_STATE___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT___GET_ALL_INVOCATIONS__ELIST = PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT___GET_ALL_OUTPUTS__ELIST = PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT___GET_ALL_CALLS__ELIST = PSEUDO_STATE___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT___OVERRIDES__FLOWELEMENT = PSEUDO_STATE___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Entry Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ExitPointImpl <em>Exit Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ExitPointImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExitPoint()
	 * @generated
	 */
	int EXIT_POINT = 15;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__DELIVERABLES = PSEUDO_STATE__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__OVERRIDES = PSEUDO_STATE__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__OVERRIDEN_BY = PSEUDO_STATE__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Exit Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT___GET_INPUTS__ELIST = PSEUDO_STATE___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT___GET_INVOCATIONS__ELIST = PSEUDO_STATE___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT___GET_ALL_INPUTS__ELIST = PSEUDO_STATE___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT___GET_ALL_INVOCATIONS__ELIST = PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT___GET_ALL_OUTPUTS__ELIST = PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT___GET_ALL_CALLS__ELIST = PSEUDO_STATE___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT___OVERRIDES__FLOWELEMENT = PSEUDO_STATE___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Exit Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ExpansionInputImpl <em>Expansion Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ExpansionInputImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExpansionInput()
	 * @generated
	 */
	int EXPANSION_INPUT = 16;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__DELIVERABLES = PSEUDO_STATE__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__OVERRIDES = PSEUDO_STATE__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__OVERRIDEN_BY = PSEUDO_STATE__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Expansion Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT___GET_INPUTS__ELIST = PSEUDO_STATE___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT___GET_INVOCATIONS__ELIST = PSEUDO_STATE___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT___GET_ALL_INPUTS__ELIST = PSEUDO_STATE___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT___GET_ALL_INVOCATIONS__ELIST = PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT___GET_ALL_OUTPUTS__ELIST = PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT___GET_ALL_CALLS__ELIST = PSEUDO_STATE___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT___OVERRIDES__FLOWELEMENT = PSEUDO_STATE___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Expansion Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ExpansionOutputImpl <em>Expansion Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ExpansionOutputImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExpansionOutput()
	 * @generated
	 */
	int EXPANSION_OUTPUT = 17;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__DELIVERABLES = PSEUDO_STATE__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__OVERRIDES = PSEUDO_STATE__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__OVERRIDEN_BY = PSEUDO_STATE__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Expansion Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT___GET_INPUTS__ELIST = PSEUDO_STATE___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT___GET_INVOCATIONS__ELIST = PSEUDO_STATE___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT___GET_ALL_INPUTS__ELIST = PSEUDO_STATE___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT___GET_ALL_INVOCATIONS__ELIST = PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT___GET_ALL_OUTPUTS__ELIST = PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT___GET_ALL_CALLS__ELIST = PSEUDO_STATE___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT___OVERRIDES__FLOWELEMENT = PSEUDO_STATE___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Expansion Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ForkImpl <em>Fork</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ForkImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFork()
	 * @generated
	 */
	int FORK = 18;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__DELIVERABLES = PSEUDO_STATE__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__OVERRIDES = PSEUDO_STATE__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__OVERRIDEN_BY = PSEUDO_STATE__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Fork</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK___GET_INPUTS__ELIST = PSEUDO_STATE___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK___GET_INVOCATIONS__ELIST = PSEUDO_STATE___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK___GET_ALL_INPUTS__ELIST = PSEUDO_STATE___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK___GET_ALL_INVOCATIONS__ELIST = PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK___GET_ALL_OUTPUTS__ELIST = PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK___GET_ALL_CALLS__ELIST = PSEUDO_STATE___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK___OVERRIDES__FLOWELEMENT = PSEUDO_STATE___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Fork</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.InputPinImpl <em>Input Pin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.InputPinImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getInputPin()
	 * @generated
	 */
	int INPUT_PIN = 19;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__DELIVERABLES = PSEUDO_STATE__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__OVERRIDES = PSEUDO_STATE__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__OVERRIDEN_BY = PSEUDO_STATE__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Input Pin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN___GET_INPUTS__ELIST = PSEUDO_STATE___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN___GET_INVOCATIONS__ELIST = PSEUDO_STATE___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN___GET_ALL_INPUTS__ELIST = PSEUDO_STATE___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN___GET_ALL_INVOCATIONS__ELIST = PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN___GET_ALL_OUTPUTS__ELIST = PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN___GET_ALL_CALLS__ELIST = PSEUDO_STATE___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN___OVERRIDES__FLOWELEMENT = PSEUDO_STATE___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Input Pin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.JoinImpl <em>Join</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.JoinImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getJoin()
	 * @generated
	 */
	int JOIN = 20;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__DELIVERABLES = PSEUDO_STATE__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__OVERRIDES = PSEUDO_STATE__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__OVERRIDEN_BY = PSEUDO_STATE__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Join</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN___GET_INPUTS__ELIST = PSEUDO_STATE___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN___GET_INVOCATIONS__ELIST = PSEUDO_STATE___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN___GET_ALL_INPUTS__ELIST = PSEUDO_STATE___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN___GET_ALL_INVOCATIONS__ELIST = PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN___GET_ALL_OUTPUTS__ELIST = PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN___GET_ALL_CALLS__ELIST = PSEUDO_STATE___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN___OVERRIDES__FLOWELEMENT = PSEUDO_STATE___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Join</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.OutputPinImpl <em>Output Pin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.OutputPinImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getOutputPin()
	 * @generated
	 */
	int OUTPUT_PIN = 21;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__DELIVERABLES = PSEUDO_STATE__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__OVERRIDES = PSEUDO_STATE__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__OVERRIDEN_BY = PSEUDO_STATE__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Output Pin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN___GET_INPUTS__ELIST = PSEUDO_STATE___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN___GET_INVOCATIONS__ELIST = PSEUDO_STATE___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN___GET_ALL_INPUTS__ELIST = PSEUDO_STATE___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN___GET_ALL_INVOCATIONS__ELIST = PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN___GET_ALL_OUTPUTS__ELIST = PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN___GET_ALL_CALLS__ELIST = PSEUDO_STATE___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN___OVERRIDES__FLOWELEMENT = PSEUDO_STATE___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Output Pin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.StartImpl <em>Start</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.StartImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getStart()
	 * @generated
	 */
	int START = 22;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__DELIVERABLES = PSEUDO_STATE__DELIVERABLES;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__OVERRIDES = PSEUDO_STATE__OVERRIDES;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__OVERRIDEN_BY = PSEUDO_STATE__OVERRIDEN_BY;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Start</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START___GET_INPUTS__ELIST = PSEUDO_STATE___GET_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START___GET_INVOCATIONS__ELIST = PSEUDO_STATE___GET_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Inputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START___GET_ALL_INPUTS__ELIST = PSEUDO_STATE___GET_ALL_INPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Invocations</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START___GET_ALL_INVOCATIONS__ELIST = PSEUDO_STATE___GET_ALL_INVOCATIONS__ELIST;

	/**
	 * The operation id for the '<em>Get All Outputs</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START___GET_ALL_OUTPUTS__ELIST = PSEUDO_STATE___GET_ALL_OUTPUTS__ELIST;

	/**
	 * The operation id for the '<em>Get All Calls</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START___GET_ALL_CALLS__ELIST = PSEUDO_STATE___GET_ALL_CALLS__ELIST;

	/**
	 * The operation id for the '<em>Overrides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START___OVERRIDES__FLOWELEMENT = PSEUDO_STATE___OVERRIDES__FLOWELEMENT;

	/**
	 * The number of operations of the '<em>Start</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package</em>'.
	 * @see org.nasdanika.flow.Package
	 * @generated
	 */
	EClass getPackage();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Package#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extends</em>'.
	 * @see org.nasdanika.flow.Package#getExtends()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Extends();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Package#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extensions</em>'.
	 * @see org.nasdanika.flow.Package#getExtensions()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Extensions();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.Package#getSubPackages <em>Sub Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Sub Packages</em>'.
	 * @see org.nasdanika.flow.Package#getSubPackages()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_SubPackages();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Package Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.nasdanika.flow.Package" valueContainment="true"
	 *        valueAnnotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	EClass getPackageEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getPackageEntry()
	 * @generated
	 */
	EAttribute getPackageEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getPackageEntry()
	 * @generated
	 */
	EReference getPackageEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Participant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant</em>'.
	 * @see org.nasdanika.flow.Participant
	 * @generated
	 */
	EClass getParticipant();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Participant#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extends</em>'.
	 * @see org.nasdanika.flow.Participant#getExtends()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Extends();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Participant#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extensions</em>'.
	 * @see org.nasdanika.flow.Participant#getExtensions()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Extensions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.flow.Participant#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see org.nasdanika.flow.Participant#getServices()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Services();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see org.nasdanika.flow.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.flow.Resource#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see org.nasdanika.flow.Resource#getServices()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_Services();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact</em>'.
	 * @see org.nasdanika.flow.Artifact
	 * @generated
	 */
	EClass getArtifact();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Artifact#getRepositories <em>Repositories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Repositories</em>'.
	 * @see org.nasdanika.flow.Artifact#getRepositories()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_Repositories();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.FlowElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.nasdanika.flow.FlowElement
	 * @generated
	 */
	EClass getFlowElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.flow.FlowElement#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outputs</em>'.
	 * @see org.nasdanika.flow.FlowElement#getOutputs()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Outputs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.flow.FlowElement#getCalls <em>Calls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Calls</em>'.
	 * @see org.nasdanika.flow.FlowElement#getCalls()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Calls();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.FlowElement#getInputArtifacts <em>Input Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Input Artifacts</em>'.
	 * @see org.nasdanika.flow.FlowElement#getInputArtifacts()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_InputArtifacts();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.FlowElement#getDeliverables <em>Deliverables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Deliverables</em>'.
	 * @see org.nasdanika.flow.FlowElement#getDeliverables()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Deliverables();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.FlowElement#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participants</em>'.
	 * @see org.nasdanika.flow.FlowElement#getParticipants()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Participants();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.flow.FlowElement#getOverrides <em>Overrides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Overrides</em>'.
	 * @see org.nasdanika.flow.FlowElement#getOverrides()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Overrides();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.FlowElement#getOverridenBy <em>Overriden By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Overriden By</em>'.
	 * @see org.nasdanika.flow.FlowElement#getOverridenBy()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_OverridenBy();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.FlowElement#getModifiers <em>Modifiers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Modifiers</em>'.
	 * @see org.nasdanika.flow.FlowElement#getModifiers()
	 * @see #getFlowElement()
	 * @generated
	 */
	EAttribute getFlowElement_Modifiers();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.FlowElement#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Resources</em>'.
	 * @see org.nasdanika.flow.FlowElement#getResources()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Resources();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.flow.FlowElement#getInputs(org.eclipse.emf.common.util.EList) <em>Get Inputs</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Inputs</em>' operation.
	 * @see org.nasdanika.flow.FlowElement#getInputs(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	EOperation getFlowElement__GetInputs__EList();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.flow.FlowElement#getInvocations(org.eclipse.emf.common.util.EList) <em>Get Invocations</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Invocations</em>' operation.
	 * @see org.nasdanika.flow.FlowElement#getInvocations(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	EOperation getFlowElement__GetInvocations__EList();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.flow.FlowElement#getAllInputs(org.eclipse.emf.common.util.EList) <em>Get All Inputs</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get All Inputs</em>' operation.
	 * @see org.nasdanika.flow.FlowElement#getAllInputs(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	EOperation getFlowElement__GetAllInputs__EList();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.flow.FlowElement#getAllInvocations(org.eclipse.emf.common.util.EList) <em>Get All Invocations</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get All Invocations</em>' operation.
	 * @see org.nasdanika.flow.FlowElement#getAllInvocations(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	EOperation getFlowElement__GetAllInvocations__EList();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.flow.FlowElement#getAllOutputs(org.eclipse.emf.common.util.EList) <em>Get All Outputs</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get All Outputs</em>' operation.
	 * @see org.nasdanika.flow.FlowElement#getAllOutputs(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	EOperation getFlowElement__GetAllOutputs__EList();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.flow.FlowElement#getAllCalls(org.eclipse.emf.common.util.EList) <em>Get All Calls</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get All Calls</em>' operation.
	 * @see org.nasdanika.flow.FlowElement#getAllCalls(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	EOperation getFlowElement__GetAllCalls__EList();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.flow.FlowElement#overrides(org.nasdanika.flow.FlowElement) <em>Overrides</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Overrides</em>' operation.
	 * @see org.nasdanika.flow.FlowElement#overrides(org.nasdanika.flow.FlowElement)
	 * @generated
	 */
	EOperation getFlowElement__Overrides__FlowElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Activity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity</em>'.
	 * @see org.nasdanika.flow.Activity
	 * @generated
	 */
	EClass getActivity();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Activity#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Services</em>'.
	 * @see org.nasdanika.flow.Activity#getServices()
	 * @see #getActivity()
	 * @generated
	 */
	EReference getActivity_Services();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see org.nasdanika.flow.Service
	 * @generated
	 */
	EClass getService();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.flow.Service#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.nasdanika.flow.Service#getTarget()
	 * @see #getService()
	 * @generated
	 */
	EReference getService_Target();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see org.nasdanika.flow.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Transition#getPayload <em>Payload</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Payload</em>'.
	 * @see org.nasdanika.flow.Transition#getPayload()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Payload();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.flow.Transition#isSuppress <em>Suppress</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suppress</em>'.
	 * @see org.nasdanika.flow.Transition#isSuppress()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Suppress();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.flow.Transition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target</em>'.
	 * @see org.nasdanika.flow.Transition#getTarget()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Target();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.flow.Transition#getTarget(org.eclipse.emf.common.util.EList) <em>Get Target</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Target</em>' operation.
	 * @see org.nasdanika.flow.Transition#getTarget(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	EOperation getTransition__GetTarget__EList();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Call <em>Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call</em>'.
	 * @see org.nasdanika.flow.Call
	 * @generated
	 */
	EClass getCall();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Call#getResponse <em>Response</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Response</em>'.
	 * @see org.nasdanika.flow.Call#getResponse()
	 * @see #getCall()
	 * @generated
	 */
	EReference getCall_Response();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Flow <em>Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Flow</em>'.
	 * @see org.nasdanika.flow.Flow
	 * @generated
	 */
	EClass getFlow();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.flow.Flow#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.nasdanika.flow.Flow#getElements()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_Elements();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Flow#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extends</em>'.
	 * @see org.nasdanika.flow.Flow#getExtends()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_Extends();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Flow#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extensions</em>'.
	 * @see org.nasdanika.flow.Flow#getExtensions()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_Extensions();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.flow.Flow#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Root</em>'.
	 * @see org.nasdanika.flow.Flow#getRoot()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_Root();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Flow#getAllElements <em>All Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>All Elements</em>'.
	 * @see org.nasdanika.flow.Flow#getAllElements()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_AllElements();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.PseudoState <em>Pseudo State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pseudo State</em>'.
	 * @see org.nasdanika.flow.PseudoState
	 * @generated
	 */
	EClass getPseudoState();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.flow.PseudoState#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.nasdanika.flow.PseudoState#getType()
	 * @see #getPseudoState()
	 * @generated
	 */
	EAttribute getPseudoState_Type();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Choice <em>Choice</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Choice</em>'.
	 * @see org.nasdanika.flow.Choice
	 * @generated
	 */
	EClass getChoice();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.End <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>End</em>'.
	 * @see org.nasdanika.flow.End
	 * @generated
	 */
	EClass getEnd();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.EntryPoint <em>Entry Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry Point</em>'.
	 * @see org.nasdanika.flow.EntryPoint
	 * @generated
	 */
	EClass getEntryPoint();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.ExitPoint <em>Exit Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exit Point</em>'.
	 * @see org.nasdanika.flow.ExitPoint
	 * @generated
	 */
	EClass getExitPoint();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.ExpansionInput <em>Expansion Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expansion Input</em>'.
	 * @see org.nasdanika.flow.ExpansionInput
	 * @generated
	 */
	EClass getExpansionInput();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.ExpansionOutput <em>Expansion Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expansion Output</em>'.
	 * @see org.nasdanika.flow.ExpansionOutput
	 * @generated
	 */
	EClass getExpansionOutput();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Fork <em>Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fork</em>'.
	 * @see org.nasdanika.flow.Fork
	 * @generated
	 */
	EClass getFork();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.InputPin <em>Input Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Pin</em>'.
	 * @see org.nasdanika.flow.InputPin
	 * @generated
	 */
	EClass getInputPin();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Join <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join</em>'.
	 * @see org.nasdanika.flow.Join
	 * @generated
	 */
	EClass getJoin();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.OutputPin <em>Output Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Pin</em>'.
	 * @see org.nasdanika.flow.OutputPin
	 * @generated
	 */
	EClass getOutputPin();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Start <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start</em>'.
	 * @see org.nasdanika.flow.Start
	 * @generated
	 */
	EClass getStart();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FlowFactory getFlowFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.PackageImpl <em>Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.PackageImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPackage()
		 * @generated
		 */
		EClass PACKAGE = eINSTANCE.getPackage();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__EXTENDS = eINSTANCE.getPackage_Extends();

		/**
		 * The meta object literal for the '<em><b>Extensions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__EXTENSIONS = eINSTANCE.getPackage_Extensions();

		/**
		 * The meta object literal for the '<em><b>Sub Packages</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__SUB_PACKAGES = eINSTANCE.getPackage_SubPackages();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.PackageEntryImpl <em>Package Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.PackageEntryImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPackageEntry()
		 * @generated
		 */
		EClass PACKAGE_ENTRY = eINSTANCE.getPackageEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_ENTRY__KEY = eINSTANCE.getPackageEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_ENTRY__VALUE = eINSTANCE.getPackageEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ParticipantImpl <em>Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ParticipantImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getParticipant()
		 * @generated
		 */
		EClass PARTICIPANT = eINSTANCE.getParticipant();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__EXTENDS = eINSTANCE.getParticipant_Extends();

		/**
		 * The meta object literal for the '<em><b>Extensions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__EXTENSIONS = eINSTANCE.getParticipant_Extensions();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__SERVICES = eINSTANCE.getParticipant_Services();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ResourceImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__SERVICES = eINSTANCE.getResource_Services();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ArtifactImpl <em>Artifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ArtifactImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getArtifact()
		 * @generated
		 */
		EClass ARTIFACT = eINSTANCE.getArtifact();

		/**
		 * The meta object literal for the '<em><b>Repositories</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__REPOSITORIES = eINSTANCE.getArtifact_Repositories();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.FlowElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.FlowElementImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFlowElement()
		 * @generated
		 */
		EClass FLOW_ELEMENT = eINSTANCE.getFlowElement();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__OUTPUTS = eINSTANCE.getFlowElement_Outputs();

		/**
		 * The meta object literal for the '<em><b>Calls</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__CALLS = eINSTANCE.getFlowElement_Calls();

		/**
		 * The meta object literal for the '<em><b>Input Artifacts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__INPUT_ARTIFACTS = eINSTANCE.getFlowElement_InputArtifacts();

		/**
		 * The meta object literal for the '<em><b>Deliverables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__DELIVERABLES = eINSTANCE.getFlowElement_Deliverables();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__PARTICIPANTS = eINSTANCE.getFlowElement_Participants();

		/**
		 * The meta object literal for the '<em><b>Overrides</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__OVERRIDES = eINSTANCE.getFlowElement_Overrides();

		/**
		 * The meta object literal for the '<em><b>Overriden By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__OVERRIDEN_BY = eINSTANCE.getFlowElement_OverridenBy();

		/**
		 * The meta object literal for the '<em><b>Modifiers</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW_ELEMENT__MODIFIERS = eINSTANCE.getFlowElement_Modifiers();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__RESOURCES = eINSTANCE.getFlowElement_Resources();

		/**
		 * The meta object literal for the '<em><b>Get Inputs</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation FLOW_ELEMENT___GET_INPUTS__ELIST = eINSTANCE.getFlowElement__GetInputs__EList();

		/**
		 * The meta object literal for the '<em><b>Get Invocations</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation FLOW_ELEMENT___GET_INVOCATIONS__ELIST = eINSTANCE.getFlowElement__GetInvocations__EList();

		/**
		 * The meta object literal for the '<em><b>Get All Inputs</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation FLOW_ELEMENT___GET_ALL_INPUTS__ELIST = eINSTANCE.getFlowElement__GetAllInputs__EList();

		/**
		 * The meta object literal for the '<em><b>Get All Invocations</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation FLOW_ELEMENT___GET_ALL_INVOCATIONS__ELIST = eINSTANCE.getFlowElement__GetAllInvocations__EList();

		/**
		 * The meta object literal for the '<em><b>Get All Outputs</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation FLOW_ELEMENT___GET_ALL_OUTPUTS__ELIST = eINSTANCE.getFlowElement__GetAllOutputs__EList();

		/**
		 * The meta object literal for the '<em><b>Get All Calls</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation FLOW_ELEMENT___GET_ALL_CALLS__ELIST = eINSTANCE.getFlowElement__GetAllCalls__EList();

		/**
		 * The meta object literal for the '<em><b>Overrides</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation FLOW_ELEMENT___OVERRIDES__FLOWELEMENT = eINSTANCE.getFlowElement__Overrides__FlowElement();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ActivityImpl <em>Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ActivityImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getActivity()
		 * @generated
		 */
		EClass ACTIVITY = eINSTANCE.getActivity();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY__SERVICES = eINSTANCE.getActivity_Services();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ServiceImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getService()
		 * @generated
		 */
		EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__TARGET = eINSTANCE.getService_Target();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.TransitionImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>Payload</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__PAYLOAD = eINSTANCE.getTransition_Payload();

		/**
		 * The meta object literal for the '<em><b>Suppress</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__SUPPRESS = eINSTANCE.getTransition_Suppress();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__TARGET = eINSTANCE.getTransition_Target();

		/**
		 * The meta object literal for the '<em><b>Get Target</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TRANSITION___GET_TARGET__ELIST = eINSTANCE.getTransition__GetTarget__EList();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.CallImpl <em>Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.CallImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getCall()
		 * @generated
		 */
		EClass CALL = eINSTANCE.getCall();

		/**
		 * The meta object literal for the '<em><b>Response</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL__RESPONSE = eINSTANCE.getCall_Response();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.FlowImpl <em>Flow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.FlowImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFlow()
		 * @generated
		 */
		EClass FLOW = eINSTANCE.getFlow();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__ELEMENTS = eINSTANCE.getFlow_Elements();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__EXTENDS = eINSTANCE.getFlow_Extends();

		/**
		 * The meta object literal for the '<em><b>Extensions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__EXTENSIONS = eINSTANCE.getFlow_Extensions();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__ROOT = eINSTANCE.getFlow_Root();

		/**
		 * The meta object literal for the '<em><b>All Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__ALL_ELEMENTS = eINSTANCE.getFlow_AllElements();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.PseudoStateImpl <em>Pseudo State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.PseudoStateImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPseudoState()
		 * @generated
		 */
		EClass PSEUDO_STATE = eINSTANCE.getPseudoState();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PSEUDO_STATE__TYPE = eINSTANCE.getPseudoState_Type();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ChoiceImpl <em>Choice</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ChoiceImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getChoice()
		 * @generated
		 */
		EClass CHOICE = eINSTANCE.getChoice();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.EndImpl <em>End</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.EndImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getEnd()
		 * @generated
		 */
		EClass END = eINSTANCE.getEnd();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.EntryPointImpl <em>Entry Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.EntryPointImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getEntryPoint()
		 * @generated
		 */
		EClass ENTRY_POINT = eINSTANCE.getEntryPoint();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ExitPointImpl <em>Exit Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ExitPointImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExitPoint()
		 * @generated
		 */
		EClass EXIT_POINT = eINSTANCE.getExitPoint();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ExpansionInputImpl <em>Expansion Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ExpansionInputImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExpansionInput()
		 * @generated
		 */
		EClass EXPANSION_INPUT = eINSTANCE.getExpansionInput();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ExpansionOutputImpl <em>Expansion Output</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ExpansionOutputImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExpansionOutput()
		 * @generated
		 */
		EClass EXPANSION_OUTPUT = eINSTANCE.getExpansionOutput();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ForkImpl <em>Fork</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ForkImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFork()
		 * @generated
		 */
		EClass FORK = eINSTANCE.getFork();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.InputPinImpl <em>Input Pin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.InputPinImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getInputPin()
		 * @generated
		 */
		EClass INPUT_PIN = eINSTANCE.getInputPin();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.JoinImpl <em>Join</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.JoinImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getJoin()
		 * @generated
		 */
		EClass JOIN = eINSTANCE.getJoin();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.OutputPinImpl <em>Output Pin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.OutputPinImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getOutputPin()
		 * @generated
		 */
		EClass OUTPUT_PIN = eINSTANCE.getOutputPin();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.StartImpl <em>Start</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.StartImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getStart()
		 * @generated
		 */
		EClass START = eINSTANCE.getStart();

	}

} //FlowPackage
